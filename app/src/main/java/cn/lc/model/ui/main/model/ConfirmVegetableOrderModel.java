package cn.lc.model.ui.main.model;

import java.util.HashMap;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/18 0018
 */

public interface ConfirmVegetableOrderModel extends MvpModel {

    Observable submitVegetableOrder(HashMap<String, String> map);

}
