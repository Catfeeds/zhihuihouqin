package cn.lc.model.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class FmPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<>();
    private List<String> tabs=new ArrayList<>();

    @Override
    public CharSequence getPageTitle(int position) {
        if(tabs!=null){
            return tabs.get(position);
        }else{
            return null;
        }
    }

    public void setFragments(List<Fragment> fragments,List<String> tabs) {
        this.fragments = fragments;
        this.tabs=tabs;
        notifyDataSetChanged();
    }

    public FmPagerAdapter(FragmentManager fm) {
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
