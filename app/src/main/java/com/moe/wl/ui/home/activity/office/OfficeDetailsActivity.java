package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.adapter.office.OfficeEquipmentAdapter;
import com.moe.wl.ui.home.adapter.office.ServiceItemAdapter;
import com.moe.wl.ui.home.bean.office.OfficeDetailsResponse;
import com.moe.wl.ui.home.model.office.OfficeDetailsModel;
import com.moe.wl.ui.home.modelimpl.office.OfficeDetailsModelImpl;
import com.moe.wl.ui.home.presenter.office.OfficeDetailsPresenter;
import com.moe.wl.ui.home.view.office.OfficeDetailsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 办公室详情
 */
public class OfficeDetailsActivity extends BaseActivity<OfficeDetailsModel, OfficeDetailsView, OfficeDetailsPresenter> implements View.OnClickListener, OfficeDetailsView {
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.ll_slider)
    SliderLayout llSlider;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_personnum)
    TextView tvPersonnum;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.gv_equipment)
    NoSlidingGridView gvEquipment;
    @BindView(R.id.gv_service)
    NoSlidingGridView gvService;
    @BindView(R.id.tv_subscribe)
    TextView tvSubscribe;


    private OfficeEquipmentAdapter adapter;
    private List<OfficeDetailsResponse.RoomDetailBean.EnameListBean> mList;

    private String id;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_office_details);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getStringExtra("id");
        initTitle();
        initData();
        if (!TextUtils.isEmpty(id)) {
            getPresenter().officedetails(id);
        }

    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("会议室信息");
    }

    @Override
    public OfficeDetailsPresenter createPresenter() {
        return new OfficeDetailsPresenter();
    }

    @Override
    public OfficeDetailsModel createModel() {
        return new OfficeDetailsModelImpl();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_subscribe:  //预订
                Intent intent = new Intent(this, SubscribeInfoActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void initData() {

        mList = new ArrayList<>();
        //会场设备
        adapter = new OfficeEquipmentAdapter(this);
        adapter.setItemList(mList);
        gvEquipment.setAdapter(adapter);
        //可供服务
        // TODO: 2017/11/6 0006 还没有设置数据
        ServiceItemAdapter serviceItemAdapter = new ServiceItemAdapter(this);
        gvService.setAdapter(serviceItemAdapter);
    }

    @Override
    public void setData(OfficeDetailsResponse.RoomDetailBean bean) {
        if (bean != null) {
            tvName.setText(bean.getName());
            tvPersonnum.setText(bean.getCapacity() + "人");
            tvAddress.setText(bean.getAddress());
            tvArea.setText("");

            if (bean.getImgList() != null) {
                llSlider.removeAllSliders();
                for (int i = 0; i < bean.getImgList().size(); i++) {
                    // map.put("", bean.getImgList().get(i));
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView
                            .description("")
                            .image(bean.getImgList().get(i));
                    llSlider.addSlider(textSliderView);
                }
            }
            mList.addAll(bean.getEnameList());
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.tv_subscribe)
    public void onViewClicked() {//预定
        Intent intent = new Intent(this, SubscribeInfoActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }
}
