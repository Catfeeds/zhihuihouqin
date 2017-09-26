package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.HealthConsultDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

import com.moe.wl.ui.main.bean.InfolistBean;

/**
 * Created by 我的电脑 on 2017/8/15 0015.
 */
public class HealthServiceRvAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<InfolistBean> data;
    private int mType = 0;

    public HealthServiceRvAdapter(Context context) {
        data = new ArrayList<>();
        this.context = context;
    }

    public HealthServiceRvAdapter(Context context, List<InfolistBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<InfolistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setData(List<InfolistBean> data, int type) {
        this.data = data;
        mType = type;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_service_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data != null) {
            viewHolder.setData(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mType != 0)
            return data.size() > 4 ? 4 : data.size();
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header)
        ImageView ivHealthServiceItem;
        @BindView(R.id.title)
        TextView tvHealthServiceTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_bumen)
        TextView tvBumen;
        private InfolistBean infolistBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HealthConsultDetailActivity.class);
                    intent.putExtra("url", infolistBean.getUrl());
                    intent.putExtra("id", infolistBean.getId());
                    context.startActivity(intent);
                }
            });
        }

        public void setData(InfolistBean infolistBean) {
            this.infolistBean = infolistBean;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, infolistBean.getImgs(), ivHealthServiceItem);
            tvHealthServiceTitle.setText(infolistBean.getTitle());
            tvTime.setText(infolistBean.getCreatetime());
            tvBumen.setText(infolistBean.getSource());
        }
    }
}
