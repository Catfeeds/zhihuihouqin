package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.OfficeSupplies.SpPaySuccessActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.PayModel;
import com.moe.wl.ui.main.modelimpl.PayModelImpl;
import com.moe.wl.ui.main.presenter.PayPresenter;
import com.moe.wl.ui.main.view.PayView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.cn.thirdplatform.pay.alipay.Alipay;
import lc.cn.thirdplatform.pay.wxpay.WecatPay;

public class PayFiveJiaoActivity extends BaseActivity<PayModel, PayView, PayPresenter> implements PayView {

    private static final int ORDERWATER = 1;
    @BindView(R.id.pay_title)
    TitleBar payTitle;
    @BindView(R.id.iv_balance_pay)
    ImageView ivBalancePay;
    @BindView(R.id.tv_balance_pay)
    TextView tvBalancePay;
    @BindView(R.id.iv_balance_pay_check)
    ImageView ivBalancePayCheck;
    @BindView(R.id.tv_available_balance)
    TextView tvAvailableBalance;
    @BindView(R.id.iv_weixin_pay)
    ImageView ivWeixinPay;
    @BindView(R.id.tv_weixin_pay)
    TextView tvWeixinPay;
    @BindView(R.id.iv_weixin_pay_check)
    ImageView ivWeixinPayCheck;
    @BindView(R.id.iv_alpay)
    ImageView ivAlpay;
    @BindView(R.id.tv_alpay)
    TextView tvAlpay;
    @BindView(R.id.iv_alpay_check)
    ImageView ivAlpayCheck;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.iv_daijinquan)
    ImageView ivDaijinquan;
    @BindView(R.id.tv_daijinquan)
    TextView tvDaijinquan;
    @BindView(R.id.iv_daijinjuan_check)
    ImageView ivDaijinjuanCheck;
    @BindView(R.id.rl_daijinquan_pay)
    RelativeLayout rlDaijinquanPay;
    @BindView(R.id.activity_pay_five_jiao)
    LinearLayout activityPayFiveJiao;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    private int paytype;
    private String ordercode;
    private String ordertype;
    private int from;

    @Override
    public PayPresenter createPresenter() {
        return new PayPresenter();
    }

    @Override
    public PayModel createModel() {
        return new PayModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pay_five_jiao);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        ordercode = intent.getStringExtra("ordercode");
        ordertype = intent.getStringExtra("ordertype");
        LogUtils.i(ordercode);
        LogUtils.i(ordertype);
        from = intent.getIntExtra("orderWater", Constants.ORDERWATER);
        int pay = intent.getIntExtra("pay", 0);
        tvNeedPay.setText("￥" + pay);
    }

    private void initTitle() {
        payTitle.setBack(true);
        payTitle.setTitle("支付");
    }

    @OnClick({R.id.iv_daijinjuan_check, R.id.iv_balance_pay_check, R.id.iv_weixin_pay_check, R.id.iv_alpay_check, R.id.bt_confirm})
    public void onViewClicked(View view) {
        unCheckPay();
        switch (view.getId()) {
            case R.id.iv_daijinjuan_check://代金券支付
                ivDaijinjuanCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.iv_balance_pay_check://余额支付
                paytype = 3;
                ivBalancePayCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.iv_weixin_pay_check://微信支付
                paytype = 2;
                ivWeixinPayCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.iv_alpay_check://支付宝支付
                paytype = 1;
                ivAlpayCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.bt_confirm://确认支付，去顶支付成功进入支付成功界面

                switch (paytype) {
                    case 1:
                        getPresenter().aliPay(ordercode,ordertype,1);
                        break;
                    case 2:
                        getPresenter().weiXinPay(ordercode,ordertype,2);
                        break;
                    case 3:
                        break;
                }
             /*   switch (from){
                    case ORDERWATER:
                        Intent intent=new Intent(this, SpPaySuccessActivity.class);

                        break;
                }
                Intent intent = new Intent(this, SubmitSuccessActivity.class);
                intent.putExtra("isPay",true);
                startActivity(intent);*/
                break;
        }
    }
    private void unCheckPay() {
        ivDaijinjuanCheck.setImageResource(R.drawable.unselected);
        ivBalancePayCheck.setImageResource(R.drawable.unselected);
        ivWeixinPayCheck.setImageResource(R.drawable.unselected);
        ivAlpayCheck.setImageResource(R.drawable.unselected);
    }

    @Override
    public void aliPay(AlipayBean bean) {
        if(bean!=null){
            new Alipay(this).doPay(bean.getPayLink());
        }
    }

    @Override
    public void weiXinPay(WeixinBean bean) {
        if(bean!=null){
            Gson gson = new Gson();
            String json = gson.toJson(bean);
            new WecatPay(this).doPay(json);
        }
    }

    @Override
    public void personalWallet(ActivityPostBean bean) {
        if(bean!=null){
            showToast("支付成功");
        }
    }
}
