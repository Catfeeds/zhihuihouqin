package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.adapter.SignUpPersonAdapter;
import cn.lc.model.ui.main.bean.ActivityHomeBean;
import cn.lc.model.ui.main.bean.ActivitySignListBean;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;
import rx.Observable;
import rx.Subscriber;

public class ActivityDetailActivity extends Base2Activity {

    @BindView(R.id.activity_title)
    TitleBar activityTitle;
    @BindView(R.id.iv_big_pic)
    ImageView ivBigPic;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.tv_act_num)
    TextView tvActNum;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_posted_time)
    TextView tvPostedTime;
    @BindView(R.id.tv_zhubanfang)
    TextView tvZhubanfang;
    @BindView(R.id.tv_sign_up_num)
    TextView tvSignUpNum;
    @BindView(R.id.nsrv_sign_up)
    NoSlideRecyclerView nsrvSignUp;
    @BindView(R.id.activity_detail)
    LinearLayout activityDetail;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.tv_jianjie)
    TextView tvJianjie;
    private SignUpPersonAdapter rvAdapter;
    private ActivityHomeBean.ActivitylistBean activitylistBean;
    private ActivitySignListBean activitySignListBean;
    private String realName;
    private String phoneNumber;
    private boolean isSign;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        activitylistBean = (ActivityHomeBean.ActivitylistBean) getIntent().getSerializableExtra("activitylistBean");
        realName = SharedPrefHelper.getInstance().getRealName();
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        //getSignData(activitylistBean.getAId(), realName, phoneNumber);
        getSignList(activitylistBean.getAId());
        setData();
        initTitle();
        initRecycler();
        //设置scrollView里面的内容置顶
        sv.smoothScrollTo(0, 20);
        sv.setFocusable(true);

    }

    private void setData() {
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this,
                activitylistBean.getAImg(), ivBigPic);
        tvActNum.setText(activitylistBean.getATitle());
        tvAddress.setText("活动地点;" + activitylistBean.getAPlace());
        phone.setText("场馆电话:" + activitylistBean.getAContactMobile());
        tvActNum.setText("活动热数:" + activitylistBean.getATotal() + "人");
        if (activitylistBean.getAStatus() == 1) {
            tvState.setText("报名进行中");
        } else if (activitylistBean.getAStatus() == 2) {
            tvState.setText("报名结束");
        }
        tvPostedTime.setText(activitylistBean.getACreateTime() + "发布");
        tvZhubanfang.setText(activitylistBean.getASponsor());
        tvJianjie.setText(activitylistBean.getAContent());
        tvSignUpNum.setText(activitylistBean.getASignCount() + "人");
    }

    private void getSignList(int aId) {
        Observable observable = RetrofitUtils.getInstance().getActivitySignList(aId);
        showProgressDialog();
        observable.subscribe(new Subscriber<ActivitySignListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                dismissProgressDialog();
            }

            @Override
            public void onNext(ActivitySignListBean o) {
                if (o.getErrCode() == 0) {
                    getSignListSucc(o);
                } else {
                    Log.e("ActivitySignListBean", o.getMsg());
                }
            }
        });
    }


    //获取报名列表
    public void getSignListSucc(ActivitySignListBean o) {
        if (o != null) {
            activitySignListBean = o;
            rvAdapter.setData(o.getMemberlist());
        }
    }

    private void initRecycler() {
        nsrvSignUp.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new SignUpPersonAdapter(this);
        nsrvSignUp.setAdapter(rvAdapter);
        // TODO: 2017/9/8 0008 根据服务端返回登录状态,修改登录字体
        isSign = false;
        if (isSign == true) {
            tvSignUp.setText("已报名");
        } else {
            tvSignUp.setText("报名");
        }

    }

    private void initTitle() {
        activityTitle.setBack(true);
        activityTitle.setTitle("活动详情");
    }

    @OnClick(R.id.tv_sign_up)
    public void onViewClicked() {
        if (isSign == true) {
            showToast("你已经报名了");
        } else {
            Intent intent = new Intent(this, FillInfoActivity.class);
            intent.putExtra("aId", activitylistBean.getAId());
            intent.putExtra("name", realName);
            intent.putExtra("phone", phoneNumber);
            startActivity(intent);
        }

    }


}
