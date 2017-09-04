package cn.lc.model.framework.network.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lc.model.framework.network.AppConstants;
import cn.lc.model.framework.network.ParameterKeys;
import cn.lc.model.framework.network.ServerConstants;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import mvp.cn.util.DateUtil;
import mvp.cn.util.Md5Util;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        //Log.d("biz请求参数：" + biz);
        Log.e("biz", biz);
        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
        paramsMap.put("timestamp", timestamp);
        //LogUtils.d("时间戳请求参数：" + timestamp);
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
            //LogUtils.d("getToken请求参数：" + SharedPrefHelper.getInstance().getToken());
            paramsMap.put("TOKEN", SharedPrefHelper.getInstance().getToken());

        } else {
            //LogUtil.log(SharedPrefHelper.getInstance().getyouke() + "");
            if (SharedPrefHelper.getInstance().getyouke() == true) {
                paramsMap.put("TOKEN", "0");
            } else {
            }
        }
        try {
            paramsMap.put("sign", getSign(biz, timestamp, "zxcadsadwa@2321$"));
            //   LogUtils.d("sign戳请求参数：" + getSign(device,biz, timestamp));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
            tempMap.put("userName", uid);
            tempMap.put("password", pwd);

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
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    public static Observable register(String mobile, String code,String password) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("phone", mobile);
            tempMap.put("captcha", code);
            tempMap.put("password", password);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.register(paramsMap));
    }

    /**
     * 医疗健康服务首页
     * @return
     */
    public static Observable getHealthServiceHomeData() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getHealthServiceHome","---》home");
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
     * @return
     */
    public static Observable getDoctorList() {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getDoctorList","---》home");
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
     * @return
     */
    public static Observable getDoctorDetail(int doctorId) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getDoctorDetail","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("doctorId",doctorId+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getDoctorList(paramsMap));
    }
    /**
     * 健康资讯收藏
     * @return
     */
    public static Observable getHealthInfoColect(int i1,int i2) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getHealthInfoColect","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("type",i1+"");
            tempMap.put("entityid",i2+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getCollectResult(paramsMap));
    }
    /**
     * 图书馆首页
     * @return
     */
    public static Observable getLibraryHomeData(int position) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getLibraryHomeData","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("position",position+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getLibraryHomeData(paramsMap));
    }
    /**
     * 推荐图书
     * @return
     */
    public static Observable getRecommandResult(String title,String author,String publisher,String remark) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getLibraryHomeData","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("title",title);
            tempMap.put("author",author);
            tempMap.put("publisher",publisher);
            tempMap.put("remark",remark);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getRecommandResult(paramsMap));
    }
    /**
     * 图书详情
     * @return
     */
    public static Observable getBookDetailResult(int bookId) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getBookDetailResult","---》bookDetail");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("bookId",bookId+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getBookDetailResult(paramsMap));
    }
    /**
     * 理发店铺
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
     *理发师列表
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
     *理发师详情信息
     * @return
     */
    public static Observable getBarberDetail(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("id",id+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getBarberDetailInfo(paramsMap));
    }
    /**
     *理发师作品列表
     * @return
     */
    public static Observable getBarberProductList(String id,String page,String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberId",id);
            tempMap.put("page",page);
            tempMap.put("limit",limit);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getbarberworklist(paramsMap));
    }
    /**
     *预约理发师
     * @return
     */
    public static Observable preOrderBarber(int id) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("barberId",id+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getbarberInfo(paramsMap));
    }
}
