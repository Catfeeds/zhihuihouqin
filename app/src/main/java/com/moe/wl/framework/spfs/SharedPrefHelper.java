package com.moe.wl.framework.spfs;


import android.content.Context;
import android.content.SharedPreferences;

import com.moe.wl.framework.application.SoftApplication;
import com.moe.wl.ui.home.bean.LoginBean;


public class SharedPrefHelper {
    /**
     * SharedPreferences的名字
     */
    private static final String SP_FILE_NAME = "APPLICATION_SP";
    private static SharedPrefHelper sharedPrefHelper = null;
    private static SharedPreferences sharedPreferences;
    /**
     * 经度
     */
    private static final String LONGITUDE = "LONGITUDE";
    /**
     * 纬度
     */
    private static final String LATITUDE = "LATITUDE";
    private static final String USER = "data";
    private static final String HELP = "help";

    public static synchronized SharedPrefHelper getInstance() {
        if (null == sharedPrefHelper) {
            sharedPrefHelper = new SharedPrefHelper();
        }
        return sharedPrefHelper;
    }

    private SharedPrefHelper() {
        sharedPreferences = SoftApplication.softApplication.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    // 电话
    public void setPhoneNumber(String phoneNumber) {
        sharedPreferences.edit().putString("phoneNumber", phoneNumber).commit();
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString("phoneNumber", "");
    }

    // 设置密码
    public void setPassword(String password) {
        sharedPreferences.edit().putString("password", password).commit();
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

    // 是否记住密码
    public void setRememberAccount(boolean bool) {
        sharedPreferences.edit().putBoolean("rememberAccount", bool).commit();
    }

    public boolean isRememberAccount() {
        return sharedPreferences.getBoolean("rememberAccount", false);
    }

    /**
     * 是否登录状态
     *
     * @param hasLogin
     */
    public void setHasLogin(boolean hasLogin) {
        sharedPreferences.edit().putBoolean("hasLogin", hasLogin).commit();
    }

    public boolean getHasLogin() {
        return sharedPreferences.getBoolean("hasLogin", false);
    }

    /**
     * 是否第一次
     *
     * @param isFirst
     */
    public void setIsFirst(boolean isFirst) {
        sharedPreferences.edit().putBoolean("isFirst", isFirst).commit();
    }

    public boolean getIsFirst() {
        return sharedPreferences.getBoolean("isFirst", true);
    }

    public void setUserInfo(Object userInfo) {
    }

    public LoginBean.UserinfoBean getUserInfo() {
        return null;
    }

    public void setToken(String token) {
        sharedPreferences.edit().putString("token", token).commit();
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public void setRyToken(String token) {
        sharedPreferences.edit().putString("rytoken", token).commit();
    }

    // 是否有团购权限
    public int getHasBuyAuth() {
        return sharedPreferences.getInt("HasBuyAuth", 0);
    }
    public void setHasBuyAuth(int hasBuyAuth) {
        sharedPreferences.edit().putInt("HasBuyAuth", hasBuyAuth).apply();
    }
    // 是否认证过
    public int getAuthStatus() {
        return sharedPreferences.getInt("AuthStatus", 0);
    }
    public void setAuthStatus(int authStatus) {
        sharedPreferences.edit().putInt("AuthStatus", authStatus).apply();
    }



    /**
     * 游客登录
     *
     * @param youke
     */
    public void setyouke(boolean youke) {
        sharedPreferences.edit().putBoolean("youke", youke).commit();
    }

    public boolean getyouke() {
        return sharedPreferences.getBoolean("youke", true);
    }

    public void setSex(String sex) {
        sharedPreferences.edit().putString("sex", sex).commit();
    }

    public String getSex() {
        return sharedPreferences.getString("sex", "");
    }

    //
    public String getUserId() {
        return sharedPreferences.getString("userId", "");
    }

    public String getNickname() {
        return sharedPreferences.getString("nickname", "");
    }

    public String getUserPhoto() {
        return sharedPreferences.getString("userPhoto", "");
    }

    public String getRealName() {
        return sharedPreferences.getString("realName", "");
    }

    public String getNation() {
        return sharedPreferences.getString("nation", "");
    }

    public String getMobile() {
        return sharedPreferences.getString("mobile", "");
    }

    public void setuserId(String userId) {
        sharedPreferences.edit().putString("userId", userId).commit();
    }

    public void setNickname(String nickname) {
        sharedPreferences.edit().putString("nickname", nickname).commit();
    }

    public void setuserPhoto(String userPhoto) {
        sharedPreferences.edit().putString("userPhoto", userPhoto).commit();
    }

    public void setRealName(String realName) {
        sharedPreferences.edit().putString("realName", realName).commit();
    }

    public void setNation(String nation) {
        sharedPreferences.edit().putString("nation", nation).commit();
    }

    public void setMobile(String mobile) {
        sharedPreferences.edit().putString("mobile", mobile).commit();
    }

    public void setRememberPassWord(boolean b) {
        sharedPreferences.edit().putBoolean("rememberPw", b).commit();
    }

    public boolean isRememberPassWord() {
        return sharedPreferences.getBoolean("rememberPw", false);
    }

    public void saveTime(String time) {
        sharedPreferences.edit().putString("time", time).commit();
    }

    public String getTime() {
        return sharedPreferences.getString("time", "");
    }

    public void saveBookName(String bookName) {
        sharedPreferences.edit().putString("bookName", bookName).commit();
    }

    public String getBookName() {
        return sharedPreferences.getString("bookName", "");
    }

    public void saveBookId(String bookId) {
        sharedPreferences.edit().putString("bookId", bookId).commit();
    }

    public String getBookId() {
        return sharedPreferences.getString("bookId", "");
    }
}