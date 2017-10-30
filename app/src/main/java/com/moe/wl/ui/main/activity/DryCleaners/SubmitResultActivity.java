package com.moe.wl.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ServiceOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmitResultActivity extends AppCompatActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_result);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("提交结果");
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("index", 0);
        intent.putExtra("from", getIntent().getIntExtra("from", 0));
    }
}
