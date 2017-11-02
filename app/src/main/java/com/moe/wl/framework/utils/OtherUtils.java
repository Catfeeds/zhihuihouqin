package com.moe.wl.framework.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RatingBar;

import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;
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
            return false;
        }
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
        String message;
        if (SharedPrefHelper.getInstance().getAuthStatus() == 0) {
            message = "您的账号未认证，请先提交认证";
        } else if (SharedPrefHelper.getInstance().getAuthStatus() == 1) {
            message = "您的账号正在进行认证，请等待审核";
        } else if (SharedPrefHelper.getInstance().getAuthStatus() == 3) {
            message = "您的账号认证未通过，请重新提交审核";
        } else {
            message = "您的账号未认证，请先提交认证";
        }
        AlertDialog dialog = new AlertDialog(context);
        dialog.builder().setMsg(message).setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, IdentityActivity.class);
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", null).show();
    }

    // 是否认证
    public static boolean isAuth() {
        if (SharedPrefHelper.getInstance().getAuthStatus() != 2) {
            return false;
        }
        return true;
    }

    // 获取图片URI转路径
    public static String getRealFilePath(Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            LogUtils.d("获取图片的路径：1");
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            LogUtils.d("获取图片的路径：2");
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            LogUtils.d("获取图片的路径：3");
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
