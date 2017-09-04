package cn.lc.model.ui.main.activity.DryCleaners;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class SubmitResultActivity extends AppCompatActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_result);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("提交结果");
    }
}
