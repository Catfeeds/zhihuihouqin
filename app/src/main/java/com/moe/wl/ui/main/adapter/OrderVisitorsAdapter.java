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
import com.moe.wl.ui.main.bean.OrderVisitorsListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：工作餐订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderVisitorsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderVisitorsListBean.OrderlistEntity> data;
    private OnClickListener listener;
    private int state;

    public OrderVisitorsAdapter(Context context, List<OrderVisitorsListBean.OrderlistEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_visitors, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;

//        holder.orderNumber.setText("订单号：" + data.get(position).get);
//        holder.name.setText("会议名称：" + data.get(position).getConferencename());
//        if (data.get(position).getConferencetype() == 1) {
//            holder.type.setText("会议类型：文艺会议");
//        }

        switch (state) {
            case 0:
                holder.comment.setVisibility(View.GONE);
                holder.order.setText("取消订单");
                break;
            case 1:
                holder.comment.setText("已完成");
                holder.comment.setVisibility(View.VISIBLE);
                holder.order.setText("会议加时");
                break;
            case 2:
                holder.comment.setText("支付");
                holder.comment.setVisibility(View.VISIBLE);
                holder.order.setText("再次预订");
                break;
            case 3:
                holder.comment.setVisibility(View.GONE);
                holder.order.setText("立即评价");
                break;
            case 4:
                holder.comment.setVisibility(View.GONE);
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

        // 评价
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 2) {
                    // 支付
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

    public interface OnClickListener {
        void onClick(int type, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.room_number)
        TextView roomNumber;
        @BindView(R.id.arrive_name)
        TextView arriveName;
        @BindView(R.id.mobile)
        TextView mobile;
        @BindView(R.id.people)
        TextView people;
        @BindView(R.id.arrive_time)
        TextView arriveTime;
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
}
