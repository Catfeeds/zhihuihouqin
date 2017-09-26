package cn.lc.model.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.ui.login.activity.IdentityActivity;
import cn.lc.model.ui.main.activity.ServiceOrderActivity;
import cn.lc.model.ui.main.activity.me.LaiFangActivity;
import cn.lc.model.ui.main.activity.me.MyCollectActivity;
import cn.lc.model.ui.main.activity.me.PersonalInfoActivity;
import cn.lc.model.ui.main.activity.me.SettingAct;
import cn.lc.model.ui.main.model.Tab4Model;
import cn.lc.model.ui.main.modelimpl.Tab4ModelImpl;
import cn.lc.model.ui.main.presenter.Tab4Presenter;
import cn.lc.model.ui.main.view.Tab4View;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab4Fragment extends BaseFragment<Tab4Model, Tab4View, Tab4Presenter> implements Tab4View {

    // 我的报修 标题
    private static final String orderRepairs = "待接单,已接单,已完成,待评价,已取消";
    // 办公用品 标题
    private static final String orderOfficeSupplies = "已下单,配送中,已完成,待评价,已取消";
    // 我的订餐 标题
    private static final String orderFood = "待接单,已接单,已完成,待评价,已取消";
    // 理发订单 标题
    private static final String orderHaircuts = "已预约,服务中,已完成,待评价,已取消";
    // 订水订单 标题
    private static final String orderWater = "已下单,配送中,已完成,待评价,已取消";
    // 医疗订单 标题
    private static final String orderMedical = "已预约,已接单,服务中,已完成,待评价";
    // 专家坐诊 标题
    private static final String orderExperts = "已预约,已接单,服务中,已完成,待评价";
    //  洗衣店 标题
    private static final String orderDryCleaner = "已预约,服务中,已完成,已评价,已取消";
    // 图书订单 标题
    private static final String orderBook = "已预订,已借阅,已归还,待评价,已取消";


    // 净菜订单 标题
    private static final String orderVegetable = "已预约,服务中,已完成,已评价,已取消";
    //   标题
    private static final String order = "已预约,服务中,已完成,已评价,已取消";

    Unbinder unbinder;
    @BindView(R.id.iv_bg)
    RelativeLayout ivBg;
    @BindView(R.id.ll_my_packge)
    LinearLayout llMyPackge;
    @BindView(R.id.ll_my_collect)
    LinearLayout llMyCollect;
    @BindView(R.id.ll_personal_auth)
    LinearLayout llPersonalAuth;
    @BindView(R.id.v_bg)
    RelativeLayout vBg;
    @BindView(R.id.civ_header)
    CircleImageView civHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_auth)
    TextView tvAuth;
    @BindView(R.id.iv_img1)
    ImageView ivImg1;
    @BindView(R.id.iv_group1)
    ImageView ivGroup1;
    @BindView(R.id.rl_order_service)
    RelativeLayout rlOrderService;
    @BindView(R.id.iv_child_img1)
    ImageView ivChildImg1;
    @BindView(R.id.tv_child_name1)
    TextView tvChildName1;
    @BindView(R.id.iv_child1)
    ImageView ivChild1;
    @BindView(R.id.rl_turn1)
    RelativeLayout rlTurn1;
    @BindView(R.id.tv_daijiedan1)
    TextView tvDaijiedan1;
    @BindView(R.id.tv_jiedan1)
    TextView tvJiedan1;
    @BindView(R.id.tv_finish1)
    TextView tvFinish1;
    @BindView(R.id.tv_pingjia1)
    TextView tvPingjia1;
    @BindView(R.id.tv_cancle1)
    TextView tvCancle1;
    @BindView(R.id.iv_child_img2)
    ImageView ivChildImg2;
    @BindView(R.id.tv_child_name2)
    TextView tvChildName2;
    @BindView(R.id.iv_child2)
    ImageView ivChild2;
    @BindView(R.id.rl_turn2)
    RelativeLayout rlTurn2;
    @BindView(R.id.tv_daijiedan2)
    TextView tvDaijiedan2;
    @BindView(R.id.tv_jiedan2)
    TextView tvJiedan2;
    @BindView(R.id.tv_finish2)
    TextView tvFinish2;
    @BindView(R.id.tv_pingjia2)
    TextView tvPingjia2;
    @BindView(R.id.tv_cancle2)
    TextView tvCancle2;
    @BindView(R.id.iv_child_img3)
    ImageView ivChildImg3;
    @BindView(R.id.tv_child_name3)
    TextView tvChildName3;
    @BindView(R.id.iv_child3)
    ImageView ivChild3;
    @BindView(R.id.rl_turn3)
    RelativeLayout rlTurn3;
    @BindView(R.id.tv_daijiedan3)
    TextView tvDaijiedan3;
    @BindView(R.id.tv_jiedan3)
    TextView tvJiedan3;
    @BindView(R.id.tv_finish3)
    TextView tvFinish3;
    @BindView(R.id.tv_pingjia3)
    TextView tvPingjia3;
    @BindView(R.id.tv_cancle3)
    TextView tvCancle3;
    @BindView(R.id.iv_child_img4)
    ImageView ivChildImg4;
    @BindView(R.id.tv_child_name4)
    TextView tvChildName4;
    @BindView(R.id.iv_child4)
    ImageView ivChild4;
    @BindView(R.id.rl_turn4)
    RelativeLayout rlTurn4;
    @BindView(R.id.tv_daijiedan4)
    TextView tvDaijiedan4;
    @BindView(R.id.tv_jiedan4)
    TextView tvJiedan4;
    @BindView(R.id.tv_finish4)
    TextView tvFinish4;
    @BindView(R.id.tv_pingjia4)
    TextView tvPingjia4;
    @BindView(R.id.tv_cancle4)
    TextView tvCancle4;
    @BindView(R.id.iv_child_img5)
    ImageView ivChildImg5;
    @BindView(R.id.tv_child_name5)
    TextView tvChildName5;
    @BindView(R.id.iv_child5)
    ImageView ivChild5;
    @BindView(R.id.rl_turn5)
    RelativeLayout rlTurn5;
    @BindView(R.id.tv_daijiedan5)
    TextView tvDaijiedan5;
    @BindView(R.id.tv_jiedan5)
    TextView tvJiedan5;
    @BindView(R.id.tv_finish5)
    TextView tvFinish5;
    @BindView(R.id.tv_pingjia5)
    TextView tvPingjia5;
    @BindView(R.id.tv_cancle5)
    TextView tvCancle5;
    @BindView(R.id.iv_child_img6)
    ImageView ivChildImg6;
    @BindView(R.id.tv_child_name6)
    TextView tvChildName6;
    @BindView(R.id.iv_child6)
    ImageView ivChild6;
    @BindView(R.id.rl_turn6)
    RelativeLayout rlTurn6;
    @BindView(R.id.tv_daijiedan6)
    TextView tvDaijiedan6;
    @BindView(R.id.tv_jiedan6)
    TextView tvJiedan6;
    @BindView(R.id.tv_finish6)
    TextView tvFinish6;
    @BindView(R.id.tv_pingjia6)
    TextView tvPingjia6;
    @BindView(R.id.tv_cancle6)
    TextView tvCancle6;
    @BindView(R.id.iv_child_img7)
    ImageView ivChildImg7;
    @BindView(R.id.tv_child_name7)
    TextView tvChildName7;
    @BindView(R.id.iv_child7)
    ImageView ivChild7;
    @BindView(R.id.rl_turn7)
    RelativeLayout rlTurn7;
    @BindView(R.id.tv_daijiedan7)
    TextView tvDaijiedan7;
    @BindView(R.id.tv_jiedan7)
    TextView tvJiedan7;
    @BindView(R.id.tv_service7)
    TextView tvService7;
    @BindView(R.id.tv_finish7)
    TextView tvFinish7;
    @BindView(R.id.tv_daipingjia7)
    TextView tvDaipingjia7;
    @BindView(R.id.ll_order_service_container)
    LinearLayout llOrderServiceContainer;
    @BindView(R.id.iv_img2)
    ImageView ivImg2;
    @BindView(R.id.iv_group2)
    ImageView ivGroup2;
    @BindView(R.id.rl_laifang_person)
    RelativeLayout rlLaifangPerson;
    @BindView(R.id.iv_img3)
    ImageView ivImg3;
    @BindView(R.id.iv_group3)
    ImageView ivGroup3;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;

    private boolean isOpen = false;
    private Intent intent5;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab4);
    }

    @Override
    public void initView(View v) {
    }

    @Override
    public Tab4Model createModel() {
        return new Tab4ModelImpl();
    }

    @Override
    public Tab4Presenter createPresenter() {
        return new Tab4Presenter();
    }

    @OnClick({R.id.ll_my_packge, R.id.ll_my_collect, R.id.ll_personal_auth, R.id.civ_header,
            R.id.rl_order_service, R.id.rl_turn1, R.id.tv_daijiedan1, R.id.tv_jiedan1, R.id.tv_finish1,
            R.id.tv_pingjia1, R.id.tv_cancle1, R.id.rl_turn2, R.id.tv_daijiedan2, R.id.tv_jiedan2,
            R.id.tv_finish2, R.id.tv_pingjia2, R.id.tv_cancle2, R.id.rl_turn3, R.id.tv_daijiedan3,
            R.id.tv_jiedan3, R.id.tv_finish3, R.id.tv_pingjia3, R.id.tv_cancle3, R.id.rl_turn4,
            R.id.tv_daijiedan4, R.id.tv_jiedan4, R.id.tv_finish4, R.id.tv_pingjia4, R.id.tv_cancle4,
            R.id.rl_turn5, R.id.tv_daijiedan5, R.id.tv_jiedan5, R.id.tv_finish5, R.id.tv_pingjia5,
            R.id.tv_cancle5, R.id.rl_turn6, R.id.tv_daijiedan6, R.id.tv_jiedan6, R.id.tv_finish6,
            R.id.tv_pingjia6, R.id.tv_cancle6, R.id.rl_turn7, R.id.tv_daijiedan7, R.id.tv_jiedan7,
            R.id.tv_service7, R.id.tv_finish7, R.id.tv_daipingjia7, R.id.rl_laifang_person,
            R.id.rl_setting, R.id.rl_turn8, R.id.tv_yuyue8, R.id.tv_service8, R.id.tv_finish8, R.id.tv_pingjia8, R.id.tv_cancle8,
            R.id.rl_turn9, R.id.tv_yuding9, R.id.tv_jieyue9, R.id.tv_guihuan9, R.id.tv_pingjia9, R.id.tv_cancle9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_packge:
                break;

            case R.id.ll_my_collect:
                Intent intent2 = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent2);
                break;

            case R.id.ll_personal_auth:
                Intent intent3 = new Intent(getActivity(), IdentityActivity.class);
                startActivity(intent3);
                break;

            case R.id.civ_header: // 个人信息
                Intent intent1 = new Intent(getActivity(), PersonalInfoActivity.class);
                startActivity(intent1);
                break;

            case R.id.rl_order_service: // 订单服务
                isOpen = !isOpen;
                if (isOpen) {
                    ivGroup1.setImageResource(R.drawable.group_up);
                    llOrderServiceContainer.setVisibility(View.VISIBLE);
                } else {
                    ivGroup1.setImageResource(R.drawable.group_down);
                    llOrderServiceContainer.setVisibility(View.GONE);
                }
                break;

            case R.id.rl_turn1://我的报修
                break;
            case R.id.tv_daijiedan1:
                break;
            case R.id.tv_jiedan1:
                break;
            case R.id.tv_finish1:
                break;
            case R.id.tv_pingjia1:
                break;
            case R.id.tv_cancle1:
                break;
            case R.id.rl_turn2://办公用品
                break;
            case R.id.tv_daijiedan2:
                break;
            case R.id.tv_jiedan2:
                break;
            case R.id.tv_finish2:
                break;
            case R.id.tv_pingjia2:
                break;
            case R.id.tv_cancle2:
                break;
            case R.id.rl_turn3://我的订餐
                break;
            case R.id.tv_daijiedan3:
                break;
            case R.id.tv_jiedan3:
                break;
            case R.id.tv_finish3:
                break;
            case R.id.tv_pingjia3:
                break;
            case R.id.tv_cancle3:
                break;
            case R.id.rl_turn4://理发订单
                break;
            case R.id.tv_daijiedan4:
                break;
            case R.id.tv_jiedan4:
                break;
            case R.id.tv_finish4:
                break;
            case R.id.tv_pingjia4:
                break;
            case R.id.tv_cancle4:
                break;
            case R.id.rl_turn5://订水订单
                break;
            case R.id.tv_daijiedan5:
                break;
            case R.id.tv_jiedan5:
                break;
            case R.id.tv_finish5:
                break;
            case R.id.tv_pingjia5:
                break;
            case R.id.tv_cancle5:
                break;
            case R.id.rl_turn6://医疗订单
                break;
            case R.id.tv_daijiedan6:
                break;
            case R.id.tv_jiedan6:
                break;
            case R.id.tv_finish6:
                break;
            case R.id.tv_pingjia6:
                break;
            case R.id.tv_cancle6:
                break;
            case R.id.rl_turn7://专家坐诊
                break;
            case R.id.tv_daijiedan7:
                break;
            case R.id.tv_jiedan7:
                break;
            case R.id.tv_service7:
                break;
            case R.id.tv_finish7:
                break;
            case R.id.tv_daipingjia7:
                break;

            case R.id.rl_turn8: // 洗衣店
                goServiceActivity(0, Constants.DRYCLEANER, orderDryCleaner);
                break;

            case R.id.tv_yuyue8:
                goServiceActivity(0, Constants.DRYCLEANER, orderDryCleaner);
                break;

            case R.id.tv_service8:
                goServiceActivity(1, Constants.DRYCLEANER, orderDryCleaner);
                break;

            case R.id.tv_finish8:
                goServiceActivity(2, Constants.DRYCLEANER, orderDryCleaner);
                break;

            case R.id.tv_pingjia8:
                goServiceActivity(3, Constants.DRYCLEANER, orderDryCleaner);
                break;

            case R.id.tv_cancle8:
                goServiceActivity(4, Constants.DRYCLEANER, orderDryCleaner);
                break;

            case R.id.rl_turn9: // 图书借阅
                goServiceActivity(0, Constants.BOOK, orderBook);
                break;

            case R.id.tv_yuding9:
                goServiceActivity(0, Constants.BOOK, orderBook);
                break;

            case R.id.tv_jieyue9:
                goServiceActivity(1, Constants.BOOK, orderBook);
                break;

            case R.id.tv_guihuan9:
                goServiceActivity(2, Constants.BOOK, orderBook);
                break;

            case R.id.tv_pingjia9:
                goServiceActivity(3, Constants.BOOK, orderBook);
                break;

            case R.id.tv_cancle9:
                goServiceActivity(4, Constants.BOOK, orderBook);
                break;

            case R.id.rl_laifang_person:
                Intent intent = new Intent(getActivity(), LaiFangActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_setting:
                Intent intent4 = new Intent(getActivity(), SettingAct.class);
                startActivity(intent4);
                break;
        }
    }

    /**
     * 跳转到ServiceOrder页面
     *
     * @param index 脚标
     * @param from  类别
     * @param state 上方标签
     */
    private void goServiceActivity(int index, int from, String state) {
        Intent intent = new Intent(getActivity(), ServiceOrderActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("from", from);
        intent.putExtra("state", state);
        startActivity(intent);
    }
}
