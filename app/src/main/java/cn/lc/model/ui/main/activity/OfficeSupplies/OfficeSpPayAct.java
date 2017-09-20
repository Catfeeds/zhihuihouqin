package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class OfficeSpPayAct extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_need_to_pay)
    TextView tvNeedToPay;
    @BindView(R.id.iv_select_acount)
    ImageView ivSelectAcount;
    @BindView(R.id.ll_select_public)
    LinearLayout llSelectPublic;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.bt_pay)
    Button btPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_sp_pay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_select_public, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_select_public:
                break;
            case R.id.bt_pay:
                Intent intent = new Intent(this, OfficeSpPaySuccessAct.class);
                startActivity(intent);
                break;
        }
    }
}
