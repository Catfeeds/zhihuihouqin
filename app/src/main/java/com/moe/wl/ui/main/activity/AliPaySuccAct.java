package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AliPaySuccAct extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.how_much)
    TextView howMuch;
    @BindView(R.id.tv_payType)
    TextView tvPayType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.tv_ordercode)
    TextView tvOrdercode;
    @BindView(R.id.tv_bt)
    TextView tvBt;
    private int ordertype;
    private String createtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_pay_succ);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        /*intent.putExtra("ordertype",ordertype1);
                    intent.putExtra("createtime",createtime);
                    intent.putExtra("paytype",paytype);
                    intent.putExtra("ordercode",ordercode);
                    intent.putExtra("money",pay);*/
        Intent intent = getIntent();
        //订单类型
        ordertype = intent.getIntExtra("ordertype", -1);
        createtime = intent.getStringExtra("createtime");
        int paytype = intent.getIntExtra("paytype", 0);
        double money = intent.getDoubleExtra("money", 0);
        howMuch.setText("￥"+money);
       /* if(ordertype==)
        tvPayType.setText();*/
    }

    @OnClick(R.id.tv_bt)
    public void onClick() {
    }
}
