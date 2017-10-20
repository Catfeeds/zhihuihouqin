package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */
public class OrderCommentSuccessActivity extends AppCompatActivity {

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;
    @BindView(R.id.goto_home)
    TextView gotoHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment_success);
        ButterKnife.bind(this);
        titleBar.setBack(true);
        titleBar.setTitle("提交成功");
    }

    @OnClick({R.id.goto_home})
    public void onViewClicked(View view){
        finish();
    }

}
