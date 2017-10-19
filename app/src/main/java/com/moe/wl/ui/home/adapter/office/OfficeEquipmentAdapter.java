package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.office.OfficeDetailsResponse;

/**
 * 办公室设备列表
 */
public class OfficeEquipmentAdapter extends MyBaseAdapter<OfficeDetailsResponse.RoomDetailBean.EnameListBean> {

    public OfficeEquipmentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office_equipment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        OfficeDetailsResponse.RoomDetailBean.EnameListBean bean=getItem(position);

        viewHolder.tv_name.setText(bean.getName());
        if ("0".equals(bean.getEstatus())){
            viewHolder.tv_name.setBackgroundResource(R.mipmap.bg_btn_blue);
            viewHolder.tv_name.setTextColor(getContext().getResources().getColor(R.color.white));
        }else{
            viewHolder.tv_name.setBackgroundResource(R.mipmap.bg_btn_transparency);
            viewHolder.tv_name.setTextColor(getContext().getResources().getColor(R.color.font_black));
        }


        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public TextView tv_name;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }

}
