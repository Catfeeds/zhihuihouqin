package com.moe.wl.ui.main.activity.MealsRecharge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeContentAct extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        setContentView(R.layout.activity_recharge_content);
        ButterKnife.bind(this);
        title.setBack(true);
        title.setTitle("充值说明");
        tvContent.setText("一日，我与上帝一同出行。 路过一条河时，我看到水里有一个人在挣扎，我指着那个人问：“上帝，为什么你不去救那个人，难道他没有向你祈祷吗？”上帝回答：“不，他向我祈祷了两次，但我也救了他两次——第一次我让一根圆木从他身边漂过，他没有去抓。第二次我让一个人划着竹筏从他身边经过，他又不肯去抓那个人向他伸出的手。你让我怎样去救他，难道非得我亲手去把他拉上来？”我哑口无言。\n" +
                "\n" +
                "一日，我与上帝一同出行。 路过一条河时，我看到水里有一个人在挣扎，我指着那个人问：“上帝，为什么你不去救那个人，难道他没有向你祈祷吗？”上帝回答：“不，他向我祈祷了两次，但我也救了他两次——第一次我让一根圆木从他身边漂过，他没有去抓。第二次我让一个人划着竹筏从他身边经过，他又不肯去抓那个人向他伸出的手。你让我怎样去救他，难道非得我亲手去把他拉上来？”我哑口无言。\n" +
                "\n" +
                "一日，我与上帝一同出行。 路过一条河时，我看到水里有一个人在挣扎，我指着那个人问：“上帝，为什么你不去救那个人，难道他没有向你祈祷吗？”上帝回答：“不，他向我祈祷了两次，但我也救了他两次——第一次我让一根圆木从他身边漂过，他没有去抓。第二次我让一个人划着竹筏从他身边经过，他又不肯去抓那个人向他伸出的手。你让我怎样去救他，难道非得我亲手去把他拉上来？”我哑口无言。");
    }
}
