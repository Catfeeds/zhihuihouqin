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
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.activity.me.OrderVegetableDetailActivity;
import com.moe.wl.ui.main.bean.OrderVegetableBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.cn.util.DateUtil;

/**
 * 类描述：报修订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderVegetableAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderVegetableBean.ListEntity> data;
    private int state;
    private OnClickListener listener;


    public OrderVegetableAdapter(Context context, List<OrderVegetableBean.ListEntity> data, int state) {
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
        if (data.get(position).getFoodName() == null || "".equals(data.get(position).getFoodName())) {
            holder.name.setText("未指定理发师");
        } else {
            holder.name.setText(data.get(position).getFoodName());
        }
        GlideLoading.getInstance().loadImgUrlHeader(context, data.get(position).getFoodImg(), holder.image, R.mipmap.ic_default_square);
        holder.content.setText(data.get(position).getFoodOriginal());
//        holder.merNumber.setText("净菜套餐等" + data.get(position).get + "件商品");

        switch (state) {
            case 0:
                if (data.get(position).getPayStatus() == 0) {
                    holder.left.setVisibility(View.VISIBLE);
                    holder.left.setText("支付");
                } else {
                    holder.left.setVisibility(View.GONE);
                }
                holder.order.setText("取消订单");
                break;
            case 2:
                holder.left.setVisibility(View.VISIBLE);
                holder.order.setText("再次预订");
                break;
            case 3:
                holder.left.setVisibility(View.GONE);
                holder.order.setText("立即评价");
                break;
            case 4:
                holder.left.setVisibility(View.GONE);
                holder.order.setText("删除订单");
                break;
        }

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(state, position);
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderVegetableDetailActivity.class);
                intent.putExtra("OrderID", data.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == 0) {
                    Intent intent = new Intent(context, PayFiveJiaoActivity.class);
                    intent.putExtra("from", Constants.VEGETABLE);
//                    intent.putExtra("pay", data.get(position).getTotalprice());
                    intent.putExtra("orderid", data.get(position).getId()+ "");
                    intent.putExtra("ordercode", data.get(position).getOrdercode());
                    intent.putExtra("ordertype", Constants.VEGETABLE + "");
                    intent.putExtra("time", DateUtil.getTimeyyyyMMddHHmm());
                    context.startActivity(intent);
                } else if (state == 2) {
                    OtherUtils.gotoComment(context, data.get(position).getId(), Constants.VEGETABLE);
                }
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
        @BindView(R.id.left)
        TextView left;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
