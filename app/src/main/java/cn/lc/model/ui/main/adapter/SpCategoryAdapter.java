package cn.lc.model.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class SpCategoryAdapter extends FragmentPagerAdapter {
    private List<Fragment> data=new ArrayList<>();
    private List<String> titles=new ArrayList<>();
    public void setData(List<Fragment> data,List<String> titles){
        this.data=data;
        this.titles=titles;
        notifyDataSetChanged();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(titles!=null&&titles.size()>0){
            return titles.get(position);
        }
        return super.getPageTitle(position);
    }

    public SpCategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return data!=null?data.get(position):null;
    }

    @Override
    public int getCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }
        return 0;
    }
}
