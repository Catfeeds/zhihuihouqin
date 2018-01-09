package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.SpCheckShopCarBean;
import com.moe.wl.ui.main.presenter.CheckShopCarPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.adapter.ShopCarListAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.CheckShopCarModel;
import com.moe.wl.ui.main.modelimpl.CheckShopCarModelImpl;
import com.moe.wl.ui.main.view.CheckShopCarView;

public class ShopCarActivity extends BaseActivity<CheckShopCarModel, CheckShopCarView, CheckShopCarPresenter> implements CheckShopCarView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_store_select)
    ImageView ivStoreSelect;
    @BindView(R.id.store)
    TextView store;
    @BindView(R.id.iv_select_all)
    ImageView ivSelectAll;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.activity_shop_car)
    LinearLayout activityShopCar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.tv_clearing)
    TextView tvClearing;
    @BindView(R.id.ll_select_all)
    LinearLayout llSelectAll;
    @BindView(R.id.ll_sum)
    LinearLayout llSum;
    @BindView(R.id.ll_caoshi)
    LinearLayout llCaoshi;
    @BindView(R.id.tv_null)
    TextView tvNull;
    private boolean isEdit = false;
    private boolean isSelectAll;
    private boolean isStoreSelect=false;
    private ShopCarListAdapter listAdapter;
    private List<SpCheckShopCarBean.CartItemsBean> cartItems;
    private int mCount;

    @Override
    public CheckShopCarPresenter createPresenter() {
        return new CheckShopCarPresenter();
    }

    @Override
    public CheckShopCarModel createModel() {
        return new CheckShopCarModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        edit();
        initRecycler();
        getPresenter().checkShopCar();

    }

    private void initData() {
        int count=0;
        double sum=0;
        for (int i = 0; i <cartItems.size() ; i++) {
            boolean seclect = cartItems.get(i).isSeclect();
            int count1 = cartItems.get(i).getCount();
            double price = cartItems.get(i).getSku().getPrice();
            if(seclect){
                count++;
                sum+=count1*price;
            }
        }
        mCount = count;
        tvSum.setText("￥"+sum);
        if(!isEdit)
        tvClearing.setText("去结算("+count+")");
    }

    private void doStoreClick() {
        isStoreSelect=!isStoreSelect;
        if (isStoreSelect) {
            listAdapter.selectAll();
        } else {
            listAdapter.unSelectAll();
        }
        changeSelectback();
    }

    //点击是否全选
    private void doSelectAll() {
        isSelectAll = !isSelectAll;
        if (isSelectAll) {
            ivSelectAll.setVisibility(View.VISIBLE);
            isStoreSelect=true;
            listAdapter.selectAll();
        } else {
            ivSelectAll.setVisibility(View.GONE);
            isStoreSelect=false;
            listAdapter.unSelectAll();
        }
        changeSelectback();

    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ShopCarListAdapter(this);
        recyclerView.setAdapter(listAdapter);
    }

    private void edit() {
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit=!isEdit;
                if (isEdit) {
                    title.setTitleRight("完成");
                    tvClearing.setText("删除");
                    llSum.setVisibility(View.GONE);
//                    ivSelectAll.setVisibility(View.VISIBLE);
//                    isStoreSelect=true;
//                    listAdapter.selectAll();
                } else {
                    title.setTitleRight("编辑");
                    tvClearing.setText("去结算("+mCount+")");
                    llSum.setVisibility(View.VISIBLE);
//                    ivSelectAll.setVisibility(View.GONE);
//                    isStoreSelect=false;
                }
//                changeSelectback();
            }
        });
    }

    private void changeSelectback() {
        if (isStoreSelect) {
            ivStoreSelect.setBackgroundResource(R.drawable.select);
        } else {
            ivStoreSelect.setBackgroundResource(R.drawable.unselect);
        }
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("购物车");
        title.setTitleRight("编辑");
    }

    @OnClick({R.id.iv_store_select, R.id.ll_select_all, R.id.tv_clearing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_select:
                doStoreClick();
                initData();
                break;
            case R.id.ll_select_all:
                doSelectAll();
                initData();
                break;
            case R.id.tv_clearing:
                if (!isEdit ) {
                    Intent intent = new Intent(this, OfficeSpConfirmOrderAct.class);
                    List<SpCheckShopCarBean.CartItemsBean> list=new ArrayList<>();
                    for (int i = 0; i < cartItems.size(); i++) {
                        boolean seclect = cartItems.get(i).isSeclect();
                        if(seclect){
                            list.add(cartItems.get(i));
                        }
                    }
                    Gson gson = new Gson();
                    String json = gson.toJson(list);
                    intent.putExtra("json",json);
                    intent.putExtra("from","shopCar");
                    if(list.size()>0){
                        startActivity(intent);
                    }else{
                        showToast("你还没有选择结算的商品");
                    }
                } else {//删除项
                    List<Integer> list=new ArrayList<>();
                    for (int i = 0; i <cartItems.size() ; i++) {
                        SpCheckShopCarBean.CartItemsBean cartItemsBean = cartItems.get(i);
                        if(cartItemsBean.isSeclect()){
                            int id = cartItemsBean.getId();
                            list.add(id);
                        }
                    }
                    int[] arr=new int[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        arr[i]=list.get(i);
                    }
                    getPresenter().cancelItem(arr);
                }
                break;
        }
    }

    @Override
    public void checkShopCar(SpCheckShopCarBean bean) {
        if (bean != null) {
            cartItems = bean.getCartItems();
            //判断购物车商品条目数
            if(cartItems.size()<=0){
                llCaoshi.setVisibility(View.GONE);
                tvNull.setVisibility(View.VISIBLE);
            }else{
                llCaoshi.setVisibility(View.VISIBLE);
                tvNull.setVisibility(View.GONE);
            }
            initData();
            listAdapter.setData(cartItems);
            listAdapter.setListener(new ShopCarListAdapter.OnClickListener() {
                @Override
                public void onCliickListener() {
                   initData();
                }
            });
        }

    }

    @Override
    public void cancelSucc(ActivityPostBean bean) {
        if(bean!=null){
            getPresenter().checkShopCar();
        }
    }
}
