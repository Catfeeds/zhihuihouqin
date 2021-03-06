package com.moe.wl.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    // 首页
    String homePage = "appuser/index";
    String search="appuser/index/search";

    // 通用接口
    String cancelOrder = "appuser/orderCommon/orderCancel"; // 取消订单 所有服务
    String deleteOrder = "appuser/orderCommon/orderDelete"; // 删除订单
    String commentOrder = "appuser/orderCommon/addComment"; // 订单评论
    String finishOrder = "appuser/orderCommon/finishOrder"; // 完成订单

    //
    String signup = "appuser/user/login?";
    String captcha = "appuser/user/getCaptcha?";
    String regist = "appuser/user/register?";
    String thirdLogin = "appuser/user/thirdLogin?";
    String positionList = "appuser/user/positionlist";//职位列表
    String nationlist = "appuser/common/nationlist";//民族列表
    String getdepartList = "appuser/user/getdepartList";//部门列表
    String getofficelist = "appuser/user/getofficelist";//处室列表
    String cartypelist = "appuser/user/cartypelist";//车辆类别
    String submitAuth = "appuser/user/auth";//个人认证
    String bindPhone = "appuser/user/bindMobile";
    String changePassWord = "appuser/user/updatePass";
    String accountComplain = "appuser/user/appeal"; // 账号申诉

    // 医疗
    String healthServiceHome = "appuser/ylfw/ylfwindex";
    String healthInfoCollect = "appuser/tfavor/addFavor";
    String doctorList = "appuser/ylfw/doctorlist";
    String doctorDetail = "appuser/ylfw/schedules";
    String more = "appuser/healthyinfo/list";
    String medicalOrderList = "appuser/ylfw/orderlist"; // 医疗订单列表
    String expertOrderList = "appuser/ylfw/expertorderlist"; // 专家订单列表
    String userCommentList = "appuser/ylfw/getcomment"; // 医生评论
    String expertDetail = "appuser/ylfw/expertdetail"; // 专家详情
    String expertComment = "appuser/ylfw/getexpertcomment"; // 专家评论
    String expertOrder = "appuser/ylfw/addexpertOrder"; // 专家下单
    String orderMedicalDetail = "appuser/ylfw/orderdetail"; // 医疗订单详情
    String orderExpertsDetail = "appuser/ylfw/expertorderdetail"; // 专家订单详情
    String addexpertnotice = "appuser/ylfw/addexpertnotice";//发送咨询消息
    String expertnoticelist = "appuser/ylfw/expertnoticelist";//获取咨询消息
    String getMedicalDetail = "appuser/healthyinfo/detail"; // 获取资讯详情
    String commentMedicalDetail = "appuser/healthyinfo/addcomment"; // 评论资讯

    // 物业维修
    String wuyeHome = "appuser/bxwx/addOrder";
    String repairsOrderList = "appuser/bxwx/orderlist"; // 报修订单列表
    //    String cancelRepairsOrder = "appuser/bxwx/cancelorder";
    //    String deleteRepairsOrder = "appuser/bxwx/delorder"; // 删除订单
    //    String commentRepairsOrder = "appuser/bxwx/addcomment"; // 报修评论
    String orderRepairsDetail = "appuser/bxwx/orderdetail"; // 报修订单详情
    String orderRepairsDetailTwo = "appuser/bxwx/orderstatuslist"; // 报修订单状态
    String getRepairItem = "appuser/bxwx/towywx"; // 报修分类Item
    String getRepairsTime="appuser/bxwx/findAvailableTime";//获得时间

    // 活动
    String activitySign = "appuser/tdhdfw/sign";
    String activityHome = "appuser/tdhdfw/index";
    String activityList = "appuser/tdhdfw/detail";
    String activityPost = "appuser/tdhdfw/pubactivity";
    String memberdetail = "appuser/tdhdfw/memberdetail";

    // 理发师
    String shop = "appuser/lffw/toindex";
    String barberList = "appuser/lffw/barberlist";
    String barberDetail = "appuser/lffw/barberdetail";
    String barberworklist = "appuser/lffw/barberworklist";
    String preorder = "appuser/lffw/preorder";//预约
    String createorder = "appuser/lffw/createorder";//下单
    String hairCutOrderList = "appuser/lffw/orderlist"; // 理发订单列表
    String hairCutOrderDetail="appuser/lffw/orderdetail";//理发订单详情
//    String cancelHairCutOrder = "appuser/lffw/cancelorder";
//    String deleteHairCutOrder = "appuser/lffw/delorder"; // 删除订单
//    String commentHairCutOrder = "appuser/lffw/addcomment"; // 评论理发师

    String consult = "appuser/lffw/noticelist";
    String sendMessage = "appuser/lffw/addnotice";
    String barberProductDetail = "appuser/lffw/barberworkdetail";
    String barberMoreComment = "appuser/lffw/getcomment";
    // 获取轮播图
    String getBanner = "appuser/service/getServiceInfo";
    String orderDryCleaner = "appuser/tgxdfwclothestype/findClothesTypeList";
    String DryOrderCommit = "appuser/tgxdfworder/generateOrder";
    String DryOrder = "appuser/tgxdfworder/findOrderList";
    String commitDryCancelOrder = "appuser/tgxdfworder/orderCancel";
    String commitDryComment = "appuser/tgxdfworder/addComment";
    String cancelDryOrder = "appuser/tgxdfworder/findReasonList";
    //    String deleteDryOrder = ""; // 删除订单
