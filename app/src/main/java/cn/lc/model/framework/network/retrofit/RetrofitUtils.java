package cn.lc.model.framework.network.retrofit;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

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
import cn.lc.model.framework.network.ParameterKeys;
import cn.lc.model.framework.network.ServerConstants;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import mvp.cn.util.DateUtil;
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
        Log.e("token==",SharedPrefHelper.getInstance().getToken());
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
           Log.e("getToken请求拼接参数：", SharedPrefHelper.getInstance().getToken());
            //paramsMap.put("TOKEN", SharedPrefHelper.getInstance().getToken());
            paramsMap.put("token", SharedPrefHelper.getInstance().getToken());

        } else {
            //LogUtil.log(SharedPrefHelper.getInstance().getyouke() + "");
            if (SharedPrefHelper.getInstance().getyouke() == true) {
               // paramsMap.put("TOKEN", "0");
                paramsMap.put("token", "0");
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
    /*private static void getRequestBody(Map<String, RequestBody> paramsMap, Map<String, String> tempMap) {
        Gson gson = new Gson();
        String biz = gson.toJson(tempMap);
        RequestBody requestbiz = RequestBody.create(MediaType.parse("multipart/form-data"), biz);
       // paramsMap.put(ParameterKeys.BIZ, requestbiz);
        //  LogUtils.d("biz请求参数：" + biz);
       // String device=gson.toJson(getdevice());
        //RequestBody requestdevice = RequestBody.create(MediaType.parse("multipart/form-data"), device);
        //paramsMap.put(ParameterKeys.DEVICE,requestdevice);
        //    LogUtils.d("device请求参数：" + device);
        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
        RequestBody requesttimestamp = RequestBody.create(MediaType.parse("multipart/form-data"),timestamp);
       // paramsMap.put(ParameterKeys.TIMESTAMP, requesttimestamp);
        //     LogUtils.d("时间戳请求参数：" + timestamp);
        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
            //        LogUtils.d("请求参数：getToken" + SharedPrefHelper.getInstance().getToken());
          //  paramsMap.put(TOKEN, RequestBody.create(MediaType.parse("multipart/form-data"), SharedPrefHelper.getInstance().getToken()));
        } else {
          //  LogUtil.log(SharedPrefHelper.getInstance().getyouke() + "。。。。。。。。。");
            if (SharedPrefHelper.getInstance().getyouke() == true) {
              //  paramsMap.put(TOKEN,RequestBody.create(MediaType.parse("multipart/form-data"), "0"));
            } else {
            }
        }
        try {
           // RequestBody requestsign= RequestBody.create(MediaType.parse("multipart/form-data"),getSign(device,biz, timestamp));
          //  paramsMap.put(ParameterKeys.SIGN, requestsign);
            //       LogUtils.d("sign戳请求参数：" + getSign(device,biz, timestamp));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static List<MultipartBody.Part> getImgList(ArrayList<String> paths, String s) {
        List<MultipartBody.Part> parts = new ArrayList<>();

        for (int i = 0; i < paths.size(); i++) {
            //这里采用的Compressor图片压缩
            LogUtils.d("图片地址：" +paths.get(i));
            File file = new Compressor.Builder(SoftApplication.softApplication)
                    .setMaxWidth(720)
                    .setMaxHeight(1080)
                    .setQuality(80)
                    .setCompressFormat(Bitmap.CompressFormat.PNG)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(new File(paths.get(i)));

            RequestBody requestFile = RequestBody.create(MediaType.parse("image*//*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(s, file.getName(), requestFile);
            parts.add(part);
        }
        return parts;
    }*/

    /**
     * 上传图片
     *
     * @return
     *
     */
   /* public static Observable UpImages(String s1,String s2,String s3,ArrayList<String> paths) {
        Map<String, RequestBody> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();

            tempMap.put("clubId ", s1);
            tempMap.put("albumId", s2);
            tempMap.put("albumTitle", s3);
           // getRequestBody(paramsMap, tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getObservable(api.getWuyeHomeInfo(paramsMap,getImgList(paths,"albumPhoto")));

    }*/



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
     绑定手机号
     */
    public static Observable bindPhone(int loginType,String userName, String thirdNum,
                                       String isRegister,String password,String captcha) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("loginType", loginType+"");
            tempMap.put("userName", userName);
            tempMap.put("thirdNum", thirdNum);
            tempMap.put("isRegister", isRegister);
            tempMap.put("password", password);
            tempMap.put("captcha", captcha);

            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.register(paramsMap));
    }
    /**
     * 职位列表
     * */
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
     * */
    public static Observable submitAuth(String name,String mobile,
    String idCard,int positionid,String roomId,String officetel,
                                        String cartypeId,String precarCode,String suffixcarCode) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("name",name);
            tempMap.put("mobile",mobile);
            tempMap.put("idCard",idCard);
            tempMap.put("positionid",positionid+"");
            tempMap.put("roomId",roomId);
            tempMap.put("officetel",officetel);
            tempMap.put("cartypeId",cartypeId);
            tempMap.put("precarCode",precarCode);
            tempMap.put("suffixcarCode",suffixcarCode);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getPositionList(paramsMap));
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
        return getObservable(api.getDoctorDetail(paramsMap));
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
     * 更多资讯列表
     * @return
     */
    public static Observable getMoreList(int page,int limit,String keywords) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getCommentList","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("page",page+"");
            tempMap.put("limit",limit+"");
            tempMap.put("keywords",keywords);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getMoreList(paramsMap));
    }
    /**
     * 获取医生详情里的用户评论列表
     * @return
     */
    public static Observable getUserCommentList(int doctorId,int page,int limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getUserCommentList","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("doctorid",doctorId+"");
            tempMap.put("page",page+"");
            tempMap.put("limit",limit+"");
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getUserCommentList(paramsMap));
    }
    /**
     * 物业首页
     * @return
     */
    public static Observable getWuyeHomeInfo(int menditem,String username,int mobile,
         String invitetime,String serviceplace,String mendcontent,File files) {
        Map<String, Object> paramsMap = new HashMap<>();
        Log.e("getUserCommentList","---》home");
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("menditem",menditem+"");
            tempMap.put("username",username);
            tempMap.put("mobile",mobile+"");
            tempMap.put("invitetime",invitetime);
            tempMap.put("serviceplace",serviceplace);
            tempMap.put("mendcontent",mendcontent);
            addParam(paramsMap, tempMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getObservable(api.getUserCommentList(paramsMap));
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
