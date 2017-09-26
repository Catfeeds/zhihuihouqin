package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class OrderTimeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private int selectPosition = 0;
    private List<String> weeks = new ArrayList<>();
    private List<String> dates = new ArrayList<>();
    private String s2;

    public int getmPosition() {
        return selectPosition;
    }

    public OrderTimeAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> week, List<String> date) {
        this.weeks = week;
        this.dates = date;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (weeks != null) {
            viewHolder.setData(weeks.get(position), dates.get(position), position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getTime() {
        String s = s2.substring(2, s2.length());
        return s+"/"+weeks.get(selectPosition);
    }

    @Override
    public int getItemCount() {
        if (weeks != null) {
            return weeks.size();
        }
        return 0;
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
                }
            });
        }

        public void setData(String s1, String s, int position) {
            this.mPosition = position;
            tvWorkday.setText(s1);
            s2 = s.replaceAll("-", "/");
            tvDay.setText(s2.substring(5, s2.length()));
            if (selectPosition == position) {
                llTime.setBackgroundColor(Color.parseColor("#cccccc"));
                tvWorkday.setTextColor(Color.parseColor("#F95759"));
                tvDay.setTextColor(Color.parseColor("#F95759"));
            } else {
                llTime.setBackgroundColor(Color.WHITE);
                tvWorkday.setTextColor(Color.parseColor("#333333"));
                tvDay.setTextColor(Color.parseColor("#333333"));
            }
        }

    }

}
