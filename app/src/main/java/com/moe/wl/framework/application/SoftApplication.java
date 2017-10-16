package com.moe.wl.framework.application;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;

import com.google.gson.Gson;
import com.moe.wl.framework.config.AppConfig;
import com.moe.wl.framework.config.AppInfo;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.ui.home.bean.LoginBean;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvp.cn.common.QuickApplication;
import mvp.cn.util.DateUtil;
import mvp.cn.util.NetUtil;


public class SoftApplication extends QuickApplication {
    /**
     * 存放活动状态的(未被销毁)的Activity列表
     */
    public static List<Activity> unDestroyActivityList = new ArrayList<Activity>();
    public static SoftApplication softApplication;
    private static AppInfo appInfo;
    private static LoginBean.UserinfoBean userInfo ;
    private static boolean isLogin;// 判断是否已经登录

    private static int authStatus;

    private double longitude;
    private double latitude;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化shareSDk
//        MobSDK.init(getApplicationContext(), "20ad34651b60e", "3e43dd44f20087c729bc6b4605d75c73");
        softApplication = this;
        refWatcher =  LeakCanary.install(this);

//        MobSDK.init(this, this.getAppkey(), this.getAppSecret());

//        appInfo = initAppInfo();

//		CrashHandler catchHandler = CrashHandler.getInstance();
//      catchHandler.init(getApplicationContext());
        //腾讯 bugly:账号：qq@cm-2.cn   已绑定QQ号码:3419836168  密码：yunyin@cm2
//		CrashReport.initCrashReport(getApplicationContext(), "d58a02c7f5", true);

        //极光推送
//		JPushInterface.setDebugMode(true);
//		JPushInterface.init(this);
    }

    /**
     * leak检测内存泄露
     * @return
     */
    @Override
    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 实例化AppInfo
     */
    private AppInfo initAppInfo() {
        AppInfo appInfo = AppConfig.getAppConfigInfo(softApplication);
        appInfo.imei = NetUtil.getIMEI(getApplicationContext());
        appInfo.imsi = NetUtil.getIMSI(getApplicationContext()) == null ? "" : NetUtil.getIMSI(getApplicationContext());
        appInfo.osVersion = getOSVersion();
        appInfo.appVersionCode = getAppVersionCode();
        return appInfo;
    }

    /**
     * 得到系统的版本号
     *
     * @return
     */
    public String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 得到应用的版本号
     *
     * @return
     */
    public int getAppVersionCode() {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        int versionCode = 0;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 得到应用的版本号
     *
     * @return
     */
    public String getAppVersionName() {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String versionCode = "";
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionCode = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取Assert文件夹中的配置文件信息
     *
     * @return
     */
    public AppInfo getAppInfo() {
        return appInfo;
    }

    public String getFrom() {
        return appInfo == null ? "" : appInfo.os;
    }

    public String getApiUser() {
        return appInfo == null ? "" : appInfo.api_user;
    }

    public String getApiPassword() {
        return appInfo == null ? "" : appInfo.api_pwd;
    }

    /**
     * 得到请求头JsonObject
     *
     * @return
     */
    public String getAuthJsonObject(String jsonString) {
        try {
            String timeStamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
            Map<String,Object> authJsonObject = new HashMap<>();
            authJsonObject.put("app_key", appInfo.appKey);
            authJsonObject.put("imei", appInfo.imei);
            authJsonObject.put("os", appInfo.os);
            authJsonObject.put("os_version", appInfo.osVersion);
            authJsonObject.put("app_version", appInfo.appVersionCode);
//            authJsonObject.put("source_id", appInfo.sourceId);
//            authJsonObject.put("ver", appInfo.ver);
            authJsonObject.put("uid", isLogin ? userInfo.getUserId() : "");
            authJsonObject.put("time_stamp", timeStamp);
//            authJsonObject.put("crc", CrcUtil.getCrc(timeStamp, appInfo.imei, (isLogin ? userInfo.uid : appInfo.uid), (isLogin ? passwordWithMd5 : CrcUtil.MD5(appInfo.crc)), jsonString));
//            authJsonObject.put("token", getToken(timeStamp));
            return new Gson().toJson(authJsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 退出应用
     */
    public void quit() {
        for (Activity activity : unDestroyActivityList) {
            if (null != activity) {
                activity.finish();
            }
        }
        unDestroyActivityList.clear();
        logout();
    }

    /**
     * 注销帐号
     */
    public void logout() {
        /**
         * 退出登录,清空数据
         */
        userInfo = null;
        isLogin = false;
        SharedPrefHelper.getInstance().setUserInfo(null);
    }

    /**
     * 保存登录成功之后用户的信息
     *
     * @param result
     */
    public void setUserInfo(LoginBean.UserinfoBean result) {
        userInfo = result;
        if (result != null) {
            isLogin = true;
        }
    }

    public boolean isLogin() {
        return isLogin;
    }

    /**
     * 获取用户的信息
     *
     * @return
     */
    public LoginBean.UserinfoBean getUserInfo() {
        if (userInfo == null) {
            userInfo = SharedPrefHelper.getInstance().getUserInfo();
        }
        return userInfo;
    }
    //保存书名
    private static List<String> mBooks=new ArrayList<>();
    public static void saveBookName(List<String> books) {
        mBooks.addAll(books);
    }
    public static List<String> getBookName(){
        if(mBooks!=null&&mBooks.size()>0){
            return mBooks;

        }else{
            return null;
        }
    }
    private static List<String> mBookId=new ArrayList<>();
    public static void saveBookId(List<String> bookId) {
        mBookId.addAll(bookId);
    }

    public static List<String> getBookId(){
        if(mBookId!=null&&mBookId.size()>0){
            return mBookId;

        }else{
            return null;
        }
    }
}