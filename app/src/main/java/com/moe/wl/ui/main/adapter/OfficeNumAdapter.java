package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.OfficeslistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class OfficeNumAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<OfficeslistBean.OfficelistBean> data=new ArrayList<>();

    public OfficeNumAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.position_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data != null) viewHolder.setDate(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }else{
            return 0;
        }

    }
    private int selectPosition=0;

    public void setData(List<OfficeslistBean.OfficelistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    if (lister != null) {
                        lister.itemClickLister(selectPosition);
                    }
                    notifyDataSetChanged();

                }
            });
        }

        public void setDate(OfficeslistBean.OfficelistBean departListBean, int position) {
            this.mPosition = position;
            tvPosition.setText(departListBean.getName());
            if (selectPosition == position) {
                ivSelect.setVisibility(View.VISIBLE);
            } else {
                ivSelect.setVisibility(View.GONE);
            }
        }
    }

    private OnClickLister lister;

    public void setLister(OnClickLister lister) {
        this.lister = lister;
    }

    public interface OnClickLister {
        void itemClickLister(int position);
    }
}
