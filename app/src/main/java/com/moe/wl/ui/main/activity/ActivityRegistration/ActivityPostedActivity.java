package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.SystemClock;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.Imglibrary.ImageSelector;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.ActivityPostMulitPicAdapter;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.Demand;
import com.moe.wl.ui.mywidget.BottomTimeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

/**
 * 发布活动
 */
public class ActivityPostedActivity extends Base2Activity implements View.OnClickListener {
    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.iv_posted_des)
    ImageView ivPostedDes;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_posted_name)
    EditText etPostedName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_activity_time)
    ImageView etActivityTime;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.et_zhubanfang)
    EditText etZhubanfang;
    @BindView(R.id.et_theme)
    EditText etTheme;
    @BindView(R.id.et_activity_address)
    EditText etActivityAddress;
    @BindView(R.id.et_person_limit)
    EditText etPersonLimit;
    @BindView(R.id.et_activity_content)
    EditText etActivityContent;
    @BindView(R.id.add_photo)
    ImageView addPhoto;
    @BindView(R.id.gv_detail_pic)
    NoSlidingGridView gvDetailPic;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_posted)
    LinearLayout activityPosted;
    @BindView(R.id.cb_sex)
    CheckBox cbSex;
    @BindView(R.id.cb_age)
    CheckBox cbAge;
    @BindView(R.id.cb_phone)
    CheckBox cbPhone;
    @BindView(R.id.ll_check)
    LinearLayout llCheck;
    @BindView(R.id.cb_nation)
    CheckBox cbNation;
    @BindView(R.id.cb_dapart)
    CheckBox cbDapart;
    @BindView(R.id.cb_office)
    CheckBox cbOffice;
    @BindView(R.id.position)
    CheckBox position;
    @BindView(R.id.cb_name)
    CheckBox cbName;

    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_complain.jpg";
    private BottomSheetDialog dialog;
    private Bitmap photo;
    private String picPath;
    private boolean isOne;
    private ActivityPostMulitPicAdapter picAdapter;
    private String imageUri;
    private ArrayList<String> paths;
    private ArrayList<String> pics;
    private int REQUEST_IMAGE_ONE = 1;
    private int REQUEST_IMAGE_MANY = 2;
    private GridViewImageAdapter imageAdapter;
    private ArrayList<String> mpic;
    private int cbname;
    private int cbage;
    private int cbsex;
    private int cbphone;
    private int cbnation;
    private int cboffice;
    private int cbdapart;
    private int cbposition;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_posted);
        ButterKnife.bind(this);
    }


    @Override
    protected void initView() {
        //单张图片的集合
        paths = new ArrayList<>();
        //多张图片的集合
        pics = new ArrayList<>();
        pics.add("addPhoto");
        initTitle();
        initGrlid();
    }

    private void initGrlid() {

        imageAdapter = new GridViewImageAdapter(this, pics, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                ImageSelector selector = ImageSelector.create();
                // selector.single();  // single mode
                selector.multi();  // multi mode, default mode;
                selector.origin(mpic) // original select data set, used width #.multi()
                        .showCamera(true)   // show camera or not. true by default
                        .count(6)   // max select image size, 9 by default. used width #.multi()
                        .spanCount(3)  // image span count ，default is 3.
                        .start(ActivityPostedActivity.this, REQUEST_IMAGE_MANY);

            }
        });
        gvDetailPic.setAdapter(imageAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("活动发布");
    }

    @OnClick({R.id.iv_posted_des, R.id.add_photo, R.id.rl_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_posted_des:
                Intent intent = new Intent(this, PostedNotesActivity.class);
                startActivity(intent);
                break;
            case R.id.add_photo:

                ImageSelector selector = ImageSelector.create();
                selector.single();  // single mode
                selector.multi();  // multi mode, default mode;
                selector.origin(paths) // original select data set, used width #.multi()
                        .showCamera(true)   // show camera or not. true by default
                        .count(1)   // max select image size, 9 by default. used width #.multi()
                        .spanCount(3)  // image span count ，default is 3.
                        .start(ActivityPostedActivity.this, REQUEST_IMAGE_ONE);

                break;
            case R.id.rl_time:
                //底部弹出框
                showPop();
                break;
            case R.id.tv_submit://发布
                String name = etPostedName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String time = tvTime.getText().toString().trim();
                String address = etActivityAddress.getText().toString().trim();
                String limit = etPersonLimit.getText().toString().trim();
                String content = etActivityContent.getText().toString().trim();
                String zhubanfang = etZhubanfang.getText().toString().trim();
                String theme = etTheme.getText().toString().trim();
                if (cbName.isChecked()) {
                    cbname = 1;
                } else {
                    cbname = 0;
                }
                if (cbAge.isChecked()) {
                    cbage = 1;
                } else {
                    cbage = 0;
                }
                if (cbSex.isChecked()) {
                    cbsex = 1;
                } else {
                    cbsex = 0;
                }
                if (cbPhone.isChecked()) {
                    cbphone = 1;
                } else {
                    cbphone = 0;
                }
                if (cbNation.isChecked()) {
                    cbnation = 1;
                } else {
                    cbnation = 0;
                }
                if (cbOffice.isChecked()) {
                    cboffice = 1;
                } else {
                    cboffice = 0;
                }
                if (cbDapart.isChecked()) {
                    cbdapart = 1;
                } else {
                    cbdapart = 0;
                }
                if (position.isChecked()) {
                    cbposition = 1;
                } else {
                    cbposition = 0;
                }
                Demand demand = new Demand(cbname,cbage,cbdapart,cbposition,cboffice,cbphone,cbsex,cbnation);
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(content) || TextUtils.isEmpty(address) ||
                        TextUtils.isEmpty(zhubanfang) || TextUtils.isEmpty(time) || TextUtils.isEmpty(theme) ||
                        TextUtils.isEmpty(limit) || TextUtils.isEmpty(name)) {
                    showToast("请将信息填写完整");
                    return;
                }
                if (!VerifyCheck.isMobilePhoneVerify(phone)) {
                    showToast("您输入的手机号码有误请重新输入");
                    return;
                }
                if (DateUtil.compareDate(time, DateUtil.getTimeyyyyMMddHHmm()) == 2) {
                    showToast("活动时间不能早于当前时间");
                    return;
                }
                if (TextUtils.isEmpty(paths.get(0))) {
                    showToast("请选择活动的封面图");
                    return;
                }
                if (pics.size() <= 1) {
                    showToast("请选择活动详情图片");
                    return;
                }
                //checkIsNull(phone, content, address, zhubanfang, time, theme, limit, name, minzu);
                postActivity(demand,phone, content, address, zhubanfang, time, theme, limit, paths, pics, name);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_ONE) {
                paths = data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT);
                // data  ..
                GlideLoading.getInstance().loadImgUrlNyImgLoader(this, paths.get(0), addPhoto);
            } else if (requestCode == REQUEST_IMAGE_MANY) {
                mpic = data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT);
                pics.clear();
                pics.add("addPhoto");
                pics.addAll(0, mpic);
                for (int i = 0; i < pics.size(); i++) {
                    LogUtils.i("mSelectPath==" + pics.size() + "=======" + pics.get(i));
                }

                imageAdapter.notifyDataSetChanged();

            }
        }
    }

    //时间选择器
    private void showPop() {
        BottomTimeDialog dialog = new BottomTimeDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int i1, int i2, int i3, String i4, String i5) {
                String time = i1 + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5;
                tvTime.setText(time);

            }
        });
    }

    private void postActivity(Demand demand,String aContactMobile, String aContent, String aPlace
            , String aSponsor, String aTime, String aTitle, String aTotal, List<String> photos, List<String> imgs, String aRealname) {
        imgs.remove(imgs.get(imgs.size() - 1));//将最后一个图片从集合中清除
        for (String str : imgs) {
            LogUtils.i("多张图片：" + str);
        }
        Observable observable = RetrofitUtils.getInstance().postActivity(aContactMobile, aContent, aPlace, aSponsor, aTime, aTitle,
                aTotal, photos, imgs, aRealname, demand);
        showProgressDialog();
        observable.subscribe(new Subscriber<ActivityPostBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("发布：" + e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean o) {
                if (o.getErrCode() == 0) {
                    showToast("发布成功");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(2);
                            finish();
                        }
                    }).start();
                } else {
                    showToast("发布失败了");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                dialog.dismiss();
                break;
            case R.id.tv_confirm:
                dialog.dismiss();
                break;
        }
    }

}
