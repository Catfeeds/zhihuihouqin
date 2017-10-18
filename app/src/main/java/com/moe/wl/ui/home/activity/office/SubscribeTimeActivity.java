package com.moe.wl.ui.home.activity.office;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.office.SubscribeInfoModel;
import com.moe.wl.ui.home.modelimpl.office.SubscribeInfoModelImpl;
import com.moe.wl.ui.home.presenter.office.SubscribeInfoPresenter;
import com.moe.wl.ui.home.view.office.SubscribeInfoView;

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
public class SubscribeTimeActivity extends BaseActivity<SubscribeInfoModel, SubscribeInfoView, SubscribeInfoPresenter> implements View.OnClickListener, SubscribeInfoView {


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
    @BindView(R.id.tv_time1)
    TextView tvTime1;
    @BindView(R.id.tv_time2)
    TextView tvTime2;
    @BindView(R.id.tv_time3)
    TextView tvTime3;
    @BindView(R.id.tv_time4)
    TextView tvTime4;
    @BindView(R.id.tv_time5)
    TextView tvTime5;
    @BindView(R.id.tv_time6)
    TextView tvTime6;
    @BindView(R.id.tv_time7)
    TextView tvTime7;
    @BindView(R.id.tv_time8)
    TextView tvTime8;

    private String[] weeks;
    private String[] dates;

    private List<TextView> listDate;
    private List<TextView> listWeek;

    private String time;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_subscribe_time);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        listDate=new ArrayList<>();
        listDate.add(tvDate1);
        listDate.add(tvDate2);
        listDate.add(tvDate3);
        listDate.add(tvDate4);
        listDate.add(tvDate5);
        listDate.add(tvDate6);
        listDate.add(tvDate7);

        listWeek=new ArrayList<>();
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
            listDate.get(i).setText(dates[i].substring(8,10));
        }

    }

    @Override
    public SubscribeInfoPresenter createPresenter() {
        return new SubscribeInfoPresenter();
    }

    @Override
    public SubscribeInfoModel createModel() {
        return new SubscribeInfoModelImpl();
    }

    @OnClick({R.id.ll_back, R.id.tv_date1, R.id.tv_date2,
            R.id.tv_date3, R.id.tv_date4, R.id.tv_date5, R.id.tv_date6, R.id.tv_date7,R.id.tv_time1, R.id.tv_time2,
            R.id.tv_time3, R.id.tv_time4, R.id.tv_time5, R.id.tv_time6, R.id.tv_time7,R.id.tv_time8})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
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
            case R.id.tv_time1:
                time="8:00~9:00";
                break;
            case R.id.tv_time2:
                time="9:00~10:00";
                break;
            case R.id.tv_time3:
                time="10:00~11:00";
                break;
            case R.id.tv_time4:
                time="11:00~12:00";
                break;
            case R.id.tv_time5:
                time="14:00~15:00";
                break;
            case R.id.tv_time6:
                time="15:00~16:00";
                break;
            case R.id.tv_time7:
                time="16:00~17:00";
                break;
            case R.id.tv_time8:
                time="17:00~18:00";
                break;
        }
    }

    /**
     * 获取未来日期
     */
    public void clickDate(int pos) {
        for (int i = 0; i < listDate.size(); i++) {
            if (i==pos){
                listDate.get(i).setBackgroundColor(getResources().getColor(R.color.red));
                listDate.get(i).setTextColor(getResources().getColor(R.color.white));
            }else{
                listDate.get(i).setBackgroundColor(0);
                listDate.get(i).setTextColor(getResources().getColor(R.color.font_black));
            }
        }
    }

    /**
     * 获取未来日期
     */
    public void getTime() {
        weeks=new String[7];
        dates=new String[7];

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dates[0]=format.format(today);
        weeks[0]=getWeek(calendar);
        for (int i = 1; i < 7; i++) {
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            dates[i]=format.format(calendar.getTime());
            weeks[i]=getWeek(calendar);
        }
    }

    /**
     * 根据当前日期获得是星期几
     */
    public String getWeek(Calendar c) {
        if (c.get(Calendar.DAY_OF_WEEK)==1) {
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
    public void submit() {

    }

}
