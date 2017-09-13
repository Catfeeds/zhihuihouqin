package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;

/**
 * Created by 我的电脑 on 2017/8/25 0025.
 */

public class DryCleanersLvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<OrderDryCleanBean.PageBean.ListBean> lists = new ArrayList<>();

    public DryCleanersLvAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<OrderDryCleanBean.PageBean.ListBean> data) {
        this.lists = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dry_cleaners_lv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(lists!=null&&lists.size()>0){
            viewHolder.setData(lists.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if (lists != null && lists.size() > 0) {
            return lists.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        private  int count;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            count = Integer.parseInt(tvCount1.getText().toString().trim());
            if (count > 0) {
                ivMinus1.setEnabled(true);
            } else {
                ivMinus1.setEnabled(false);
            }
            ivAdd1.setOnClickListener(this);
            ivMinus1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.iv_add1:
                    count++;
                    break;
                case R.id.iv_minus1:
                    count--;
                    break;
            }
            tvCount1.setText(count + "");
        }


        public void setData(OrderDryCleanBean.PageBean.ListBean listBean, int position) {
            if(listBean!=null){
                tvYiwu.setText(listBean.getName());
                tvMoney1.setText("￥"+listBean.getPrice());
            }
        }
    }
}
