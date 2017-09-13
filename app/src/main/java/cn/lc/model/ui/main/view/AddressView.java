package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.AddressBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface AddressView extends MvpView {

    void addressData(AddressBean bean);

}
