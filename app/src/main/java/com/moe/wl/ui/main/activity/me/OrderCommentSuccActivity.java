package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/9 0009
 */
public class OrderCommentSuccActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment_succ);
        ButterKnife.bind(this);
        titleBar.setBack(true);
        titleBar.setTitle("评价成功");
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

}
