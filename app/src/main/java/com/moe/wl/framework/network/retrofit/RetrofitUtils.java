package com.moe.wl.framework.network.retrofit;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.moe.wl.framework.application.SoftApplication;
import com.moe.wl.framework.network.AppConstants;
import com.moe.wl.framework.network.ServerConstants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import id.zelory.compressor.Compressor;
import mvp.cn.util.DateUtil;
import mvp.cn.util.LogUtil;
import mvp.cn.util.Md5Util;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.moe.wl.framework.network.retrofit.ParameterKeys.TOKEN;

/**
 * Created by hh on 2017/5/12.
 */

public class RetrofitUtils implements AppConstants, ServerConstants {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static NetAPI api;
    private static RetrofitUtils instance;

    private static RetrofitUtils createApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(NetAPI.class);
        return new RetrofitUtils();
    }

    //指定线程
    private static Observable<?> getObservable(Observable<?> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private static void addParam(Map<String, Object> paramsMap, Map<String, String> tempMap) {
        Gson gson = new Gson();
        String biz = gson.toJson(tempMap);
        paramsMap.put("biz", biz);
        LogUtils.d("biz", biz);
        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
        paramsMap.put("timestamp", timestamp);
        LogUtils.d("token==" + SharedPrefHelper.getInstance().getToken());
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
            paramsMap.put(TOKEN, SharedPrefHelper.getInstance().getToken());
        } else {
            if (SharedPrefHelper.getInstance().getyouke()) {
                paramsMap.put(TOKEN, "0");
            } else {
            }
        }
        try {
            String sign = getSign(biz, timestamp, "zxcadsadwa@2321$");
            LogUtils.d("sign:" + sign);
            paramsMap.put("sign", sign);

        } catch (UnsupportedEncodingException e) {
            Log.e("sign异常", e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addParams(Map<String, Object> paramsMap, Map<String, Object> tempMap) {
        Gson gson = new Gson();
        String biz = gson.toJson(tempMap);
        paramsMap.put("biz", biz);
        LogUtils.d("biz", biz);
        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
        paramsMap.put("timestamp", timestamp);
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
            paramsMap.put(TOKEN, SharedPrefHelper.getInstance().getToken());

        } else {
            if (SharedPrefHelper.getInstance().getyouke()) {
                paramsMap.put(TOKEN, "0");
            } else {
            }
        }
        try {
            String sign = getSign(biz, timestamp, "zxcadsadwa@2321$");
            paramsMap.put("sign", sign);
        } catch (UnsupportedEncodingException e) {
            Log.e("sign异常", e.getMessage());
            e.printStackTrace();
        }
    }


    private static void getRequestBody(Map<String, RequestBody> paramsMap, Map<String, String> tempMap) {
        Gson gson = new Gson();
        String biz = gson.toJson(tempMap);
        LogUtils.d("biz", biz);
        RequestBody requestbiz = RequestBody.create(MediaType.parse("multipart/form-data"), biz);
        paramsMap.put(ParameterKeys.BIZ, requestbiz);
        //  LogUtils.d("biz请求参数：" + biz);
        //  String device=gson.toJson(getdevice());
        //  RequestBody requestdevice = RequestBody.create(MediaType.parse("multipart/form-data"), device);
        //  paramsMap.put(ParameterKeys.DEVICE,requestdevice);
        //  LogUtils.d("device请求参数：" + device);
        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
        RequestBody requesttimestamp = RequestBody.create(MediaType.parse("multipart/form-data"), timestamp);
        paramsMap.put(ParameterKeys.TIMESTAMP, requesttimestamp);
        //     LogUtils.d("时间戳请求参数：" + timestamp);
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
            //        LogUtils.d("请求参数：getToken" + SharedPrefHelper.getInstance().getToken());
            paramsMap.put(TOKEN, RequestBody.create(MediaType.parse("multipart/form-data"), SharedPrefHelper.getInstance().getToken()));
        } else {
            LogUtil.log(SharedPrefHelper.getInstance().getyouke() + "。。。。。。。。。");
            if (SharedPrefHelper.getInstance().getyouke() == true) {
                paramsMap.put(TOKEN, RequestBody.create(MediaType.parse("multipart/form-data"), "0"));
            } else {
            }
        }
        try {
            RequestBody requestsign = RequestBody.create(MediaType.parse("multipart/form-data"), getSign(biz, timestamp, "zxcadsadwa@2321$"));
            paramsMap.put(ParameterKeys.SIGN, requestsign);
            //       LogUtils.d("sign戳请求参数：" + getSign(device,biz, timestamp));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static MultipartBody.Part getHeaderImg(File file, String s) {

        File file1 = new Compressor.Builder(SoftApplication.softApplication)
                .setMaxWidth(720)
                .setMaxHeight(1080)
                .setQuality(80)
                .setCompressFormat(Bitmap.CompressFormat.PNG)
                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).getAbsolutePath())
                .build()
                .compressToFile(file);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part part = MultipartBody.Part.createFormData(s, file.getName(), requestFile);
        return part;
    }

    private static List<MultipartBody.Part> getImgList(List<String> paths, String s) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        LogUtils.d("文件长度：" + paths.size());
        for (int i = 0; i < paths.size(); i++) {
            //这里采用的Compressor图片压缩
            LogUtils.d("图片地址：" + paths.get(i));
            File files = new File(paths.get(i));
            LogUtils.i(files.length() + "");
            File file = new Compressor.Builder(SoftApplication.softApplication)
                    .setMaxWidth(720)
                    .setMaxHeight(1080)
                    .setQuality(80)
                    .setCompressFormat(Bitmap.CompressFormat.PNG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(files);
//                    .compressToFile(new File(paths.get(i)));

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(s, file.getName(), requestFile);
            parts.add(part);
        }
        return parts;
    }


    /*加密*/
    private static String getSign(String biz, String timestamp, String secretKey) throws UnsupportedEncodingException {
        return Md5Util.getMD5(URLEncoder.encode(biz, "UTF-8") + timestamp + secretKey);
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.d("retrofit", "retrofitBack = " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpClient = builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    connectTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    addInterceptor(loggingInterceptor).build();
        }
        return okHttpClient;
    }

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            createApi();
        }
        return instance;
    }

    //-------------------------------------------请求内容----------------------------------------------

    /**
     * 登陆
     *
     * @param uid
     * @param pwd
     * @return
     */
    public static Observable login(String uid, String pwd) {
       /* Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            paramsMap.put("uid", uid);
            paramsMap.put("pwd", pwd);

            Gson gson = new Gson();
            String info = gson.toJson(tempMap);
            paramsMap.put(ParameterKeys.KEY_INFO, info);
            paramsMap.put(ParameterKeys.KEY_SIGN, "");
            paramsMap.put(ParameterKeys.KEY_TOKEN, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.login(paramsMap);*/
        /*eg： {"userName":"13269770032","password":123456}*/
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("password", pwd);
            tempMap.put("userName", uid);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.login(paramsMap));
    }

    /**
     * 获取验证码
     */
    public static Observable getCaptcha(String number, int num) {

        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("userName", number);
            tempMap.put("from", num + "");

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getCaptcha(paramsMap));
    }

    /**
     * 注册
     *
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    public static Observable register(String mobile, String code, String password) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("userName", mobile);
            tempMap.put("captcha", code);
            String s = Md5Util.getMD5(password);
            tempMap.put("password", s);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.register(paramsMap));

    }

    /**
     * 修改密码
     *
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    public static Observable changePassWord(String mobile, String code, String password) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("userName", mobile);
            tempMap.put("captcha", code);
            String s = Md5Util.getMD5(password);
            tempMap.put("password", s);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.changePassWord(paramsMap));

    }

    /**
     * 绑定手机号
     */
    public static Observable bindPhone(int loginType, String userName, String thirdNum,
                                       String isRegister, String password, String captcha) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("loginType", loginType + "");
            tempMap.put("userName", userName);
            tempMap.put("thirdNum", thirdNum);
            tempMap.put("isRegister", isRegister);
            tempMap.put("password", password);
            tempMap.put("captcha", captcha);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.bindPhone(paramsMap));
    }

    /**
     * 首页数据
     */
    public static Observable homePageData() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.homePageData(paramsMap));
    }

    /**
     * 职位列表
     */
    public static Observable getPostionList() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getPositionList(paramsMap));
    }

    /**
     * 职位列表
     */
    public static Observable submitAuth(String name, String mobile,
                                        String idCard, int positionid, String roomId, String officetel,
                                        String cartypeId, String precarCode, String suffixcarCode,
                                        String buildnum, String departid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("name", name);
            tempMap.put("mobile", mobile);
            tempMap.put("idCard", idCard);
            tempMap.put("positionid", positionid + "");
            tempMap.put("roomId", roomId);
            tempMap.put("officetel", officetel);
            tempMap.put("cartypeId", cartypeId);
            tempMap.put("precarCode", precarCode);
            tempMap.put("suffixcarCode", suffixcarCode);
            tempMap.put("buildnum", buildnum);
            tempMap.put("departid", departid);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getPositionList(paramsMap));
    }

    /**
     * 医疗健康服务首页
     *
     * @return
     */
    public static Observable getHealthServiceHomeData() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getHealthServiceHome", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getHomeData(paramsMap));
    }

    /**
     * 医生列表
     *
     * @return
     */
    public static Observable getDoctorList() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getDoctorList", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDoctorList(paramsMap));
    }

    /**
     * 医生详情
     *
     * @return
     */
    public static Observable getDoctorDetail(int doctorId) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getDoctorDetail", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("doctorId", doctorId + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDoctorDetail(paramsMap));
    }

    /**
     * 健康资讯收藏
     *
     * @return
     */
    public static Observable getHealthInfoColect(int i1, int i2) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getHealthInfoColect", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("type", i1 + "");
            tempMap.put("entityid", i2 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getCollectResult(paramsMap));
    }

    /**
     * 更多资讯列表
     *
     * @return
     */
    public static Observable getMoreList(int page, int limit, String keywords) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            tempMap.put("keywords", keywords);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getMoreList(paramsMap));
    }

    /**
     * 获取医生评论列表
     *
     * @return
     */
    public static Observable getUserCommentList(int doctorId, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("doctorid", doctorId + "");
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getUserCommentList(paramsMap));
    }


    /**
     * 获取专家详情
     */
    public static Observable getExpertDetail() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getExpertDetail(paramsMap));
    }

    /**
     * 获取专家评论列表
     *
     * @return
     */
    public static Observable getExpertComment(int expertId, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getUserCommentList", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("doctorid", expertId + "");
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getExpertComment(paramsMap));
    }


    /**
     * 专家下单
     */
    public static Observable submitExpertOrder(int id, int timeId) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("doctorid", id + "");
            tempMap.put("scheduleid", timeId + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.submitExpertOrder(paramsMap));
    }

    /**
     * 物业首页
     *
     * @return
     */
    public static Observable getWuyeHomeInfo(int menditem, String username, String mobile,
                                             String invitetime, String serviceplace, String mendcontent, List<String> files) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        Log.e("getUserCommentList", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("menditem", menditem + "");
            tempMap.put("username", username);
            tempMap.put("mobile", mobile);
            tempMap.put("invitetime", invitetime);
            tempMap.put("serviceplace", serviceplace);
            tempMap.put("mendcontent", mendcontent);
//            addParam(paramsMap, tempMap);
            getRequestBody(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getWuyeHomeInfo(paramsMap, getImgList(files, "files")));
    }

    /**
     * 活动首页
     *
     * @return
     */
    public static Observable getActivityHome(int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getActivityHome", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getActivityHome(paramsMap));
    }

    /**
     * 活动发布
     *
     * @return
     */

    public static Observable postActivity(String aContactMobile, String aContent, String aPlace
            , String aSponsor, String aTime, String aTitle, String aTotal, List<String> photos, List<String> imgs, String aRealname,
                                          String aNaion) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        Log.e("postActivity", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("aContactMobile", aContactMobile);
            tempMap.put("aContent", aContent);
            tempMap.put("aPlace", aPlace);
            tempMap.put("aTime", aTime);
            tempMap.put("aTitle", aTitle);
            tempMap.put("aTotal", aTotal);
            tempMap.put("aSponsor", aSponsor);
            //图片文件上传
            tempMap.put("aRealname", aRealname);
            tempMap.put("aNaion", aNaion);
            getRequestBody(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.postActivity(paramsMap, getImgList(photos, "photos"), getImgList(imgs, "imgs")));
    }

    /**
     * 活动报名
     *
     * @return
     */
    public static Observable getActivitySign(int activityid, String asMebile, String asUsername) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getActivityHome", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("activityid", activityid + "");
            tempMap.put("asMebile", asMebile);
            tempMap.put("asUsername", asUsername);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getActivitySign(paramsMap));
    }

    /**
     * 活动报名列表
     *
     * @return
     */
    public static Observable getActivitySignList(int activityid) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getActivitySignList", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("activityid", activityid + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getActivitySignList(paramsMap));
    }

    /**
     * 活动用户详情
     *
     * @return
     */
    public static Observable getActivityUserDetail(int signmemberid) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getActivitySignList", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("signmemberid", signmemberid + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getActivityUserDetail(paramsMap));
    }

    /**
     * 图书馆首页
     *
     * @return
     */
    public static Observable getLibraryHomePic() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getLibraryHomePic", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getLibraryHomePic(paramsMap));
    }

    /**
     * 图书馆fragment
     *
     * @return
     */
    public static Observable getLibraryHomeData(int position) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getLibraryHomeData", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("position", position + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getLibraryHomeData(paramsMap));
    }

    /**
     * 推荐图书
     *
     * @return
     */
    public static Observable getRecommandResult(String title, String author, String publisher, String remark) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getLibraryHomeData", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("title", title);
            tempMap.put("author", author);
            tempMap.put("publisher", publisher);
            tempMap.put("remark", remark);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getRecommandResult(paramsMap));
    }

    /**
     * 图书详情
     *
     * @return
     */
    public static Observable getBookDetailResult(int bookId) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getBookDetailResult", "---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("bookId", bookId + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getBookDetailResult(paramsMap));
    }

    /**
     * 图书收藏
     *
     * @return
     */
    public static Observable bookCollect(int type, int entityid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("type", type + "");
            tempMap.put("entityid", entityid + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.bookCollect(paramsMap));
    }

    /**
     * 图书借阅
     *
     * @return
     */
    public static Observable jieYueBook(String invitegetbooktime, String uname, String umobile,
                                        String bookids) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("jieYueBook", "---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap();
            tempMap.put("invitegetbooktime", invitegetbooktime);
            tempMap.put("uname", uname);
            tempMap.put("umobile", umobile);
            tempMap.put("bookids", bookids);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.jieYueBook(paramsMap));
    }

    /**
     * 图书类别搜索
     */
    public static Observable getSearchCategory() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("jieYueBook", "---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getSearchCategory(paramsMap));
    }

    /**
     * 图书借阅
     */
    public static Observable getJieyueTime() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getJieyueTime", "---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.jieYueTime(paramsMap));
    }

    /**
     * 获取图书列表
     */
    public static Observable getSearchBookList(String keywords, String typeid, String order) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getSearchBookList", "---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap();
            tempMap.put("keywords", keywords);
            tempMap.put("typeid", typeid);
            tempMap.put("order", order);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getSearchBookList(paramsMap));
    }

    /**
     * 获取图书订单列表
     */
    public static Observable bookOrderList(String type, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("bookOrderList", "---》bookDetail");
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("type", type);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.bookOrderList(paramsMap));
    }

    /**
     * 理发店铺
     *
     * @return
     */
    public static Observable getShopInfo() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getShopInfo(paramsMap));
    }

    /**
     * 理发师列表
     *
     * @return
     */
    public static Observable getBarberList() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getBarberList(paramsMap));
    }

    /**
     * 理发师详情信息
     *
     * @return
     */
    public static Observable getBarberDetail(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberid", id + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getBarberDetailInfo(paramsMap));
    }

    /**
     * 理发师作品列表
     *
     * @return
     */
    public static Observable getBarberProductList(String id, String page, String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberId", id);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getbarberworklist(paramsMap));
    }

    /**
     * 预约理发师
     *
     * @return
     */
    public static Observable preOrderBarber(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberId", id + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getbarberInfo(paramsMap));
    }

    /**
     * 咨询理发师
     *
     * @return
     */
    public static Observable consultBarber(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberid", id + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getConsultInfo(paramsMap));
    }

    /**
     * 发送消息
     *
     * @return
     */
    public static Observable sendMessage(int barberid, String content) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberid", barberid + "");
            tempMap.put("content", content);
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.sendMessage(paramsMap));
    }

    /**
     * 理发师作品详情信息
     *
     * @return
     */
    public static Observable getBarberProductDetail(int workid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("workid", workid + "");
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDetail(paramsMap));
    }

    /**
     * 理发师更多评论
     *
     * @return
     */
    public static Observable getBarberMoreComment(int id, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberid", id + "");
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getBarberMoreComment(paramsMap));
    }

    /**
     * 干洗店首页
     *
     * @return
     */
    public static Observable getDryCleanerHome(int serviceType) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("serviceType", serviceType + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDryCleanerHomeInfo(paramsMap));
    }

    /**
     * 预约干洗
     *
     * @return
     */
    public static Observable orderDryCleaner(String page, String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderDryClean(paramsMap));
    }

    /**
     * 提交预约
     *
     * @return
     */
    public static Observable dryOrderCommit(String mobile, String expectarrivaItme,
                                            String clothList) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("mobile", mobile);
            tempMap.put("expectarrivaItme", expectarrivaItme);
            tempMap.put("clothList", clothList);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.dryOrderCommit(paramsMap));
    }

    /**
     * 查看干洗订单
     *
     * @return
     */
    public static Observable checkDryOrder(int orderStatus, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("orderStatus", orderStatus + "");
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.checkDryOrder(paramsMap));
    }

    /**
     * 获取干洗取消列表
     *
     * @return
     */
    /*public static Observable getDryCancelList() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDryCancelList(paramsMap));
    }*/

    /**
     * 获取干洗取消列表
     *
     * @return
     */
    public static Observable cancelDryCancel(int old, int[] reasonIds, String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", old + "");
            tempMap.put("reasonIds", reasonIds);
            tempMap.put("reasonContent", reasonContent);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.commitDryCancelOrder(paramsMap));
    }

    /**
     * 干洗评论
     *
     * @return
     */
    public static Observable dryToComment(int oid, double stars, String content, String isAnonymous, File imgFile) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("oid", oid + "");
            tempMap.put("stars", stars + "");
            tempMap.put("content", content);
            tempMap.put("isAnonymous", isAnonymous);
            //TODO: 2017/9/16 0016 需要添加图片文件
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.commitDryComment(paramsMap));
    }

    /**
     * 办公首页
     *
     * @return
     */
    public static Observable officeIndex() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getOfficeIndex(paramsMap));
    }

    /**
     * 办公商品类别
     *
     * @return
     */
    public static Observable getSpCategory(String categoryOneId,
                                           String page, String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("categoryOneId", categoryOneId);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getProductCategory(paramsMap));
    }

    /**
     * 商品详情
     *
     * @return
     */
    public static Observable getSpDetail(String productId) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("productId", productId);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getSpDetail(paramsMap));
    }

    /**
     * 商品所有评价
     *
     * @return
     */
    public static Observable getSpAllCommentCount(String productId) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("productId", productId);
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getSpAllCommentCount(paramsMap));
    }

    /**
     * 商品所有评价
     *
     * @return
     */
    public static Observable getSpAllComment(String productId, String commentType, String page, String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("productId", productId);
            tempMap.put("commentType", commentType);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getSpAllComment(paramsMap));
    }

    /**
     * 商品购物车信息
     *
     * @return
     */
    public static Observable getSpShopCarInfo(String productId) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("productId", productId);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getShopCarInfo(paramsMap));
    }

    /**
     * 加入购物车
     *
     * @return
     */
    public static Observable shopCar(String skuid, String count) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("skuid", skuid);
            tempMap.put("count", count);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.shopCar(paramsMap));
    }

    /**
     * 查看购物车
     *
     * @return
     */
    public static Observable checkShopCar() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.checkShopCar(paramsMap));
    }

    /**
     * 商品订单
     *
     * @return
     */
    public static Observable spOrder(String addressid, String expectedTime, String remark, String productList,
                                     String skuid, String count) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("addressid", addressid);
            tempMap.put("expectedTime", expectedTime);
            tempMap.put("remark", remark);
            tempMap.put("productList", productList);
            tempMap.put("skuid", skuid);
            tempMap.put("count", count);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getSpOrder(paramsMap));
    }

    /**
     * 删除订单选项
     *
     * @return
     */
    public static Observable cancelItem(int[] arr) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("ids", arr);

            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelItem(paramsMap));
    }

    /**
     * 发布商品
     *
     * @return
     */

    public static Observable post(String realname, String mobile, String remark, String productName,
                                  String count) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("realname", realname);
            tempMap.put("mobile", mobile);
            tempMap.put("remark", remark);
            tempMap.put("productName", productName);
            tempMap.put("count", count);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.postNeed(paramsMap));
    }

    /**
     * 历史发布
     *
     * @return
     */

    public static Observable historyPost(String page, String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("page", page);
            tempMap.put("limit", limit);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.historyPostNeed(paramsMap));
    }

    /**
     * 生成订餐订单
     *
     * @return
     */
    public static Observable createOrdering(String userName, String phoneNumber, int timeId, int count, int addressId) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("realname", userName);
            tempMap.put("mobile", phoneNumber);
            tempMap.put("sendfoodtimeId", timeId + "");
            tempMap.put("count", count + "");
            tempMap.put("addressId", addressId + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.createOrdering(paramsMap));
    }

    /**
     * 获取取餐时间
     *
     * @return
     */
    public static Observable getTime() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getTime(paramsMap));
    }

    /**
     * 获取取消订单原因
     *
     * @return
     */
    public static Observable getReason() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getReason(paramsMap));
    }

    /**
     * 获取营养套餐
     */
    public static Observable getNutritionData(int type) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("type", type + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getNutritionData(paramsMap));
    }


    /**
     * 获取今日菜谱
     */
    public static Observable getTodayRecipe(int timeType, int type) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("timeType", timeType + "");
            tempMap.put("type", type + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getTodayRecipe(paramsMap));
    }

    /**
     * 获取营养套餐banner
     */
    public static Observable getNutritionBanner() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getNutritionBanner(paramsMap));
    }

    /**
     * 获取意见反馈标签
     */
    public static Observable getLabelling() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getLabelling(paramsMap));
    }

    /**
     * 提交意见反馈
     */
    public static Observable submitComplain(int id, String complaintContent, String suggestContent, int anonymous, ArrayList<String> paths) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("tagid", id + "");
            tempMap.put("complaintContent", complaintContent);
            tempMap.put("suggestContent", suggestContent);
            tempMap.put("anonymous", anonymous + "");
            getRequestBody(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.submitComplain(paramsMap, getImgList(paths, "imgFile")));
    }

    /**
     * 获取反馈历史
     */
    public static Observable getComplainHistory(int page, int pageCount) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("page", page + "");
            tempMap.put("limit", pageCount + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getComplainHistory(paramsMap));
    }

    /**
     * 获取用户地址
     */
    public static Observable getAddress() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getAddress(paramsMap));
    }

    /**
     * 修改地址
     */
    public static Observable editAddress(int id, String realname, String mobile, String address) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("id", id + "");
            tempMap.put("realname", realname + "");
            tempMap.put("mobile", mobile + "");
            tempMap.put("address", address);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.editAddress(paramsMap));
    }

    /**
     * 添加地址
     */
    public static Observable addAddress(String realname, String mobile, String address) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("realname", realname + "");
            tempMap.put("mobile", mobile + "");
            tempMap.put("address", address);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.addAddress(paramsMap));
    }

    /**
     * 删除地址
     */
    public static Observable deleteAddress(int[] id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            JsonArray json = new JsonArray();
            for (int i = 0; i < id.length; i++) {
                json.add(id[i]);
            }
            tempMap.put("ids", json.toString());
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteAddress(paramsMap));
    }

    /**
     * 查询水类型
     */
    public static Observable queryWaterType() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.queryWaterType(paramsMap));
    }

    /**
     * 查询水列表
     */
    public static Observable queryWaterList(int categoryId, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("categoryId", categoryId + "");
            tempMap.put("page", page + "");
            tempMap.put("limit", limit + "");
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.queryWaterList(paramsMap));
    }

    /**
     * 获取送水时间
     */
    public static Observable getOrderWaterTime() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getOrderWaterTime(paramsMap));
    }

    /**
     * 生成订水订单
     */
    public static Observable generateOrder(String realname, String mobile, int addressId, String sendTime,
                                           Object[] arr, String remark) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("realname", realname);
            tempMap.put("mobile", mobile);
            tempMap.put("addressId", addressId + "");
            tempMap.put("sendTime", sendTime);
            tempMap.put("goodsList", arr);
            tempMap.put("remark", remark);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.generateOrder(paramsMap));
    }

    /**
     * 获取投诉详情
     */
    public static Observable getComplainDetail(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("cid", id + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getComplainDetail(paramsMap));
    }

    /**
     * 获取投诉反馈列表
     */
    public static Observable getComplainReply(int id, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("cid", id + "");
            tempMap.put("page", page + "");
            tempMap.put("limit", 20 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getComplainReply(paramsMap));
    }

    /**
     * 反馈信息
     */
    public static Observable feedbackMessage(int id, String content) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("cid", id + "");
            tempMap.put("content", content);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.feedbackMessage(paramsMap));
    }

    /**
     * 拜访人员
     */
    /*username	是	string	访问人姓名
mobile	是	string	电话
roomnum	是	string	访问房间号
reason	是	string	拜访原因
expertarrivaltime	是	string	到访时间2017-09-09 11：11
expertleavetime	是	string	离开时间2017-09-09 11：11
visitperiod	是	number	周期 1: 一次，2：一星期，3：半个月，4：长期
cartype	是	string	车类型
carcode	是	string	车牌号*/
    public static Observable postBaifagnInfo(String username, String mobile, String roomnum, String reason,
                                             String expertarrivaltime, String expertleavetime, String
                                                     visitperiod, String cartype, String carcode) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("username", username);
            tempMap.put("mobile", mobile);
            tempMap.put("roomnum", roomnum);
            tempMap.put("reason", reason);
            tempMap.put("expertarrivaltime", expertarrivaltime);
            tempMap.put("expertleavetime", expertleavetime);
            tempMap.put("visitperiod", visitperiod);
            tempMap.put("cartype", cartype);
            tempMap.put("carcode", carcode);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.postBaiFangInfo(paramsMap));
    }

    /*------------------------------------------信息公告----------------------------------------------*/

    /**
     * 获取我的信息公告分类
     */
    public static Observable getInformationClass(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("users", id + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getInformationClass(paramsMap));
    }


    /**
     * 获取信息列表
     */
    public static Observable getInformation(int typeid, int isRecommend, String keyword, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("typeid", typeid + "");
            tempMap.put("isRecommend", isRecommend + "");
            tempMap.put("keyword", keyword);
            tempMap.put("page", page + "");
            tempMap.put("limit", 20 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getInformation(paramsMap));
    }


    /**
     * 编辑我的分类
     */
    public static Observable editMyInformationClass(int[] ids) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
