package com.moe.wl.ui.main.activity.ordering;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */
public class OrderingSuccessActivity extends AppCompatActivity {

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_success);
        ButterKnife.bind(this);
        titleBar.setBack(true);
        titleBar.setTitle("提交成功");
    }
}
