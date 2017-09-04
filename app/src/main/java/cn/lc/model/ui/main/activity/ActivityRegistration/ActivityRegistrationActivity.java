package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.HomeNsrlv3Adapter;

public class ActivityRegistrationActivity extends AppCompatActivity {

    @BindView(R.id.activity_title)
    TitleBar activityTitle;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.iv_big_pic)
    ImageView ivBigPic;
    @BindView(R.id.rv_activity)
    RecyclerView rvActivity;
    @BindView(R.id.tv_activity_posted)
    TextView tvActivityPosted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        ButterKnife.bind(this);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        rvActivity.setLayoutManager(new LinearLayoutManager(this));
        HomeNsrlv3Adapter homeNsrlv3Adapter = new HomeNsrlv3Adapter(this);
        rvActivity.setAdapter(homeNsrlv3Adapter);
    }

    private void initTitle() {
        activityTitle.setBack(true);
        activityTitle.setTitle("活动报名");
    }

    @OnClick(R.id.tv_activity_posted)
    public void onViewClicked() {
        Intent intent = new Intent(this, ActivityPostedActivity.class);
        startActivity(intent);
    }
}
