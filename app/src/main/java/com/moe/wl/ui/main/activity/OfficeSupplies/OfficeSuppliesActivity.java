package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.OfficeIndexAdapter;
import com.moe.wl.ui.main.adapter.OfficeSpAdapter;
import com.moe.wl.ui.main.bean.OfficeIndexBean;
import com.moe.wl.ui.main.model.OfficeIndexModel;
import com.moe.wl.ui.main.modelimpl.OfficeIndexModelImpl;
import com.moe.wl.ui.main.presenter.OfficeIndexPresenter;
import com.moe.wl.ui.main.view.OfficeIndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 办公用品首页
 */
public class OfficeSuppliesActivity extends BaseActivity<OfficeIndexModel, OfficeIndexView, OfficeIndexPresenter> implements AdapterView.OnItemClickListener, OfficeIndexView {


    @BindView(R.id.office_supplies_title)
    TitleBar titleBar;
    @BindView(R.id.iv_office_big_pic)
    ImageView ivOfficeBigPic;
    @BindView(R.id.nsgv_office)
    NoSlidingGridView nsgvOffice;
    @BindView(R.id.nsgv_sp)
    NoSlidingGridView nsgvSp;

    private OfficeSpAdapter officeSpAdapter;
    private List<OfficeIndexBean.NewProductListBean> spList;
    private OfficeIndexAdapter indexAdapter;

    @Override
    public OfficeIndexPresenter createPresenter() {
        return new OfficeIndexPresenter();
    }

    @Override
    public OfficeIndexModel createModel() {
        return new OfficeIndexModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_office_supplies);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initnsgvOffice();
        initNsgvSp();
        getPresenter().getIndexInfo();
    }

    private void initNsgvSp() {
        spList=new ArrayList<>();
        officeSpAdapter = new OfficeSpAdapter(this);
        officeSpAdapter.setItemList(spList);
        nsgvSp.setAdapter(officeSpAdapter);
    }

    private void initnsgvOffice() {
        indexAdapter = new OfficeIndexAdapter(this);
        nsgvOffice.setAdapter(indexAdapter);
        nsgvOffice.setOnItemClickListener(this);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("办公用品");
        titleBar.setTitleRight("发布需求");
        titleBar.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfficeSuppliesActivity.this, PostNeedActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, OfficeCategoryActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void getIndexInfo(OfficeIndexBean bean) {
        if (bean.getErrCode() == 0) {
            if (bean != null) {
                GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getTopphoto(), ivOfficeBigPic);

                List<OfficeIndexBean.CategoryListBean> categoryList = bean.getCategoryList();
                Collections.reverse(categoryList);
                indexAdapter.setData(categoryList);

                spList.addAll(bean.getNewProductList());
                officeSpAdapter.notifyDataSetChanged();
            }
        } else {
            LogUtils.i("获取办公首页出现为题:" + bean.getMsg());
        }
    }
    @OnClick(R.id.civ_car)
    public void onViewClicked() {
        Intent intent=new Intent(this,ShopCarActivity.class);//进入购物车
        startActivity(intent);
    }
}
