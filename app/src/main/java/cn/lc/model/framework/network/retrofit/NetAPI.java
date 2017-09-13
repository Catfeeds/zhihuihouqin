package cn.lc.model.framework.network.retrofit;

import java.util.List;
import java.util.Map;

import cn.lc.model.framework.widget.bean.BindPhoneBean;
import cn.lc.model.ui.home.bean.LoginBean;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.PositionListBean;
import cn.lc.model.ui.login.bean.RegistBean;
import cn.lc.model.ui.login.bean.SubmitAuthBean;
import cn.lc.model.ui.main.bean.ActivityHomeBean;
import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.bean.ActivitySignBean;
import cn.lc.model.ui.main.bean.ActivitySignListBean;
import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BarberListBean;
import cn.lc.model.ui.main.bean.BarberWorkListBean;
import cn.lc.model.ui.main.bean.BookDetailBean;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ComplainDetailBean;
import cn.lc.model.ui.main.bean.ComplainHistoryBean;
import cn.lc.model.ui.main.bean.ComplainReplyBean;
import cn.lc.model.ui.main.bean.DoctorDetailBean;
import cn.lc.model.ui.main.bean.DoctorListBean;
import cn.lc.model.ui.main.bean.HealthServerceHomeBean;
import cn.lc.model.ui.main.bean.InformationBean;
import cn.lc.model.ui.main.bean.InformationClazzBean;
import cn.lc.model.ui.main.bean.JieYueBean;
import cn.lc.model.ui.main.bean.LabellingBean;
import cn.lc.model.ui.main.bean.LibraryHomeBean;
import cn.lc.model.ui.main.bean.LibraryPicBean;
import cn.lc.model.ui.main.bean.MoreListBean;
import cn.lc.model.ui.main.bean.NutritionBean;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;
import cn.lc.model.ui.main.bean.PreOrderBean;
import cn.lc.model.ui.main.bean.ReasonBean;
import cn.lc.model.ui.main.bean.RecommandBookBean;
import cn.lc.model.ui.main.bean.SearchBookListBean;
import cn.lc.model.ui.main.bean.SearchCategoryBean;
import cn.lc.model.ui.main.bean.SelectTimeBean;
import cn.lc.model.ui.main.bean.ServiceBean;
import cn.lc.model.ui.main.bean.ShopBean;
import cn.lc.model.ui.main.bean.UserCommentBean;
import cn.lc.model.ui.main.bean.WuyeHomeBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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
    Observable<LoginBean> login(@FieldMap Map<String, Object> map);

    //获取验证码
    @FormUrlEncoded
    @POST(NetUrl.captcha)
    Observable<CaptchaBean> getCaptcha(@FieldMap Map<String, Object> map);

    //注册
    @FormUrlEncoded
    @POST(NetUrl.regist)
    Observable<RegistBean> register(@FieldMap Map<String, Object> map);

    //更改密码
    @FormUrlEncoded
    @POST(NetUrl.changePassWord)
    Observable<RegistBean> changePassWord(@FieldMap Map<String, Object> map);

    //绑定手机号
    @FormUrlEncoded
    @POST(NetUrl.bindPhone)
    Observable<BindPhoneBean> bindPhone(@FieldMap Map<String, Object> map);

    //职位列表
    @FormUrlEncoded
    @POST(NetUrl.positionList)
    Observable<PositionListBean> getPositionList(@FieldMap Map<String, Object> map);

    //认证
    @FormUrlEncoded
    @POST(NetUrl.submitAuth)
    Observable<SubmitAuthBean> submitAuth(@FieldMap Map<String, Object> map);

    //医疗首页
    @FormUrlEncoded
    @POST(NetUrl.healthServiceHome)
    Observable<HealthServerceHomeBean> getHomeData(@FieldMap Map<String, Object> map);

    //健康资讯收藏
    @FormUrlEncoded
    @POST(NetUrl.healthServiceHome)
    Observable<CollectBean> getCollectResult(@FieldMap Map<String, Object> map);

    //获取更多资讯列表
    @FormUrlEncoded
    @POST(NetUrl.more)
    Observable<MoreListBean> getMoreList(@FieldMap Map<String, Object> map);

    //医生列表
    @FormUrlEncoded
    @POST(NetUrl.doctorList)
    Observable<DoctorListBean> getDoctorList(@FieldMap Map<String, Object> map);

    //医生详情
    @FormUrlEncoded
    @POST(NetUrl.doctorDetail)
    Observable<DoctorDetailBean> getDoctorDetail(@FieldMap Map<String, Object> map);

    //获得医生评论列表
    @FormUrlEncoded
    @POST(NetUrl.userCommentList)
    Observable<UserCommentBean> getUserCommentList(@FieldMap Map<String, Object> map);

    //物业首页
    /*@FormUrlEncoded
    @POST(NetUrl.wuyeHome)
    Observable<WuyeHomeBean> getWuyeHomeInfo(@FieldMap Map<String,Object> map);*/
    @Multipart
    @POST(NetUrl.wuyeHome)
    Observable<WuyeHomeBean> getWuyeHomeInfo(@PartMap() Map<String, RequestBody> map, @Part List<MultipartBody.Part> partList);
    //活动首页
    @FormUrlEncoded
    @POST(NetUrl.activityHome)
    Observable<ActivityHomeBean> getActivityHome(@FieldMap Map<String, Object> map);
    //活动发布
    @FormUrlEncoded
    @POST(NetUrl.activityPost)
    Observable<ActivityPostBean> postActivity(@FieldMap Map<String, Object> map);
    //活动包名
    @FormUrlEncoded
    @POST(NetUrl.activitySign)
    Observable<ActivitySignBean> getActivitySign(@FieldMap Map<String, Object> map);
    //活动报名列表
    @FormUrlEncoded
    @POST(NetUrl.activityList)
    Observable<ActivitySignListBean> getActivitySignList(@FieldMap Map<String, Object> map);

    //图书首页
    @FormUrlEncoded
    @POST(NetUrl.libraryHome)
    Observable<LibraryHomeBean> getLibraryHomeData(@FieldMap Map<String, Object> map);
    //图书首页
    @FormUrlEncoded
    @POST(NetUrl.libraryIndex)
    Observable<LibraryPicBean> getLibraryHomePic(@FieldMap Map<String, Object> map);

    //推荐图书
    @FormUrlEncoded
    @POST(NetUrl.recommandBook)
    Observable<RecommandBookBean> getRecommandResult(@FieldMap Map<String, Object> map);

    //图书详情
    @FormUrlEncoded
    @POST(NetUrl.bookdetail)
    Observable<BookDetailBean> getBookDetailResult(@FieldMap Map<String, Object> map);
    //图书借阅
    @FormUrlEncoded
    @POST(NetUrl.jieyue)
    Observable<JieYueBean> jieYueBook(@FieldMap Map<String, Object> map);
    //查询类别
    @FormUrlEncoded
    @POST(NetUrl.searchCategory)
    Observable<SearchCategoryBean> getSearchCategory(@FieldMap Map<String, Object> map);
    //查询书列表
    @FormUrlEncoded
    @POST(NetUrl.searchBookList)
    Observable<SearchBookListBean> getSearchBookList(@FieldMap Map<String, Object> map);

    //店铺
    @FormUrlEncoded
    @POST(NetUrl.shop)
    Observable<ShopBean> getShopInfo(@FieldMap Map<String, Object> map);

    //理发师列表
    @FormUrlEncoded
    @POST(NetUrl.barberList)
    Observable<BarberListBean> getBarberList(@FieldMap Map<String, Object> map);

    //理发师详情
    @FormUrlEncoded
    @POST(NetUrl.barberDetail)
    Observable<BarberDetailBean> getBarberDetailInfo(@FieldMap Map<String, Object> map);

    //理发师作品列表
    @FormUrlEncoded
    @POST(NetUrl.barberworklist)
    Observable<BarberWorkListBean> getbarberworklist(@FieldMap Map<String, Object> map);

    //预约理发师
    @FormUrlEncoded
    @POST(NetUrl.preorder)
    Observable<PreOrderBean> getbarberInfo(@FieldMap Map<String, Object> map);

    //干洗店
    @FormUrlEncoded
    @POST(NetUrl.dryCleanerHome)
    Observable<ServiceBean> getDryCleanerHomeInfo(@FieldMap Map<String, Object> map);
    //预约干洗
    @FormUrlEncoded
    @POST(NetUrl.orderDryCleaner)
    Observable<OrderDryCleanBean> orderDryClean(@FieldMap Map<String, Object> map);

    //生成工作餐订单
    @FormUrlEncoded
    @POST(NetUrl.createOrder)
    Observable<CollectBean> createOrdering(@FieldMap Map<String, Object> map);

    //生成工作餐订单
    @FormUrlEncoded
    @POST(NetUrl.getTime)
    Observable<SelectTimeBean> getTime(@FieldMap Map<String, Object> map);

    //获取我的地址
    @FormUrlEncoded
    @POST(NetUrl.getAddress)
    Observable<AddressBean> getAddress(@FieldMap Map<String, Object> map);

    //添加地址
    @FormUrlEncoded
    @POST(NetUrl.addAddress)
    Observable<CollectBean> addAddress(@FieldMap Map<String, Object> map);

    //修改地址
    @FormUrlEncoded
    @POST(NetUrl.editAddress)
    Observable<CollectBean> editAddress(@FieldMap Map<String, Object> map);

    //删除地址
    @FormUrlEncoded
    @POST(NetUrl.deleteAddress)
    Observable<CollectBean> deleteAddress(@FieldMap Map<String, Object> map/*, @Query("ids[]") int... ids*/);

    //获取取消订单原因
    @FormUrlEncoded
    @POST(NetUrl.orderReason)
    Observable<ReasonBean> getReason(@FieldMap Map<String, Object> map);

    //取消工作餐订单
    @FormUrlEncoded
    @POST(NetUrl.cancelOrder)
    Observable<CollectBean> cancelOrder(@FieldMap Map<String, Object> map, @Query("linked[]") int[] linked);

    //获取营养套餐列表
    @FormUrlEncoded
    @POST(NetUrl.getNutritionData)
    Observable<NutritionBean> getNutritionData(@FieldMap Map<String, Object> map);

    //获取今日菜谱
    @FormUrlEncoded
    @POST(NetUrl.getNutritionData)
    Observable<NutritionBean> getTodayRecipe(@FieldMap Map<String, Object> map);

    //获取套餐Banner
    @FormUrlEncoded
    @POST(NetUrl.getNutritionData)
    Observable<NutritionBean> getNutritionBanner(@FieldMap Map<String, Object> map);

    // 提交意见投诉
