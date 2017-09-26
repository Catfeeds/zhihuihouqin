package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class CategoryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> category = new ArrayList<>();

    public CategoryAdapter(Context mContext, List<String> category) {
        this.mContext = mContext;
        this.category = category;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sp_category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        if (category != null) {
            viewHolder.setData(category.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if (category != null) {
            return category.size();
        }
        return 0;
    }

    private int selectPosition = 0;

    public void setSelected(int selected) {
        this.selectPosition = selected;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category)
        TextView tvCategory;
         @BindView(R.id.ll_category)
         LinearLayout llCategory;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition=mPosition;
                    Log.e("mPosition",mPosition+"");
                    notifyDataSetChanged();
                }
            });
        }

        public void setData(String s, int position) {
            this.mPosition=position;
            tvCategory.setText(s);
            if(selectPosition==position){
                tvCategory.setTextColor(Color.WHITE);
                llCategory.setBackgroundColor(Color.parseColor("#0B8BAE"));
            }else{
                tvCategory.setTextColor(Color.parseColor("#333333"));
                llCategory.setBackgroundColor(Color.WHITE);
            }
        }
    }
}