//    String commentDryOrder = "appuser/tgxdfworder/addComment"; // 评论干洗订单
    String orderDryDetail = "appuser/tgxdfworder/getOrderDetail"; // 干洗订单详情

    // 图书
    String libraryIndex = "appuser/tsjyfw/getTopPicture";
    String libraryHome = "appuser/tsjyfw/indexbooklist";
    String recommandBook = "appuser/tsjyfw/recommand";
    String bookdetail = "appuser/tsjyfw/getbookdetail";
    String jieyue = "appuser/tsjyfw/bookorder";
    String jieyuetime = "appuser/tsjyfw/gettime";
    String searchCategory = "appuser/tsjyfw/gettypelist";
    String getSearchData = "appuser/tsjyfw/tosearch";
    String clearHistory = "appuser/tsjyfw/cleansearchhistory"; // 清空搜索历史
    String searchBookResult = "appuser/tsjyfw/search";
    String searchBookList = "appuser/tsjyfw/getbooklist";
    String bookOrderList = "appuser/tsjyfw/orderlist";
    String getBookOrderDetail="appuser/tsjyfw/orderdetail";
//    String cancelBookOrder = "appuser/tsjyfw/cancelorder";
//    String deleteBookOrder = "appuser/tsjyfw/delorder"; // 删除订单
//    String commentBookOrder = "appuser/tsjyfw/addcomment"; // 评论图书订单

    // 办公用品
    String officeIndex = "appuser/bgypfwproduct/index";
    String productCategory = "appuser/bgypfwproduct/findProductList";
    String spDetail = "appuser/bgypfwproduct/findProductDetail";
    String spAllComment = "appuser/bgypfwproduct/findCommentList";
    String spAllCommentCount = "appuser/bgypfwproduct/findCommentRate";
    String shopCarInfo = "appuser/bgypfwskuid/findSkuList";
    String shopCar = "appuser/bgypfwcart/addToCart";
    String checkShopCar = "appuser/bgypfwcart/findUserCart";
    String spOrder = "appuser/bgypfworder/generateOrder";
    String postNeed = "appuser/bgypfwapplyproduct/generateOrder";
    String requested = "appuser/bgypfwapplyproduct/findApplyList";
    String deleteItem = "appuser/bgypfwcart/deleteCartItems";
    String officeOrderList = "appuser/bgypfworder/findOrderList"; // 办公用品订单列表
    //    String cancelOfficeOrder = "appuser/bgypfworder/orderCancel";
//    String deleteOfficeOrder = "appuser/bgypfworder/orderDelete"; // 删除订单
//    String commentOfficeOrder = "appuser/bgypfworder/addComment";  // 评论办公用品订单
    String orderOfficeDetail = "appuser/bgypfworder/getOrderDetail"; // 办公用品订单详情

    // 工作餐预订
    String createOrder = "appuser/dcfwgzcorder/generateOrder";
    String orderReason = "appuser/dcfwgzcorder/findReasonList";
    String getTime = "appuser/dcfwgzcorder/getFoodTimeList";
    String mealOrderList = "appuser/dcfwgzcorder/findOrderList"; // 订餐订单列表
    //    String cancelMealOrder = "appuser/dcfwgzcorder/orderCancel";
//    String deleteMealOrder = ""; // 删除订单
//    String commentMealOrder = "appuser/dcfwgzcorder/addComment";  // 评论工作餐订单
    String orderMealDetail = "appuser/dcfwgzcorder/getOrderDetail"; // 订餐订单详情

    // 订水
    String orderWater = "appuser/dsfwgoods/findCategoryList";
    String queryWater = "appuser/dsfwgoods/findGoodsList";
    String getOrderTime = "appuser/dsfworder/sendTimeList";
    String generateOrder = "appuser/dsfworder/generateOrder";
    String waterOrderList = "appuser/dsfworder/findOrderList";
    //    String cancelWaterOrder = "appuser/dsfworder/orderCancel";
//    String deleteWaterOrder = ""; // 删除订单
//    String commentWaterOrder = "appuser/dsfworder/addComment";  // 评论订水订单
    String orderWaterDetail = "appuser/dsfworder/getOrderDetail"; // 订水订单详情
    String orderWaterTime="appuser/dsfworder/findAvailableTime";//订水时间

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
    String getinformationDetail = "appuser/tnotice/getNoticeInfo";//获得信息详情
    String informationDetailComment = "appuser/tnotice/addComment"; // 评论

    // 拜访人员
    String baifang = "appuser/user/addVisitLog";

    // 净菜预订
    String getVegetableData = "appuser/tdcfwfood/findFoodList"; // 获取食物列表
    String getVegetableTime = "appuser/tdcfworder/getFoodTimeList"; // 获取取餐时间
    String submitVegetableOrder = "appuser/tdcfworder/generateOrder"; // 提交净菜订单
    String vegetableOrderList = "appuser/tdcfworder/findOrderList"; // 净菜订单列表
    String orderVegetableDetail = "appuser/tdcfworder/getOrderDetail"; // 获取净菜订单详情
