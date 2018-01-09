package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.PayDepositModel;
import com.moe.wl.ui.main.modelimpl.PayDepositModelImpl;
import com.moe.wl.ui.main.presenter.PayDepositPresenter;
import com.moe.wl.ui.main.view.PayDepositView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订水押金
 */
public class PayDepositActivity extends BaseActivity<PayDepositModel,PayDepositView,PayDepositPresenter> implements PayDepositView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    double pay=50;

    @Override
    public PayDepositPresenter createPresenter() {
        return new PayDepositPresenter();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pay_deposit);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        intiTitle();
    }

    @Override
    public PayDepositModel createModel() {
        return new PayDepositModelImpl();
    }

    private void intiTitle() {
        title.setBack(true);
        title.setTitle("缴纳押金");
    }

    @OnClick(R.id.tv_pay)
    public void onViewClicked() {
        getPresenter().getOrder(( pay),20);
    }

    @Override
    public void getOrder(WalletOrderBean bean) {
        if(bean!=null){
            String ordercode = bean.getOrdercode();
            int ordertype = bean.getOrdertype();
            int orderid = bean.getOrderid();
            Intent intent=new Intent(this, PayFiveJiaoActivity.class);
            intent.putExtra("pay",pay);
            intent.putExtra("orderid",orderid+"");
            intent.putExtra("ordercode",ordercode);
            intent.putExtra("ordertype",ordertype+"");
            intent.putExtra("from", Constants.ORDERWATER);
            startActivity(intent);
        }
    }
}
