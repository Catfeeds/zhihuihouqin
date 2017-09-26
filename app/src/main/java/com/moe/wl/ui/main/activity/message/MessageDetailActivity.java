package com.moe.wl.ui.main.activity.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.MessageListBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/25 0025
 */
public class MessageDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.time)
    TextView time;
    private MessageListBean.PageEntity.ListEntity data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        ButterKnife.bind(this);

        data = (MessageListBean.PageEntity.ListEntity) getIntent().getSerializableExtra("Data");
        titleBar.setTitle("消息详情");
        titleBar.setBack(true);
        if (data == null) {
            return;
        }
        title.setText(data.getMessagetitle());
        content.setText(data.getContent());
        time.setText(data.getCreatetime());
    }
}
