package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import cn.lc.model.ui.main.activity.ActivityRegistration.ActivityDetailActivity;
import cn.lc.model.ui.main.bean.ActivityHomeBean;
import mvp.cn.util.CallPhoneUtils;

/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeNsrlv3Adapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ActivityHomeBean.ActivitylistBean> data = new ArrayList<>();

    public HomeNsrlv3Adapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv3_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data != null && data.size() > 0) {
            viewHolder.setData(data.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<ActivityHomeBean.ActivitylistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_thridrv_content)
        TextView tvThridrvContent;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.tv_thridrv_time)
        TextView tvThridrvTime;
        @BindView(R.id.tv_sign_up)
        TextView tvSignUp;
        @BindView(R.id.iv_call_phone)
        ImageView ivCalPhone;
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        private ActivityHomeBean.ActivitylistBean activitylistBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ActivityDetailActivity.class);
                    intent.putExtra("activitylistBean", activitylistBean);
                    mContext.startActivity(intent);
                }
            });
            ivCalPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callPhone();
                }
            });
        }

        private void callPhone() {
            String phone = activitylistBean.getAContactMobile();
            Log.e("phone =", phone);
            CallPhoneUtils.call(ivCalPhone, phone, mContext);
        }

        public void setData(ActivityHomeBean.ActivitylistBean activitylistBean, int position) {
            if (activitylistBean != null) {
                this.activitylistBean = activitylistBean;
                tvThridrvContent.setText(activitylistBean.getATitle());
                tvLocation.setText(activitylistBean.getAPlace());
                String aContactMobile = activitylistBean.getAContactMobile();
                String str = aContactMobile.substring(0, 3) + "****" + aContactMobile.substring(7, aContactMobile.length());
                phone.setText("电话: " + str);
                tvThridrvTime.setText("时间: " + activitylistBean.getACreateTime());
                tvSignUp.setText("包名(" + activitylistBean.getASignCount() / activitylistBean.getATotal() + ")");
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, activitylistBean.getAImg(), ivPhoto);
            }
        }
    }
}
