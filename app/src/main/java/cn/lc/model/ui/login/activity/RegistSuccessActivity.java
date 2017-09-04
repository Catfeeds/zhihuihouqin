package cn.lc.model.ui.login.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.mywidget.AlertDialog;

public class RegistSuccessActivity extends AppCompatActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_success);
        ButterKnife.bind(this);
        initTitle();
        showhintDialog();
    }

    private void showhintDialog() {
        new AlertDialog(RegistSuccessActivity.this).builder().setTitle("提示")
                .setMsg("注册完成后,需继续填写个人身份信息审核后,帐号方能使用")
                .setPositiveButton("确认退出", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    private void initTitle() {
        mTitleBar.setBack(true);
        mTitleBar.setTitle("注册成功");
    }
}
