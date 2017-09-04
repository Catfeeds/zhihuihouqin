package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.login.activity.LoginActivity;
import cn.lc.model.ui.main.adapter.GuideVpAdapter;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.vp_splash)
    ViewPager vpSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!SharedPrefHelper.getInstance().getIsFirst()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            //设置无标题
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //设置全屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);
            ButterKnife.bind(this);
            initViewPager();

        }
    }

    private void initViewPager() {
        GuideVpAdapter splashVpAdapter = new GuideVpAdapter(this);
        vpSplash.setAdapter(splashVpAdapter);
    }
}
