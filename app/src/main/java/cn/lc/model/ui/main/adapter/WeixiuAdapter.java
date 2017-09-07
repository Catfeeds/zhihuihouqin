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
 * Created by 我的电脑 on 2017/9/6 0006.
 */

public class WeixiuAdapter extends BaseAdapter {
    private List<String> datas=new ArrayList<>();

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(datas!=null){
            return datas.size();
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
    private int selectPosition=0;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
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
       if (datas != null && datas.size() > 0)
            viewHolder.setData(datas.get(position),position);
        return convertView;
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
            this.mPosition=position;
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
