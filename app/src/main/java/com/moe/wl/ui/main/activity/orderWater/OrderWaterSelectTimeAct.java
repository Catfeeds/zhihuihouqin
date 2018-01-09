package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.OrderWaterSelectTimeAdapter;
import com.moe.wl.ui.main.adapter.TimeAdapter;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.model.OrderWaterTimeModel;
import com.moe.wl.ui.main.modelimpl.OrderWaterModelImpl;
import com.moe.wl.ui.main.presenter.OrderWaterTimePresenter;
import com.moe.wl.ui.main.view.OrderWaterTimeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderWaterSelectTimeAct extends BaseActivity<OrderWaterTimeModel,OrderWaterTimeView,OrderWaterTimePresenter> implements OrderWaterTimeView {

    @BindView(R.id.grid_am)
    GridView gridAm;
    @BindView(R.id.grid_pm)
    GridView gridPm;
    @BindView(R.id.title)
    TitleBar title;
    private OrderWaterSelectTimeAdapter adapterAm;
    private OrderWaterSelectTimeAdapter adapterPm;
    private TimeAdapter am;
    private OrderWaterTimeBean timeBean;
    private String time;
    private int mId;
    private TimeAdapter pm;

    @Override
    public OrderWaterTimePresenter createPresenter() {
        return new OrderWaterTimePresenter();
    }

    @Override
    public OrderWaterTimeModel createModel() {
        return new OrderWaterModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_order_water_select_time);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setTitle("选择时间");
        title.setBack(true);
        title.setTitleRight("确定");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("time",time);
                intent.putExtra("id",mId);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        am = new TimeAdapter();
        pm = new TimeAdapter();
        gridAm.setAdapter(am);
        gridPm.setAdapter(pm);
        am.setListener(new TimeAdapter.OnTimeItemClickListener() {
            @Override
            public void clickListener(int position) {
                String timeStr = timeBean.getAmList().get(position).getTimeStr();
                int id1 = timeBean.getAmList().get(position).getId();
                time=timeStr;
                mId =id1;
                pm.clearTime();
            }
        });
        pm.setListener(new TimeAdapter.OnTimeItemClickListener() {
            @Override
            public void clickListener(int position) {
                String timeStr = timeBean.getPmList().get(position).getTimeStr();
                int id1 = timeBean.getPmList().get(position).getId();
                time=timeStr;
                mId =id1;
                am.clearTime();
            }
        });
        //获取时间
        getPresenter().getTime();
    }

    @Override
    public void getTimeSucc(OrderWaterTimeBean bean) {
        if(bean!=null){
            timeBean =bean;
            am.setData(bean,0);
            pm.setData(bean,1);
        }
    }

}
