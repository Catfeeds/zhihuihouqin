package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.office.AppointmentDateBean;

import java.text.SimpleDateFormat;

/**
 * 办公室设备列表
 */
public class AffirmDateAdapter extends MyBaseAdapter<AppointmentDateBean> {


    public AffirmDateAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office_affirm_date, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AppointmentDateBean bean=getItem(position);
        viewHolder.tv_date.setText(toDtae(bean.getDate()));
        AffirmTimeAdapter adapter=new AffirmTimeAdapter(getContext());
        adapter.setItemList(bean.getTimes());
        viewHolder.gv_time.setAdapter(adapter);
        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public NoSlidingGridView gv_time;
        public TextView tv_date;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.gv_time = (NoSlidingGridView) rootView.findViewById(R.id.gv_time);
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        }

    }

    /**
     * 获取未来日期
     */
    public String toDtae(String date) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd");
        String myDate = format1.format(date);
        return format2.format(myDate);
    }

}
