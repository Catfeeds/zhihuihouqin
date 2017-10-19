package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.me.OrderOfficeDetailActivity;
import com.moe.wl.ui.main.bean.OrderOfficeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：报修订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderOfficeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderOfficeBean.ListEntity> data;
    private int state;
    private OnClickListener listener;

    public OrderOfficeAdapter(Context context, List<OrderOfficeBean.ListEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_office, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;

        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getImg(), holder.image);
        holder.name.setText(data.get(position).getProductName());
        holder.mass.setText(data.get(position).getSkuname());
        holder.number.setText("x" + data.get(position).getCount());
        holder.price.setText("¥" + data.get(position).getPrice());
        holder.orderNumber.setText("订单号：" + data.get(position).getOrdercode());

        switch (state) {
            case 0:
                holder.order1.setText("取消订单");
                break;
            case 1:
                holder.order1.setText("联系商家");
                break;
            case 2:
                holder.order1.setText("再来一单");
                break;
            case 3:
                holder.order1.setText("立即评价");
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

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderOfficeDetailActivity.class);
                intent.putExtra("OrderID", data.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnClickListener {
        void onClick(int type, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.mass)
        TextView mass;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.order_1)
        TextView order1;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
