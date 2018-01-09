package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.SignUpPersonAdapter;
import com.moe.wl.ui.main.bean.ActIndexBean;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.ActivitySignListBean;
import com.moe.wl.ui.main.bean.ActivitylistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * 活动详情
 */
public class ActivityDetailActivity extends Base2Activity {

    @BindView(R.id.activity_title)
    TitleBar activityTitle;
    /*@BindView(R.id.iv_big_pic)
    ImageView ivBigPic;*/
    @BindView(R.id.slider_layout)
    SliderLayout sliderLayout;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.tv_act_num)
    TextView tvActNum;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_posted_time)
    TextView tvPostedTime;
    @BindView(R.id.tv_zhubanfang)
    TextView tvZhubanfang;
    @BindView(R.id.tv_sign_up_num)
    TextView tvSignUpNum;
    @BindView(R.id.nsrv_sign_up)
    NoSlidingListView nsrvSignUp;
    @BindView(R.id.activity_detail)
    LinearLayout activityDetail;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.tv_jianjie)
    TextView tvJianjie;
    @BindView(R.id.tv_theme)
    TextView title;

    private SignUpPersonAdapter rvAdapter;
    private ActivitylistBean activitylistBean;
    private ActivitySignListBean activitySignListBean;
    private String realName;
    private String phoneNumber;
    private boolean isSign;

    private List<ActivitySignListBean.MemberlistBean> data;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        activitylistBean = (ActivitylistBean) getIntent().getSerializableExtra("activitylistBean");
        realName = SharedPrefHelper.getInstance().getRealName();
        if (TextUtils.isEmpty(realName)){
            realName = SharedPrefHelper.getInstance().getNickname();
        }
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        //getSignData(activitylistBean.getAId(), realName, phoneNumber);
        getSignList(activitylistBean.getAId());
        setData();
        initTitle();
        initRecycler();
        //设置scrollView里面的内容置顶
        sv.smoothScrollTo(0, 20);
        sv.setFocusable(true);

    }

    private void setData() {
        /*GlideLoading.getInstance().loadImgUrlNyImgLoader(this,
                activitylistBean.getAImg(), ivBigPic);*/
       /* List<String> picPaths=new ArrayList<>();
        String aImg = activitylistBean.getAImg();
        String[] split = aImg.split(",");
        for (int i = 0; i < split.length; i++) {
            picPaths.add(split[i]);
        }*/
        List<String> picPaths = activitylistBean.getaPhoto();
        sliderLayout.removeAllSliders();
        for (int i = 0; i < picPaths.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(ActivityDetailActivity.this);
            textSliderView.description("").image(picPaths.get(i));
            sliderLayout.addSlider(textSliderView);
        }

        LogUtils.i("getATitle=="+activitylistBean.getATitle());
        title.setText(activitylistBean.getATitle());
        tvAddress.setText("活动地点：" + activitylistBean.getAPlace());
        phone.setText("场馆电话：" + activitylistBean.getAContactMobile());
        tvActNum.setText("活动人数：" + activitylistBean.getATotal() + "人");
        if (activitylistBean.getAStatus() == 1) {
            tvState.setText("报名进行中");
        } else if (activitylistBean.getAStatus() == 2) {
            tvState.setText("报名结束");
        }
        tvPostedTime.setText(activitylistBean.getACreateTime() + "发布");
        tvZhubanfang.setText(activitylistBean.getASponsor());
        tvJianjie.setText(activitylistBean.getAContent());


//        tvSignUpNum.setText(activitylistBean.getASignCount() + "人");
    }

    private void getSignList(int aId) {
        Observable observable = RetrofitUtils.getInstance().getActivitySignList(aId);
        showProgressDialog();
        observable.subscribe(new Subscriber<ActivitySignListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                dismissProgressDialog();
            }

            @Override
            public void onNext(ActivitySignListBean mResponse) {
                if (mResponse.getErrCode() == 0) {
                    data.addAll(mResponse.getMemberlist());
                    tvSignUpNum.setText(data.size() + "人");
                    activitySignListBean = mResponse;
                    LogUtils.d("----------222------");
                    rvAdapter.notifyDataSetChanged();
                } else {
                    LogUtils.d("----------------");
                }
            }
        });
    }

    private void initRecycler() {
        data = new ArrayList<>();
        rvAdapter = new SignUpPersonAdapter(this);
        rvAdapter.setItemList(data);
        nsrvSignUp.setAdapter(rvAdapter);

        // TODO: 2017/9/8 0008 根据服务端返回登录状态,修改登录字体
        isSign = false;
        if (isSign == true) {
            tvSignUp.setText("已报名");
        } else {
            tvSignUp.setText("报名");
        }

    }

    private void initTitle() {
        activityTitle.setBack(true);
        activityTitle.setTitle("活动详情");
    }

    @OnClick(R.id.tv_sign_up)
    public void onViewClicked() {
        if (isSign == true) {
            showToast("你已经报名了");
        } else {
            Intent intent = new Intent(this, FillInfoActivity.class);
            intent.putExtra("aId", activitylistBean.getAId());
            intent.putExtra("name", realName);
            intent.putExtra("phone", phoneNumber);
            startActivity(intent);
        }

    }


}
