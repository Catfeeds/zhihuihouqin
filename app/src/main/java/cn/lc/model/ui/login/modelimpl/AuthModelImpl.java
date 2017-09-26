package cn.lc.model.ui.login.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.login.model.AuthModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class AuthModelImpl implements AuthModel {

    @Override
    public Observable submitAuth(String name, String mobile, String idCard, int positionid, String roomId, String officetel, String cartypeId, String precarCode, String suffixcarCode) {
        Log.e("AuthModelImpl","--->获取了数据");
        Observable observer = RetrofitUtils.getInstance().submitAuth(name,mobile,idCard,positionid,roomId,officetel,cartypeId,precarCode,suffixcarCode);
        return observer ;
    }
}
