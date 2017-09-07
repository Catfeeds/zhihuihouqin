package cn.lc.model.ui.login.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface AuthModel extends MvpModel{
    Observable submitAuth(String name,String mobile,
                          String idCard,int positionid,String roomId,String officetel,
                          String cartypeId,String precarCode,String suffixcarCode);
}
