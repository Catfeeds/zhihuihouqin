package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.OfficeIndexAdapter;
import cn.lc.model.ui.main.adapter.OfficeSpAdapter;
import cn.lc.model.ui.main.bean.OfficeIndexBean;
import cn.lc.model.ui.main.model.OfficeIndexModel;
import cn.lc.model.ui.main.modelimpl.OfficeIndexModelImpl;
import cn.lc.model.ui.main.presenter.OfficeIndexPresenter;
import cn.lc.model.ui.main.view.OfficeIndexView;

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
        officeSpAdapter = new OfficeSpAdapter(this);
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
                List<OfficeIndexBean.NewProductListBean> newProductList = bean.getNewProductList();
                officeSpAdapter.setData(newProductList);
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
