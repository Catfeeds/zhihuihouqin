package com.moe.wl.ui.home.adapter.saving;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/12/18 0018.
 */

public class InformationsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SaveHomeListBean.NewsBean> itemList=new ArrayList<>();

    public InformationsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.save_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(itemList!=null&&itemList.size()>0){
            SaveHomeListBean.NewsBean newsBean = itemList.get(position);
            ((ViewHolder) holder).setData(newsBean,position);
            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.setOnItemClickListener(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(itemList!=null&&itemList.size()>0){
            return itemList.size();
        }else{
            return 0;
        }
    }

    public void setData(List<SaveHomeListBean.NewsBean> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
         private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.setOnItemClickListener(mPosition);
                    }
                }
            });*/
        }

        public void setData(SaveHomeListBean.NewsBean dataBean, int position) {
            this.mPosition=position;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,dataBean.getImg(),ivFirstrvLogo);
            tvFirstrvTitle.setText(dataBean.getTitle());
            tvFirstrvTime.setText(dataBean.getCreatTime());
            tvFirstrvDes.setText(dataBean.getSourceName());
        }
    }
    private ClickListener listener;

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public interface ClickListener{
        void setOnItemClickListener(int position);
    }
}
