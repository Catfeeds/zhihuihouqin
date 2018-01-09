package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.bean.RepairTimeBean;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.BarberGridAdapter;
import com.moe.wl.ui.main.adapter.OrderTimesAdapter;
import com.moe.wl.ui.main.adapter.TimeOrderAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtils;
import rx.Observable;
import rx.Subscriber;


public class OrderWaterTimeActivity extends Base2Activity {

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
    private BarberGridAdapter gridAdapter;
    private TimeOrderAdapter orderTimeAdapter;
    private OrderTimesAdapter orderTimesAdapter;
    private List<RepairTimeBean.AppointmentListBean> appointmentList;
    private String userId;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_order_time);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        userId = SharedPrefHelper.getInstance().getUserId();
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
        orderTimesAdapter.setListener(new OrderTimesAdapter.OnTimeSelectListener() {
            @Override
            public void onTimeSelectListener(int position) {
                for (int i = 0; i < appointmentList.size(); i++) {
                    RepairTimeBean.AppointmentListBean appointmentListBean = appointmentList.get(i);
                    if (i == position) {
                        appointmentListBean.setChecked(true);
                    } else {
                        appointmentListBean.setChecked(false);
                    }
                }
                orderTimesAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecycler() {
        rvOrderTime.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        orderTimeAdapter = new TimeOrderAdapter(this);
        rvOrderTime.setAdapter(orderTimeAdapter);
        List<String> week = DateUtils.get3week();
        final List<String> date = DateUtils.get3date();
        orderTimeAdapter.setData(week, date);
        String today = date.get(0);
        //初始化
        getTime(today, userId);
        orderTimeAdapter.setListener(new TimeOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                String s = date.get(position);//日期
                //获得点击日期的数据
                getTime(s, userId);
            }
        });

    }

    @OnClick({R.id.tv_now, R.id.tv_confirm_select_time})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_now:
                intent.putExtra("time", "立即上门");
                intent.putExtra("id", -1);
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(d);
                intent.putExtra("date", format);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_confirm_select_time:
                String s = orderTimeAdapter.getTime();
                String date = orderTimeAdapter.getDate();
                String time = "";
                int intervalid = 0;
                for (int i = 0; i < appointmentList.size(); i++) {
                    RepairTimeBean.AppointmentListBean appointmentListBean = appointmentList.get(i);
                    if (appointmentListBean != null) {
                        boolean checked = appointmentListBean.isChecked();
                        if (checked) {
                            time = appointmentListBean.getDurationstr();
                            intervalid = appointmentListBean.getIntervalid();
                            break;
                        }
                    }
                }
                if (TextUtils.isEmpty(time)) {
                    showToast("请选择时间");
                    return;
                }
                //获得时间
                intent.putExtra("time", date + " " + time);
                intent.putExtra("date", date);
                intent.putExtra("id", intervalid);
                setResult(RESULT_OK, intent);
                finish();
                break;

        }
    }

    public void getTime(String date, String uid) {
        Observable observable = RetrofitUtils.getInstance().orderWaterTime(date, uid);
        showProgressDialog();
        observable.subscribe(new Subscriber<RepairTimeBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("gettime" + e.getMessage());
            }

            @Override
            public void onNext(RepairTimeBean bean) {
                if (bean.getErrCode() == 0) {
                    if (bean != null) {
                        appointmentList = bean.getAppointmentList();
                        orderTimesAdapter.setData(appointmentList);
                    }

                } else if (bean.getErrCode() == 2) {
                    reLogin(bean.getMsg());
                } else {
                    showToast(bean.getMsg());
                }
            }
        });
    }
}
