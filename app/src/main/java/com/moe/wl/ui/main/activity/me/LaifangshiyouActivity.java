package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

public class LaifangshiyouActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_shiyou)
    EditText etShiyou;
    @BindView(R.id.tb_confirm)
    Button tbConfirm;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laifangshiyou);
        ButterKnife.bind(this);
        from = getIntent().getStringExtra("from");
        initTitle();
    }

    private void initTitle() {
        if (Constants.YIJIANFANKUI.equals(from)) {
            title.setTitle("意见反馈");
            etShiyou.setHint("请在这里写下您宝贵的意见，让我们不断进步，更好的为您服务！");
            title.setBack(true);
        } else {
            etShiyou.setHint("请输入来访事由");
            title.setTitle("来访事由");
            title.setBack(true);
        }
    }

    @OnClick(R.id.tb_confirm)
    public void onViewClicked() {
        String shiyou = etShiyou.getText().toString().trim();
        Intent intent = new Intent();
        if (Constants.YIJIANFANKUI.equals(from)) {
            intent.putExtra("yijianfankui", shiyou);
            setResult(Constants.SHIYOU, intent);
        } else {
            intent.putExtra("shiyou", shiyou);
            setResult(Constants.SHIYOU, intent);
        }
        finish();
    }
}
