package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.QueryWaterTypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moe.wl.R.id.view;

/**
 * Created by 我的电脑 on 2017/9/25 0025.
 */

public class OrderWaterTypeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<QueryWaterTypeBean.CategoryListBean> data = new ArrayList<>();

    public OrderWaterTypeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.order_water_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data != null && data.size() > 0) {
            viewHolder.setData(data.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<QueryWaterTypeBean.CategoryListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    private int selectPosition = 0;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_water_item)
        TextView tvOrderWaterItem;
        @BindView(view)
        View view1;
        @BindView(R.id.item)
        LinearLayout item;

        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    if (listener != null) {
                        listener.onItemClickListener(selectPosition);
                    }
                    notifyDataSetChanged();
                }
            });
        }

        public void setData(QueryWaterTypeBean.CategoryListBean s, int position) {
            this.mPosition = position;
            if (s != null) {
                String skucataname = s.getSkucataname();
                tvOrderWaterItem.setText(skucataname);
            }
            if (selectPosition == position) {
                view1.setVisibility(View.VISIBLE);
                item.setBackgroundColor(mContext.getResources().getColor(R.color.gray_lighter));
                tvOrderWaterItem.setTextColor(Color.parseColor("#0099FF"));
            } else {
                view1.setVisibility(View.INVISIBLE);
                item.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                tvOrderWaterItem.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
