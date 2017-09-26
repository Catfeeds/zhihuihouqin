package com.moe.wl.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class BookPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<>();
    private List<String> tabNames= Arrays.asList("最新","最热");

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public BookPagerAdapter(FragmentManager fm) {
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
        return 2;
    }
}
