package cn.lc.model.framework.network.retrofit;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.network.AppConstants;
import cn.lc.model.framework.network.ServerConstants;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
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

import static cn.lc.model.framework.network.retrofit.ParameterKeys.TOKEN;

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
        LogUtils.d("时间戳请求参数：" + timestamp);
        LogUtils.d("token==" + SharedPrefHelper.getInstance().getToken());
        //LogUtils.d("时间戳请求参数：" + timestamp);
        Log.e("token==", SharedPrefHelper.getInstance().getToken() + "测试");
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
            //LogUtils.d("getToken请求参数：" + SharedPrefHelper.getInstance().getToken());
            paramsMap.put(TOKEN, SharedPrefHelper.getInstance().getToken());

        } else {
            //LogUtil.log(SharedPrefHelper.getInstance().getyouke() + "");
            if (SharedPrefHelper.getInstance().getyouke()) {
                paramsMap.put(TOKEN, "0");
            } else {
            }
        }
        try {
            String sign = getSign(biz, timestamp, "zxcadsadwa@2321$");
            LogUtils.d("sign:" + sign);
            Log.e("sign戳请求参数：", sign);
            paramsMap.put("sign", sign);
            //   LogUtils.d("sign戳请求参数：" + getSign(device,biz, timestamp));

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


    private static List<MultipartBody.Part> getImgList(ArrayList<String> paths, String s) {
        List<MultipartBody.Part> parts = new ArrayList<>();

        for (int i = 0; i < paths.size(); i++) {
            //这里采用的Compressor图片压缩
            LogUtils.d("图片地址：" + paths.get(i));
            File file = new Compressor.Builder(SoftApplication.softApplication)
                    .setMaxWidth(720)
                    .setMaxHeight(1080)
                    .setQuality(80)
                    .setCompressFormat(Bitmap.CompressFormat.PNG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(new File(paths.get(i)));

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
                                        String cartypeId, String precarCode, String suffixcarCode) {
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
        Log.e("getCommentList", "---》home");
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
     * 获取医生详情里的用户评论列表
     *
     * @return
     */
    public static Observable getUserCommentList(int doctorId, int page, int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getUserCommentList", "---》home");
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
     * 物业首页
     *
     * @return
     */
    public static Observable getWuyeHomeInfo(int menditem, String username, int mobile,
                                             String invitetime, String serviceplace, String mendcontent, File files) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getUserCommentList", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("menditem", menditem + "");
            tempMap.put("username", username);
            tempMap.put("mobile", mobile + "");
            tempMap.put("invitetime", invitetime);
            tempMap.put("serviceplace", serviceplace);
            tempMap.put("mendcontent", mendcontent);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getUserCommentList(paramsMap));
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
            , String aSponsor, String aTime, String aTitle, String aTotal, File photos, File imgs, String aRealname,
                                          String aNaion) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("postActivity", "---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("aContactMobile", aContactMobile);
            tempMap.put("aContent", aContent);
            tempMap.put("aPlace", aPlace);
            tempMap.put("aTime", aTime);
            tempMap.put("aTitle", aTitle);
            tempMap.put("aTotal", aTotal);
            //图片文件上传

            tempMap.put("aRealname", aRealname);
            tempMap.put("aNaion", aNaion);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.postActivity(paramsMap));
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
        Log.e("getJieyueTime","---》bookDetail");
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
    public static Observable bookOrderList(String typeid) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("bookOrderList","---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap();
            tempMap.put("type",typeid);
            addParam(paramsMap, tempMap);

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
            tempMap.put("id", id + "");
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
    }/**
     * 提交预约
     *
     * @return
     */
    public static Observable dryOrderCommit(String mobile ,String expectarrivaItme,
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
    public static Observable checkDryOrder(int orderStatus,int page,int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("orderStatus", orderStatus+"");
            tempMap.put("page", page+"");
            tempMap.put("limit", limit+"");
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
    public static Observable getDryCancelList() {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDryCancelList(paramsMap));
    }
    /**
     * 获取干洗取消列表
     *
     * @return
     */
    public static Observable commitDryCancel(int old,String reasonIds,String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("oid",old+"");
            tempMap.put("reasonIds",reasonIds);
            tempMap.put("reasonContent",reasonContent);
            addParam(paramsMap, tempMap);

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
            tempMap.put("oid",oid+"");
            tempMap.put("stars",stars+"");
            tempMap.put("content",content);
            tempMap.put("isAnonymous",isAnonymous);
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
                                           String page,String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("categoryOneId",categoryOneId);
            tempMap.put("page",page);
            tempMap.put("limit",limit);
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
            tempMap.put("productId",productId);
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
            tempMap.put("productId",productId);
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
    public static Observable getSpAllComment(String productId,String commentType ,String page,String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("productId",productId);
            tempMap.put("commentType",commentType);
            tempMap.put("page",page);
            tempMap.put("limit",limit);
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
            tempMap.put("productId",productId);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getShopCarInfo(paramsMap));
    }
    /**
     * 购物车
     *
     * @return
     */
    public static Observable shopCar(String skuid,String count) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("skuid",skuid);
            tempMap.put("count",count);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getShopCarInfo(paramsMap));
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
     * 提交
     *
     * @return
     */
    public static Observable cancelOrder(int oid, int[] reasonIds, String reasonContent) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("oid", oid + "");
            tempMap.put("reasonContent", reasonContent);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.cancelOrder(paramsMap));
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
        return getObservable(api.getInformationClass(paramsMap));
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
            Map<String, String> tempMap = new HashMap<>();
            JSONArray json = new JSONArray();
            for (int i = 0; i < ids.length; i++) {
                json.put(ids[i]);
            }
            tempMap.put("typeIdList", json.toString());
            addParam(paramsMap, tempMap);

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
     *  提交净菜订单
     */
    public static Observable submitVegetableOrder(HashMap<String, String> tempMap) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
//            Map<String, String> tempMap = new HashMap<>();
//            tempMap.put("keyword", keyword);
//            tempMap.put("page", page + "");
            tempMap.put("limit", 20 + "");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.submitVegetableOrder(paramsMap));
    }

}
