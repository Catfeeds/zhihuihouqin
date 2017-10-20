package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.adapter.NoticeAdapter;
import com.moe.wl.ui.main.bean.McNoticeListResponse;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.modelimpl.McNoticeModelImpl;
import com.moe.wl.ui.main.presenter.McNoticePresenter;
import com.moe.wl.ui.main.view.McNoticeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的收藏列表
 */

public class McNoticeFragment extends BaseFragment<McNocticeModel,McNoticeView,McNoticePresenter> implements McNoticeView {

    @BindView(R.id.rv_collect)
    XRecyclerView rvCollect;
    Unbinder unbinder;

    private String type;  //1: 公告，2：办公，3：理发作品，4：图书，5：医生，6：活动，7：发型师 //8:健康资讯 9专家 10营养套餐

    public static McNoticeFragment getInstance(String type){
        McNoticeFragment fragment = new McNoticeFragment();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
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
            type = bundle.getString("type");
        }
        if (!TextUtils.isEmpty(type)){
            getPresenter().findUserFavorList(type);//请求收藏
        }

        rvCollect.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void getCollect(List<McNoticeListResponse.PageBean.ListBean> list) {
        if(list!=null){
            NoticeAdapter adapter = new NoticeAdapter(getActivity(),type);
            rvCollect.setAdapter(adapter);
            adapter.setData(list);
        }
    }
}
