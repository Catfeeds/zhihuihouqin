package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.Arith;
import com.moe.wl.framework.utils.LogUtils;
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
import com.moe.wl.ui.main.model.PreOderBarberModel;
import com.moe.wl.ui.main.modelimpl.PreOrderBarberModelImpl;
import com.moe.wl.ui.main.presenter.PreOrderBarberPresenter;
import com.moe.wl.ui.main.view.PreOrderBarberView;
import com.moe.wl.ui.mywidget.NoScrollExpandableListView;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

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
    NoSlideRecyclerView recyclerView;
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
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.activity_reserva_barber)
    LinearLayout activityReservaBarber;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;


    private BarberGridAdapter gridAdapter;

    private BarberlistBean barberlistBean;
    private String address;
    private int id;
    private String barberid;
    private List<Itemid> list;
    private double sumAll;
    private OrderTimeAdapter orderTimeAdapter;
    private List<PreOrderBean.TimelistBean> timelist;
    private List<PreOrderBean.TimelistBean.SchedulelistBean> schedulelist;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private boolean isRefresh;

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
        String mobile = SharedPrefHelper.getInstance().getMobile();
        etMobile.setText(mobile);
        list = new ArrayList<>();
        timelist = new ArrayList<>();
        schedulelist = new ArrayList<>();
        barberlistBean = (BarberlistBean) getIntent().getSerializableExtra("barberlistBean");
        barberid = barberlistBean.getId() + "";
        address = getIntent().getStringExtra("address");
        if (barberlistBean != null) {
            getPresenter().getData(barberlistBean.getId());//预约信息数据加载
        }
        Glide.with(this).load(barberlistBean.getPhoto()).into(civBarberHeader);
        Glide.with(this).load(barberlistBean.getPhoto()).into(ivBarberBackground);
        //初始化金额和服务数量
        tvSumService.setText("共" + 0 + "项服务 合计：");
        tvHowMuch.setText("￥" + 0);
        initBarberInfo();
        //设置上午默认被点击
        initgride();
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
                wordNum.setText(String.valueOf(s.length()) + "/" + MAX_NUM);
            }
        };
        etScanner.addTextChangedListener(watcher);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                getPresenter().getData(barberlistBean.getId());
            }
        });

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
            refresh.setRefreshing(false);
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
                        int typeid = itemlistBeanX.getTypeid();//以及衣物类型的id
                        for (int j = 0; j < itemlist1.size(); j++) {
                            if (itemlist1.get(j).isSelect()) {
                                double price = itemlist1.get(j).getPrice();
                                sum = Arith.add(sum,price);//选择项目的总价格
                                count++;
                                int id = itemlist1.get(j).getId();
                                list.add(new Itemid(id,price,typeid));
                            }
                        }
                    }
                    //设置支付总额,服务数量
                    sumAll = sum;
                    tvSumService.setText("共" + count + "项服务 合计：");
                    tvHowMuch.setText("￥" + sum);
                }
            });

            //timelist = preOrderBean.getTimelist();
            if (preOrderBean.getTimelist() != null) {
                if(isRefresh){
                    timelist.clear();
                    isRefresh=false;
                }
                timelist.addAll(preOrderBean.getTimelist());
                orderTimeAdapter.notifyDataSetChanged();
            }
            if (preOrderBean.getTimelist().size() > 0 && preOrderBean.getTimelist().get(0) != null) {
                schedulelist.clear();
                schedulelist.addAll(preOrderBean.getTimelist().get(0).getSchedulelist());
                gridAdapter.setData(schedulelist);
            }
            //orderTimeAdapter.setData(timelist);
            /*orderTimeAdapter.setListener(new OrderTimeAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    PreOrderBean.TimelistBean timelistBean = timelist.get(position);
                    schedulelist = timelistBean.getSchedulelist();
                    gridAdapter.setData(schedulelist);

                }
            });*/
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
//                        barberid = schedulelistBean.getBarberid();
                    }
                }
            });
        } else {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        orderTimeAdapter = new OrderTimeAdapter(this, timelist, new OrderTimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                PreOrderBean.TimelistBean timelistBean = timelist.get(position);
                schedulelist.clear();
                schedulelist.addAll(timelistBean.getSchedulelist());
                gridAdapter.setData(schedulelist);
            }
        });
        recyclerView.setAdapter(orderTimeAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发型师");
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
                double amount = Double.parseDouble(price);
                if (amount <= 0) {
                    showToast("请选择服务项");
                    return;
                }
                Order order = new Order(barberid, mobile, remark, price, id + "");
                getPresenter().createOrder(order, list);//下单
                break;
        }
    }

    @Override
    public void createOrederResult(CreateorderBean bean) {
        LogUtils.i("预约理发师下单" + bean.getMsg() + bean.getErrCode());
        Intent intent = new Intent(this, SubmitSuccessActivity.class);
        intent.putExtra("from", Constants.HAIRCUTS);

//        Intent intent = new Intent(this, PayFiveJiaoActivity.class);
//        int orderid = bean.getOrderid();
//        int ordertype = bean.getOrdertype();
//        intent.putExtra("from", Constants.BARBER);
//        intent.putExtra("pay", sumAll);
//        intent.putExtra("orderid", orderid + "");
//        intent.putExtra("ordercode", bean.getOrdercode());
//        intent.putExtra("ordertype", ordertype + "");
//        intent.putExtra("time", bean.getCreatetime());
        //intent.putExtra("from",Constants.BARBER);
        //intent.putExtra("ordertype",6);//订单类型为理发
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ReservaBarber Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
