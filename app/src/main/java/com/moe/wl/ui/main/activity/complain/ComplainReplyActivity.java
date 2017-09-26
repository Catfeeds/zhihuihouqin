package com.moe.wl.ui.main.activity.complain;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ComplainReplyAdapter;
import com.moe.wl.ui.main.bean.ComplainReplyBean;
import com.moe.wl.ui.main.model.ComplainReplyModel;
import com.moe.wl.ui.main.modelimpl.ComplainReplyModelImpl;
import com.moe.wl.ui.main.view.ComplainReplyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

import com.moe.wl.ui.main.presenter.ComplainReplyPresenter;

import mvp.cn.util.ToastUtil;

/**
 * 类描述：投诉反馈列表页
 * 作者：Shixhe On 2017/9/9 0009
 */
public class ComplainReplyActivity extends BaseActivity<ComplainReplyModel, ComplainReplyView, ComplainReplyPresenter> implements ComplainReplyView {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recycleView)
    XRecyclerView recycleView;

    private int id;
    private ComplainReplyAdapter adapter;
    private List<ComplainReplyBean.PageEntity.ListEntity> data;
    private int page = 1;

    private int lastOffset, lastPosition;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain_reply);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("ID", 0);
        titleBar.setTitle("反馈回复");
        titleBar.setBack(true);
        data = new ArrayList<>();
        recycleView.setLoadingMoreEnabled(false);
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page++;
                getPresenter().getComplainReply(id, page);
            }

            @Override
            public void onLoadMore() {
            }
        });
        //监听RecyclerView滚动状态
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.getLayoutManager() != null) {
                    getPositionAndOffset();
                }
            }
        });
        getPresenter().getComplainReply(id, page);
    }

    /**
     * 记录RecyclerView当前位置
     */
    private void getPositionAndOffset() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recycleView.getLayoutManager();
        //获取可视的第一个view
        View topView = layoutManager.getChildAt(0);
        if (topView != null) {
            //获取与该view的顶部的偏移量
            lastOffset = topView.getTop();
            //得到该View的数组位置
            lastPosition = layoutManager.getPosition(topView);
        }
    }

    /**
     * 让RecyclerView滚动到指定位置
     */
    private void scrollToPosition(int dataSize) {
        if (recycleView.getLayoutManager() != null && lastPosition >= 0) {
            ((LinearLayoutManager) recycleView.getLayoutManager()).scrollToPositionWithOffset(lastPosition + dataSize, lastOffset);
        }
    }

    @OnClick({R.id.send})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.send) {
            if (etContent.getText().toString().trim().length() > 0)
                getPresenter().feedbackMessage(id, etContent.getText().toString().trim());
            else
                ToastUtil.showToast(this, "请输入反馈内容！");
        }
    }

    @Override
    public void getComplainReply(ComplainReplyBean bean) {
        recycleView.refreshComplete();
        if (bean.getPage() == null || bean.getPage().getList() == null)
            return;
        if (bean.getPage().getCurrPage() == 1) {
            data.clear();
        }
        for (int i = 0; i < bean.getPage().getList().size(); i++) {
            data.add(0, bean.getPage().getList().get(i));
        }
        if (null == adapter) {
            adapter = new ComplainReplyAdapter(this, data);
            recycleView.setLayoutManager(new LinearLayoutManager(ComplainReplyActivity.this));
            recycleView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        LogUtils.d("当前页数：" + bean.getPage().getCurrPage() + "   数据长度：" + bean.getPage().getList().size());
        scrollToPosition(bean.getPage().getList().size() + 1);
        if (page == 1)
            recycleView.scrollToPosition(data.size());
        etContent.setText("");
        page = bean.getPage().getCurrPage();
    }

    @Override
    public void feedbackMessage() {
//        ComplainReplyBean.PageEntity.ListEntity entity = new ComplainReplyBean.PageEntity.ListEntity();
//        entity.setCreatetime(null);
//        entity.setContent(etContent.getText().toString());
//        entity.setUtype(1);
//        etContent.setText("");
//        adapter.notifyDataSetChanged();
        getPresenter().getComplainReply(id, 1);
    }

    @Override
    public ComplainReplyModel createModel() {
        return new ComplainReplyModelImpl();
    }

    @Override
    public ComplainReplyPresenter createPresenter() {
        return new ComplainReplyPresenter();
    }

}
