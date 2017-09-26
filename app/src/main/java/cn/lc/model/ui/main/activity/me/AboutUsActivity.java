package cn.lc.model.ui.main.activity.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

/**
 * Created by 我的电脑 on 2017/9/11 0011.
 */
public class AboutUsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        TitleBar title = (TitleBar) findViewById(R.id.title);
        title.setBack(true);
        title.setTitle("关于我们");
    }
}
