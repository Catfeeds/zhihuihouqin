package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.OfficeSupplies.SpDetailActivity;
import com.moe.wl.ui.main.bean.OfficeIndexBean;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class OfficeSpAdapter extends MyBaseAdapter<OfficeIndexBean.NewProductListBean> {


    public OfficeSpAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.office_sp_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final OfficeIndexBean.NewProductListBean bean = getItem(position);

        if (position%2==0){
            viewHolder.line2.setVisibility(View.GONE);
            viewHolder.line3.setVisibility(View.VISIBLE);
        }else{
            viewHolder.line2.setVisibility(View.VISIBLE);
            viewHolder.line3.setVisibility(View.GONE);
        }

        if (position==0 || position==1){
            viewHolder.line1.setVisibility(View.GONE);
        }else{
            viewHolder.line1.setVisibility(View.VISIBLE);
        }

        if (bean != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(getContext(), bean.getProductImg(), viewHolder.iv_sp_pic,R.mipmap.ic_default_book);
            viewHolder.tv_sp_des.setText(bean.getProductname());
            if (TextUtils.isEmpty( bean.getPrice())){
                viewHolder.tv_price.setText("暂无价格数据");
            }else{
                viewHolder.tv_price.setText("￥" + bean.getPrice());
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SpDetailActivity.class);
                intent.putExtra("id",bean.getId());
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public View line1;
        public View line2;
        public View line3;
        public ImageView iv_sp_pic;
        public TextView tv_sp_des;
        public TextView tv_price;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.line1 = (View) rootView.findViewById(R.id.line1);
            this.line2 = (View) rootView.findViewById(R.id.line2);
            this.line3 = (View) rootView.findViewById(R.id.line3);
            this.iv_sp_pic = (ImageView) rootView.findViewById(R.id.iv_sp_pic);
            this.tv_sp_des = (TextView) rootView.findViewById(R.id.tv_sp_des);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        }

    }
}
