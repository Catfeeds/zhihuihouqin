package com.moe.wl.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.RatingBar;

import com.moe.wl.R;
import com.moe.wl.ui.login.activity.IdentityActivity;
import com.moe.wl.ui.main.activity.me.OrderCommentActivity;
import com.moe.wl.ui.mywidget.AlertDialog;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/19 0019
 */

public class OtherUtils {

    // 设置评分星星颜色
    public static void ratingBarColor(RatingBar ratingBar, Context context) {
        LayerDrawable drawable = (LayerDrawable) ratingBar.getProgressDrawable();
        drawable.getDrawable(2).setColorFilter(context.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP);
    }

    // 手机号正则
    public static boolean phoneNumber(String mobile) {
        String telRegex = "[1][3578]\\d{9}";
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

    // 展示认证弹窗
    public static void showAuth(final Context context) {
        AlertDialog dialog = new AlertDialog(context);
        dialog.builder().setMsg("您的账号未认证，请先提交认证").setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, IdentityActivity.class);
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", null).show();
    }

    // 是否认证
    public static boolean isAuth() {
//        if (SharedPrefHelper.getInstance().getRealName() == null || "".equals(SharedPrefHelper.getInstance().getRealName())) {
//            return false;
//        }
        return true;
    }
}
