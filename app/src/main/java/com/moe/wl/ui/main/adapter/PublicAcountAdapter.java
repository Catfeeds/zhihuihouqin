package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.PurchaseAccountListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Wang
 * 日期 2017/10/25.
 * 描述
 */

public class PublicAcountAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<PurchaseAccountListBean.AccountListBean> data=new ArrayList<>();

    public PublicAcountAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.public_acount_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        if(data!=null){
            PurchaseAccountListBean.AccountListBean accountListBean = data.get(position);
            if(accountListBean!=null){
                holder1.tvAcountType.setText(accountListBean.getTypename());
                holder1.tvAcount.setText(accountListBean.getMoney()+"");
            }

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

    public void setData(List<PurchaseAccountListBean.AccountListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_acount_type)
        TextView tvAcountType;
        @BindView(R.id.tv_acount)
        TextView tvAcount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
