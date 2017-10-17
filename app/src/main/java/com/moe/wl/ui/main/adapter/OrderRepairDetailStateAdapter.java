package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.OrderRepairsDetailTwoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/17 0017
 */
public class OrderRepairDetailStateAdapter extends BaseAdapter {

    private Context context;
    private List<OrderRepairsDetailTwoBean.DetailEntity> data;

    public OrderRepairDetailStateAdapter(Context context, List<OrderRepairsDetailTwoBean.DetailEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_repair_state, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.time.setText(data.get(position).getCreatetime());
        holder.content.setText(data.get(position).getDetail());

        if (position == 0) {
            holder.view1.setVisibility(View.INVISIBLE);
            holder.view2.setVisibility(View.VISIBLE);
        } else if (position == data.size() - 1) {
            holder.view1.setVisibility(View.VISIBLE);
            holder.view2.setVisibility(View.INVISIBLE);
        } else {
            holder.view1.setVisibility(View.VISIBLE);
            holder.view2.setVisibility(View.VISIBLE);
        }

        switch (data.get(position).getStatus()) {
            case 1: // 1: 下单
                holder.title.setText("下单");
                break;
            case 2: // 2: 审核中
                holder.title.setText("审核中");
                break;
            case 3: // 3：审核通过
                holder.title.setText("审核通过");
                break;
            case 4: // 4：审核不通过
                holder.title.setText("审核不通过");
                break;
            case 5: // 5：派工
                holder.title.setText("派工");
                break;
            case 6: // 6：接单
                holder.title.setText("接单");
                break;
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.view1)
        View view1;
        @BindView(R.id.view2)
        View view2;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
