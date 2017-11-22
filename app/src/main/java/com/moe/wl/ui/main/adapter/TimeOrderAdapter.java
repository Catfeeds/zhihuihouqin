package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/18 0018.
 */

public class TimeOrderAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private int selectPosition = 0;
    private List<String> week = new ArrayList<>();
    private List<String> date = new ArrayList<>();

    public TimeOrderAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public String getTime() {
        String s1 = date.get(selectPosition);
        String[] split = s1.split("-");
        String s = split[1]+"/"+split[2];
        return s+"/"+week.get(selectPosition);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_times_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (week != null) {
            viewHolder.setData(week.get(position), date.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return week.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_workday)
        TextView tvWorkday;
        @BindView(R.id.tv_day)
        TextView tvDay;
        @BindView(R.id.ll_time)
        LinearLayout llTime;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onItemClickListener(selectPosition);
                    }
                }
            });
        }
        public void setData(String s, String s1, int position) {
            mPosition=position;
            //处理一下日期的格式
            String[] split = s1.split("-");
            tvWorkday.setText(s);
            tvDay.setText(split[1]+"/"+split[2]);
            if (selectPosition == position) {
                llTime.setBackgroundColor(Color.parseColor("#00CCFF"));
                tvWorkday.setTextColor(Color.WHITE);
                tvDay.setTextColor(Color.WHITE);
            } else {
                llTime.setBackgroundColor(Color.WHITE);
                tvWorkday.setTextColor(Color.parseColor("#333333"));
                tvDay.setTextColor(Color.parseColor("#333333"));

            }
        }
    }

    public void setData(List<String> week, List<String> date) {
        this.week = week;
        this.date = date;
        notifyDataSetChanged();
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }
}
