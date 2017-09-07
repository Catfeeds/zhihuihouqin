package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.lc.model.R;
import cn.lc.model.ui.main.bean.NutritionBean;

/**
 * 类描述：营养套餐Adapter
 * 作者：Shixhe On 2017/9/5 0005
 */
public class NutritionAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private NutritionBean data;
    private int type;

    public NutritionAdapter(Context context, NutritionBean data, int type) {
        this.data = data;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (type == 0)
            return data.getPage().getList().size() > 4 ? 4 : data.getPage().getList().size();
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
            convertView = inflater.inflate(R.layout.item_nutrition, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.from = (TextView) convertView.findViewById(R.id.from);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(data.getPage().getList().get(position).getMTitle());
        holder.time.setText(data.getPage().getList().get(position).getMCreateTime());
        holder.from.setText(data.getPage().getList().get(position).getMSource());

        return convertView;
    }

    class ViewHolder {
        TextView title, time, from;
    }
}
