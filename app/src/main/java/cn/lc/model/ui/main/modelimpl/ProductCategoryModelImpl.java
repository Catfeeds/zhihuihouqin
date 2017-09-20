package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.ProductCategoryModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ProductCategoryModelImpl implements ProductCategoryModel {

    @Override
    public Observable getSpCategory(String s1,String s2,String s3) {
        Observable observable = RetrofitUtils.getInstance().getSpCategory(s1,s2,s3);
        return observable;
    }
}
