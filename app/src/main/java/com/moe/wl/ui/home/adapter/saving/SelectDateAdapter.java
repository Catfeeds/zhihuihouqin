package com.moe.wl.ui.home.adapter.saving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.saving.DateBean;

/**
 *
 */
public class SelectDateAdapter extends MyBaseAdapter<DateBean> {

    public SelectDateAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_calendar, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DateBean bean=getItem(position);
        viewHolder.text.setText(bean.getDate());
        if (bean.isCheck()) {
            viewHolder.text.setTextColor(0xff9299a1);
        } else {
            viewHolder.text.setTextColor(0xff444444);
        }

        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public TextView text;
        public TextView chinaText;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.text = (TextView) rootView.findViewById(R.id.text);
            this.chinaText = (TextView) rootView.findViewById(R.id.chinaText);
        }

    }
}
