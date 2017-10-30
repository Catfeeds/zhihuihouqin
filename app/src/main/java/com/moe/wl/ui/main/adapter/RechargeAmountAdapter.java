package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/14 0014.
 */

public class RechargeAmountAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList = new ArrayList<>();

    public RechargeAmountAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recharge_amount_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String text = mList.get(position);
        viewHolder.setData(text, position);
        //viewHolder.tvSelectMoney.setText(mList.get(position));
        return convertView;
    }

    public void setData(List<String> data) {
        this.mList = data;
    }

    public int selectPosition = 0;

    class ViewHolder {
        @BindView(R.id.tv_select_money)
        TextView tvSelectMoney;
        private String data;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition=mPosition;
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onItemClickListener(mList.get(selectPosition));
                    }
                }
            });
        }
        public void setData(String data, int position) {
            this.data = data;
            this.mPosition = position;
            tvSelectMoney.setText(data);
            if(selectPosition==position){
                tvSelectMoney.setBackgroundResource(R.drawable.shape_blue_confirm);
                tvSelectMoney.setTextColor(Color.WHITE);
            }else{
                tvSelectMoney.setBackgroundResource(R.drawable.shape_blue_line);
                tvSelectMoney.setTextColor(Color.parseColor("#333333"));
            }
        }
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(String text);
    }
}
