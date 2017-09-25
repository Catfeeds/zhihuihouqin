package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.bean.SpCheckShopCarBean;

/**
 * Created by 我的电脑 on 2017/9/23 0023.
 */

public class SpOrderAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<SpCheckShopCarBean.CartItemsBean> mCartItemLists;

    public SpOrderAdapter(Context context, List<SpCheckShopCarBean.CartItemsBean> cartItemLists) {
        this.mCartItemLists = cartItemLists;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.order_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(mCartItemLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mCartItemLists.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_sum)
        TextView tvSum;
        private SpCheckShopCarBean.CartItemsBean data;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(SpCheckShopCarBean.CartItemsBean data) {
            this.data = data;
            SpCheckShopCarBean.CartItemsBean.SkuBean sku = data.getSku();
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                    sku.getMainimg(),ivPic);
            tvDes.setText(sku.getCataName());
            tvCount.setText("x"+data.getCount());
            tvSum.setText("￥"+sku.getPrice());

        }
    }
}
