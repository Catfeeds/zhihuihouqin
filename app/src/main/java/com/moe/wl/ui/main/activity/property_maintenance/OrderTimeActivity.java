package com.moe.wl.ui.main.activity.property_maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.OrderTimeAdapter;

import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

import com.moe.wl.ui.main.adapter.BarberGridAdapter;
import com.moe.wl.ui.main.adapter.OrderTimesAdapter;
import mvp.cn.util.DateUtils;


public class OrderTimeActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.rv_order_time)
    RecyclerView rvOrderTime;
    @BindView(R.id.gv_order_time)
    GridView gvOrderTime;
    @BindView(R.id.tv_now)
    TextView tvNow;
    @BindView(R.id.tv_confirm_select_time)
    TextView tvConfirmSelectTime;
    @BindView(R.id.activity_order_time)
    RelativeLayout activityOrderTime;
    private List<String> time= Arrays.asList(
            "08:00","08:30","09:00","09:30","10:00","10:30","11:00",
            "11:30","12:00","12:30",
            "13:00","13:30","14:00","14:30","15:00","15:30","16:00",
            "16:30","17:00","17:30","18:00");
    private BarberGridAdapter gridAdapter;
    private OrderTimeAdapter orderTimeAdapter;
    private OrderTimesAdapter orderTimesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_time);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("预约时间");
        initRecycler();
        initGrid();
    }

    private void initGrid() {

        orderTimesAdapter = new OrderTimesAdapter();
        gvOrderTime.setAdapter(orderTimesAdapter);
        orderTimesAdapter.setData(time);

    }

    private void initRecycler() {
        rvOrderTime.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        orderTimeAdapter = new OrderTimeAdapter(this);
        rvOrderTime.setAdapter(orderTimeAdapter);
        List<String> week = DateUtils.get7week();
        List<String> date = DateUtils.get7date();
        orderTimeAdapter.setData(week,date);

    }

    @OnClick({R.id.tv_now, R.id.tv_confirm_select_time})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_now:
                intent.putExtra("time","立即上门");
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.tv_confirm_select_time:
                String s = orderTimeAdapter.getTime();
                int selectPosition = orderTimesAdapter.getSelectPosition();
                //获得时间
                intent.putExtra("time", s+" "+this.time.get(selectPosition));
                setResult(RESULT_OK,intent);
                finish();
                break;

        }
    }
}
