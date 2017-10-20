package com.moe.wl.framework.utils;

import com.moe.wl.R;
import com.moe.wl.ui.home.activity.office.OfficeListActivity;
import com.moe.wl.ui.home.activity.saving.SavingActivity;
import com.moe.wl.ui.main.activity.ActivityRegistration.ActivityRegistrationActivity;
import com.moe.wl.ui.main.activity.DryCleaners.DryCleanersActivity;
import com.moe.wl.ui.main.activity.HealthServerceActivity;
import com.moe.wl.ui.main.activity.Library.LibraryActivity;
import com.moe.wl.ui.main.activity.MealsRecharge.MealsRechargeActivity;
import com.moe.wl.ui.main.activity.MoreActivity;
import com.moe.wl.ui.main.activity.OfficeSupplies.OfficeSuppliesActivity;
import com.moe.wl.ui.main.activity.OrderCutHearActivity;
import com.moe.wl.ui.main.activity.complain.SubmitComplainActivity;
import com.moe.wl.ui.main.activity.information.InformationActivity;
import com.moe.wl.ui.main.activity.nutritionalmeal.NutritionActivity;
import com.moe.wl.ui.main.activity.orderWater.orderWaterServiceActivity;
import com.moe.wl.ui.main.activity.ordering.OrderingActivity;
import com.moe.wl.ui.main.activity.property_maintenance.PropertyAintenanceActivity;
import com.moe.wl.ui.main.activity.vegetable.VegetableMainActivity;

import java.util.Arrays;
import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/21 0021
 */

public class ServiceIntentUtils {


    public static List<Integer> serviceImageData = Arrays.asList(
            R.drawable.meals_top_up, R.drawable.nutrition_in,
            R.drawable.cake_reservation, R.drawable.major_reserve,
            R.drawable.cook_service, R.drawable.work_food_order,
            R.drawable.library, R.drawable.order_cut_hair_red,
            R.drawable.dry_cleaners, R.drawable.order_water_service,
            R.drawable.managed_class, R.drawable.mom_child_room,
            R.drawable.deformaldehyde, R.drawable.postal_parcel,
            R.drawable.the_service, R.drawable.hostel_service,
            R.drawable.office_green, R.drawable.car_wash_service,
            R.drawable.vehicle_maintenance, R.drawable.vehicle_inspection,
            R.drawable.vehicle_insurance, R.drawable.offic_room, R.drawable.fixed_assets,
            R.drawable.office_supplies, R.drawable.send_receive_files,
            R.drawable.document_printing, R.drawable.conference_room,
            R.drawable.ticketing_services, R.drawable.destruction,
            R.drawable.car_manger, R.drawable.about_car_service,
            R.drawable.household_manger, R.drawable.wage_undertake, R.drawable.sales_of_travel_expenses,
            R.drawable.medical_treatment, R.drawable.water,
            R.drawable.electricity, R.drawable.gas_fee,
            R.drawable.cable_tv, R.drawable.loacation_phone,
            R.drawable.broadband_pay, R.drawable.heating,
            R.drawable.property_pay_cost, R.drawable.rent_payment,
            R.drawable.capture_expends_query, R.drawable.property_maintenance,
            R.drawable.health_manger, R.drawable.health_service,
            R.drawable.experts_visit, R.drawable.family_planning_manager,
            R.drawable.internal_personnel_discrepancy, R.drawable.visitor_discrepancy,
            R.drawable.internal_vehicle_access, R.drawable.visit_by_visiting_vehicle,
            R.drawable.parking_guidance, R.drawable.building_entrance_guard,
            R.drawable.info_announcement, R.drawable.opinion_complaints,
            R.drawable.enrollment, R.drawable.lost_found, R.drawable.service_saving);

    /**
     * 跳转相应服务
     *
     * @param serviceId
     * @return
     */
    public static Class<?> goService(int serviceId) {
        switch (serviceId) {
            case 1: // 1	餐费充值
                return MealsRechargeActivity.class;
            case 2: // 2	营养套餐
                return NutritionActivity.class;

            case 3: // 3	蛋糕预订

                break;
            case 4: // 4	净菜预订
                return VegetableMainActivity.class;

            case 5: // 5	厨师服务

                break;
            case 6: // 6	工作餐预订
                return OrderingActivity.class;

            case 7: // 7	图书馆
                return LibraryActivity.class;

            case 8: // 8	预约理发
                return OrderCutHearActivity.class;

            case 9: // 9	干洗店
                return DryCleanersActivity.class;

            case 10: //10	订水服务

               return orderWaterServiceActivity.class;
            case 11: //11	托管班

                break;
            case 12: //12	母婴室

                break;
            case 13: //13	除甲醛

                break;
            case 14: //14	邮政包裹

                break;
            case 15: //15	团购服务

                break;
            case 16: //16	招待所服务

                break;
            case 17: //17	办公室绿植

                break;
            case 18: //18	汽车服务

                break;
            case 19: //19	车辆保养

                break;
            case 20: //20	车辆年险

                break;
            case 21: //21	车辆保险

                break;
            case 22: //22	办公用房

                break;
            case 23: //23	固定资产

                break;
            case 24: //24	办公用品
                return OfficeSuppliesActivity.class;

            case 25: //25	机要文件收发

                break;
            case 26: //26	文件印刷

                break;
            case 27: //27	会议室
                return OfficeListActivity.class;

            case 28: //28	票务服务

                break;
            case 29: //29	保密文件销毁

                break;
            case 30: //30	车辆管理

                break;
            case 31: //31	约车服务

                break;
            case 32: //32	户籍管理

                break;
            case 33: //33	工资代发

                break;
            case 34: //34	差旅费核销

                break;
            case 35: //35	医疗费核销

                break;
            case 36: //36	水费收缴

                break;
            case 37: //37	电费收缴

                break;
            case 38: //38	燃气费收缴

                break;
            case 39: //39	有线电视费

                break;
            case 40: //40	固话缴费

                break;
            case 41: //41	宽带缴费

                break;
            case 42: //42	暖气费缴费

                break;
            case 43: //43	物业缴费

                break;
            case 44: //44	房租缴费

                break;
            case 45: //45	缴费查询

                break;
            case 46: //46	物业维修
                return PropertyAintenanceActivity.class;

            case 47: //47	健康管理

                break;
            case 48: //48	医疗服务
                return HealthServerceActivity.class;

            case 49: //49	专家坐诊

                break;
            case 50: //50	计生管理

                break;
            case 51: //51	内部人员出入

                break;
            case 52: //52	来访人员出入

                break;
            case 53: //53	内部车辆出入

                break;
            case 54: //54	来访车辆出入

                break;
            case 55: //55	停车引导

                break;
            case 56: //56	楼宇门禁

                break;
            case 57: //57	信息公告
                return InformationActivity.class;

            case 58: //58	意见投诉
                return SubmitComplainActivity.class;

            case 59: //59	活动报名
                return ActivityRegistrationActivity.class;

            case 60: //60	失物招领

                break;
            case 61: //61	节能减排
                return SavingActivity.class;

            case 10001:
                return MoreActivity.class;
        }
        return null;
    }

}
