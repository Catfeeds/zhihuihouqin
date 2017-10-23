package com.moe.wl.ui.main.activity.me;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.SubmitSuccessActivity;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.RechargeAmountModel;
import com.moe.wl.ui.main.modelimpl.RechargeAmountModelImpl;
import com.moe.wl.ui.main.presenter.RechargeAmountPresenter;
import com.moe.wl.ui.main.view.RechargeAmountView;
import com.moe.wl.ui.mywidget.BottomRechargeDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.cn.thirdplatform.pay.alipay.AliPaySuccess;
import lc.cn.thirdplatform.pay.alipay.Alipay;
import lc.cn.thirdplatform.pay.wxpay.WecatPay;
import mvp.cn.util.ToastUtil;

public class RechargeActivity extends BaseActivity<RechargeAmountModel,RechargeAmountView,RechargeAmountPresenter> implements RechargeAmountView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_scanner_amount)
    EditText etScannerAmount;
    @BindView(R.id.tv_confirm_pay)
    TextView tvConfirmPay;
    private static  final int ORDERTYPE=19;
    private BottomRechargeDialog dialog;

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
        EventBus.getDefault().register(this);
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
        dialog = new BottomRechargeDialog(this, R.style.dialog_style);
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
        LogUtils.i(bean.getPaytype()+"---------");
        LogUtils.i(bean.getOrdertype()+"-----======----");
        String ordercode = bean.getOrdercode();
        int orderid = bean.getOrderid();
        int ordertype = bean.getOrdertype();
        int paytype = bean.getPaytype();
        switch (paytype) {
            case 1:
                getPresenter().aliPay(orderid+"", ordercode, ordertype+"", 1);
                break;
            case 2:
                getPresenter().weiXinPay(orderid+"", ordercode, ordertype+"", 2);
                break;
        }
        dialog.dismiss();
    }
    //支付宝支付成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(AliPaySuccess event) {
        if (event!=null){
            LogUtils.d("--------------支付订单----------");
            Intent intent=new Intent(this, SubmitSuccessActivity.class);
            intent.putExtra("isPay",true);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void aliPay(AlipayBean bean) {
        if (bean != null) {
            new Alipay(this).doPay(bean.getPayLink());
        }
        finish();
    }

    @Override
    public void weiXinPay(WeixinBean bean) {
        if (bean != null) {
            Gson gson = new Gson();
            String json = gson.toJson(bean);
            new WecatPay(this).doPay(json);
        }
        finish();
    }
}
