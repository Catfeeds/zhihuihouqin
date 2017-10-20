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
import com.moe.wl.ui.main.activity.me.OrderRepairDetailActivity;
import com.moe.wl.ui.main.bean.HomePageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeNsrlv2Adapter extends RecyclerView.Adapter {

    private Context context;
    private List<HomePageBean.BxwxOrderList> data;

    public HomeNsrlv2Adapter(Context context, List<HomePageBean.BxwxOrderList> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv2_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.tvContentDes.setText(data.get(position).getMendcontent());
        holder.tvSecondrvTime.setText(data.get(position).getCreatetime());
        holder.tvRoomNum.setText(data.get(position).getServiceplace());
        holder.tvName.setText(data.get(position).getMendername());
        switch (data.get(position).getOrderstatus()) {
            case 1:
                holder.tvDaili.setText("【待接单】");
                break;
            case 2:
                holder.tvDaili.setText("【已接单】");
                break;
            case 3:
                holder.tvDaili.setText("【已完成】");
                break;
            case 4:
                holder.tvDaili.setText("【待评价】");
                break;
            case 5:
                holder.tvDaili.setText("【已取消】");
                break;
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderRepairDetailActivity.class);
                intent.putExtra("OrderID", data.get(position).getId());
                intent.putExtra("State", data.get(position).getOrderstatus());
//                intent.putExtra("Mobile", data.get(position).getMendermobile());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content_des)
        TextView tvContentDes;
        @BindView(R.id.tv_secondrv_time)
        TextView tvSecondrvTime;
        @BindView(R.id.tv_room_num)
        TextView tvRoomNum;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_daili)
        TextView tvDaili;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
