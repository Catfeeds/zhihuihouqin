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
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.bean.SpCheckShopCarBean;

/**
 * Created by 我的电脑 on 2017/9/20 0020.
 */

public class ShopCarListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SpCheckShopCarBean.CartItemsBean> data = new ArrayList<>();
    private boolean isSelect=true;
    private boolean isSelectAll;

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
        if (data != null) {
            viewHolder.setData(data.get(position), position,isSelect);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<SpCheckShopCarBean.CartItemsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void selectAll(){

        notifyDataSetChanged();
    }

    private int seclectPosition;

    class ViewHolder extends RecyclerView.ViewHolder {
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
        int count ;
        private int mPosition;
        private SpCheckShopCarBean.CartItemsBean cartItemsBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            doAdd();
            doMinus();
            select();
        }

        private void select() {
            ivItemSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seclectPosition=mPosition;
                    boolean seclect = data.get(seclectPosition).isSeclect();
                    if(seclect==true){
                        ivItemSelect.setImageResource(R.drawable.unselected);
                    }else{
                        ivItemSelect.setImageResource(R.drawable.selected);
                    }
                    seclect=!seclect;
                    data.get(seclectPosition).setSeclect(seclect);
                    notifyDataSetChanged();
                }
            });
        }

        private void doMinus() {
            tvMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count <= 0) {
                        count = 0;
                    } else {
                        count--;
                    }
                    cartItemsBean.setCount(count);
                    tvCount.setText(count+"");
                    if(listener!=null){
                        listener.onCliickListener(count,mPosition);
                    }
                }
            });

        }

        private void doAdd() {
            tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    cartItemsBean.setCount(count);
                    tvCount.setText(count+"");
                    if(listener!=null){
                        listener.onCliickListener(count,mPosition);
                    }
                }
            });
        }
        public void setData(SpCheckShopCarBean.CartItemsBean cartItemsBean, int position,boolean isSelect) {
            this.cartItemsBean=cartItemsBean;
            this.mPosition = position;
            if (cartItemsBean != null) {
                int count = cartItemsBean.getCount();
                this.count=count;
                SpCheckShopCarBean.CartItemsBean.SkuBean sku = cartItemsBean.getSku();
                String mainimg = sku.getMainimg();//图片
                int price = sku.getPrice();
                String skuname = sku.getSkuname();
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, mainimg, ivPic);
                tvCount.setText(count+"");
                tvDes.setText(skuname);
                if(isSelectAll){
                    cartItemsBean.setSeclect(true);
                }else{
                    cartItemsBean.setSeclect(isSelect);
                }
            }
        }
    }
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onCliickListener(int count,int index);
    }
}
