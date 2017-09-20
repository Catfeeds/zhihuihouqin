package cn.lc.model.ui.main.activity.property_maintenance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ProertyAddPicAdapter;
import cn.lc.model.ui.main.adapter.WeixiuAdapter;
import cn.lc.model.ui.main.bean.WuyeHomeBean;
import cn.lc.model.ui.main.model.WuyeHomeModel;
import cn.lc.model.ui.main.modelimpl.WuyeHomeModelImpl;
import cn.lc.model.ui.main.presenter.WuyeHomePresenter;
import cn.lc.model.ui.main.view.WuyeHomeView;
import cn.lc.model.ui.mywidget.StringListDialog;

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
    /*@BindView(R.id.iv_up_photo)
    ImageView ivUpPhoto;*/
    @BindView(R.id.gv_addPhoto)
    GridView gvAddPhoto;
    @BindView(R.id.tv_now_posted)
    TextView tvNowPosted;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.tv_yuyue_time)
    TextView tvYuyueTime;
    private List<String> lists = Arrays.asList(
            "马桶疏通", "水电维修", "房屋维修",
            "开锁/换锁", "线路维修", "其他");
    private String mobile;
    private WeixiuAdapter weixiuAdapter;
    private int selectPosition;
    private String realName;
    private String address;
    private ProertyAddPicAdapter picAdapter;
    private List<String> pics;

    @Override
    public WuyeHomePresenter createPresenter() {
        return new WuyeHomePresenter();
    }

    @Override
    public WuyeHomeModel createModel() {
        return new WuyeHomeModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_property_aintenance);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mobile = SharedPrefHelper.getInstance().getMobile();
        realName = SharedPrefHelper.getInstance().getRealName();
        tvName.setText(realName);
        etPhoneNum.setText(mobile);
        etPhoneNum.setSelection(mobile.length());
        initTitle();
        initGrid();
        initAddPhotoGrid();
        pics = new ArrayList<>();
        pics.add("addPhoto");

    }

    private void initAddPhotoGrid() {
        picAdapter = new ProertyAddPicAdapter(this);
        gvAddPhoto.setAdapter(picAdapter);
        picAdapter.setListener(new ProertyAddPicAdapter.OnClickListener() {
            @Override
            public void addPhotoClick() {
               //打开
                final StringListDialog dialog = new StringListDialog(PropertyAintenanceActivity.this, R.style.dialog_style);
                List<String> itemList = new ArrayList<String>();
                itemList.add("相机拍摄");
                itemList.add("手机相册");
                itemList.add("取消");
                dialog.setData(itemList);
                dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        switch (position) {
                            case 0:// 拍照上传
                                //doTakePhoto();
                                dialog.dismiss();
                                break;
                            case 1:// 从gallery选择
                                //doPickPhotoFromGallery();
                                dialog.dismiss();
                                break;
                            case 2:// 取消
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    private void initGrid() {
        weixiuAdapter = new WeixiuAdapter();
        nsgvServiceType.setAdapter(weixiuAdapter);
        weixiuAdapter.setDatas(lists);
        selectPosition = weixiuAdapter.getSelectPosition();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("物业维修");
    }

    @OnClick({R.id.rl_time, R.id.tv_now_posted})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_time:
                Intent intent = new Intent(PropertyAintenanceActivity.this, OrderTimeActivity.class);
                startActivityForResult(intent, ORDERTIMEREQUEST);
                break;
            case R.id.tv_now_posted:
                doPost();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                String time = data.getStringExtra("time");
                tvYuyueTime.setText(time);
            }
        }
    }

    private void doPost() {
        String invitetime = tvYuyueTime.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            showToast("请输入电话号码");
            return;
        }
        if (TextUtils.isEmpty(etServiceAddress.getText().toString().trim())) {
            showToast("地址不能为空");
            return;
        } else {
            address = etServiceAddress.getText().toString().trim();
        }
        int phone = Integer.parseInt(mobile);
        String mendcontent = etBaoxiuDetail.getText().toString().trim();
        // TODO: 2017/9/6 0006 传入图片文件
        // getPresenter().getData(selectPosition,realName,phone,invitetime,address,mendcontent);
    }

    @Override
    public void getWuyeHomeResult(WuyeHomeBean wuyeHomeBean) {
        if (wuyeHomeBean.getErrCode() == 0) {
            showToast("发布成功");
        }
    }

}
