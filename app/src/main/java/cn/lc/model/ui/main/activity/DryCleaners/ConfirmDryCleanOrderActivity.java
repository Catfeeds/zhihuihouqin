package cn.lc.model.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ConfirmDryCleanOrderAdapter;

public class ConfirmDryCleanOrderActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.lv_order_info)
    ListView lvOrderInfo;
    @BindView(R.id.activity_confirm_dry_clean_order)
    LinearLayout activityConfirmDryCleanOrder;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_dry_clean_order);
        ButterKnife.bind(this);
        initTitle();
        initListView();
    }

    private void initListView() {
        ConfirmDryCleanOrderAdapter orderAdapter = new ConfirmDryCleanOrderAdapter(this);
        lvOrderInfo.setAdapter(orderAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确定订单");
    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        Intent intent=new Intent(this,SubmitResultActivity.class);
        startActivity(intent);
    }
}
