package cn.lc.model.ui.main.activity.Library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.mywidget.SelectTimePop;

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
    private String bookids;
    private String realName;
    private String phoneNumber;
    private String bookName;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_borrow_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        bookids = getIntent().getStringExtra("bookids");
        bookName = getIntent().getStringExtra("bookName");
        realName = SharedPrefHelper.getInstance().getRealName();
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        tvName.setText(realName);
        tvPhoneNum.setText(phoneNumber);
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
                Intent intent = new Intent(this, BookConfirmOrderActivity.class);
                intent.putExtra("bookId",bookids);
                intent.putExtra("bookName",bookName);
                // TODO: 2017/9/8 0008 后端没给提供时间接口
                intent.putExtra("time","12:00");
                startActivity(intent);
                break;
        }
    }

    private void showWindow() {
        SelectTimePop selectTimePop = new SelectTimePop(this, null, new SelectTimePop.OnSelectClick() {
            @Override
            public void onClick(int id, String time) {

            }
        });

    }
}
