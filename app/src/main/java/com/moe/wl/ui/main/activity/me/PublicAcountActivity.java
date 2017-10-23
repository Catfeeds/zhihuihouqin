package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublicAcountActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_wen)
    ImageView ivWen;
    @BindView(R.id.tv_acount_banlance)
    TextView tvAcountBanlance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_acount);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        Intent intent = getIntent();
        int publicRemain = intent.getIntExtra("publicRemain", 0);
        title.setBack(true);
        title.setTitle("对公账户");
        tvAcountBanlance.setText(publicRemain+"");
    }

    @OnClick(R.id.iv_wen)
    public void onViewClicked() {
        Intent intent2=new Intent(this,BalanceExplainActivity.class);
        startActivity(intent2);
    }
}
