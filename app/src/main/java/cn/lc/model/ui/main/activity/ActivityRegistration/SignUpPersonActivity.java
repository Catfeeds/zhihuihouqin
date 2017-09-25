package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class SignUpPersonActivity extends AppCompatActivity {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_minzu)
    TextView tvMinzu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_person);
        ButterKnife.bind(this);
        initTitle();
        showMessage();
    }

    private void showMessage() {
        tvName.setText("姓名：" + getIntent().getStringExtra("name"));
        tvPhone.setText("电话：" + getIntent().getStringExtra("phone"));
        tvMinzu.setText("民族：" + "");
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("报名人信息");
    }
}
