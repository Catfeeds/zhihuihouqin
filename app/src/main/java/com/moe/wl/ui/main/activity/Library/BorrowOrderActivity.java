package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.JieYueTimeBean;
import com.moe.wl.ui.mywidget.PopSelectTime;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * 图书馆借阅订单
 */
public class BorrowOrderActivity extends Base2Activity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.iv_take_book_time)
    ImageView ivTakeBookTime;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.activity_borrow_order)
    LinearLayout activityBorrowOrder;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private String realName;
    private String phoneNumber;
    private String myTime;
    private JieYueTimeBean mTimeBean;
    private BooklistBean bean;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_borrow_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        bean = (BooklistBean) getIntent().getSerializableExtra("bean");
        realName = SharedPrefHelper.getInstance().getRealName();
        if (TextUtils.isEmpty(realName)) {
            realName = SharedPrefHelper.getInstance().getNickname();
        }
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        tvName.setText(realName);
        tvPhoneNum.setText(phoneNumber);
        getTime();
    }

    private void initTitle() {
        titleBar.setTitle("借阅订单");
        titleBar.setBack(true);
    }


    @OnClick({R.id.rl_time, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_time:
                showWindow();
                break;
            case R.id.tv_confirm:
                if (TextUtils.isEmpty(myTime)){
                    showToast("请先选择预计取书时间");
                    return;
                }
                Intent intent = new Intent(this, BookConfirmOrderActivity.class);
                intent.putExtra("bean", (Serializable) bean);
                intent.putExtra("time", myTime);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void showWindow() {
        PopSelectTime selectTimePop = new PopSelectTime(this, mTimeBean, new PopSelectTime.OnSelectClick() {
            @Override
            public void onClick(String typeid, String time) {
                if (typeid.equals("1")) {
                    myTime="上午 " + time;
                    tvTime.setText(myTime);
                } else {
                    myTime="下午 " + time;
                    tvTime.setText(myTime);
                }
            }
        });

        selectTimePop.showAtLocation(activityBorrowOrder, Gravity.CENTER, 0, 0);
    }

    public void getTime() {
        Observable observable = RetrofitUtils.getInstance().getJieyueTime();
        showProgressDialog();
        observable.subscribe(new Subscriber<JieYueTimeBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(JieYueTimeBean timeBean) {
                if (timeBean != null) {
                    mTimeBean = timeBean;
                }
            }
        });
    }
}
