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
import com.moe.wl.ui.home.adapter.office.OfficeEquipmentAdapter;
import com.moe.wl.ui.home.bean.office.OfficeDetailsResponse;
import com.moe.wl.ui.home.model.office.OfficeDetailsModel;
import com.moe.wl.ui.home.modelimpl.office.OfficeDetailsModelImpl;
import com.moe.wl.ui.home.presenter.office.OfficeDetailsPresenter;
import com.moe.wl.ui.home.view.office.OfficeDetailsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 办公室详情
 */
public class OfficeDetailsActivity extends BaseActivity<OfficeDetailsModel, OfficeDetailsView, OfficeDetailsPresenter> implements View.OnClickListener, OfficeDetailsView {

    private LinearLayout ll_back;
    private TextView tv_title;
    private LinearLayout ll_right;
    private SliderLayout ll_slider;
    private TextView tv_name;
    private TextView tv_saturation;
    private TextView tv_time;
    private TextView tv_number;
    private TextView tv_location;
    private TextView tv_introduce;
    private NoSlidingGridView gv_equipment;
    private TextView tv_subscribe;

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
        id=getIntent().getStringExtra("id");

        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_slider = (SliderLayout) findViewById(R.id.ll_slider);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_saturation = (TextView) findViewById(R.id.tv_saturation);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_location = (TextView) findViewById(R.id.tv_location);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        gv_equipment = (NoSlidingGridView) findViewById(R.id.gv_equipment);
        tv_subscribe = (TextView) findViewById(R.id.tv_subscribe);
        tv_subscribe.setOnClickListener(this);

        initData();
        if (!TextUtils.isEmpty(id)){
            getPresenter().officedetails(id);
        }

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
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void initData() {

        mList = new ArrayList<>();

        adapter = new OfficeEquipmentAdapter(this);
        adapter.setItemList(mList);
        gv_equipment.setAdapter(adapter);

    }

    @Override
    public void setData(OfficeDetailsResponse.RoomDetailBean bean) {
        if (bean!=null){
            tv_name.setText(bean.getName());
            tv_saturation.setText(bean.getCapacity()+"人");
            if ("1".equals(bean.getTimeserving())){
                tv_time.setText("上午");
            }else if ("2".equals(bean.getTimeserving())){
                tv_time.setText("下午午");
            }else if ("3".equals(bean.getTimeserving())){
                tv_time.setText("全天");
            }else{
                tv_time.setText("全天");
            }
            tv_number.setText(bean.getUsenumber());
            tv_location.setText(bean.getAddress());

            tv_introduce.setText(bean.getIntroduce());

            if (bean.getImgList()!=null){
                HashMap<String, String> map = new HashMap<>();
                for (int i = 0; i < bean.getImgList().size(); i++) {
                    map.put("", bean.getImgList().get(i));
                }
                initSliderLayout(map);
            }
            mList.addAll(bean.getEnameList());
            adapter.notifyDataSetChanged();
        }
    }

    // 轮播图数据
    private void initSliderLayout(HashMap<String, String> map) {
        ll_slider.removeAllSliders();
        for (String desc : map.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(desc)
                    .image(map.get(desc));
            ll_slider.addSlider(textSliderView);
        }
    }
}
