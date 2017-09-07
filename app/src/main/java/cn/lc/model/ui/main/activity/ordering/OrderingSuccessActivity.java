package cn.lc.model.ui.main.activity.ordering;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

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
