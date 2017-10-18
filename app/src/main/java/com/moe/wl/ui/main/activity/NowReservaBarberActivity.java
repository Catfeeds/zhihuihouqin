package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberGridAdapter;
import com.moe.wl.ui.main.adapter.ExpandableListAdapter;
import com.moe.wl.ui.main.adapter.OrderTimeAdapter;
import com.moe.wl.ui.main.bean.BarberListBean;
import com.moe.wl.ui.main.bean.CreateorderBean;
import com.moe.wl.ui.main.bean.Itemid;
import com.moe.wl.ui.main.bean.Order;
import com.moe.wl.ui.main.bean.PreOrderBean;
import com.moe.wl.ui.main.model.NowOrderBarberModel;
import com.moe.wl.ui.main.modelimpl.NowOrderBarberModelImpl;
import com.moe.wl.ui.main.presenter.NowOrderBarberPresenter;
import com.moe.wl.ui.main.view.PreOrderBarberView;
import com.moe.wl.ui.mywidget.NoScrollExpandableListView;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.DateUtils;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

public class NowReservaBarberActivity extends BaseActivity<NowOrderBarberModel, PreOrderBarberView, NowOrderBarberPresenter> implements PreOrderBarberView {
    private static final int MAX_NUM = 100;
    @BindView(R.id.reserve_info_title)
    TitleBar titile;
    @BindView(R.id.civ_header)
    CircleImageView civHeader;
    @BindView(R.id.rv_reserva_date)
    NoSlideRecyclerView recyclerView;
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
    @BindView(R.id.et_mobile)
    EditText etMobile;


    private BarberGridAdapter gridAdapter;

    private BarberListBean.BarberlistBean barberlistBean;
    private String address;
    private int id;
    private String barberid;
    private List<Itemid> list;

    @Override
    public NowOrderBarberPresenter createPresenter() {
        return new NowOrderBarberPresenter();
    }

    @Override
    public NowOrderBarberModel createModel() {
        return new NowOrderBarberModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_now_reserva_barber);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        barberlistBean = (BarberListBean.BarberlistBean) getIntent().getSerializableExtra("barberlistBean");
        address = getIntent().getStringExtra("address");
        getPresenter().order();
        //设置上午默认被点击
        initgride();
        initTitle();
        initRecycler();
        setListener();
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
                wordNum.setText(String.valueOf(s.length()) + "/" + MAX_NUM);
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
                    int sum = 0;
                    int count = 0;
                    for (int i = 0; i < itemlist.size(); i++) {
                        PreOrderBean.ItemlistBeanX itemlistBeanX = itemlist.get(i);
                        List<PreOrderBean.ItemlistBeanX.ItemlistBean> itemlist1 = itemlistBeanX.getItemlist();
                        for (int j = 0; j < itemlist1.size(); j++) {
                            if (itemlist1.get(j).isSelect()) {
                                sum += itemlist1.get(j).getPrice();
                                int id = itemlist1.get(j).getId();
                                list.add(new Itemid(id));
                                count++;
                            }
                        }
                    }
                    //设置支付总额,服务数量
                    tvSumService.setText("共" + count + "项服务 合计：");
                    tvHowMuch.setText("￥" + sum);
                }
            });


            List<PreOrderBean.TimelistBean> timelist = preOrderBean.getTimelist();
            for (int i = 0; i < timelist.size(); i++) {
                PreOrderBean.TimelistBean timelistBean = timelist.get(i);
                final List<PreOrderBean.TimelistBean.SchedulelistBean> schedulelist = timelistBean.getSchedulelist();

                gridAdapter.setData(schedulelist);
                gridAdapter.setListener(new BarberGridAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        PreOrderBean.TimelistBean.SchedulelistBean schedulelistBean = schedulelist.get(position);
                        id = schedulelistBean.getId();
                        barberid = schedulelistBean.getBarberid();
                    }
                });

               /* if (typeid == 1) {
                    tvSun.setText(starttime + "-" + endtime);
                    gridAdapter.setData(schedulelist);
                } else {
                    tvAfter.setText(starttime + "-" + endtime);
                    gridAdapter.setData(schedulelist);
                }*/
            }
//
        } else {
            showToast("PreOrderBean这个bean为空");
        }
    }

    @Override//下单成功
    public void createOrederResult(CreateorderBean bean) {
        if(bean!=null){

            Intent intent = new Intent(this, PayFiveJiaoActivity.class);
            intent.putExtra("from", Constants.BARBER);
            // TODO: 2017/8/22 0022 携带支付信息
            startActivity(intent);
        }
    }


    //预约时间
    private void initgride() {
        gridAdapter = new BarberGridAdapter();
        nsgvBarber.setAdapter(gridAdapter);
    }

    //顶部日期
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        /*BarberRvAdapter rvAdapter = new BarberRvAdapter();
        recyclerView.setAdapter(rvAdapter);*/

        OrderTimeAdapter orderTimeAdapter = new OrderTimeAdapter(this);
        recyclerView.setAdapter(orderTimeAdapter);
        List<String> week = DateUtils.get3week();
        List<String> date = DateUtils.get3date();
        orderTimeAdapter.setData(week, date);
    }

    private void initTitle() {
        titile.setBack(true);
        titile.setTitle("发型师");
    }

    @OnClick({ R.id.tb_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_regist:
                String mobile = etMobile.getText().toString().trim();
                String remark = etScanner.getText().toString().trim();
                String money = tvHowMuch.getText().toString().trim();
                String price=money.replaceAll("￥","");
                if (StringUtil.isNullOrEmpty(mobile)) {
                    showToast("请输入手机号");
                    return ;
                }
                if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
                    showToast("请输入正确的手机号码");
                    return ;
                }

                Order order = new Order(barberid,mobile,remark,price,id+"");
                getPresenter().createOrder(order,list);//下单

                break;
        }
    }

}
