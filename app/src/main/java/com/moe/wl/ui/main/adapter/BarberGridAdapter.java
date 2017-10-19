package com.moe.wl.ui.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.PreOrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/8/22 0022.
 */

public class BarberGridAdapter extends BaseAdapter {
    private List<PreOrderBean.TimelistBean.SchedulelistBean> data = new ArrayList<>();

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data != null && data.size() > 0)
            viewHolder.setData(data.get(position), position);
        return convertView;
    }


    public void setData(List<PreOrderBean.TimelistBean.SchedulelistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private int selectPosition = 0;

    class ViewHolder {
        @BindView(R.id.tv_barber_grid_item)
        TextView tvBarberGridItem;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            tvBarberGridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.onItemClickListener(selectPosition);
                    }
                }
            });
        }

        public void setData(PreOrderBean.TimelistBean.SchedulelistBean schedulelistBean, int position) {
           this.mPosition=position;
            String starttime = schedulelistBean.getStarttime();
            String endtime = schedulelistBean.getEndtime();
            tvBarberGridItem.setText(starttime + "-" + endtime);
            if (selectPosition == position) {
                tvBarberGridItem.setTextColor(Color.parseColor("#00CCFF"));
            } else {
                tvBarberGridItem.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
