package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceExplainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_des)
    TextView tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_explain);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("余额说明");
        tvDes.setText("\n" + "\n" + "1.自己充值的金额方位私人账户金额\n" + "\n" + "2.一日，我与上帝一同出行。 路过一条河时，我看到水里有3.一个人在挣扎，我指着那个人问：“上帝，为什么你不去救4.那个人，难道他没有向你祈祷吗？”上帝回答：“不，他向5.我祈祷了两次，但我也救了他两次——第一次我让一根圆6.木从他身边漂过，他没有去抓。第二次我让一个人划着竹筏从他身边经过，他又不肯去抓那个人向他伸出的手。你让我怎样去救他，难道非得我亲手去把他拉上来？”我哑口无\n");
    }
}
