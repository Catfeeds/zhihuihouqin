package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.lc.model.R;
import cn.lc.model.ui.main.bean.LabellingBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class LabellingAdapter extends BaseAdapter {

    private LabellingBean data;
    private LayoutInflater inflater;
    private Context context;

    public LabellingAdapter(Context context, LabellingBean data) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.getTagList().size();
    }

    @Override
    public Object getItem(int position) {
        return data.getTagList().get(position);
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
            convertView = inflater.inflate(R.layout.item_labelling, null);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(data.getTagList().get(position).getTagName());
        if (data.getTagList().get(position).isSelect()) {
            holder.text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_complain_labe_true));
        } else {
            holder.text.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_complain_labe_false));
        }

        return convertView;
    }

    class ViewHolder {
        TextView text;
    }

}
