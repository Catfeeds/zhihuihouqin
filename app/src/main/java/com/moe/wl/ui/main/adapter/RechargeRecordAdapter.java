package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.FindChargeOrderBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/11 0011.
 */

public class RechargeRecordAdapter extends RecyclerView.Adapter {
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.tv_add_amount)
    TextView tvAddAmount;
    @BindView(R.id.tv_recharge_amount)
    TextView tvRechargeAmount;
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
            String createtime = listBean.getCreatetime();//2017-10-20 11:11:11
            String monthName = listBean.getMonthName();
            double  money = listBean.getMoney();
            if (position == 0) {
                viewHolder.tvMonth.setText(monthName);
            } else {//不是第一个条
                FindChargeOrderBean.ListBean last = mList.get(position - 1);
                String lastMonthName = last.getMonthName();
                if (lastMonthName.equals(monthName)) {
                    viewHolder.tvMonth.setVisibility(View.GONE);
                } else {
                    viewHolder.tvMonth.setVisibility(View.VISIBLE);
                    viewHolder.tvMonth.setText(monthName);
                }
            }
            viewHolder.tvWeek.setText(listBean.getWeekday());
            viewHolder.tvData.setText(listBean.getMonthDay());//设置日期
            int paytype = listBean.getPaytype();
            if (paytype == 1) {
                viewHolder.tvPayWay.setText("支付宝");
            } else if (paytype == 2) {
                viewHolder.tvPayWay.setText("微信");
            }
            viewHolder.tvAddAmount.setText("+" + money);
            DecimalFormat df = new DecimalFormat("###.00");
            viewHolder.tvRechargeAmount.setText("充值" +df.format(money)+ "元");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<FindChargeOrderBean.ListBean> data) {
        this.mList = data;
        LogUtils.i("adpter获取到了数据" + data.size());
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_month)
        TextView tvMonth;
        @BindView(R.id.ll_container)
        LinearLayout llContainer;
        @BindView(R.id.tv_week)
        TextView tvWeek;
        @BindView(R.id.tv_data)
        TextView tvData;
        @BindView(R.id.tv_pay_way)
        TextView tvPayWay;
        @BindView(R.id.tv_add_amount)
        TextView tvAddAmount;
        @BindView(R.id.tv_recharge_amount)
        TextView tvRechargeAmount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
