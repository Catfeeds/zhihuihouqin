package com.moe.wl.ui.main.activity.DryCleaners;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

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
}
