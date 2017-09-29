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
import com.moe.wl.ui.main.bean.OrderVegetableBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：报修订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderVegetableAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderVegetableBean.PageEntity.ListEntity> data;
    private int state;
    private OnClickListener listener;


    public OrderVegetableAdapter(Context context, List<OrderVegetableBean.PageEntity.ListEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_vegetable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        holder.orderNumber.setText("订单号：" + data.get(position).getOrdercode());
        if (data.get(position).getDetailList().size() != 0) {
            holder.name.setText(data.get(position).getDetailList().get(0).getFoodName());
            GlideLoading.getInstance().loadImgUrlHeader(context, data.get(position).getDetailList().get(0).getFoodImg(), holder.image);
            holder.content.setText(data.get(position).getDetailList().get(0).getFoodOriginal());
        }
        if (data.get(position).getDetailList() != null) {
            holder.merNumber.setText("净菜套餐等" + data.get(position).getDetailList().size() + "件商品");
        } else {
            holder.merNumber.setText("净菜套餐等null件商品");
        }

        switch (state) {
            case 0:
                holder.order.setText("取消订单");
                break;
            case 1:
                holder.order.setText("联系商家");
                break;
            case 2:
                holder.order.setText("评价");
                break;
            case 3:
                holder.order.setText("评价");
                break;
        }

        holder.order.setOnClickListener(new View.OnClickListener() {
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

    public interface OnClickListener {
        void onClick(int type, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.mer_number)
        TextView merNumber;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.order)
        TextView order;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
