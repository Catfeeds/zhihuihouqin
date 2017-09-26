package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.ui.main.bean.ExpertDetailBean;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */
public class ExpertSelectTimeAdapter extends BaseAdapter {

    private Context context;
    private List<ExpertDetailBean.SchedulesEntity> data;
    private OnClickListener listener;

    public ExpertSelectTimeAdapter(Context context, List<ExpertDetailBean.SchedulesEntity> data, OnClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expert_time, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(position);
        return convertView;
    }

    private int mPosition = -1;

    class ViewHolder {
        @BindView(R.id.text)
        TextView text;
        int position;

        ViewHolder(View view) {
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
                    listener.onClick(data.get(position).getId());
                }
            });
        }

        void setData(int position) {
            this.position = position;
            text.setText(data.get(position).getStarttime() + "-" + data.get(position).getEndtime());
            if (data.get(position).getStatus() == 0) {
                if (mPosition == position) {
                    text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_blue_fill));
                    text.setTextColor(context.getResources().getColor(R.color.white));
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

    public interface OnClickListener {
        void onClick(int id);
    }
}
