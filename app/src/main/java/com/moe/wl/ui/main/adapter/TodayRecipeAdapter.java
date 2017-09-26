package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.NutritionBean;

/**
 * 类描述：每日食谱Adapter
 * 作者：Shixhe On 2017/9/5 0005
 */
public class TodayRecipeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private NutritionBean data;
    private int type;

    public TodayRecipeAdapter(Context context, NutritionBean data, int type) {
        this.data = data;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (type == 0) {
            return data.getPage().getList().size() > 4 ? 4 : data.getPage().getList().size();
        }
        return data.getPage().getList().size();
    }

    @Override
    public Object getItem(int position) {
        return data.getPage().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_today_recipe, null);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.content.setText(data.getPage().getList().get(position).getMTitle());

        return convertView;
    }

    class ViewHolder {
        TextView content;
    }

}
