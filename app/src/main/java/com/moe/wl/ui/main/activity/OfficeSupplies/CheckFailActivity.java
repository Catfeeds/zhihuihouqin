package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckFailActivity extends AppCompatActivity {

    @BindView(R.id.tv_fail)
    TextView tvFail;
    @BindView(R.id.title)
    TitleBar tilte;
    @BindView(R.id.activity_check_fail)
    LinearLayout activityCheckFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fail);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        ButterKnife.bind(this);
        tilte.setBack(true);
        tilte.setTitle("失败原因");

    }
}
