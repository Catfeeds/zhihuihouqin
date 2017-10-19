package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.bean.office.AppointmentListBean;
import com.moe.wl.ui.main.adapter.MyBaseAdapter;

/**
 * 办公室列表
 */
public class SubscribeTimeAdapter extends MyBaseAdapter<AppointmentListBean> {

    private MyCallBack callBack;

    public SubscribeTimeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_subscribe_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AppointmentListBean bean=getItem(position);
        viewHolder.tv_time.setText(bean.getDurationstr());
        if (bean.isCheck()){
            viewHolder.tv_time.setTextColor(getContext().getResources().getColor(R.color.white));
            viewHolder.tv_time.setBackgroundResource(R.mipmap.bg_btn_blue);
        }else{
            viewHolder.tv_time.setTextColor(getContext().getResources().getColor(R.color.font_black));
            viewHolder.tv_time.setBackgroundResource(R.mipmap.bg_btn_transparency);
        }
        viewHolder.tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null){
                    callBack.cb(position);
                }
            }
        });

        return convertView;
    }

    public class ViewHolder{
        public View rootView;
        public TextView tv_time;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }
    }

    public interface MyCallBack{
        void cb(int pos);
    }

    public void setMyCallBack(MyCallBack callBack){
        this.callBack=callBack;
    }
}
