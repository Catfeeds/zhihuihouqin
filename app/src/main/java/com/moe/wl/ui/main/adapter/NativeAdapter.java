package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.NationslistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class NativeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<NationslistBean.NationlistBean> mList=new ArrayList<>();

    public NativeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.position_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mList != null) {
            viewHolder.setDate(mList.get(position), position);

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private int selectPosition = -1;

    public void setData(List<NationslistBean.NationlistBean> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    if (lister != null) {
                        lister.itemClickLister(selectPosition);
                    }
                    notifyDataSetChanged();

                }
            });
        }

        public void setDate(NationslistBean.NationlistBean positionlistBean, int position) {
            this.mPosition = position;
            tvPosition.setText(positionlistBean.getName());
            if (selectPosition == position) {
                ivSelect.setVisibility(View.VISIBLE);
            } else {
                ivSelect.setVisibility(View.GONE);
            }
        }
    }

    private OnClickLister lister;

    public void setLister(OnClickLister lister) {
        this.lister = lister;
    }

    public interface OnClickLister {
        void itemClickLister(int position);
    }
}
