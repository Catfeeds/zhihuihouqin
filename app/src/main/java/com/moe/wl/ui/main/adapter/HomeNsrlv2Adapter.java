package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.me.OrderBookDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderConferenceDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderDryDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderExpertDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderHairCutDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderMealDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderOfficeDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderRepairDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderVegetableDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderVisitorsDetailActivity;
import com.moe.wl.ui.main.activity.me.OrderWaterDetailActivity;
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
        final String type = data.get(position).getType();
        switch (type){
            case "1":
                holder.tvContentDes.setText("物业报修");
                break;
            case "3":
                holder.tvContentDes.setText("图书借阅");
                break;
            case "4":
                holder.tvContentDes.setText("餐费充值");
                break;
            case "6":
                holder.tvContentDes.setText("预约理发");
                break;
            case "7":
                holder.tvContentDes.setText("会议室预定");
                break;
            case "8":
                holder.tvContentDes.setText("办公用品");
                break;
            case "9":
                holder.tvContentDes.setText("净菜预定");
                break;
            case "14":
                holder.tvContentDes.setText("专家坐诊");
                break;
            case "15":
                holder.tvContentDes.setText("工作餐");
                break;
            case "16":
                holder.tvContentDes.setText("洗衣店");
                break;
            case "17":
                holder.tvContentDes.setText("医疗服务预约挂号挂号单号");
                break;
            case "18":
                holder.tvContentDes.setText("订水服务");
                break;
            case "20":
                holder.tvContentDes.setText("用户钱包");
                break;
            case "21":
                holder.tvContentDes.setText("来访人员");
                break;

        }
        /*if (!TextUtils.isEmpty(data.get(position).getMendcontent())) {
            holder.tvContentDes.setText(data.get(position).getMendcontent());
        } else {
            holder.tvContentDes.setText("暂无名称");
        }*/
        holder.tvSecondrvTime.setText(data.get(position).getCreatetime());
        holder.tvRoomNum.setText(data.get(position).getRemark());
//        holder.tvName.setText(data.get(position).getMendername());
        if (!TextUtils.isEmpty(data.get(position).getUsername())) {
            holder.tvName.setText(data.get(position).getUsername());
        } else {
            holder.tvName.setText("暂无姓名");
        }
        switch (data.get(position).getPaystatus()) {
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
            default:
                holder.tvDaili.setText("【待接单】");
                break;
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!OtherUtils.isAuth()){
                    // 没有认证
                    OtherUtils.showAuth(context);
                    return;
                }
                /*1:网络报修，3：图书借阅，4：餐费充值，6：预约理发，7会议室预定8：办公用品，
                9：订餐，14：医疗服务专家坐诊，15：工作餐 16：干洗 17: 医疗服务预约挂号挂号单号，
                18：订水服务，20：用户钱包*/
                Intent intent=null;
                switch (type){
                    case "1"://网络报修
                        intent = new Intent(context, OrderRepairDetailActivity.class);
                        intent.putExtra("OrderID", data.get(position).getOid());
                        intent.putExtra("State", data.get(position).getPaystatus());
                        context.startActivity(intent);
                        break;
                    case "3"://图书借阅
                        intent = new Intent(context, OrderBookDetailActivity.class);
                        intent.putExtra("id",data.get(position).getOid()+"");
                        context.startActivity(intent);
                        break;
                    case "4"://餐费充值
                        //intent = new Intent(context, OrderBookDetailActivity.class);
                        break;
                    case "6"://预约理发
                        intent = new Intent(context, OrderHairCutDetailActivity.class);
                        intent.putExtra("id",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "7"://会议室预定
//                      //OrderConferenceDetailActivity
                        intent = new Intent(context, OrderMealDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "8"://办公用品
                        intent = new Intent(context, OrderOfficeDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "9"://净菜预定
                        intent = new Intent(context, OrderVegetableDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "14"://医疗服务专家坐诊
                        intent = new Intent(context, OrderExpertDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "15"://工作餐
                        intent = new Intent(context, OrderMealDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "16"://干洗
                        intent = new Intent(context, OrderDryDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "17"://医疗服务预约挂号挂号单号
                        intent = new Intent(context, OrderExpertDetailActivity.class);
                        break;
                    case "18"://订水服务
                        intent = new Intent(context, OrderWaterDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        context.startActivity(intent);
                        break;
                    case "20"://用户钱包
                        //intent = new Intent(context, OrderW.class);
                        break;
                    case "21":
                        intent = new Intent(context, OrderVisitorsDetailActivity.class);
                        intent.putExtra("OrderID",data.get(position).getOid());
                        intent.putExtra("state",9);
                        context.startActivity(intent);
                        break;

                }

//                intent.putExtra("Mobile", data.get(position).getMendermobile());

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
