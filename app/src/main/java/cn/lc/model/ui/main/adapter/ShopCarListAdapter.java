package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/9/20 0020.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ShopCarListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.shop_car_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return 2;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item_select)
        ImageView ivItemSelect;
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_minus)
        TextView tvMinus;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_add)
        TextView tvAdd;
         int count=0;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            doAdd();
            doMinus();
        }

         private void doMinus() {
             tvMinus.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(count<=0){
                         count=0;
                     }else{
                         count--;
                     }
                     tvCount.setText("");
                 }
             });

         }

         private void doAdd() {
             tvAdd.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     count++;
                     tvCount.setText("");
                 }
             });
         }
     }
}
