package cn.lc.model.framework.spfs;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.ui.home.bean.LoginBean;


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

    public void setPhoneNumber(String phoneNumber) {
        sharedPreferences.edit().putString("phoneNumber", phoneNumber).commit();
    }

    public String getPhoneNumber() {
        return sharedPreferences.getString("phoneNumber", "");
    }

    public void setPassword(String password) {
        sharedPreferences.edit().putString("password", password).commit();
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }

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
}