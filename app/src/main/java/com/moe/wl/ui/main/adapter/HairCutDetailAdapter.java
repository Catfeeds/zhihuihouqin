package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.moe.wl.R;
import com.moe.wl.ui.main.bean.HairOrderDetailBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/12/15 0015.
 */

public class HairCutDetailAdapter extends RecyclerView.Adapter {
    private  Context mContext;
    private List<HairOrderDetailBean.DetaillistBean> data=new ArrayList<>();

    public HairCutDetailAdapter(Context context) {
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.cut_hair_service_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(data!=null&&data.size()>0){
            HairOrderDetailBean.DetaillistBean detaillistBean = data.get(position);
            ((ViewHolder) holder).setData(detaillistBean,position);
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }else{
            return 0;
        }
    }

    public void setData(List<HairOrderDetailBean.DetaillistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_name)
        TextView itemName;
        @BindView(R.id.item_money)
        TextView itemMoney;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
         public void setData(HairOrderDetailBean.DetaillistBean data,int position){
             itemName.setText(data.getName());
             itemMoney.setText("￥"+data.getPrice()+"");
         }
    }
}
