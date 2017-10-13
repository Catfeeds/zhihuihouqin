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
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.CircleImageView;
import com.moe.wl.ui.main.activity.me.OrderExpertDetailActivity;
import com.moe.wl.ui.main.bean.OrderExpertBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：专家订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderExpertAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderExpertBean.OrderlistEntity> data;
    private int state;
    private OnPayClickListener payListener;
    private OnClickListener listener;


    public OrderExpertAdapter(Context context, List<OrderExpertBean.OrderlistEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_medical, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;

        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getPhoto(), holder.header);
        holder.userName.setText(data.get(position).getRealname());
        holder.orderNumber.setText("订  单  号：" + data.get(position).getOrdercode());
        holder.reserveTime.setText("预约时间：" + data.get(position).getScheduledate());
        holder.orderTime.setText("下单时间：" + data.get(position).getCreatetime());
        holder.arriveTime.setText("预  约  号：" + data.get(position).getCreatetime());
        holder.payState.setText("支付状态：" + (data.get(position).getPaystatus() == 0 ? "未支付" : "已支付"));

        switch (state) {
            case 0:
                holder.order.setText("取消预约");
                break;
            case 1:
                holder.order.setText("完成");
                break;
            case 2:
                holder.order.setText("再次预约");
                break;
            case 3:
                holder.order.setText("评价");
                break;
            case 4:
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

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderExpertDetailActivity.class);
                intent.putExtra("OrderID", data.get(position).getId());
                intent.putExtra("Type", 2);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnPayClickListener {
        void onPayClick();
    }

    public void setOnPayClickListener(OnPayClickListener listener) {
        payListener = listener;
    }

    public interface OnClickListener {
        void onClick(int type, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header)
        CircleImageView header;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.reserve_time)
        TextView reserveTime;
        @BindView(R.id.order_time)
        TextView orderTime;
        @BindView(R.id.arrive_time)
        TextView arriveTime;
        @BindView(R.id.pay_state)
        TextView payState;
        @BindView(R.id.order)
        TextView order;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
