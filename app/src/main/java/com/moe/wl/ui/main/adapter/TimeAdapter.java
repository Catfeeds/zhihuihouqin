package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.JieYueTimeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Wang
 * 日期 2017/10/24.
 * 描述
 */

public class TimeAdapter extends BaseAdapter {
    private Context mContext;
    private List<JieYueTimeBean.PmListBean> mList;

    public TimeAdapter(Context context, List<JieYueTimeBean.PmListBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public JieYueTimeBean.PmListBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_select_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mList != null && mList.size() > 0) {
            viewHolder.setData(mList.get(position), position);
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(JieYueTimeBean.PmListBean timelistBean, int position) {
            if (timelistBean != null) {
                time.setText(timelistBean.getTimeperiod());

            }
        }
    }
}
