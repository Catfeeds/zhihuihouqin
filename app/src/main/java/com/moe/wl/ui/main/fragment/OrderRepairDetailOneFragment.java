package com.moe.wl.ui.main.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderRepairsDetailOneBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：净菜订单详情
 * 作者：Shixhe On 2017/10/11 0011
 */

public class OrderRepairDetailOneFragment extends BaseFragment2 {

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
    TextView states;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.service_type)
    TextView serviceTypes;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.order_state)
    TextView orderState;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.service_content)
    TextView serviceContent;
    Unbinder unbinder;

    private OrderRepairsDetailOneBean data;

    private int serviceType = 6;
    private int state;

    @Override
    public View setLayout() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_repair_detail_one, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        getData(getArguments().getInt("OrderID"));
    }

    private void setUI() {
        if (data == null) {
            ToastUtil.showToast(getActivity(), "报修订单数据错误！");
            getActivity().finish();
        }
        orderNumber.setText("订单号：" + data.getDetail().getOrdercode());
        orderTime.setText("下单时间：" + data.getDetail().getCreatetime());
        time.setText("预约时间：" + data.getDetail().getInvitetime());
        orderState.setText("支付状态：" + (data.getDetail().getPaystatus() == 0 ? "未支付" : "已支付"));
        GlideLoading.getInstance().loadImgUrlNyImgLoader(getActivity(), data.getDetail().getMenderphoto(), image);
        name.setText(data.getDetail().getMendername());
        ratingBar.setRating(data.getDetail().getScore());
        score.setText("" + data.getDetail().getScore());
        state = data.getDetail().getOrderstatus();
        switch (state) {
            case 1: // 1: 待接单
                states.setText("待接单");
                cancelOrder.setVisibility(View.VISIBLE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 2: // 2：已接单
                states.setText("已接单");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
                break;
            case 3: // 3：已完成
                states.setText("已完成");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
                break;
            case 4: // 4：待评价
                states.setText("待评价");
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.VISIBLE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 5: // 5：已取消
                states.setText("已取消");
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
                showAlertDialog("是否取消订单");
                break;

            case R.id.comment: // TODO 评论按钮

                break;

            case R.id.delete_order: // 删除订单按钮
                showAlertDialog("是否删除订单");
                break;

            case R.id.again_order: // 再次预订按钮
                showAlertDialog("是否拨打电话");
                break;
        }
    }

    private void showAlertDialog(String s) {
        new AlertDialog(getActivity()).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (state == 1) {
                            Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.VEGETABLE);
                            intent.putExtra("ServiceType", serviceType);
                            intent.putExtra("OrderingID", data.getDetail().getOrderid());
                            startActivity(intent);
                        } else if (state == 2) {
                            // 联系客服
                            String mobile = data.getDetail().getMendermobile();
                            CallPhoneUtils.callPhone(mobile, getActivity());
                        } else if (state == 3 || state == 4) {
                            // TODO 评论

                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(data.getDetail().getOrderid());
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
        Observable observable = RetrofitUtils.getInstance().orderRepairsDetailOne(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderRepairsDetailOneBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderRepairsDetailOneBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteRepairsOrder(orderID);
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
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
