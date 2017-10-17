package com.moe.wl.ui.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.RepairItmeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/9/6 0006.
 */

public class WeixiuAdapter extends BaseAdapter {
    //    private List<String> datas = new ArrayList<>();
    private List<RepairItmeBean.ItemlistEntity> data = new ArrayList<>();
    private OnclickListener listener;
//    public void setDatas(List<String> datas) {
//        this.datas = datas;
//        notifyDataSetChanged();keytool -v -list -keystore ***.keystoreE:\liuboyan
//    }

    public void setDatas(List<RepairItmeBean.ItemlistEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private int selectPosition = -1;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
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
                    selectPosition = mPosition;
                    if (listener != null)
                        listener.onClick(data.get(selectPosition).getId());
                    notifyDataSetChanged();
                }
            });
        }

        public void setData(RepairItmeBean.ItemlistEntity entity, int position) {
            this.mPosition = position;
            tvBarberGridItem.setText(entity.getName());
            if (selectPosition == position) {
                tvBarberGridItem.setBackgroundColor(Color.parseColor("#0099FF"));
                tvBarberGridItem.setTextColor(Color.WHITE);
            } else {
                tvBarberGridItem.setBackgroundColor(Color.WHITE);
                tvBarberGridItem.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

    public interface OnclickListener {
        void onClick(int id);
    }

    public void setOnclickListener(OnclickListener listener) {
        this.listener = listener;
    }

}
