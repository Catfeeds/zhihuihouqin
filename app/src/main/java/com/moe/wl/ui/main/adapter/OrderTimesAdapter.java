package com.moe.wl.ui.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.ui.home.bean.RepairTimeBean;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public class OrderTimesAdapter extends BaseAdapter {
    private List<RepairTimeBean.AppointmentListBean> data = new ArrayList();

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
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
        if (data != null && data.size() > 0){
            RepairTimeBean.AppointmentListBean appointmentListBean = data.get(position);
            viewHolder.setData(appointmentListBean, position);
        }
        return convertView;
    }

    public void setData(List<RepairTimeBean.AppointmentListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.tv_barber_grid_item)
        TextView tvBarberGridItem;
        @BindView(R.id.ll_container)
        LinearLayout llContainer;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(RepairTimeBean.AppointmentListBean bean, final int position) {
            this.mPosition = position;
            tvBarberGridItem.setText(bean.getDurationstr());
            int status = bean.getStatus();
            if(status==1){//可以使使用的
                if(bean.isChecked()){
                    tvBarberGridItem.setBackgroundColor(Color.parseColor("#00CCFF"));
                    tvBarberGridItem.setTextColor(Color.WHITE);
                }else{
                    tvBarberGridItem.setBackgroundColor(Color.WHITE);
                    tvBarberGridItem.setTextColor(Color.parseColor("#333333"));
                }
                llContainer.setEnabled(true);
            }else{
                tvBarberGridItem.setBackgroundColor(Color.parseColor("#BBBBBB"));
                tvBarberGridItem.setTextColor(Color.parseColor("#333333"));
                llContainer.setEnabled(false);
            }
            llContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onTimeSelectListener(position);
                    }
                }
            });
        }
    }
    private OnTimeSelectListener listener;

    public void setListener(OnTimeSelectListener listener) {
        this.listener = listener;
    }

    public interface OnTimeSelectListener{
        void onTimeSelectListener(int position);
    }
}
