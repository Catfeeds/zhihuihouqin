package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.wheelView.adapter.NumericWheelAdapter;

import java.util.Calendar;

import com.moe.wl.R;

import com.moe.wl.ui.main.wheelView.OnWheelScrollListener;
import com.moe.wl.ui.main.wheelView.WheelView;

import mvp.cn.util.ScreenUtils;

public class BottomTimeDialog extends Dialog implements View.OnClickListener {

    private WheelView year;
    private WheelView month;
    private WheelView day;
    private WheelView time;
    private WheelView min;
    private WheelView sec;
    private int mYear=1996;
    private int mMonth=0;
    private int mDay=1;
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

    public BottomTimeDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(ct, R.layout.getrevice_time_dialog, null);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        year = (WheelView) view.findViewById(R.id.year);
        month = (WheelView) view.findViewById(R.id.month);
        day = (WheelView) view.findViewById(R.id.day);
        time = (WheelView) view.findViewById(R.id.time);
        min = (WheelView) view.findViewById(R.id.min);
        sec = (WheelView) view.findViewById(R.id.sec);

        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        Calendar c = Calendar.getInstance();
        int norYear = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        curYear = norYear;
        curMonth = month+1;
        curDate = day;
        curHour = 00;
        curMin = 00;

        NumericWheelAdapter numericWheelAdapter1=new NumericWheelAdapter(ct,norYear,norYear+20 );
        numericWheelAdapter1.setLabel("年");
        year.setViewAdapter(numericWheelAdapter1);
        year.setCyclic(true);//是否可循环滑动
        year.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter2=new NumericWheelAdapter(ct,1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        this.month.setViewAdapter(numericWheelAdapter2);
        this.month.setCyclic(true);
        this.month.addScrollingListener(scrollListener);

        this.day = (WheelView) view.findViewById(R.id.day);
        initDay(curYear, curMonth);
        this.day.setCyclic(true);

        NumericWheelAdapter numericWheelAdapter3=new NumericWheelAdapter(ct,0, 23, "%02d");
        numericWheelAdapter3.setLabel("时");
        min.setViewAdapter(numericWheelAdapter3);
        min.setCyclic(true);
        min.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter4=new NumericWheelAdapter(ct,0, 59, "%02d");
        numericWheelAdapter4.setLabel("分");
        sec.setViewAdapter(numericWheelAdapter4);
        sec.setCyclic(true);
        sec.addScrollingListener(scrollListener);

        year.setVisibleItems(7);//设置显示行数
        this.month.setVisibleItems(7);
        this.day.setVisibleItems(7);
//		time.setVisibleItems(7);
        min.setVisibleItems(7);
        sec.setVisibleItems(7);

        year.setCurrentItem(curYear -2017);
        this.month.setCurrentItem(curMonth - 1);
        this.day.setCurrentItem(curDate - 1);
        min.setCurrentItem(curHour);
        sec.setCurrentItem(curMin);

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

    private int n_month;
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            //年
            curYear = year.getCurrentItem() + 2017;//月
            curMonth = month.getCurrentItem() + 1;
            curDate = day.getCurrentItem() + 1;
            curHour = min.getCurrentItem() ;
            curMin = sec.getCurrentItem() ;

            //initDay(n_year, n_month);
            initDay(curYear, curMonth);

        }
    };
    /**
     *
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }
    /**
     */
    private void initDay(int arg1, int arg2) {
        NumericWheelAdapter numericWheelAdapter=new NumericWheelAdapter(ct,1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel("日");
        day.setViewAdapter(numericWheelAdapter);
    }
    private OnConfirmClickListener listener2;

    public void setListener2(OnConfirmClickListener listener2) {
        this.listener2 = listener2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.tv_cancel:
               dismiss();
            break;
            case R.id.tv_confirm:
                LogUtils.i(curYear+" "+curMonth+" "+curDate+" "+curHour+" "+curMin);
                if(listener2!=null){
                    listener2.onConfirmClickListener(curYear,curMonth,curDate,curHour,curMin);
                }else{
                    LogUtils.i("listerner2为空");
                }
                dismiss();
            break;
        }
    }

    public interface OnConfirmClickListener{
        void onConfirmClickListener(int i1, int i2, int i3, int i4, int i5);
    }
}
