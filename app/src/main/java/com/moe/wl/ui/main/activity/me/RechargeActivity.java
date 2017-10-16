package com.moe.wl.ui.main.activity.me;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.RechargeAmountModel;
import com.moe.wl.ui.main.modelimpl.RechargeAmountModelImpl;
import com.moe.wl.ui.main.presenter.RechargeAmountPresenter;
import com.moe.wl.ui.main.view.RechargeAmountView;
import com.moe.wl.ui.mywidget.BottomRechargeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class RechargeActivity extends BaseActivity<RechargeAmountModel,RechargeAmountView,RechargeAmountPresenter> implements RechargeAmountView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_scanner_amount)
    EditText etScannerAmount;
    @BindView(R.id.tv_confirm_pay)
    TextView tvConfirmPay;
    private static  final int ORDERTYPE=19;

    @Override
    public RechargeAmountPresenter createPresenter() {
        return new RechargeAmountPresenter();
    }

    @Override
    public RechargeAmountModel createModel() {
        return new RechargeAmountModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("充值");
        title.setTitleRight("充值记录");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RechargeActivity.this,RechargeRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.tv_confirm_pay)
    public void onViewClicked() {
        final String amount = etScannerAmount.getText().toString().trim();
        if(TextUtils.isEmpty(amount)){
            ToastUtil.showToast(this,"请输入充值金额");
            return ;
        }
        BottomRechargeDialog dialog=new BottomRechargeDialog(this,R.style.dialog_style);
        dialog.setAmount(amount);
        dialog.show();
        dialog.setListener(new BottomRechargeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(String money,int paytype) {
                double amount1 = Double.parseDouble(amount);
                getPresenter().rechargeAmount(amount1,paytype,ORDERTYPE);
            }
        });
    }

    @Override
    public void rechargeResult(WalletOrderBean bean) {
        // TODO: 2017/10/13 0013 生成订单结果
        showToast("生成订单成功");
        LogUtils.i(bean.getPaytype()+"---------");
        LogUtils.i(bean.getOrdertype()+"-----======----");
        finish();
    }
}
