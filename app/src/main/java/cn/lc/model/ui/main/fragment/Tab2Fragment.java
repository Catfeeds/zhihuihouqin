package cn.lc.model.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.ui.main.activity.complain.SubmitComplainActivity;
import cn.lc.model.ui.main.activity.information.InformationActivity;
import cn.lc.model.ui.main.activity.nutritionalmeal.NutritionActivity;
import cn.lc.model.ui.main.activity.ordering.OrderingActivity;
import cn.lc.model.ui.main.adapter.AllGrideAdapter;
import cn.lc.model.ui.main.model.Tab2Model;
import cn.lc.model.ui.main.presenter.Tab2Presenter;
import cn.lc.model.ui.main.view.Tab2View;


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
    Unbinder unbinder;
    private String[] serviceTypes = {"我的应用", "餐饮服务", "生活服务", "资产管理", "机关办公",
            "金融服务", "物业服务", "健康服务", "出入管理", "即使信息"};

    List<String> MyApps = Arrays.asList("医疗服务", "物业维修", "图书馆", "活动报名", "预约理发",
            "干洗店", "办公用品");
    List<Integer> MyAppPhotos = Arrays.asList(R.drawable.health_service,
            R.drawable.property_maintenance, R.drawable.library,
            R.drawable.enrollment, R.drawable.reserva_haircut,
            R.drawable.dry_cleaners, R.drawable.office_supplies);
    List<String> foodService = Arrays.asList("餐费充值", "营养套餐", "蛋糕预订", "外卖预订", "厨师服务",
            "净菜预订", "工作餐预订");
    List<Integer> foodServicePhotos = Arrays.asList(
            R.drawable.meals_top_up, R.drawable.nutrition_in,
            R.drawable.cake_reservation, R.drawable.delivery_booking,
            R.drawable.cook_service, R.drawable.major_reserve,
            R.drawable.work_food_order);
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
    List<String> assetManagement = Arrays.asList("办公用房", "固定资产");
    List<Integer> assetManagementPhotos = Arrays.asList(
            R.drawable.offic_room, R.drawable.fixed_assets);
    List<String> office = Arrays.asList(
            "办公用品", "机要文件收发", "文件印刷", "会议室",
            "票务服务", "保密文件销毁", "车辆管理", "约车服务",
            "户籍管理");
    List<Integer> officePhotos = Arrays.asList(
            R.drawable.office_upplies, R.drawable.send_receive_files,
            R.drawable.document_printing, R.drawable.conference_room,
            R.drawable.ticketing_services, R.drawable.destruction,
            R.drawable.car_manger, R.drawable.about_car_service,
            R.drawable.household_manger);
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
    List<String> propertyService = Arrays.asList("物业维修");
    List<Integer> propertyServicePhotos = Arrays.asList(R.drawable.property_maintenance);
    List<String> healthService = Arrays.asList(
            "健康管理", "医疗服务", "专家坐诊", "计生管理");
    List<Integer> healthServicePhotos = Arrays.asList(
            R.drawable.health_manger, R.drawable.health_service,
            R.drawable.experts_visit, R.drawable.family_planning_manager);
    List<String> outInManger = Arrays.asList(
            "内部人员出入", "来访人员出入", "内部车辆出入", "来访车辆出入",
            "停车引导", "楼宇门禁");
    List<Integer> outInMangerPhotos = Arrays.asList(
            R.drawable.internal_personnel_discrepancy, R.drawable.visitor_discrepancy,
            R.drawable.internal_vehicle_access, R.drawable.visit_by_visiting_vehicle,
            R.drawable.parking_guidance, R.drawable.building_entrance_guard);
    List<String> instantMessage = Arrays.asList(
            "信息公告", "意见投诉", "活动报名", "失物招领");
    List<Integer> instantMessagePhotos = Arrays.asList(
            R.drawable.info_announcement, R.drawable.opinion_complaints,
            R.drawable.enrollment, R.drawable.lost_found);

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab2);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        for (int i = 0; i < 10; i++) {
            View view = View.inflate(getActivity(), R.layout.ll_f_tab2_item, null);
            TextView tvFinish = (TextView) view.findViewById(R.id.tv_finish);
            TextView tvServiceType = (TextView) view.findViewById(R.id.tv_service_type);
            NoSlidingGridView gridView = (NoSlidingGridView) view.findViewById(R.id.nsgv_f_tab_2);

            switch (i) {
                case 0:
                    AllGrideAdapter grideAdapter = new AllGrideAdapter(getActivity(), MyApps, MyAppPhotos);
                    gridView.setAdapter(grideAdapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    break;
                            }

                        }
                    });
                    break;
                case 1://餐饮服务
                    AllGrideAdapter grideAdapter1 = new AllGrideAdapter(getActivity(), foodService, foodServicePhotos);
                    gridView.setAdapter(grideAdapter1);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 1:// 营养套餐
                                    startActivity(new Intent(getActivity(), NutritionActivity.class));
                                    break;
                                case 6:// 工作餐预订
                                    startActivity(new Intent(getActivity(), OrderingActivity.class));
                                    break;
                            }

                        }
                    });
                    break;
                case 2:
                    AllGrideAdapter grideAdapter2 = new AllGrideAdapter(getActivity(), lifeService, lifeServicePhotos);
                    gridView.setAdapter(grideAdapter2);
                    break;
                case 3:
                    AllGrideAdapter grideAdapter3 = new AllGrideAdapter(getActivity(), assetManagement, assetManagementPhotos);
                    gridView.setAdapter(grideAdapter3);
                    break;
                case 4:
                    AllGrideAdapter grideAdapter4 = new AllGrideAdapter(getActivity(), office, officePhotos);
                    gridView.setAdapter(grideAdapter4);
                    break;
                case 5:
                    AllGrideAdapter grideAdapter5 = new AllGrideAdapter(getActivity(), financialService, financialServicePhotos);
                    gridView.setAdapter(grideAdapter5);
                    break;
                case 6:
                    AllGrideAdapter grideAdapter6 = new AllGrideAdapter(getActivity(), propertyService, propertyServicePhotos);
                    gridView.setAdapter(grideAdapter6);
                    break;
                case 7:
                    AllGrideAdapter grideAdapter7 = new AllGrideAdapter(getActivity(), healthService, healthServicePhotos);
                    gridView.setAdapter(grideAdapter7);
                    break;
                case 8:
                    AllGrideAdapter grideAdapter8 = new AllGrideAdapter(getActivity(), outInManger, outInMangerPhotos);
                    gridView.setAdapter(grideAdapter8);
                    break;
                case 9:
                    AllGrideAdapter grideAdapter9 = new AllGrideAdapter(getActivity(), instantMessage, instantMessagePhotos);
                    gridView.setAdapter(grideAdapter9);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:// 信息公告
                                    startActivity(new Intent(getActivity(), InformationActivity.class));
                                    break;

                                case 1:// 意见投诉
                                    startActivity(new Intent(getActivity(), SubmitComplainActivity.class));
                                    break;
//                                case 6:// 工作餐预订
//                                    startActivity(new Intent(getActivity(), OrderingActivity.class));
//                                    break;
                            }

                        }
                    });
                    break;

            }
            tvServiceType.setText(serviceTypes[i]);

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


  /*  class Params {
        String keyWords;
        String consultType;
        int startPage = 1;
        int pageSize = 10;
        String orderBy;
        String addrId;
        double lon = 0;
        String sectionId;
        String free;
        double lat = 0;
        String status;
    }*/

}
