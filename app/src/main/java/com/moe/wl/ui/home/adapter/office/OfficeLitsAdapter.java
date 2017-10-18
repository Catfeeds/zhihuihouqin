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
import com.moe.wl.ui.home.bean.office.OfficeListResponse;
import com.moe.wl.ui.main.adapter.MyBaseAdapter;

/**
 * 办公室列表
 */
public class OfficeLitsAdapter extends MyBaseAdapter<OfficeListResponse.ListBean> {

    public OfficeLitsAdapter(Context context) {
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

        final OfficeListResponse.ListBean bean=getItem(position);
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.tv_saturation.setText(bean.getCapacity()+"人");
        if ("1".equals(bean.getTimeserving())){
            viewHolder.tv_time.setText("上午");
        }else if ("2".equals(bean.getTimeserving())){
            viewHolder.tv_time.setText("下午午");
        }else if ("3".equals(bean.getTimeserving())){
            viewHolder.tv_time.setText("全天");
        }else{
            viewHolder.tv_time.setText("全天");
        }
        viewHolder.tv_number.setText(bean.getUsenumber());
        viewHolder.tv_location.setText(bean.getAddress());
        if ("1".equals(bean.getStatus())){
            viewHolder.tv_subscribe.setText("可预订");
            viewHolder.tv_subscribe.setBackgroundResource(R.mipmap.bg_btn_blue);
        }else{
            viewHolder.tv_subscribe.setText("使用中");
            viewHolder.tv_subscribe.setBackgroundResource(R.mipmap.bg_btn_red);
        }
        viewHolder.tv_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OfficeDetailsActivity.class);
                intent.putExtra("id",bean.getId());
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

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_icon = (ImageView) rootView.findViewById(R.id.iv_icon);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_saturation = (TextView) rootView.findViewById(R.id.tv_saturation);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_number = (TextView) rootView.findViewById(R.id.tv_number);
            this.tv_location = (TextView) rootView.findViewById(R.id.tv_location);
            this.tv_subscribe = (TextView) rootView.findViewById(R.id.tv_subscribe);
        }

    }
}
