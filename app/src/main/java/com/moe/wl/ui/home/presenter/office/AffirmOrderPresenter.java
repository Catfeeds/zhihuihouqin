package com.moe.wl.ui.home.presenter.office;

import com.google.gson.JsonArray;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.office.AffirmOrderResponse;
import com.moe.wl.ui.home.model.office.AffirmOrderModel;
import com.moe.wl.ui.home.view.office.AffirmOrderView;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AffirmOrderPresenter extends MvpRxPresenter<AffirmOrderModel, AffirmOrderView> {

    public void findAvailableEquipment(String roomid, String username, String mobile, String equipmentids, String conferencetype, String conferencename,
                                       String attendnum, String attentdleader, String remark, JsonArray appointmentInfo, List<String> path) {
        getView().showProgressDialog();
        Observable request;
        if (path==null || path.size()==0){
            request = getModel().findAvailableEquipment(roomid, username, mobile, equipmentids, conferencetype, conferencename,
                    attendnum, attentdleader, remark,appointmentInfo);
        }else{
            request = getModel().findAvailableEquipment(roomid, username, mobile, equipmentids, conferencetype, conferencename,
                    attendnum, attentdleader, remark,appointmentInfo,path);
        }

        getNetWork(request, new Subscriber<AffirmOrderResponse>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("接口请求错误："+e);
            }

            @Override
            public void onNext(AffirmOrderResponse mResponse) {
                if (mResponse.errCode == 0) {
                    getView().setData();
                } else {
                    getView().showToast(mResponse.msg);
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
