package cn.lc.model.ui.main.activity.complain;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.ServiceOrderActivity;

public class SubmitComplainSuccessActivity extends AppCompatActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;
    @BindView(R.id.activity_sbumit_success)
    LinearLayout activitySbumitSuccess;
    @BindView(R.id.tv_submit_success)
    TextView tvSubmitSuccess;
    @BindView(R.id.tv_check_order)
    TextView tvCheckOrder;
    @BindView(R.id.iv_submit_or_pay)
    ImageView ivSubmitOrPay;
    @BindView(R.id.tv_book_submit_des)
    TextView tvBookSubmitDes;
//    private boolean isPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_complain_success);
        ButterKnife.bind(this);
        initTitle();
        //设置字体加粗
        tvSubmitSuccess.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void initTitle() {

        titleBar.setBack(true);
        titleBar.setTitle("提交结果");
//        if (isPay) {
//            titleBar.setTitle("支付成功");
//            ivSubmitOrPay.setImageResource(R.drawable.selected);
//        } else {
        titleBar.setTitle("提交成功");
        ivSubmitOrPay.setImageResource(R.drawable.unselected);
//        }
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        startActivity(intent);
    }
}
