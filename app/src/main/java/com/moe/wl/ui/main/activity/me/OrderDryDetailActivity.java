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
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.DryCleaners.DryCleanersActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.adapter.OrderDryClearDetailAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderDryClearDetailBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：洗衣店订单详情页
 * 作者：Shixhe On 2017/10/10 0010
 */
public class OrderDryDetailActivity extends MyBaseActivity {

    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.order_type)
    TextView orderType;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.service_state)
    TextView serviceState;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.point1)
    View point1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.point2)
    View point2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.point3)
    View point3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.point4)
    View point4;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.point5)
    View point5;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.point6)
    View point6;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;

    private OrderDryClearDetailBean data;
    private OrderDryClearDetailAdapter adapter;

    private CustomerDialog progressDialog;
    private int orderID; // 订单类型分类
    private int state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dry_detail);
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
            ToastUtil.showToast(this, "洗衣订单数据异常！");
            return;
        }
        if (data.getDetail() != null && data.getDetail().getClothesList() != null) {
            adapter = new OrderDryClearDetailAdapter(this, data.getDetail().getClothesList());
            listView.setAdapter(adapter);
        }
        price.setText("¥" + data.getDetail().getTotalprice());
        orderId.setText("订  单  号：" + data.getDetail().getOrdercode());
        orderTime.setText("下单时间：" + data.getDetail().getCreatetime());
        orderType.setText("支付状态：" + (data.getDetail().getPayStatus() == 0 ? "未支付" : "已支付"));

        right.setVisibility(View.VISIBLE);
        state = data.getDetail().getStatus();
        switch (state) {
            case 1: // 1: 已预约
                serviceState.setText("服务状态：已下单");
                right.setText("取消预订");
                break;
            case 2: // 2: 配送中
                serviceState.setText("服务状态：服务中");
                right.setText("拨打电话");
                break;
            case 3: // 3：已完成
                serviceState.setText("服务状态：已完成");
                right.setText("再次预订");
                break;
            case 4: // 4：待评价
                serviceState.setText("服务状态：待评价");
                right.setText("立即评价");
                break;
            case 5: // 5：已取消
                serviceState.setText("服务状态：已取消");
                right.setText("删除订单");
                break;
        }

        switch (data.getDetail().getLogStatus()) {
            case 7:
                view7.setBackgroundColor(getResources().getColor(R.color.blue));
                point6.setBackgroundColor(getResources().getColor(R.color.blue));
                text6.setTextColor(getResources().getColor(R.color.blue));
            case 6:
                view6.setBackgroundColor(getResources().getColor(R.color.blue));
                point6.setBackgroundColor(getResources().getColor(R.color.blue));
                text6.setTextColor(getResources().getColor(R.color.blue));
            case 5:
                view5.setBackgroundColor(getResources().getColor(R.color.blue));
                point5.setBackgroundColor(getResources().getColor(R.color.blue));
                text5.setTextColor(getResources().getColor(R.color.blue));
            case 4:
                view4.setBackgroundColor(getResources().getColor(R.color.blue));
                point4.setBackgroundColor(getResources().getColor(R.color.blue));
                text4.setTextColor(getResources().getColor(R.color.blue));
            case 3:
                view3.setBackgroundColor(getResources().getColor(R.color.blue));
                point3.setBackgroundColor(getResources().getColor(R.color.blue));
                text3.setTextColor(getResources().getColor(R.color.blue));
            case 2:
                view2.setBackgroundColor(getResources().getColor(R.color.blue));
                point2.setBackgroundColor(getResources().getColor(R.color.blue));
                text2.setTextColor(getResources().getColor(R.color.blue));
            case 1:
                view1.setBackgroundColor(getResources().getColor(R.color.blue));
                point1.setBackgroundColor(getResources().getColor(R.color.blue));
                text1.setTextColor(getResources().getColor(R.color.blue));
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right:
                switch (state) {
                    case 1:
                        showAlertDialog("是否取消预订", state);
                        break;
                    case 2:
                        showAlertDialog("是否拨打电话", state);
                        break;
                    case 3:
                        startActivity(new Intent(OrderDryDetailActivity.this, DryCleanersActivity.class));
                        break;
                    case 4:
                        OtherUtils.gotoComment(OrderDryDetailActivity.this, data.getDetail().getId(), Constants.DRYCLEANER);
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
                            Intent intent = new Intent(OrderDryDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.DRYCLEANER);
                            intent.putExtra("OrderingID", data.getDetail().getId());
                            startActivity(intent);
                        } else if (state == 2) {
                            // TODO 联系客服
                            String mobile = data.getDetail().getServiceMobile();
                            CallPhoneUtils.callPhone(mobile, OrderDryDetailActivity.this);
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
        Observable observable = RetrofitUtils.getInstance().orderDryDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderDryClearDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderDryClearDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.DRYCLEANER, orderID);
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
                    ToastUtil.showToast(OrderDryDetailActivity.this, "删除成功！");
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