//    String getVegetableReason = "appuser/tdcfwordercancelreason/findReasonList"; // 获取取消原因列表
    String canOrdered="appuser/tdcfworder/canOrdered";//判断是否可以预定
//    String cancelVegetableOrder = "appuser/tdcfworder/orderCancel"; // 取消净菜订单
//    String deleteVegetableOrder = ""; // 删除订单
//    String commentVegetableOrder = "appuser/tdcfworder/addComment";  // 评论净菜订单

    //餐费充值
    String findLastCardNum = "appuser/usercfczorder/findLastCardNum";//最后充值卡号
    String findRemain = "appuser/usercfczorder/findRemain";//查询余额
    String generateChargeOrder = "appuser/usercfczorder/generateChargeOrder";//生成充值订单
    String getChargeOrder="appuser/usercfczorder/findChargeOrder";//获得充值订单
    String pay = "appuser/pay";//支付

    // 服务
    String getMyService = "appuser/service/findUserService";
    String submitMyService = "appuser/service/updateUserService";
    String getCancelReason = "appuser/service/getCancelReason";

    // 消息
    String getMessage = "appuser/message/messagePage";
    String getMessageList = "appuser/message/findUserMessage";
    //个人信息
    String getUserInfo = "appuser/user/getUserinfo";
    String changeUserInfo = "appuser/user/modifyUserinfo";
    String upLoadHeader = "appuser/user/uploadPhoto";

    //退换押金
    String backDeposit ="appuser/wallet/backDeposit";

    //意见反馈
    String saveAdvice = "appuser/user/saveAdvice";
    //我的收藏列表
    String findUserFavorList = "appuser/tfavor/findUserFavorList";
    //我的收藏
    String addFavor = "appuser/tfavor/addFavor";
    //删除我的收藏
    String deleteFavor= "appuser/tfavor/deleteFavor";
    //我的钱包
    String findUserWallet = "appuser/wallet/findUserWallet";
    String findChargeOrder = "appuser/wallet/findChargeOrder";
    String getUserDeposit = "appuser/wallet/getUserDeposit";
    String generateChargeWalletOrder = "appuser/wallet/generateChargeWalletOrder";//生成充值订单
    String findWalletLog = "appuser/wallet/findWalletLog";//明细
    String hasPaypass = "appuser/wallet/hasPaypass";//是否有商品交易密码
    String checkOldPassword = "appuser/wallet/checkOldPassword";//检测是否有密码
    String modifyCode = "appuser/wallet/modifyCode";//修改密码
    String findPurchaseAccountList="appuser/wallet/findPurchaseAccountList";//查询对公账户列表

    /*------------------------------办公室预订模块------------------------------------------------*/
    String findAvailableTime = "appuser/hysfworder/findAvailableTime";  //查询会议室预约列表
    String officelist = "appuser/hysfwconferenceroom/findConferenceList";  //办公室列表
    String officedetails = "appuser/hysfwconferenceroom/findConferenceDetail";  //办公室详情
    String subscribeInfo = "appuser/hysfwconferenceroom/findAvailableEquipment";  //查询会议室内可以预定的设备
    String generateOfficeOrder = "appuser/hysfworder/generateOrder";  //生成会议室订单
    String orderinfo = "appuser/wallet/orderinfo";  //办公室预订订单信息
    String conferenceOrderList = "appuser/hysfworder/findOrderList"; // 会议室订单列表
    String orderConferenceDetail = "appuser/hysfworder/getOrderDetail";


//    String deleteConferenceOrder = ""; // 删除办公室订单

    /*------------------------------节能减排------------------------------------------------*/
    String information = "appuser/wallet/information";  //资讯列表
    String energyCostQuery="appuser//energy/energyCostQuery";
    String getHomeBanner="appuser/energy/rollingPic";//轮播图
    String getHomeList="appuser/energy/index";//资讯列表和轮播图
    String informationDetail="appuser/energy/information";//节能减排资讯详情
    String energyCostCompare="appuser//energy/energyCostCompare";//节能减排的数据对比

    /*---------------------------------来访人员---------------------------------------*/
    String visitorsOrderList = "appuser/user/orderlist";
    String getLaiFangShareUrl = "appuser/user/getvisitaddr";
    String commitOrder = "appuser/user/check";
    String getBuildNumList="appuser/user/buildings";
    String visitorsOrderDetail="appuser/user/queryObjectByOid";//来访人员订单详情
     String visitorsNoOrderDetail="appuser/user/visitDetail";//来访人员还未生成订单详情


}
