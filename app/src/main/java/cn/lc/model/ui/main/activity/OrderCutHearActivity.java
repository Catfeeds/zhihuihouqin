package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.CutHearAdapter;
import cn.lc.model.ui.main.bean.ShopBean;
import cn.lc.model.ui.main.model.ShopModel;
import cn.lc.model.ui.main.modelimpl.ShopModelImpl;
import cn.lc.model.ui.main.presenter.ShopPresenter;
import cn.lc.model.ui.main.view.ShopView;

public class OrderCutHearActivity extends BaseActivity<ShopModel,ShopView,ShopPresenter>implements ShopView {

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
    @BindView(R.id.barber)
    TextView barber;
    @BindView(R.id.shop_grid)
    GridView shopGrid;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.activity_order_cut_hear)
    RelativeLayout activityOrderCutHear;
    private CutHearAdapter adapter;
    private ShopBean shopBean;

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
        this.shopBean=shopBean;
        System.out.println("shopBean = " + shopBean);
        if(shopBean!=null){
            shopName.setText(shopBean.getTradename());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this,shopBean.getPicture(),ivCutHearLogo);
            tvAddress.setText(shopBean.getPlace());
            tvWorkTime.setText(shopBean.getBusinesshour());
            tvPhone.setText(shopBean.getMobile());
            adapter.setData(shopBean.getBarberlist());
        }else{
            showToast(shopBean.getMsg());
        }
    }
    private void initGrid() {
        adapter = new CutHearAdapter(this);
        shopGrid.setAdapter(adapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("店铺");
    }

    @OnClick({R.id.barber, R.id.tv_now_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.barber:
                Intent intent = new Intent(this, BarberActivity.class);
                intent.putExtra("address",shopBean.getPlace());
                intent.putExtra("shopName",shopBean.getTradename());
                startActivity(intent);
                break;
            case R.id.tv_now_order:
                break;
        }
    }


}
