package com.moe.wl.ui.home.adapter.saving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;

/**
 * 资讯列表
 */
public class InformationAdapter extends MyBaseAdapter<String> {

    public InformationAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_information, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public ImageView iv_icon;
        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_addreass;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_icon = (ImageView) rootView.findViewById(R.id.iv_icon);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_addreass = (TextView) rootView.findViewById(R.id.tv_addreass);
        }

    }
}
