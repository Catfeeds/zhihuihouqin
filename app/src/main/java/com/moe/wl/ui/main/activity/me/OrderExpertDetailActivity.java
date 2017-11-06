package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.orderWater.orderWaterServiceActivity;
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

import static com.moe.wl.R.id.visits_num;

/**
 * 类描述：订水订单详情页
 * 作者：Shixhe On 2017/10/10 0010
 */
public class OrderExpertDetailActivity extends MyBaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;
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
    @BindView(visits_num)
    TextView visitsNum;
    @BindView(R.id.hospital)
    TextView hospital;

    private OrderExpertsDetailBean.DetailEntity data;
    private OrderMedicalDetailBean.DetailEntity bean;

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

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right:
                switch (states) {
                    case 1:
                        showAlertDialog("是否取消订单", states);
                        break;
                    case 2:
//                        showAlertDialog("是否拨打电话", states);
                        break;
                    case 3:
                        if (type == 1) {
                            startActivity(new Intent(OrderExpertDetailActivity.this, orderWaterServiceActivity.class));
                        } else {
                            startActivity(new Intent(OrderExpertDetailActivity.this, orderWaterServiceActivity.class));
                        }
                        break;
                    case 4:
                        OtherUtils.gotoComment(OrderExpertDetailActivity.this, orderID, Constants.ORDERWATER);
                        break;
                    case 5:
                        showAlertDialog("是否删除订单", states);
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
                            Intent intent = new Intent(OrderExpertDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.EXPERTS);
                            if (type == 1) {
                                intent.putExtra("OrderingID", bean.getId());
                            } else {
                                intent.putExtra("OrderingID", data.getId());
                            }
                            startActivity(intent);
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
        orderState.setText("支付状态：" + (bean.getPaystatus() == 0 ? "未支付" : "已支付"));
        if (bean.getDoctor() != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getDoctor().getPhoto(), image, R.mipmap.ic_default_square);
            name.setText(bean.getDoctor().getRealname());
            docPosition.setText(bean.getDoctor().getPositionname());
            ratingBar.setRating(bean.getDoctor().getScore());
            score.setText("" + bean.getDoctor().getScore() + "分");
            visitsNum.setText("问诊量：" + bean.getDoctor().getConsultcount());
            hospital.setText("所属医院：" + bean.getDoctor().getHospitalname());
        }
        right.setVisibility(View.VISIBLE);

        states = data.getStatus();
        switch (states) {
            case 1: // 1: 已预约
                right.setText("取消订单");
                break;
            case 2: // 2: 配送中
                right.setText("已完成");
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

    // 设置页面
    private void setUI() {
        orderNumber.setText("订单号：" + data.getOrdercode());
        orderTime.setText("下单时间：" + data.getCreatetime());
        time.setText("预约时间：" + data.getScheduledate());
        orderState.setText("支付状态：未支付"/* + (data.get == 0 ? "未支付" : "已支付")*/);
        if (data.getDoctor() != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, data.getDoctor().getPhoto(), image, R.mipmap.ic_default_square);
            name.setText(data.getDoctor().getRealname());
            docPosition.setText(data.getDoctor().getPositionname());
            ratingBar.setRating(data.getDoctor().getScore());
            OtherUtils.ratingBarColor(ratingBar, this);
            score.setText("" + data.getDoctor().getScore() + "分");
            visitsNum.setText("问诊量：" + data.getDoctor().getConsultcount());
            hospital.setText("所属医院：" + data.getDoctor().getHospitalName());
        }
        right.setVisibility(View.VISIBLE);
        states = data.getStatus();
        switch (states) {
            case 1: // 1: 已预约
                right.setText("取消订单");
                break;
            case 2: // 2: 配送中
                right.setText("已完成");
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
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.MEDICAL, orderID);
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
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.EXPERTS, orderID);
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
