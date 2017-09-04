package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface ShopModel extends MvpModel{

    Observable getData();
}
