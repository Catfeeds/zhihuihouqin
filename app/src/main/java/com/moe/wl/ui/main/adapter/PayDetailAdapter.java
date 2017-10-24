package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.FindWalletLogBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/13 0013.
 */

public class PayDetailAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<FindWalletLogBean.PageBean.ListBean> mList=new ArrayList<>();

    public PayDetailAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.pay_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        FindWalletLogBean.PageBean.ListBean listBean = mList.get(position);
        String ordertypename = listBean.getOrdertypename();
        holder1.tvDetail.setText(ordertypename);
        holder1.tvCreatetime.setText(listBean.getCreatetime());
        int usetype = listBean.getUsetype();
        if(usetype==1){//收入
            holder1.tvMoney.setText("+"+listBean.getMoney());
        }else if(usetype==2){//指出
            holder1.tvMoney.setText("-"+listBean.getMoney());
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<FindWalletLogBean.PageBean.ListBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_detail)
        TextView tvDetail;
        @BindView(R.id.tv_createtime)
        TextView tvCreatetime;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
