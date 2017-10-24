package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.JieYueTimeBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class TimeSelectAdapter extends BaseAdapter {
    private Context mContext;
    private JieYueTimeBean data;
    private int type;

    public TimeSelectAdapter(Context context, JieYueTimeBean data, int type) {
        this.mContext = context;
        this.data = data;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (type == 1) {
            return data.getAmList().size();
        } else {
            return data.getPmList().size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (type == 1) {
            return data.getAmList().get(position);
        } else {
            return data.getPmList().get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_select_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (type == 1) {
            viewHolder.time.setText(data.getAmList().get(position).getTimeperiod());
        } else {
            viewHolder.time.setText(data.getPmList().get(position).getTimeperiod());
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
