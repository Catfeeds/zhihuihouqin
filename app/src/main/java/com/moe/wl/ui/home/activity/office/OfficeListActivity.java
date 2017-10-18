package com.moe.wl.ui.home.activity.office;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.office.OfficeLitsAdapter;
import com.moe.wl.ui.home.model.office.OfficeListModel;
import com.moe.wl.ui.home.modelimpl.office.OfficeListModelImpl;
import com.moe.wl.ui.home.presenter.office.OfficeListPresenter;
import com.moe.wl.ui.home.view.office.OfficeListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 办公室列表
 */
public class OfficeListActivity extends BaseActivity<OfficeListModel, OfficeListView, OfficeListPresenter> implements View.OnClickListener,OfficeListView {

    private LinearLayout ll_back;
    private TextView tv_title;
    private ImageView iv_icon;
    private ListView lv_content;

    private OfficeLitsAdapter adapter;
    private List<String> mList;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_officelist);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        lv_content = (ListView) findViewById(R.id.lv_content);

        initData();
        getPresenter().officelist();

    }

    @Override
    public OfficeListPresenter createPresenter() {
        return new OfficeListPresenter();
    }

    @Override
    public OfficeListModel createModel() {
        return new OfficeListModelImpl();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void initData() {

        mList=new ArrayList<>();

        adapter = new OfficeLitsAdapter(this);
        adapter.setItemList(mList);
        lv_content.setAdapter(adapter);

    }


    @Override
    public void setData() {
        for (int i = 0; i < 10; i++) {
            mList.add("办公室名称"+i);
        }
        adapter.notifyDataSetChanged();
    }
}
