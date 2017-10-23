package com.moe.wl.ui.login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.FullscreenActivity;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.ui.home.model.office.TestModel;
import com.moe.wl.ui.home.modelimpl.office.TestModelImpl;
import com.moe.wl.ui.home.presenter.office.TestPresenter;
import com.moe.wl.ui.home.view.office.TestView;
import com.moe.wl.ui.login.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */

public class GuideAct extends FullscreenActivity<TestModel, TestView, TestPresenter> implements TestView {

    private ViewPager viewpager;
    private GuideAdapter baseAdapter;
    private List<View> views;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.act_guide);
        initGuide();

    }

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public TestModel createModel() {
        return new TestModelImpl();
    }

    private void initGuide() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        views = new ArrayList<View>();

        View view_1 = View.inflate(this, R.layout.item_guide, null);
        ImageView iv_1 = (ImageView) view_1.findViewById(R.id.iv_imageView);
        iv_1.setImageResource(R.mipmap.ic_guide_1);

        View view_2 = View.inflate(this, R.layout.item_guide, null);
        ImageView iv_2 = (ImageView) view_2.findViewById(R.id.iv_imageView);
        iv_2.setImageResource(R.mipmap.ic_guide_2);

        View view_3 = View.inflate(this, R.layout.item_guide, null);
        ImageView iv_3 = (ImageView) view_3.findViewById(R.id.iv_imageView);
        iv_3.setImageResource(R.mipmap.ic_guide_3);

        View view_4 = View.inflate(this, R.layout.item_guide, null);
        ImageView iv_4 = (ImageView) view_4.findViewById(R.id.iv_imageView);
        TextView iv_guide = (TextView) view_4.findViewById(R.id.iv_guide);
        iv_guide.setVisibility(View.VISIBLE);
        iv_4.setImageResource(R.mipmap.ic_guide_4);


        iv_guide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //回调跳转的逻辑
                SharedPrefHelper.getInstance().setIsFirst(false);
                Bundle bundle=new Bundle();
                bundle.putInt("type",1);
                UIManager.turnToAct(GuideAct.this, LoginActivity.class,bundle);
                finish();
            }
        });

        views.add(view_1);
        views.add(view_2);
        views.add(view_3);
        views.add(view_4);

        baseAdapter = new GuideAdapter(views);
        viewpager.setAdapter(baseAdapter);

    }

}
