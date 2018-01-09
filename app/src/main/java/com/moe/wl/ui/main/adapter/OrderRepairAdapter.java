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
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.me.OrderRepairDetailActivity;
import com.moe.wl.ui.main.bean.OrderRepairBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：报修订单Adapter
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderRepairAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<OrderRepairBean.OrderlistEntity> data;
    private int state;
    private OnClickListener listener;


    public OrderRepairAdapter(Context context, List<OrderRepairBean.OrderlistEntity> data, int state) {
        this.context = context;
        this.data = data;
        this.state = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_repair, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;

        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getMenderphoto(), holder.image, R.mipmap.ic_default_square);
        holder.orderNumber.setText("订  单  号：" + data.get(position).getOrdercode());
        holder.time.setText("下单时间：" + data.get(position).getCreatetime());
        holder.repairType.setText("维修类型：" + data.get(position).getItemname());
        holder.address.setText("服务地址：" + data.get(position).getServiceplace());
        holder.callTime.setText("上门时间：" + data.get(position).getInvitetime());

        switch (state) {
            case 0:
                holder.onLine.setVisibility(View.GONE);
                holder.order1.setText("取消订单");
                break;
            case 1:
                holder.onLine.setVisibility(View.GONE);
                holder.order1.setText("拨打电话");
                break;
            case 2:
                holder.onLine.setVisibility(View.GONE);
                holder.order1.setText("立即评价");
                break;
            case 3:
                holder.onLine.setVisibility(View.GONE);
                holder.order1.setText("再次预订");
                break;
            case 4:
                holder.onLine.setVisibility(View.GONE);
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

        // TODO 在线沟通
        holder.onLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderRepairDetailActivity.class);
                LogUtils.i("orderid=="+data.get(position).getOrderid());
                intent.putExtra("OrderID", data.get(position).getOrderid());
                intent.putExtra("State", data.get(position).getOrderstatus());
                intent.putExtra("Mobile", data.get(position).getMendermobile());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.order_number)
        TextView orderNumber;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.repair_type)
        TextView repairType;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.call_time)
        TextView callTime;
        @BindView(R.id.order_1)
        TextView order1;
        @BindView(R.id.on_line)
        TextView onLine;
        @BindView(R.id.item)
        LinearLayout item;

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
