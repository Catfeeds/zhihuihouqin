package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.OrderWaterAdapter;
import com.moe.wl.ui.main.adapter.OrderWaterTypeAdapter;
import com.moe.wl.ui.main.bean.OrderWaterSumAndcount;
import com.moe.wl.ui.main.bean.QueryWaterListBean;
import com.moe.wl.ui.main.bean.QueryWaterTypeBean;
import com.moe.wl.ui.main.fragment.OrderWaterFragment;
import com.moe.wl.ui.main.model.OrderHomeModel;
import com.moe.wl.ui.main.modelimpl.OrderHomeModelImpl;
import com.moe.wl.ui.main.presenter.OrderHomePresenter;
import com.moe.wl.ui.main.view.OrderHomeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        if (countNum > 0) {
            Intent intent = new Intent(this, OrderInfoActivity.class);
            Gson gson = new Gson();
            String json = gson.toJson(list);
            intent.putExtra("json", json);
            startActivity(intent);
        } else {
            showToast("你还没有选择购买");
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
                        fragments.add(OrderWaterFragment.getInstance(id));
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
}
