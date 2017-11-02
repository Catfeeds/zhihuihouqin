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
import com.moe.wl.ui.main.bean.GetChargeOrderBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/11/2 0002.
 */

public class RechargeOrderRecordAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<GetChargeOrderBean.ListBean> mList=new ArrayList<>();
    private int type=1;

    public RechargeOrderRecordAdapter(Context mContext) {
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
            GetChargeOrderBean.ListBean listBean = mList.get(position);
            String monthName = listBean.getMonthName();
            double  money = listBean.getMoney();
            if (position == 0) {
                viewHolder.tvMonth.setText(monthName);
            } else {//不是第一个条
                GetChargeOrderBean.ListBean last = mList.get(position - 1);
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
            viewHolder.tvState.setVisibility(View.VISIBLE);
            if(type==1){//正在进行
                viewHolder.tvState.setText("充值中");
            }else if(type==0){
                viewHolder.tvState.setText("交易关闭");
            }

        }
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }else {
            return 0;
        }
    }

    public void setData(int type, List<GetChargeOrderBean.ListBean> data) {
        this.type=type;
        this.mList = data;
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
        @BindView(R.id.tv_state)
        TextView tvState;
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
