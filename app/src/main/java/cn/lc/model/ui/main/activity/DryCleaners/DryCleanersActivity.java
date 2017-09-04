package cn.lc.model.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import mvp.cn.util.CommonUtil;

public class DryCleanersActivity extends AppCompatActivity {

    @BindView(R.id.dry_cleaners_title)
    TitleBar titleBar;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.iv_cut_hear_logo)
    ImageView ivCutHearLogo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_hot_phone)
    ImageView ivHotPhone;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.activity_dry_cleaners)
    LinearLayout activityDryCleaners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_cleaners);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("店铺");
    }

    @OnClick({R.id.iv_hot_phone, R.id.tv_now_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hot_phone:
                break;
            case R.id.tv_now_order:
                Intent intent=new Intent(this,DryCleanReserveInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
