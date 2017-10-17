package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.activity.saving.calendar.CaledarAdapter;
import com.moe.wl.ui.home.activity.saving.calendar.CalendarBean;
import com.moe.wl.ui.home.activity.saving.calendar.CalendarDateView;
import com.moe.wl.ui.home.activity.saving.calendar.CalendarUtil;
import com.moe.wl.ui.home.activity.saving.calendar.CalendarView;
import com.moe.wl.ui.home.model.saving.ComparisonModel;
import com.moe.wl.ui.home.modelimpl.saving.ComparisonModelImpl;
import com.moe.wl.ui.home.presenter.saving.ComparisonPresenter;
import com.moe.wl.ui.home.view.saving.ComparisonView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 日历
 */
public class CalendarActivity extends BaseActivity<ComparisonModel, ComparisonView, ComparisonPresenter> implements View.OnClickListener {


    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.calendarDateView)
    CalendarDateView calendarDateView;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String myDate;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

        calendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_calendar, null);
                }

                TextView chinaText = (TextView) convertView.findViewById(R.id.chinaText);
                TextView text = (TextView) convertView.findViewById(R.id.text);

                text.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    text.setTextColor(0xff9299a1);
                } else {
                    text.setTextColor(0xff444444);
                }
                chinaText.setText(bean.chinaDay);

                return convertView;
            }
        });

        calendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {
                myDate=bean.year + "-" + bean.moth + "-" + bean.day;
                tvTitle.setText(myDate);
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        myDate=data[0] + "-" + data[1] + "-" + data[2];
        tvTitle.setText(myDate);

    }

    @Override
    public ComparisonPresenter createPresenter() {
        return new ComparisonPresenter();
    }

    @Override
    public ComparisonModel createModel() {
        return new ComparisonModelImpl();
    }


    @OnClick({R.id.ll_back, R.id.ll_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent=new Intent();
                intent.putExtra("date",myDate);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

}
