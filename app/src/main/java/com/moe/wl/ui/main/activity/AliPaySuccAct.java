package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.moe.wl.framework.contant.Constants.orderRepairs;

public class AliPaySuccAct extends MyBaseActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.how_much)
    TextView howMuch;
    @BindView(R.id.tv_payType)
    TextView tvPayType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.tv_ordercode)
    TextView tvOrdercode;
    @BindView(R.id.tv_bt)
    TextView tvBt;
    private int ordertype;
    private String createtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_pay_succ);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        title.setTitle("支付成功");
        title.setBack(true);
        /*intent.putExtra("ordertype",ordertype1);
                    intent.putExtra("createtime",createtime);
                    intent.putExtra("paytype",paytype);
                    intent.putExtra("ordercode",ordercode);
                    intent.putExtra("money",pay);*/
        Intent intent = getIntent();
        //订单类型serviceType服务类型 ： 1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7会议室预定，8：办公用品，
        // 9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊 15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务
        ordertype = intent.getIntExtra("ordertype", -1);
        createtime = intent.getStringExtra("createtime");
        int paytype = intent.getIntExtra("paytype", 0);
        String ordercode = intent.getStringExtra("ordercode");
        double money = intent.getDoubleExtra("money", 0);
        LogUtils.i("ordertype" + ordertype + "createtime" + createtime + "paytype" + paytype + "ordercode" + ordercode + "money" + money);
        howMuch.setText("￥" + money);
        // TODO: 2017/10/26 订单类型需要增加
        if (ordertype == Constants.HAIRCUTS) {//理发
            tvPayType.setText("理发服务");
        } else if (ordertype == Constants.MEALSPAY) {
            tvPayType.setText("餐费充值");
        } else if (ordertype == Constants.OFFICESUPPLIES) {//办公
            tvPayType.setText("办公用品");
        } else if (ordertype == Constants.ORDERWATER) {//定水服务
            tvPayType.setText("订水服务");
        } else if(ordertype==Constants.VEGETABLE){//净菜预定
            tvPayType.setText("净菜预定");
        }
        tvTime.setText(createtime);
        tvOrdercode.setText(ordercode);
        if (paytype == 1) {//支付宝支付
            tvPayWay.setText("支付宝");
        } else if (paytype == 2) {
            tvPayWay.setText("微信");
        } else if (paytype == 3) {
            tvPayWay.setText("钱包支付");
        } else if (paytype == 4) {
            tvPayWay.setText("集体账户支付");
        } else if (paytype == 5) {
            tvPayWay.setText("代金券支付");
        }

    }

    @OnClick(R.id.tv_bt)
    public void onClick() {
        switch (ordertype) {
            case Constants.PROPERRY:// 物业维修
                goServiceActivity(0, Constants.PROPERRY, Constants.orderRepairs);
                break;

            case Constants.OFFICESUPPLIES:// 办公用品
                goServiceActivity(0, Constants.OFFICESUPPLIES, Constants.orderOfficeSupplies);
                break;

            case Constants.ORDERMEAL:// 订餐订单
                goServiceActivity(0, Constants.ORDERMEAL, Constants.orderFood);
                break;

            case Constants.HAIRCUTS:// 理发订单
                goServiceActivity(0, Constants.HAIRCUTS, Constants.orderHaircuts);
                break;

            case Constants.ORDERWATER:// 订水订单
                goServiceActivity(0, Constants.ORDERWATER, Constants.orderWater);
                break;

            case Constants.MEDICAL:// 医疗订单
                goServiceActivity(0, Constants.MEDICAL, Constants.orderMedical);
                break;

            case Constants.EXPERTS:// 专家坐诊
                goServiceActivity(0, Constants.EXPERTS, Constants.orderExperts);
                break;

            case Constants.DRYCLEANER://洗衣店
                goServiceActivity(0, Constants.DRYCLEANER, Constants.orderDryCleaner);
                break;

            case Constants.BOOK: // 图书馆
                goServiceActivity(0, Constants.BOOK, Constants.orderBook);
                break;

            case Constants.VEGETABLE: // 净菜
                goServiceActivity(0, Constants.VEGETABLE, Constants.orderVegetable);
                break;

            case Constants.CONFERENCE: // 会议室
                goServiceActivity(0, Constants.CONFERENCE, Constants.orderConference);
                break;

            default:
                goServiceActivity(0, Constants.PROPERRY, Constants.orderRepairs);
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
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("from", from);
        intent.putExtra("state", state);
        startActivity(intent);
    }

}
