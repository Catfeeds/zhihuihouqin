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

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */
public class ExpertSelectTimeDayAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ExpertDetailBean.SchedulesEntity> data;
    private OnClickListener listener;

    public ExpertSelectTimeDayAdapter(Context context, List<ExpertDetailBean.SchedulesEntity> data, OnClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expert_day_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        ViewHolder holder = (ViewHolder) holder1;
        holder.setData(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    private int mPosition = 0;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;

        private int position;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition = position;
                    notifyDataSetChanged();
                    if (listener != null)
                        listener.onClick(position);
                }
            });
        }

        void setData(int position) {
            this.position = position;
            if (data.get(position).getScheduleDate() == null || "".equals(data.get(position).getScheduleDate())) {
                text.setText("空");
            } else if (data.get(position).getScheduleDate().length() > 10) {
                text.setText(data.get(position).getScheduleDate().substring(0, 10));
            } else {
                text.setText(data.get(position).getScheduleDate());
            }
            if (mPosition == position) {
                text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_blue_fill));
                text.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_button_white));
                text.setTextColor(context.getResources().getColor(R.color.tv_black));
            }
        }
    }
}
