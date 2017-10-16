package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.FindChargeOrderBean;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/11 0011.
 */

public class RechargeRecordAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<FindChargeOrderBean.ListBean> mList = new ArrayList<>();

    public RechargeRecordAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.recharge_record_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mList != null) {
            FindChargeOrderBean.ListBean listBean = mList.get(position);
            String monthName = listBean.getMonthName();
            List<FindChargeOrderBean.ListBean.MonthListBean> monthList = listBean.getMonthList();
            viewHolder.tvMonth.setText(monthName);//设置月份
            for (int i = 0; i < monthList.size(); i++) {
                View view = View.inflate(mContext, R.layout.recharge_item, null);
                TextView tvWeek = (TextView) view.findViewById(R.id.tv_week);
                TextView tvData = (TextView) view.findViewById(R.id.tv_data);
                TextView tvAddAmount = (TextView) view.findViewById(R.id.tv_add_amount);
                TextView tvRecharrgeAmount = (TextView) view.findViewById(R.id.tv_recharrge_amount);
                TextView tvPayWay = (TextView) view.findViewById(R.id.tv_pay_way);

                FindChargeOrderBean.ListBean.MonthListBean monthListBean = monthList.get(i);
                int money = monthListBean.getMoney();
                String weekday = monthListBean.getWeekday();
                String monthDay = monthListBean.getMonthDay();
                int paytype = monthListBean.getPaytype();
                if (paytype == 1) {
                    tvPayWay.setText("支付宝");
                } else if (paytype == 2) {
                    tvPayWay.setText("微信");
                }
                tvWeek.setText(weekday);
                tvData.setText(monthDay);
                tvAddAmount.setText("+" + money);
                tvRecharrgeAmount.setText("充值" + money + "元");
                viewHolder.llContainer.addView(view);
            }



        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<FindChargeOrderBean.ListBean> data) {
        this.mList = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.ll_container)
        LinearLayout llContainer;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}