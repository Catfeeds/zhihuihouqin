package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ExpandableListAdapter;
import com.moe.wl.ui.main.model.PreOderBarberModel;
import com.moe.wl.ui.main.modelimpl.PreOrderBarberModelImpl;
import com.moe.wl.ui.main.presenter.PreOrderBarberPresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.adapter.BarberGridAdapter;
import com.moe.wl.ui.main.adapter.OrderTimeAdapter;
import com.moe.wl.ui.main.bean.BarberListBean;
import com.moe.wl.ui.main.bean.PreOrderBean;
import com.moe.wl.ui.main.view.PreOrderBarberView;
import com.moe.wl.ui.mywidget.NoScrollExpandableListView;

import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.DateUtils;

public class ReservaBarberActivity extends BaseActivity<PreOderBarberModel, PreOrderBarberView, PreOrderBarberPresenter> implements PreOrderBarberView {
    private static final int MAX_NUM = 100;
    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.iv_barber_background)
    ImageView ivBarberBackground;
    @BindView(R.id.civ_barber_header)
    CircleImageView civBarberHeader;
    @BindView(R.id.tv_barber_name)
    TextView tvBarberName;
    @BindView(R.id.tv_barber_chengwei)
    TextView tvBarberChengwei;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.rv_reserva_date)
    RecyclerView recyclerView;
    @BindView(R.id.iv_sun)
    ImageView ivSun;
    @BindView(R.id.tv_sun)
    TextView tvSun;
    @BindView(R.id.ll_morning)
    LinearLayout llMorning;
    @BindView(R.id.iv_moon)
    ImageView ivMoon;
    @BindView(R.id.tv_after)
    TextView tvAfter;
    @BindView(R.id.ll_afternoon)
    LinearLayout llAfternoon;
    @BindView(R.id.view_morning)
    View viewMorning;
    @BindView(R.id.view_after)
    View viewAfter;
    @BindView(R.id.nsgv_barber)
    NoSlidingGridView nsgvBarber;
    @BindView(R.id.e_list)
    NoScrollExpandableListView eList;
    @BindView(R.id.ll_huli_list)
    LinearLayout llHuliList;
    @BindView(R.id.et_scanner)
    EditText etScanner;
    @BindView(R.id.word_num)
    TextView wordNum;
    @BindView(R.id.tv_sum_service)
    TextView tvSumService;
    @BindView(R.id.tv_how_much)
    TextView tvHowMuch;
    @BindView(R.id.tb_regist)
    Button tbRegist;
    @BindView(R.id.activity_reserva_barber)
    LinearLayout activityReservaBarber;

    private BarberGridAdapter gridAdapter;
    List<String> afterTime = Arrays.asList(
            "17:30", "18:00", "18:30", "19:00", "19:30",
            "20:00", "20:30", "21:00"
    );
    List<String> morningTime = Arrays.asList(
            "10:00", "10:30", "11:00", "11:30", "12:00",
            "14:00", "14:30", "17:00"
    );
    private BarberListBean.BarberlistBean barberlistBean;
    private String address;

    @Override
    public PreOrderBarberPresenter createPresenter() {
        return new PreOrderBarberPresenter();
    }

    @Override
    public PreOderBarberModel createModel() {
        return new PreOrderBarberModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_reserva_barber);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        barberlistBean = (BarberListBean.BarberlistBean) getIntent().getSerializableExtra("barberlistBean");
        address = getIntent().getStringExtra("address");
        getPresenter().getData(barberlistBean.getId());//预约信息数据加载
        //初始化金额和服务数量
        tvSumService.setText("共"+0+"项服务 合计：");
        tvHowMuch.setText("￥"+0);
        initBarberInfo();
        //设置上午默认被点击
        initgride();
        llMorning.performClick();
        initTitle();
        initRecycler();
        setListener();
        //备注字数监听
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
                if (s.length() > MAX_NUM) {
                    s.delete(MAX_NUM, s.length());
                }
                wordNum.setText(String.valueOf(s.length())+"/"+MAX_NUM);
            }
        };
        etScanner.addTextChangedListener(watcher);

    }

    private void setListener() {
        eList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        eList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    @Override
    public void getBarberInfo(PreOrderBean preOrderBean) {
        if (preOrderBean != null) {
            //理发类型数据
            final List<PreOrderBean.ItemlistBeanX> itemlist = preOrderBean.getItemlist();
            ExpandableListAdapter adapter = new ExpandableListAdapter(this, itemlist);
            eList.setAdapter(adapter);
            adapter.setListener(new ExpandableListAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener() {
                    int sum=0;
                    int count=0;
                    for (int i = 0; i < itemlist.size(); i++) {
                        PreOrderBean.ItemlistBeanX itemlistBeanX = itemlist.get(i);
                        List<PreOrderBean.ItemlistBeanX.ItemlistBean> itemlist1 = itemlistBeanX.getItemlist();
                        for (int j = 0; j < itemlist1.size(); j++) {
                            if(itemlist1.get(j).isSelect()){
                                sum+=itemlist1.get(j).getPrice();
                                count++;
                            }
                        }
                    }
                    //设置支付总额,服务数量
                    tvSumService.setText("共"+count+"项服务 合计：");
                    tvHowMuch.setText("￥"+sum);
                }
            });

            List<PreOrderBean.TimelistBean> timelist = preOrderBean.getTimelist();
            for (int i = 0; i < timelist.size(); i++) {
                PreOrderBean.TimelistBean timelistBean = timelist.get(i);
                List<PreOrderBean.TimelistBean.SchedulelistBean> schedulelist = timelistBean.getSchedulelist();
                String starttime = timelistBean.getStarttime();
                String endtime = timelistBean.getEndtime();
                int typeid = timelistBean.getTypeid();
                if(typeid==1){
                    tvSun.setText(starttime+"-"+endtime);
                    gridAdapter.setData(schedulelist);
                }else{
                    tvAfter.setText(starttime+"-"+endtime);
                    gridAdapter.setData(schedulelist);
                }
            }
        }else{
            showToast("PreOrderBean这个bean为空");
        }
    }
    //初始化理发师信息
    private void initBarberInfo() {
        tvBarberName.setText(barberlistBean.getName());
        tvBarberChengwei.setText(barberlistBean.getPositionName());
        tvShopName.setText(address);
    }

    //预约时间
    private void initgride() {
        gridAdapter = new BarberGridAdapter();
        nsgvBarber.setAdapter(gridAdapter);
    }
    //顶部日期
    private void initRecycler() {
       /* recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        BarberRvAdapter rvAdapter = new BarberRvAdapter();
        recyclerView.setAdapter(rvAdapter);*/

        OrderTimeAdapter orderTimeAdapter = new OrderTimeAdapter(this);
        recyclerView.setAdapter(orderTimeAdapter);
        List<String> week = DateUtils.get7week();
        List<String> date = DateUtils.get7date();
        orderTimeAdapter.setData(week,date);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发型师");
    }

    @OnClick({R.id.ll_morning, R.id.ll_afternoon, R.id.tb_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_morning:
//                ivMoon.setImageResource();
//                ivSun.setImageResource();
                tvAfter.setTextColor(Color.parseColor("#000000"));
                tvSun.setTextColor(Color.parseColor("#0000aa"));
                viewMorning.setBackgroundColor(Color.parseColor("#0000aa"));
                viewAfter.setBackgroundColor(Color.parseColor("#33000000"));
                //gridAdapter.setTime(morningTime);
                break;
            case R.id.ll_afternoon:
                //                ivMoon.setImageResource();
//                ivSun.setImageResource();
                tvAfter.setTextColor(Color.parseColor("#0000aa"));
                tvSun.setTextColor(Color.parseColor("#000000"));
                viewMorning.setBackgroundColor(Color.parseColor("#33000000"));
                viewAfter.setBackgroundColor(Color.parseColor("#0000aa"));
                //gridAdapter.setTime(afterTime);
                break;
            case R.id.tb_regist:
                Intent intent = new Intent(this, PayFiveJiaoActivity.class);
                // TODO: 2017/8/22 0022 携带支付信息
                startActivity(intent);
                break;
        }
    }

}
