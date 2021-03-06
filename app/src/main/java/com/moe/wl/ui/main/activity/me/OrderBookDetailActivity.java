package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.Library.LibraryActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.BookOrderDetailBean;
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
public class OrderBookDetailActivity extends MyBaseActivity {

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
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;

    private BookOrderListBean.OrderlistBean data;

    private CustomerDialog progressDialog;
    private int state;
    private Object orderInfo;

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
        //data = (BookOrderListBean.OrderlistBean) getIntent().getSerializableExtra("Data");
        int id = getIntent().getIntExtra("id",0);
        if (id == 0) {
            ToastUtil.showToast(this, "图书订单数据异常！");
            finish();
        }
        //获得订单信息
        getOrderInfo(id);
        /*GlideLoading.getInstance().loadImgUrlNyImgLoader(this, data.getImg(), image);
        itemName.setText(data.getTitle());
        author.setText("作者：" + data.getAuthor());
        press.setText("出版社：" + data.getPublisher());
        time.setText("取书时间：" + data.getInvitegetbooktime());
        orderId.setText("订单号：" + data.getOrdercode());
        orderTime.setText("下单时间：" + data.getCreatetime());
        right.setVisibility(View.VISIBLE);*/

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
                        toFinish(data.getOrderid());
                        break;
                    case 3:
                        OtherUtils.gotoComment(OrderBookDetailActivity.this, data.getOrderid(), Constants.BOOK);
                        break;
                    case 4:
                        startActivity(new Intent(OrderBookDetailActivity.this, LibraryActivity.class));
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

    // 点击完成
    private void toFinish(int id) {
        Observable observable = RetrofitUtils.getInstance().finishOrder(Constants.BOOK, id);
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
                    ToastUtil.showToast(OrderBookDetailActivity.this, "完成订单");
                } else {
                    ToastUtil.showToast(OrderBookDetailActivity.this, orderBean.getMsg());
                }
            }
        });
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
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.BOOK, orderID);
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

    public void getOrderInfo(int id) {
        Observable observable = RetrofitUtils.getInstance().getBookOrderDetail(id);
        showProgressDialog();
        observable.subscribe(new Subscriber<BookOrderDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("orderbookdetail  error"+e.getMessage());
            }

            @Override
            public void onNext(BookOrderDetailBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    BookOrderDetailBean.DetailBean detail = mResponse.getDetail();
                    if(detail!=null){
                        GlideLoading.getInstance().loadImgUrlNyImgLoader(OrderBookDetailActivity.this,detail.getImg(),image);
                        itemName.setText(detail.getTitle());
                        author.setText("作者：" + detail.getAuthor());
                        press.setText("出版社：" + detail.getPublisher());
                        time.setText("取书时间：" + detail.getInvitegetbooktime());
                        orderId.setText("订单号：" + detail.getOrdercode());
                        orderTime.setText("下单时间：" + detail.getCreatetime());
                        right.setVisibility(View.VISIBLE);
                        state = detail.getOrderstatus();
                        switch (state) {
                            case 1: // 1: 已预约
                                right.setText("取消订单");
                                break;
                            case 2: // 2: 配送中
                                right.setText("已归还");
                                break;
                            case 3: // 3：待评价
                                right.setText("立即评价");
                                break;
                            case 4: // 4：已完成
                                right.setText("再次预订");
                                break;
                            case 5: // 5：已取消
                                right.setText("删除订单");
                                break;
                        }
                    }
                }
            }
        });
    }
}
