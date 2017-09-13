package cn.lc.model.ui.main.activity.DryCleaners;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.adapter.DryCleanersLvAdapter;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;
import cn.lc.model.ui.main.wheelView.WheelView;
import rx.Observable;
import rx.Subscriber;

public class DryCleanReserveInfoActivity extends Base2Activity {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_send_time)
    ImageView ivSendTime;
    @BindView(R.id.rl_set_time)
    RelativeLayout rlTime;
    @BindView(R.id.rv_dry_clean)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_dry_clean_reserve_info)
    RelativeLayout activityDryCleanReserveInfo;
    @BindView(R.id.tv_more)
    TextView tvMore;
    private int page = 1;
    private String limit = "10";
    private DryCleanersLvAdapter lvAdapter;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_dry_clean_reserve_info);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        initListView();
        tvUserName.setText(SharedPrefHelper.getInstance().getRealName());
        etPhoneNum.setText(SharedPrefHelper.getInstance().getPhoneNumber());

        getData();
//        CommonUtil.closeSoftKeyboard(this,etPhoneNum);
    }

    private void getData() {
        Observable observable = RetrofitUtils.getInstance().orderDryCleaner(page + "", limit);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderDryCleanBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();

            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("获取订单列表",e.getMessage());
            }

            @Override
            public void onNext(OrderDryCleanBean orderDryCleanBean) {
                if(orderDryCleanBean.getErrCode()==0){
                    List<OrderDryCleanBean.PageBean.ListBean> list = orderDryCleanBean.getPage().getList();
                   getListSucc(list);
                }else{
                    showToast(orderDryCleanBean.getMsg());
                }
            }
        });
    }

    private void getListSucc(List<OrderDryCleanBean.PageBean.ListBean> list) {
        if(list!=null){
            lvAdapter.setData(list);
        }
    }

    private void initListView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lvAdapter = new DryCleanersLvAdapter(this);
        recyclerView.setAdapter(lvAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("干洗店");
    }

    @OnClick({R.id.tv_sum, R.id.tv_submit,R.id.tv_more,R.id.rl_set_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sum:
                break;
            case R.id.tv_submit:
                Intent intent = new Intent(this, ConfirmDryCleanOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_more:
                page++;
                Observable observable = RetrofitUtils.getInstance().orderDryCleaner(page + "", limit);
                break;
            case R.id.rl_set_time:
                Dialog dialog = new Dialog(this);
                View view1 = View.inflate(this, R.layout.time_selector, null);
                LinearLayout lLoyout = (LinearLayout) view1.findViewById(R.id.ll_layout);
                WheelView wv1 = (WheelView) view1.findViewById(R.id.w_v1);
                WheelView wv2 = (WheelView) view1.findViewById(R.id.w_v2);
                WheelView wv3 = (WheelView) view1.findViewById(R.id.w_v3);
                dialog.setContentView(view1);
                // 调整dialog背景大小
                WindowManager windowManager = (WindowManager)
                        getSystemService(Context.WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();
                lLoyout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                        .getWidth() * 0.8), LinearLayout.LayoutParams.WRAP_CONTENT));
                break;
        }
    }
}
