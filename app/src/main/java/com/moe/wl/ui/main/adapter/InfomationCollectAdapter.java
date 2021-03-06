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
import com.moe.wl.ui.main.activity.information.InformationDetailActivity;
import com.moe.wl.ui.main.bean.InforMationCollect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class InfomationCollectAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<InforMationCollect.ListBean> data = new ArrayList<>();
    private boolean mIsEdit=false;

    public InfomationCollectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        return new ViewHolder(view);
    }
    public void setIsEdit(boolean isEdit) {
        this.mIsEdit = isEdit;
        LogUtils.i("adapter里的isEdit==" + isEdit);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final InforMationCollect.ListBean listBean = data.get(position);
        holder1.tvFirstrvTitle.setText(data.get(position).getTitle());
        holder1.tvFirstrvTime.setText(data.get(position).getCreatetime());
        holder1.tvFirstrvDes.setText(data.get(position).getSource());
        GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, data.get(position).getImg(), holder1.ivFirstrvLogo);

        holder1.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, InformationDetailActivity.class);
                intent.putExtra("ID", data.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        holder1.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = listBean.isSelect();
                select=!select;
                listBean.setSelect(select);
                notifyDataSetChanged();
                if(listener!=null){
                    listener.updataListListener(select,position);
                }
            }
        });
        if (mIsEdit) {
            holder1.cancel.setVisibility(View.VISIBLE);
        } else {
            holder1.cancel.setVisibility(View.GONE);
        }
        boolean select = listBean.isSelect();
        if (select) {
            holder1.cancel.setImageResource(R.drawable.selected);
        } else {
            holder1.cancel.setImageResource(R.drawable.unselected);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(List<InforMationCollect.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
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
        @BindView(R.id.iv_cancel)
        ImageView cancel;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private UpdataListListener listener;

    public void setListener(UpdataListListener listener) {
        this.listener = listener;
    }

    public interface UpdataListListener {
        void updataListListener(boolean isSelect, int position);
    }
}
