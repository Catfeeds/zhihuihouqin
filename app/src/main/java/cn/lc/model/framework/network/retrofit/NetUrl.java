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

}
