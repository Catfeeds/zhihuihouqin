package cn.lc.model.ui.main.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.CommentlistBean;
import cn.lc.model.ui.main.bean.DoctorDetailBean;
import cn.lc.model.ui.main.bean.UserCommentBean;
import cn.lc.model.ui.main.model.DoctorDetailModel;
import cn.lc.model.ui.main.modelimpl.DoctorDetailModelImpl;
import cn.lc.model.ui.main.presenter.DoctorDetailPresenter;
import cn.lc.model.ui.main.view.DoctorDetailView;

public class MoreUSerCommentActivity extends BaseActivity<DoctorDetailModel,DoctorDetailView,DoctorDetailPresenter> implements DoctorDetailView {

    @BindView(R.id.more_user_comment_title)
    TitleBar titleBar;
    @BindView(R.id.rv_more_user_comment)
    XRecyclerView recyclerView;
    private DoctorDetailrvAdapter rvAdapter;
    private List<CommentlistBean> lists=new ArrayList<>();
    private int page;
    private int id;
    private boolean isRefresh=false;

    @Override
    public DoctorDetailPresenter createPresenter() {
        return new DoctorDetailPresenter();
    }

    @Override
    public DoctorDetailModel createModel() {
        return new DoctorDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_more_user_comment);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", -1);
        getPresenter().getUserComment(id,1,10);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new DoctorDetailrvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                page = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPresenter().getUserComment(id,page,10);
                        recyclerView.refreshComplete();
                    }
                }, 200);

            }

            @Override
            public void onLoadMore() {
                isRefresh=false;
                page++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPresenter().getUserComment(id,page,10);
                        recyclerView.loadMoreComplete();
                    }
                }, 200);
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("更多评论");
    }

    @Override
    public void getDoctorDetailSucc(DoctorDetailBean listBean) {

    }

    @Override
    public void getUserCommentListSucc(UserCommentBean userCommentBean) {
        if(userCommentBean!=null){
            if(isRefresh=true){
                lists.clear();
            }
            lists.addAll(userCommentBean.getCommentlist());
           rvAdapter.setData(lists);
        }
    }

    @Override
    public void getCollectResult(CollectBean collectBean) {

    }
}
