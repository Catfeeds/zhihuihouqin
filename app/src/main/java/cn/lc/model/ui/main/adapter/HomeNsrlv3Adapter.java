package cn.lc.model.ui.main.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.activity.ActivityRegistration.ActivityDetailActivity;
import cn.lc.model.ui.main.activity.MainActivity;
import mvp.cn.util.CallPhoneUtils;

/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeNsrlv3Adapter extends RecyclerView.Adapter {
    private Context mContext;

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

    }

    @Override
    public int getItemCount() {
        return 3;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            callPhone();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext,ActivityDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

         private void callPhone() {
             String phone = this.phone.getText().toString().trim();
             CallPhoneUtils.call(ivCalPhone, phone, mContext);
         }
     }
}
