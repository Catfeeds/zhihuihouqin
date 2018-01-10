package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.UserWalletBean;
import com.moe.wl.ui.main.model.MyPurseModel;
import com.moe.wl.ui.main.modelimpl.MyPurseModelImpl;
import com.moe.wl.ui.main.presenter.MyPursePresenter;
import com.moe.wl.ui.main.view.MyPurseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包页面
 */

public class MyPurseActivity extends BaseActivity<MyPurseModel, MyPurseView, MyPursePresenter> implements MyPurseView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.iv_wen)
    ImageView ivWen;
    @BindView(R.id.ll_haircut_ticket)
    LinearLayout llHaircutTicket;
    @BindView(R.id.tv_ticket_count)
    TextView tvTicketCount;
    @BindView(R.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R.id.ll_public_amount)
    LinearLayout llPublicAcount;
    @BindView(R.id.ll_passward_management)
    LinearLayout llPasswardManagement;
    @BindView(R.id.ll_yajin)
    LinearLayout llYajin;
    @BindView(R.id.view_amount)
    View view_amount;
    @BindView(R.id.view_yajin)
    View view_yajin;

    private double publicRemain;

    @Override
    public MyPursePresenter createPresenter() {
        return new MyPursePresenter();
    }

    @Override
    public MyPurseModel createModel() {
        return new MyPurseModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_my_purse);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("我的钱包");
        title.setTitleRight("明细");
        getPresenter().getInfo();
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPurseActivity.this, PayDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_wen, R.id.ll_recharge, R.id.ll_yajin, R.id.ll_passward_management, R.id.ll_public_amount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wen://问号
                Intent intent2 = new Intent(this, BalanceExplainActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_recharge://充值
                Intent intent = new Intent(this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yajin://我的押金
                Intent intent4 = new Intent(this, MyDeposit.class);
                startActivity(intent4);
                break;
            case R.id.ll_public_amount://对公账户
                Intent intent3 = new Intent(this, PublicAcountActivity.class);
                intent3.putExtra("publicRemain", publicRemain);
                startActivity(intent3);
                break;
            case R.id.ll_passward_management://密码管理
                Intent intent1 = new Intent(this, PwdManageMentActivity.class);
                startActivity(intent1);
                break;
        }
    }

    /**
     * @param bean
     */
    @Override
    public void getInfoSucc(UserWalletBean bean) {
        if (bean != null) {
            publicRemain = bean.getPublicRemain();
            double walletRemain = bean.getWalletRemain();
            if (walletRemain>10000){
                tvBalance.setText(walletRemain/10000 + "万");//钱包余额
            }else {
                tvBalance.setText(walletRemain + "");//钱包余额
            }
            tvTicketCount.setText(bean.getVoucherNum() + "张");//理发券数量
            int hasBuyAuth = bean.getHasBuyAuth();//是否有团购权限
            int payPasswordState = bean.getPayPasswordState();//是否有支付密码
            /*llPublicAcount.setVisibility(View.VISIBLE);
            llYajin.setVisibility(View.VISIBLE);*/
            if (hasBuyAuth == 1) {//有团购权限
                llPublicAcount.setVisibility(View.VISIBLE);
                view_amount.setVisibility(View.VISIBLE);
                llYajin.setVisibility(View.VISIBLE);
                view_yajin.setVisibility(View.VISIBLE);
            } else {
                llPublicAcount.setVisibility(View.GONE);
                view_amount.setVisibility(View.GONE);
                llYajin.setVisibility(View.GONE);
                view_yajin.setVisibility(View.GONE);
            }
        }
    }
}
