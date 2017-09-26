package com.moe.wl.ui.main.adapter;

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
    private List<String> titles=new ArrayList<>();

    public void setFragments(List<Fragment> fragments, List<String> states) {
        this.fragments = fragments;
        this.titles=states;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titles!=null&&titles.size()>0){
          return titles.get(position);
        }
        return null;
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