//            JSONArray json = new JSONArray();
//            for (int i = 0; i < ids.length; i++) {
//                json.put(ids[i]);
//            }
            tempMap.put("typeIdList", ids);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.updataInformationClass(paramsMap));
    }

    /**
     * 搜索指定信息列表
     */
    public static Observable searchInformation(int typeid, int isRecommend, String keyword, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("typeid", typeid + "");
            tempMap.put("isRecommend", isRecommend + "");
            tempMap.put("keyword", keyword);
            tempMap.put("page", page + "");
            tempMap.put("limit", 20 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getInformation(paramsMap));
    }

    /*------------------------------------------净菜预订----------------------------------------------*/

    /**
     * 获取蔬菜列表（包括搜索）
     */
    public static Observable getVegetableData(int page, String keyword) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("keyword", keyword);
            tempMap.put("page", page + "");
            tempMap.put("limit", 20 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getVegetableData(paramsMap));
    }

    /**
     * 提交净菜订单
     */
    public static Observable submitVegetableOrder(HashMap<String, String> tempMap) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            tempMap.put("limit", 20 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.submitVegetableOrder(paramsMap));
    }

    /**
     * 获取取餐时间
     */
    public static Observable getVegetableSelectTime() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getVegetableTime(paramsMap));
    }

    /*------------------------------------------服务管理----------------------------------------------*/

    /**
     * 获取我的服务
     */
    public static Observable getMyService() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getMyService(paramsMap));
    }

    /**
     * 更改我的服务
     */
    public static Observable submitMyService(int[] serviceID) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
