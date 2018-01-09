package com.moe.wl.ui.home.adapter.saving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;

import java.util.List;

/**
 * 资讯列表
 */
public class InformationAdapter extends MyBaseAdapter<SaveHomeListBean.NewsBean> {

    public InformationAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_nsrlv1_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        List<SaveHomeListBean.NewsBean> itemList = getItemList();
        if(itemList!=null&&itemList.size()>0){
            SaveHomeListBean.NewsBean dataBean = itemList.get(position);
            viewHolder.setData(dataBean,position);
        }


        return convertView;
    }

    class ViewHolder {
        public View rootView;
        /*public ImageView iv_icon;
        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_addreass;*/

        ImageView ivFirstrvLogo;

        TextView tvFirstrvTitle;

        TextView tvFirstrvTime;

        TextView tvFirstrvDes;

        RelativeLayout item;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ivFirstrvLogo = (ImageView) rootView.findViewById(R.id.iv_firstrv_logo);
            this.tvFirstrvTitle = (TextView) rootView.findViewById(R.id.tv_firstrv_title);
            this.tvFirstrvTime = (TextView) rootView.findViewById(R.id.tv_firstrv_time);
            this.tvFirstrvDes = (TextView) rootView.findViewById(R.id.tv_firstrv_des);
        }

        public void setData(SaveHomeListBean.NewsBean dataBean, int position) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,dataBean.getImg(),ivFirstrvLogo);
            tvFirstrvTitle.setText(dataBean.getTitle());
            tvFirstrvTime.setText(dataBean.getCreatTime());
            tvFirstrvDes.setText(dataBean.getSourceName());
        }
    }
}
