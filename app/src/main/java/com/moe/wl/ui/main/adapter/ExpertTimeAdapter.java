package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.ExpertDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.cn.util.ToastUtil;

/**
 * Created by 我的电脑 on 2017/10/30 0030.
 */

public class ExpertTimeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ExpertDetailBean.SchedulesEntity.SchedulelistEntity> data;
    private OnClickListener listener;

    public ExpertTimeAdapter(Context context, List<ExpertDetailBean.SchedulesEntity.SchedulelistEntity> data, OnClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expert_day_time, parent, false);
        return new ViewHolder(view);
        //item_expert_time
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(data, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnClickListener {
        void onClick(int id, String time);
    }

    private int mPosition = -1;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        private List<ExpertDetailBean.SchedulesEntity.SchedulelistEntity> data;
        private int position;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.get(position).getStatus() == 1) {
                        ToastUtil.showToast(context, "该时段已被预约！");
                        return;
                    }
                    mPosition = position;
                    notifyDataSetChanged();
                    listener.onClick(data.get(position).getId(), data.get(position).getStarttime() + "-" + data.get(position).getEndtime());
                }
            });
        }

        public void setData(List<ExpertDetailBean.SchedulesEntity.SchedulelistEntity> data, int position) {
            this.data = data;
            this.position = position;
            if (data.get(position).getStarttime() == null || "".equals(data.get(position).getStarttime())) {
                text.setText("空");
            } else {
                text.setText(data.get(position).getStarttime() + "-" + data.get(position).getEndtime());
            }
            if (data.get(position).getStatus() == 0) {
                if (mPosition == position) {
                    text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_button_white));
                    text.setTextColor(context.getResources().getColor(R.color.blue));
                } else {
                    text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_button_white));
                    text.setTextColor(context.getResources().getColor(R.color.tv_black));
                }
            } else {
                text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_gray_line));
                text.setTextColor(context.getResources().getColor(R.color.gray_light));
            }
        }
    }
}
