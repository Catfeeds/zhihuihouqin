package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.adapter.AllGrideAdapter;
import com.moe.wl.ui.main.bean.ServiceDataBean;
import com.moe.wl.ui.main.model.Tab2Model;
import com.moe.wl.ui.main.presenter.Tab2Presenter;
import com.moe.wl.ui.main.view.Tab2View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by hh on 2016/5/18.
 */
public class Tab2Fragment extends BaseFragment<Tab2Model, Tab2View, Tab2Presenter> implements Tab2View {


    @BindView(R.id.iv_search)
    TextView ivSearch;
    @BindView(R.id.iv_f_tab2_close)
    ImageView ivFTab2Close;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.ll_f_tab2_grid_container)
    LinearLayout llFTab2GridContainer;
    @BindView(R.id.line)
    View line;
    Unbinder unbinder;

    private String[] serviceTypes = {/*"我的应用",*/ "餐饮服务", "生活服务", "资产管理", "机关办公",
            "金融服务", "物业服务", "健康服务", "出入管理", "即时信息"};

    /*List<String> MyApps = Arrays.asList("健康档案", "物业维修", "图书馆", "活动报名", "美容美发",
            "干洗店", "办公用品");
    List<Integer> MyAppPhotos = Arrays.asList(R.drawable.health_service,
            R.drawable.property_maintenance, R.drawable.library,
            R.drawable.enrollment, R.drawable.reserva_haircut,
            R.drawable.dry_cleaners, R.drawable.office_supplies);*/

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
            "图书馆", "美容美发", "洗衣店", "订水服务",
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
            "工资代发", "差旅费核销", "医疗费核销", "水费收缴",
            "电费收缴", "燃气费收缴", "有线电视费", "固话缴费",
            "宽带缴费", "暖气费缴费", "物业缴费", "房租缴费",
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
            "健康管理", "健康档案", "专家坐诊", "计生管理");
    List<Integer> healthServicePhotos = Arrays.asList(
            R.drawable.health_manger, R.drawable.health_service,
            R.drawable.experts_visit, R.drawable.family_planning_manager);

    private List<ServiceDataBean> outInData;
    List<Integer> outInID = Arrays.asList(51, 52, 53, 54, 55, 56);
    List<String> outInManger = Arrays.asList(
            "内部人员出入", "来访人员", "内部车辆出入", "来访车辆出入",
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

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        sysColor=R.color.white;
        setContentView(R.layout.f_tab2);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white), true);
    }

    private void initData() {
//        myData = new ArrayList<>();
        foodData = new ArrayList<>();
        lifeData = new ArrayList<>();
        assetData = new ArrayList<>();
        officeData = new ArrayList<>();
        financialData = new ArrayList<>();
        propertyData = new ArrayList<>();
        healthData = new ArrayList<>();
        outInData = new ArrayList<>();
        instantData = new ArrayList<>();

//        for (int i = 0; i < MyApps.size(); i++) {
//            ServiceDataBean bean = new ServiceDataBean(lifeID.get(i), 2, MyApps.get(i), MyAppPhotos.get(i));
//            myData.add(bean);
//        }

        for (int i = 0; i < foodID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(foodID.get(i), type, foodService.get(i), foodServicePhotos.get(i));
            foodData.add(bean);
        }

        for (int i = 0; i < lifeID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(lifeID.get(i), type, lifeService.get(i), lifeServicePhotos.get(i));
            lifeData.add(bean);
        }
        for (int i = 0; i < assetID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(assetID.get(i), type, assetManagement.get(i), assetManagementPhotos.get(i));
            assetData.add(bean);
        }
        for (int i = 0; i < officeID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(officeID.get(i), type, office.get(i), officePhotos.get(i));
            officeData.add(bean);
        }
        for (int i = 0; i < financialID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(financialID.get(i), type, financialService.get(i), financialServicePhotos.get(i));
            financialData.add(bean);
        }
        for (int i = 0; i < propertyID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(propertyID.get(i), type, propertyService.get(i), propertyServicePhotos.get(i));
            propertyData.add(bean);
        }
        for (int i = 0; i < healthID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(healthID.get(i), type, healthService.get(i), healthServicePhotos.get(i));
            healthData.add(bean);
        }
        for (int i = 0; i < outInID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(outInID.get(i), type, outInManger.get(i), outInMangerPhotos.get(i));
            outInData.add(bean);
        }
        for (int i = 0; i < instantID.size(); i++) {
            int type = 0;
            ServiceDataBean bean = new ServiceDataBean(instantID.get(i), type, instantMessage.get(i), instantMessagePhotos.get(i));
            instantData.add(bean);
        }
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        initData();
        for (int i = 1; i < 10; i++) {
            View view = View.inflate(getActivity(), R.layout.ll_f_tab2_item, null);
//            TextView tvFinish = (TextView) view.findViewById(R.id.tv_finish);
            TextView tvServiceType = (TextView) view.findViewById(R.id.tv_service_type);
            NoSlidingGridView gridView = (NoSlidingGridView) view.findViewById(R.id.nsgv_f_tab_2);

            switch (i) {
                case 1://餐饮服务
                    AllGrideAdapter grideAdapter1 = new AllGrideAdapter(getActivity(), foodData);
                    gridView.setAdapter(grideAdapter1);
                    break;
                case 2:
                    AllGrideAdapter grideAdapter2 = new AllGrideAdapter(getActivity(), lifeData);
                    gridView.setAdapter(grideAdapter2);
                    break;
                case 3:
                    AllGrideAdapter grideAdapter3 = new AllGrideAdapter(getActivity(), assetData);
                    gridView.setAdapter(grideAdapter3);
                    break;
                case 4:
                    AllGrideAdapter grideAdapter4 = new AllGrideAdapter(getActivity(), officeData);
                    gridView.setAdapter(grideAdapter4);
                    break;
                case 5:
                    AllGrideAdapter grideAdapter5 = new AllGrideAdapter(getActivity(), financialData);
                    gridView.setAdapter(grideAdapter5);
                    break;
                case 6:
                    AllGrideAdapter grideAdapter6 = new AllGrideAdapter(getActivity(), propertyData);
                    gridView.setAdapter(grideAdapter6);
                    break;
                case 7:
                    AllGrideAdapter grideAdapter7 = new AllGrideAdapter(getActivity(), healthData);
                    gridView.setAdapter(grideAdapter7);
                    break;
                case 8:
                    AllGrideAdapter grideAdapter8 = new AllGrideAdapter(getActivity(), outInData);
                    gridView.setAdapter(grideAdapter8);
                    break;
                case 9:
                    AllGrideAdapter grideAdapter9 = new AllGrideAdapter(getActivity(), instantData);
                    gridView.setAdapter(grideAdapter9);
                    break;
            }
            tvServiceType.setText(serviceTypes[i - 1]);

            llFTab2GridContainer.addView(view);
        }

    }

    @Override
    public Tab2Presenter createPresenter() {
        return new Tab2Presenter();
    }

    @Override
    public Tab2Model createModel() {
        return null;
    }

}
