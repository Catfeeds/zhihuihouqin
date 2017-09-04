package cn.lc.model.ui.main.activity.OfficeSupplies;

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
import cn.lc.model.framework.widget.TitleBar;

public class SpPayActivity extends AppCompatActivity {

    @BindView(R.id.pay_title)
    TitleBar payTitle;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.iv_pay)
    ImageView ivPay;
    @BindView(R.id.tv_weixin_pay)
    TextView tvWeixinPay;
    @BindView(R.id.iv_pay_check)
    ImageView ivPayCheck;
    @BindView(R.id.tv_available_balance)
    TextView tvAvailableBalance;
    @BindView(R.id.tv_now_pay)
    TextView tvNowPay;
    @BindView(R.id.activity_sp_pay)
    LinearLayout activitySpPay;
    private boolean isCheck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_pay);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        payTitle.setBack(true);
        payTitle.setTitle("支付");
    }

    @OnClick({R.id.iv_pay_check, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pay_check:
                checkSelect();
                break;
            case R.id.tv_now_pay:
                // TODO: 2017/8/30 0030 支付
                break;
        }
    }

    private void checkSelect() {
        isCheck=!isCheck;
        if(isCheck){
            ivPayCheck.setImageResource(R.drawable.selected);
        }else{
            ivPayCheck.setImageResource(R.drawable.unselected);
        }
    }
}
