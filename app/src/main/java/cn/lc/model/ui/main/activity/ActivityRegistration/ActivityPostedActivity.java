package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class ActivityPostedActivity extends AppCompatActivity {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_posted_name)
    EditText etPostedName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_minzu)
    EditText etMinzu;
    @BindView(R.id.et_activity_time)
    ImageView etActivityTime;
    @BindView(R.id.et_activity_address)
    EditText etActivityAddress;
    @BindView(R.id.et_person_limit)
    EditText etPersonLimit;
    @BindView(R.id.et_activity_content)
    EditText etActivityContent;
    @BindView(R.id.add_photo)
    ImageView addPhoto;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_posted)
    LinearLayout activityPosted;
    @BindView(R.id.iv_posted_des)
    ImageView ivPostedDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("活动发布");
    }

    @OnClick({R.id.iv_posted_des,R.id.et_activity_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_posted_des:
                Intent intent = new Intent(this, PostedNotesActivity.class);
                startActivity(intent);
                break;
            case R.id.et_activity_time:
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
