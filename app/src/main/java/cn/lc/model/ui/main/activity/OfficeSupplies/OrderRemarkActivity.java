package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class OrderRemarkActivity extends AppCompatActivity {

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;
    @BindView(R.id.et_scanner)
    EditText etScanner;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.activity_order_remark)
    LinearLayout activityOrderRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_remark);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("订单备注");
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        finish();
    }
}
