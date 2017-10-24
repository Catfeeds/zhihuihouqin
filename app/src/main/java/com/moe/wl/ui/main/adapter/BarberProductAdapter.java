package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.HairStyleDetailActivity;
import com.moe.wl.ui.main.bean.WorklistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/8/22 0022.
 */

public class BarberProductAdapter extends BaseAdapter {
    private  Context mContext;
    private List<WorklistBean> data=new ArrayList<>();
    private int id;

    public BarberProductAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    public int getCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.barber_product_grid_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WorklistBean worklistBean = data.get(position);
        viewHolder.setData(worklistBean);

        return convertView;
    }

    public void setData(List<WorklistBean> data) {
        this.data = data;
        LogUtils.i("data:=="+data.size());
        notifyDataSetChanged();
    }

    public void setMoreData(List<WorklistBean> moreData) {
        this.data = moreData;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.iv_product_photo)
        ImageView ivProductPhoto;
        @BindView(R.id.tv_product_des)
        TextView tvProductDes;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        private WorklistBean data;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HairStyleDetailActivity.class);
                    intent.putExtra("workid", id);
                    intent.putExtra("img",data.getDetailimg());
                    intent.putExtra("price",data.getPrice());
                    intent.putExtra("name",data.getName());
                    intent.putExtra("brief",data.getBrief());
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(WorklistBean worklistBean) {
            this.data = worklistBean;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, worklistBean.getDetailimg(),ivProductPhoto);
            tvProductDes.setText(worklistBean.getName());
            LogUtils.i("worklistbean===" + worklistBean.getName() + "   " + worklistBean.getPrice());
            tvMoney.setText("￥" + worklistBean.getPrice());
            id = worklistBean.getId();
        }
    }
}
