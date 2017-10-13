package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberAdapter;
import com.moe.wl.ui.main.bean.BarberListBean;
import com.moe.wl.ui.main.model.BarberListModel;
import com.moe.wl.ui.main.modelimpl.BarberListModelImpl;
import com.moe.wl.ui.main.presenter.BarberListPresenter;
import com.moe.wl.ui.main.view.BarberListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 办公室列表
 */
public class OfficeListActivity extends BaseActivity<BarberListModel, BarberListView, BarberListPresenter> implements BarberListView {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.rv_barber)
    RecyclerView rvBarber;
    private BarberAdapter barberAdapter;
    private String address;
    private String shopName;


    @Override
    public BarberListPresenter createPresenter() {
        return new BarberListPresenter();
    }

    @Override
    public BarberListModel createModel() {
        return new BarberListModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_officelist);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getData();
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        shopName = intent.getStringExtra("shopName");
        System.out.println("理发师"+address+"==="+shopName);
        initTitle();
        initRecycler();
    }

    @Override
    public void getBarberListSucc(BarberListBean listBean) {
        if (listBean != null) {
            List<BarberListBean.BarberlistBean> barberlist = listBean.getBarberlist();
            barberAdapter.setData(barberlist,address,shopName);
        }else{
            System.out.println("理发师"+listBean+"为空");
        }
    }

    private void initRecycler() {
        rvBarber.setLayoutManager(new LinearLayoutManager(this));
        barberAdapter = new BarberAdapter(this);
        rvBarber.setAdapter(barberAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("会议室预订");
    }
}
