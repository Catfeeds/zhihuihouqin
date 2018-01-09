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
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.bean.Bbean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.OrderVisitorsListBean;
import com.moe.wl.ui.main.bean.VisitorsOrderDetailBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import java.util.List;

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
    @BindView(R.id.media)
    TextView media;
    @BindView(R.id.tv_build_num)
    TextView buildNum;

    //private VisitorsOrderDetailBean data;
    private Bbean data;

    private CustomerDialog progressDialog;
    //    private int orderID; // 订单类型分类
    private int state;
    private Object noOrderDetail;

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
        //setUI();
        Intent intent = getIntent();
        int orderID = intent.getIntExtra("OrderID", 0);
        int state = intent.getIntExtra("state", 0);
        LogUtils.i("state==="+state);
        if (state == 9) {//获得还不是真正的订单的详情信息
            getNoOrderDetail(orderID,10);
        } else {
            //getData(orderID);
            getNoOrderDetail(orderID,1);
        }

    }

    private void getData(int orderID) {
        Observable observable = RetrofitUtils.getInstance().visitorOrderDetail(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<VisitorsOrderDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("来访人员订单详情");
            }

            @Override
            public void onNext(VisitorsOrderDetailBean bean) {
                if(bean==null){
                    LogUtils.i("订单为空");
                    return;
                }
                if (bean.getErrCode() == 0) {
                    //setUI(bean);
                    //data=bean;
                } else if (bean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    ToastUtil.showToast(OrderVisitorsDetailActivity.this, bean.getMsg());
                }
            }
        });
    }

    // 设置页面
    private void setUI(Bbean data) {
        //data = (OrderVisitorsListBean.OrderlistEntity) getIntent().getSerializableExtra("Data");
        if (data == null) {
            ToastUtil.showToast(this, "来访人员订单数据异常！");
            return;
        }
        Bbean.VisitOrderBean orderlistBean = data.getVisitOrder();
        //VisitorsOrderDetailBean.VisitOrderBean orderlistBean = data.getVisitOrder();
        realName.setText("被访人员：" + orderlistBean.getRealname());
        myMobile.setText("电话：" + orderlistBean.getPhonenum());
        roomNumber.setText("房间号：" + orderlistBean.getRoomnum());
        arriveName.setText("来访人员：" + orderlistBean.getVname());
        mobile.setText("电话：" + orderlistBean.getVmobile());
        idCard.setText("身份证号：" + orderlistBean.getVidnum());
        arrivePeople.setText("随行人员：" + orderlistBean.getVpnum());
        arriveTime.setText("来访时间：" + orderlistBean.getVisittime());
        orderId.setText("订单号：" + orderlistBean.getOrdercode());
        orderTime.setText("下单时间：" +orderlistBean.getCreatetime());
        buildNum.setText("楼号"+orderlistBean.getBuildnum());
        if (orderlistBean.getByuserlist() == null || orderlistBean.getByuserlist().size() == 0) {
            llUsers.setVisibility(View.GONE);
        } else {
            llUsers.setVisibility(View.VISIBLE);
            StringBuffer str = new StringBuffer();
            for (int i = 0; i <orderlistBean.getByuserlist().size(); i++) {
                str.append("姓名：" +orderlistBean.getByuserlist().get(i).getName() + "\n身份证号：" + orderlistBean.getByuserlist().get(i).getIdcard());
                if (i != orderlistBean.getByuserlist().size() - 1) {
                    str.append("\n");
                }
            }
            arriveUsers.setText(str.toString());
        }

        state = orderlistBean.getStatus();
        right.setVisibility(View.VISIBLE);
        switch (state) {
            case 1: // 1: 已预约
                left.setText("确认订单");
                if (orderlistBean.getVisitchecked() == 0) {
                    left.setVisibility(View.VISIBLE);
                } else {
                    left.setVisibility(View.GONE);
                }
                right.setText("取消订单");
                break;
            case 2: // 2: 配送中
//                right.setText("已完成");
                break;
            case 3: // 3：待评价
                right.setText("再次预订");
                break;
            case 4: // 4：已完成
                right.setText("立即评价");
                break;
            case 5: // 5：已取消
                right.setText("删除订单");
                break;
            default:
                media.setVisibility(View.VISIBLE);
                left.setVisibility(View.VISIBLE);
                left.setText("提交");
                right.setText("取消");
                break;
        }
    }

    @OnClick({R.id.media,R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.media:
                // TODO: 2017/12/6 0006 跳到来访人员界面
                Intent intent = new Intent(this, LaiFangActivity.class);
                //OrderVisitorsListBean.OrderlistEntity data
                intent.putExtra("from",1);
                intent.putExtra("data",data);
                startActivity(intent);
                finish();
                break;
            case R.id.right:
                switch (state) {
                    case 1:
                        showAlertDialog("是否取消预约", state);
                        break;
                    case 2:
//                        showAlertDialog("是否拨打电话", state);
                        break;
                    case 3:
                        OtherUtils.gotoComment(OrderVisitorsDetailActivity.this, data.getVisitOrder().getId(), Constants.VISITORS);
                        break;
                    case 4:
                        startActivity(new Intent(OrderVisitorsDetailActivity.this, LaiFangActivity.class));
                        break;
                    case 5:
                        showAlertDialog("是否删除订单", state);
                        break;
                    default:
                        // TODO: 2017/12/6 0006 取消订单
                        showAlertDialog("是否取消订单", state);
                        break;
                }
                break;

            case R.id.left:
                // TODO 提交订单
                commitOrder(data.getVisitOrder().getId());
                break;

        }
    }

    private void showAlertDialog(String s, final int state) {
        new AlertDialog(this).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.i("stata==========="+state);
                        if (state == 0) {
                            Intent intent = new Intent(OrderVisitorsDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.VISITORS);
                            intent.putExtra("OrderingID", data.getVisitOrder().getId());
                            startActivity(intent);

                        } else if (state == 3) {
                            // TODO 再次预订

                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(data.getVisitOrder().getId());
                        }
                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    // 审核通过这个订单
    private void commitOrder(int id) {
        Observable observable = RetrofitUtils.getInstance().commitOrder(id,1);
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
                    ToastUtil.showToast(OrderVisitorsDetailActivity.this, "确认订单成功");
                } else if (orderBean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    ToastUtil.showToast(OrderVisitorsDetailActivity.this, orderBean.getMsg());
                }
            }
        });
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

    public void getNoOrderDetail(int orderID,int type) {
        Observable observable = RetrofitUtils.getInstance().visitorNoOrderDetail(orderID,type);
        showProgressDialog();
        observable.subscribe(new Subscriber<Bbean>() {
        //observable.subscribe(new Subscriber<VisitorsOrderDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("来访人员订单详情");
            }

            @Override
            //public void onNext(VisitorsOrderDetailBean bean) {
            public void onNext(Bbean bean) {
                if(bean==null){
                    LogUtils.i("订单为空");
                    return;
                }
                if (bean.getErrCode() == 0) {
                    setUI(bean);
                    data=bean;
                } else if (bean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    ToastUtil.showToast(OrderVisitorsDetailActivity.this, bean.getMsg());
                }
            }
        });
    }
}
