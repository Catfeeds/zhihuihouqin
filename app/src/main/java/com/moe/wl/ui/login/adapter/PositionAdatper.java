package com.moe.wl.ui.login.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.ui.login.bean.PositionListBean;

/**
 * Created by 我的电脑 on 2017/9/4 0004.
 */

public class PositionAdatper extends RecyclerView.Adapter {
    private Context mContecxt;
    private List<PositionListBean.PositionlistBean> date = new ArrayList<>();

    public PositionAdatper(Context mContecxt) {
        this.mContecxt = mContecxt;
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
        if(date!=null){
            viewHolder.setDate(date.get(position),position);

        }
    }

    @Override
    public int getItemCount() {
        if (date != null) {
            return date.size();
        }
        return 0;
    }

    private int selectPosition=0;

    public void setDate(List<PositionListBean.PositionlistBean> date) {
        this.date = date;
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
                    selectPosition=mPosition;
                    if(lister!=null){
                        lister.itemClickLister(selectPosition);
                    }
                    notifyDataSetChanged();

                }
            });
        }

        public void setDate(PositionListBean.PositionlistBean positionlistBean, int position) {
            this.mPosition=position;
            tvPosition.setText(positionlistBean.getName());
            if(selectPosition==position){
                ivSelect.setVisibility(View.VISIBLE);
            }else{
                ivSelect.setVisibility(View.GONE);
            }
        }
    }
    private OnClickLister lister;

    public void setLister(OnClickLister lister) {
        this.lister = lister;
    }

    public interface OnClickLister{
        void itemClickLister(int position);
    }
}
