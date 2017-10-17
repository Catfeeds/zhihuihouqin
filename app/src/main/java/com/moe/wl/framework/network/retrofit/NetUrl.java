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

    // 医疗
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
    String deleteMedicalOrder = ""; // 删除医疗订单
    String orderMedicalDetail = "appuser/ylfw/orderdetail"; // 医疗订单详情
    String commentMedicalOrder = "appuser/ylfw/addcomment"; // 评论医疗订单
    String cancelExpertsOrder = "appuser/ylfw/cancelexpertorder"; // 取消专家订单
    String deleteExpertsOrder = "appuser/ylfw/delexpertorder"; // 删除专家订单
    String orderExpertsDetail = "appuser/ylfw/expertorderdetail"; // 专家订单详情

    // 物业维修
    String wuyeHome="appuser/bxwx/addOrder";
    String repairsOrderList = "appuser/bxwx/orderlist"; // 报修订单列表
    String cancelRepairsOrder = "appuser/bxwx/cancelorder";
    String deleteRepairsOrder = "appuser/bxwx/delorder"; // 删除订单
    String commentRepairsOrder = "appuser/bxwx/addcomment"; // 报修评论
    String orderRepairsDetail = "appuser/bxwx/orderdetail"; // 报修订单详情
    String orderRepairsDetailTwo = "appuser/bxwx/orderstatuslist"; // 报修订单状态
    String getRepairItem = "appuser/bxwx/towywx"; // 报修分类Item

    // 活动
    String activitySign="appuser/tdhdfw/sign";
    String activityHome="appuser/tdhdfw/index";
    String activityList="appuser/tdhdfw/detail";
    String activityPost="appuser/tdhdfw/pubactivity";
    String memberdetail="appuser/tdhdfw/memberdetail";

    // 理发师
    String shop="appuser/lffw/toindex";
    String barberList="appuser/lffw/barberlist";
    String barberDetail="appuser/lffw/barberdetail";
    String barberworklist="appuser/lffw/barberworklist";
    String preorder="appuser/lffw/preorder";
    String hairCutOrderList = "appuser/lffw/orderlist"; // 理发订单列表
    String cancelHairCutOrder = "appuser/lffw/cancelorder";
    String deleteHairCutOrder = "appuser/lffw/delorder"; // 删除订单
    String commentHairCutOrder = "appuser/lffw/addcomment"; // 评论理发师

    String consult="appuser/lffw/noticelist";
    String sendMessage="appuser/lffw/addnotice";
    String barberProductDetail="appuser/lffw/barberworkdetail";
    String barberMoreComment="appuser/lffw/getcomment";
    // 干洗店
    String dryCleanerHome="appuser/service/getServiceInfo";
    String orderDryCleaner="appuser/tgxdfwclothestype/findClothesTypeList";
    String DryOrderCommit="appuser/tgxdfworder/generateOrder";
    String DryOrder ="appuser/tgxdfworder/findOrderList";
    String commitDryCancelOrder="appuser/tgxdfworder/orderCancel";
    String commitDryComment="appuser/tgxdfworder/addComment";
    String cancelDryOrder ="appuser/tgxdfworder/findReasonList";
    String deleteDryOrder = ""; // 删除订单
    String commentDryOrder = "appuser/tgxdfworder/addComment"; // 评论干洗订单
    String orderDryDetail = "appuser/tgxdfworder/getOrderDetail"; // 干洗订单详情

    // 图书
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
    String deleteBookOrder = "appuser/tsjyfw/delorder"; // 删除订单
    String commentBookOrder = "appuser/tsjyfw/addcomment"; // 评论图书订单

    // 办公用品
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
    String deleteOfficeOrder = "appuser/bgypfworder/orderDelete"; // 删除订单
    String commentOfficeOrder = "appuser/bgypfworder/addComment";  // 评论办公用品订单
    String orderOfficeDetail = "appuser/bgypfwproduct/getOrderDetail"; // 办公用品订单详情

    // 工作餐预订
    String createOrder = "appuser/dcfwgzcorder/generateOrder";
    String orderReason = "appuser/dcfwgzcorder/findReasonList";
    String getTime = "appuser/dcfwgzcorder/getFoodTimeList";
    String mealOrderList = "appuser/tdcfworder/findOrderList"; // 订餐订单列表
    String cancelMealOrder = "appuser/dcfwgzcorder/orderCancel";
    String deleteMealOrder = ""; // 删除订单
    String commentMealOrder = "appuser/dcfwgzcorder/addComment";  // 评论工作餐订单
    String orderMealDetail = "appuser/dcfwgzcorder/getOrderDetail"; // 订餐订单详情

    // 订水
    String orderWater="appuser/dsfwgoods/findCategoryList";
    String queryWater="appuser/dsfwgoods/findGoodsList";
    String getOrderTime="appuser/dsfworder/sendTimeList";
    String generateOrder ="appuser/dsfworder/generateOrder";
    String waterOrderList = "appuser/tdcfworder/findOrderList";
    String cancelWaterOrder = "appuser/dsfworder/orderCancel";
    String deleteWaterOrder = ""; // 删除订单
    String commentWaterOrder = "appuser/dsfworder/addComment";  // 评论订水订单
    String orderWaterDetail = "appuser/dsfworder/getOrderDetail"; // 订水订单详情

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

    // 拜访人员
    String baifang="appuser/user/addVisitLog";

    // 净菜预订
    String getVegetableData = "appuser/tdcfwfood/findFoodList"; // 获取食物列表
    String getVegetableTime = "appuser/tdcfworder/getFoodTimeList"; // 获取取餐时间
    String submitVegetableOrder = "appuser/tdcfworder/generateOrder"; // 提交净菜订单
    String vegetableOrderList = "appuser/tdcfworder/findOrderList"; // 净菜订单列表
    String orderVegetableDetail = "appuser/tdcfworder/getOrderDetail"; // 获取净菜订单详情
