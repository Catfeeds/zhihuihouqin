package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TabBar;
import com.moe.wl.ui.main.fragment.Tab1Fragment;
import com.moe.wl.ui.main.fragment.Tab2Fragment;
import com.moe.wl.ui.main.fragment.Tab3Fragment;
import com.moe.wl.ui.main.fragment.Tab4Fragment;
import com.moe.wl.ui.main.model.MainModel;
import com.moe.wl.ui.main.modelimpl.MainModelImpl;
import com.moe.wl.ui.main.presenter.MainPresenter;
import com.moe.wl.ui.main.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mvp.cn.util.ToastUtil;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView {

    private static final int SCANNING_CODE = 1001;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @BindView(R.id.m_frameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.m_bottom)
    TabBar mBottom;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    public void initView() {
        fragments.add(new Tab1Fragment());
        fragments.add(new Tab2Fragment());
        fragments.add(new Tab3Fragment());
        fragments.add(new Tab4Fragment());
        //默认选中的界面
        mBottom.setOnItemChangedListener(onBottomItemClickListener);
        mBottom.setItemChecked(0);

        getData();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getActivity(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
    /**
     * 底部导航栏的点击
     * 未登录状态下
     */
    TabBar.OnItemChangedListener onBottomItemClickListener = new TabBar.OnItemChangedListener() {
        @Override
        public boolean onItemChecked(int position) {
//            if (position == 2) {
//                if (!isLogin()) {
//                    return true;
//                }
//            }
            changeFragment(fragments.get(position));
            return false;
        }
    };

    private void changeFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.m_frameLayout, f);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {

        } else {
            // 扫描二维码/条码回传
            if (requestCode == SCANNING_CODE) {
                if (data != null) {
                    String content = data.getStringExtra("Result");
                    // 跳转系统浏览器
                    ToastUtil.showToast(MainActivity.this, "扫描结果： " + content);
                    Intent intent= new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(content));
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainModel createModel() {
        return new MainModelImpl();
    }

    public void getData() {
        getPresenter().getData();
    }
}
