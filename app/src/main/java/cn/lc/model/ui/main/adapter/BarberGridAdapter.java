package cn.lc.model.ui.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.PreOrderBean;
import retrofit2.http.POST;

/**
 * Created by 我的电脑 on 2017/8/22 0022.
 */

public class BarberGridAdapter extends BaseAdapter {
    private int mPosition=-1;
    private List<PreOrderBean.TimelistBean.SchedulelistBean> data=new ArrayList<>();

    @Override
    public int getCount() {
        if(data!=null){
            return data.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(data!=null&&data.size()>0)
        viewHolder.setData(position);
        return convertView;
    }

    public void changeColor(int position) {
        this.mPosition=position;
        notifyDataSetChanged();
    }

    public void setData(List<PreOrderBean.TimelistBean.SchedulelistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.tv_barber_grid_item)
        TextView tvBarberGridItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(int position) {
            tvBarberGridItem.setText(data.get(position)+"");
            if(mPosition==position){
                tvBarberGridItem.setTextColor(Color.parseColor("#00aa00"));
            }else{
                tvBarberGridItem.setTextColor(Color.parseColor("#333333"));
            }
        }
    }
}
