package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderExpertsDetailBean;
import com.moe.wl.ui.main.bean.OrderMedicalDetailBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：订水订单详情页
 * 作者：Shixhe On 2017/10/10 0010
 */
public class OrderExpertDetailActivity extends AppCompatActivity {

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
    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.order_state)
    TextView orderState;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.doc_position)
    TextView docPosition;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.visits_num)
    TextView visitsNum;
    @BindView(R.id.hospital)
    TextView hospital;

    private OrderExpertsDetailBean.DetailEntity data;
    private OrderMedicalDetailBean.DetailEntity bean;

    private int serviceType = 14;
    private CustomerDialog progressDialog;
    private int type; // 订单类型分类
    private int orderID; // 订单ID
    private int states;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_expert_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        type = getIntent().getIntExtra("Type", 0);
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        titleBar.setTitle("订单详情");
        titleBar.setBack(true);
        orderID = getIntent().getIntExtra("OrderID", 0);
        if (type == 1) {
            getMedicalData(orderID);
        } else {
            getExpertData(orderID);
        }
    }

    @OnClick({R.id.cancel_order, R.id.comment, R.id.delete_order, R.id.again_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_order: // 取消订单按钮
                showAlertDialog("是否取消订单", states);
                break;

            case R.id.comment: // TODO 评论按钮

                break;

            case R.id.delete_order: // 删除订单按钮
                showAlertDialog("是否删除订单", states);
                break;

            case R.id.again_order: // 再次预订按钮
                showAlertDialog("是否再次预订", states);
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
                            Intent intent = new Intent(OrderExpertDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.OFFICESUPPLIES);
                            intent.putExtra("ServiceType", serviceType);
                            if (type == 1) {
                                intent.putExtra("OrderingID", bean.getId());
                            } else {
                                intent.putExtra("OrderingID", data.getId());
                            }
                            startActivity(intent);
                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            if (type == 1) {
                                deleteMedicalOrder(bean.getId());
                            } else {
                                deleteExpertsOrder(data.getId());
                            }
                        }
                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    // 设置页面
    private void setUI(OrderMedicalDetailBean.DetailEntity bean) {
        if (bean == null) {
            return;
        }
        this.bean = bean;
        orderNumber.setText("订单号：" + bean.getOrdercode());
        orderTime.setText("下单时间：" + bean.getCreatetime());
        time.setText("预约时间：" + bean.getScheduledate());
//        state.setText("支付状态：" + (data.get == 0 ? "未支付" : "已支付"));
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getPhoto(), image);
        if (bean.getDoctor() != null) {
            name.setText(bean.getDoctor().getRealname());
            docPosition.setText(bean.getDoctor().getPositionname());
            ratingBar.setRating((int) bean.getDoctor().getScore());
            score.setText("" + bean.getDoctor().getScore());
            number.setText("问诊量：" + bean.getDoctor().getConsultcount());
            hospital.setText("所属医院：" + bean.getDoctor().getHospitalname());
        }
        states = bean.getStatus();
        switch (states) {
            case 1: // 1: 已预约
                state.setText("已预约");
                cancelOrder.setVisibility(View.VISIBLE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 2: // 2: 服务中
                state.setText("服务中");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
                break;
            case 3: // 3：已完成
                state.setText("已完成");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
                break;
            case 4: // 4：待评价
                state.setText("待评价");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.VISIBLE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 5: // 5：已取消
                state.setText("已取消");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.VISIBLE);
                againOrder.setVisibility(View.GONE);
                break;
        }
    }

    // 设置页面
    private void setUI() {
        orderNumber.setText("订单号：" + data.getOrdercode());
        orderTime.setText("下单时间：" + data.getCreatetime());
        time.setText("预约时间：" + data.getScheduledate());
//        state.setText("支付状态：" + (data.get == 0 ? "未支付" : "已支付"));
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, data.getPhoto(), image);
        if (data.getDoctor() != null) {
            name.setText(data.getDoctor().getRealname());
            docPosition.setText(data.getDoctor().getPositionname());
            ratingBar.setRating(data.getDoctor().getScore());
            score.setText("" + data.getDoctor().getScore());
            number.setText("问诊量：" + data.getDoctor().getConsultcount());
            hospital.setText("所属医院：" + data.getDoctor().getHospitalName());
        }
        states = data.getStatus();
        switch (data.getStatus()) {
            case 1: // 1: 已预约
                cancelOrder.setVisibility(View.VISIBLE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 2: // 2: 服务中
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
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

    // 获取医疗数据
    private void getMedicalData(int orderID) {
        Observable observable = RetrofitUtils.getInstance().orderMedicalDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderMedicalDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderMedicalDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    setUI(orderBean.getDetail().get(0));
                }
            }
        });
    }

    // 获取专家数据
    private void getExpertData(int orderID) {
        Observable observable = RetrofitUtils.getInstance().orderExpertsDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderExpertsDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderExpertsDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean.getDetail();
                    setUI();
                }
            }
        });
    }

    // 删除医疗订单接口
    private void deleteMedicalOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteMedicalOrder(orderID);
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
                    ToastUtil.showToast(OrderExpertDetailActivity.this, "删除成功！");
                    finish();
                }
            }
        });
    }

    // 删除专家订单接口
    private void deleteExpertsOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteExpertsOrder(orderID);
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
                    ToastUtil.showToast(OrderExpertDetailActivity.this, "删除成功！");
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
