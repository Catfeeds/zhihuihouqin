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
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.BookOrderListBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

import static com.moe.wl.R.id.order_time;

/**
 * 类描述：图书订单详情类
 * 作者：Shixhe On 2017/10/10 0010
 */
public class OrderBookDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.press)
    TextView press;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(order_time)
    TextView orderTime;
    @BindView(R.id.cancel_order)
    TextView cancelOrder;
    @BindView(R.id.comment)
    TextView comment;
    @BindView(R.id.delete_order)
    TextView deleteOrder;
    @BindView(R.id.again_order)
    TextView againOrder;

    private BookOrderListBean.OrderlistBean data;

    private CustomerDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_book_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        titleBar.setTitle("订单详情");
        titleBar.setBack(true);
        data = (BookOrderListBean.OrderlistBean) getIntent().getSerializableExtra("Data");
        if (data == null) {
            ToastUtil.showToast(this, "图书订单数据异常！");
            finish();
        }
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, data.getImg(), image);
        itemName.setText(data.getTitle());
        author.setText("作者：" + data.getAuthor());
        press.setText("出版社：" + data.getPublisher());
        time.setText("取书时间：" + data.getInvitegetbooktime());
        orderId.setText("订单号：" + data.getOrdercode());
        orderTime.setText("下单时间：" + data.getCreatetime());
        switch (data.getOrderstatus()) {
            case 1: // 1: 已预订
                cancelOrder.setVisibility(View.VISIBLE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.GONE);
                break;
            case 2: // 2: 已借阅
                cancelOrder.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                deleteOrder.setVisibility(View.GONE);
                againOrder.setVisibility(View.VISIBLE);
                break;
            case 3: // 3：已评价
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
                showAlertDialog("是否取消订单", data.getOrderstatus());
                break;

            case R.id.comment: // TODO 评论按钮

                break;

            case R.id.delete_order: // 删除订单按钮
                showAlertDialog("是否删除订单", data.getOrderstatus());
                break;

            case R.id.again_order: // 再次预订按钮
                showAlertDialog("是否再次预订", data.getOrderstatus());
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
                            Intent intent = new Intent(OrderBookDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.BOOK);
                            intent.putExtra("OrderingID", data.getOrderid());
                            startActivity(intent);
                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(data.getOrderid());
                        } else if (state == 6) {
                            // 联系客服
                            String mobile = data.getOrdercode();
                            CallPhoneUtils.callPhone(mobile, OrderBookDetailActivity.this);
                        }
                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }


    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteBookOrder(orderID);
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
                    ToastUtil.showToast(OrderBookDetailActivity.this, "删除成功！");
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
