package com.moe.wl.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/11/2 0002.
 */

public class RechargeOrderAdapter extends FragmentPagerAdapter {
    private List<String> tabName=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    public CharSequence getPageTitle(int position) {
        return tabName.get(position);
       // return super.getPageTitle(position);
    }

    public RechargeOrderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments!=null){
            return fragments.get(position);
        }else{
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabName.size();
    }

    public void setData(List<String> tabname, List<Fragment> fragments) {
        this.tabName=tabname;
        this.fragments=fragments;
        notifyDataSetChanged();

    }
}
