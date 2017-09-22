package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ShopCarListAdapter;
import cn.lc.model.ui.main.bean.SpCheckShopCarBean;
import cn.lc.model.ui.main.model.CheckShopCarModel;
import cn.lc.model.ui.main.modelimpl.CheckShopCarModelImpl;
import cn.lc.model.ui.main.presenter.CheckShopCarPresenter;
import cn.lc.model.ui.main.view.CheckShopCarView;

public class ShopCarActivity extends BaseActivity<CheckShopCarModel,CheckShopCarView,CheckShopCarPresenter> implements CheckShopCarView {

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
    private boolean isEdit=false;
    private ShopCarListAdapter listAdapter;

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

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ShopCarListAdapter(this);
        recyclerView.setAdapter(listAdapter);
    }

    private void edit() {
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEdit){
                    isEdit=true;
                    title.setTitleRight("完成");
                    tvSum.setText("删除");
                    ivSelectAll.setVisibility(View.VISIBLE);
                    ivStoreSelect.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("购物车");
        title.setTitleRight("编辑");
    }

    @OnClick({R.id.iv_store_select, R.id.iv_select_all,R.id.tv_clearing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_select:
                break;
            case R.id.iv_select_all:

                break;
            case R.id.tv_clearing:
                if(!isEdit){
                    Intent intent = new Intent(this,OfficeSpConfirmOrderAct.class);
                    // TODO: 2017/9/21 0021 传递参数到订单界面
//                    intent.putExtra()
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void checkShopCar(SpCheckShopCarBean bean) {
        if(bean!=null){
            final List<SpCheckShopCarBean.CartItemsBean> cartItems = bean.getCartItems();
            listAdapter.setData(cartItems);
            setSumAndCount(cartItems);//没生效
            listAdapter.setListener(new ShopCarListAdapter.OnClickListener() {
                @Override
                public void onCliickListener(int count, int index) {
                    setSumAndCount(cartItems);
                }
            });
        }

    }

    private void setSumAndCount(List<SpCheckShopCarBean.CartItemsBean> cartItems) {
        int sum=0;
        int count=0;
        for (int i = 0; i < cartItems.size(); i++) {
            SpCheckShopCarBean.CartItemsBean cartItemsBean = cartItems.get(i);
            int count1 = cartItemsBean.getCount();
            int price = cartItemsBean.getSku().getPrice();
            if(cartItemsBean.isSeclect()==true){
                sum+=count1*price;
                count+=count1;
            }
        }
        tvSum.setText(sum+"");
        tvClearing.setText("去结算("+count+")");
    }
}
