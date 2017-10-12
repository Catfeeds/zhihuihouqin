package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgSettingActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_setting);
        ButterKnife.bind(this);
        title.setBack(true);
        title.setTitle("消息设置");
    }
}
