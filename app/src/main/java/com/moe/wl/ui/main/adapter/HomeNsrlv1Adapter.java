package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.information.InformationDetailActivity;
import com.moe.wl.ui.main.bean.ListEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.cn.util.ToastUtil;

/**
 * 首页最新公告
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeNsrlv1Adapter extends RecyclerView.Adapter {

    private Context context;
    private List<ListEntity> data;
    private int i = 0;

    public HomeNsrlv1Adapter() {
        data = new ArrayList<>();
        i = 1;
    }

    public HomeNsrlv1Adapter(Context context, List<ListEntity> data) {
        this.context = context;
        this.data = data;
        if(data!=null){
            LogUtils.i("data bu为空");
        }else{
            LogUtils.i("data 为空");
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        if (i == 1)
            return;
        ViewHolder holder = (ViewHolder) holder1;
        holder.tvFirstrvTitle.setText(data.get(position).getTitle());
        holder.tvFirstrvTime.setText(data.get(position).getCreatetime());
        holder.tvFirstrvDes.setText(data.get(position).getSource());
        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getImg(), holder.ivFirstrvLogo, R.mipmap.ic_default_square);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!OtherUtils.isAuth()){
                    // 没有认证
                    OtherUtils.showAuth(context);
                    return;
                }
                Intent intent = new Intent(context, InformationDetailActivity.class);
                intent.putExtra("ID", data.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (i == 1)
            return 3;
        return data.size();
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
        @BindView(R.id.item)
        RelativeLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
