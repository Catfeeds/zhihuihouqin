package cn.lc.model.ui.main.activity.Library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.TitleBar;

public class BorrowOrderActivity extends AppCompatActivity {

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
    @BindView(R.id.activity_borrow_order)
    LinearLayout activityBorrowOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_order);
        ButterKnife.bind(this);
        initTitle();
        String realName = SharedPrefHelper.getInstance().getRealName();
        String phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        tvName.setText(realName);
        tvPhoneNum.setText(phoneNumber);
        // TODO: 2017/9/1 0001 还需要后端提供一个时间

    }

    private void initTitle() {
        titleBar.setTitle("借阅订单");
        titleBar.setBack(true);
    }


    @OnClick({R.id.iv_take_book_time, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_take_book_time:
                break;
            case R.id.tv_confirm:
                Intent intent = new Intent(this, BookConfirmOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
