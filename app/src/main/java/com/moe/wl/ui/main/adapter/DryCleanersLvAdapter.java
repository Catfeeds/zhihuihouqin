package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

public class DryCleanersLvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ClothBean.PageEntity.ListEntity> lists = new ArrayList<>();

    public DryCleanersLvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<ClothBean.PageEntity.ListEntity> data) {
        this.lists = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dry_cleaners_lv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (lists != null && lists.size() > 0) {
            viewHolder.setData(lists.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_yiwu)
        TextView tvYiwu;
        @BindView(R.id.tv_money1)
        TextView tvMoney1;
        @BindView(R.id.iv_minus1)
        ImageView ivMinus1;
        @BindView(R.id.tv_count1)
        TextView tvCount1;
        @BindView(R.id.iv_add1)
        ImageView ivAdd1;
        @BindView(R.id.ll_remark)
        LinearLayout llRemark;
        @BindView(R.id.et_remark)
        EditText remark;
        private int count = 0;
        private ClothBean.PageEntity.ListEntity listBean;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ivAdd1.setOnClickListener(this);
            ivMinus1.setOnClickListener(this);
            remark.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    listBean.setRemark(s.toString());
                    if (etListener != null) {
                        etListener.etlisten(mPosition, s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_add1:
                    count = listBean.getCount();
                    count++;
                    if (addListener != null) {
                        addListener.addClick(count, mPosition);
                    }
                    isHint(count);
                    break;
                case R.id.iv_minus1:
                    count = listBean.getCount();
                    if (count <= 0) {
                        count = 0;
                    } else {
                        count--;
                    }
                    if (minusListener != null) {
                        minusListener.minusClick(count, mPosition);
                    }
                    isHint(count);
                    break;
            }
            tvCount1.setText(count + "");
        }


        public void setData(ClothBean.PageEntity.ListEntity listBean, int position) {

            this.mPosition = position;
            if (listBean != null) {
                this.listBean = listBean;
                tvYiwu.setText(listBean.getName());
                tvMoney1.setText("￥" + listBean.getPrice());
                int count = listBean.getCount();
                tvCount1.setText(count + "");
                if (count > 0) {
                    remark.setText(listBean.getRemark());
                } else {
                    remark.setText("");
                }
                isHint(count);

            }
        }

        public void isHint(int count) {
            if (count > 0) {
                llRemark.setVisibility(View.VISIBLE);
            } else {
                llRemark.setVisibility(View.GONE);
            }
        }
    }

    private OnAddClickListener addListener;
    private OnMinusClickListener minusListener;

    public void setAddListener(OnAddClickListener addListener) {
        this.addListener = addListener;
    }

    public void setMinusListener(OnMinusClickListener minusListener) {
        this.minusListener = minusListener;
    }

    public interface OnAddClickListener {
        void addClick(int count, int position);
    }

    public interface OnMinusClickListener {
        void minusClick(int count, int position);
    }

    private EtListener etListener;

    public void setEtListener(EtListener etListener) {
        this.etListener = etListener;
    }

    public interface EtListener {
        void etlisten(int position, String content);
    }
}
