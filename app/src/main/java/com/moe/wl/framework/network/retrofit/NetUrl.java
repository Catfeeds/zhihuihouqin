package com.moe.wl.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    // 首页
    String homePage = "appuser/index";

    //
    String signup="appuser/user/login?";
    String captcha="appuser/user/getCaptcha?";
    String regist="appuser/user/register?";
    String positionList="appuser/user/positionlist";
    String submitAuth="appuser/user/auth";
    String bindPhone="appuser/user/bindMobile";
    String changePassWord = "appuser/user/updatePass";

    //医疗
    String healthServiceHome="appuser/ylfw/ylfwindex";
    String healthInfoCollect="appuser/tfavor/addFavor";
    String doctorList="appuser/ylfw/doctorlist";
    String doctorDetail="appuser/ylfw/schedules";
    String more="appuser/healthyinfo/list";
    String medicalOrderList = "appuser/ylfw/orderlist"; // 医疗订单列表
    String expertOrderList = "appuser/ylfw/expertorderlist"; // 专家订单列表
    String userCommentList="appuser/ylfw/getcomment"; // 医生评论
    String expertDetail = "appuser/ylfw/expertdetail"; // 专家详情
    String expertComment = "appuser/ylfw/getexpertcomment"; // 专家评论
    String expertOrder = "appuser/ylfw/addexpertOrder"; // 专家下单
    String cancelMedicalOrder = "appuser/ylfw/cancelorder"; // 取消医疗订单
    String cancelExpertsOrder = "appuser/ylfw/cancelexpertorder"; // 取消专家订单

    //物业维修
    String wuyeHome="appuser/bxwx/addOrder";
    String repairsOrderList = "appuser/bxwx/orderlist"; // 报修订单列表
    String cancelRepairsOrder = "appuser/bxwx/cancelorder";

    //活动
    String activitySign="appuser/tdhdfw/sign";
    String activityHome="appuser/tdhdfw/index";
    String activityList="appuser/tdhdfw/detail";
    String activityPost="appuser/tdhdfw/pubactivity";

    //理发师
    String shop="appuser/lffw/toindex";
    String barberList="appuser/lffw/barberlist";
    String barberDetail="appuser/lffw/barberdetail";
    String barberworklist="appuser/lffw/barberworklist";
    String preorder="appuser/lffw/preorder";
    String hairCutOrderList = "appuser/lffw/orderlist"; // 理发订单列表
    String cancelHairCutOrder = "appuser/lffw/cancelorder";

    String consult="appuser/lffw/noticelist";
    String sendMessage="appuser/lffw/addnotice";
    String barberProductDetail="appuser/lffw/barberworkdetail";
    String barberMoreComment="appuser/lffw/getcomment";
    //干洗店
    String dryCleanerHome="appuser/service/getServiceInfo";
    String orderDryCleaner="appuser/tgxdfwclothestype/findClothesTypeList";
    String DryOrderCommit="appuser/tgxdfworder/generateOrder";
    String DryOrder ="appuser/tgxdfworder/findOrderList";
    String commitDryCancelOrder="appuser/tgxdfworder/orderCancel";
    String commitDryComment="appuser/tgxdfworder/addComment";
    String cancelDryOrder ="appuser/tgxdfworder/findReasonList";

    //图书
    String libraryIndex="appuser/tsjyfw/getTopPicture";
    String libraryHome ="appuser/tsjyfw/indexbooklist";
    String recommandBook="appuser/tsjyfw/recommand";
    String bookdetail="appuser/tsjyfw/getbookdetail";
    String jieyue="appuser/tsjyfw/bookorder";
    String jieyuetime="appuser/tsjyfw/gettime";
    String searchCategory="appuser/tsjyfw/gettypelist";
    String searchBookList="appuser/tsjyfw/getbooklist";
    String bookOrderList="appuser/tsjyfw/orderlist";
    String cancelBookOrder = "appuser/tsjyfw/cancelorder";

    //办公用品
    String officeIndex="appuser/bgypfwproduct/index";
    String productCategory="appuser/bgypfwproduct/findProductList";
    String spDetail="appuser/bgypfwproduct/findProductDetail";
    String spAllComment="appuser/bgypfwproduct/findCommentList";
    String spAllCommentCount="appuser/bgypfwproduct/findCommentRate";
    String shopCarInfo="appuser/bgypfwskuid/findSkuList";
    String shopCar="appuser/bgypfwcart/addToCart";
    String checkShopCar="appuser/bgypfwcart/findUserCart";
    String spOrder="appuser/bgypfworder/generateOrder";
    String postNeed="appuser/bgypfwapplyproduct/generateOrder";
    String requested="appuser/bgypfwapplyproduct/findApplyList";
    String deleteItem ="appuser/bgypfwcart/deleteCartItems";
    String officeOrderList = "appuser/bgypfworder/findOrderList"; // 办公用品订单列表
    String cancelOfficeOrder = "appuser/bgypfworder/orderCancel";

    //工作餐预订
    String createOrder = "appuser/dcfwgzcorder/generateOrder";
    String orderReason = "appuser/dcfwgzcorder/findReasonList";
    String getTime = "appuser/dcfwgzcorder/getFoodTimeList";
    String mealOrderList = "appuser/tdcfworder/findOrderList"; // 订餐订单列表
    String cancelMealOrder = "appuser/dcfwgzcorder/orderCancel";

    //订水
    String orderWater="appuser/dsfwgoods/findCategoryList";
    String queryWater="appuser/dsfwgoods/findGoodsList";
    String getOrderTime="appuser/dsfworder/sendTimeList";
    String generateOrder ="appuser/dsfworder/generateOrder";
    String waterOrderList = "appuser/tdcfworder/findOrderList";
    String cancelWaterOrder = "appuser/dsfworder/orderCancel";

    // 地址管理
    String getAddress = "appuser/address/findUserAddressList";
    String addAddress = "appuser/address/addAddress";
    String editAddress = "appuser/address/updateAddress";
    String deleteAddress = "appuser/address/deleteAddress";

    // 营养套餐
    String getNutritionData = "appuser/tyytcmeal/findMealList";

    // 意见投诉
    String submitComplain = "appuser/jyfwcomplaint/commitComplanit";
    String getLabelling = "appuser/jyfwcomplaint/findComplanitTag";
    String complainHistory = "appuser/jyfwcomplaint/findComplanitList";
    String complainDetail = "appuser/jyfwcomplaint/getComplanitDetail";
    String complainReply = "appuser/jyfwcomplaint/findFeedbackList";
    String feedbackMessage = "appuser/jyfwcomplaint/addFeedback";

    // 信息公告
    String informationClass = "appuser/tnoticetype/findNoticeTypeList";
    String informationData = "appuser/tnotice/findNoticeList";
    String updataInformationClass = "appuser/tnoticetype/updateUserNoticeType";

    //拜访人员
    String baifang="appuser/user/addVisitLog";

    // 净菜预订
    String getVegetableData = "appuser/tdcfwfood/findFoodList"; // 获取食物列表
    String getVegetableTime = "appuser/tdcfworder/getFoodTimeList"; // 获取取餐时间
    String submitVegetableOrder = "appuser/tdcfworder/generateOrder"; // 提交净菜订单
    String vegetableOrderList = "appuser/tdcfworder/findOrderList"; // 净菜订单列表
    String getVegetableDetail = "appuser/tdcfworder/getOrderDetail"; // 获取净菜订单详情
    String getVegetableReason = "appuser/tdcfwordercancelreason/findReasonList"; // 获取取消原因列表
    String vegetableComment = "appuser/tdcfworder/addComment"; // 添加评论
    String cancelVegetableOrder = "appuser/tdcfworder/orderCancel"; // 取消净菜订单

    // 服务
    String getMyService = "appuser/service/findUserService";
    String submitMyService = "appuser/service/updateUserService";
    String getCancelReason = "appuser/service/getCancelReason";

    // 消息
    String getMessage = "appuser/message/messagePage";
    String getMessageList = "appuser/message/findUserMessage";

}
