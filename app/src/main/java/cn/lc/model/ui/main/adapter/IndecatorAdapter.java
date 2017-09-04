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
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class IndecatorAdapter extends RecyclerView.Adapter {
    private Context mContxt;

    public IndecatorAdapter(Context mContxt) {
        this.mContxt = mContxt;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.indecator_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(position);
    }
private int selectPosition=0;
    @Override
    public int getItemCount() {
        return 12;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_indecator)
        TextView tvIndecator;
        @BindView(R.id.view)
        View view;
         private int mPosition;

         ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition=mPosition;
                    notifyDataSetChanged();
                }
            });
        }

         public void setData(int position) {
             this.mPosition = position;
             if(selectPosition==position){
                 tvIndecator.setTextColor(Color.parseColor("#0B8BAE"));
                 view.setBackgroundColor(Color.parseColor("#0B8BAE"));
             }else{
                 tvIndecator.setTextColor(Color.parseColor("#333333"));
                 view.setBackgroundColor(Color.WHITE);
             }
         }
     }
}
