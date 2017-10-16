package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderVegetableDetailBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

import static android.R.attr.type;

/**
 * 类描述：净菜订单详情页
 * 作者：Shixhe On 2017/10/10 0010
 */
public class OrderVegetableDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.cancel_order)
    TextView cancelOrder;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.delete_order)
    TextView deleteOrder;
    @BindView(R.id.again_order)
    TextView againOrder;
    @BindView(R.id.group_name)
    TextView groupName;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.practice_price)
    TextView practicePrice;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.order_type)
    TextView orderType;
    @BindView(R.id.time)
    TextView time;


    private OrderVegetableDetailBean data;

    private int serviceType = 18;
    private CustomerDialog progressDialog;
    private int orderID; // 订单类型分类

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_vegetable_detail);
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
        itemName.setText(data.getDetail().getFoodName());
        price.setText("¥" + data.getDetail().getTotalprice());
        allPrice.setText("总计：¥" + data.getDetail().getTotalprice());
        practicePrice.setText("¥" + data.getDetail().getTotalprice());
        time.setText("取货时间：" + data.getDetail().getTaketime());
        orderId.setText("订单号：" + data.getDetail().getOrdercode());
        orderTime.setText("下单时间：" + data.getDetail().getCreatetime());
        String pay = "";
        switch (data.getDetail().getPaytype()) { // 1：支付宝，2：微信，3：个人钱包，4：集体账户，5：个人代金券（理发）
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
        orderType.setText("支付方式：" + pay);

        switch (data.getDetail().getStatus()) {
            case 1: // 1: 已预约
                cancelOrder.setVisibility(View.VISIBLE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 3: // 3：已完成
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
                break;
            case 4: // 4：待评价
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.VISIBLE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 5: // 5：已取消
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.VISIBLE);
                againOrder.setVisibility(View.GONE);
                break;
        }
    }

    @OnClick({R.id.cancel_order, R.id.comment, R.id.delete_order, R.id.again_order, R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_order: // 取消订单按钮
                showAlertDialog("是否取消订单", type);
                break;

            case R.id.comment: // TODO 评论按钮

                break;

            case R.id.delete_order: // 删除订单按钮
                showAlertDialog("是否删除订单", type);
                break;

            case R.id.again_order: // 再次预订按钮
                showAlertDialog("是否再次预订", type);
                break;

            case R.id.call: // 拨打电话
                showAlertDialog("是否联系商家", 6);
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
                            Intent intent = new Intent(OrderVegetableDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.VEGETABLE);
                            intent.putExtra("ServiceType", serviceType);
                            intent.putExtra("OrderingID", data.getDetail().getId());
                            startActivity(intent);
                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(data.getDetail().getId());
                        } else if (state == 6) {
                            // 联系客服
                            String mobile = data.getDetail().getServiceMobile();
                            CallPhoneUtils.callPhone(mobile, OrderVegetableDetailActivity.this);
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
        Observable observable = RetrofitUtils.getInstance().orderVegetableDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderVegetableDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderVegetableDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteVegetableOrder(orderID);
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
                    ToastUtil.showToast(OrderVegetableDetailActivity.this, "删除成功！");
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