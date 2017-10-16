package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.support.v7.app.AppCompatActivity;
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

public class MyPurseActivity extends BaseActivity<MyPurseModel,MyPurseView,MyPursePresenter> implements MyPurseView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_quota)
    TextView tvQuota;
    @BindView(R.id.iv_wen)
    ImageView ivWen;
    @BindView(R.id.ll_haircut_ticket)
    LinearLayout llHaircutTicket;
    @BindView(R.id.tv_ticket_count)
    TextView tvTicketCount;
    @BindView(R.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R.id.ll_passward_management)
    LinearLayout llPasswardManagement;

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
                Intent intent=new Intent(MyPurseActivity.this,PayDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @OnClick({ R.id.iv_wen ,R.id.ll_recharge, R.id.ll_passward_management})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_wen:
                Intent intent2=new Intent(this,BalanceExplainActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_recharge:
                Intent intent=new Intent(this,RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_passward_management:
                Intent intent1=new Intent(this,PwdManageMentActivity.class);
                startActivity(intent1);
                break;
        }
    }

    /**
     * @param bean
     */
    @Override
    public void getInfoSucc(UserWalletBean bean) {
        if(bean!=null){
            tvBalance.setText(bean.getPublicRemain()+"");//对公钱包余额
            tvQuota.setText(bean.getWalletRemain()+"元");//对私钱包余额
            tvTicketCount.setText(bean.getVoucherNum()+"张");//理发券数量
            int hasBuyAuth = bean.getHasBuyAuth();//是否有团购权限
            int payPasswordState = bean.getPayPasswordState();//是否有支付密码
            if(hasBuyAuth==1){//有团购权限

            }else{

            }

        }

    }
}
