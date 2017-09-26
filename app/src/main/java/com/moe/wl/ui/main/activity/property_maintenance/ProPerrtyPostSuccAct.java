package com.moe.wl.ui.main.activity.property_maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ServiceOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

public class ProPerrtyPostSuccAct extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_perrty_post_succ);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("发布结果");
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent=new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("from", Constants.PROPERRY);
        startActivity(intent);
    }
}
