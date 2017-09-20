package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
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
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.activity.OfficeSupplies.SpDetailActivity;
import cn.lc.model.ui.main.bean.OfficeIndexBean;
import cn.lc.model.ui.main.bean.ProductCategoryBean;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class SpAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ProductCategoryBean.PageBean.ListBean> data=new ArrayList<>();

    public SpAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.office_sp_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(data!=null&&data.size()>0){
            viewHolder.setData(data.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }
        return 0;
    }

    public void setData(List<ProductCategoryBean.PageBean.ListBean> data) {
        this.data = data;
        LogUtils.i("adapter========="+data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_sp_pic)
        ImageView ivSpPic;
        @BindView(R.id.tv_sp_des)
        TextView tvSpDes;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        private int mPosition;
        private int id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SpDetailActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("position",mPosition);
                    mContext.startActivity(intent);
                }
            });
        }
         public void setData(ProductCategoryBean.PageBean.ListBean newProductListBean, int position) {
            this.mPosition=position;
             if(newProductListBean!=null){
                 id = newProductListBean.getId();
                 GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,newProductListBean.getProductImg(),
                         ivSpPic);
                 tvSpDes.setText(newProductListBean.getProductname());
                 tvPrice.setText("￥"+newProductListBean.getPrice());
             }else{
                 LogUtils.i("newProductListBean为空啊");
             }
         }
    }
}
