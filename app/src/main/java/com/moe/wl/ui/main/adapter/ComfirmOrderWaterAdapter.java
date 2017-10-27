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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/9/27 0027.
 */

public class ComfirmOrderWaterAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<QueryWaterListBean.PageBean.ListBean> mList;

    public ComfirmOrderWaterAdapter(Context context, List<QueryWaterListBean.PageBean.ListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.order_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        QueryWaterListBean.PageBean.ListBean listBean = mList.get(position);
        String img = listBean.getImg();
        int count = listBean.getCount();
        double price = listBean.getPrice();
        String name = listBean.getName();
        GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, img, viewHolder.ivPic);
        viewHolder.tvCount.setText("x" + count);
        viewHolder.tvDes.setText(name);
        viewHolder.tvSum.setText("￥" + price);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_sum)
        TextView tvSum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
