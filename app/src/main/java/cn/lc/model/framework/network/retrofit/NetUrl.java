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

    //医疗
    String healthServiceHome="appuser/ylfw/ylfwindex";
    String healthInfoCollect="zhihuihouqin-api/appuser/tfavor/addFavor";
    String doctorList="appuser/ylfw/doctorlist";
    String doctorDetail="appuser/ylfw/schedules";
    String more="appuser/healthyinfo/list";
    String userCommentList="appuser/ylfw/getcomment";
    //物业维修
    String wuyeHome="appuser/lffw/preorder";
    //理发师
    String shop="appuser/lffw/toindex";
    String barberList="appuser/lffw/barberlist";
    String barberDetail="appuser/lffw/barberdetail";
    String barberworklist="appuser/lffw/barberworklist";
    String preorder="appuser/lffw/preorder";
    //图书
    String libraryHome ="appuser/tsjyfw/index";
    String recommandBook="appuser/tsjyfw/recommand";
    String bookdetail="appuser/tsjyfw/getbookdetail";
    //工作餐预订
    String createOrder = "appuser/dcfwgzcorder/generateOrder";
    String orderReason = "appuser/dcfwgzcorder/findReasonList";
    String cancelOrder = "appuser/dcfwgzcorder/orderCancel";
    String getTime = "appuser/dcfwgzcorder/getFoodTimeList";
    // 地址管理
    String getAddress = "appuser/address/findUserAddressList";
    // 营养套餐
    String getNutritionData = "appuser/tyytcmeal/findMealList";
    // 意见投诉
    String submitComplain = "appuser/jyfwcomplaint/commitComplanit";
    String getLabelling = "appuser/jyfwcomplaint/findComplanitTag";
    String complainHistory = "appuser/jyfwcomplaint/findComplanitList";

}
