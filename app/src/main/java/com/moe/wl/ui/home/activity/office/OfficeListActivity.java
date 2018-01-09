package com.moe.wl.ui.home.activity.office;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.adapter.office.OfficeLitsAdapter;
import com.moe.wl.ui.home.bean.office.OfficeListResponse;
import com.moe.wl.ui.home.model.office.OfficeListModel;
import com.moe.wl.ui.home.modelimpl.office.OfficeListModelImpl;
import com.moe.wl.ui.home.presenter.office.OfficeListPresenter;
import com.moe.wl.ui.home.view.office.OfficeListView;
import com.moe.wl.ui.main.activity.vegetable.VegetableIndexActivity;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.mywidget.ShowHintDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 办公室列表
 */
public class OfficeListActivity extends BaseActivity<OfficeListModel, OfficeListView, OfficeListPresenter> implements  OfficeListView, View.OnClickListener {


    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.lv_content)
    ListView lvContent;
    @BindView(R.id.iv_hint)
    ImageView ivHint;
    private OfficeLitsAdapter adapter;
    private List<OfficeListResponse.ListBean> mList;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_officelist);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        ivHint.setOnClickListener(this);
        initData();
        getPresenter().officelist();
        getHint();

    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("会议室预订");
    }

    @Override
    public OfficeListPresenter createPresenter() {
        return new OfficeListPresenter();
    }

    @Override
    public OfficeListModel createModel() {
        return new OfficeListModelImpl();
    }


    private void initData() {

        mList = new ArrayList<>();

        adapter = new OfficeLitsAdapter(this);
        adapter.setItemList(mList);
        lvContent.setAdapter(adapter);

    }

    @Override
    public void setData(List<OfficeListResponse.ListBean> list, String img) {
        GlideLoading.getInstance().loadImgUrlHeader(this, img, ivIcon, R.mipmap.ic_default_rectangle);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                String id = list.get(i).getId();
                LogUtils.i("id======"+id);
            }
            mList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        SharedPrefHelper.getInstance().setServiceHint(Constants.CONFERENCE, false);
        getHint();

    }
    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.CONFERENCE);
        showProgressDialog();
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
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
                    // TODO 弹出温馨提示窗
                    if (!SharedPrefHelper.getInstance().getServiceHint(Constants.CONFERENCE)) {
                        final ShowHintDialog pop = new ShowHintDialog(OfficeListActivity.this, mResponse.getServiceInfo().getRemind(), Constants.CONFERENCE);
                        pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                            @Override
                            public void onSetting(TextView content) {
                                pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(OfficeListActivity.this, 280));
                            }
                        });
                        pop.show();
                    }
                } else {
                    ToastUtil.showToast(OfficeListActivity.this, mResponse.msg);
                }
            }
        });
    }

}
