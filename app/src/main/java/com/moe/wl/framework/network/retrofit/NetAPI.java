package com.moe.wl.framework.network.retrofit;

import com.moe.wl.framework.widget.bean.BindPhoneBean;
import com.moe.wl.ui.home.bean.EnergyCostQueryBean;
import com.moe.wl.ui.home.bean.LoginBean;
import com.moe.wl.ui.home.bean.RepairTimeBean;
import com.moe.wl.ui.home.bean.SaveHomeBanner;
import com.moe.wl.ui.home.bean.office.AffirmOrderResponse;
import com.moe.wl.ui.home.bean.office.OfficeDetailsResponse;
import com.moe.wl.ui.home.bean.office.OfficeListResponse;
import com.moe.wl.ui.home.bean.office.SubscribeInfoResponse;
import com.moe.wl.ui.home.bean.office.SubscribeTimeResponse;
import com.moe.wl.ui.home.bean.saving.EnergyCostCompare;
import com.moe.wl.ui.home.bean.saving.InforMationDetailBean;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;
import com.moe.wl.ui.login.bean.CaptchaBean;
import com.moe.wl.ui.login.bean.PositionListBean;
import com.moe.wl.ui.login.bean.RegistBean;
import com.moe.wl.ui.login.bean.SubmitAuthBean;
import com.moe.wl.ui.main.bean.*;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    //三方登录
    @FormUrlEncoded
    @POST(NetUrl.thirdLogin)
    Observable<LoginBean> thirdLogin(@FieldMap Map<String, Object> map);

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

    //民族
    @FormUrlEncoded
    @POST(NetUrl.nationlist)
    Observable<NationslistBean> getNationlist(@FieldMap Map<String, Object> map);

    //部门列表
    @FormUrlEncoded
    @POST(NetUrl.getdepartList)
    Observable<DepartsListBean> getdepartList(@FieldMap Map<String, Object> map);

    //处室列表
    @FormUrlEncoded
    @POST(NetUrl.getofficelist)
    Observable<OfficeslistBean> getofficelist(@FieldMap Map<String, Object> map);

    //车辆类别列表
    @FormUrlEncoded
    @POST(NetUrl.cartypelist)
    Observable<CartypeslistBean> getcartypelist(@FieldMap Map<String, Object> map);

    //认证
    @FormUrlEncoded
    @POST(NetUrl.submitAuth)
    Observable<SubmitAuthBean> submitAuth(@FieldMap Map<String, Object> map);

    //账号申诉
    @FormUrlEncoded
    @POST(NetUrl.accountComplain)
    Observable<CollectBean> accountComplain(@FieldMap Map<String, Object> map);

    // 首页数据
    @FormUrlEncoded
    @POST(NetUrl.homePage)
    Observable<HomePageBean> homePageData(@FieldMap Map<String, Object> map);

    // 首页服务搜索
    @FormUrlEncoded
    @POST(NetUrl.search)
    Observable<HomeSearchBean> homeSearch(@FieldMap Map<String, Object> map);

    //医疗首页
    @FormUrlEncoded
    @POST(NetUrl.healthServiceHome)
    Observable<HealthServerceHomeBean> getHomeData(@FieldMap Map<String, Object> map);

    //健康资讯收藏
    @FormUrlEncoded
    @POST(NetUrl.healthInfoCollect)
    Observable<CollectBean> getCollectResult(@FieldMap Map<String, Object> map);

    //获取更多资讯列表
    @FormUrlEncoded
    @POST(NetUrl.more)
    Observable<MoreListBean> getMoreList(@FieldMap Map<String, Object> map);

    // 获取资讯详情
    @FormUrlEncoded
    @POST(NetUrl.getMedicalDetail)
    Observable<MedicalDetailBean> getMedicalDetail(@FieldMap Map<String, Object> map);

    // 评论资讯
    @FormUrlEncoded
    @POST(NetUrl.commentMedicalDetail)
    Observable<CollectBean> commentMedicalDetail(@FieldMap Map<String, Object> map);

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

    // 获取专家详情
    @FormUrlEncoded
    @POST(NetUrl.expertDetail)
    Observable<ExpertDetailBean> getExpertDetail(@FieldMap Map<String, Object> map);

    // 获得专家评论列表
    @FormUrlEncoded
    @POST(NetUrl.expertComment)
    Observable<ExpertsCommentBean> getExpertComment(@FieldMap Map<String, Object> map);

    // 专家下单
    @FormUrlEncoded
    @POST(NetUrl.expertOrder)
    Observable<ExpertOrderBean> submitExpertOrder(@FieldMap Map<String, Object> map);

    //发送消息
    @FormUrlEncoded
    @POST(NetUrl.addexpertnotice)
    Observable<DexpertnoticeBean> addexpertnotice(@FieldMap Map<String, Object> map);

    //查询咨询消息
    @FormUrlEncoded
    @POST(NetUrl.expertnoticelist)
    Observable<ExpertnoticelistBean> expertnoticelist(@FieldMap Map<String, Object> map);

    //物业首页
    @Multipart
    @POST(NetUrl.wuyeHome)
    Observable<ActivityPostBean> getWuyeHomeInfo(@PartMap() Map<String, RequestBody> map, @Part List<MultipartBody.Part> partList);

    //报修分类Item
    @FormUrlEncoded
    @POST(NetUrl.getRepairItem)
    Observable<RepairItmeBean> getRepairItem(@FieldMap Map<String, Object> map);

    //报修时间获取
    @FormUrlEncoded
    @POST(NetUrl.getRepairsTime)
    Observable<RepairTimeBean> getRepairTime(@FieldMap Map<String, Object> map);

    //活动首页
    @FormUrlEncoded
    @POST(NetUrl.activityHome)
    Observable<ActIndexBean> getActivityHome(@FieldMap Map<String, Object> map);

    //活动发布
    @Multipart
    @POST(NetUrl.activityPost)
    Observable<ActivityPostBean> postActivity(@PartMap() Map<String, RequestBody> paramsMap,
                                              @Part List<MultipartBody.Part> albumPhoto, @Part List<MultipartBody.Part> albumPhotos);

    //活动包名
    @FormUrlEncoded
    @POST(NetUrl.activitySign)
    Observable<ActivitySignBean> getActivitySign(@FieldMap Map<String, Object> map);

    //活动报名列表
    @FormUrlEncoded
    @POST(NetUrl.activityList)
    Observable<ActivitySignListBean> getActivitySignList(@FieldMap Map<String, Object> map);

    //活动报名列表
    @FormUrlEncoded
    @POST(NetUrl.memberdetail)
    Observable<ActivityUserDetailBean> getActivityUserDetail(@FieldMap Map<String, Object> map);

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

    //图书收藏
    @FormUrlEncoded
    @POST(NetUrl.healthInfoCollect)
    Observable<CollectBean> addCollect(@FieldMap Map<String, Object> map);

    //图书借阅
    @FormUrlEncoded
    @POST(NetUrl.jieyue)
    Observable<JieYueBean> jieYueBook(@FieldMap Map<String, Object> map);

    //图书借阅时间
    @FormUrlEncoded
    @POST(NetUrl.jieyuetime)
    Observable<JieYueTimeBean> jieYueTime(@FieldMap Map<String, Object> map);

    //查询类别
    @FormUrlEncoded
    @POST(NetUrl.searchCategory)
    Observable<SearchCategoryBean> getSearchCategory(@FieldMap Map<String, Object> map);

    //查询图书搜索历史和类别
    @FormUrlEncoded
    @POST(NetUrl.getSearchData)
    Observable<BookSearchDataBean> getSearchData(@FieldMap Map<String, Object> map);

    //清空图书搜索历史
    @FormUrlEncoded
    @POST(NetUrl.clearHistory)
    Observable<CollectBean> clearHistory(@FieldMap Map<String, Object> map);

    //搜索图书结果
    @FormUrlEncoded
    @POST(NetUrl.searchBookResult)
    Observable<SearchBookListBean> searchBookResult(@FieldMap Map<String, Object> map);

    //查询书列表
    @FormUrlEncoded
    @POST(NetUrl.searchBookList)
    Observable<SearchBookListBean> getSearchBookList(@FieldMap Map<String, Object> map);

    //查询图书订单列表
    @FormUrlEncoded
    @POST(NetUrl.bookOrderList)
    Observable<BookOrderListBean> bookOrderList(@FieldMap Map<String, Object> map);

    //图书订单详情
    @FormUrlEncoded
    @POST(NetUrl.getBookOrderDetail)
    Observable<BookOrderDetailBean> getBookOrderDetail(@FieldMap Map<String, Object> map);


    //店铺
    @FormUrlEncoded
    @POST(NetUrl.shop)
    Observable<ShopBean> getShopInfo(@FieldMap Map<String, Object> map);

    //理发师列表
    @FormUrlEncoded
    @POST(NetUrl.barberList)
    Observable<BarberListsBean> getBarberList(@FieldMap Map<String, Object> map);

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

    //下单
    @FormUrlEncoded
    @POST(NetUrl.createorder)
    Observable<CreateorderBean> createorder(@FieldMap Map<String, Object> map);

    //咨询理发师
    @FormUrlEncoded
    @POST(NetUrl.consult)
    Observable<ConsultBarberBean> getConsultInfo(@FieldMap Map<String, Object> map);

    //发送消息
    @FormUrlEncoded
    @POST(NetUrl.sendMessage)
    Observable<SendMessageBean> sendMessage(@FieldMap Map<String, Object> map);

    //理发师作品详情
    @FormUrlEncoded
    @POST(NetUrl.barberProductDetail)
    Observable<BarberProductDetailBean> getDetail(@FieldMap Map<String, Object> map);

    //理发师更多评论
    @FormUrlEncoded
    @POST(NetUrl.barberMoreComment)
    Observable<BarberMoreCommentBean> getBarberMoreComment(@FieldMap Map<String, Object> map);

    //干洗店
    @FormUrlEncoded
    @POST(NetUrl.getBanner)
    Observable<BannerResponse> getBanner(@FieldMap Map<String, Object> map);

    //预约干洗
    @FormUrlEncoded
    @POST(NetUrl.orderDryCleaner)
    Observable<ClothBean> orderDryClean(@FieldMap Map<String, Object> map);

    //提交预约
    @FormUrlEncoded
    @POST(NetUrl.DryOrderCommit)
    Observable<JieYueBean> dryOrderCommit(@FieldMap Map<String, Object> map);

    //查看干洗订单
    @FormUrlEncoded
    @POST(NetUrl.DryOrder)
    Observable<CheckDryOrderBean> checkDryOrder(@FieldMap Map<String, Object> map);

    //提交并删除订单
    @FormUrlEncoded
    @POST(NetUrl.commitDryCancelOrder)
    Observable<CollectBean> commitDryCancelOrder(@FieldMap Map<String, Object> map);

    //提交评论
    @FormUrlEncoded
    @POST(NetUrl.commitDryComment)
    Observable<CollectBean> commitDryComment(@FieldMap Map<String, Object> map);

    //办公用品首页
    @FormUrlEncoded
    @POST(NetUrl.officeIndex)
    Observable<OfficeIndexBean> getOfficeIndex(@FieldMap Map<String, Object> map);

    //办公用品类别
    @FormUrlEncoded
    @POST(NetUrl.productCategory)
    Observable<ProductCategoryBean> getProductCategory(@FieldMap Map<String, Object> map);

    //商品详情
    @FormUrlEncoded
    @POST(NetUrl.spDetail)
    Observable<SpDetailBean> getSpDetail(@FieldMap Map<String, Object> map);

    //商品评价
    @FormUrlEncoded
    @POST(NetUrl.spAllCommentCount)
    Observable<SpAllCommentCountBean> getSpAllCommentCount(@FieldMap Map<String, Object> map);

    //商品评价
    @FormUrlEncoded
    @POST(NetUrl.spAllComment)
    Observable<SpAllCommentBean> getSpAllComment(@FieldMap Map<String, Object> map);

    //商品购物车信息
    @FormUrlEncoded
    @POST(NetUrl.shopCarInfo)
    Observable<ShopCarInfoBean> getShopCarInfo(@FieldMap Map<String, Object> map);

    //加入购物车
    @FormUrlEncoded
    @POST(NetUrl.shopCar)
    Observable<ActivityPostBean> shopCar(@FieldMap Map<String, Object> map);

    //查看购物车
    @FormUrlEncoded
    @POST(NetUrl.checkShopCar)
    Observable<SpCheckShopCarBean> checkShopCar(@FieldMap Map<String, Object> map);

    //生成订单
    @FormUrlEncoded
    @POST(NetUrl.spOrder)
    Observable<SpOrderBean> getSpOrder(@FieldMap Map<String, Object> map);

    //删除订单项
    @FormUrlEncoded
    @POST(NetUrl.deleteItem)
    Observable<ActivityPostBean> cancelItem(@FieldMap Map<String, Object> map);

    //发布需求
    @FormUrlEncoded
    @POST(NetUrl.postNeed)
    Observable<ActivityPostBean> postNeed(@FieldMap Map<String, Object> map);

    //历史需求
    @FormUrlEncoded
    @POST(NetUrl.requested)
    Observable<HistoryPostBean> historyPostNeed(@FieldMap Map<String, Object> map);

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

    //查询水类型
    @FormUrlEncoded
    @POST(NetUrl.orderWater)
    Observable<QueryWaterTypeBean> queryWaterType(@FieldMap Map<String, Object> map);

    //查询水列表
    @FormUrlEncoded
    @POST(NetUrl.queryWater)
    Observable<QueryWaterListBean> queryWaterList(@FieldMap Map<String, Object> map);

    //获得订水时间
    @FormUrlEncoded
    @POST(NetUrl.getOrderTime)
    Observable<OrderWaterTimeBean> getOrderWaterTime(@FieldMap Map<String, Object> map);
    //Observable<SelectTimeBean> getOrderWaterTime(@FieldMap Map<String, Object> map);

    //提交订单
    @FormUrlEncoded
    @POST(NetUrl.generateOrder)
    Observable<GenerateOrderWaterBean> generateOrder(@FieldMap Map<String, Object> map);

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

    // 反馈新信息
    @FormUrlEncoded
    @POST(NetUrl.getLaiFangShareUrl)
    Observable<CollectBean> getLaiFangShareUrl(@FieldMap Map<String, Object> map);

    // 获取信息公告分类
    @FormUrlEncoded
    @POST(NetUrl.informationClass)
    Observable<InformationClazzBean> getInformationClass(@FieldMap Map<String, Object> map);

    // 获取信息公告详情
    @FormUrlEncoded
    @POST(NetUrl.getinformationDetail)
    Observable<InformationDetailBean> getinformationDetail(@FieldMap Map<String, Object> map);

    // 评论信息公告详情
    @FormUrlEncoded
    @POST(NetUrl.informationDetailComment)
    Observable<CollectBean> informationDetailComment(@FieldMap Map<String, Object> map);

    // 获取信息公告列表
    @FormUrlEncoded
    @POST(NetUrl.informationData)
    Observable<InformationBean> getInformation(@FieldMap Map<String, Object> map);

    // 修改用户分类
    @FormUrlEncoded
    @POST(NetUrl.updataInformationClass)
    Observable<CollectBean> updataInformationClass(@FieldMap Map<String, Object> map);

    // 拜访人员
    @FormUrlEncoded
    @POST(NetUrl.baifang)
    Observable<ActivityPostBean> postBaiFangInfo(@FieldMap Map<String, Object> map);

    // 获取净菜食物列表
    @FormUrlEncoded
    @POST(NetUrl.getVegetableData)
    Observable<VegetableBean> getVegetableData(@FieldMap Map<String, Object> map);

    // 提交净菜订单
    @FormUrlEncoded
    @POST(NetUrl.submitVegetableOrder)
    Observable<PayBean> submitVegetableOrder(@FieldMap Map<String, Object> map);

    // 获取净菜取餐时间
    @FormUrlEncoded
    @POST(NetUrl.getVegetableTime)
    Observable<SelectTimeBean> getVegetableTime(@FieldMap Map<String, Object> map);

    // 获取我的服务
    @FormUrlEncoded
    @POST(NetUrl.getMyService)
    Observable<ServiceMyBean> getMyService(@FieldMap Map<String, Object> map);

    // 更改我的服务
    @FormUrlEncoded
    @POST(NetUrl.submitMyService)
    Observable<CollectBean> submitMyService(@FieldMap Map<String, Object> map);

    // 获取消息tab
    @FormUrlEncoded
    @POST(NetUrl.getMessage)
    Observable<MessageBean> getMessage(@FieldMap Map<String, Object> map);

    // 获取消息列表
    @FormUrlEncoded
    @POST(NetUrl.getMessageList)
    Observable<MessageListBean> getMessageList(@FieldMap Map<String, Object> map);

    /**
     * *************************************************************************************
     * // 取消医疗订单
     *
     * @FormUrlEncoded
     * @POST(NetUrl.cancelMedicalOrder) Observable<CollectBean> cancelMedicalOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消专家订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelExpertsOrder) Observable<CollectBean> cancelExpertsOrder(@FieldMap Map<String, Object> map);
     * <p>
     * //取消工作餐订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelMealOrder) Observable<CollectBean> cancelMealOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消报修订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelRepairsOrder) Observable<CollectBean> cancelRepairsOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消剪发订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelHairCutOrder) Observable<CollectBean> cancelHairCutOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消图书订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelBookOrder) Observable<CollectBean> cancelBookOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消办公用品订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelOfficeOrder) Observable<CollectBean> cancelOfficeOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消订水订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelWaterOrder) Observable<CollectBean> cancelWaterOrder(@FieldMap Map<String, Object> map);
     * <p>
     * // 取消净菜订单
     * @FormUrlEncoded
     * @POST(NetUrl.cancelVegetableOrder) Observable<CollectBean> cancelVegetableOrder(@FieldMap Map<String, Object> map);
     * ****************************************************************************************
     */

    // 获取取消订单原因
    @FormUrlEncoded
    @POST(NetUrl.getCancelReason)
    Observable<ReasonBean> getCancelReason(@FieldMap Map<String, Object> map);

    // 取消所有订单的接口
    @FormUrlEncoded
    @POST(NetUrl.cancelOrder)
    Observable<CollectBean> cancelOrder(@FieldMap Map<String, Object> map);

    /*----------------------------------获取各种订单列表-------------------------------------*/

    // 医疗订单列表
    @FormUrlEncoded
    @POST(NetUrl.medicalOrderList)
    Observable<OrderMedicalBean> medicalOrderList(@FieldMap Map<String, Object> map);

    // 专家订单列表
    @FormUrlEncoded
    @POST(NetUrl.expertOrderList)
    Observable<OrderExpertBean> expertOrderList(@FieldMap Map<String, Object> map);

    // 报修订单列表
    @FormUrlEncoded
    @POST(NetUrl.repairsOrderList)
    Observable<OrderRepairBean> repairsOrderList(@FieldMap Map<String, Object> map);

    // 会议室订单列表
    @FormUrlEncoded
    @POST(NetUrl.conferenceOrderList)
    Observable<OrderConferenceBean> conferenceOrderList(@FieldMap Map<String, Object> map);

    // 来访人员列表
    @FormUrlEncoded
    @POST(NetUrl.visitorsOrderList)
    Observable<OrderVisitorsListBean> visitorsOrderList(@FieldMap Map<String, Object> map);

    // 提交来访人员列表
    @FormUrlEncoded
    @POST(NetUrl.commitOrder)
    Observable<CollectBean> commitOrder(@FieldMap Map<String, Object> map);

    // 来访人员订单详情
    @FormUrlEncoded
    @POST(NetUrl.visitorsOrderDetail)
    Observable<VisitorsOrderDetailBean> visitorsOrderDetail(@FieldMap Map<String, Object> map);

    // 来访人员还为生成订单详情
    @FormUrlEncoded
    @POST(NetUrl.visitorsNoOrderDetail)
    //Observable<VisitorsNoOrderDetailBean> visitorsNoOrderDetail(@FieldMap Map<String, Object> map);
    Observable<Bbean> visitorsNoOrderDetail(@FieldMap Map<String, Object> map);

    // 楼号列表
    @FormUrlEncoded
    @POST(NetUrl.getBuildNumList)
    Observable<BuildNumList> getBuildNumList(@FieldMap Map<String, Object> map);

    // 理发订单列表
    @FormUrlEncoded
    @POST(NetUrl.hairCutOrderList)
    Observable<OrderHairCutBean> hairCutOrderList(@FieldMap Map<String, Object> map);

    // 理发订单详情
    @FormUrlEncoded
    @POST(NetUrl.hairCutOrderDetail)
    Observable<HairOrderDetailBean> hairOrderDetail(@FieldMap Map<String, Object> map);

    // 办公用品订单列表
    @FormUrlEncoded
    @POST(NetUrl.officeOrderList)
    Observable<OrderOfficeBean> officeOrderList(@FieldMap Map<String, Object> map);

    // 订餐订单列表
    @FormUrlEncoded
    @POST(NetUrl.mealOrderList)
    Observable<OrderMealBean> mealOrderList(@FieldMap Map<String, Object> map);

    // 订水订单列表
    @FormUrlEncoded
    @POST(NetUrl.waterOrderList)
    Observable<OrderWaterBean> waterOrderList(@FieldMap Map<String, Object> map);

    // 净菜订单列表
    @FormUrlEncoded
    @POST(NetUrl.vegetableOrderList)
    Observable<OrderVegetableBean> vegetableOrderList(@FieldMap Map<String, Object> map);

    // 查询最后充值卡号
    @FormUrlEncoded
    @POST(NetUrl.findLastCardNum)
    Observable<LastCardNumBean> findLastCardNum(@FieldMap Map<String, Object> map);

    // 查询餐卡余额
    @FormUrlEncoded
    @POST(NetUrl.findRemain)
    Observable<FindRemainBean> findRemain(@FieldMap Map<String, Object> map);

    // 生成充值订单
    @FormUrlEncoded
    @POST(NetUrl.generateChargeOrder)
    Observable<ChargeOrderBean> generateChargeOrder(@FieldMap Map<String, Object> map);

    // 查询充值订单
    @FormUrlEncoded
    @POST(NetUrl.getChargeOrder)
    Observable<GetChargeOrderBean> getChargeOrder(@FieldMap Map<String, Object> map);

    // 支付宝支付
    @FormUrlEncoded
    @POST(NetUrl.pay)
    Observable<AlipayBean> alipay(@FieldMap Map<String, Object> map);

    // 微信支付
    @FormUrlEncoded
    @POST(NetUrl.pay)
    Observable<WeixinBean> weixinpay(@FieldMap Map<String, Object> map);

    // 个人钱包支付
    @FormUrlEncoded
    @POST(NetUrl.pay)
    Observable<ActivityPostBean> personalwalletpay(@FieldMap Map<String, Object> map);

    //获取个人信息
    @FormUrlEncoded
    @POST(NetUrl.getUserInfo)
    Observable<UserInfoBean> getUserInfo(@FieldMap Map<String, Object> map);

    /*----------------------------------删除各种订单列表-------------------------------------*/
    // 删除订单
    @FormUrlEncoded
    @POST(NetUrl.deleteOrder)
    Observable<CollectBean> deleteOrder(@FieldMap Map<String, Object> map);

    /*----------------------------------完成订单-------------------------------------*/
    // 完成订单
    @FormUrlEncoded
    @POST(NetUrl.finishOrder)
    Observable<CollectBean> finishOrder(@FieldMap Map<String, Object> map);

    /*----------------------------------评论各种订单-------------------------------------*/
    // 评论订单
    @Multipart
    @POST(NetUrl.commentOrder)
    Observable<CollectBean> commentOrder(@PartMap() Map<String, RequestBody> paramsMap, @Part List<MultipartBody.Part> albumPhoto);

     /*-----------------------------------订单详情-------------------------------------*/

    // 专家订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderExpertsDetail)
    Observable<OrderExpertsDetailBean> orderExpertsDetail(@FieldMap Map<String, Object> map);

    // 医疗订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderMedicalDetail)
    Observable<OrderMedicalDetailBean> orderMedicalDetail(@FieldMap Map<String, Object> map);

    // 办公用品订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderOfficeDetail)
    Observable<OrderOfficeDetailBean> orderOfficeDetail(@FieldMap Map<String, Object> map);

    // 净菜订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderVegetableDetail)
    Observable<OrderVegetableDetailBean> orderVegetableDetail(@FieldMap Map<String, Object> map);

    // 是否可以预定
    @FormUrlEncoded
    @POST(NetUrl.canOrdered)
    Observable<CanOrderedBean> canOrdered(@FieldMap Map<String, Object> map);

    // 报修订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderRepairsDetail)
    Observable<OrderRepairsDetailOneBean> orderRepairsDetail(@FieldMap Map<String, Object> map);


    // 报修订单状态详情
    @FormUrlEncoded
    @POST(NetUrl.orderRepairsDetailTwo)
    Observable<OrderRepairsDetailTwoBean> orderRepairsDetailTwo(@FieldMap Map<String, Object> map);

    // 订水订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderWaterDetail)
    Observable<OrderWaterDetailBean> orderWaterDetail(@FieldMap Map<String, Object> map);

    // 订水时间
    @FormUrlEncoded
    @POST(NetUrl.orderWaterTime)
    Observable<RepairTimeBean> orderWaterTime(@FieldMap Map<String, Object> map);

    // 工作餐订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderMealDetail)
    Observable<OrderMealDetailBean> orderMealDetail(@FieldMap Map<String, Object> map);

    // 工作餐订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderConferenceDetail)
    Observable<OrderConferenceDetailBean> orderConferenceDetail(@FieldMap Map<String, Object> map);

    // 干洗订单详情
    @FormUrlEncoded
    @POST(NetUrl.orderDryDetail)
    Observable<OrderDryClearDetailBean> orderDryDetail(@FieldMap Map<String, Object> map);

    //我的收藏
    @FormUrlEncoded
    @POST(NetUrl.addFavor)
    Observable<MyCollectBean> addFavor(@FieldMap Map<String, Object> map);

    //删除我的收藏
    @FormUrlEncoded
    @POST(NetUrl.deleteFavor)
    Observable<ActivityPostBean> deleteFavor(@FieldMap Map<String, Object> map);

    //我的收藏
    //公告
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<InforMationCollect> findUserFavorList1(@FieldMap Map<String, Object> map);
    //办公用品收藏
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<OfficeCollect> findUserFavorList2(@FieldMap Map<String, Object> map);
    //理发作品收藏
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<BarberProductCollect> findUserFavorList3(@FieldMap Map<String, Object> map);
    //图书收藏
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<BookCollect> findUserFavorList4(@FieldMap Map<String, Object> map);
    //专家收藏
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<HealthServerceHomeBean> findUserFavorList5(@FieldMap Map<String, Object> map);
    //活动收藏
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<ActivityHomeBean> findUserFavorList6(@FieldMap Map<String, Object> map);
    //理发师收藏
    @FormUrlEncoded
    @POST(NetUrl.findUserFavorList)
    Observable<BarberCollect> findUserFavorList7(@FieldMap Map<String, Object> map);

    //修改用户信息
    @FormUrlEncoded
    @POST(NetUrl.changeUserInfo)
    Observable<ActivityPostBean> changeUserInfo(@FieldMap Map<String, Object> map);

    //我的钱包信息
    @FormUrlEncoded
    @POST(NetUrl.findUserWallet)
    Observable<UserWalletBean> findUserWallet(@FieldMap Map<String, Object> map);

    //明细
    @FormUrlEncoded
    @POST(NetUrl.findWalletLog)
    Observable<FindWalletLogBean> findWalletLog(@FieldMap Map<String, Object> map);

    //生成充值订单
    @FormUrlEncoded
    @POST(NetUrl.generateChargeWalletOrder)
    Observable<WalletOrderBean> generateChargeWalletOrder(@FieldMap Map<String, Object> map);

    //充值记录
    @FormUrlEncoded
    @POST(NetUrl.findChargeOrder)
    Observable<FindChargeOrderBean> findChargeOrder(@FieldMap Map<String, Object> map);

    //查询押金
    @FormUrlEncoded
    @POST(NetUrl.getUserDeposit)
    Observable<UserDepositBean> getUserDeposit(@FieldMap Map<String, Object> map);

    //退回押金
    @FormUrlEncoded
    @POST(NetUrl.backDeposit)
    Observable<ActivityPostBean> backDeposit(@FieldMap Map<String, Object> map);

    //是否有交易密码
    @FormUrlEncoded
    @POST(NetUrl.hasPaypass)
    Observable<ActivityPostBean> hasPaypass(@FieldMap Map<String, Object> map);

    //验证旧支付密码
    @FormUrlEncoded
    @POST(NetUrl.checkOldPassword)
    Observable<ActivityPostBean> checkOldPassword(@FieldMap Map<String, Object> map);

    //查询对公账户列表
    @FormUrlEncoded
    @POST(NetUrl.findPurchaseAccountList)
    Observable<PurchaseAccountListBean> findPurchaseAccountList(@FieldMap Map<String, Object> map);

    //修改支付密码
    @FormUrlEncoded
    @POST(NetUrl.modifyCode)
    Observable<ActivityPostBean> modifyCode(@FieldMap Map<String, Object> map);

    //修改头像
    @Multipart
    @POST(NetUrl.upLoadHeader)
    Observable<UpLoadHeaderBean> upLoadHeader(@PartMap() Map<String, RequestBody> paramsMap, @Part MultipartBody.Part albumPhoto);

    //办公室预订
    @FormUrlEncoded
    @POST(NetUrl.officelist)
    Observable<OfficeListResponse> officelist(@FieldMap Map<String, Object> map);

    //办公室详情
    @FormUrlEncoded
    @POST(NetUrl.officedetails)
    Observable<OfficeDetailsResponse> officedetails(@FieldMap Map<String, Object> map);

    //查询会议室内可以预定的设备
    @FormUrlEncoded
    @POST(NetUrl.subscribeInfo)
    Observable<SubscribeInfoResponse> subscribeInfo(@FieldMap Map<String, Object> map);

    //生成会议室订单-不带附件
    @Multipart
    @POST(NetUrl.generateOfficeOrder)
    Observable<AffirmOrderResponse> findAvailableEquipment(@PartMap() Map<String, RequestBody> paramsMap);

    //生成会议室订单
    @Multipart
    @POST(NetUrl.generateOfficeOrder)
    Observable<AffirmOrderResponse> findAvailableEquipment(@PartMap() Map<String, RequestBody> paramsMap, @Part List<MultipartBody.Part> albumPhoto);

    //查询会议室预约列表
    @FormUrlEncoded
    @POST(NetUrl.findAvailableTime)
    Observable<SubscribeTimeResponse> findAvailableTime(@FieldMap Map<String, Object> map);


    //资讯列表
    @FormUrlEncoded
    @POST(NetUrl.information)
    Observable<OfficeListResponse> information(@FieldMap Map<String, Object> map);

    //节能加派查询
    @FormUrlEncoded
    @POST(NetUrl.energyCostQuery)
    Observable<EnergyCostQueryBean> energyCostQuery(@FieldMap Map<String, Object> map);

   /* //获得首页轮播图
    @FormUrlEncoded
    @POST(NetUrl.getHomeBanner)
    Observable<SaveHomeBanner> getHomeBanner(@FieldMap Map<String, Object> map);*/

    //获得首页资讯列表
    @FormUrlEncoded
    @POST(NetUrl.getHomeList)
    Observable<SaveHomeListBean> getHomeList(@FieldMap Map<String, Object> map);

    //获得首页资讯详情
    @FormUrlEncoded
    @POST(NetUrl.informationDetail)
    Observable<InforMationDetailBean> informationDetail(@FieldMap Map<String, Object> map);

    //节能减排的对比
    @FormUrlEncoded
    @POST(NetUrl.energyCostCompare)
    Observable<EnergyCostCompare> energyCostCompare(@FieldMap Map<String, Object> map);

    //意见反馈
    @FormUrlEncoded
    @POST(NetUrl.saveAdvice)
    Observable<ActivityPostBean> saveAdvice(@FieldMap Map<String, Object> map);
}