//    String getVegetableReason = "appuser/tdcfwordercancelreason/findReasonList"; // 获取取消原因列表
    String cancelVegetableOrder = "appuser/tdcfworder/orderCancel"; // 取消净菜订单
    String deleteVegetableOrder = ""; // 删除订单
    String commentVegetableOrder = "appuser/tdcfworder/addComment";  // 评论净菜订单

    //餐费充值
    String findLastCardNum="appuser/usercfczorder/findLastCardNum";//最后充值卡号
    String findRemain="appuser/usercfczorder/findRemain";//查询余额
    String generateChargeOrder="appuser/usercfczorder/generateChargeOrder";
    String pay="appuser/pay";//支付

    // 服务
    String getMyService = "appuser/service/findUserService";
    String submitMyService = "appuser/service/updateUserService";
    String getCancelReason = "appuser/service/getCancelReason";

    // 消息
    String getMessage = "appuser/message/messagePage";
    String getMessageList = "appuser/message/findUserMessage";
    //个人信息
    String getUserInfo="appuser/user/getUserinfo";
    String changeUserInfo="appuser/user/modifyUserinfo";
    String upLoadHeader="appuser/user/uploadPhoto";

    //我的收藏
    String addFavor="appuser/tfavor/addFavor";
    //我的钱包
    String findUserWallet ="appuser/wallet/findUserWallet";
    String findChargeOrder="appuser/wallet/findChargeOrder";
    String getUserDeposit="appuser/wallet/getUserDeposit";
    String generateChargeWalletOrder="appuser/wallet/generateChargeWalletOrder";//生成充值订单
    String findWalletLog="appuser/pay/findWalletLog";//明细
    String hasPaypass="appuser/wallet/hasPaypass";//是否有商品交易密码
    String checkOldPassword="appuser/wallet/checkOldPassword";
    String modifyCode="appuser/wallet/modifyCode";

    /*------------------------------办公室预订模块------------------------------------------------*/
    String officelist="appuser/wallet/officelist";  //办公室列表
    String officedetails="appuser/wallet/officedetails";  //办公室详情
    String subscribeInfo="appuser/wallet/subscribeInfo";  //填写预订信息
    String orderinfo="appuser/wallet/orderinfo";  //办公室预订订单信息


    /*------------------------------节能减排------------------------------------------------*/
    String information="appuser/wallet/information";  //资讯列表


}
