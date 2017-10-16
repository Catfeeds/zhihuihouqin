package com.moe.wl.ui.main.fragment;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.adapter.NoticeAdapter;
import com.moe.wl.ui.main.bean.MyCollectBean;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.modelimpl.McNoticeModelImpl;
import com.moe.wl.ui.main.presenter.McNoticePresenter;
import com.moe.wl.ui.main.view.McNoticeView;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/11 0011.
 */

public class McNoticeFragment extends BaseFragment<McNocticeModel,McNoticeView,McNoticePresenter> implements McNoticeView {
    @BindView(R.id.rv_collect)
    XRecyclerView rvCollect;
    Unbinder unbinder;
    private int type;

    public static McNoticeFragment getInstance(int type){
        McNoticeFragment fragment = new McNoticeFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public McNocticeModel createModel() {
        return new McNoticeModelImpl();
    }

    @Override
    public McNoticePresenter createPresenter() {
        return new McNoticePresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.rv_fragment);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        Bundle bundle = getArguments();
        if(bundle!=null){
            type = bundle.getInt("type", 1);
        }
        getPresenter().getCollect(type);//请求收藏

        rvCollect.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
    @Override
    public void getCollect(MyCollectBean bean) {
        List<MyCollectBean.PageBean.ListBean> list = bean.getPage().getList();
        switch (type){
            case 1:
                NoticeAdapter adapter = new NoticeAdapter(getActivity());
                rvCollect.setAdapter(adapter);
                adapter.setData(list);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                break;




        }

    }
}
