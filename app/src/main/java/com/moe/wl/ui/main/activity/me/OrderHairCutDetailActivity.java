package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.CircleImageView;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderHairCutBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：理发订单详情页
 * 作者：Shixhe On 2017/10/11 0011
 */
public class OrderHairCutDetailActivity extends AppCompatActivity {

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
    @BindView(R.id.order_state)
    TextView orderState;
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.address)
    TextView address;

    private CustomerDialog progressDialog;
    private OrderHairCutBean.OrderlistEntity data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_haircut_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        data = (OrderHairCutBean.OrderlistEntity) getIntent().getSerializableExtra("Data");
        if (data == null) {
            ToastUtil.showToast(this, "理发订单数据错误！");
            finish();
        }

        orderNumber.setText("订单号：" + data.getOrdercode());
        orderTime.setText("下单时间：" + data.getCreatetime());
        time.setText("预约时间：" + data.getInvitetime());
        orderState.setText("支付状态：" + (data.getPaystatus() == 0 ? "未支付" : "已支付"));
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, data.getPhoto(), image);
        name.setText(data.getRealname());
        ratingBar.setRating(data.getScore());
        score.setText("" + data.getScore());
        address.setText("地址：" + data.getAddr());
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

    @OnClick({R.id.cancel_order, R.id.comment, R.id.delete_order, R.id.again_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_order: // 取消订单按钮
                showAlertDialog("是否取消订单", data.getStatus());
                break;

            case R.id.comment: // TODO 评论按钮

                break;

            case R.id.delete_order: // 删除订单按钮
                showAlertDialog("是否删除订单", data.getStatus());
                break;

            case R.id.again_order: // 再次预订按钮
                showAlertDialog("是否再次预订", data.getStatus());
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
                            Intent intent = new Intent(OrderHairCutDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.HAIRCUTS);
                            intent.putExtra("OrderingID", data.getOrderid());
                            startActivity(intent);
                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            deleteExpertsOrder(data.getOrderid());
                        }
                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
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
                    ToastUtil.showToast(OrderHairCutDetailActivity.this, "删除成功！");
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
