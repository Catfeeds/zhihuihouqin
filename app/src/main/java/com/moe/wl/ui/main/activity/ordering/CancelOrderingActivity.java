package com.moe.wl.ui.main.activity.ordering;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ReasonAdapter;
import com.moe.wl.ui.main.modelimpl.CancelOrderingModelImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.ReasonBean;
import com.moe.wl.ui.main.model.CancelOrderingModel;
import com.moe.wl.ui.main.presenter.CancelOrderingPresenter;
import com.moe.wl.ui.main.view.CancelOrderingView;
import mvp.cn.util.ToastUtil;

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
    private List<Integer> ids;

    private ReasonAdapter adapter;
    private List<ReasonBean.ReasonListEntity> data;
    private String from;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_cancel_ordering);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("取消订单");
        ids = new ArrayList<>();
        data = new ArrayList<>();
        adapter = new ReasonAdapter(this, data);
        listView.setAdapter(adapter);
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        id = intent.getIntExtra("OrderingID", 0);
        switch (from) {
            case Constants.DRYCANCEL:
                getPresenter().getDryCancelList();
                break;
        }
        getPresenter().getReasonList();

        adapter.setOnSelectItemListener(new ReasonAdapter.OnSelectItemListener() {
            @Override
            public void onSelect(int id, boolean isSelect) {
                if (isSelect) {
                    // 添加ID
                    ids.add(id);
                } else {
                    // 删除ID
                    for (int i = 0; i < ids.size(); i++) {
                        if (id == ids.get(i)) {
                            ids.remove(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void getReasonList(ReasonBean reasonBean) {
        if (reasonBean == null || reasonBean.getReasonList() == null)
            return;
        data.clear();
        data.addAll(reasonBean.getReasonList());
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

                LogUtils.i(id+"的值为");
                if (id == 0) {
                    ToastUtil.showToast(CancelOrderingActivity.this, "当前订单信息错误！");
                }
                int[] reasonId = new int[ids.size()];
                for (int i = 0; i < ids.size(); i++) {
                    reasonId[i] = ids.get(i);
                }
                Gson gson = new Gson();
                String s = gson.toJson(reasonId);
                String content = etReason.getText().toString().trim();
                if(from.equals(Constants.DRYCANCEL)){
                    getPresenter().commitDryCancelOrder(id,s,content);
                }

//                getPresenter().cancelOrdering(id, reasonId, etReason.getText().toString().trim());
                break;
        }
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
