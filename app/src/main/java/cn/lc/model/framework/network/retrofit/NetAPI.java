package cn.lc.model.framework.network.retrofit;

import java.util.List;
import java.util.Map;

import cn.lc.model.framework.widget.bean.BindPhoneBean;
import cn.lc.model.ui.home.bean.LoginBean;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.PositionListBean;
import cn.lc.model.ui.login.bean.RegistBean;
import cn.lc.model.ui.login.bean.SubmitAuthBean;
import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BarberListBean;
import cn.lc.model.ui.main.bean.BarberWorkListBean;
import cn.lc.model.ui.main.bean.BookDetailBean;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.DoctorDetailBean;
import cn.lc.model.ui.main.bean.DoctorListBean;
import cn.lc.model.ui.main.bean.HealthServerceHomeBean;
import cn.lc.model.ui.main.bean.LibraryHomeBean;
import cn.lc.model.ui.main.bean.MoreListBean;
import cn.lc.model.ui.main.bean.PreOrderBean;
import cn.lc.model.ui.main.bean.RecommandBookBean;
import cn.lc.model.ui.main.bean.ShopBean;
import cn.lc.model.ui.main.bean.UserCommentBean;
import cn.lc.model.ui.main.bean.WuyeHomeBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetAPI {

    @FormUrlEncoded
    @POST(NetUrl.signup)
    Observable<LoginBean> login (@FieldMap Map<String,Object> map);
    //获取验证码
    @FormUrlEncoded
    @POST(NetUrl.captcha)
    Observable<CaptchaBean> getCaptcha(@FieldMap Map<String,Object> map);
    //注册
    @FormUrlEncoded
    @POST(NetUrl.regist)
    Observable<RegistBean> register(@FieldMap Map<String,Object> map);
    //绑定手机号
    @FormUrlEncoded
    @POST(NetUrl.bindPhone)
    Observable<BindPhoneBean> bindPhone(@FieldMap Map<String,Object> map);
    //职位列表
    @FormUrlEncoded
    @POST(NetUrl.positionList)
    Observable<PositionListBean> getPositionList(@FieldMap Map<String,Object> map);
    //认证
    @FormUrlEncoded
    @POST(NetUrl.submitAuth)
    Observable<SubmitAuthBean> submitAuth(@FieldMap Map<String,Object> map);
    //医疗首页
    @FormUrlEncoded
    @POST(NetUrl.healthServiceHome)
    Observable<HealthServerceHomeBean> getHomeData(@FieldMap Map<String,Object> map);
    //健康资讯收藏
    @FormUrlEncoded
    @POST(NetUrl.healthServiceHome)
    Observable<CollectBean> getCollectResult(@FieldMap Map<String,Object> map);
    //获取更多资讯列表
    @FormUrlEncoded
    @POST(NetUrl.more)
    Observable<MoreListBean> getMoreList(@FieldMap Map<String,Object> map);
    //医生列表
    @FormUrlEncoded
    @POST(NetUrl.doctorList)
    Observable<DoctorListBean> getDoctorList(@FieldMap Map<String,Object> map);
    //医生详情
    @FormUrlEncoded
    @POST(NetUrl.doctorDetail)
    Observable<DoctorDetailBean> getDoctorDetail(@FieldMap Map<String,Object> map);
    //获得医生评论列表
    @FormUrlEncoded
    @POST(NetUrl.userCommentList)
    Observable<UserCommentBean> getUserCommentList(@FieldMap Map<String,Object> map);

    //物业首页
    /*@FormUrlEncoded
    @POST(NetUrl.wuyeHome)
    Observable<WuyeHomeBean> getWuyeHomeInfo(@FieldMap Map<String,Object> map);*/
    @Multipart
    @POST(NetUrl.wuyeHome)
    Observable<WuyeHomeBean> getWuyeHomeInfo(@PartMap() Map<String, RequestBody> map, @Part List<MultipartBody.Part> partList);

    //图书首页
    @FormUrlEncoded
    @POST(NetUrl.libraryHome)
    Observable<LibraryHomeBean> getLibraryHomeData(@FieldMap Map<String,Object> map);
    //推荐图书
    @FormUrlEncoded
    @POST(NetUrl.recommandBook)
    Observable<RecommandBookBean> getRecommandResult(@FieldMap Map<String,Object> map);
    //图书详情
    @FormUrlEncoded
    @POST(NetUrl.bookdetail)
    Observable<BookDetailBean> getBookDetailResult(@FieldMap Map<String,Object> map);
    //店铺
    @FormUrlEncoded
    @POST(NetUrl.shop)
    Observable<ShopBean> getShopInfo(@FieldMap Map<String,Object> map);
    //理发师列表
    @FormUrlEncoded
    @POST(NetUrl.barberList)
    Observable<BarberListBean> getBarberList(@FieldMap Map<String,Object> map);
    //理发师详情
    @FormUrlEncoded
    @POST(NetUrl.barberDetail)
    Observable<BarberDetailBean> getBarberDetailInfo(@FieldMap Map<String,Object> map);
    //理发师作品列表
    @FormUrlEncoded
    @POST(NetUrl.barberworklist)
    Observable<BarberWorkListBean> getbarberworklist(@FieldMap Map<String,Object> map);
    //预约理发师
    @FormUrlEncoded
    @POST(NetUrl.preorder)
    Observable<PreOrderBean> getbarberInfo(@FieldMap Map<String,Object> map);
}
