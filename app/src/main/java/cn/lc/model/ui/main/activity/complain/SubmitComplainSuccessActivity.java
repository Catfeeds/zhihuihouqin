package cn.lc.model.ui.main.activity.complain;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class SubmitComplainSuccessActivity extends AppCompatActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar heathServerceTitle;
    @BindView(R.id.iv_submit_or_pay)
    ImageView ivSubmitOrPay;
    @BindView(R.id.tv_submit_success)
    TextView tvSubmitSuccess;
    @BindView(R.id.tv_book_submit_des)
    TextView tvBookSubmitDes;
    @BindView(R.id.activity_sbumit_success)
    LinearLayout activitySbumitSuccess;

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
        heathServerceTitle.setBack(true);
        heathServerceTitle.setTitle("提交结果");
    }
}
