package com.moe.wl.ui.main.fragment;

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
import com.moe.wl.ui.main.adapter.HomeNsrlv1Adapter;
import com.moe.wl.ui.main.adapter.InformationAdapter;

/**
 * Created by 我的电脑 on 2017/9/11 0011.
 */

public class McNoticeFragment extends Fragment {
    @BindView(R.id.rv_collect)
    XRecyclerView rvCollect;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, initView());
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvCollect.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeNsrlv1Adapter adapter=new HomeNsrlv1Adapter();
        rvCollect.setAdapter(adapter);
    }

    public View initView() {
        return View.inflate(getActivity(), R.layout.rv_fragment, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
