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
import com.moe.wl.ui.main.bean.OfficeCollect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Wang
 * 日期 2017/10/25.
 * 描述
 */

public class OfficeCollectAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<OfficeCollect.ListBean> data=new ArrayList<>();

    public OfficeCollectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.office_collect_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(data!=null){
            OfficeCollect.ListBean listBean = data.get(position);
            viewHolder.setData(listBean,position);
        }
    }

    @Override
    public int getItemCount() {
       if(data!=null){
           return data.size();
       }else{
           return 0;
       }
    }

    public void setData(List<OfficeCollect.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ic_sp_photo)
        ImageView icSpPhoto;
        @BindView(R.id.tv_sp_des)
        TextView tvSpDes;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(OfficeCollect.ListBean listBean, int position) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,listBean.getProductImg(),icSpPhoto,R.mipmap.ic_default_square);
            tvSpDes.setText(listBean.getProductname());
            tvPrice.setText(listBean.getPricerange());
        }
    }
}
