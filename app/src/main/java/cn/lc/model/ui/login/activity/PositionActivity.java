package cn.lc.model.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.adapter.PositionAdatper;
import cn.lc.model.ui.login.bean.PositionListBean;
import cn.lc.model.ui.login.model.PositionListModel;
import cn.lc.model.ui.login.modelimpl.PositionListModelImpl;
import cn.lc.model.ui.login.presenter.PositionListPresenter;
import cn.lc.model.ui.login.view.PositionListView;

public class PositionActivity extends BaseActivity<PositionListModel,PositionListView,PositionListPresenter> implements PositionListView {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.rv_item_container)
    RecyclerView rvItemContainer;
    private PositionAdatper positionAdatper;

    @Override
    public PositionListPresenter createPresenter() {
        return new PositionListPresenter();
    }

    @Override
    public PositionListModel createModel() {
        return new PositionListModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_position);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getData();
        mTitleBar.setBack(true);
        mTitleBar.setTitle("职位");
        rvItemContainer.setLayoutManager(new LinearLayoutManager(this));
        positionAdatper = new PositionAdatper(this);
        rvItemContainer.setAdapter(positionAdatper);

    }

    @Override
    public void getListSucc(PositionListBean positionListBean) {
        if(positionListBean!=null){
            final List<PositionListBean.PositionlistBean> positionlist = positionListBean.getPositionlist();
            if(positionlist!=null&&positionlist.size()>0){
                positionAdatper.setDate(positionlist);

                positionAdatper.setLister(new PositionAdatper.OnClickLister() {
                    @Override
                    public void itemClickLister(int position) {
                        Log.e("index","返回选中position"+position);
                        int positionId=positionlist.get(position).getId();
                        String name = positionlist.get(position).getName();
                        Intent intent = new Intent();
                        intent.putExtra("position",name);
                        intent.putExtra("positionId",positionId);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });

            }
        }
    }

}
