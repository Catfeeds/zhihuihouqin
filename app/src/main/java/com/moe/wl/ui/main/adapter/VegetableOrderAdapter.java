package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.ui.main.bean.VegetableBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/18 0018
 */
public class VegetableOrderAdapter extends BaseAdapter {

    private Context context;
    private List<VegetableBean.PageEntity.ListEntity> data;

    public VegetableOrderAdapter(Context context, List<VegetableBean.PageEntity.ListEntity> data) {
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
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_vegetable_order, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.vegetableName.setText(data.get(position).getName());
        holder.vegetableNumber.setText("x" + data.get(position).getNumber() + "");
        holder.vegetablePrice.setText("¥" + data.get(position).getPrice());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.vegetable_name)
        TextView vegetableName;
        @BindView(R.id.vegetable_number)
        TextView vegetableNumber;
        @BindView(R.id.vegetable_price)
        TextView vegetablePrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
