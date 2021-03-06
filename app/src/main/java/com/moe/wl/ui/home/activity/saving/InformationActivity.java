package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.saving.InformationAdapter;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;
import com.moe.wl.ui.home.model.saving.InformationModel;
import com.moe.wl.ui.home.modelimpl.saving.InformationModelImpl;
import com.moe.wl.ui.home.presenter.saving.InformationPresenter;
import com.moe.wl.ui.home.view.saving.InformationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资讯列表页面
 */
public class InformationActivity extends BaseActivity<InformationModel, InformationView, InformationPresenter> implements InformationView ,View.OnClickListener{

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.lv_content)
    ListView lvContent;

    private InformationAdapter adapter;
    private List<String> mList;


    @Override
    public InformationPresenter createPresenter() {
        return new InformationPresenter();
    }

    @Override
    public InformationModel createModel() {
        return new InformationModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_information);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mList=new ArrayList<>();

        adapter=new InformationAdapter(this);
        //adapter.setItemList(mList);
        lvContent.setAdapter(adapter);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(InformationActivity.this,InfoDetailsActivity.class));
            }
        });
        getPresenter().information();
    }

    @OnClick({R.id.ll_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }


    @Override
    public void setData() {
        for (int i = 0; i < 10 ; i++) {
            mList.add("");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getHomeList(SaveHomeListBean homeListBean) {
        //设置列表
        List<SaveHomeListBean.NewsBean> news = homeListBean.getNews();
        adapter.setItemList(news);
        adapter.notifyDataSetChanged();
    }
}
