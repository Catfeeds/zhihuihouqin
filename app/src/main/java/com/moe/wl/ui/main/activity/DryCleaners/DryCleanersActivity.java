package com.moe.wl.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.SimpleImageBanner;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.framework.widget.bean.BannerItem;
import com.moe.wl.ui.main.activity.vegetable.VegetableIndexActivity;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.model.BannerModel;
import com.moe.wl.ui.main.modelimpl.BannerModelImpl;
import com.moe.wl.ui.main.presenter.BannerPresenter;
import com.moe.wl.ui.main.view.BannerView;
import com.moe.wl.ui.mywidget.AlertDialog;
import com.moe.wl.ui.mywidget.ShowHintDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.LogUtil;

public class DryCleanersActivity extends BaseActivity<BannerModel, BannerView, BannerPresenter> implements BannerView {


    @BindView(R.id.dry_cleaners_title)
    TitleBar titleBar;
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
    @BindView(R.id.iv_hot_phone)
    ImageView ivHotPhone;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.activity_dry_cleaners)
    LinearLayout activityDryCleaners;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.message_hint)
    TextView hint;
    @BindView(R.id.iv_show_dialog)
    ImageView IvShowDialog;
    private BannerResponse.ServiceInfoBean bean;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_dry_cleaners);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        getPresenter().getBanner(16);
    }

    private void call(final String mobile) {
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(DryCleanersActivity.this).builder()
                        .setMsg("拨打服务热线:  " + mobile)
                        .setPositiveButton("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CallPhoneUtils.callPhone(mobile, DryCleanersActivity.this);
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

   /* private void initBanner(String[] topPhotos) {
        if (topPhotos.length > 0) {
            ArrayList<BannerItem> list = new ArrayList<>();
            for (int i = 0; i < topPhotos.length; i++) {
                BannerItem item = new BannerItem();
                item.imgUrl = topPhotos[i];
                LogUtil.log(item.imgUrl);
                list.add(item);
            }
            sib
                    .setSource(list)
                    .startScroll();
        } *//*else {
            sib
                    .setSource(DataProvider.getList())
                    .startScroll();
        }*//*
        sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                *//*showToast("position--->" + position);*//*
            }
        });
    }*/

  /*  @Override
    public void onResume() {
        super.onResume();
        sib.computeScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        sib.pauseScroll();
    }*/

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("洗衣店");
    }

    @OnClick({R.id.iv_hot_phone, R.id.tv_now_order,R.id.iv_show_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hot_phone:
                break;
            case R.id.iv_show_dialog:
                SharedPrefHelper.getInstance().setServiceHint(Constants.DRYCLEANER,false);
                showHint(bean);
                break;
            case R.id.tv_now_order:
                Intent intent = new Intent(this, DryCleanReserveInfoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setData(BannerResponse.ServiceInfoBean bean) {
        this.bean=bean;
        String topphoto = bean.getTopphoto();//轮播图
       // String[] topPhotos = topphoto.split(",");
        String name = bean.getTradename();//店名
        String smallimg = bean.getSmallimg();//小图
        String place = bean.getPlace();//地址
        String businesshour = bean.getBusinesshour();//shijian
        String mobile = bean.getMobile();//电话
        Log.e("打印备案:", bean + "");
        //initBanner(topPhotos);

        if (bean != null && !TextUtils.isEmpty(topphoto)) {
            //this.bean=bean;
            String[] strings = bean.getTopphoto().split(",");
            sliderLayout.removeAllSliders();
            for (int i = 0; i < strings.length; i++) {
                TextSliderView textSliderView = new TextSliderView(DryCleanersActivity.this);
                textSliderView.description("").image(strings[i]);
                sliderLayout.addSlider(textSliderView);
            }
        }

        shopName.setText(name);
        GlideLoading.getInstance().loadImgUrlNyImgLoader(DryCleanersActivity.this,
                smallimg, ivCutHearLogo);
        tvAddress.setText(place);
        tvWorkTime.setText(businesshour);
        tvPhone.setText(mobile);
        call(mobile);

        if (bean.getRemind() == null) {
            hint.setText("空");
        } else {
            hint.setText(Html.fromHtml(bean.getRemind()));
        }
        showHint(bean);


    }

    private void showHint(BannerResponse.ServiceInfoBean bean) {
        // TODO 弹温馨出提示窗
        final ShowHintDialog pop = new ShowHintDialog(this, bean.getRemind(), Constants.DRYCLEANER);
        if (!SharedPrefHelper.getInstance().getServiceHint(Constants.DRYCLEANER)) {
            pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                @Override
                public void onSetting(TextView content) {
                    pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(DryCleanersActivity.this, 280));
                }
            });

//            pop.showAtLocation(findViewById(R.id.activity_dry_cleaners), Gravity.CENTER, 0, 0);
            pop.show();
        }
    }

    @Override
    public BannerModel createModel() {
        return new BannerModelImpl();
    }


    @Override
    public BannerPresenter createPresenter() {
        return new BannerPresenter();
    }

}
