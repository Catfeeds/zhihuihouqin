package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.ReasonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/12 0012
 */
public class ReasonAdapter extends BaseAdapter {

    private Context context;
    private List<ReasonBean.ServicereasonlistEntity> data;
    private OnSelectItemListener listener;

    public ReasonAdapter(Context context, List<ReasonBean.ServicereasonlistEntity> data) {
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
        if (data != null) {
            holder.setData(data.get(position), position);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.cb_select)
        CheckBox checkBox;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = checkBox.isChecked();
                    if (listener != null) {
                        listener.onSelect(data.get(mPosition).getId(), data.get(mPosition).getReason(), checked);
                    }
                }
            });

        }

        public void setData(ReasonBean.ServicereasonlistEntity reasonListEntity, int position) {
            this.mPosition = position;
            if (reasonListEntity != null) {
                checkBox.setText(reasonListEntity.getReason());
            }
        }
    }

    public void setOnSelectItemListener(OnSelectItemListener listener) {
        this.listener = listener;
    }

    public interface OnSelectItemListener {
        void onSelect(int id, String content, boolean isSelect);
    }
}
