package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.office.SubscribeTimeAdapter;
import com.moe.wl.ui.home.bean.office.AppointmentListBean;
import com.moe.wl.ui.home.model.office.SubscribeTimeModel;
import com.moe.wl.ui.home.modelimpl.office.SubscribeTimeModelImpl;
import com.moe.wl.ui.home.presenter.office.SubscribeTimePresenter;
import com.moe.wl.ui.home.view.office.SubscribeTimeView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 预约时间
 */
public class SubscribeTimeActivity extends BaseActivity<SubscribeTimeModel, SubscribeTimeView, SubscribeTimePresenter> implements View.OnClickListener, SubscribeTimeView {


    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_week1)
    TextView tvWeek1;
    @BindView(R.id.tv_week2)
    TextView tvWeek2;
    @BindView(R.id.tv_week3)
    TextView tvWeek3;
    @BindView(R.id.tv_week4)
    TextView tvWeek4;
    @BindView(R.id.tv_week5)
    TextView tvWeek5;
    @BindView(R.id.tv_week6)
    TextView tvWeek6;
    @BindView(R.id.tv_week7)
    TextView tvWeek7;
    @BindView(R.id.tv_date1)
    TextView tvDate1;
    @BindView(R.id.tv_date2)
    TextView tvDate2;
    @BindView(R.id.tv_date3)
    TextView tvDate3;
    @BindView(R.id.tv_date4)
    TextView tvDate4;
    @BindView(R.id.tv_date5)
    TextView tvDate5;
    @BindView(R.id.tv_date6)
    TextView tvDate6;
    @BindView(R.id.tv_date7)
    TextView tvDate7;
    @BindView(R.id.gv_time)
    GridView gvTime;
    @BindView(R.id.ll_right)
    LinearLayout llRight;

    private String[] weeks;
    private String[] dates;

    private List<TextView> listDate;
    private List<TextView> listWeek;

    private String date;
    private String id;

    private List<AppointmentListBean> listTime;
    private SubscribeTimeAdapter adapter;

    private List<AppointmentListBean> selectTime;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_subscribe_time);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getStringExtra("id");
        listDate = new ArrayList<>();
        listDate.add(tvDate1);
        listDate.add(tvDate2);
        listDate.add(tvDate3);
        listDate.add(tvDate4);
        listDate.add(tvDate5);
        listDate.add(tvDate6);
        listDate.add(tvDate7);

        listWeek = new ArrayList<>();
        listWeek.add(tvWeek1);
        listWeek.add(tvWeek2);
        listWeek.add(tvWeek3);
        listWeek.add(tvWeek4);
        listWeek.add(tvWeek5);
        listWeek.add(tvWeek6);
        listWeek.add(tvWeek7);

        getTime();

        for (int i = 0; i < weeks.length; i++) {
            listWeek.get(i).setText(weeks[i]);
        }

        for (int i = 0; i < dates.length; i++) {
            listDate.get(i).setText(dates[i].substring(8, 10));
        }

        initTime();
        getPresenter().findAvailableEquipment(id, dates[0]);

    }

    public void initTime() {
        listTime = new ArrayList<>();
        selectTime= new ArrayList<>();
        adapter = new SubscribeTimeAdapter(this);
        adapter.setItemList(listTime);
        gvTime.setAdapter(adapter);
        adapter.setMyCallBack(new SubscribeTimeAdapter.MyCallBack() {
            @Override
            public void cb(int pos) {
                if (listTime.get(pos).isCheck()) {
                    listTime.get(pos).setCheck(false);
                } else {
                    listTime.get(pos).setCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public SubscribeTimePresenter createPresenter() {
        return new SubscribeTimePresenter();
    }

    @Override
    public SubscribeTimeModel createModel() {
        return new SubscribeTimeModelImpl();
    }

    @OnClick({R.id.ll_back,R.id.ll_right, R.id.tv_date1, R.id.tv_date2,
            R.id.tv_date3, R.id.tv_date4, R.id.tv_date5, R.id.tv_date6, R.id.tv_date7})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                selectTime.clear();
                for (int i = 0; i < listTime.size() ; i++) {
                    if (listTime.get(i).isCheck()){
                        selectTime.add(listTime.get(i));
                    }
                }
                if (selectTime.size()==0){
                    showToast("请先选择时间");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra("date",date);
                intent.putExtra("list", (Serializable) selectTime);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.tv_date1:
                clickDate(0);
                break;
            case R.id.tv_date2:
                clickDate(1);
                break;
            case R.id.tv_date3:
                clickDate(2);
                break;
            case R.id.tv_date4:
                clickDate(3);
                break;
            case R.id.tv_date5:
                clickDate(4);
                break;
            case R.id.tv_date6:
                clickDate(5);
                break;
            case R.id.tv_date7:
                clickDate(6);
                break;
        }
    }

    /**
     * 获取未来日期
     */
    public void clickDate(int pos) {
        for (int i = 0; i < listDate.size(); i++) {
            date = dates[pos];
            if (i == pos) {
                listDate.get(i).setBackgroundResource(R.mipmap.bg_btn_blue);
                listDate.get(i).setTextColor(getResources().getColor(R.color.white));
            } else {
                listDate.get(i).setBackgroundResource(0);
                listDate.get(i).setTextColor(getResources().getColor(R.color.font_blue));
            }
            getPresenter().findAvailableEquipment(id, date);
        }
    }

    /**
     * 获取未来日期
     */
    public void getTime() {
        weeks = new String[7];
        dates = new String[7];

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dates[0] = format.format(today);
        weeks[0] = getWeek(calendar);
        for (int i = 1; i < 7; i++) {
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            dates[i] = format.format(calendar.getTime());
            weeks[i] = getWeek(calendar);
        }
    }

    /**
     * 根据当前日期获得是星期几
     */
    public String getWeek(Calendar c) {
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            return "日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            return "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            return "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            return "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            return "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            return "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            return "六";
        }
        return "";
    }

    @Override
    public void setData(List<AppointmentListBean> appointmentList) {
        listTime.clear();
        listTime.addAll(appointmentList);
        adapter.notifyDataSetChanged();
    }

}
