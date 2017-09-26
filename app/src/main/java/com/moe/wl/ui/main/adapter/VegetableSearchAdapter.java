package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.ui.main.bean.VegetableBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/19 0019
 */
public class VegetableSearchAdapter extends RecyclerView.Adapter {

    private List<VegetableBean.PageEntity.ListEntity> data;
    private Context context;
    private OnClickListener listener;

    public VegetableSearchAdapter(Context context, List<VegetableBean.PageEntity.ListEntity> data, OnClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vegetable_search, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder h = (ViewHolder) holder;
        h.content.setText(data.get(position).getName());
        h.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.ll_item)
        LinearLayout ll_item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }

}
