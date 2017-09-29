package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.OrderWaterBean;
import com.moe.wl.ui.main.bean.QueryWaterListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class WaterAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<OrderWaterBean.PageEntity.ListEntity> data;
    private int state;
    private OnClickListener listener;


    public WaterAdapter(Context context, List<OrderWaterBean.PageEntity.ListEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_water, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        holder.orderNumber.setText("订单号：" + data.get(position).getOrdercode());
        if (data.get(position).getDetailList().size() != 0) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getDetailList().get(0).getGoods().getImg(), holder.image);
            holder.name.setText(data.get(position).getDetailList().get(0).getGoods().getName()); // 商品名称
            holder.number.setText("x" + data.get(position).getDetailList().get(0).getCount());  // 商品数量
        }
        holder.price.setText("￥" + data.get(position).getTotalprice());

        switch (state) {
            case 0:
                holder.order1.setText("取消订单");
                break;
            case 1:
                holder.order1.setText("联系商家");
                break;
            case 2:
                holder.order1.setText("评价");
                break;
            case 3:
                holder.order1.setText("评价");
                break;
            case 4:
                holder.order1.setText("删除订单");
                break;
        }

        holder.order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(state, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.order_1)
        TextView order1;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickListener {
        void onClick(int type, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
