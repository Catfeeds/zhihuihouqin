package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.OfficeNumAdapter;
import com.moe.wl.ui.main.bean.OfficeslistBean;
import com.moe.wl.ui.main.model.OfficeNumModel;
import com.moe.wl.ui.main.modelimpl.OfficeModelImpl;
import com.moe.wl.ui.main.presenter.OfficeNumPresenter;
import com.moe.wl.ui.main.view.OfficeNumView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class OfficeidActivity extends BaseActivity<OfficeNumModel, OfficeNumView, OfficeNumPresenter> implements OfficeNumView {
    @BindView(R.id.mTitleBar)
    TitleBar title;
    @BindView(R.id.rv_item_container)
    RecyclerView rvItemContainer;
    private OfficeNumAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_position);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("处室号");
        getPresenter().getOfficeNum();
        rvItemContainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OfficeNumAdapter(this);
        rvItemContainer.setAdapter(adapter);
    }

    @Override
    public void getOfficeNumResult(OfficeslistBean bean) {
        if(bean!=null){
            final List<OfficeslistBean.DepartListBean> departList = bean.getDepartList();
            adapter.setData(departList);
            adapter.setLister(new OfficeNumAdapter.OnClickLister() {
                @Override
                public void itemClickLister(int position) {
                    OfficeslistBean.DepartListBean departListBean = departList.get(position);
                    int bgypright = departListBean.getBgypright();
                    String name = departListBean.getName();
                    int departid = departListBean.getDepartid();
                    int id = departListBean.getId();
                    Intent intent = new Intent();
                    intent.putExtra("bgypright",bgypright);
                    intent.putExtra("name",name);
                    intent.putExtra("departid",departid);
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    @Override
    public OfficeNumModel createModel() {
        return new OfficeModelImpl();
    }

    @Override
    public OfficeNumPresenter createPresenter() {
        return new OfficeNumPresenter();
    }
}
