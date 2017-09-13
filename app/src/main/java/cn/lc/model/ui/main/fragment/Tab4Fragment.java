package cn.lc.model.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.login.activity.IdentityActivity;
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
public class Tab4Fragment extends BaseFragment<Tab4Model, Tab4View, Tab4Presenter> implements Tab4View, View.OnClickListener {



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
    Unbinder unbinder;
    private ImageView ivChildTurn;
    private List<String> titles= Arrays.asList("我的报修","办公用品","我的订餐",
            "理发订单","订水订单","医疗订单","专家坐诊","8","9","10");
    private List<String> states=Arrays.asList("已预约","已接单","服务中","已完成"
            ,"待评价");
    private boolean isOpen=false;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab4);
    }

    @Override
    public void initView(View v) {
        for (int i = 0; i < 10; i++) {
            View view = View.inflate(getActivity(), R.layout.order_item, null);
            RelativeLayout rlTurn = (RelativeLayout) view.findViewById(R.id.rl_turn);
            ImageView ivChildImg = (ImageView) view.findViewById(R.id.iv_child_img);
            TextView tvChildName = (TextView) view.findViewById(R.id.tv_child_name);
            ivChildTurn = (ImageView) view.findViewById(R.id.iv_child1);
            TextView tvDaijiedan = (TextView) view.findViewById(R.id.tv_daijiedan);
            TextView tvJiedan = (TextView) view.findViewById(R.id.tv_jiedan);
            TextView tvFinish = (TextView) view.findViewById(R.id.tv_finish);
            TextView tvPingjia = (TextView) view.findViewById(R.id.tv_pingjia);
            TextView tvCancle = (TextView) view.findViewById(R.id.tv_cancle);
            rlTurn.setOnClickListener(this);
            tvDaijiedan.setOnClickListener(this);
            tvJiedan.setOnClickListener(this);
            tvFinish.setOnClickListener(this);
            tvPingjia.setOnClickListener(this);
            tvCancle.setOnClickListener(this);
            tvDaijiedan.setText("已预约");
            tvJiedan.setText("已接单");
            tvFinish.setText("服务中");
            for (int j = 0; j < titles.size(); j++) {
                if(j==i){
                    tvChildName.setText(titles.get(j));
                }
            }
            llOrderServiceContainer.addView(view);
        }

    }

    @Override
    public Tab4Model createModel() {
        return new Tab4ModelImpl();
    }

    @Override
    public Tab4Presenter createPresenter() {
        return new Tab4Presenter();
    }


    @OnClick({R.id.ll_my_packge, R.id.ll_my_collect, R.id.ll_personal_auth, R.id.civ_header, R.id.rl_order_service, R.id.rl_laifang_person, R.id.rl_setting})
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
            case R.id.civ_header:
                Intent intent1 = new Intent(getActivity(),PersonalInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_order_service:
                isOpen = !isOpen;
                if(isOpen==true){
                    ivGroup1.setImageResource(R.drawable.group_up);
                    llOrderServiceContainer.setVisibility(View.VISIBLE);
                }else{
                    ivGroup1.setImageResource(R.drawable.group_down);
                    llOrderServiceContainer.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_laifang_person:
                Intent intent = new Intent(getActivity(),LaiFangActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_setting:
                Intent intent4 = new Intent(getActivity(), SettingAct.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_turn:
                break;
            case R.id.tv_daijiedan:
                break;
            case R.id.tv_jiedan:
                break;
            case R.id.tv_finish:
                break;
            case R.id.tv_pingjia:
                break;
            case R.id.tv_cancle:
                break;
        }

    }
}
