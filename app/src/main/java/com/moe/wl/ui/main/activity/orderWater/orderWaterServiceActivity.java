package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.OrderWaterAdapter;
import com.moe.wl.ui.main.adapter.OrderWaterTypeAdapter;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.OrderWaterSumAndcount;
import com.moe.wl.ui.main.bean.QueryWaterListBean;
import com.moe.wl.ui.main.bean.QueryWaterTypeBean;
import com.moe.wl.ui.main.fragment.OrderWaterFragment;
import com.moe.wl.ui.main.model.OrderHomeModel;
import com.moe.wl.ui.main.modelimpl.OrderHomeModelImpl;
import com.moe.wl.ui.main.presenter.OrderHomePresenter;
import com.moe.wl.ui.main.view.OrderHomeView;
import com.moe.wl.ui.mywidget.ShowHintDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

public class orderWaterServiceActivity extends BaseActivity<OrderHomeModel, OrderHomeView, OrderHomePresenter> implements OrderHomeView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    /*  @BindView(R.id.rv_water)
      RecyclerView rvWater;*/
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.activity_order_water_service)
    LinearLayout activityOrderWaterService;
    private OrderWaterTypeAdapter typeAdapter;
    private OrderWaterAdapter waterAdapter;
    private List<Fragment> fragments;
    private List<QueryWaterListBean.PageBean.ListBean> list;
    private List<QueryWaterListBean.PageBean.ListBean> listAll;
    private int countNum;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_order_water_service);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OrderWaterSumAndcount event) {

        int count = 0;
        int sum = 0;
        boolean isHave = false;
        if (event.isAdd()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == event.getId()) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                list.add(event.getData());
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == event.getId()) {
//                    int count1 = list.get(i).getCount();
                    int num = list.get(i).getCount();
                    if (num <= 0) {
                        list.remove(i);
                        break;
                    } else {
                        list.get(i).setCount(num);
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            count += list.get(i).getCount();
            sum += list.get(i).getCount() * list.get(i).getPrice();
        }
        countNum = count;
        tvCount.setText("共" + count + "份");
        tvSum.setText("￥" + sum);
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        listAll = new ArrayList<>();
        initTitle();
        initTypeView();
        getHint();
        getPresenter().queryWaterType();
        fragments = new ArrayList<>();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("订水服务");
    }

    private void initTypeView() {
        rvType.setLayoutManager(new LinearLayoutManager(this));
        typeAdapter = new OrderWaterTypeAdapter(this);
        rvType.setAdapter(typeAdapter);
    }
    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.ORDERWATER);
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.errCode == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.errCode == 0) {
                    //初始化首页信息
                    // TODO 弹出温馨提示窗
                    if (!SharedPrefHelper.getInstance().getServiceHint(Constants.ORDERWATER)) {
                        final ShowHintDialog pop = new ShowHintDialog(orderWaterServiceActivity.this, mResponse.getServiceInfo().getRemind(), Constants.ORDERWATER);
                        pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                            @Override
                            public void onSetting(TextView content) {
                                pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(orderWaterServiceActivity.this, 280));
                            }
                        });
                        pop.show();
                    }
                } else {
                    ToastUtil.showToast(orderWaterServiceActivity.this, mResponse.msg);
                }
            }
        });
    }
    @OnClick({R.id.tv_now_order,R.id.iv_hint})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.tv_now_order:
                if (countNum > 0) {
                    Intent intent = new Intent(this, ConfirmOrderActivity.class);
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    intent.putExtra("json", json);
                    startActivity(intent);
                } else {
                    showToast("你还没有选择购买");
                }
                break;
            case R.id.iv_hint:
                SharedPrefHelper.getInstance().setServiceHint(Constants.ORDERWATER, false);
                getHint();
                break;
        }

    }

    @Override
    public void queryTypeSucc(QueryWaterTypeBean bean) {
        if (bean != null) {
            List<QueryWaterTypeBean.CategoryListBean> categoryList = bean.getCategoryList();
            typeAdapter.setData(categoryList);
            if (categoryList != null) {
                for (int i = 0; i < categoryList.size(); i++) {
                    QueryWaterTypeBean.CategoryListBean categoryListBean = categoryList.get(i);
                    if (categoryListBean != null) {
                        int id = categoryListBean.getId();
                        int needdeposit = categoryListBean.getNeeddeposit();
                        fragments.add(OrderWaterFragment.getInstance(id,needdeposit));
                    }
                }
            }
            //初始化fragment首页
            switchFragment(fragments.get(0));
            typeAdapter.setListener(new OrderWaterTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    Fragment fragment = fragments.get(position);
                    switchFragment(fragment);
                }
            });
        }
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public OrderHomePresenter createPresenter() {
        return new OrderHomePresenter();
    }

    @Override
    public OrderHomeModel createModel() {
        return new OrderHomeModelImpl();
    }
}
