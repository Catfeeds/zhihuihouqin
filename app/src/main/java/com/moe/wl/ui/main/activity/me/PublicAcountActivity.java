package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.PublicAcountAdapter;
import com.moe.wl.ui.main.bean.PurchaseAccountListBean;
import com.moe.wl.ui.main.model.PublicAcountModel;
import com.moe.wl.ui.main.modelimpl.PublicAcountModelImpl;
import com.moe.wl.ui.main.presenter.PublicAcountPresenter;
import com.moe.wl.ui.main.view.PublicAcountView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublicAcountActivity extends BaseActivity<PublicAcountModel,PublicAcountView,PublicAcountPresenter>implements PublicAcountView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_wen)
    ImageView ivWen;
    @BindView(R.id.rv_public)
    RecyclerView rvPublic;
    @BindView(R.id.activity_public_acount)
    LinearLayout activityPublicAcount;
    private PublicAcountAdapter adapter;

    @Override
    public PublicAcountPresenter createPresenter() {
        return new PublicAcountPresenter();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_public_acount);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("对公账户");
        getPresenter().getPurchaseAccountList();
        rvPublic.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PublicAcountAdapter(this);
        rvPublic.setAdapter(adapter);
        ivWen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PublicAcountActivity.this, BalanceExplainActivity.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    public PublicAcountModel createModel() {
        return new PublicAcountModelImpl();
    }

    @Override
    public void findList(PurchaseAccountListBean bean) {
        if(bean!=null){
            List<PurchaseAccountListBean.AccountListBean> accountList = bean.getAccountList();
            adapter.setData(accountList);
        }
    }
}
