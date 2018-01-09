package com.moe.wl.ui.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/11/24 0024.
 */

public class TimeAdapter extends BaseAdapter {
    private OrderWaterTimeBean timeBean;
    private int type;

    @Override
    public int getCount() {
        if (type == 0) {
            if (timeBean != null) {
                return timeBean.getAmList().size();
            } else {
                return 0;
            }

        } else {
            if (timeBean != null) {
                return timeBean.getPmList().size();
            } else {
                return 0;
            }
        }

    }

    @Override
    public Object getItem(int position) {
        if (timeBean != null) {
            if (type == 0)
                return timeBean.getAmList().get(position);
            else
                return timeBean.getPmList().get(position);
        } else {
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_select_time, null);
//            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (timeBean != null) {
            holder.setData(timeBean, position);

        }

        return convertView;
    }

    public void setData(OrderWaterTimeBean bean, int i) {
        this.timeBean = bean;
        this.type = i;
        notifyDataSetChanged();
    }

    private int selectPosition=-1;

    public void clearTime() {
        selectPosition = -1;
        notifyDataSetChanged();
    }

     class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        private int mPosition;
         ViewHolder(View view) {
             ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    if (listener != null) {
                        listener.clickListener(selectPosition);
                    }
                    notifyDataSetChanged();
                }
            });
        }

        public void setData(OrderWaterTimeBean timeBean, int position) {
            this.mPosition = position;
            if (type == 0) {
                time.setText(timeBean.getAmList().get(position).getTimeStr());
            } else {
                time.setText(timeBean.getPmList().get(position).getTimeStr());
            }
            if (selectPosition == position) {
                time.setBackgroundColor(Color.parseColor("#2EA7E0"));
                time.setTextColor(Color.WHITE);
            } else {
                time.setBackgroundColor(Color.WHITE);
                time.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

    private OnTimeItemClickListener listener;

    public OnTimeItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnTimeItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnTimeItemClickListener {
        void clickListener(int position);
    }
}
