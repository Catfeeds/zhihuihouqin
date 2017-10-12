package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.ActivityUserDetailBean;
import com.moe.wl.ui.main.model.SignUpPersonModel;
import com.moe.wl.ui.main.modelimpl.SignUpPersonModelImpl;
import com.moe.wl.ui.main.presenter.SignUpPersonPresenter;
import com.moe.wl.ui.main.view.SignUpPersonView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpPersonActivity extends BaseActivity<SignUpPersonModel,SignUpPersonView,SignUpPersonPresenter> implements SignUpPersonView {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_minzu)
    TextView tvMinzu;

    @Override
    public SignUpPersonPresenter createPresenter() {
        return new SignUpPersonPresenter();
    }

    @Override
    public SignUpPersonModel createModel() {
        return new SignUpPersonModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_sign_up_person);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        getPresenter().getData(id);
        initTitle();
    }
    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("报名人信息");
    }

    @Override
    public void getUserDetailSucc(ActivityUserDetailBean bean) {
        if(bean!=null){
            tvName.setText("姓名：" +bean.getUsername());
            tvPhone.setText("电话：" +bean.getMobile());
            tvMinzu.setText("民族：" + bean.getNation());
        }
    }
}
