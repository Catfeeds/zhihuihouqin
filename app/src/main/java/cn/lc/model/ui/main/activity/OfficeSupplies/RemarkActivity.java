package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.widget.TitleBar;

public class RemarkActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_write)
    EditText etWrite;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("订单备注");
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        String remark = etWrite.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("remark",remark);
        setResult(Constants.REMARK,intent);
        finish();
    }
}
