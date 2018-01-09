package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.HasPwdModel;
import com.moe.wl.ui.main.modelimpl.HasPwdModelImpl;
import com.moe.wl.ui.main.presenter.HasPwdPresenter;
import com.moe.wl.ui.main.view.HasPwdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PwdManageMentActivity extends BaseActivity<HasPwdModel,HasPwdView,HasPwdPresenter> implements HasPwdView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.ll_haspwd)
    LinearLayout llHaspwd;
    @BindView(R.id.ll_nohaspwd)
    LinearLayout llNoHaspwd;

    @Override
    public HasPwdPresenter createPresenter() {
        return new HasPwdPresenter();
    }

    @Override
    public HasPwdModel createModel() {
        return new HasPwdModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pwd_manage_ment);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        getPresenter().getIsHasPwd();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("支付密码管理");
    }

    @OnClick({R.id.tv_set_pay_pwd,R.id.tv_change_pay_pwd, R.id.tv_forget_pay_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_change_pay_pwd:
                Intent intent=new Intent(this,AcountSaftActivity.class);
                intent.putExtra("from", Constants.CHANGE_PWD);
                startActivity(intent);
                break;
            case R.id.tv_set_pay_pwd:
                Intent intent1=new Intent(this,AcountSaftActivity.class);
                intent1.putExtra("from", Constants.SET_PWD);
                startActivity(intent1);
                break;
            case R.id.tv_forget_pay_pwd:
                Intent intent2=new Intent(this,AcountSaftActivity.class);
                intent2.putExtra("from", Constants.FORGET_PWD);
                startActivity(intent2);
                finish();
                break;
        }
    }

    @Override
    public void isHasPwd(ActivityPostBean bean) {
        LogUtils.i("是否有交易密码:"+bean.getErrCode());
        if(bean!=null){
            if(bean.getErrCode()==0){
                llHaspwd.setVisibility(View.VISIBLE);
                llNoHaspwd.setVisibility(View.GONE);
            }else{
                llHaspwd.setVisibility(View.GONE);
                llNoHaspwd.setVisibility(View.VISIBLE);
            }
        }
    }
}
