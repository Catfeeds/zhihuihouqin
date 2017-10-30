package com.moe.wl.ui.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.SimpleImageBanner;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.framework.widget.bean.BannerItem;
import com.moe.wl.ui.main.adapter.CutHearAdapter;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.BarberlistBean;
import com.moe.wl.ui.main.bean.ShopBean;
import com.moe.wl.ui.main.model.ShopModel;
import com.moe.wl.ui.main.modelimpl.ShopModelImpl;
import com.moe.wl.ui.main.presenter.ShopPresenter;
import com.moe.wl.ui.main.view.ShopView;
import com.moe.wl.ui.mywidget.ShowHintPop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.LogUtil;
import mvp.cn.util.StringUtil;

public class OrderCutHearActivity extends BaseActivity<ShopModel, ShopView, ShopPresenter> implements ShopView {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
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
    @BindView(R.id.rv_hot_phone)
    ImageView rvHotPhone;
    @BindView(R.id.tv_barber_num)
    TextView tvBarberNum;
    @BindView(R.id.shop_grid)
    NoSlidingGridView shopGrid;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.ll_call)
    LinearLayout llCall;
    @BindView(R.id.h_banner_viewPager)
    SimpleImageBanner sib;
    private CutHearAdapter adapter;
    private ShopBean shopBean;
    private List<BarberlistBean> barberlist;

    @Override
    public ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    public ShopModel createModel() {
        return new ShopModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_order_cut_hear);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getData();
        initTitle();
        initGrid();
    }

    @Override
    public void getShopInfo(ShopBean shopBean) {
        getPresenter().getphotos(6);//获取顶部轮播图
        this.shopBean = shopBean;
        System.out.println("shopBean = " + shopBean);
        if (shopBean != null) {
            shopName.setText(shopBean.getTradename());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, shopBean.getPicture(), ivCutHearLogo);
            tvAddress.setText(shopBean.getPlace());
            tvWorkTime.setText(shopBean.getBusinesshour());
            tvPhone.setText(shopBean.getMobile());
            barberlist = shopBean.getBarberlist();
            tvBarberNum.setText("全部" + barberlist.size() + "位理发师");
            adapter.setData(barberlist);
        } else {
            showToast(shopBean.getMsg());
        }
    }

    @Override
    public void getServiceInfo(BannerResponse bean) {
        if (bean != null) {
            //获取轮播图
            BannerResponse.ServiceInfoBean infoBean = bean.getServiceInfo();
            //ServiceBean.ServiceInfoBean infoBean= bean.getServiceInfo();
            if (infoBean != null) {
                String detailphoto = infoBean.getDetailphoto();
                if (detailphoto != null) {
                    String[] urls = detailphoto.split(",");
                    List<?> imgList = Arrays.asList(urls);
                    if (imgList.size() > 0) {
                        ArrayList<BannerItem> list = new ArrayList<>();
                        for (int i = 0; i < imgList.size(); i++) {
                            BannerItem item = new BannerItem();
                            item.imgUrl = (String) imgList.get(i);
                            LogUtil.log(item.imgUrl);
                            list.add(item);
                        }
                        sib
                                .setSource(list)
                                .startScroll();
                    }
                    sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
                        @Override
                        public void onItemClick(int position) {
                        /*ToastUtil.showToast(getActivity(), "position--->" + position);*/

                        }
                    });
                }
            }

            // TODO 弹温馨出提示窗
            if (!SharedPrefHelper.getInstance().getServiceHint(Constants.HAIRCUTS)) {
                ShowHintPop pop = new ShowHintPop(this, infoBean.getRemind(), Constants.HAIRCUTS);
                pop.showAtLocation(findViewById(R.id.activity_order_cut_hear), Gravity.CENTER, 0, 0);
            }
        }

    }

    private void initGrid() {
        adapter = new CutHearAdapter(this);
        shopGrid.setAdapter(adapter);
        //进入到理发师详情
        shopGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BarberlistBean barberlistBean = barberlist.get(position);
                String place = shopBean.getPlace();
                String tradename = shopBean.getTradename();
                Intent intent = new Intent(OrderCutHearActivity.this, BarberDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("address", place);
                bundle.putString("shopName", tradename);
                bundle.putSerializable("barberlistBean", barberlistBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("美容美发");
    }

    @OnClick({R.id.tv_barber_num, R.id.tv_now_order, R.id.ll_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_barber_num:
                Intent intent = new Intent(this, BarberActivity.class);
                String place = tvAddress.getText().toString().trim();
                intent.putExtra("address", place);
                intent.putExtra("shopName", shopBean.getTradename());
                startActivity(intent);
                break;
            case R.id.tv_now_order:
                Intent intent1 = new Intent(this, NowReservaBarberActivity.class);
                // TODO: 2017/9/28 0028
                startActivity(intent1);
                break;
            case R.id.ll_call:
                final String phone = tvPhone.getText().toString().trim();
                if (StringUtil.isNullOrEmpty(phone)) {
                    return;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("拨打电话" + phone).setPositiveButton("是", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        CallPhoneUtils.callPhone(phone, OrderCutHearActivity.this);
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
    }


}
