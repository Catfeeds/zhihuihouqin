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
import com.moe.wl.ui.main.activity.me.OrderVisitorsDetailActivity;
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

        holder.orderNumber.setText("订单号：" + data.get(position).getOrdercode());
        holder.time.setText("下单时间：" + data.get(position).getCreatetime());
        holder.name.setText("被访人员：" + data.get(position).getRealname());
        holder.roomNumber.setText("房间号：" + data.get(position).getRoomnum());
        holder.arriveName.setText("来访人员：" + data.get(position).getVname());
        holder.mobile.setText("电话：" + data.get(position).getVmobile());
        holder.people.setText("随行人数：" + data.get(position).getVpnum());
        switch (data.get(position).getVisitperiod()) {
            case 1:
                holder.arriveTime.setText("来访时间：一次");
                break;
            case 2:
                holder.arriveTime.setText("来访时间：一星期");
                break;
            case 3:
                holder.arriveTime.setText("来访时间：半个月");
                break;
            case 4:
                holder.arriveTime.setText("来访时间：长期");
                break;
            default:
                holder.arriveTime.setText("来访时间：无");
                break;
        }

        switch (state) {
            case 0:
                holder.comment.setVisibility(View.GONE);
                holder.order.setText("取消订单");
                break;
//            case 1:
//                holder.comment.setVisibility(View.GONE);
//                holder.order.setText("会议加时");
//                break;
            case 2:
                holder.comment.setVisibility(View.GONE);
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

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderVisitorsDetailActivity.class);
                intent.putExtra("Data", data.get(position));
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
