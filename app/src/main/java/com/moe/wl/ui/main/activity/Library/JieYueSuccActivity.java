package com.moe.wl.ui.main.activity.Library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

public class JieYueSuccActivity extends AppCompatActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;
    @BindView(R.id.tv_book_submit_des)
    TextView tvBookSubmitDes;
    private int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_yue_succ);
        ButterKnife.bind(this);
        from = getIntent().getIntExtra("from", -1);
        initTitle();
        if (from == 1) {
            tvBookSubmitDes.setText("感谢您的提交，我方将以最快时间为您来采购，买回后会以消息或短信方式提示您，感谢您的申请。");
        }else if(from==2){
            tvBookSubmitDes.setText("您的申请已提交成功,我们会尽快审核完毕,审核后会以短信方式通知您,感谢您的配合");
        }else if(from==3){
            tvBookSubmitDes.setText("已经收到您的需求，我们会尽快采集购买，采购 后会及时联系您，给您带来的不便，敬请谅解！");
        }

    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("提交成功");
    }
}
