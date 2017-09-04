package cn.lc.model.ui.main.modelimpl;





import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.HealthServerceModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public class HealthServerceModelImpl implements HealthServerceModel {

    @Override
    public Observable getData() {
        LogUtil.log("HealthServerceModel请求数据-->HealthServerceModelImpl");
       Observable observer = RetrofitUtils.getInstance().getHealthServiceHomeData();
        return observer ;
    }
}
