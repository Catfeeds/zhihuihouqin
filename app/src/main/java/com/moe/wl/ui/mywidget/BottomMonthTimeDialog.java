package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.wheelView.OnWheelScrollListener;
import com.moe.wl.ui.main.wheelView.WheelView;
import com.moe.wl.ui.main.wheelView.adapter.NumericWheelAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mvp.cn.util.ScreenUtils;

public class BottomMonthTimeDialog extends Dialog implements View.OnClickListener {

    private WheelView year;
    private WheelView month;
    private TextView tv_title;

    private int mYear = 1996;
    private int mMonth = 0;
    private int mDay = 1;
    private Context ct;
    private int n_year;
    private int n_day;
    private int n_min;
    private int n_sec;
    private int curYear;
    private int curMonth;
    private int curDate;
    private int curHour;
    private int curMin;
    private int preyear=50;

    private String title;
    private NumericWheelAdapter numericWheelAdapter;
    private List<Integer> yearList;

    public BottomMonthTimeDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    public BottomMonthTimeDialog(Context context, String title, int theme) {
        super(context, theme);
        this.ct = context;
        this.title = title;
        initView();
    }

    private void initView() {
        yearList = new ArrayList<>();
        View view = View.inflate(ct, R.layout.getrevice_month_time_dialog, null);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        year = (WheelView) view.findViewById(R.id.year);
        month = (WheelView) view.findViewById(R.id.month);

        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        Calendar c = Calendar.getInstance();
        int norYear = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        curYear = norYear;
        curMonth = month + 1;
        curDate = day;
        curHour = hour;
        curMin = minute;
        //存放年份
        for (int i = 0; i < preyear; i++) {
            yearList.add(curYear-preyear+i);
        }

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        }


        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(ct, norYear-50, norYear);
        numericWheelAdapter1.setLabel("年");
        year.setViewAdapter(numericWheelAdapter1);
        year.setCyclic(true);//是否可循环滑动
        year.addScrollingListener(scrollListener);
        year.setVisibleItems(7);//设置显示行数
        year.setCurrentItem(preyear);

        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(ct, 1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        this.month.setViewAdapter(numericWheelAdapter2);
        this.month.setCyclic(true);
        this.month.addScrollingListener(scrollListener);
        this.month.setVisibleItems(7);

        int width = ScreenUtils.getScreenWidth(ct);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, -2);
        view.setLayoutParams(params);
        setContentView(view, params);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(p);
        getWindow().setWindowAnimations(R.style.AnimationDialog);

    }

    /**
     * 是否显示年滑轮
     */
    public void showYear(boolean bln) {
        if (year != null) {
            if (bln) {
                year.setVisibility(View.VISIBLE);
            } else {
                year.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示月滑轮
     */
    public void showMonth(boolean bln) {
        if (month != null) {
            if (bln) {
                month.setVisibility(View.VISIBLE);
            } else {
                month.setVisibility(View.GONE);
            }
        }
    }

    private int n_month;
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            //年
            int index = year.getCurrentItem();//月
            for (int i = 0; i < preyear; i++) {
                if(index==i){
                    curYear=yearList.get(i);
                    break;
                }
            }
            curMonth = month.getCurrentItem() + 1;

        }
    };


    private OnConfirmClickListener listener2;

    public void setListener2(OnConfirmClickListener listener2) {
        this.listener2 = listener2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_confirm:
                LogUtils.i(curYear + " " + curMonth + " " + curDate + " " + curHour + " " + curMin);
                String hour = "";
                String min = "";
                if (listener2 != null) {
                    if (curHour < 10) {
                        hour = "0" + curHour;
                    } else {
                        hour = curHour + "";
                    }
                    if (curMin < 10) {
                        min = "0" + curMin;
                    } else {
                        min = curMin + "";
                    }

                    listener2.onConfirmClickListener(curYear, curMonth, curDate, hour, min);
                } else {
                    LogUtils.i("listerner2为空");
                }
                dismiss();
                break;
        }
    }

    public interface OnConfirmClickListener {
        void onConfirmClickListener(int i1, int i2, int i3, String i4, String i5);
    }
}
