package com.moe.wl.ui.main.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.me.AcountSaftActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.UserWalletBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.PayModel;
import com.moe.wl.ui.main.modelimpl.PayModelImpl;
import com.moe.wl.ui.main.presenter.PayPresenter;
import com.moe.wl.ui.main.view.PayView;
import com.moe.wl.ui.mywidget.TsAlertDialog;
import com.moe.wl.ui.mywidget.WalletPayDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.cn.thirdplatform.pay.alipay.AliPaySuccess;
import lc.cn.thirdplatform.pay.alipay.Alipay;
import lc.cn.thirdplatform.pay.alipay.PayListener;
import lc.cn.thirdplatform.pay.wxpay.WecatPay;

public class PayFiveJiaoActivity extends BaseActivity<PayModel, PayView, PayPresenter> implements PayView {

    private static final int ORDERWATER = 1;
    @BindView(R.id.pay_title)
    TitleBar payTitle;
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
    @BindView(R.id.rl_banance)
    RelativeLayout rlBanance;
    @BindView(R.id.rl_banance_pay)
    RelativeLayout rlBanancePay;

    private int paytype;
    private String payTypeName;
    private String ordercode;
    private String ordertype;
    private int from;
    private String orderid;
    private double walletRemain;
    private double pay;
    private int voucherNum;
    private int count;
    private MyReceiver receiver;
    private String createtime;
    private int ordertype1;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pay_five_jiao);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        initTitle();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        ordercode = intent.getStringExtra("ordercode");
        ordertype = intent.getStringExtra("ordertype");
        createtime = intent.getStringExtra("time");
        ordertype1 = intent.getIntExtra("ordertype", -1);
        from = intent.getIntExtra("from", Constants.ORDERWATER);
        pay = intent.getDoubleExtra("pay", 0);
        if (pay % 5 > 0) {
            count = (int) (pay / 5) + 1;
        } else {
            count = (int) (pay / 5);
        }
        LogUtils.d("支付金额 ： " + pay);
        tvNeedPay.setText("￥" + pay);
        if (from == Constants.BARBER) {//是理发展示代金券
            rlDaijinquanPay.setVisibility(View.VISIBLE);
        }else if(from==Constants.RECHARGE){//钱包充值
            rlBanance.setVisibility(View.GONE);
            rlBanancePay.setVisibility(View.GONE);
        } else{
            rlDaijinquanPay.setVisibility(View.GONE);
        }
        getPresenter().findUserWallet();//查询钱包信息
        //广播接受者实例
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("COM.ALIPAY.SUCCESS.BROADCAST");
        registerReceiver(receiver, intentFilter);
    }

    private void initTitle() {
        payTitle.setBack(true);
        payTitle.setTitle("支付");
    }

    @OnClick({R.id.rl_daijinquan_pay, R.id.rl_banance_pay, R.id.rl_wx_pay, R.id.rl_alipay, R.id.bt_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_daijinquan_pay://代金券支付
                paytype = 5;
                payTypeName = "代金券支付";
                unCheckPay();
                ivDaijinjuanCheck.setImageResource(R.drawable.select);
                break;
            case R.id.rl_banance_pay://余额支付
                paytype = 3;
                payTypeName = "余额支付";
                unCheckPay();
                ivBalancePayCheck.setImageResource(R.drawable.select);
                break;
            case R.id.rl_wx_pay://微信支付
                paytype = 2;
                payTypeName = "微信支付";
                unCheckPay();
                ivWeixinPayCheck.setImageResource(R.drawable.select);
                break;
            case R.id.rl_alipay://支付宝支付
                paytype = 1;
                payTypeName = "支付宝支付";
                unCheckPay();
                ivAlpayCheck.setImageResource(R.drawable.select);
                break;
            case R.id.bt_confirm://确认支付，去顶支付成功进入支付成功界面
                switch (paytype) {
                    case 1:
                        getPresenter().aliPay(orderid, ordercode, ordertype, 1);
                        break;
                    case 2:
                        getPresenter().weiXinPay(orderid, ordercode, ordertype, 2);
                        break;
                    case 3:
                        getPresenter().getIsHasPwd();
                        break;
                    case 4:
                        //getPresenter().personalWalletPay(orderid, ordercode, ordertype, 4);
                        break;
                    case 5:
                        // 代金券5元一张
                        if (voucherNum < count) {
                            showToast("您的代金券不足");
                            return;
                        } else {
                            getPresenter().personalWalletPay(orderid, ordercode, ordertype, 5, "", count);
                        }
                        break;
                }
                break;
        }
    }

    //钱包支付
    private void showPayDialog() {
        final WalletPayDialog payDialog = new WalletPayDialog(this).builder();
        payDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = payDialog.getPwd();
                if (pwd.length() == 6) {
                    if (walletRemain < pay) {
                        showToast("您的余额不足");
                        return;
                    }
                    getPresenter().personalWalletPay(orderid, ordercode, ordertype, 3, pwd, 0);//钱包支付
                } else {
                    showToast("您输入的密码有误");
                }
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        payDialog.show();
    }

    private void unCheckPay() {
        ivDaijinjuanCheck.setImageResource(R.drawable.unselect);
        ivBalancePayCheck.setImageResource(R.drawable.unselect);
        ivWeixinPayCheck.setImageResource(R.drawable.unselect);
        ivAlpayCheck.setImageResource(R.drawable.unselect);
    }

    @Override
    public void aliPay(AlipayBean bean) {
        if (bean != null) {
            new Alipay(this).doPay(bean.getPayLink(), new PayListener() {
                @Override
                public void paySuccess() {
                    Intent intent = new Intent(PayFiveJiaoActivity.this,AliPaySuccAct.class);
                    intent.putExtra("ordertype",ordertype1);
                    intent.putExtra("createtime",createtime);
                    intent.putExtra("paytype",paytype);
                    intent.putExtra("ordercode",ordercode);
                    intent.putExtra("money",pay);
                    startActivity(intent);

                }

                @Override
                public void payFail() {
                    Toast.makeText(PayFiveJiaoActivity.this, "支付失败了" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                }
            });
        }
        finish();
    }

    //支付宝支付成功

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        EventBus.getDefault().unregister(this);
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

    @Override
    public void personalWallet(ActivityPostBean bean) {
        if (bean != null) {
            showToast("支付成功");
            Intent intent = new Intent(this, SubmitSuccessActivity.class);
            intent.putExtra("isPay", true);
            intent.putExtra("from", from);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void findUserWalletResult(UserWalletBean bean) {
        if (bean != null) {
            //对私钱包余额
            walletRemain = bean.getWalletRemain();
            double publicRemain = bean.getPublicRemain();//对公钱包余额
            voucherNum = bean.getVoucherNum();//理发券数量
            tvAvailableBalance.setText("￥" + walletRemain);
            LogUtils.i("代金券的数量" + voucherNum);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void isHasPwd(ActivityPostBean bean) {
        LogUtils.i("没有设置支付密码===============");
        if (bean != null) {
            int errCode = bean.getErrCode();
            if (errCode == 0) {
                showPayDialog();
            } else if (errCode == 1) {//没有支付密码
                LogUtils.i("没有设置支付密码===============");
                TsAlertDialog dialog=new TsAlertDialog(this).builder();
                dialog.setTitle("提示")
                        .setMsg("为了保护你财产的安全请先设置支付密码");
                dialog.setPositiveButton("设置支付密码", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(PayFiveJiaoActivity.this,AcountSaftActivity.class);
                        intent.putExtra("from", Constants.SET_PWD);
                        startActivity(intent);
                    }
                }).show();
                //getPresenter().personalWalletPay(orderid, ordercode, ordertype, 3, "", 0);//钱包支付
            }
        }
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intents) {
            LogUtils.d("我接受到广播啦！！！");
            Intent intent = new Intent(PayFiveJiaoActivity.this, SubmitSuccessActivity.class);
            intent.putExtra("isPay", true);
            intent.putExtra("payTypeName", payTypeName);
            startActivity(intent);
            finish();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(AliPaySuccess event) {
        LogUtils.d("--------------支付订单----------" + event == null ? "空" : "非空");
        if (event != null) {
        }
    }

    @Override
    public PayPresenter createPresenter() {
        return new PayPresenter();
    }

    @Override
    public PayModel createModel() {
        return new PayModelImpl();
    }
}
