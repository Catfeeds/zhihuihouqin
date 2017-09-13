package cn.lc.model.ui.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public class OrderTimesAdapter extends BaseAdapter {
    private List<String> data = new ArrayList();

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data != null && data.size() > 0)
            viewHolder.setData(data.get(position), position);
        return convertView;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private int selectPosition = 0;
    public int getSelectPosition(){
        return selectPosition;
    }

    class ViewHolder {
        @BindView(R.id.tv_barber_grid_item)
        TextView tvBarberGridItem;
        @BindView(R.id.ll_container)
        LinearLayout llContainer;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition=mPosition;
                    notifyDataSetChanged();
                }
            });
        }

        public void setData(String s, int position) {
            this.mPosition = position;
            tvBarberGridItem.setText(s);
            if(selectPosition==position){
                tvBarberGridItem.setBackgroundColor(Color.parseColor("#cccccc"));
                tvBarberGridItem.setTextColor(Color.parseColor("#F95759"));
            }else{
                tvBarberGridItem.setBackgroundColor(Color.WHITE);
                tvBarberGridItem.setTextColor(Color.parseColor("#333333"));
            }
        }
    }
}
