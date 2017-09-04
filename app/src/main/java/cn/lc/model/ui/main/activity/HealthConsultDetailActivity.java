package cn.lc.model.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.main.adapter.UserCommentRvAdapter;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.HealthServerceHomeBean;
import cn.lc.model.ui.main.model.CollectModel;
import cn.lc.model.ui.main.modelimpl.CollectModelImpl;
import cn.lc.model.ui.main.presenter.CollectPresenter;
import cn.lc.model.ui.main.view.CollectView;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;

public class HealthConsultDetailActivity extends BaseActivity<CollectModel,CollectView,CollectPresenter> implements CollectView{

    @BindView(R.id.tv_content_des)
    TextView tvContentDes;
    @BindView(R.id.wv_health_consult_detail)
    WebView wvHealthConsultDetail;
    @BindView(R.id.rv_user_comment)
    NoSlideRecyclerView recyclerView;
    @BindView(R.id.iv_health_consult_detail_back)
    ImageView ivHealthConsultDetailBack;
    @BindView(R.id.tv_write_comment)
    TextView tvWriteComment;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_srours)
    TextView tvSrours;
    @BindView(R.id.activity_health_consult_detail)
    RelativeLayout activityHealthConsultDetail;
    private HealthServerceHomeBean.InfolistBean infolistBean;

    @Override
    public CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public CollectModel createModel() {
        return new CollectModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_health_consult_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        infolistBean = (HealthServerceHomeBean.InfolistBean) getIntent().getSerializableExtra("infolistBean");
        initRecycler();
        wvHealthConsultDetail.loadUrl(infolistBean.getUrl());
        tvContentDes.setText(infolistBean.getTitle());
        tvTime.setText(infolistBean.getCreatetime());
        tvSrours.setText(infolistBean.getSource());
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserCommentRvAdapter rvAdapter = new UserCommentRvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
    }

    @OnClick({R.id.iv_health_consult_detail_back, R.id.tv_write_comment, R.id.tv_collect, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_health_consult_detail_back:
                finish();
                break;
            case R.id.tv_write_comment://写评论
                View view1 = View.inflate(this, R.layout.comment_pop, null);
                break;
            case R.id.tv_collect://收藏
                getPresenter().getData(8,infolistBean.getId());
                break;
            case R.id.tv_share://分享
                break;
        }
    }

    @Override
    public void getCollectResult(CollectBean collectBean) {
        // TODO: 2017/9/1 0001 通过后端返回状态更改收藏图标
    }
}
