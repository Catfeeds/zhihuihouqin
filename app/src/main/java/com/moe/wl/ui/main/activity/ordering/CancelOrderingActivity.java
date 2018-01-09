package com.moe.wl.ui.main.activity.ordering;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ReasonAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.ReasonBean;
import com.moe.wl.ui.main.model.CancelOrderingModel;
import com.moe.wl.ui.main.modelimpl.CancelOrderingModelImpl;
import com.moe.wl.ui.main.presenter.CancelOrderingPresenter;
import com.moe.wl.ui.main.view.CancelOrderingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：工作餐取消订单页面
 * 作者：Shixhe On 2017/9/12 0012
 */

public class CancelOrderingActivity extends BaseActivity<CancelOrderingModel, CancelOrderingView, CancelOrderingPresenter> implements CancelOrderingView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.et_reason)
    EditText etReason;

    private int id;
//    private int type;
//    private List<Integer> ids;

    private ReasonAdapter adapter;
    private List<ReasonBean.ServicereasonlistEntity> data;
    private int from;

    private List<String> contentList;
    private int state;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_cancel_ordering);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("取消订单");
//        ids = new ArrayList<>();
        data = new ArrayList<>();
        contentList = new ArrayList<>();
        adapter = new ReasonAdapter(this, data);
        listView.setAdapter(adapter);
        Intent intent = getIntent();
        from = intent.getIntExtra("from", 0);
        id = intent.getIntExtra("OrderingID", 0);
        state = intent.getIntExtra("state", 0);
//        type = intent.getIntExtra("ServiceType", 0);
        //获取取消原因的结果
        getPresenter().getReasonList(from);

        adapter.setOnSelectItemListener(new ReasonAdapter.OnSelectItemListener() {
            @Override
            public void onSelect(int id, String content, boolean isSelect) {
                if (isSelect) {
                    // 添加ID
//                    ids.add(id);
                    contentList.add(content);
                } else {
                    // 删除ID
                    /*for (int i = 0; i < ids.size(); i++) {
                        if (id == ids.get(i)) {
                            ids.remove(i);
                            return;
                        }
                    }*/
                    for (int i = 0; i < contentList.size(); i++) {
                        if (content.equals(contentList.get(i))) {
                            contentList.remove(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void getReasonList(ReasonBean reasonBean) {
        if (reasonBean == null || reasonBean.getServicereasonlist() == null)
            return;
        data.clear();
        data.addAll(reasonBean.getServicereasonlist());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cancelOrder(CollectBean bean) {
        ToastUtil.showToast(this, "取消订单成功！");
        setResult(RESULT_OK, new Intent().putExtra("OrderingID", id));
        EventBus.getDefault().post(new NotifyChange());
        finish();
    }

    @OnClick({R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (id == 0) {
                    ToastUtil.showToast(CancelOrderingActivity.this, "当前订单信息错误！");
                    return;
                }
                String reasonContent = "";
                for (int i = 0; i < contentList.size(); i++) {
                    reasonContent += contentList.get(i) + ",";
                }
                String content = etReason.getText().toString().trim();
                if (reasonContent.length() > 0 && content.length() == 0) {
                    reasonContent = reasonContent.substring(0, reasonContent.length() - 1);
                }
                reasonContent = reasonContent + content;
                if(state==0){//真正的订单取消
                    getPresenter().cancelOrder(from, id, reasonContent);
                }else if(state==9){//还不是订单的取消
                    cancel();
                }

        }
    }
    //取消state=9待提交的订单
    private void cancel() {
        Observable observable = RetrofitUtils.getInstance().commitOrder(id,2);
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean orderBean) {
                if(orderBean==null){
                    return;
                }
                if (orderBean.getErrCode() == 0) {
                    ToastUtil.showToast(CancelOrderingActivity.this, "取消订单成功！");
                    setResult(RESULT_OK, new Intent().putExtra("OrderingID", id));
                    EventBus.getDefault().post(new NotifyChange());
                    finish();
                } else if (orderBean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    showToast(orderBean.getMsg());
                }
            }
        });
    }

    @Override
    public CancelOrderingModel createModel() {
        return new CancelOrderingModelImpl();
    }

    @Override
    public CancelOrderingPresenter createPresenter() {
        return new CancelOrderingPresenter();
    }
}
