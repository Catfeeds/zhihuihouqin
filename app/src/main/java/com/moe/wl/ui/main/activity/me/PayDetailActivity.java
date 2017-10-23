package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.PayDetailAdapter;
import com.moe.wl.ui.main.bean.FindWalletLogBean;
import com.moe.wl.ui.main.model.PayDetailModel;
import com.moe.wl.ui.main.modelimpl.PayDetailModelImpl;
import com.moe.wl.ui.main.presenter.PayDetailPresenter;
import com.moe.wl.ui.main.view.PayDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayDetailActivity extends BaseActivity<PayDetailModel,PayDetailView,PayDetailPresenter> implements PayDetailView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rv_pay_detail)
    XRecyclerView rvPayDetail;
    private PayDetailAdapter adapter;

    @Override
    public PayDetailPresenter createPresenter() {
        return new PayDetailPresenter();
    }

    @Override
    public PayDetailModel createModel() {
        return new PayDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pay_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        getPresenter().getPayDetailInfo();//请求获得明细信息
        rvPayDetail.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PayDetailAdapter(this);
        rvPayDetail.setAdapter(adapter);
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("明细");
    }

    @Override
    public void getPayDetailResult(FindWalletLogBean bean) {
        if(bean!=null){
            List<FindWalletLogBean.PageBean.ListBean> list = bean.getPage().getList();
            //adapter.setData(list);
        }
    }
}
