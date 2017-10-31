package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.SpCheckShopCarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import mvp.cn.util.ToastUtil;

/**
 * Created by 我的电脑 on 2017/9/20 0020.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SpCheckShopCarBean.CartItemsBean> data = new ArrayList<>();
    private boolean isSelect = true;
    private boolean isSelectAll;

    public ShopCarListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.shop_car_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        /*if (data != null) {
            viewHolder.setData(data.get(position), position,isSelect);
        }*/
        if (data != null) {
            final SpCheckShopCarBean.CartItemsBean cartItemsBean = data.get(position);
            if (cartItemsBean != null) {
                int count = cartItemsBean.getCount();
                //this.count=count;
                SpCheckShopCarBean.CartItemsBean.SkuBean sku = cartItemsBean.getSku();
                String mainimg = sku.getMainimg();//图片
                double price = sku.getPrice();
                String skuname = sku.getSkuname();
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, mainimg, viewHolder.ivPic);
                viewHolder.tvCount.setText(count + "");
                viewHolder.tvDes.setText(skuname);
              /*  if (isSelectAll) {
                    cartItemsBean.setSeclect(true);
                } else {
                    cartItemsBean.setSeclect(isSelect);
                }*/
                if (cartItemsBean.isSeclect()) {
                    viewHolder.ivItemSelect.setImageResource(R.drawable.select);
                } else {
                    viewHolder.ivItemSelect.setImageResource(R.drawable.unselect);
                }
            }
        //数量增加
        viewHolder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cartItemsBean.getCount();
                count++;
                int store = cartItemsBean.getSku().getStore();
                //判断库存数量是否少于选中数量
                if(count<=store){
                    cartItemsBean.setCount(count);
                }else{
                    ToastUtil.showToast(mContext,"库存量剩余"+store+"个");
                }
                if(listener!=null){
                    listener.onCliickListener();
                }
                notifyDataSetChanged();
            }
        });
            //减少
        viewHolder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = cartItemsBean.getCount();
                if(count<=0){
                    count=0;
                }else{
                    count--;
                }
                cartItemsBean.setCount(count);
                if (listener != null) {
                    listener.onCliickListener();
                }
                notifyDataSetChanged();
            }
        });
            /*ivItemSelect*/
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartItemsBean.setSeclect(!cartItemsBean.isSeclect());
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onCliickListener();
                    }
                }
            });
    }
    }
    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<SpCheckShopCarBean.CartItemsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void selectAll() {
        if(data!=null){
            for (int i = 0; i < data.size(); i++) {
                SpCheckShopCarBean.CartItemsBean cartItemsBean = data.get(i);
                cartItemsBean.setSeclect(true);
            }
        }
        notifyDataSetChanged();
    }
    public void unSelectAll() {
        if(data!=null){
            for (int i = 0; i < data.size(); i++) {
                SpCheckShopCarBean.CartItemsBean cartItemsBean = data.get(i);
                cartItemsBean.setSeclect(false);
            }
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_select)
        ImageView ivItemSelect;
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_minus)
        ImageView tvMinus;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_add)
        ImageView tvAdd;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onCliickListener();
    }
}
