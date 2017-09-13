package cn.lc.model.ui.main.activity.ordering;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.NoSlidingListView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ReasonAdapter;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ReasonBean;
import cn.lc.model.ui.main.model.CancelOrderingModel;
import cn.lc.model.ui.main.modelimpl.CancelOrderingModelImpl;
import cn.lc.model.ui.main.presenter.CancelOrderingPresenter;
import cn.lc.model.ui.main.view.CancelOrderingView;
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

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_cancel_ordering);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("取消订单");
        id = getIntent().getIntExtra("OrderingID", 0);
        ids = new ArrayList<>();
        data = new ArrayList<>();
        adapter = new ReasonAdapter(this, data);
        listView.setAdapter(adapter);

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
        finish();
    }

    @OnClick({R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (id == 0) {
                    ToastUtil.showToast(CancelOrderingActivity.this, "当前订单信息错误！");
                }
                int[] reasonId = new int[ids.size()];
                for (int i = 0; i < ids.size(); i++) {
                    reasonId[i] = ids.get(i);
                }
                getPresenter().cancelOrdering(id, reasonId, etReason.getText().toString().trim());
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
