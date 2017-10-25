package com.moe.wl.ui.main.activity.me;

import android.view.View;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.ActivityPostBean;
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
    private int deposit1;

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
        getPresenter().getDepositInfo();//获取押金信息
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("我的押金");
    }

    @OnClick(R.id.tv_return_depoosit)
    public void onViewClicked() {
        if(deposit1==0){
            showToast("你还没有交押金");
            return;
        }
        final AlertDialog dialog = new AlertDialog(this).builder();
        dialog.setBigMsg("退还押金,您下次订购桶装水时还需要缴纳押金")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPresenter().backDeposit();//返回押金
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
            deposit1 = deposit.getDeposit();
            tvMoney.setText(deposit1 +"");
        }
    }

    @Override
    public void getOrderResult(WalletOrderBean bean) {

    }

    @Override
    public void backDepositResult(ActivityPostBean bean) {
        if(bean!=null){
            showToast("退换押金成功，会在2~3个工作日将押金退还到账户");
            finish();
        }
    }

    @Override
    public void getTimeSucc(OrderWaterTimeBean bean) {

    }

    @Override
    public void generateOrderSucc(GenerateOrderWaterBean bean) {

    }
}
