package mvp.cn.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by 我的电脑 on 2017/8/24 0024.
 */

public class CallPhoneUtils {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    public static void call(ImageView ivCalPhone, final String phone, final Context mContext) {
        // 给按钮设置监听（点击事件）
        ivCalPhone.setOnClickListener(new View.OnClickListener() { // 匿名内部类
            // 按钮点击时回调
            @Override
            public void onClick(View view) {
                // 检查是否获得了权限（Android6.0运行时权限）
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext,
                            Manifest.permission.CALL_PHONE)) {
                        // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        Toast.makeText(mContext, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                        intent.setData(uri);
                        mContext.startActivity(intent);
                    }else{
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions((Activity) mContext,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                }else {
                    // 已经获得授权，可以打电话
                    if (TextUtils.isEmpty(phone)) {
                        // 提醒用户
                        // 注意：在这个匿名内部类中如果用this则表示是View.OnClickListener类的对象，
                        // 所以必须用MainActivity.this来指定上下文环境。
                        Toast.makeText(mContext, "号码不能为空！", Toast.LENGTH_SHORT).show();
                    } else {
                        // 拨号：激活系统的拨号组件
                        Intent intent = new Intent(); // 意图对象：动作 + 数据
                        intent.setAction(Intent.ACTION_CALL); // 设置动作
                        Uri data = Uri.parse("tel:" + phone); // 设置数据
                        intent.setData(data);
                        mContext.startActivity(intent); // 激活Activity组件
                    }
                }
            }
        });
    }
}


