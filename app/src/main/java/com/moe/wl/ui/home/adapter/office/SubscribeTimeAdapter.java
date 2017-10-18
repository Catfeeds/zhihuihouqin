package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.bean.office.SubscribeTimeResponse;
import com.moe.wl.ui.main.adapter.MyBaseAdapter;

/**
 * 办公室列表
 */
public class SubscribeTimeAdapter extends MyBaseAdapter<SubscribeTimeResponse.AppointmentListBean> {

    public SubscribeTimeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_subscribe_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SubscribeTimeResponse.AppointmentListBean bean=getItem(position);
        viewHolder.tv_time.setText(bean.getDurationstr());

        return convertView;
    }

    public class ViewHolder{
        public View rootView;
        public TextView tv_time;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
