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
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.BarberDetailActivity;
import com.moe.wl.ui.main.bean.BarberProductCollect;

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
    private List<BarberProductCollect.ListBean> data=new ArrayList<>();
    private boolean mIsEdit=false;

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
            BarberProductCollect.ListBean listBean = data.get(position);
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

    public void setData(List<BarberProductCollect.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void setIsEdit(boolean isEdit) {
        this.mIsEdit = isEdit;
        LogUtils.i("adapter里的isEdit==" + isEdit);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ic_sp_photo)
        ImageView icSpPhoto;
        @BindView(R.id.tv_sp_des)
        TextView tvSpDes;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_cancel)
        ImageView cancel;
        private BarberProductCollect.ListBean data;
        private int id;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* BarberlistBean barberlistBean = data.get(position);
                    String place = data.getPlace();
                    String tradename = shopBean.getTradename();*//*
                    Intent intent = new Intent(mContext, BarberDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("address", place);
                    bundle.putString("shopName", tradename);
                    bundle.putSerializable("barberlistBean", barberlistBean);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean select = data.isSelect();
                    select = !select;
                    data.setSelect(select);
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.updataListListener(select, mPosition);
                    }
                }
            });
        }

        public void setData(BarberProductCollect.ListBean listBean, int position) {
            this.data=listBean;
            this.mPosition=position;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,listBean.getPhoto(),icSpPhoto,R.mipmap.ic_default_square);
            tvSpDes.setText(listBean.getName());
            tvPrice.setText("￥" +listBean.getPrice());
            id = listBean.getId();

            if (mIsEdit) {
                cancel.setVisibility(View.VISIBLE);
            } else {
                cancel.setVisibility(View.GONE);
            }
            boolean select = listBean.isSelect();
            if (select) {
                cancel.setImageResource(R.drawable.selected);
            } else {
                cancel.setImageResource(R.drawable.unselected);
            }
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
