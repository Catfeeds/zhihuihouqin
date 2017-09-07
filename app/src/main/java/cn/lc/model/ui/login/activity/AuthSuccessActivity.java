package cn.lc.model.ui.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class AuthSuccessActivity extends AppCompatActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_success);
        ButterKnife.bind(this);
        mTitleBar.setBack(true);
        mTitleBar.setTitle("提交结果");
    }
}
