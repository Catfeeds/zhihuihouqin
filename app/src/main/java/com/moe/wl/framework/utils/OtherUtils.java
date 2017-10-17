package com.moe.wl.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.widget.RatingBar;

import com.moe.wl.ui.main.activity.me.OrderCommentActivity;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/19 0019
 */

public class OtherUtils {

    // 设置评分星星颜色
    public static void ratingBarColor(RatingBar ratingBar) {
        LayerDrawable drawable = (LayerDrawable) ratingBar.getProgressDrawable();
        drawable.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }

    // 手机号正则
    public static boolean phoneNumber(String mobile) {
        String telRegex = "[1][358]\\d{9}";
        if (!mobile.matches(telRegex)) {
            LogUtils.d("不匹配！！！");
            return false;
        }
        LogUtils.d("匹配！！！");
        return true;
    }

    // 订单评论页面
    public static void gotoComment(Context context, int orderID, int serviceType) {
        Intent intent = new Intent(context, OrderCommentActivity.class);
        intent.putExtra("OrderID", orderID);
        intent.putExtra("ServiceType", serviceType);
        context.startActivity(intent);
    }
}
