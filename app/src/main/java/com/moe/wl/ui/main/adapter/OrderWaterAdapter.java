package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.QueryWaterListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class OrderWaterAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<QueryWaterListBean.PageBean.ListBean> data=new ArrayList<>();

    public OrderWaterAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.order_water_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final int mPosition=position;
        if (data != null && data.size() > 0) {
            final QueryWaterListBean.PageBean.ListBean listBean = data.get(position);
            viewHolder.setData(listBean);
            viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = listBean.getCount();
                    count++;
                    listBean.setCount(count);
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onClickListener(listBean, listBean.getId(), true);
                    }
                }
            });
            viewHolder.ivMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = listBean.getCount();
                    if(count>0){
                        count--;
                    }else{
                        count=0;
                    }
                    listBean.setCount(count);
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onClickListener(listBean, listBean.getId(), false);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }
        return 0;
    }

    public void setData(List<QueryWaterListBean.PageBean.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item)
        ImageView ivItem;
        @BindView(R.id.tv_water_name)
        TextView tvWaterName;
        @BindView(R.id.tv_how_much)
        TextView tvHowMuch;
        @BindView(R.id.iv_minus)
        ImageView ivMinus;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.iv_add)
        ImageView ivAdd;
        private QueryWaterListBean.PageBean.ListBean data;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(QueryWaterListBean.PageBean.ListBean data) {
            this.data = data;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,data.getImg(),ivItem);
            tvWaterName.setText(data.getName());
            tvHowMuch.setText("￥"+data.getPrice());
            int count = data.getCount();
            tvCount.setText(count + "");
            if(count>0){
                ivMinus.setVisibility(View.VISIBLE);
                tvCount.setVisibility(View.VISIBLE);
            }else{
                ivMinus.setVisibility(View.GONE);
                tvCount.setVisibility(View.GONE);
            }
        }
    }
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClickListener(QueryWaterListBean.PageBean.ListBean bean, int id, boolean isAdd);
    }
}
