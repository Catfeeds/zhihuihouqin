package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.ReasonBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/12 0012
 */
public class ReasonAdapter extends BaseAdapter {

    private Context context;
    private List<ReasonBean.ReasonListEntity> data;
    private OnSelectItemListener listener;

    public ReasonAdapter(Context context, List<ReasonBean.ReasonListEntity> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cancel_order_reason, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvReason.setText(data.get(position).getContent());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.radio.isChecked()) {
                    holder.radio.setChecked(false);
                } else {
                    holder.radio.setChecked(true);
                }
                if (null != listener) {
                    if (holder.radio.isChecked()) {
                        listener.onSelect(data.get(position).getId(), true);
                    } else {
                        listener.onSelect(data.get(position).getId(), false);
                    }
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.radio)
        RadioButton radio;
        @BindView(R.id.tv_reason)
        TextView tvReason;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnSelectItemListener(OnSelectItemListener listener) {
        this.listener = listener;
    }

    public interface OnSelectItemListener {
        void onSelect(int id, boolean isSelect);
    }
}
