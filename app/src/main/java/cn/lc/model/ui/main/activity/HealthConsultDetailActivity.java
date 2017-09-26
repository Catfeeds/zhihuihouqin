package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.model.CollectModel;
import cn.lc.model.ui.main.modelimpl.CollectModelImpl;
import cn.lc.model.ui.main.presenter.CollectPresenter;
import cn.lc.model.ui.main.view.CollectView;

public class HealthConsultDetailActivity extends BaseActivity<CollectModel, CollectView, CollectPresenter> implements CollectView {


    @BindView(R.id.wv_health_consult_detail)
    WebView wvHealthConsultDetail;
    @BindView(R.id.iv_health_consult_detail_back)
    ImageView ivHealthConsultDetailBack;
    @BindView(R.id.tv_write_comment)
    TextView tvWriteComment;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.activity_health_consult_detail)
    RelativeLayout activityHealthConsultDetail;
    private int id;

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
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        id = intent.getIntExtra("id", 0);
        if (url != null)
            wvHealthConsultDetail.loadUrl(url);
    }

   /* private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserCommentRvAdapter rvAdapter = new UserCommentRvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
    }*/

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
                getPresenter().getData(8, id);
                break;
            case R.id.tv_share://分享
                break;
        }
    }

    @Override
    public void getCollectResult(CollectBean collectBean) {
        if(collectBean.getStatus()==1){
            showToast("已收藏");
            // TODO: 2017/9/5 0005 没有图片，根据收藏状态切换图标
            //ivCollect.setImageResource();

        }else{
            showToast("取消收藏");
            //ivCollect.setImageResource();
        }
    }
}
