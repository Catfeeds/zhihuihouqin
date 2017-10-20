package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.McNoticeListResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/13 0013.
 */

public class NoticeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<McNoticeListResponse.PageBean.ListBean> mList = new ArrayList<>();
    private String type;  //1: 公告，2：办公，3：理发作品，4：图书，5：医生，6：活动，7：发型师 //8:健康资讯 9专家 10营养套餐


    public NoticeAdapter(Context context,String type) {
        this.mContext = context;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if ("1".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("2".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("3".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("4".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("5".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("6".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("7".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("8".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else if ("9".equals(type)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
       /* viewHolder.tvFirstrvTitle.setText(mList.get(position).getTitle());
        viewHolder.tvFirstrvTime.setText(mList.get(position).getCreatetime());
        viewHolder.tvFirstrvDes.setText(mList.get(position).getSource());
        GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, mList.get(position).getImg(), viewHolder.ivFirstrvLogo);*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<McNoticeListResponse.PageBean.ListBean> list) {
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_firstrv_logo)
        ImageView ivFirstrvLogo;
        @BindView(R.id.tv_firstrv_title)
        TextView tvFirstrvTitle;
        @BindView(R.id.tv_firstrv_time)
        TextView tvFirstrvTime;
        @BindView(R.id.tv_firstrv_des)
        TextView tvFirstrvDes;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
