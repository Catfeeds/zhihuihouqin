package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderWaterDetailBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：订水订单详情页
 * 作者：Shixhe On 2017/10/10 0010
 */
public class OrderWaterDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.group_name)
    TextView groupName;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.practice_price)
    TextView practicePrice;
    @BindView(R.id.time)
    TextView time; // 期望时间
    @BindView(R.id.name)
    TextView name; // 接收人姓名 和 电话
    @BindView(R.id.address)
    TextView address; // 地址
    @BindView(R.id.delivery)
    TextView delivery; // 配送信息
    @BindView(R.id.order_id)
    TextView orderId;  // 订单号
    @BindView(R.id.order_time)
    TextView orderTime; // 下单时间
    @BindView(R.id.order_type)
    TextView orderType;
    @BindView(R.id.cancel_order)
    TextView cancelOrder;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.delete_order)
    TextView deleteOrder;
    @BindView(R.id.again_order)
    TextView againOrder;

    private OrderWaterDetailBean data;

    private CustomerDialog progressDialog;
    private int state; // 订单类型分类
    private int orderID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_water_detail);
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

    @OnClick({R.id.cancel_order, R.id.comment, R.id.delete_order, R.id.again_order, R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_order: // 取消订单按钮
                showAlertDialog("是否取消订单", state);
                break;

            case R.id.comment: // TODO 评论按钮

                break;

            case R.id.delete_order: // 删除订单按钮
                showAlertDialog("是否删除订单", state);
                break;

            case R.id.again_order: // 再次预订按钮
                showAlertDialog("是否再次预订", state);
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
                            Intent intent = new Intent(OrderWaterDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.ORDERWATER);
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
                            CallPhoneUtils.callPhone(mobile, OrderWaterDetailActivity.this);
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
        Observable observable = RetrofitUtils.getInstance().orderWaterDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderWaterDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderWaterDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    // 设置页面数据展示
    private void setUI() {
        if (data == null) {
            ToastUtil.showToast(this, "订水订单数据异常！");
            return;
        }
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, data.getDetail().getImg(), image);
        number.setText("x" + data.getDetail().getCount());
        itemName.setText(data.getDetail().getName());
        price.setText("¥" + data.getDetail().getPrice());
        allPrice.setText("总计：¥" + data.getDetail().getTotalprice());
        practicePrice.setText("¥" + data.getDetail().getTotalprice());
        time.setText("期望时间：" + data.getDetail().getSendTime());
        name.setText("送货地址：" + data.getDetail().getReceiverName() + "  " + data.getDetail().getReceiverMobile());
        address.setText("送至：" + data.getDetail().getReceiverAddress());
        orderId.setText("订单号：" + data.getDetail().getOrdercode());
        orderTime.setText("订单时间：" + data.getDetail().getCreatetime());
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

        state = data.getDetail().getStatus();
        LogUtils.d("订水订单state : " + state);
        switch (state) {
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

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteWaterOrder(orderID);
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
                    ToastUtil.showToast(OrderWaterDetailActivity.this, "删除成功！");
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
