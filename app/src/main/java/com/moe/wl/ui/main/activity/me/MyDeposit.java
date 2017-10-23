package com.moe.wl.ui.main.activity.me;

import android.view.View;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.GenerateOrderWaterBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.bean.UserDepositBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.MyDepositModel;
import com.moe.wl.ui.main.modelimpl.MyDepositModelImpl;
import com.moe.wl.ui.main.presenter.MyDepositPresenter;
import com.moe.wl.ui.main.view.MyDepositView;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDeposit extends BaseActivity<MyDepositModel, MyDepositView, MyDepositPresenter> implements MyDepositView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_return_depoosit)
    TextView tvReturnDepoosit;

    @Override
    public MyDepositPresenter createPresenter() {
        return new MyDepositPresenter();
    }

    @Override
    public MyDepositModel createModel() {
        return new MyDepositModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_my_deposit);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        getPresenter().getDepositInfo();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("我的押金");
    }

    @OnClick(R.id.tv_return_depoosit)
    public void onViewClicked() {

        final AlertDialog dialog = new AlertDialog(this).builder();
        dialog.setBigMsg("退还押金,您下次订购桶装水时还需要缴纳押金")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2017/10/13 0013 还没有提供借口
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    @Override
    public void getUserDepositResult(UserDepositBean bean) {
        if (bean != null) {
            UserDepositBean.DepositBean deposit = bean.getDeposit();
            int deposit1 = deposit.getDeposit();
            tvMoney.setText(deposit1);
        }
    }

    @Override
    public void getOrderResult(WalletOrderBean bean) {

    }

    @Override
    public void getTimeSucc(OrderWaterTimeBean bean) {

    }

    @Override
    public void generateOrderSucc(GenerateOrderWaterBean bean) {

    }
}
