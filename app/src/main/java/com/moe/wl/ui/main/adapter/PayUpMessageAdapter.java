package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.ui.main.bean.PayUpMessageBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * 类描述：缴费列表Adapter
 * 作者：Shixhe On 2017/9/23 0023
 */
public class PayUpMessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<PayUpMessageBean.PageEntity.ListEntity> data;
    private OnItemClickListener listener;

    public PayUpMessageAdapter(Context context, List<PayUpMessageBean.PageEntity.ListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pay_up_message, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        StringBuffer title = new StringBuffer();
        switch (data.get(position).getOrdertype()) {
            case 1: // 网络报修
                title.append("网络报修");
                break;

            case 2: // 医疗服务
                title.append("医疗服务");
                break;

            case 3: // 图书借阅
                title.append("图书借阅");
                break;

            case 4: // 餐费充值
                title.append("餐费充值");
                break;

            case 5: // 团队活动
                title.append("团队活动");
                break;

            case 6: // 美容美发
                title.append("美容美发");
                break;

            case 7: // 干洗店
                title.append("干洗店");
                break;

            case 8: // 办公用品
                title.append("办公用品");
                break;

            case 9: // 订餐
                title.append("订餐");
                break;

            case 10: // 营养套餐
                title.append("营养套餐");
                break;

            case 11: // 信息公告
                title.append("信息公告");
                break;

            case 12: // 意见反馈
                title.append("意见反馈");
                break;
        }

        holder.payTo.setText(title.toString());
        int status = data.get(position).getStatus();
        if (status == 0) {
            holder.title.setText(title.toString() + "付款中");
        } else if (status == 1) {
            holder.title.setText(title.toString() + "成功");
        } else if (status == 2) {
            holder.title.setText(title.toString() + "失败");
        }

        String payWay = "";
        switch (data.get(position).getPaytype()) {
            case 1: // 支付宝
                payWay = "支付宝";
                break;
            case 2: // 微信
                payWay = "微信";
                break;

            case 3: // 个人余额对私
                payWay = "个人余额对私";
                break;

            case 4: // 集体余额对公
                payWay = "集体余额对公";
                break;

            case 5: // 代金券对私
                payWay = "代金券对私";
                break;

            case 6: // 代金券对公
                payWay = "代金券对公";
                break;
        }
        holder.payWay.setText("支付方式：" + payWay);
        holder.price.setText(data.get(position).getPaymoney() + "");
        holder.timeDate.setText(data.get(position).getPaytime());
        holder.time.setText(data.get(position).getCreatetime());
        holder.payState.setText("缴费说明：" + data.get(position).getPayremark());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time_date)
        TextView timeDate;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.pay_way)
        TextView payWay;
        @BindView(R.id.pay_to)
        TextView payTo;
        @BindView(R.id.pay_state)
        TextView payState;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
