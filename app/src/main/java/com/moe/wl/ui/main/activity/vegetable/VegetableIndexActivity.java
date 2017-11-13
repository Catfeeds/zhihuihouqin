package com.moe.wl.ui.main.activity.vegetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.login.activity.LoginActivity;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.mywidget.AlertDialog;
import com.moe.wl.ui.mywidget.ShowHintDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 我的电脑 on 2017/11/9 0009.
 */

public class VegetableIndexActivity extends MyBaseActivity {
    @BindView(R.id.dry_cleaners_title)
    TitleBar title;
    @BindView(R.id.iv_show_dialog)
    ImageView ivShowDialog;
    @BindView(R.id.slider_layout)
    SliderLayout sliderLayout;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.iv_cut_hear_logo)
    ImageView ivCutHearLogo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.iv_hot_phone)
    ImageView ivHotPhone;
    @BindView(R.id.message_hint)
    TextView messageHint;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_cleaners);
        ButterKnife.bind(this);
        initTitle();
        tvNowOrder.setText("立即预订");
        getHint();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("净菜预订");
    }

    @OnClick({R.id.ll_call, R.id.tv_now_order, R.id.iv_show_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_call:
                break;
            case R.id.tv_now_order:
                Intent intent = new Intent(this, VegetableMainActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_show_dialog:
                SharedPrefHelper.getInstance().setServiceHint(Constants.VEGETABLE, false);
                getHint();
                break;
        }
    }

    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.VEGETABLE);
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
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
                    //初始化首页信息
                    BannerResponse.ServiceInfoBean bean = mResponse.getServiceInfo();
                    String place = bean.getPlace();
                    String businesshour = bean.getBusinesshour();
                    String mobile = bean.getMobile();
                    tvAddress.setText(place);
                    tvWorkTime.setText(businesshour);
                    tvPhone.setText(mobile);
                    //获得轮播图
                    //serviceInfo.get
                    if (bean != null && !TextUtils.isEmpty(bean.getTopphoto())) {
                        //this.bean=bean;
                        String[] strings = bean.getTopphoto().split(",");
                        sliderLayout.removeAllSliders();
                        for (int i = 0; i < strings.length; i++) {
                            TextSliderView textSliderView = new TextSliderView(VegetableIndexActivity.this);
                            textSliderView.description("").image(strings[i]);
                            sliderLayout.addSlider(textSliderView);
                        }
                    }

                    // TODO 弹出温馨提示窗
                    if (!SharedPrefHelper.getInstance().getServiceHint(Constants.VEGETABLE)) {
                        final ShowHintDialog pop = new ShowHintDialog(VegetableIndexActivity.this, mResponse.getServiceInfo().getRemind(), Constants.VEGETABLE);
                        pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                            @Override
                            public void onSetting(TextView content) {
                                pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(VegetableIndexActivity.this, 280));
                            }
                        });
                        pop.show();
                    }
                } else {
                    ToastUtil.showToast(VegetableIndexActivity.this, mResponse.msg);
                }
            }
        });
    }

    public void reLogin(String msg) {
        if (!TextUtils.isEmpty(msg))
            ToastUtil.showToast(this, msg);
        SharedPrefHelper.getInstance().setPassword("");
        SharedPrefHelper.getInstance().setToken("");
        UIManager.turnToAct(this, LoginActivity.class);
        finish();
    }

    private void call(final String mobile) {
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(VegetableIndexActivity.this).builder()
                        .setMsg("拨打服务热线:  " + mobile)
                        .setPositiveButton("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CallPhoneUtils.callPhone(mobile, VegetableIndexActivity.this);
                            }
                        })
                        .setNegativeButton("否", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
            }
        });
    }
}
