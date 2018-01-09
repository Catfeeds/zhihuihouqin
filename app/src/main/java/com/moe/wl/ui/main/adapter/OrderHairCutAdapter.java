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
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.CircleImageView;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.activity.me.OrderHairCutDetailActivity;
import com.moe.wl.ui.main.bean.OrderHairCutBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：理发订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderHairCutAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderHairCutBean.OrderlistEntity> data;
    private int state;
    private OnPayClickListener payListener;
    private OnClickListener listener;

    public OrderHairCutAdapter(Context context, List<OrderHairCutBean.OrderlistEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_haircut, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        if (data.get(position).getPhoto() == null || "".equals(data.get(position).getPhoto())) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getPhoto(), holder.header);
        } else {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getPhoto(), holder.header);
        }
        if (data.get(position).getRealname() == null || "".equals(data.get(position).getRealname())) {
            holder.userName.setText("理发师");
        } else {
            holder.userName.setText(data.get(position).getRealname());
        }
        holder.orderNumber.setText("订  单  号：" + data.get(position).getOrdercode());
        holder.time.setText("预约时间：" + data.get(position).getInvitetime());
        holder.arriveTime.setText("下单时间：" + data.get(position).getCreatetime());
        holder.number.setText("支付状态：" + (data.get(position).getPaystatus() == 0 ? "未支付" : "已支付"));

        switch (state) {
            case 0:
                if (data.get(position).getPaystatus() == 0) {
                    holder.pay.setVisibility(View.VISIBLE);
                    if (data.get(position).getCheckstatus() == 0) {
                        holder.pay.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_order_gray_button));
                    } else {
                        holder.pay.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_order_button));
                    }
                } else {
                    holder.pay.setVisibility(View.GONE);
                }
                holder.order.setText("取消预约");
                break;
            case 1:
                holder.pay.setVisibility(View.GONE);
                holder.order.setText("已完成");
                break;
            case 2:
                holder.pay.setVisibility(View.GONE);
                holder.order.setText("立即评价");
                break;
            case 3:
                holder.order.setVisibility(View.GONE);
                break;
            case 4:
                holder.pay.setVisibility(View.GONE);
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

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getCheckstatus() == 1) {
                    Intent intent = new Intent(context, PayFiveJiaoActivity.class);
                    intent.putExtra("from", Constants.HAIRCUTS);
                    intent.putExtra("pay", data.get(position).getPrice());
                    intent.putExtra("orderid", data.get(position).getOrderid() + "");
                    intent.putExtra("ordercode", data.get(position).getOrdercode());
                    intent.putExtra("ordertype", Constants.HAIRCUTS + "");
                    intent.putExtra("time", data.get(position).getCreatetime());
                    context.startActivity(intent);
                }
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderHairCutDetailActivity.class);
                //intent.putExtra("Data", data.get(position));
                intent.putExtra("id",data.get(position).getOrderid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header)
        CircleImageView header;
        @BindView(R.id.user_name)
        TextView userName;
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
        @BindView(R.id.pay)
        TextView pay;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
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

}
