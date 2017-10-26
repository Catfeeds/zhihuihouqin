package com.moe.wl.ui.main.activity.MealsRecharge;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.RechargeAmountAdapter;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.ChargeOrderBean;
import com.moe.wl.ui.main.bean.FindRemainBean;
import com.moe.wl.ui.main.bean.LastCardNumBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.MealsRechargeModel;
import com.moe.wl.ui.main.modelimpl.MealsRechargeModelImpl;
import com.moe.wl.ui.main.presenter.MealsRechargePresenter;
import com.moe.wl.ui.main.view.MealsRechargeView;
import com.moe.wl.ui.mywidget.BottomQuerybalanceDialog;
import com.moe.wl.ui.mywidget.BottomRechargeDialog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.cn.thirdplatform.pay.alipay.Alipay;
import lc.cn.thirdplatform.pay.alipay.PayListener;
import lc.cn.thirdplatform.pay.wxpay.WecatPay;

public class MealsRechargeActivity extends BaseActivity<MealsRechargeModel,MealsRechargeView,MealsRechargePresenter> implements MealsRechargeView {

    private static final int ZHIFUBAO = 1;
    private static final int WEIXIN = 2;
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.gv_recharge_amount)
    GridView gvRechargeAmount;
    @BindView(R.id.tv_recharge_money)
    TextView tvRechargeMoney;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_check_balance)
    TextView tvCheckBalance;
    private BottomQuerybalanceDialog dialog;
    private RechargeAmountAdapter adapter;
    private List<String> datas= Arrays.asList("50","100","200","300","400","500");

    @Override
    public MealsRechargePresenter createPresenter() {
        return new MealsRechargePresenter();
    }

    @Override
    public MealsRechargeModel createModel() {
        return new MealsRechargeModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_meals_recharge);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        getPresenter().getFindRemain();//查看余额

        adapter = new RechargeAmountAdapter(this);
        gvRechargeAmount.setAdapter(adapter);
        adapter.setData(datas);
        adapter.setListener(new RechargeAmountAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(String text) {
                tvRechargeMoney.setText(text);
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("餐费充值");
        title.setTitleRight("充值订单");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(MealsRechargeActivity.this,RechargeOrederActivity.class);
                startActivity(intent);*/
            }
        });
    }

    @OnClick({R.id.tv_confirm, R.id.tv_check_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm://支付
                BottomRechargeDialog dialog = new BottomRechargeDialog(this, R.style.dialog_style);
                String rechargeMoney = tvRechargeMoney.getText().toString().trim();
                dialog.setAmount(rechargeMoney);
                dialog.show();
                dialog.setListener(new BottomRechargeDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClickListener(String money,int paytype) {
                        String cardNum = etPhone.getText().toString().toString().trim();
                        //生成订单
                        getPresenter().generateChargeOrder(money,paytype,cardNum);

                    }
                });
                break;
            case R.id.tv_check_balance://查询余额
                this.dialog = new BottomQuerybalanceDialog(this, R.style.dialog_style);
                this.dialog.show();
                //点击提现
               this.dialog.setListener(new BottomQuerybalanceDialog.OnWithdrawalClickListener() {
                   @Override
                   public void onWithdrawalClickListener() {
                        showToast("还没有接口");
                   }
               });
                break;
        }
    }

    @Override
    public void getfindLastCardNumResult(LastCardNumBean bean) {

        if(bean!=null){
            if(TextUtils.isEmpty(bean.getLastNum())){//如果卡号为空,设置当前登录手机号码
                String phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
                etPhone.setText(phoneNumber);
            }else{
                etPhone.setText(bean.getLastNum());
            }
        }
    }

    @Override
    public void getFindRemain(FindRemainBean bean) {
        getPresenter().getLastCardNum();//获得最后卡号
        LogUtils.i("获取余额成功了");
        if(bean!=null){
            FindRemainBean.CfEntityBean cfEntity = bean.getCfEntity();
            if(cfEntity!=null){
                int remainSum = cfEntity.getRemainSum();//余额
                int subsidySum = cfEntity.getSubsidySum();//补贴金额
                if(dialog!=null){
                    dialog.setData(remainSum,subsidySum);
                }
            }else{
                LogUtils.i("cfEntity=="+"为null");
            }
        }

    }

    @Override
    public void generateChargeOrder(ChargeOrderBean bean) {
        if(bean!=null){
            int paytype = bean.getPaytype();
            switch (paytype){
                case ZHIFUBAO:
                    getPresenter().aliPay("",bean.getOrdercode()+"" ,bean.getOrdertype()+"",paytype);
                    break;
                case WEIXIN:
                    getPresenter().weiXinPay("",bean.getOrdercode()+"" ,bean.getOrdertype()+"",paytype);
                    break;
            }
        }
    }

    @Override
    public void aliPay(AlipayBean bean) {
        if(bean!=null){
            String payLink = bean.getPayLink();
            new Alipay(MealsRechargeActivity.this).doPay(payLink, new PayListener() {
                @Override
                public void paySuccess() {

                }

                @Override
                public void payFail() {

                }
            });
        }
    }

    @Override
    public void weiXinPay(WeixinBean bean) {
        if(bean!=null){
            Gson gson = new Gson();
            String json = gson.toJson(bean);
            new WecatPay(MealsRechargeActivity.this).doPay(json);
        }

    }
}
