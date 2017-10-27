package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.PreOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class OrderTimeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private int selectPosition = 0;
    private String s2;
    private List<PreOrderBean.TimelistBean> mList;/*=new ArrayList<>();*/

    public int getmPosition() {
        return selectPosition;
    }

    public OrderTimeAdapter(Context context, List<PreOrderBean.TimelistBean> list, OnItemClickListener listener) {
        this.mContext = context;
        this.mList=list;
        this.listener=listener;
    }

   /* public void setData(List<PreOrderBean.TimelistBean> list) {
        this.mList=list;
        notifyDataSetChanged();
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mList != null) {
            viewHolder.setData(mList.get(position), position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

  /*  public String getTime() {
        String s = s2.substring(2, s2.length());
        return s+"/"+weeks.get(selectPosition);
    }*/

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_workday)
        TextView tvWorkday;
        @BindView(R.id.ll_time)
        LinearLayout llTime;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onItemClickListener(selectPosition);
                    }
                }
            });
        }

        public void setData(PreOrderBean.TimelistBean timelistBean, int position) {
            mPosition=position;
            if(timelistBean!=null){
                String scheduleDate = timelistBean.getScheduleDate();
                String[] split = scheduleDate.split(" ");
                tvWorkday.setText(split[0]);
                if (selectPosition == position) {
                    llTime.setBackgroundColor(Color.parseColor("#00CCFF"));
                    tvWorkday.setTextColor(Color.WHITE);
                } else {
                    llTime.setBackgroundColor(Color.WHITE);
                    tvWorkday.setTextColor(Color.parseColor("#333333"));

                }
            }
        }
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

}
