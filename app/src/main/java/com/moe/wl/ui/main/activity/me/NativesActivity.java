package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.adapter.PositionAdatper;
import com.moe.wl.ui.main.adapter.NativeAdapter;
import com.moe.wl.ui.main.bean.NationslistBean;
import com.moe.wl.ui.main.model.NationsModel;
import com.moe.wl.ui.main.modelimpl.NationsModelImpl;
import com.moe.wl.ui.main.presenter.NationsPresenter;
import com.moe.wl.ui.main.view.NationsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NativesActivity extends BaseActivity<NationsModel,NationsView,NationsPresenter> implements NationsView {

    @BindView(R.id.mTitleBar)
    TitleBar title;
    @BindView(R.id.rv_item_container)
    RecyclerView rvItemContainer;
    private NativeAdapter adapter;

    @Override
    public NationsPresenter createPresenter() {
        return new NationsPresenter();
    }

    @Override
    public NationsModel createModel() {
        return new NationsModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_position);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initList();
        getPresenter().getNationList();//获取民族列表
    }

    private void initList() {
        rvItemContainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NativeAdapter(this);
        rvItemContainer.setAdapter(adapter);
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("民族");
    }

    @Override
    public void getNationResult(NationslistBean bean) {
        if(bean!=null){
            final List<NationslistBean.NationlistBean> nationlist = bean.getNationlist();
            adapter.setData(nationlist);
            adapter.setLister(new NativeAdapter.OnClickLister() {
                @Override
                public void itemClickLister(int position) {
                    NationslistBean.NationlistBean nationlistBean = nationlist.get(position);
                    if(nationlistBean!=null){
                        String name = nationlistBean.getName();
                        int id = nationlistBean.getId();
                        Intent intent=new Intent();
                        intent.putExtra("nation",name);
                        intent.putExtra("id",id);
                        setResult(RESULT_OK,intent);
                        finish();
                    }

                }
            });
        }

    }
}
