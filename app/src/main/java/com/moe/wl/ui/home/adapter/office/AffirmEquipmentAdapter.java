package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.office.EquipmentListBean;

/**
 * 办公室设备列表
 */
public class AffirmEquipmentAdapter extends MyBaseAdapter<EquipmentListBean> {

    private MyCallBack callBack;
    private OnMinusClickListener minusClickListener;
    private OnAddClickListener addClickListener;

    public AffirmEquipmentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_office_affirm_equipment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final EquipmentListBean bean = getItem(position);
        viewHolder.checkbox.setChecked(bean.isCheck());
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.number.setText("" + bean.getCount());
        if (bean.getCount() == 0) {
            viewHolder.minus.setVisibility(View.GONE);
            viewHolder.number.setVisibility(View.GONE);
        } else {
            viewHolder.minus.setVisibility(View.VISIBLE);
            viewHolder.number.setVisibility(View.VISIBLE);
        }

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minusClickListener != null) {
                    if (bean.getCount() > 0) {
                        minusClickListener.onMinusClick(position, bean.getCount() - 1);
                    } else {
                        minusClickListener.onMinusClick(position, 0);
                    }
                }
            }
        });

        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addClickListener != null) {
                    addClickListener.onAddClick(position, bean.getCount() + 1);
                }
            }
        });

        viewHolder.ll_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.cb(position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public CheckBox checkbox;
        private ImageView minus, add;
        private TextView number;
        public LinearLayout ll_all;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ll_all = (LinearLayout) rootView.findViewById(R.id.ll_all);
            this.minus = (ImageView) rootView.findViewById(R.id.minus);
            this.add = (ImageView) rootView.findViewById(R.id.add);
            this.number = (TextView) rootView.findViewById(R.id.number);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.checkbox = (CheckBox) rootView.findViewById(R.id.checkbox);
        }
    }

    public interface MyCallBack {
        void cb(int pos);
    }

    public void setMyCallBack(MyCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnMinusClickListener {
        void onMinusClick(int pos, int count);
    }

    public void setOnMinusClickListener(OnMinusClickListener minusClickListener) {
        this.minusClickListener = minusClickListener;
    }

    public interface OnAddClickListener {
        void onAddClick(int pos, int count);
    }

    public void setOnAddClickListener(OnAddClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

}
