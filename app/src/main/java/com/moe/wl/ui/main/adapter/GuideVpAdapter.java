package com.moe.wl.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.ui.login.activity.LoginActivity;

/**
 * Created by 我的电脑 on 2017/8/21 0021.
 */

public class GuideVpAdapter extends PagerAdapter {
    private  Context mContext;
    List<Integer> photos= Arrays.asList(R.drawable.partner_luffy,
            R.drawable.partner_luffy,
            R.drawable.partner_luffy,
            R.drawable.partner_luffy,
            R.drawable.partner_luffy);

    public GuideVpAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.splash_item, null);
        ImageView ivSplash= (ImageView) view.findViewById(R.id.iv_splash);
        TextView tvBottom= (TextView) view.findViewById(R.id.tv_bottom);
        if(position==photos.size()-1){
            tvBottom.setVisibility(View.VISIBLE);
        }else{
            tvBottom.setVisibility(View.GONE);
        }
        tvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击进入到登陆界面
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
                SharedPrefHelper.getInstance().setIsFirst(false);
                ((Activity) mContext).finish();
            }
        });
        ivSplash.setImageResource(photos.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
