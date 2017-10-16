package com.moe.wl.ui.home.activity.office;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout ll_right;
    private ImageView iv_icon;
    private RecyclerView lv_content;

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
        ll_right = (LinearLayout) findViewById(R.id.ll_right);
        ll_right.setOnClickListener(this);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_icon.setOnClickListener(this);
        lv_content = (RecyclerView) findViewById(R.id.lv_content);

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
            case R.id.ll_right:  //标题右边搜索

                break;
        }
    }

    private void initData() {

        mList=new ArrayList<>();

        lv_content.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OfficeLitsAdapter(this);
        lv_content.setAdapter(adapter);
        adapter.setData(mList);

    }


    @Override
    public void setData() {
        for (int i = 0; i < 10; i++) {
            mList.add("办公室名称"+i);
        }
        adapter.notifyDataSetChanged();
    }
}
