package com.moe.wl.ui.main.activity;

import android.content.Intent;
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
import com.moe.wl.framework.utils.Arith;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberGridAdapter;
import com.moe.wl.ui.main.adapter.ExpandableListAdapter;
import com.moe.wl.ui.main.adapter.OrderTimeAdapter;
import com.moe.wl.ui.main.bean.BarberlistBean;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

public class NowReservaBarberActivity extends BaseActivity<NowOrderBarberModel, PreOrderBarberView, NowOrderBarberPresenter> implements PreOrderBarberView {
    private static final int MAX_NUM = 100;
    @BindView(R.id.reserve_info_title)
    TitleBar title;
    @BindView(R.id.civ_header)
    CircleImageView civHeader;
    @BindView(R.id.rv_reserva_date)
    NoSlideRecyclerView recyclerView;
    @BindView(R.id.nsgv_barber)
    NoSlidingGridView nsgvBarber;
    @BindView(R.id.e_list)
    NoScrollExpandableListView eList;
    @BindView(R.id.et_mobile)
    EditText etMobile;
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
    private BarberlistBean barberlistBean;
    private String address;
    private int id;
    private String barberid;
    private List<Itemid> list;
    private double sumAll;
    private List<PreOrderBean.TimelistBean> timelist;
    private OrderTimeAdapter orderTimeAdapter;
    private List<PreOrderBean.TimelistBean.SchedulelistBean> schedulelist;

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
        etMobile.setText(SharedPrefHelper.getInstance().getMobile());
        list = new ArrayList<>();
        barberlistBean = (BarberlistBean) getIntent().getSerializableExtra("barberlistBean");
        address = getIntent().getStringExtra("address");
        initgride();
        initTitle();
        initRecycler();
        getPresenter().order();
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
            //理发类型数据
            final List<PreOrderBean.ItemlistBeanX> itemlist = preOrderBean.getItemlist();
            ExpandableListAdapter adapter = new ExpandableListAdapter(this, itemlist);
            eList.setAdapter(adapter);
            adapter.setListener(new ExpandableListAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener() {
                    list.clear();
                    double sum = 0;
                    int count = 0;
                    for (int i = 0; i < itemlist.size(); i++) {
                        PreOrderBean.ItemlistBeanX itemlistBeanX = itemlist.get(i);
                        List<PreOrderBean.ItemlistBeanX.ItemlistBean> itemlist1 = itemlistBeanX.getItemlist();
                        for (int j = 0; j < itemlist1.size(); j++) {
                            if (itemlist1.get(j).isSelect()) {
                                sum = Arith.add(sum, itemlist1.get(j).getPrice());
                                int id = itemlist1.get(j).getId();
                                list.add(new Itemid(id));
                                count++;
                            }
                        }
                    }
                    //设置支付总额,服务数量
                    BigDecimal bg = new BigDecimal(sum);
                    sumAll = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvSumService.setText("共" + count + "项服务 合计：");
                    tvHowMuch.setText("￥" + sum);
                }
            });
            timelist = preOrderBean.getTimelist();
            orderTimeAdapter.setData(timelist);
            orderTimeAdapter.setListener(new OrderTimeAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    PreOrderBean.TimelistBean timelistBean = timelist.get(position);
                    schedulelist = timelistBean.getSchedulelist();
                    gridAdapter.setData(schedulelist);

                }
            });
            gridAdapter.setListener(new BarberGridAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    PreOrderBean.TimelistBean.SchedulelistBean schedulelistBean = schedulelist.get(position);
                    int status = schedulelistBean.getStatus();
                    if (status == 1) {//已经有预约了
                        showToast("该时间段已经被预约");
                        return;
                    } else if (status == 0) {//没有预约
                        id = schedulelistBean.getId();
                        barberid = schedulelistBean.getBarberid();
                    }
                }
            });
    }

    @Override//下单成功
    public void createOrederResult(CreateorderBean bean) {
        if (bean != null) {
            int orderid = bean.getOrderid();
            int ordertype = bean.getOrdertype();
            Intent intent = new Intent(this, PayFiveJiaoActivity.class);
            intent.putExtra("from", Constants.BARBER);
            intent.putExtra("pay", sumAll);
            intent.putExtra("orderid", orderid + "");
            intent.putExtra("ordercode", "");
            intent.putExtra("ordertype", ordertype + "");
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
        orderTimeAdapter = new OrderTimeAdapter(this);
        recyclerView.setAdapter(orderTimeAdapter);

    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("发型师");
    }

    @OnClick({R.id.tb_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tb_regist:
                String mobile = etMobile.getText().toString().trim();
                String remark = etScanner.getText().toString().trim();
                String money = tvHowMuch.getText().toString().trim();
                String price = money.replaceAll("￥", "");
                if (StringUtil.isNullOrEmpty(mobile)) {
                    showToast("请输入手机号");
                    return;
                }
                if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                float amount = Float.parseFloat(price);
                if (amount <= 0) {
                    showToast("请选择服务项");
                    return;
                }
                Order order = new Order(barberid, mobile, remark, price, id + "");
                getPresenter().createOrder(order, list);//下单

                break;
        }
    }
}
