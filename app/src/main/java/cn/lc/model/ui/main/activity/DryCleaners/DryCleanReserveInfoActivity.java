package cn.lc.model.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.DryCleanersLvAdapter;
import mvp.cn.util.CommonUtil;

public class DryCleanReserveInfoActivity extends AppCompatActivity {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_send_time)
    ImageView ivSendTime;
    @BindView(R.id.lv_gry_clean)
    ListView lvGryClean;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_dry_clean_reserve_info)
    RelativeLayout activityDryCleanReserveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_clean_reserve_info);
        ButterKnife.bind(this);
        initTitle();
        initListView();
        CommonUtil.closeSoftKeyboard(this,etPhoneNum);
    }

    private void initListView() {
        DryCleanersLvAdapter lvAdapter = new DryCleanersLvAdapter(this);
        lvGryClean.setAdapter(lvAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("干洗店");
    }

    @OnClick({R.id.tv_sum, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sum:
                break;
            case R.id.tv_submit:
                Intent intent=new Intent(this,ConfirmDryCleanOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
