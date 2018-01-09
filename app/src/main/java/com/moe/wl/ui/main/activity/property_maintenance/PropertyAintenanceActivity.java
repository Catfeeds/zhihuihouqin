package com.moe.wl.ui.main.activity.property_maintenance;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.Imglibrary.ImageSelector;
import com.moe.wl.ui.main.activity.me.BuildNumAct;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.main.adapter.WeixiuAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.PropertyOrderInfo;
import com.moe.wl.ui.main.bean.RepairItmeBean;
import com.moe.wl.ui.main.model.WuyeHomeModel;
import com.moe.wl.ui.main.modelimpl.WuyeHomeModelImpl;
import com.moe.wl.ui.main.presenter.WuyeHomePresenter;
import com.moe.wl.ui.main.view.WuyeHomeView;
import com.moe.wl.ui.mywidget.ShowHintDialog;
import com.moe.wl.ui.mywidget.StringListDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import mvp.cn.util.DateUtil;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

public class PropertyAintenanceActivity extends BaseActivity<WuyeHomeModel, WuyeHomeView, WuyeHomePresenter> implements WuyeHomeView {

    private static final int ORDERTIMEREQUEST = 10;
    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.et_service_address)
    EditText etServiceAddress;
    @BindView(R.id.nsgv_service_type)
    NoSlidingGridView nsgvServiceType;
    @BindView(R.id.et_baoxiu_detail)
    EditText etBaoxiuDetail;
    @BindView(R.id.gv_addPhoto)
    NoSlidingGridView gvAddPhoto;
    @BindView(R.id.tv_now_posted)
    TextView tvNowPosted;
    @BindView(R.id.rl_time)
    LinearLayout rlTime;
    @BindView(R.id.tv_yuyue_time)
    TextView tvYuyueTime;
    @BindView(R.id.nsgv_service_type2)
    NoSlidingGridView getNsgvServiceType2;
    @BindView(R.id.ll_build_num)
    LinearLayout llBuildNum;
    @BindView(R.id.tv_build_num)
    TextView tvBuildNum;
    private List<RepairItmeBean.ItemlistEntity> data;
    private String mobile;
    private WeixiuAdapter weixiuAdapter;
    private int menditem;
    private String realName;
    private String address;
    //    private ProertyAddPicAdapter picAdapter;
    private GridViewImageAdapter imageAdapter;
    private ArrayList<String> pics;
    private int maxNum=6;
    private int imageSpanCount=3;
    private int REQUEST_IMAGE=1;
    private ArrayList<String> mSelectPath;
    private String date;
    private int intervalid;
    private List<PropertyOrderInfo> infoList;
    private int BUILDNUMREQUEST=11;
    private int buildNum;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_property_aintenance);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        //初始化预定信息结合
        infoList = new ArrayList<>();
        mobile = SharedPrefHelper.getInstance().getMobile();
        realName = SharedPrefHelper.getInstance().getRealName();
        tvName.setText(realName);
        etPhoneNum.setText(mobile);
        etPhoneNum.setSelection(mobile.length());
        initTitle();
        getHint();
        pics = new ArrayList<>();
        pics.add("addPhoto");
        initAddPhotoGrid();
