package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class FillInfoActivity extends AppCompatActivity {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.activity_fill_info)
    LinearLayout activityFillInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("填写信息");
    }

    @OnClick(R.id.tv_sign_up)
    public void onViewClicked() {
        // TODO: 2017/8/24 0024 报名成功后弹出对话框，几秒后回到活动详情界面，报名列表人数加1

    }
}
