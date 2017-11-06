package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.HomeNsrlv1Adapter;
import com.moe.wl.ui.main.bean.InformationBean;
import com.moe.wl.ui.main.bean.ListEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：今日菜谱Fragment（早餐 午餐 晚餐）
 * 作者：Shixhe On 2017/9/5 0005
 */

public class InformationFragment extends BaseFragment2 {

    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    Unbinder unbinder;

    // private List<InformationBean.PageEntity.ListEntity> data;
    private List<ListEntity> data;
    private HomeNsrlv1Adapter adapter;

    private int page = 1;

    private boolean isRecommend = false;
    private int typeID = 0;
    private String keyword;

    public static InformationFragment getInstance(int id, boolean isRecommend, String keyword) {
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        LogUtils.d("typeID : " + id);
        bundle.putInt("typeID", id);
        bundle.putBoolean("isRecommend", isRecommend);
        bundle.putString("keyWord", keyword);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View setLayout() {
        View view = View.inflate(getActivity(), R.layout.fragment_information, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        typeID = getArguments().getInt("typeID", 0);
        keyword = getArguments().getString("keyWord", "");
        isRecommend = getArguments().getBoolean("isRecommend", false);

        data = new ArrayList<>();
        adapter = new HomeNsrlv1Adapter(getActivity(), data);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();
            }
        });
        getData();
    }

    private void getData() {
        Observable observable;
        if (isRecommend) {
            observable = RetrofitUtils.getInstance().getInformation(0, 1, keyword, page);
        } else {
            observable = RetrofitUtils.getInstance().getInformation(typeID, 0, keyword, page);
        }
        showProgressDialog();
        observable.subscribe(new Subscriber<InformationBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                recycleView.refreshComplete();
                recycleView.loadMoreComplete();
            }

            @Override
            public void onNext(InformationBean bean) {
                if (bean == null || bean.getPage() == null || bean.getPage().getList() == null)
                    return;
                if (bean.getErrCode() == 0) {
                    if (bean.getPage().getTotalPage() <= page) {
                        recycleView.setLoadingMoreEnabled(false);
                    } else {
                        recycleView.setLoadingMoreEnabled(true);
                    }
                    if (page == 1) {
                        data.clear();
                        recycleView.refreshComplete();
                    } else {
                        recycleView.loadMoreComplete();
                    }
                    data.addAll(bean.getPage().getList());
                    adapter.notifyDataSetChanged();
                } else if (bean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    showToast(bean.getMsg());
                }
            }
        });
    }

}
