package com.moe.wl.ui.main.activity.orderWater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.WaterAdapter;
import com.moe.wl.ui.main.adapter.OrderWaterTypeAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.bean.OrderWaterSumAndcount;
import com.moe.wl.ui.main.bean.QueryWaterTypeBean;
import com.moe.wl.ui.main.fragment.WaterFragment;
import com.moe.wl.ui.main.model.OrderHomeModel;
import com.moe.wl.ui.main.modelimpl.OrderHomeModelImpl;
import com.moe.wl.ui.main.presenter.OrderHomePresenter;
import com.moe.wl.ui.main.view.OrderHomeView;

public class orderWaterServiceActivity extends BaseActivity<OrderHomeModel,OrderHomeView,OrderHomePresenter> implements OrderHomeView {

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
    private WaterAdapter waterAdapter;
    private List<Fragment> fragments;

    @Override
    public OrderHomePresenter createPresenter() {
        return new OrderHomePresenter();
    }

    @Override
    public OrderHomeModel createModel() {
        return new OrderHomeModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_order_water_service);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OrderWaterSumAndcount event) {
        int count = event.getCount();
        int sum = event.getSum();
        tvCount.setText("共"+count+"份");
        tvSum.setText("￥"+sum);
    };


    @Override
    public void initView() {
        initTitle();
        initTypeView();
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

    @OnClick(R.id.tv_now_order)
    public void onViewClicked() {
    }

    @Override
    public void queryTypeSucc(QueryWaterTypeBean bean) {
        if(bean!=null){
            List<QueryWaterTypeBean.CategoryListBean> categoryList = bean.getCategoryList();
            typeAdapter.setData(categoryList);
            if(categoryList!=null){
                for (int i = 0; i < categoryList.size(); i++) {
                    QueryWaterTypeBean.CategoryListBean categoryListBean = categoryList.get(i);
                    if(categoryListBean!=null){
                        int id = categoryListBean.getId();
                        fragments.add(WaterFragment.getInstance(id));
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
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
