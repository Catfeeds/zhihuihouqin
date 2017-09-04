package cn.lc.model.ui.main.activity.property_maintenance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.HorizontialListView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.BarberGridAdapter;
import cn.lc.model.ui.main.adapter.OrderTimeAdapter;

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
        gridAdapter = new BarberGridAdapter();
        gvOrderTime.setAdapter(gridAdapter);
        //gridAdapter.setTime(time);
        gvOrderTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridAdapter.changeColor(position);
            }
        });
    }

    private void initRecycler() {
        rvOrderTime.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        orderTimeAdapter = new OrderTimeAdapter(this);
        rvOrderTime.setAdapter(orderTimeAdapter);


    }

    @OnClick({R.id.tv_now, R.id.tv_confirm_select_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_now:
                break;
            case R.id.tv_confirm_select_time:
                break;
        }
    }
}
