package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.ServiceOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JieYueSuccActivity extends MyBaseActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;
    @BindView(R.id.tv_book_submit_des)
    TextView tvBookSubmitDes;
    @BindView(R.id.tv_bt)
    TextView tvBt;

    private int from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_yue_succ);
        ButterKnife.bind(this);
        from = getIntent().getIntExtra("from", -1);
        initTitle();
        tvBt.setVisibility(View.GONE);
        /*//判断行数
        tvBookSubmitDes.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //这个回调会调用多次，获取完行数记得注销监听
                tvBookSubmitDes.getViewTreeObserver().removeOnPreDrawListener(this);
                LogUtils.i("TextView 行数：" + tvBookSubmitDes.getLineCount());
                if(tvBookSubmitDes.getLineCount()>1){
                    tvBookSubmitDes.setGravity(Gravity.LEFT);
                }else{
                    tvBookSubmitDes.setGravity(Gravity.CENTER);
                }
                return false;
            }
        });*/
        if (from == 1) {
            tvBookSubmitDes.setText("感谢您的推荐");
        } else if (from == 2) {
            tvBookSubmitDes.setText("您的申请已提交成功，我们会尽快审核完毕，审核后会以短信方式通知您，感谢您的配合");
        } else if (from == 3) {
            tvBookSubmitDes.setText("已经收到您的需求，我们会尽快采集购买，采购后会及时联系您，给您带来的不便，敬请谅解！");
        } else {
            tvBt.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.tv_bt)
    public void onViewClicked(View view){
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("index", 0);
        intent.putExtra("from", Constants.BOOK);
        intent.putExtra("state", Constants.orderBook);
        startActivity(intent);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("提交成功");
    }
}
