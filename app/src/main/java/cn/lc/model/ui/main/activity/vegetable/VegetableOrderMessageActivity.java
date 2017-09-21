package cn.lc.model.ui.main.activity.vegetable;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.bean.SelectTimeBean;
import cn.lc.model.ui.main.bean.VegetableBean;
import cn.lc.model.ui.main.model.VegetableOrderMessageModel;
import cn.lc.model.ui.main.modelimpl.VegetableOrderMessageModelImpl;
import cn.lc.model.ui.main.presenter.VegetableOrderMessagePresenter;
import cn.lc.model.ui.main.view.VegetableOrderMessageView;
import cn.lc.model.ui.mywidget.SelectTimePop;
import mvp.cn.util.ToastUtil;

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
        userName.setText(SharedPrefHelper.getInstance().getRealName());
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
        if (phoneNumber.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(this, "请输入手机号！");
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
