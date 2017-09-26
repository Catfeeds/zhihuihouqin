package com.moe.wl.ui.main.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.RegistrationAdapter;
import com.moe.wl.ui.main.bean.DoctorListBean;
import com.moe.wl.ui.main.model.DoctorListModel;
import com.moe.wl.ui.main.modelimpl.DoctorListModelImpl;
import com.moe.wl.ui.main.presenter.DoctorListPresenter;
import com.moe.wl.ui.main.view.DoctorListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

public class RegistrationActivity extends BaseActivity<DoctorListModel,DoctorListView,DoctorListPresenter> implements DoctorListView {

    @BindView(R.id.registration_title)
    TitleBar titleBar;
    @BindView(R.id.rv_registration)
    RecyclerView rvRegistration;
    @BindView(R.id.activity_registration)
    LinearLayout activityRegistration;
    private RegistrationAdapter adapter;

    @Override
    public DoctorListPresenter createPresenter() {
        return new DoctorListPresenter();
    }

    @Override
    public DoctorListModel createModel() {
        return new DoctorListModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getData();
        initTitle();
        initRecycler();
    }
    @Override
    public void getDoctorListSucc(DoctorListBean listBean) {
        Log.e("DoctorListBean","==="+listBean);
        Log.e("getDoctorlist()","==="+listBean.getDoctorlist());
        if(listBean!=null){
            List<DoctorListBean.DoctorlistBean> doctorlist = listBean.getDoctorlist();
            adapter.setData(doctorlist);
        }

    }
    private void initRecycler() {
        rvRegistration.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RegistrationAdapter(this);
        rvRegistration.setAdapter(adapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("当日挂号");
    }


}
