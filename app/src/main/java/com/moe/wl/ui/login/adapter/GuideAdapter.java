package com.moe.wl.ui.login.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GuideAdapter extends PagerAdapter {

	private List<View> verticalViews1;

	public GuideAdapter(List<View> mListViews) {
		this.verticalViews1 = mListViews;
	}

	@Override
	public int getCount() {
		return verticalViews1.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// 添加数据
	@Override
	public Object instantiateItem(ViewGroup viewpager, int position) {
		viewpager.addView(verticalViews1.get(position));
		return verticalViews1.get(position);
	}

	// 删除数据
	@Override
	public void destroyItem(ViewGroup viewpager, int position, Object object) {
		viewpager.removeView(verticalViews1.get(position));
	}
}
