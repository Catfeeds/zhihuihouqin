package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.bean.OrderRepairsDetailOneBean;
import com.moe.wl.ui.mywidget.StarBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：物业报修详情ONE
 * 作者：Shixhe On 2017/10/11 0011
 */

public class OrderRepairDetailOneFragment extends BaseFragment2 {


    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.state)
    TextView states;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.service_type)
    TextView serviceType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.order_state)
    TextView orderState;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ratingBar)
    StarBar ratingBar;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.service_content)
    TextView serviceContent;
    Unbinder unbinder;
    private OrderRepairsDetailOneBean data;

    private int state;

    @Override
    public View setLayout() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_repair_detail_one, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            int orderID = bundle.getInt("OrderID");
            getData(orderID);
        }
    }

    private void setUI() {
        if (data == null) {
            ToastUtil.showToast(getActivity(), "报修订单数据错误！");
            getActivity().finish();
        }
        orderNumber.setText("订单号：" + data.getDetail().getOrdercode());
        orderTime.setText("下单时间：" + data.getDetail().getCreatetime());
        address.setText("服务地址：" + data.getDetail().getServiceplace());
        serviceType.setText("服务类型："+data.getDetail().getItemname());
        orderState.setText("支付状态：" + (data.getDetail().getPaystatus() == 0 ? "未支付" : "已支付"));
        time.setText("上门时间：" + data.getDetail().getInvitetime());
        serviceContent.setText("服务内容：" + data.getDetail().getItemname());
        GlideLoading.getInstance().loadImgUrlNyImgLoader(getActivity(), data.getDetail().getMenderphoto(), image, R.mipmap.ic_default_square);
        name.setText(data.getDetail().getMendername());
        ratingBar.setStarMark(data.getDetail().getScore());
        ratingBar.setIntegerMark(false);
        ratingBar.ismove(false);
        score.setText("" + data.getDetail().getScore() + "分");
        state = data.getDetail().getOrderstatus();
        switch (state) {
            case 1: // 1: 待接单
                states.setText("待接单");
                break;
            case 2: // 2：已接单
                states.setText("已接单");
                break;
            case 3: // 3：已完成
                states.setText("已完成");
                break;
            case 4: // 4：待评价
                states.setText("待评价");
                break;
            case 5: // 5：已取消
                states.setText("已取消");
                break;
        }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
