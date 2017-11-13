package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.ClothBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/8/25 0025.
 */

public class ConfirmDryCleanOrderAdapter extends BaseAdapter {
    private List<ClothBean.PageEntity.ListEntity> mList = new ArrayList();
    private Context mContent;

    public ConfirmDryCleanOrderAdapter(Context mContent) {
        this.mContent = mContent;
    }

    public void setList(List<ClothBean.PageEntity.ListEntity> list) {
        mList.clear();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCount() != 0) {
                mList.add(list.get(i));
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dry_cleaner_order_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mList != null && mList.size() > 0) {
            viewHolder.setData(mList.get(position), position);
        }
        if (mList.get(position).getRemark() != null && mList.get(position).getRemark().length() > 0) {
            viewHolder.remark.setVisibility(View.VISIBLE);
            viewHolder.remark.setText("备注：" + mList.get(position).getRemark());
        } else {
            viewHolder.remark.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.rl_item)
        LinearLayout rlItem;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.remark)
        TextView remark;

        private int mPosition;
        private ClothBean.PageEntity.ListEntity listBean;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(ClothBean.PageEntity.ListEntity listBean, int position) {
            this.mPosition = position;
            this.listBean = listBean;
            if (listBean != null) {
                if (listBean.getCount() > 0) {
                    tvCategory.setText(listBean.getName());
                    tvCount.setText("x" + listBean.getCount());
                    tvPrice.setText("¥" + listBean.getPrice());
                } else {
                    rlItem.setVisibility(View.GONE);
                }
            }
        }
    }
}
