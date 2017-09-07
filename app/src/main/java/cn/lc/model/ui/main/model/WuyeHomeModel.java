package cn.lc.model.ui.main.model;

import java.io.File;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface WuyeHomeModel extends MvpModel{
    Observable getData(int menditem,String username,int mobile,
                       String invitetime,String serviceplace,String mendcontent,File files);

}
