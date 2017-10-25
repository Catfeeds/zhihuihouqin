package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.HairStyleDetailActivity;
import com.moe.wl.ui.main.bean.WorklistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Wang
 * 日期 2017/10/25.
 * 描述
 */

public class BarberCollectAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<WorklistBean> data=new ArrayList<>();

    public BarberCollectAdapter(Context mContext) {
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
            WorklistBean worklistBean = data.get(position);
            viewHolder.setData(worklistBean,position);
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

    public void setData(List<WorklistBean> data) {
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
        private WorklistBean data;
        private int id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HairStyleDetailActivity.class);
                    intent.putExtra("workid", id);
                    intent.putExtra("img",data.getDetailimg());
                    intent.putExtra("price",data.getPrice());
                    intent.putExtra("name",data.getName());
                    intent.putExtra("brief",data.getBrief());
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(WorklistBean worklistBean, int position) {
            this.data=worklistBean;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,worklistBean.getDetailimg(),icSpPhoto,R.mipmap.ic_default_square);
            tvSpDes.setText(worklistBean.getName());
            tvPrice.setText("￥" +worklistBean.getPrice());
            id = worklistBean.getId();
        }
    }
}
