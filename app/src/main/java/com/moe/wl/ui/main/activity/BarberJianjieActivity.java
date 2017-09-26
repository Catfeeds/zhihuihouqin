package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

public class BarberJianjieActivity extends AppCompatActivity {

    @BindView(R.id.jianjie_content_title)
    TitleBar titleBar;
    @BindView(R.id.tv_jianjie_content)
    TextView tvJianjieContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_jianjie);
        ButterKnife.bind(this);
        initTitle();
        initContent();
    }

    private void initContent() {
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        tvJianjieContent.setText(content);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("简介");
    }
}
