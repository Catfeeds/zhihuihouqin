package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.activity.ordering.OrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderMealDetailBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：订餐订单详情页面
 * 作者：Shixhe On 2017/10/12 0012
 */
public class OrderMealDetailActivity extends MyBaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;
    //    @BindView(R.id.group_name)
    //    TextView groupName;
//    @BindView(R.id.item_name)
//    TextView itemName;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.fix)
    TextView fix;
    @BindView(R.id.item_name)
    TextView itemName;
    private OrderMealDetailBean data;

    private CustomerDialog progressDialog;
    private int orderID; // 订单类型分类
    private int state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_meal_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        titleBar.setTitle("订单详情");
        titleBar.setBack(true);
        orderID = getIntent().getIntExtra("OrderID", 0);
        getData(orderID);
    }

    // 设置页面
    private void setUI() {
        if (data == null) {
            ToastUtil.showToast(this, "净菜订单数据异常！");
            return;
        }
        number.setText("x" + data.getDetail().getCount());
        itemName.setText("工作餐");
        price.setText("¥" + data.getDetail().getTotalprice());
        time.setText("送货时间：" + data.getDetail().getSendTime());
        orderId.setText("订单号：" + data.getDetail().getOrdercode());
        orderTime.setText("下单时间：" + data.getDetail().getCreatetime());
        address.setText("送货地点：" + data.getDetail().getAddress());
        if (data.getDetail().getType() == 2) {
            fix.setVisibility(View.VISIBLE);
        }
        /*String pay = "";
        switch (data.getDetail().getPayStatus()) { // 1：支付宝，2：微信，3：个人钱包，4：集体账户，5：个人代金券（理发）
            case 1:
                pay = "支付宝";
                break;
            case 2:
                pay = "微信";
                break;
            case 3:
                pay = "个人钱包";
                break;
            case 4:
                pay = "集体账户";
                break;
            case 5:
                pay = "个人代金券";
                break;
        }
        orderType.setText("支付方式：" + pay);*/
        state = data.getDetail().getStatus();
        switch (state) {
            case 1: // 1: 已预约
                right.setText("取消订单");
                break;
            case 2: // 2: 配送中
//                right.setText("已完成");
                break;
            case 3: // 3：已完成
                right.setText("再次预订");
                break;
            case 4: // 4：待评价
                right.setText("立即评价");
                break;
            case 5: // 5：已取消
                right.setText("删除订单");
                break;
        }
    }

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right:
                switch (state) {
                    case 1:
                        showAlertDialog("是否取消预约", state);
                        break;
                    case 2:
//                        showAlertDialog("是否拨打电话", state);
                        break;
                    case 3:
                        startActivity(new Intent(OrderMealDetailActivity.this, OrderingActivity.class));
                        break;
                    case 4:
                        OtherUtils.gotoComment(OrderMealDetailActivity.this, data.getDetail().getId(), Constants.ORDERMEAL);
                        break;
                    case 5:
                        showAlertDialog("是否删除订单", state);
                        break;
                }
                break;

            case R.id.left:
                // TODO 在线沟通
                break;

        }
    }

    private void showAlertDialog(String s, final int state) {
        new AlertDialog(this).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (state == 1) {
                            Intent intent = new Intent(OrderMealDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.ORDERMEAL);
                            intent.putExtra("OrderingID", data.getDetail().getId());
                            startActivity(intent);
                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(data.getDetail().getId());
                        }
                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    // 获取订单详情
    private void getData(int orderID) {
        Observable observable = RetrofitUtils.getInstance().orderMealDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderMealDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderMealDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.ORDERMEAL, orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    ToastUtil.showToast(OrderMealDetailActivity.this, "删除成功！");
                    finish();
                }
            }
        });
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog() {
        progressDialog.show();
    }

}