//    @FormUrlEncoded
    @Multipart
    @POST(NetUrl.submitComplain)
    Observable<CollectBean> submitComplain(@PartMap() Map<String, RequestBody> paramsMap, @Part List<MultipartBody.Part> albumPhoto);

    // 获取意见投诉标签
    @FormUrlEncoded
    @POST(NetUrl.getLabelling)
    Observable<LabellingBean> getLabelling(@FieldMap Map<String, Object> map);

    // 获取意见投诉历史
    @FormUrlEncoded
    @POST(NetUrl.complainHistory)
    Observable<ComplainHistoryBean> getComplainHistory(@FieldMap Map<String, Object> map);

    // 投诉详情
    @FormUrlEncoded
    @POST(NetUrl.complainDetail)
    Observable<ComplainDetailBean> getComplainDetail(@FieldMap Map<String, Object> map);

    // 投诉反馈列表
    @FormUrlEncoded
    @POST(NetUrl.complainReply)
    Observable<ComplainReplyBean> getComplainReply(@FieldMap Map<String, Object> map);

    // 反馈新信息
    @FormUrlEncoded
    @POST(NetUrl.feedbackMessage)
    Observable<CollectBean> feedbackMessage(@FieldMap Map<String, Object> map);

    // 获取信息公告分类
    @FormUrlEncoded
    @POST(NetUrl.informationClass)
    Observable<InformationClazzBean> getInformationClass(@FieldMap Map<String, Object> map);

    // 获取信息公告列表
    @FormUrlEncoded
    @POST(NetUrl.informationData)
    Observable<InformationBean> getInformation(@FieldMap Map<String, Object> map);

    // 修改用户分类
    @FormUrlEncoded
    @POST(NetUrl.updataInformationClass)
    Observable<CollectBean> updataInformationClass(@FieldMap Map<String, Object> map);

    // 上传图片
    @Multipart
    @POST(NetUrl.submitComplain)
    Observable<CollectBean> UpImage(@PartMap() Map<String, RequestBody> paramsMap, @Part List<MultipartBody.Part> albumPhoto);
//    Observable<CollectBean> UpImage(@Part Map<String, RequestBody> paramsMap, @PartMap List<MultipartBody.Part> albumPhoto);
    // 拜访人员
    @FormUrlEncoded
    @POST(NetUrl.baifang)
    Observable<ActivityPostBean> postBaiFangInfo(@FieldMap Map<String, Object> map);
}
