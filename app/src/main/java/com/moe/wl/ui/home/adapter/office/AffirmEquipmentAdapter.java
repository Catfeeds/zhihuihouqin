package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;

/**
 * 办公室设备列表
 */
public class AffirmEquipmentAdapter extends MyBaseAdapter<String> {

    public AffirmEquipmentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office_affirm_equipment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position%2==0){
            viewHolder.checkbox.setChecked(true);
        }else{
            viewHolder.checkbox.setChecked(false);
        }
        viewHolder.tv_name.setText(getItem(position));

        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public CheckBox checkbox;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.checkbox = (CheckBox) rootView.findViewById(R.id.checkbox);
        }

    }

}
