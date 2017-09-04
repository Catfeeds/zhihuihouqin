package cn.lc.model.ui.main.activity.Library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class BookConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.iv_take_book_time)
    TextView ivTakeBookTime;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.activity_book_confirm_order)
    LinearLayout activityBookConfirmOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_confirm_order);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确认订单");
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
    }
}
