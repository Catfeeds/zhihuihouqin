package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ProductCategoryModel;
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