//            JSONArray json = new JSONArray();
//            for (int i = 0; i < serviceID.length; i++) {
//                json.put(serviceID[i]);
//            }
            tempMap.put("serviceidList", serviceID);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.submitMyService(paramsMap));
    }

    /*------------------------------------------消息Tab----------------------------------------------*/

    /**
     * 获取消息tab
     */
    public static Observable getMessage() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getMessage(paramsMap));
    }

    /**
     * 获取消息列表
     */
    public static Observable getMessageList(int messageType, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("messagetype", messageType);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getMessageList(paramsMap));
    }

     /*--------------------------------------------取消各种订单----------------------------------------------*/

    /**
     * 获取取消订单原因
     */
    public static Observable getCancelReason(int serviceType) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("serviceType", serviceType);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getCancelReason(paramsMap));
    }

    /**
     * 取消报修订单
     */
    public static Observable cancelRepairOrder(int orderid, String cancelreason) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            tempMap.put("cancelreason", cancelreason);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelRepairsOrder(paramsMap));
    }

    /**
     * 取消办公用品订单
     */
    public static Observable cancelOfficeOrder(int oid, int[] reasonIds, String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            tempMap.put("reasonIds", reasonIds);
            tempMap.put("reasonContent", reasonContent);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelOfficeOrder(paramsMap));
    }

    /**
     * 取消订餐订单
     */
    public static Observable cancelMealOrder(int oid, int[] reasonIds, String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            tempMap.put("reasonIds", reasonIds);
            tempMap.put("reasonContent", reasonContent);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelMealOrder(paramsMap));
    }

    /**
     * 取消理发订单
     */
    public static Observable cancelHaircutsOrder(int orderid, String cancelreason) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            tempMap.put("cancelreason", cancelreason);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelHairCutOrder(paramsMap));
    }

    /**
     * 取消订水订单
     */
    public static Observable cancelWaterOrder(int oid, int[] reasonIds, String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            tempMap.put("reasonIds", reasonIds);
            tempMap.put("reasonContent", reasonContent);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelWaterOrder(paramsMap));
    }

    /**
     * 取消医疗订单
     */
    public static Observable cancelMedicalOrder(int orderid, String cancelreason) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            tempMap.put("cancelreason", cancelreason);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelMedicalOrder(paramsMap));
    }

    /**
     * 取消专家坐诊订单
     */
    public static Observable cancelExpertsOrder(int orderid, String cancelreason) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            tempMap.put("cancelreason", cancelreason);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelExpertsOrder(paramsMap));
    }

    /**
     * 取消图书订单
     */
    public static Observable cancelBookOrder(int orderid, String cancelreason) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            tempMap.put("cancelreason", cancelreason);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelBookOrder(paramsMap));
    }

    /**
     * 取消净菜订单
     */
    public static Observable cancelVegetableOrder(int oid, int[] reasonIds, String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            tempMap.put("reasonIds", reasonIds);
            tempMap.put("reasonContent", reasonContent);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelVegetableOrder(paramsMap));
    }

    /*--------------------------------------------各种订单----------------------------------------------*/

    /**
     * 查看报修订单
     * 1:待接单，2： 已接单，3：已完成，4:待评价，5：已取消
     *
     * @return
     */
    public static Observable getRepairOrder(int type, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("type", type);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.repairsOrderList(paramsMap));
    }

    /**
     * 查看办公用品订单
     * 订单状态 1: 已预约，2配送中3：已完成，4：待评价，5：已取消
     *
     * @return
     */
    public static Observable getOfficeOrder(int orderStatus, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderStatus", orderStatus);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.officeOrderList(paramsMap));
    }

    /**
     * 查看医疗订单
     * 订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
     *
     * @return
     */
    public static Observable getMedicalOrder(int type, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("type", type);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.medicalOrderList(paramsMap));
    }

    /**
     * 查看专家订单
     * 订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
     *
     * @return
     */
    public static Observable getExpertOrder(int type, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("type", type);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.expertOrderList(paramsMap));
    }

    /**
     * 查看理发订单
     * 订单状态 1: 已预约，2：服务中，3：已完成，4：待评价，5：已取消
     *
     * @return
     */
    public static Observable getHairCutOrder(int type, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("type", type);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.hairCutOrderList(paramsMap));
    }

    /**
     * 查看订餐订单
     * 订单状态 1: 已预约 3：已完成，4：待评价，5：已取消
     *
     * @return
     */
    public static Observable getMealOrder(int orderStatus, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderStatus", orderStatus);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.mealOrderList(paramsMap));
    }

    /**
     * 查看订水订单
     *
     * @return
     */
    public static Observable getWaterOrder(int type, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("type", type);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.waterOrderList(paramsMap));
    }

    /**
     * 查看净菜订单
     * 订单状态 1: 已预约，3：已完成，4：待评价，5：已取消
     *
     * @return
     */
    public static Observable getVegetableOrder(int orderStatus, int page) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderStatus", orderStatus);
            tempMap.put("page", page);
            tempMap.put("limit", 20);
            addParams(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.vegetableOrderList(paramsMap));
    }

    /*------------------------------------------删除各种订单------------------------------------------*/

    /**
     * 删除报修订单
     */
    public static Observable deleteRepairsOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteRepairsOrder(paramsMap));
    }

    /**
     * 删除办公用品订单
     */
    public static Observable deleteOfficeOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteOfficeOrder(paramsMap));
    }

    /**
     * 删除工作餐订单
     */
    public static Observable deleteMealOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteMealOrder(paramsMap));
    }

    /**
     * 删除理发订单
     */
    public static Observable deleteHairCutOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteHairCutOrder(paramsMap));
    }

    /**
     * 删除订水订单
     */
    public static Observable deleteWaterOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteWaterOrder(paramsMap));
    }

    /**
     * 删除医疗订单
     */
    public static Observable deleteMedicalOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteMedicalOrder(paramsMap));
    }

    /**
     * 删除专家坐诊订单
     */
    public static Observable deleteExpertsOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteExpertsOrder(paramsMap));
    }

    /**
     * 删除干洗订单
     */
    public static Observable deleteDryOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteDryOrder(paramsMap));
    }

    /**
     * 删除图书订单
     */
    public static Observable deleteBookOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteBookOrder(paramsMap));
    }

    /**
     * 删除净菜订单
     */
    public static Observable deleteVegetableOrder(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.deleteVegetableOrder(paramsMap));
    }

    /*------------------------------------------评论各种订单------------------------------------------*/

    /**
     * 报修订单评论
     */
    public static Observable commentRepairsOrder(int orderid, String content, int anonymous, List<String> files) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid + "");
            tempMap.put("content", content);
            tempMap.put("anonymous", anonymous + "");
            getRequestBody(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.commentRepairsOrder(paramsMap, getImgList(files, "files")));
    }

    /**
     * 办公用品订单评论
     */
    public static Observable commentOfficeOrder(int oid, int productId, boolean stars, String content, int isAnonymous, List<String> files) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("oid", oid + "");
            tempMap.put("productId", productId + "");
            tempMap.put("stars", stars + "");
            tempMap.put("content", content);
            tempMap.put("isAnonymous", isAnonymous + "");
            getRequestBody(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.commentOfficeOrder(paramsMap, getImgList(files, "files")));
    }

    /*------------------------------------------订单详情------------------------------------------*/

    /**
     * 医疗订单详情
     */
    public static Observable orderMedicalDetail(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderMedicalDetail(paramsMap));
    }

    /**
     * 专家订单详情
     */
    public static Observable orderExpertsDetail(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderExpertsDetail(paramsMap));
    }

    /**
     * 办公用品订单详情
     */
    public static Observable orderOfficeDetail(int oid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderOfficeDetail(paramsMap));
    }

    /**
     * 净菜订单详情
     */
    public static Observable orderVegetableDetail(int oid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderVegetableDetail(paramsMap));
    }

    /**
     * 报修订单详情
     */
    public static Observable orderRepairsDetailOne(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderRepairsDetail(paramsMap));
    }

    /**
     * 订水订单详情
     */
    public static Observable orderWaterDetail(int orderid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("orderid", orderid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderWaterDetail(paramsMap));
    }

    /**
     * 工作餐订单详情
     */
    public static Observable orderMealDetail(int oid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderMealDetail(paramsMap));
    }

    /**
     * 洗衣店订单详情
     */
    public static Observable orderDryDetail(int oid) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("oid", oid);
            addParams(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.orderDryDetail(paramsMap));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static Observable getUserInfo() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getUserInfo(paramsMap));
    }

    /**
     * 我的收藏
     *
     * @return
     */
    public static Observable addFavor(int type) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("type", type + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.addFavor(paramsMap));
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    public static Observable changeUserInfo(int sex, String nickname, String photo, int positionid,
                                            String tel, String roomnum, String mobile, String buildNum) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("sex", sex + "");
            tempMap.put("nickname", nickname);
            tempMap.put("photo", photo);
            tempMap.put("positionid", positionid + "");
            tempMap.put("tel", tel);
            tempMap.put("roomnum", roomnum);
            tempMap.put("mobile", mobile);
            tempMap.put("buildNum", buildNum);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.changeUserInfo(paramsMap));
    }

    /**
     * 钱包信息
     *
     * @return
     */
    public static Observable findUserWallet() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.findUserWallet(paramsMap));
    }

    /**
     * 明细
     *
     * @return
     */
    public static Observable findWalletLog() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.findWalletLog(paramsMap));
    }

    /**
     * 生成充值订单
     *
     * @return
     */
    public static Observable generateChargeWalletOrder(double money, int paytype, int ordertype) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("money", money + "");
            tempMap.put("paytype", paytype + "");
            tempMap.put("ordertype", ordertype + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.generateChargeWalletOrder(paramsMap));
    }

    /**
     * 充值记录
     *
     * @return
     */
    public static Observable findChargeOrder() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.findChargeOrder(paramsMap));
    }

    /**
     * 充值记录
     *
     * @return
     */
    public static Observable getUserDeposit() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getUserDeposit(paramsMap));
    }

    /**
     * 是否有交易密码
     *
     * @return
     */
    public static Observable hasPaypass() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.hasPaypass(paramsMap));
    }

    /**
     * 验证旧支付密码
     *
     * @return
     */
    public static Observable checkOldPassword(String pwd) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            Md5Util.getMD5(pwd);
            tempMap.put("paypass", pwd);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.checkOldPassword(paramsMap));
    }

    /**
     * 修改支付密码
     *
     * @return
     */
    public static Observable modifyCode(String pwd) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            Md5Util.getMD5(pwd);
            tempMap.put("code", pwd);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.modifyCode(paramsMap));
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    public static Observable upLoadHeader(File file) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        Log.e("postActivity", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            getRequestBody(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.upLoadHeader(paramsMap, getHeaderImg(file, "file")));
    }

}