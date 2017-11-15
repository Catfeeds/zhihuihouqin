package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.activity.office.OfficeDetailsActivity;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.office.OfficeListResponse;

/**
 * Created by 我的电脑 on 2017/11/6 0006.
 */

public class ServiceItemAdapter extends MyBaseAdapter {
    public ServiceItemAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        final OfficeListResponse.ListBean bean=getItem(position);
//        viewHolder.tv_name.setText(bean.getName());
//        viewHolder.tv_saturation.setText(bean.getCapacity()+"人");
//        viewHolder.tv_location.setText(bean.getAddress());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OfficeDetailsActivity.class);
//                intent.putExtra("id",bean.getId());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        public View rootView;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_saturation;
        public TextView tv_time;
        public TextView tv_number;
        public TextView tv_location;
        public TextView tv_subscribe;
        private  TextView tv_area;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_icon = (ImageView) rootView.findViewById(R.id.iv_icon);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_saturation = (TextView) rootView.findViewById(R.id.tv_saturation);
            tv_area = (TextView) rootView.findViewById(R.id.tv_area);
            this.tv_location = (TextView) rootView.findViewById(R.id.tv_location);
        }

    }
}
