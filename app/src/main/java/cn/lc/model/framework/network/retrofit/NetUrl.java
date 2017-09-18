package cn.lc.model.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    String signup="appuser/user/login?";
    String captcha="appuser/user/getCaptcha?";
    String regist="appuser/user/register?";
    String positionList="appuser/user/positionlist";
    String submitAuth="appuser/user/auth";
    String bindPhone="appuser/user/bindMobile";
    String changePassWord = "appuser/user/updatePass";

    //医疗
    String healthServiceHome="appuser/ylfw/ylfwindex";
    String healthInfoCollect="zhihuihouqin-api/appuser/tfavor/addFavor";
    String doctorList="appuser/ylfw/doctorlist";
    String doctorDetail="appuser/ylfw/schedules";
    String more="appuser/healthyinfo/list";
    String userCommentList="appuser/ylfw/getcomment";
    //物业维修
    String wuyeHome="appuser/lffw/preorder";
    String activityHome="appuser/tdhdfw/index";
    //活动
    String activitySign="appuser/tdhdfw/sign";
    String activityList="appuser/tdhdfw/detail";
    String activityPost="appuser/tdhdfw/pubactivity";
    //理发师
    String shop="appuser/lffw/toindex";
    String barberList="appuser/lffw/barberlist";
    String barberDetail="appuser/lffw/barberdetail";
    String barberworklist="appuser/lffw/barberworklist";
    String preorder="appuser/lffw/preorder";
    //干洗店
    String dryCleanerHome="appuser/service/getServiceInfo";
    String orderDryCleaner="appuser/tgxdfwclothestype/findClothesTypeList";
    String DryOrderCommit="appuser/tgxdfworder/generateOrder";
    String DryOreder="appuser/tgxdfworder/findOrderList";
    String getDryCancelList="appuser/tgxdfworder/findReasonList";
    String commitDryCancelOrder="appuser/tgxdfworder/orderCancel";
    String commitDryComment="appuser/tgxdfworder/addComment";
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
    //工作餐预订
    String createOrder = "appuser/dcfwgzcorder/generateOrder";
    String orderReason = "appuser/dcfwgzcorder/findReasonList";
    String cancelOrder = "appuser/dcfwgzcorder/orderCancel";
    String getTime = "appuser/dcfwgzcorder/getFoodTimeList";
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
    String findVegetableOrderList = "appuser/tdcfworder/findOrderList"; // 查询净菜订单
    String getVegetableDetail = "appuser/tdcfworder/getOrderDetail"; // 获取净菜订单详情
    String getVegetableReason = "appuser/tdcfwordercancelreason/findReasonList"; // 获取取消原因列表
    String cancelVegetableOrder = "appuser/tdcfworder/orderCancel"; // 取消订单
    String vegetableComment = "appuser/tdcfworder/addComment"; // 添加评论

}
