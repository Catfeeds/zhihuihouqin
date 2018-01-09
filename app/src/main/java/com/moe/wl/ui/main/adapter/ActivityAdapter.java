package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.ActivityRegistration.ActivityDetailActivity;
import com.moe.wl.ui.main.bean.ActIndexBean;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.ActivitylistBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import mvp.cn.util.CallPhoneUtils;

/**
 * Created by 我的电脑 on 2017/11/15 0015.
 */

public class ActivityAdapter extends CommonAdapter<ActivitylistBean> {

    public ActivityAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final ActivitylistBean activitylistBean, int position) {
        //tvThridrvContent.setText(activitylistBean.getATitle());
        TextView tvThridrvTime = (TextView) holder.getView(R.id.tv_thridrv_time);
        holder.setText(R.id.tv_thridrv_content,activitylistBean.getATitle());
       // tvLocation.setText(activitylistBean.getAPlace());
        holder.setText(R.id.tv_location,activitylistBean.getAPlace());
        String aContactMobile = activitylistBean.getAContactMobile();
        String str;
        if (aContactMobile.length() > 7)
            str = aContactMobile.substring(0, 3) + "****" + aContactMobile.substring(7, aContactMobile.length());
        else
            str = aContactMobile;
        holder.setText(R.id.phone,"电话：" + str);
        if (TextUtils.isEmpty(activitylistBean.getACreateTime()) || "null".equals(activitylistBean.getACreateTime())){
            tvThridrvTime.setText("暂无时间");
        }else{
            tvThridrvTime.setText("时间：" + activitylistBean.getACreateTime());
        }
        holder.setText(R.id.tv_sign_up,"报名(" + activitylistBean.getASignCount() + "/" + activitylistBean.getATotal() + ")");
        ImageView ivPhoto = (ImageView) holder.getView(R.id.iv_photo);
        GlideLoading.getInstance().loadImgUrlHeader(mContext, activitylistBean.getAImg(), ivPhoto, R.mipmap.ic_default_square);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!OtherUtils.isAuth()){
                    // 没有认证
                    OtherUtils.showAuth(mContext);
                    return;
                }
                Intent intent = new Intent(mContext, ActivityDetailActivity.class);
                intent.putExtra("activitylistBean", activitylistBean);
                mContext.startActivity(intent);
            }
        });
        ImageView ivCalPhone = (ImageView) holder.getView(R.id.iv_call_phone);
        ivCalPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = activitylistBean.getAContactMobile();
                Log.e("phone =", phone);
                CallPhoneUtils.callPhone(phone, mContext);
            }
        });
    }
}
