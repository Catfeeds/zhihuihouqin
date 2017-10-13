package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.activity.me.OrderMealDetailActivity;
import com.moe.wl.ui.main.bean.OrderMealBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：工作餐订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderMealAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderMealBean.ListEntity> data;
    private OnCommentClickListener commentListener;
    private OnClickListener listener;
    private int state;

    public OrderMealAdapter(Context context, List<OrderMealBean.ListEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;

        holder.orderNumber.setText("订  单  号：" + data.get(position).getOrdercode());
        holder.time.setText("下单时间：" + data.get(position).getCreatetime());
        holder.arriveTime.setText("送达时间：" + data.get(position).getSendTime());
        holder.number.setText("预订数量：" + data.get(position).getCount() + "份");

        switch (state) {
            case 0:
                holder.comment.setVisibility(View.GONE);
                holder.order.setText("取消订单");
                break;
            case 1:
                holder.comment.setVisibility(View.VISIBLE);
                holder.order.setText("再次预订");
                break;
            case 2:
                holder.comment.setVisibility(View.GONE);
                holder.order.setText("评价");
                break;
            case 3:
                holder.comment.setVisibility(View.GONE);
                holder.order.setText("删除");
                break;
        }

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(state, position);
            }
        });

        // 评价
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentListener != null) {
                    commentListener.onCommentClick();
                }
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderMealDetailActivity.class);
                intent.putExtra("OrderID", data.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.arrive_time)
        TextView arriveTime;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.order)
        TextView order;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnCommentClickListener {
        void onCommentClick();
    }

    public void setOnCommentClickListener(OnCommentClickListener listener) {
        commentListener = listener;
    }

    public interface OnClickListener {
        void onClick(int type, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
