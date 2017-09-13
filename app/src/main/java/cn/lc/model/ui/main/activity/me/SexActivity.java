package cn.lc.model.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.widget.TitleBar;

public class SexActivity extends AppCompatActivity {

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
    }

    @OnClick({R.id.rl_man, R.id.rl_girl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_man:
                selectManOrGirl(true);
                break;
            case R.id.rl_girl:
                selectManOrGirl(false);
                break;
        }
    }

    private void selectManOrGirl(boolean selectMan) {
        Intent intent = new Intent();
        if(selectMan){
            ivSelectMan.setVisibility(View.VISIBLE);
            ivSelectGirl.setVisibility(View.GONE);
            intent.putExtra("sex","男");
            setResult(Constants.SELECTSEX,intent);
        }else{
            ivSelectMan.setVisibility(View.GONE);
            ivSelectGirl.setVisibility(View.VISIBLE);
            intent.putExtra("sex","女");
            setResult(Constants.SELECTSEX,intent);
        }
        finish();
    }
}
