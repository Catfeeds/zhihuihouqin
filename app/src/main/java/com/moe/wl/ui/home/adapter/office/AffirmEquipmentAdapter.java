package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.office.EquipmentListBean;

/**
 * 办公室设备列表
 */
public class AffirmEquipmentAdapter extends MyBaseAdapter<EquipmentListBean> {

    private MyCallBack callBack;

    public AffirmEquipmentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office_affirm_equipment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        EquipmentListBean bean=getItem(position);
        if (bean.isCheck()){
            viewHolder.checkbox.setChecked(true);
        }else{
            viewHolder.checkbox.setChecked(false);
        }
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null){
                    callBack.cb(position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public CheckBox checkbox;
        public LinearLayout ll_all;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ll_all = (LinearLayout) rootView.findViewById(R.id.ll_all);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.checkbox = (CheckBox) rootView.findViewById(R.id.checkbox);
        }

    }

    public interface MyCallBack{
        void cb(int pos);
    }

    public void setMyCallBack(MyCallBack callBack){
        this.callBack=callBack;
    }

}
