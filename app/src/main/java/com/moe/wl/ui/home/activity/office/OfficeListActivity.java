package com.moe.wl.ui.home.activity.office;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.adapter.office.OfficeLitsAdapter;
import com.moe.wl.ui.home.bean.office.OfficeListResponse;
import com.moe.wl.ui.home.model.office.OfficeListModel;
import com.moe.wl.ui.home.modelimpl.office.OfficeListModelImpl;
import com.moe.wl.ui.home.presenter.office.OfficeListPresenter;
import com.moe.wl.ui.home.view.office.OfficeListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 办公室列表
 */
public class OfficeListActivity extends BaseActivity<OfficeListModel, OfficeListView, OfficeListPresenter> implements  OfficeListView {


    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.lv_content)
    ListView lvContent;
    private OfficeLitsAdapter adapter;
    private List<OfficeListResponse.ListBean> mList;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_officelist);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();

        initData();
        getPresenter().officelist();

    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("会议室预订");
    }

    @Override
    public OfficeListPresenter createPresenter() {
        return new OfficeListPresenter();
    }

    @Override
    public OfficeListModel createModel() {
        return new OfficeListModelImpl();
    }


    private void initData() {

        mList = new ArrayList<>();

        adapter = new OfficeLitsAdapter(this);
        adapter.setItemList(mList);
        lvContent.setAdapter(adapter);

    }

    @Override
    public void setData(List<OfficeListResponse.ListBean> list, String img) {
        GlideLoading.getInstance().loadImgUrlHeader(this, img, ivIcon, R.mipmap.ic_default_rectangle);
        if (list != null && list.size() != 0) {
            mList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}
