package cn.lc.model.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.widget.SimpleImageBanner;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.framework.widget.bean.BannerItem;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.bean.ServiceBean;
import cn.lc.model.ui.mywidget.AlertDialog;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

public class DryCleanersActivity extends Base2Activity {


    @BindView(R.id.dry_cleaners_title)
    TitleBar titleBar;
    @BindView(R.id.h_banner_viewPager)
    SimpleImageBanner sib;
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

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_dry_cleaners);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        getData();
    }

    private void getData() {
        Observable observable = RetrofitUtils.getInstance().getDryCleanerHome(16);
        showProgressDialog();
        observable.subscribe(new Subscriber<ServiceBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("干洗首页", e.getMessage());
            }

            @Override
            public void onNext(ServiceBean serviceBean) {
                Log.e("errcode",serviceBean.getErrCode()+"");
                if (serviceBean.getErrCode() == 0) {
                    ServiceBean.ServiceInfoBean serviceInfo = serviceBean.getServiceInfo();
                    String topphoto = serviceInfo.getTopphoto();//轮播图
                    String[] topPhotos = topphoto.split(",");
                    String name = serviceInfo.getTradename();//店名
                    String smallimg = serviceInfo.getSmallimg();//小图
                    String place = serviceInfo.getPlace();//地址
                    String businesshour = serviceInfo.getBusinesshour();//shijian
                    String mobile = serviceInfo.getMobile();//电话
                    Log.e("打印备案:",serviceInfo+"");
                    initBanner(topPhotos);
                    shopName.setText(name);
                    GlideLoading.getInstance().loadImgUrlNyImgLoader(DryCleanersActivity.this,
                            smallimg,ivCutHearLogo);
                    tvAddress.setText(place);
                    tvWorkTime.setText(businesshour);
                    tvPhone.setText(mobile);
                    call(mobile);
                } else {
                    Log.e("ServiceBean==", serviceBean.getMsg());
                }
            }
        });
    }

    private void call(final String mobile) {
        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(DryCleanersActivity.this).builder()
                        .setMsg("拨打服务热线:  "+mobile)
                        .setPositiveButton("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CallPhoneUtils.callPhone(mobile,DryCleanersActivity.this);
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

    private void initBanner(String[] topPhotos) {
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
        } /*else {
            sib
                    .setSource(DataProvider.getList())
                    .startScroll();
        }*/
        sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                showToast("position--->" + position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        sib.computeScroll();

    }

    @Override
    public void onPause() {
        super.onPause();
        sib.pauseScroll();

    }
    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("店铺");
    }

    @OnClick({R.id.iv_hot_phone, R.id.tv_now_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hot_phone:
                break;
            case R.id.tv_now_order:
                Intent intent = new Intent(this, DryCleanReserveInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
