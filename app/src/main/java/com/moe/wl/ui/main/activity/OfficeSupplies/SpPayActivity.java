package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.ui.main.activity.AliPaySuccAct;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.activity.me.AcountSaftActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.UserWalletBean;
import com.moe.wl.ui.main.model.SpPayModel;
import com.moe.wl.ui.main.modelimpl.SpPayModelImpl;
import com.moe.wl.ui.main.presenter.SpPayPresenter;
import com.moe.wl.ui.main.view.SpPayView;
import com.moe.wl.ui.mywidget.TsAlertDialog;
import com.moe.wl.ui.mywidget.WalletPayDialog;

import static com.moe.wl.R.id.from;

public class SpPayActivity extends BaseActivity<SpPayModel,SpPayView,SpPayPresenter> implements  SpPayView {

    @BindView(R.id.pay_title)
    TitleBar payTitle;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.iv_pay)
    ImageView ivPay;
    @BindView(R.id.tv_weixin_pay)
    TextView tvWeixinPay;
    @BindView(R.id.iv_pay_check)
    ImageView ivPayCheck;
    @BindView(R.id.tv_available_balance)
    TextView tvAvailableBalance;
    @BindView(R.id.tv_now_pay)
    TextView tvNowPay;
    @BindView(R.id.activity_sp_pay)
    LinearLayout activitySpPay;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    private boolean isCheck=false;
    private double publicRemain;
    private String orderid;
    private String ordercode;
    private String ordertype;
    private String createtime;
    private double pay;
    private int from;
    private int payType=4;//对公账户

    @Override
    public SpPayPresenter createPresenter() {
        return new SpPayPresenter();
    }

    @Override
    public SpPayModel createModel() {
        return new SpPayModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_sp_pay);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        orderid = intent.getStringExtra("orderid");
        ordercode = intent.getStringExtra("ordercode");
        ordertype = intent.getStringExtra("ordertype");
        createtime = intent.getStringExtra("time");
        from = intent.getIntExtra("from", Constants.ORDERWATER);
        pay = intent.getDoubleExtra("pay", 0);
        LogUtils.i("ordertype======  " + ordertype);
        initTitle();
       /* Intent intent =getIntent();
        String money = intent.getStringExtra("money");*/
        tvNeedPay.setText("￥"+pay);
        getPresenter().getData("8");
    }

    private void initTitle() {
        payTitle.setBack(true);
        payTitle.setTitle("支付");
    }

    @OnClick({R.id.rl_select, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_select:
                checkSelect();
                break;
            case R.id.tv_now_pay:
                // TODO: 2017/8/30 0030 支付
                if(!isCheck){
                    showToast("请选择支付方式");
                    return ;
                }
                if (publicRemain < pay) {
                    showToast("您的余额不足");
                    return;
                }
                getPresenter().getIsHasPwd();//是否又支付密码
                break;
        }
    }

    private void checkSelect() {
        isCheck=!isCheck;
        if(isCheck){
            ivPayCheck.setImageResource(R.drawable.select);
        }else{
            ivPayCheck.setImageResource(R.drawable.unselect);
        }
    }

    @Override//钱包信息
    public void getResult(UserWalletBean bean) {
        if (bean != null) {
            //对公钱包余额
            publicRemain = bean.getPublicRemain();
            tvAvailableBalance.setText("￥" + publicRemain);
            LogUtils.i("tvAvailableBalance===" + publicRemain);
        }
    }

    @Override
    public void personalWallet(ActivityPostBean bean) {
        if (bean != null) {
            showToast("支付成功");
            Intent intent = new Intent(this, AliPaySuccAct.class);
            int ordertype1 = Integer.parseInt(ordertype);
            intent.putExtra("ordertype", ordertype1);
            intent.putExtra("createtime", createtime);
            intent.putExtra("paytype",payType );
            intent.putExtra("ordercode", ordercode);
            intent.putExtra("money", pay);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void isHasPwd(ActivityPostBean bean) {
        LogUtils.i("没有设置支付密码===============");
        if (bean != null) {
            int errCode = bean.getErrCode();
            if (errCode == 0) {
                showPayDialog();
            } else if (errCode == 1) {//没有支付密码
                TsAlertDialog dialog = new TsAlertDialog(this).builder();
                dialog.setTitle("提示")
                        .setMsg("为了保护你财产的安全请先设置支付密码");
                dialog.setPositiveButton("设置支付密码", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SpPayActivity.this, AcountSaftActivity.class);
                        intent.putExtra("from", Constants.SET_PWD);
                        startActivity(intent);
                    }
                }).show();
                //getPresenter().personalWalletPay(orderid, ordercode, ordertype, 3, "", 0);//钱包支付
            }
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
                    getPresenter().personalWalletPay(orderid, ordercode, ordertype, payType, pwd, 0);//钱包支付
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
}
