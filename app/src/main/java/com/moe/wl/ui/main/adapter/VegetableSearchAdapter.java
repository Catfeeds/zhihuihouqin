package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.VegetableBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getImg(), h.image, R.mipmap.ic_default_square);

        h.title.setText(data.get(position).getName());
        h.content.setText(data.get(position).getOriginal());
        h.num.setText(data.get(position).getRemain() + "/" + data.get(position).getTotalcount());
        h.price.setText("¥" + data.get(position).getPrice());

        h.item.setOnClickListener(new View.OnClickListener() {
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


    public interface OnClickListener {
        void onClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.item)
        LinearLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
