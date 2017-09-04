package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.SignUpPersonAdapter;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;

public class ActivityDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_title)
    TitleBar activityTitle;
    @BindView(R.id.iv_big_pic)
    ImageView ivBigPic;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.tv_act_num)
    TextView tvActNum;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_posted_time)
    TextView tvPostedTime;
    @BindView(R.id.tv_zhubanfang)
    TextView tvZhubanfang;
    @BindView(R.id.tv_sign_up_num)
    TextView tvSignUpNum;
    @BindView(R.id.nsrv_sign_up)
    NoSlideRecyclerView nsrvSignUp;
    @BindView(R.id.activity_detail)
    LinearLayout activityDetail;
    @BindView(R.id.sv)
    ScrollView sv;
    private SignUpPersonAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initTitle();
        initRecycler();
        //设置scrollView里面的内容置顶
        sv.smoothScrollTo(0,20);
        sv.setFocusable(true);

    }

    private void initRecycler() {
        nsrvSignUp.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new SignUpPersonAdapter(this);
        nsrvSignUp.setAdapter(rvAdapter);

        rvAdapter.setListener(new SignUpPersonAdapter.OnitemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent=new Intent(ActivityDetailActivity.this,SignUpPersonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTitle() {
        activityTitle.setBack(true);
        activityTitle.setTitle("活动详情");
    }

    @OnClick(R.id.tv_sign_up)
    public void onViewClicked() {
        Intent intent=new Intent(this,FillInfoActivity.class);
        startActivity(intent);
    }
}
