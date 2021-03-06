package com.moe.wl.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.base.MessageEvent;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.login.activity.IdentityActivity;
import com.moe.wl.ui.main.activity.ServiceOrderActivity;
import com.moe.wl.ui.main.activity.me.LaiFangActivity;
import com.moe.wl.ui.main.activity.me.MyCollectActivity;
import com.moe.wl.ui.main.activity.me.MyPurseActivity;
import com.moe.wl.ui.main.activity.me.PersonalInfoActivity;
import com.moe.wl.ui.main.activity.me.SettingAct;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.main.model.Tab4Model;
import com.moe.wl.ui.main.modelimpl.Tab4ModelImpl;
import com.moe.wl.ui.main.presenter.Tab4Presenter;
import com.moe.wl.ui.main.view.Tab4View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab4Fragment extends BaseFragment<Tab4Model, Tab4View, Tab4Presenter> implements Tab4View {

    Unbinder unbinder;
    @BindView(R.id.ll_my_packge)
    LinearLayout llMyPackge;
    @BindView(R.id.ll_my_collect)
    LinearLayout llMyCollect;
    @BindView(R.id.ll_personal_auth)
    LinearLayout llPersonalAuth;
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
    //    @BindView(R.id.tv_jiedan3)
//    TextView tvJiedan3;
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
    @BindView(R.id.rl_vegetable)
    RelativeLayout rlVegetable;
    @BindView(R.id.tv_vegetable_one)
    TextView tvVegetableOne;
    @BindView(R.id.tv_vegetable_two)
    TextView tvVegetableTwo;
    @BindView(R.id.tv_vegetable_three)
    TextView tvVegetableThree;
    @BindView(R.id.tv_vegetable_four)
    TextView tvVegetableFour;
    @BindView(R.id.tv_vegetable_five)
    TextView tvVegetableFive;
    Unbinder unbinder1;

    private boolean isOpen = false;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        sysColor = R.color.font_blue;
        setContentView(R.layout.f_tab4);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getData();//获取用户信息
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.font_blue), true);
    }

    @Override
    public void initView(View v) {

    }

    //更改头像和昵称
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        showToast("更改了用户头像");
        if (event.HEADERCHANGE == event.getCode()) {
//            LogUtils.i("更改了用户头像================"+"url="+event.getParam1());
//            Glide.with(getActivity()).load(event.getParam1()).placeholder(R.drawable.avatar2).into(civHeader);
//            tvName.setText(event.getParam3() + "~" + event.getParam2());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
            /*R.id.tv_jiedan3,*/ R.id.tv_finish3, R.id.tv_pingjia3, R.id.tv_cancle3, R.id.rl_turn4,
            R.id.tv_daijiedan4, R.id.tv_jiedan4, R.id.tv_finish4, R.id.tv_pingjia4, R.id.tv_cancle4,
            R.id.rl_turn5, R.id.tv_daijiedan5, R.id.tv_jiedan5, R.id.tv_finish5, R.id.tv_pingjia5,
            R.id.tv_cancle5, R.id.rl_turn6, R.id.tv_daijiedan6, R.id.tv_jiedan6, R.id.tv_finish6,
            R.id.tv_pingjia6, R.id.tv_cancle6, R.id.rl_turn7, R.id.tv_daijiedan7, R.id.tv_jiedan7,
            R.id.tv_service7, R.id.tv_finish7, R.id.tv_daipingjia7, R.id.rl_laifang_person,
            R.id.rl_setting, R.id.rl_turn8, R.id.tv_yuyue8, R.id.tv_service8, R.id.tv_finish8, R.id.tv_pingjia8, R.id.tv_cancle8,
            R.id.rl_turn9, R.id.tv_yuding9, R.id.tv_jieyue9, R.id.tv_guihuan9, R.id.tv_pingjia9, R.id.tv_cancle9,
            R.id.rl_vegetable, R.id.tv_vegetable_one, R.id.tv_vegetable_two, R.id.tv_vegetable_three, R.id.tv_vegetable_four,
            R.id.tv_vegetable_five, R.id.tv_conference_one, R.id.tv_conference_two, R.id.tv_conference_three, R.id.tv_conference_four,
            R.id.tv_conference_five, R.id.rl_conference,
            R.id.rl_visitors, R.id.tv_visitors_one, R.id.tv_visitors_two, R.id.tv_visitors_three, R.id.tv_visitors_four,
            R.id.tv_daitijao})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.ll_personal_auth) {
            if (SharedPrefHelper.getInstance().getAuthStatus() == 2) {
                showToast("该账号已通过认证，不可重复认证");
                return;
            } else if (SharedPrefHelper.getInstance().getAuthStatus() == 1) {
                showToast("该账号正在认证审核中，请等待审核结果");
                return;
            }else{
                Intent intent3 = new Intent(getActivity(), IdentityActivity.class);
                startActivity(intent3);
            }
            return;
        }
        if (!OtherUtils.isAuth()) {
            // 没有认证
            OtherUtils.showAuth(getActivity());
            return;
        }
        switch (view.getId()) {
            case R.id.ll_my_packge:
                Intent intent = new Intent(getActivity(), MyPurseActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_collect:
                Intent intent2 = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent2);
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
                    ivGroup1.setImageResource(R.mipmap.ic_arrows_down);
                    llOrderServiceContainer.setVisibility(View.GONE);
                }
                break;

            case R.id.rl_turn1://我的报修
                goServiceActivity(0, Constants.PROPERRY, Constants.orderRepairs);
                break;
            case R.id.tv_daijiedan1:
                goServiceActivity(0, Constants.PROPERRY, Constants.orderRepairs);
                break;
            case R.id.tv_jiedan1:
                goServiceActivity(1, Constants.PROPERRY, Constants.orderRepairs);
                break;
            case R.id.tv_finish1:
                goServiceActivity(2, Constants.PROPERRY, Constants.orderRepairs);
                break;
            case R.id.tv_pingjia1:
                goServiceActivity(3, Constants.PROPERRY, Constants.orderRepairs);
                break;
            case R.id.tv_cancle1:
                goServiceActivity(4, Constants.PROPERRY, Constants.orderRepairs);
                break;

            case R.id.rl_turn2://办公用品
                goServiceActivity(0, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;
            case R.id.tv_daijiedan2:
                goServiceActivity(0, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;
            case R.id.tv_jiedan2:
                goServiceActivity(1, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;
            case R.id.tv_finish2:
                goServiceActivity(2, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;
            case R.id.tv_pingjia2:
                goServiceActivity(3, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;
            case R.id.tv_cancle2:
                goServiceActivity(4, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;

            case R.id.rl_turn3:// 我的订餐
                goServiceActivity(0, Constants.ORDERMEAL, Constants.orderFood);
                break;
            case R.id.tv_daijiedan3:
                goServiceActivity(0, Constants.ORDERMEAL, Constants.orderFood);
                break;
//            case R.id.tv_jiedan3:
//                goServiceActivity(2, Constants.ORDERMEAL, Constants.orderFood);
//                break;
            case R.id.tv_finish3:
                goServiceActivity(1, Constants.ORDERMEAL, Constants.orderFood);
                break;
            case R.id.tv_pingjia3:
                goServiceActivity(2, Constants.ORDERMEAL, Constants.orderFood);
                break;
            case R.id.tv_cancle3:
                goServiceActivity(3, Constants.ORDERMEAL, Constants.orderFood);
                break;

            case R.id.rl_turn4:// 理发订单
                goServiceActivity(0, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;
            case R.id.tv_daijiedan4:
                goServiceActivity(0, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;
            case R.id.tv_jiedan4:
                goServiceActivity(1, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;
            case R.id.tv_finish4:
                goServiceActivity(2, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;
            case R.id.tv_pingjia4:
                goServiceActivity(3, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;
            case R.id.tv_cancle4:
                goServiceActivity(4, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;

            case R.id.rl_turn5://订水订单
                goServiceActivity(0, Constants.ORDERWATER, Constants.orderWater);
                break;
            case R.id.tv_daijiedan5:
                goServiceActivity(0, Constants.ORDERWATER, Constants.orderWater);
                break;
            case R.id.tv_jiedan5:
                goServiceActivity(1, Constants.ORDERWATER, Constants.orderWater);
                break;
            case R.id.tv_finish5:
                goServiceActivity(2, Constants.ORDERWATER, Constants.orderWater);
                break;
            case R.id.tv_pingjia5:
                goServiceActivity(3, Constants.ORDERWATER, Constants.orderWater);
                break;
            case R.id.tv_cancle5:
                goServiceActivity(4, Constants.ORDERWATER, Constants.orderWater);
                break;

            case R.id.rl_turn6://医疗订单
                goServiceActivity(0, Constants.MEDICAL, Constants.orderMedical);
                break;
            case R.id.tv_daijiedan6:
                goServiceActivity(0, Constants.MEDICAL, Constants.orderMedical);
                break;
            case R.id.tv_jiedan6:
                goServiceActivity(1, Constants.MEDICAL, Constants.orderMedical);
                break;
            case R.id.tv_finish6:
                goServiceActivity(2, Constants.MEDICAL, Constants.orderMedical);
                break;
            case R.id.tv_pingjia6:
                goServiceActivity(3, Constants.MEDICAL, Constants.orderMedical);
                break;
            case R.id.tv_cancle6:
                goServiceActivity(4, Constants.MEDICAL, Constants.orderMedical);
                break;

            case R.id.rl_turn7://专家坐诊
                goServiceActivity(0, Constants.EXPERTS, Constants.orderExperts);
                break;
            case R.id.tv_daijiedan7:
                goServiceActivity(0, Constants.EXPERTS, Constants.orderExperts);
                break;
            case R.id.tv_jiedan7:
//                goServiceActivity(1, Constants.EXPERTS, Constants.orderExperts);
                break;
            case R.id.tv_service7:
                goServiceActivity(1, Constants.EXPERTS, Constants.orderExperts);
                break;
            case R.id.tv_finish7:
                goServiceActivity(2, Constants.EXPERTS, Constants.orderExperts);
                break;
            case R.id.tv_daipingjia7:
                goServiceActivity(3, Constants.EXPERTS, Constants.orderExperts);
                break;

            case R.id.rl_turn8: // 洗衣店
                goServiceActivity(0, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;
            case R.id.tv_yuyue8:
                goServiceActivity(0, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;
            case R.id.tv_service8:
                goServiceActivity(1, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;
            case R.id.tv_finish8:
                goServiceActivity(2, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;
            case R.id.tv_pingjia8:
                goServiceActivity(3, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;
            case R.id.tv_cancle8:
                goServiceActivity(4, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;

            case R.id.rl_turn9: // 图书借阅
                goServiceActivity(0, Constants.BOOK, Constants.orderBook);
                break;
            case R.id.tv_yuding9:
                goServiceActivity(0, Constants.BOOK, Constants.orderBook);
                break;
            case R.id.tv_jieyue9:
                goServiceActivity(1, Constants.BOOK, Constants.orderBook);
                break;
            case R.id.tv_guihuan9:
                goServiceActivity(2, Constants.BOOK, Constants.orderBook);
                break;
            case R.id.tv_pingjia9:
                goServiceActivity(3, Constants.BOOK, Constants.orderBook);
                break;
            case R.id.tv_cancle9:
                goServiceActivity(4, Constants.BOOK, Constants.orderBook);
                break;

            case R.id.rl_vegetable: // 净菜
                goServiceActivity(0, Constants.VEGETABLE, Constants.orderVegetable);
                break;
            case R.id.tv_vegetable_one:
                goServiceActivity(0, Constants.VEGETABLE, Constants.orderVegetable);
                break;
            case R.id.tv_vegetable_two:
                goServiceActivity(1, Constants.VEGETABLE, Constants.orderVegetable);
                break;
            case R.id.tv_vegetable_three:
                goServiceActivity(2, Constants.VEGETABLE, Constants.orderVegetable);
                break;
            case R.id.tv_vegetable_four:
                goServiceActivity(3, Constants.VEGETABLE, Constants.orderVegetable);
                break;
            case R.id.tv_vegetable_five:
                goServiceActivity(4, Constants.VEGETABLE, Constants.orderVegetable);
                break;

            case R.id.rl_conference:
                goServiceActivity(0, Constants.CONFERENCE, Constants.orderConference);
                break;
            case R.id.tv_conference_one:
                goServiceActivity(0, Constants.CONFERENCE, Constants.orderConference);
                break;
            case R.id.tv_conference_two:
                goServiceActivity(1, Constants.CONFERENCE, Constants.orderConference);
                break;
            case R.id.tv_conference_three:
                goServiceActivity(2, Constants.CONFERENCE, Constants.orderConference);
                break;
            case R.id.tv_conference_four:
                goServiceActivity(3, Constants.CONFERENCE, Constants.orderConference);
                break;
            case R.id.tv_conference_five:
                goServiceActivity(4, Constants.CONFERENCE, Constants.orderConference);
                break;
            case R.id.rl_visitors:
                goServiceActivity(0, Constants.VISITORS, Constants.orderVisitors);
                break;
            case R.id.tv_daitijao:
                goServiceActivity(0, Constants.VISITORS, Constants.orderVisitors);
                break;
            case R.id.tv_visitors_one:
                goServiceActivity(1, Constants.VISITORS, Constants.orderVisitors);
                break;
            case R.id.tv_visitors_two:
                goServiceActivity(2, Constants.VISITORS, Constants.orderVisitors);
                break;
            case R.id.tv_visitors_three:
                goServiceActivity(3, Constants.VISITORS, Constants.orderVisitors);
                break;
            case R.id.tv_visitors_four:
                goServiceActivity(4, Constants.VISITORS, Constants.orderVisitors);
                break;

            case R.id.rl_laifang_person: // 来访人员
                Intent intent5 = new Intent(getActivity(), LaiFangActivity.class);
                startActivity(intent5);
                break;

            case R.id.rl_setting: // 设置
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

 /*   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }*/

  /*  @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }*/

    @Override
    public void getUserInfo(UserInfoBean bean) {
        UserInfoBean.UserinfoEntity userinfo = bean.getUserinfo();
        if (userinfo != null) {
//            Glide.with(this).load(userinfo.getPhoto()).placeholder(R.drawable.avatar2).into(civHeader);
            SharedPrefHelper.getInstance().setHasBuyAuth(userinfo.getHasBuyAuth());
            SharedPrefHelper.getInstance().setAuthStatus(userinfo.getAuthStatus());
            SharedPrefHelper.getInstance().setNickname(userinfo.getNickname());
            SharedPrefHelper.getInstance().setuserPhoto(userinfo.getPhoto());
            SharedPrefHelper.getInstance().setRealName(userinfo.getRealname());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(getActivity(), userinfo.getPhoto(), civHeader, R.drawable.header);
            if (TextUtils.isEmpty(userinfo.getNickname())) {
                if (TextUtils.isEmpty(userinfo.getPosition())) {
                    tvName.setText("职位" + "~" + "暂无昵称");
                } else {
                    tvName.setText(userinfo.getPosition() + "~" + "暂无昵称");
                }
            } else {
                if (TextUtils.isEmpty(userinfo.getPosition())) {
                    tvName.setText("职位" + "~" + userinfo.getNickname());
                } else {
                    tvName.setText(userinfo.getPosition() + "~" + userinfo.getNickname());
                }
            }
            switch (userinfo.getAuthStatus()) {
                case 0: // 未提交认证
                    tvAuth.setText("未提交认证");
                    break;
                case 1: // 认证待审核
                    tvAuth.setText("认证待审核");
                    break;
                case 2: // 认证完成
                    tvAuth.setText("认证完成");
                    break;
                case 3: // 认证不通过
                    tvAuth.setText("认证不通过");
                    break;
                default:
                    tvAuth.setText("未认证");
                    break;
            }
        }
    }
}
