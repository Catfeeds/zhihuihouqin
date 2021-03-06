package com.moe.wl.framework.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.LogUtil;


/**
 * Created by hh on 2016/5/17.
 */
public class TabBar extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.iv_tab1)
    ImageView ivTab1;
    @BindView(R.id.tv_tab1)
    TextView tvTab1;
    @BindView(R.id.iv_tab2)
    ImageView ivTab2;
    @BindView(R.id.tv_tab2)
    TextView tvTab2;
    @BindView(R.id.iv_tab3)
    ImageView ivTab3;
    @BindView(R.id.tv_tab3)
    TextView tvTab3;
    @BindView(R.id.iv_tab4)
    ImageView ivTab4;
    @BindView(R.id.tv_tab4)
    TextView tvTab4;
    @BindView(R.id.ll_tab1)
    LinearLayout llTab1;
    @BindView(R.id.ll_tab2)
    LinearLayout llTab2;
    @BindView(R.id.ll_tab3)
    LinearLayout llTab3;
    @BindView(R.id.ll_tab4)
    LinearLayout llTab4;


    private Context ct;
    private ViewPager mViewPager;
    private OnItemChangedListener onItemChangedListener;
    private int mCurrentItem = -1;

    public TabBar(Context context) {
        this(context, null, -1);
    }

    public TabBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.ct = context;
        init();
    }

    private void init() {
        View view = View.inflate(ct, R.layout.m_bottom, this);
        ButterKnife.bind(this, view);

        //设置成下面的颜色
        setBackgroundColor(ContextCompat.getColor(ct,android.R.color.white));
    }

    @OnClick({R.id.ll_tab1, R.id.ll_tab2, R.id.ll_tab3, R.id.ll_tab4})
    public void onClick(View view) {
        int clickPosition = -1;
        switch (view.getId()) {
            case R.id.ll_tab1:
                clickPosition = 0;
                break;
            case R.id.ll_tab2:
                clickPosition = 1;
                break;
            case R.id.ll_tab3:
                clickPosition = 2;
                break;
            case R.id.ll_tab4:
                clickPosition = 3;
                break;
        }
        setItemChecked(clickPosition);
    }


    public void setItemChecked(int position) {

        if (mCurrentItem == position) {
            return;
        }

        //如果监听了此方法,且return true , 则消费掉
        if (onItemChangedListener != null) {
            if (onItemChangedListener.onItemChecked(position)) {
//                if (mCurrentItem != -1) {//                    bottom_rg.check(tvArray[mCurrentItem].getId());
//                }
//                mViewPager.setCurrentItem(mCurrentItem);
                return;
            }
        }

        LogUtil.log("切换到:" + position);
        mCurrentItem = position;

        checkTab(position);

        if (mViewPager != null) {
            mViewPager.setCurrentItem(position);
        }
    }

    private void checkTab(int position) {
        //处理一些颜色的变化
        switch (position) {
            case 0:
                resetColors();
                ivTab1.setImageResource(R.drawable.home_select);
                tvTab1.setTextColor(ContextCompat.getColor(ct,R.color.tv_bottom));
                break;
            case 1:
                resetColors();
                ivTab2.setImageResource(R.drawable.treasure_chest_select);
                tvTab2.setTextColor(ContextCompat.getColor(ct,R.color.tv_bottom));
                break;
            case 2:
                resetColors();
                ivTab3.setImageResource(R.drawable.message_select);
                tvTab3.setTextColor(ContextCompat.getColor(ct,R.color.tv_bottom));
                break;
            case 3:
                resetColors();
                ivTab4.setImageResource(R.drawable.me_select);
                tvTab4.setTextColor(ContextCompat.getColor(ct,R.color.tv_bottom));
                break;
        }
    }

    private void resetColors(){
        ivTab1.setImageResource(R.drawable.home);
        ivTab2.setImageResource(R.drawable.treasure_chest);
        ivTab3.setImageResource(R.drawable.message);
        ivTab4.setImageResource(R.drawable.me);
      /*  tvTab1.setTextColor(Color.parseColor("#000"));
        tvTab2.setTextColor(Color.parseColor("#000"));
        tvTab3.setTextColor(Color.parseColor("#000"));
        tvTab4.setTextColor(Color.parseColor("#000"));*/
        tvTab1.setTextColor(Color.BLACK);
        tvTab2.setTextColor(Color.BLACK);
        tvTab3.setTextColor(Color.BLACK);
        tvTab4.setTextColor(Color.BLACK);
    }



    public void setViewPager(ViewPager vp) {
        if (vp == null)
            return;
        this.mViewPager = vp;
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }


    OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setItemChecked(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


    public void setOnItemChangedListener(OnItemChangedListener l) {
        this.onItemChangedListener = l;
    }



    public interface OnItemChangedListener {
        boolean onItemChecked(int position);
    }
}
