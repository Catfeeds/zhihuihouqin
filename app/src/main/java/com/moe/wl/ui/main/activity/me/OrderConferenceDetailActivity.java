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
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.activity.ordering.OrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderConferenceDetailBean;
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
public class OrderConferenceDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;
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
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.time_start)
    TextView timeStart;
    @BindView(R.id.time_end)
    TextView timeEnd;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.people)
    TextView people;
    @BindView(R.id.content)
    TextView content;
    private OrderConferenceDetailBean data;

    private CustomerDialog progressDialog;
    private int orderID; // 订单类型分类
    private int state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_conference_detail);
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
            ToastUtil.showToast(this, "会议室订单数据异常！");
            return;
        }
        state = data.getDetail().getStatus();

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
                        startActivity(new Intent(OrderConferenceDetailActivity.this, OrderingActivity.class));
                        break;
                    case 4:
                        OtherUtils.gotoComment(OrderConferenceDetailActivity.this, data.getDetail().getId(), Constants.CONFERENCE);
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
                            Intent intent = new Intent(OrderConferenceDetailActivity.this, CancelOrderingActivity.class);
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
        Observable observable = RetrofitUtils.getInstance().orderConferenceDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderConferenceDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderConferenceDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.CONFERENCE, orderID);
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
                    ToastUtil.showToast(OrderConferenceDetailActivity.this, "删除成功！");
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
