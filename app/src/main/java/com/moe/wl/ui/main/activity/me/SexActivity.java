package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SexActivity extends MyBaseActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_select_man)
    ImageView ivSelectMan;
    @BindView(R.id.rl_man)
    RelativeLayout rlMan;
    @BindView(R.id.iv_select_girl)
    ImageView ivSelectGirl;
    @BindView(R.id.rl_girl)
    RelativeLayout rlGirl;
    @BindView(R.id.activity_sex)
    LinearLayout activitySex;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        ButterKnife.bind(this);
        initTitle();
        selectManOrGirl(true);
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("性别");
      /*title.setTitleRight("确定");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
//        if ("男".equals(getIntent().getStringExtra("sex"))) {
//            ivSelectMan.setVisibility(View.VISIBLE);
//            ivSelectGirl.setVisibility(View.GONE);
//        } else if("女".equals(getIntent().getStringExtra("sex"))) {
//            ivSelectMan.setVisibility(View.GONE);
//            ivSelectGirl.setVisibility(View.VISIBLE);
//        }
    }

    @OnClick({R.id.rl_man, R.id.rl_girl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_man:
                selectManOrGirl(true);
                finish();
                break;
            case R.id.rl_girl:
                selectManOrGirl(false);
                finish();
                break;
        }
    }

    private void selectManOrGirl(boolean selectMan) {
        intent = new Intent();
        if (selectMan) {
            ivSelectMan.setVisibility(View.VISIBLE);
            ivSelectGirl.setVisibility(View.GONE);
            intent.putExtra("sex", "男");
            setResult(RESULT_OK, intent);
        } else {
            ivSelectMan.setVisibility(View.GONE);
            ivSelectGirl.setVisibility(View.VISIBLE);
            intent.putExtra("sex", "女");
            setResult(RESULT_OK, intent);
        }
    }
}
