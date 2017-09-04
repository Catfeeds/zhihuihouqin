package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class OrderTimeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private int mPosition=-1;

    public OrderTimeAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mPosition=position;
            }
        });
        //viewHolder.setData(mPosition,position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 9;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_workday)
        TextView tvWorkday;
        @BindView(R.id.tv_day)
        TextView tvDay;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

       /* public void setData(int mPosition, int position) {
            for (int i = 0; i < getItemCount(); i++) {
                if(mPosition==i){
                    tvWorkday.setTextColor(Color.parseColor("#00aa00"));
                    tvDay.setTextColor(Color.parseColor("#00aa00"));
                }else{
                    tvWorkday.setTextColor(Color.parseColor("#333333"));
                    tvDay.setTextColor(Color.parseColor("#333333"));
                }

            }
            notifyDataSetChanged();
        }*/
    }

}