//        List<String> firSercives= Arrays.asList("公共维修");
//        getNsgvServiceType2.getChildAt(0).performClick();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT);
                // data  ..
                pics.clear();
                pics.add("addPhoto");
                pics.addAll(0,mSelectPath);
                LogUtils.i("mSelectPath=="+mSelectPath.size()+"======="+mSelectPath.get(0));
                imageAdapter.notifyDataSetChanged();
            }
        }else if(requestCode==ORDERTIMEREQUEST){
            if (resultCode == RESULT_OK) {
                String time = data.getStringExtra("time");
                date = data.getStringExtra("date");
                intervalid = data.getIntExtra("id", 1);
                infoList.clear();
                infoList.add(new PropertyOrderInfo(date, intervalid+""));
                tvYuyueTime.setText(time);

            }
        }else if(requestCode==BUILDNUMREQUEST){
            if(resultCode==RESULT_OK){
                String buildname = data.getStringExtra("buildname");
                tvBuildNum.setText(buildname);
                buildNum = data.getIntExtra("buildNum", 0);
            }
        }
    }
    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.PROPERRY);
        showProgressDialog();
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable==hint", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.errCode == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.errCode == 0) {
                    //获得维修项目
                    getPresenter().getRepairItem();
                    //初始化首页信息
                    BannerResponse.ServiceInfoBean bean = mResponse.getServiceInfo();

                    // TODO 弹出温馨提示窗
                    if (!SharedPrefHelper.getInstance().getServiceHint(Constants.PROPERRY)) {
                        final ShowHintDialog pop = new ShowHintDialog(PropertyAintenanceActivity.this, mResponse.getServiceInfo().getRemind(), Constants.PROPERRY);
                        pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                            @Override
                            public void onSetting(TextView content) {
                                pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(PropertyAintenanceActivity.this, 280));
                            }
                        });
                        pop.show();
                    }
                } else {
                    ToastUtil.showToast(PropertyAintenanceActivity.this, mResponse.msg);
                }
            }
        });
    }
    private void initAddPhotoGrid() {
        imageAdapter = new GridViewImageAdapter(this, pics, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                ImageSelector selector = ImageSelector.create();
                // selector.single();  // single mode
                selector.multi();  // multi mode, default mode;
                selector.origin(mSelectPath) // original select data set, used width #.multi()
                        .showCamera(true)   // show camera or not. true by default
                        .count(maxNum)   // max select image size, 9 by default. used width #.multi()
                        .spanCount(imageSpanCount)  // image span count ，default is 3.
                        .start(PropertyAintenanceActivity.this, REQUEST_IMAGE);

            }
        });
        gvAddPhoto.setAdapter(imageAdapter);
    }

    private void initGrid(RepairItmeBean bean) {
        data = new ArrayList<>();
        data.addAll(bean.getItemlist());
        weixiuAdapter = new WeixiuAdapter();
        nsgvServiceType.setAdapter(weixiuAdapter);
        weixiuAdapter.setDatas(data);
        weixiuAdapter.setOnclickListener(new WeixiuAdapter.OnclickListener() {
            @Override
            public void onClick(int id) {
                menditem = id;
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("物业维修");
    }

    @OnClick({R.id.rl_time, R.id.tv_now_posted,R.id.iv_hint,R.id.ll_build_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_time:
                Intent intent = new Intent(PropertyAintenanceActivity.this, OrderTimeActivity.class);
                startActivityForResult(intent, ORDERTIMEREQUEST);
                break;
            case R.id.ll_build_num:
                Intent intent1 = new Intent(PropertyAintenanceActivity.this, BuildNumAct.class);
                startActivityForResult(intent1, BUILDNUMREQUEST);
                break;
            case R.id.tv_now_posted:
                doPost();
                break;
            case R.id.iv_hint:
                SharedPrefHelper.getInstance().setServiceHint(Constants.PROPERRY, false);
                getHint();
                break;
        }
    }
    private void doPost() {
        String invitetime = tvYuyueTime.getText().toString().trim();
        String phone = etPhoneNum.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入电话号码");
            return;
        }

        if (!VerifyCheck.isMobilePhoneVerify(phone) ) {
            showToast("请输入正确的手机号");
            return;
        }
        if (menditem == 0) {
            showToast("请选择服务分类");
            return;
        }
        if (TextUtils.isEmpty(etServiceAddress.getText().toString().trim())) {
            showToast("地址不能为空");
            return;
        } else {
            address = etServiceAddress.getText().toString().trim();
        }
        String mendcontent = etBaoxiuDetail.getText().toString().trim();
//        pics.remove(pics.size() - 1);
        if (TextUtils.isEmpty(mendcontent)) {
            showToast("请填写报修详情");
            return;
        }
//        if (pics.size() < 1) {
//            showToast("请上传维修图片");
//            return;
//        }
        // 发布传入图片文件
        getPresenter().getData(infoList,menditem, realName, mobile, invitetime, address, mendcontent, pics);
    }

    @Override
    public void getWuyeHomeResult(ActivityPostBean wuyeHomeBean) {
        if (wuyeHomeBean.getErrCode() == 0) {
            //发布成功清空填写内容
            etPhoneNum.setText("");
            tvYuyueTime.setText("");
            etServiceAddress.setText("");
            etBaoxiuDetail.setText("");
            pics.clear();
            pics.add("addPhoto");
            imageAdapter.notifyDataSetChanged();
            Intent intent = new Intent(this, ProPerrtyPostSuccAct.class);
            startActivity(intent);
        } else {
            showToast("发布失败了");
        }
    }

    @Override
    public void getRepairItemSucc(RepairItmeBean bean) {
        initGrid(bean);
    }
    @Override
    public WuyeHomePresenter createPresenter() {
        return new WuyeHomePresenter();
    }

    @Override
    public WuyeHomeModel createModel() {
        return new WuyeHomeModelImpl();
    }

}
