package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.bean.SelectTimeBean;

/**
 * Created by 我的电脑 on 2017/9/27 0027.
 */

public class OrderWaterSelectTimeAdapter extends BaseAdapter{
    private OrderWaterTimeBean data;
    private LayoutInflater inflater;
    private int type;

    public OrderWaterSelectTimeAdapter(Context context, OrderWaterTimeBean data, int type) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (type == 0) {
            return data.getAmList().size();
        } else
            return data.getPmList().size();
    }

    @Override
    public Object getItem(int position) {
        if (type == 0)
            return data.getAmList().get(position);
        else
            return data.getPmList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_select_time, null);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (type == 0) {
            holder.time.setText(data.getAmList().get(position).getTimeStr());
        } else {
            holder.time.setText(data.getPmList().get(position).getTimeStr());
        }

        return convertView;
    }

    class ViewHolder {
        TextView time;
    }
}
