package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ShopBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface ShopView extends MvpView{
void getShopInfo(ShopBean shopBean);


}