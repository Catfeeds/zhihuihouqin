package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

import com.moe.wl.ui.main.activity.HairStyleDetailActivity;
import com.moe.wl.ui.main.bean.BarberDetailBean;
import com.moe.wl.ui.main.bean.BarberWorkListBean;

/**
 * Created by 我的电脑 on 2017/8/22 0022.
 */

public class BarberProductAdapter extends BaseAdapter {
    private  Context mContext;
    private List<BarberDetailBean.WorklistBean> data=new ArrayList<>();

    public BarberProductAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    public int getCount() {
        if(data!=null){
            data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.barber_product_grid_item, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        BarberDetailBean.WorklistBean worklistBean = data.get(position);
        viewHolder.setData(worklistBean);
        return convertView;
    }

    public void setData(List<BarberDetailBean.WorklistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setMoreData(List<BarberWorkListBean.WorklistBean> moreData) {
        //this.data = moreData;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.iv_product_photo)
        ImageView ivProductPhoto;
        @BindView(R.id.tv_product_des)
        TextView tvProductDes;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HairStyleDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(BarberDetailBean.WorklistBean worklistBean) {
            if(worklistBean!=null){
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,worklistBean.getDetailimg(),ivProductPhoto);
            tvProductDes.setText(worklistBean.getName());
                tvMoney.setText("￥"+worklistBean.getPrice());
            }
        }
    }
}
