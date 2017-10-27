package com.moe.wl.ui.main.activity.vegetable;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.SelectTimeBean;
import com.moe.wl.ui.main.bean.VegetableBean;
import com.moe.wl.ui.main.model.VegetableOrderMessageModel;
import com.moe.wl.ui.main.modelimpl.VegetableOrderMessageModelImpl;
import com.moe.wl.ui.main.presenter.VegetableOrderMessagePresenter;
import com.moe.wl.ui.main.view.VegetableOrderMessageView;
import com.moe.wl.ui.mywidget.SelectTimePop;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/18 0018
 */
public class VegetableOrderMessageActivity extends BaseActivity<VegetableOrderMessageModel, VegetableOrderMessageView, VegetableOrderMessagePresenter> implements VegetableOrderMessageView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private List<VegetableBean.PageEntity.ListEntity> data;
    private int timeID = 0;
    private String time;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_vegetable_order_message);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("预订信息");
        if ("".equals(SharedPrefHelper.getInstance().getRealName()) || SharedPrefHelper.getInstance().getRealName() == null) {
            userName.setText(SharedPrefHelper.getInstance().getNickname());
        } else {
            userName.setText(SharedPrefHelper.getInstance().getRealName());
        }
        phoneNumber.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        phoneNumber.setSelection(SharedPrefHelper.getInstance().getPhoneNumber().length());
        data = (List<VegetableBean.PageEntity.ListEntity>) getIntent().getSerializableExtra("Data");
    }

    @OnClick({R.id.sure, R.id.ll_arrive_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure:
                confirm();
                break;

            case R.id.ll_arrive_time:
                getPresenter().getVegetableSelectTime();
                break;
        }
    }

    private void confirm() {
        if (!VerifyCheck.isMobilePhoneVerify(phoneNumber.getText().toString().trim())) {
            ToastUtil.showToast(this, "请输入正确的手机号码");
            return;
        }
        if (timeID == 0) {
            ToastUtil.showToast(this, "请选择取餐时间！");
            return;
        }
        Intent intent = new Intent(this, ConfirmVegetableOrderActivity.class);
        intent.putExtra("Data", (Serializable) data);
        intent.putExtra("TimeID", timeID);
        intent.putExtra("Time", time);
        intent.putExtra("PhoneNumber", phoneNumber.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    public void getVegetableSelectTimeSucc(SelectTimeBean bean) {
        SelectTimePop pop = new SelectTimePop(this, bean, new SelectTimePop.OnSelectClick() {
            @Override
            public void onClick(int id, String time, boolean isAm) {
                VegetableOrderMessageActivity.this.time = isAm ? "上午 " + time : "下午 " + time;
                timeID = id;
                tvTime.setText(isAm ? "上午 " + time : "下午 " + time);
            }
        });
        pop.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
    }

    @Override
    public VegetableOrderMessagePresenter createPresenter() {
        return new VegetableOrderMessagePresenter();
    }

    @Override
    public VegetableOrderMessageModel createModel() {
        return new VegetableOrderMessageModelImpl();
    }
}
