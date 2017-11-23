package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderVisitorsListBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：来访人员订单详情页面
 * 作者：Shixhe On 2017/10/12 0012
 */
public class OrderVisitorsDetailActivity extends MyBaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.real_name)
    TextView realName;
    @BindView(R.id.my_mobile)
    TextView myMobile;
    @BindView(R.id.room_number)
    TextView roomNumber;
    @BindView(R.id.arrive_name)
    TextView arriveName;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.id_card)
    TextView idCard;
    @BindView(R.id.arrive_people)
    TextView arrivePeople;
    @BindView(R.id.arrive_time)
    TextView arriveTime;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.ll_users)
    LinearLayout llUsers;
    @BindView(R.id.arrive_users)
    TextView arriveUsers;

    private OrderVisitorsListBean.OrderlistEntity data;

    private CustomerDialog progressDialog;
    //    private int orderID; // 订单类型分类
    private int state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_visitors_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        titleBar.setTitle("订单详情");
        titleBar.setBack(true);
        setUI();
//        orderID = getIntent().getIntExtra("OrderID", 0);
//        getData(orderID);
    }

    // 设置页面
    private void setUI() {
        data = (OrderVisitorsListBean.OrderlistEntity) getIntent().getSerializableExtra("Data");
        if (data == null) {
            ToastUtil.showToast(this, "来访人员订单数据异常！");
            return;
        }
        realName.setText("被访人员：" + data.getRealname());
        myMobile.setText("电话：" + data.getPhonenum());
        roomNumber.setText("房间号：" + data.getRoomnum());
        arriveName.setText("来访人员：" + data.getVname());
        mobile.setText("电话：" + data.getVmobile());
        idCard.setText("身份证号：" + data.getVidnum());
        arrivePeople.setText("随行人员：" + data.getVpnum());
        arriveTime.setText("来访时间：" + data.getVisittime());
        orderId.setText("订单号：" + data.getOrdercode());
        orderTime.setText("下单时间：" + data.getCreatetime());
        if (data.getByuserlist() == null || data.getByuserlist().size() == 0) {
            llUsers.setVisibility(View.GONE);
        } else {
            llUsers.setVisibility(View.VISIBLE);
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < data.getByuserlist().size(); i++) {
                str.append("姓名：" + data.getByuserlist().get(i).getName() + "\n身份证号：" + data.getByuserlist().get(i).getIdcard() + "\n");
            }
            arriveUsers.setText(str.toString());
        }

        state = data.getStatus();
        right.setVisibility(View.VISIBLE);
        switch (state) {
            case 1: // 1: 已预约
                right.setText("取消订单");
                break;
            case 2: // 2: 配送中
//                right.setText("已完成");
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
                        OtherUtils.gotoComment(OrderVisitorsDetailActivity.this, data.getId(), Constants.VISITORS);
                        break;
                    case 4:
                        startActivity(new Intent(OrderVisitorsDetailActivity.this, LaiFangActivity.class));
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
                            Intent intent = new Intent(OrderVisitorsDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.VISITORS);
                            intent.putExtra("OrderingID", data.getId());
                            startActivity(intent);
                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(data.getId());
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
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.VISITORS, orderID);
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
                    ToastUtil.showToast(OrderVisitorsDetailActivity.this, "删除成功！");
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
