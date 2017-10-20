package com.moe.wl.ui.main.activity;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.adapter.AllGrideAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ServiceDataBean;
import com.moe.wl.ui.main.bean.ServiceMyBean;
import com.moe.wl.ui.main.model.MoreServiceModel;
import com.moe.wl.ui.main.modelimpl.MoreServiceModelImpl;
import com.moe.wl.ui.main.presenter.MoreServicePresenter;
import com.moe.wl.ui.main.view.MoreServiceView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class MoreActivity extends BaseActivity<MoreServiceModel, MoreServiceView, MoreServicePresenter> implements MoreServiceView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    TextView ivSearch;
    @BindView(R.id.iv_f_tab2_close)
    ImageView ivFTab2Close;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.gv_app)
    GridView gvApp;
    @BindView(R.id.ll_f_tab2_grid_container)
    LinearLayout llFTab2GridContainer;
    @BindView(R.id.tv_service_type)
    TextView tvServiceType;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rl_my_app)
    RelativeLayout rlMyApp;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view_line)
    View viewLine;
    private boolean isShow = false;

    private String[] serviceTypes = {"我的应用", "餐饮服务", "生活服务", "资产管理", "机关办公",
            "金融服务", "物业服务", "健康服务", "出入管理", "即时信息"};

    private List<ServiceDataBean> myData;
    List<String> MyApps = Arrays.asList("医疗服务", "物业维修", "图书馆", "活动报名", "预约理发",
            "干洗店", "办公用品");
    List<Integer> MyAppPhotos = Arrays.asList(R.drawable.health_service,
            R.drawable.property_maintenance, R.drawable.library,
            R.drawable.enrollment, R.drawable.reserva_haircut,
            R.drawable.dry_cleaners, R.drawable.office_supplies);

    private List<ServiceDataBean> foodData;
    List<Integer> foodID = Arrays.asList(1, 2, 3, 4, 5, 6);
    List<String> foodService = Arrays.asList("餐费充值", "营养套餐", "蛋糕预订", "净菜预订",/*"外卖预订",*/
            "厨师服务", "工作餐预订");
    List<Integer> foodServicePhotos = Arrays.asList(
            R.drawable.meals_top_up, R.drawable.nutrition_in,
            R.drawable.cake_reservation, R.drawable.major_reserve,/*R.drawable.delivery_booking,*/
            R.drawable.cook_service,
            R.drawable.work_food_order);

    private List<ServiceDataBean> lifeData;
    List<Integer> lifeID = Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
    List<String> lifeService = Arrays.asList(
            "图书馆", "预约理发", "干洗店", "订水服务",
            "托管班", "母婴室", "除甲醛", "邮政包裹",
            "团购服务", "招待所服务", "办公室绿植", "洗车服务",
            "车辆保养", "车辆年检", "车辆保险");
    List<Integer> lifeServicePhotos = Arrays.asList(
            R.drawable.library, R.drawable.order_cut_hair_red,
            R.drawable.dry_cleaners, R.drawable.order_water_service,
            R.drawable.managed_class, R.drawable.mom_child_room,
            R.drawable.deformaldehyde, R.drawable.postal_parcel,
            R.drawable.the_service, R.drawable.hostel_service,
            R.drawable.office_green, R.drawable.car_wash_service,
            R.drawable.vehicle_maintenance, R.drawable.vehicle_inspection,
            R.drawable.vehicle_insurance);

    private List<ServiceDataBean> assetData;
    List<Integer> assetID = Arrays.asList(22, 23);
    List<String> assetManagement = Arrays.asList("办公用房", "固定资产");
    List<Integer> assetManagementPhotos = Arrays.asList(
            R.drawable.offic_room, R.drawable.fixed_assets);

    private List<ServiceDataBean> officeData;
    List<Integer> officeID = Arrays.asList(24, 25, 26, 27, 28, 29, 30, 31, 32);
    List<String> office = Arrays.asList(
            "办公用品", "机要文件收发", "文件印刷", "会议室",
            "票务服务", "保密文件销毁", "车辆管理", "约车服务",
            "户籍管理");
    List<Integer> officePhotos = Arrays.asList(
            R.drawable.office_supplies, R.drawable.send_receive_files,
            R.drawable.document_printing, R.drawable.conference_room,
            R.drawable.ticketing_services, R.drawable.destruction,
            R.drawable.car_manger, R.drawable.about_car_service,
            R.drawable.household_manger);

    private List<ServiceDataBean> financialData;
    List<Integer> financialID = Arrays.asList(33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45);
    List<String> financialService = Arrays.asList(
            "工资代发", "差旅费核销", "医疗费", "水费",
            "电费", "燃气费", "有线电视", "固话",
            "宽带缴费", "暖气费", "物业缴费", "房租缴费",
            "缴费查询");
    List<Integer> financialServicePhotos = Arrays.asList(
            R.drawable.wage_undertake, R.drawable.sales_of_travel_expenses,
            R.drawable.medical_treatment, R.drawable.water,
            R.drawable.electricity, R.drawable.gas_fee,
            R.drawable.cable_tv, R.drawable.loacation_phone,
            R.drawable.broadband_pay, R.drawable.heating,
            R.drawable.property_pay_cost, R.drawable.rent_payment,
            R.drawable.capture_expends_query);

    private List<ServiceDataBean> propertyData;
    List<Integer> propertyID = Arrays.asList(46);
    List<String> propertyService = Arrays.asList("物业维修");
    List<Integer> propertyServicePhotos = Arrays.asList(R.drawable.property_maintenance);

    private List<ServiceDataBean> healthData;
    List<Integer> healthID = Arrays.asList(47, 48, 49, 50);
    List<String> healthService = Arrays.asList(
            "健康管理", "医疗服务", "专家坐诊", "计生管理");
    List<Integer> healthServicePhotos = Arrays.asList(
            R.drawable.health_manger, R.drawable.health_service,
            R.drawable.experts_visit, R.drawable.family_planning_manager);

    private List<ServiceDataBean> outInData;
    List<Integer> outInID = Arrays.asList(51, 52, 53, 54, 55, 56);
    List<String> outInManger = Arrays.asList(
            "内部人员出入", "来访人员出入", "内部车辆出入", "来访车辆出入",
            "停车引导", "楼宇门禁");
    List<Integer> outInMangerPhotos = Arrays.asList(
            R.drawable.internal_personnel_discrepancy, R.drawable.visitor_discrepancy,
            R.drawable.internal_vehicle_access, R.drawable.visit_by_visiting_vehicle,
            R.drawable.parking_guidance, R.drawable.building_entrance_guard);

    private List<ServiceDataBean> instantData;
    List<Integer> instantID = Arrays.asList(57, 58, 59, 60, 61);
    List<String> instantMessage = Arrays.asList(
            "信息公告", "意见投诉", "活动报名", "失物招领", "节能减排");
    List<Integer> instantMessagePhotos = Arrays.asList(
            R.drawable.info_announcement, R.drawable.opinion_complaints,
            R.drawable.enrollment, R.drawable.lost_found, R.drawable.lost_found);

    private AllGrideAdapter gridAdapter;
    private AllGrideAdapter gridAdapter1;
    private AllGrideAdapter gridAdapter2;
    private AllGrideAdapter gridAdapter3;
    private AllGrideAdapter gridAdapter4;
    private AllGrideAdapter gridAdapter5;
    private AllGrideAdapter gridAdapter6;
    private AllGrideAdapter gridAdapter7;
    private AllGrideAdapter gridAdapter8;
    private AllGrideAdapter gridAdapter9;
