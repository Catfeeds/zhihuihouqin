package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ProductCategoryBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface ProductCategoryView extends MvpView {

    void getSpCategory(ProductCategoryBean bean);

}
