package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.adapter.PositionAdatper;
import com.moe.wl.ui.main.adapter.DepartAdapter;
import com.moe.wl.ui.main.bean.DepartsListBean;
import com.moe.wl.ui.main.model.DepartMentModel;
import com.moe.wl.ui.main.modelimpl.DepartmentModelImpl;
import com.moe.wl.ui.main.presenter.DepartMentPresenter;
import com.moe.wl.ui.main.view.DepartMentView;

import java.util.List;

import butterknife.BindView;

public class DepartmentActivity extends BaseActivity<DepartMentModel, DepartMentView, DepartMentPresenter> implements DepartMentView {

    @BindView(R.id.mTitleBar)
    TitleBar title;
    @BindView(R.id.rv_item_container)
    RecyclerView rvItemContainer;
    private DepartAdapter adapter;

    @Override
    public DepartMentPresenter createPresenter() {
        return new DepartMentPresenter();
    }

    @Override
    public DepartMentModel createModel() {
        return new DepartmentModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_position);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("部门");
        initList();
        getPresenter().getDepartList();//获得部门列表
    }

    private void initList() {
        rvItemContainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DepartAdapter(this);
        rvItemContainer.setAdapter(adapter);
    }

    @Override
    public void getDepartResult(DepartsListBean bean) {
        if(bean!=null){
            final List<DepartsListBean.DepartListBean> departList = bean.getDepartList();
            adapter.setData(departList);
            adapter.setLister(new DepartAdapter.OnClickLister() {
                @Override
                public void itemClickLister(int position) {
                    DepartsListBean.DepartListBean departListBean = departList.get(position);
                    int id = departListBean.getId();
                    String name = departListBean.getName();
                    Intent intent=new Intent();
                    intent.putExtra("depart",name);
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }

    }
}
