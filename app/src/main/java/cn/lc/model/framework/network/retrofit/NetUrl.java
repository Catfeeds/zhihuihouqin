package cn.lc.model.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    String signup="appuser/user/login?";
    String captcha="appuser/user/getCaptcha?";
    String regist="appuser/user/register?";
    //医疗
    String healthServiceHome="appuser/ylfw/ylfwindex";
    String healthInfoCollect="zhihuihouqin-api/appuser/tfavor/addFavor";
    String doctorList="appuser/ylfw/doctorlist";
    String doctorDetail="appuser/ylfw/schedules";
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
