package cn.lc.model.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.activity.ordering.CancelOrderingActivity;
import cn.lc.model.ui.main.adapter.DryCleanAdapter;
import cn.lc.model.ui.main.adapter.WaitOrderAdapter;
import cn.lc.model.ui.main.bean.CheckDryOrderBean;
import cn.lc.model.ui.main.bean.NotifyChange;
import cn.lc.model.ui.mywidget.AlertDialog;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 我的电脑 on 2017/8/17 0017.
 */
public class WaitOrderFragment extends BaseFragment2 {
    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private DryCleanAdapter dryCleanAdapter;
    int limit=10;
    private int page=1;
    private List<CheckDryOrderBean.PageBean.ListBean> listAll=new ArrayList();
    private List<CheckDryOrderBean.PageBean.ListBean> list;
    private int state;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        getData(state, page,limit,true);
    }
    @Override
    public View setLayout() {
        View view = View.inflate(getActivity(), R.layout.fragment_wait_order, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void initView() {
        initRecycler();
        setClick();
    }

    private void setClick() {
        dryCleanAdapter.setListener(new DryCleanAdapter.OnClickListener() {
            @Override
            public void onClickListener(int state,int position) {
                switch (state){
                    case 1:
                      showAlertDialog("是否取消订单",state,position);
                        break;
                    case 2:
                        showAlertDialog("是否拨打服务电话",state,position);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5://删除

                        break;
                }
            }
        });
    }

    private void showAlertDialog(String s, final int state, final int position) {
        new AlertDialog(getActivity()).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                        intent.putExtra("from", Constants.DRYCANCEL);
                        if(list!=null&&list.size()>0){
                            CheckDryOrderBean.PageBean.ListBean listBean = list.get(state - 1);
                            if(listBean!=null){
                                if(state==1){
                                    int id = listBean.getId();
                                    intent.putExtra("OrderingID",id);
                                    startActivity(intent);
                                }else if(state==2){
                                    //todo 应该是服务电话
                                    String mobile = listBean.getMobile();
                                    CallPhoneUtils.callPhone(mobile,getActivity());
                                }
                            }else{
                                LogUtils.i("listBean为空了");

                            }
                        }

                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    public static WaitOrderFragment getIntance(int i){
        WaitOrderFragment waitOrderFragment = new WaitOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from",i);
        waitOrderFragment.setArguments(bundle);
        return waitOrderFragment;
    }
    private void initRecycler() {
        Bundle arguments = getArguments();
        state = arguments.getInt("from");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //WaitOrderAdapter waitOrderAdapter = new WaitOrderAdapter(getActivity());
        dryCleanAdapter = new DryCleanAdapter(getActivity());
        recyclerView.setAdapter(dryCleanAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(state, page,limit,true);
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData(state, page,limit,false);
                recyclerView.loadMoreComplete();
            }
        });
        switch (state){
            case 1:
                getData(1,1,limit,true);
                break;
            case 2:
                getData(2,1,limit,true);
                break;
            case 3:
                getData(3,1,limit,true);
                break;
            case 4:
                getData(4,1,limit,true);
                break;
            case 5:
                getData(5,1,limit,true);
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void getData(final int state, int page, int limit, final boolean isRefresh) {
        Observable observable = RetrofitUtils.getInstance().checkDryOrder(state, page, limit);
        showProgressDialog();
        observable.subscribe(new Subscriber<CheckDryOrderBean>() {
            @Override
            public void onCompleted() {
            dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i("获取订单出现问题");
                dismissProgressDialog();
            }

            @Override
            public void onNext(CheckDryOrderBean orderBean) {
                if(orderBean.getErrCode()==0){
                    list = orderBean.getPage().getList();
                    if(isRefresh==true){
                        listAll.clear();
                    }
                    listAll.addAll(list);
                    dryCleanAdapter.setData(listAll,state);
                }
            }
        });
    }
}
