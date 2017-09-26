package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.widget.CustomerDialog;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.adapter.HomeNsrlv3Adapter;
import cn.lc.model.ui.main.bean.ActivityHomeBean;
import rx.Observable;
import rx.Subscriber;

public class ActivityRegistrationActivity extends Base2Activity {

    @BindView(R.id.activity_title)
    TitleBar activityTitle;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.iv_big_pic)
    ImageView ivBigPic;
    @BindView(R.id.rv_activity)
    XRecyclerView rvActivity;
    @BindView(R.id.tv_activity_posted)
    TextView tvActivityPosted;
    private CustomerDialog progressDialog;
    private HomeNsrlv3Adapter homeNsrlv3Adapter;
    private boolean isRefresh=false;
    private int page;
    List<ActivityHomeBean.ActivitylistBean> listAll=new ArrayList<>();

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_registration2);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        getData(1,10);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        rvActivity.setLayoutManager(new LinearLayoutManager(this));
        homeNsrlv3Adapter = new HomeNsrlv3Adapter(this);
        rvActivity.setAdapter(homeNsrlv3Adapter);
        rvActivity.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvActivity.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh=true;
                page = 1;
                getData(page,10);
                rvActivity.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                isRefresh=false;
                page ++;
                getData(page,10);
                rvActivity.loadMoreComplete();
            }
        });
    }

    private void initTitle() {
        activityTitle.setBack(true);
        activityTitle.setTitle("活动报名");
    }

    @OnClick(R.id.tv_activity_posted)
    public void onViewClicked() {
        Intent intent = new Intent(this, ActivityPostedActivity.class);
        startActivity(intent);
    }

    public void getData(int page,int limit) {
        Observable observer = RetrofitUtils.getInstance().getActivityHome(page,limit);
        showProgressDialog();
        observer.subscribe(new Subscriber<ActivityHomeBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(ActivityHomeBean homeBean) {
                if(homeBean.getErrCode()==0){
                    getDataSucc(homeBean);
                }else{
                    Log.e("ActivityHomeBean",homeBean.getMsg());
                }
            }
        });
    }

    private void getDataSucc(ActivityHomeBean homeBean) {
        if(homeBean!=null){
            String picture = homeBean.getPicture();
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this,picture,ivBigPic);
            if(isRefresh==true) {
                listAll.clear();
            }
            listAll.addAll(homeBean.getActivitylist());
            homeNsrlv3Adapter.setData(listAll);
        }
    }

}
