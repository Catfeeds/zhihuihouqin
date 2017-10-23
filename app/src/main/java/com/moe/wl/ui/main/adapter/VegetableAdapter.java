package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.VegetableBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */
public class VegetableAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<VegetableBean.PageEntity.ListEntity> data;
    private OnAddClickListener addClickListener;
    private OnMinusClickListener minusClickListener;

    public VegetableAdapter(Context context, List<VegetableBean.PageEntity.ListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vegetable, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder h = (ViewHolder) holder;
        final VegetableBean.PageEntity.ListEntity entity = data.get(position);
        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, entity.getImg(), h.image);
        h.title.setText(entity.getName());
        h.content.setText(entity.getOriginal());
        h.price.setText("¥" + entity.getPrice());
        h.num.setText(entity.getRemain() + "/" + entity.getTotalcount());
        if (entity.getNumber() == 0) {
            h.number.setVisibility(View.GONE);
            h.minus.setVisibility(View.GONE);
        } else {
            h.number.setText(entity.getNumber() + "");
            h.number.setVisibility(View.VISIBLE);
            h.minus.setVisibility(View.VISIBLE);
        }

        h.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = entity.getNumber() + 1;
                if (entity.getNumber() >= data.get(position).getRemain()) {
                    ToastUtil.showToast(context, "该菜品已售罄！");
                    return;
                }
                if (addClickListener != null)
                    addClickListener.onAddClick(position, num);
                entity.setNumber(num);
                notifyDataSetChanged();
//                h.number.setText(num + "");
//                h.number.setVisibility(View.VISIBLE);
//                h.minus.setVisibility(View.VISIBLE);
            }
        });

        h.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = entity.getNumber() - 1 <= 0 ? 0 : entity.getNumber() - 1;
                if (num == 0) {
                    h.number.setVisibility(View.GONE);
                    h.minus.setVisibility(View.GONE);
                }
                if (minusClickListener != null)
                    minusClickListener.onMinusClick(position, num);
//                h.number.setText(num + "");
                entity.setNumber(num);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.minus)
        ImageView minus;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.add)
        ImageView add;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnAddClickListener(OnAddClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

    public interface OnAddClickListener {
        void onAddClick(int position, int num);
    }

    public void setOnMinusClickListener(OnMinusClickListener minusClickListener) {
        this.minusClickListener = minusClickListener;
    }

    public interface OnMinusClickListener {
        void onMinusClick(int position, int num);
    }
}