//    private TextView tvFinish1;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.f_tab2);
    }

    @Override
    public void initView() {
        myData = new ArrayList<>();
        foodData = new ArrayList<>();
        lifeData = new ArrayList<>();
        assetData = new ArrayList<>();
        officeData = new ArrayList<>();
        financialData = new ArrayList<>();
        propertyData = new ArrayList<>();
        healthData = new ArrayList<>();
        outInData = new ArrayList<>();
        instantData = new ArrayList<>();

        setVisibility();
        getPresenter().getMyService();
    }

    @Override
    public void getMyServiceSucc(ServiceMyBean bean) {
        if (bean.getServiceList() == null) {
            initData();
            initGrid();
            return;
        }
        for (int i = 0; i < bean.getServiceList().size(); i++) {
            ServiceDataBean serviceDataBean = new ServiceDataBean(bean.getServiceList().get(i).getId(), 2,
                    bean.getServiceList().get(i).getName());
            myData.add(serviceDataBean);
        }
        initData();
        initGrid();
    }

    @Override
    public void submitMyServiceSucc(CollectBean bean) {
        tvFinish.setText("编辑");
        int type = 0;
        gridAdapter.setType(type);
        gridAdapter1.setType(type);
        gridAdapter2.setType(type);
        gridAdapter3.setType(type);
        gridAdapter4.setType(type);
        gridAdapter5.setType(type);
        gridAdapter6.setType(type);
        gridAdapter7.setType(type);
        gridAdapter8.setType(type);
        gridAdapter9.setType(type);
        refreshUI();
        finish();
    }

    private void initData() {

        for (int i = 0; i < foodID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (foodID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(foodID.get(i), type, foodService.get(i), foodServicePhotos.get(i));
            foodData.add(bean);
        }

        for (int i = 0; i < lifeID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (lifeID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(lifeID.get(i), type, lifeService.get(i), lifeServicePhotos.get(i));
            lifeData.add(bean);
        }
        for (int i = 0; i < assetID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (assetID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(assetID.get(i), type, assetManagement.get(i), assetManagementPhotos.get(i));
            assetData.add(bean);
        }
        for (int i = 0; i < officeID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (officeID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(officeID.get(i), type, office.get(i), officePhotos.get(i));
            officeData.add(bean);
        }
        for (int i = 0; i < financialID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (financialID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(financialID.get(i), type, financialService.get(i), financialServicePhotos.get(i));
            financialData.add(bean);
        }
        for (int i = 0; i < propertyID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (propertyID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(propertyID.get(i), type, propertyService.get(i), propertyServicePhotos.get(i));
            propertyData.add(bean);
        }
        for (int i = 0; i < healthID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (healthID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(healthID.get(i), type, healthService.get(i), healthServicePhotos.get(i));
            healthData.add(bean);
        }
        for (int i = 0; i < outInID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (outInID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(outInID.get(i), type, outInManger.get(i), outInMangerPhotos.get(i));
            outInData.add(bean);
        }
        for (int i = 0; i < instantID.size(); i++) {
            int type = 0;
            for (int j = 0; j < myData.size(); j++) {
                if (instantID.get(i) == myData.get(j).getId()) {
                    type = 1;
                }
            }
            ServiceDataBean bean = new ServiceDataBean(instantID.get(i), type, instantMessage.get(i), instantMessagePhotos.get(i));
            instantData.add(bean);
        }
    }

    private void beyondMost() {
        if (myData.size() == 7) {
            ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
            return;
        }
    }

    private void initGrid() {
        gridAdapter = new AllGrideAdapter(this, myData);
        gvApp.setAdapter(gridAdapter);
        gridAdapter.setOnMinusServiceListener(new AllGrideAdapter.OnMinusServiceListener() {
            @Override
            public void onMinusClick(int position) {
                LogUtils.d("减position：" + position);
                for1:
                for (int x = 0; x < 1; x++) {
                    for (int i = 0; i < foodData.size(); i++) {
                        if (myData.get(position).getId() == foodData.get(i).getId()) {
                            foodData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < lifeData.size(); i++) {
                        if (myData.get(position).getId() == lifeData.get(i).getId()) {
                            lifeData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < assetData.size(); i++) {
                        if (myData.get(position).getId() == assetData.get(i).getId()) {
                            assetData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < officeData.size(); i++) {
                        if (myData.get(position).getId() == officeData.get(i).getId()) {
                            officeData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < financialData.size(); i++) {
                        if (myData.get(position).getId() == financialData.get(i).getId()) {
                            financialData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < propertyData.size(); i++) {
                        if (myData.get(position).getId() == propertyData.get(i).getId()) {
                            propertyData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < healthData.size(); i++) {
                        if (myData.get(position).getId() == healthData.get(i).getId()) {
                            healthData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < outInData.size(); i++) {
                        if (myData.get(position).getId() == outInData.get(i).getId()) {
                            outInData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                    for (int i = 0; i < instantData.size(); i++) {
                        if (myData.get(position).getId() == instantData.get(i).getId()) {
                            instantData.get(i).setIsAdd(0);
                            continue for1;
                        }
                    }
                }
                myData.remove(position);
                LogUtils.d("myData剩余长度：" + myData.size());
                refreshUI();
            }
        });
        for (int i = 1; i < 10; i++) {
            View view = View.inflate(this, R.layout.ll_f_tab2_item, null);
//            tvFinish1 = (TextView) view.findViewById(R.id.tv_finish);
            TextView tvServiceType = (TextView) view.findViewById(R.id.tv_service_type);
            NoSlidingGridView gridView = (NoSlidingGridView) view.findViewById(R.id.nsgv_f_tab_2);
            switch (i) {
                case 1:
                    gridAdapter1 = new AllGrideAdapter(MoreActivity.this, foodData);
                    gridView.setAdapter(gridAdapter1);
                    gridAdapter1.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(foodData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(foodData.get(position).getServiceName());
                            bean.setSourceID(foodData.get(position).getSourceID());
                            myData.add(bean);
                            foodData.get(position).setIsAdd(1);
                            LogUtils.d("myData长度:" + myData.size() + "myData是否选中:" + myData.get(myData.size() - 1).getIsAdd() + "foodData长度:" + foodData.size());
                            refreshUI();
                        }
                    });
                    break;
                case 2:
                    gridAdapter2 = new AllGrideAdapter(MoreActivity.this, lifeData);
                    gridView.setAdapter(gridAdapter2);
                    gridAdapter2.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(lifeData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(lifeData.get(position).getServiceName());
                            bean.setSourceID(lifeData.get(position).getSourceID());
                            myData.add(bean);
                            lifeData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 3:
                    gridAdapter3 = new AllGrideAdapter(MoreActivity.this, assetData);
                    gridView.setAdapter(gridAdapter3);
                    gridAdapter3.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(assetData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(assetData.get(position).getServiceName());
                            bean.setSourceID(assetData.get(position).getSourceID());
                            myData.add(bean);
                            assetData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 4:
                    gridAdapter4 = new AllGrideAdapter(MoreActivity.this, officeData);
                    gridView.setAdapter(gridAdapter4);
                    gridAdapter4.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(officeData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(officeData.get(position).getServiceName());
                            bean.setSourceID(officeData.get(position).getSourceID());
                            myData.add(bean);
                            officeData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 5:
                    gridAdapter5 = new AllGrideAdapter(MoreActivity.this, financialData);
                    gridView.setAdapter(gridAdapter5);
                    gridAdapter5.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(financialData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(financialData.get(position).getServiceName());
                            bean.setSourceID(financialData.get(position).getSourceID());
                            myData.add(bean);
                            financialData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 6:
                    gridAdapter6 = new AllGrideAdapter(MoreActivity.this, propertyData);
                    gridView.setAdapter(gridAdapter6);
                    gridAdapter6.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(propertyData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(propertyData.get(position).getServiceName());
                            bean.setSourceID(propertyData.get(position).getSourceID());
                            myData.add(bean);
                            propertyData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 7:
                    gridAdapter7 = new AllGrideAdapter(MoreActivity.this, healthData);
                    gridView.setAdapter(gridAdapter7);
                    gridAdapter7.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(healthData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(healthData.get(position).getServiceName());
                            bean.setSourceID(healthData.get(position).getSourceID());
                            myData.add(bean);
                            healthData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 8:
                    gridAdapter8 = new AllGrideAdapter(MoreActivity.this, outInData);
                    gridView.setAdapter(gridAdapter8);
                    gridAdapter8.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(outInData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(outInData.get(position).getServiceName());
                            bean.setSourceID(outInData.get(position).getSourceID());
                            myData.add(bean);
                            outInData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;
                case 9:
                    gridAdapter9 = new AllGrideAdapter(MoreActivity.this, instantData);
                    gridView.setAdapter(gridAdapter9);
                    gridAdapter9.setOnAddServiceListener(new AllGrideAdapter.OnAddServiceListener() {
                        @Override
                        public void onAddClick(int position) {
                            if (myData.size() == 7) {
                                ToastUtil.showToast(MoreActivity.this, "最多添加7种服务！");
                                return;
                            }
                            ServiceDataBean bean = new ServiceDataBean();
                            bean.setId(instantData.get(position).getId());
                            bean.setIsAdd(2);
                            bean.setServiceName(instantData.get(position).getServiceName());
                            bean.setSourceID(instantData.get(position).getSourceID());
                            myData.add(bean);
                            instantData.get(position).setIsAdd(1);
                            refreshUI();
                        }
                    });
                    break;

            }
            tvServiceType.setText(serviceTypes[i]);

            llFTab2GridContainer.addView(view);
        }
    }

    private void setVisibility() {
        ivBack.setVisibility(View.VISIBLE);
        rlMyApp.setVisibility(View.VISIBLE);
        gvApp.setVisibility(View.VISIBLE);
        viewLine.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.iv_back, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish://点击进行编辑
                isShow = !isShow;
                if (isShow) {
                    int type = 1;
                    tvFinish.setText("完成");
                    gridAdapter.setType(type);
                    gridAdapter1.setType(type);
                    gridAdapter2.setType(type);
                    gridAdapter3.setType(type);
                    gridAdapter4.setType(type);
                    gridAdapter5.setType(type);
                    gridAdapter6.setType(type);
                    gridAdapter7.setType(type);
                    gridAdapter8.setType(type);
                    gridAdapter9.setType(type);
                    refreshUI();
                } else {
                    int[] ids = new int[myData.size()];
                    for (int i = 0; i < myData.size(); i++) {
                        ids[i] = myData.get(i).getId();
                    }
                    getPresenter().submitMyService(ids);
                }
                break;
        }
    }

    private void refreshUI() {
        gridAdapter.notifyDataSetChanged();
        gridAdapter1.notifyDataSetChanged();
        gridAdapter2.notifyDataSetChanged();
        gridAdapter3.notifyDataSetChanged();
        gridAdapter4.notifyDataSetChanged();
        gridAdapter5.notifyDataSetChanged();
        gridAdapter6.notifyDataSetChanged();
        gridAdapter7.notifyDataSetChanged();
        gridAdapter8.notifyDataSetChanged();
        gridAdapter9.notifyDataSetChanged();
    }

    @Override
    public MoreServicePresenter createPresenter() {
        return new MoreServicePresenter();
    }

    @Override
    public MoreServiceModel createModel() {
        return new MoreServiceModelImpl();
    }
}
