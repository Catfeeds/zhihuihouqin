package com.moe.wl.ui.main.activity.complain;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ComplainImageAdapter;
import com.moe.wl.ui.main.bean.ComplainDetailBean;
import com.moe.wl.ui.main.model.ComplainDetailModel;
import com.moe.wl.ui.main.modelimpl.ComplainDetailModelImpl;
import com.moe.wl.ui.main.presenter.ComplainDetailPresenter;
import com.moe.wl.ui.main.view.ComplainDetailView;

/**
 * 类描述：投诉详情页
 * 作者：Shixhe On 2017/9/8 0008
 */
public class ComplainHistoryDetailActivity extends BaseActivity<ComplainDetailModel, ComplainDetailView, ComplainDetailPresenter> implements ComplainDetailView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.labelling)
    TextView labelling;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.suggest)
    TextView suggest;

    private int id;
    private ComplainImageAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("反馈详情");
        id = getIntent().getIntExtra("ID", 0);
//        id = 1;
        getPresenter().getComplainDetail(id);
    }

    @OnClick({R.id.btn_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_reply:
                startActivity(new Intent(ComplainHistoryDetailActivity.this, ComplainReplyActivity.class).putExtra("ID", id));
                break;
        }
    }

    @Override
    public void getComplainDetail(ComplainDetailBean bean) {
        if (bean.getComplaint() == null || bean.getComplaint().getImgs() == null)
            return;
        adapter = new ComplainImageAdapter(this, bean.getComplaint().getImgs());
        listView.setAdapter(adapter);
        labelling.setText("投诉标签：" + bean.getComplaint().getTagName());
        content.setText("投诉内容：" + bean.getComplaint().getComplaintContent());
        suggest.setText("建议：" + bean.getComplaint().getSuggestContent());
    }

    @Override
    public ComplainDetailModel createModel() {
        return new ComplainDetailModelImpl();
    }

    @Override
    public ComplainDetailPresenter createPresenter() {
        return new ComplainDetailPresenter();
    }
}
