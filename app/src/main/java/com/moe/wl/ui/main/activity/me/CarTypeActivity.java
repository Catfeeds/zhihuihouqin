package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.CarTypeAdapter;
import com.moe.wl.ui.main.bean.CartypeslistBean;
import com.moe.wl.ui.main.model.CarTypeModel;
import com.moe.wl.ui.main.presenter.CarTypePresenter;
import com.moe.wl.ui.main.view.CarTypeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class CarTypeActivity extends BaseActivity<CarTypeModel, CarTypeView, CarTypePresenter> implements CarTypeView {
    @BindView(R.id.mTitleBar)
    TitleBar title;
    @BindView(R.id.rv_item_container)
    RecyclerView rvItemContainer;
    private CarTypeAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_position);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("车辆类别");
        rvItemContainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarTypeAdapter(this);
        rvItemContainer.setAdapter(adapter);

    }

    @Override
    public void getcartypelistResult(CartypeslistBean bean) {
        if(bean!=null){
          final  List<CartypeslistBean.CartypelistBean> cartypelist = bean.getCartypelist();
            adapter.setData(cartypelist);
            adapter.setLister(new CarTypeAdapter.OnClickLister() {
                @Override
                public void itemClickLister(int position) {
                    CartypeslistBean.CartypelistBean cartypelistBean = cartypelist.get(position);
                    String typename = cartypelistBean.getTypename();
                    int id = cartypelistBean.getId();
                    Intent intent = new Intent();
                    intent.putExtra("typename",typename);
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
    }

    @Override
    public CarTypeModel createModel() {
        return null;
    }

    @Override
    public CarTypePresenter createPresenter() {
        return null;
    }
}
