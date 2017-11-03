package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.BarberProductDetailBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.HairStyleDetailModel;
import com.moe.wl.ui.main.modelimpl.HairStyleDetailModelImpl;
import com.moe.wl.ui.main.presenter.HairStyleDetailPresenter;
import com.moe.wl.ui.main.view.HairStyleDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HairStyleDetailActivity extends BaseActivity<HairStyleDetailModel,
        HairStyleDetailView, HairStyleDetailPresenter> implements HairStyleDetailView {

    @BindView(R.id.more_product_title)
    TitleBar titleBar;
    @BindView(R.id.iv_hair_style)
    ImageView ivHairStyle;
    @BindView(R.id.tv_hair_style_name)
    TextView tvHairStyleName;
    @BindView(R.id.tv_how_much)
    TextView tvHowMuch;
    @BindView(R.id.tv_hair_style_des)
    TextView tvHairStyleDes;
    @BindView(R.id.activity_hair_style_detail)
    RelativeLayout activityHairStyleDetail;
    private BottomSheetDialog dialog;
    private int favorstatus;
    private int workid;
    private static final int Type = 3;

    @Override
    public HairStyleDetailPresenter createPresenter() {
        return new HairStyleDetailPresenter();
    }

    @Override
    public HairStyleDetailModel createModel() {
        return new HairStyleDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_hair_style_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        Intent intent = getIntent();
        int workid = intent.getIntExtra("workid", -1);
        String img = intent.getStringExtra("img");
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0);
        String brief = intent.getStringExtra("brief");
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, img, ivHairStyle);
        tvHairStyleName.setText("名称：" + name);
        tvHowMuch.setText("价格：￥" + price);
        tvHairStyleDes.setText(brief);
        getPresenter().getdata(workid);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发型详情");
        getCollectStatus();


        titleBar.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().collect(Type, workid);
            }
        });
    }

    private void getCollectStatus() {
        if (favorstatus == 0) {
            titleBar.setTitleRight("收藏");
        } else if(favorstatus==1){
            titleBar.setTitleRight("取消收藏");
        }
    }

    @Override
    public void getDataSucc(BarberProductDetailBean bean) {
        workid = bean.getWorkid();
        favorstatus = bean.getFavorstatus();//0为收藏  1已收藏
        getCollectStatus();
    }

    @Override
    public void collectSucc(CollectBean bean) {
        LogUtils.i("collect state:==" + bean.getStatus());
        if (bean.getStatus() == 0) {
            titleBar.setTitleRight("收藏");
            showToast("取消收藏");
        } else if (bean.getStatus() == 1) {
            titleBar.setTitleRight("取消收藏");
            showToast("收藏成功");
        } else {
            showToast("未知异常");
        }
    }
}
