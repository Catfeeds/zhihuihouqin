package cn.lc.model.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/16 0016.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<>();
    private String[] titles={"已预约","服务中","已完成","待评价","已取消"};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments!=null){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(fragments!=null){
            return fragments.size();
        }
        return 0;
    }
}
