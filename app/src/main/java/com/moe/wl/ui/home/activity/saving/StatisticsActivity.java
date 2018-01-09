package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.saving.MyFragmentPagerAdapter;
import com.moe.wl.ui.home.fragment.ElectroStatistcsFrgment;
import com.moe.wl.ui.home.fragment.WaterStatistcsFragment;
import com.moe.wl.ui.home.model.saving.StatisticsModel;
import com.moe.wl.ui.home.modelimpl.saving.StatisticsModelImpl;
import com.moe.wl.ui.home.presenter.saving.StatisticsPresenter;
import com.moe.wl.ui.home.view.saving.MenuPopwindow;
import com.moe.wl.ui.home.view.saving.StatisticsView;
import com.moe.wl.ui.main.activity.me.BuildNumAct;
import com.moe.wl.ui.mywidget.BottomMonthTimeDialog;
import com.moe.wl.ui.mywidget.BottomYearTimeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.view.hack.HackyViewPager;

import static com.moe.wl.R.id.tv_date;
import static com.moe.wl.R.id.tv_electro;
import static com.moe.wl.R.id.tv_water;

/**
 * 消耗统计
 */
public class StatisticsActivity extends BaseActivity<StatisticsModel, StatisticsView, StatisticsPresenter> implements StatisticsView, View.OnClickListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    HackyViewPager viewpager;


    private List<Fragment> list_fragment;     //fragment列表
    private List<String> list_Title;          //tab名的列表
    private MyFragmentPagerAdapter adapter;
    private MenuPopwindow popwindow;
    private int REQUEST_CODE=1000;
    private ElectroStatistcsFrgment f1;
    private WaterStatistcsFragment f2;
    private int DateType=1;   //  2月  1年
    private int bid=1;//楼号id
    public String time;
    private static  final int BUILDNUM=100;

    @Override
    public StatisticsPresenter createPresenter() {
        return new StatisticsPresenter();
    }

    @Override
    public StatisticsModel createModel() {
        return new StatisticsModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_statistics);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        tvRight.setText("按年");
        tabs.addTab(tabs.newTab().setText("用电"), true);//添加 Tab,默认选中
        tabs.addTab(tabs.newTab().setText("用水"), false);//添加 Tab,默认不选中

        tabs.setupWithViewPager(viewpager);

        f1 = new ElectroStatistcsFrgment();
        f2 = new WaterStatistcsFragment();

        list_Title = new ArrayList<>();
        list_Title.add("用电");
        list_Title.add("用水");
        list_fragment = new ArrayList<>();
        list_fragment.add(f1);
        list_fragment.add(f2);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment, list_Title);
        viewpager.setAdapter(adapter);

    }


    @OnClick({R.id.ll_back,R.id.ll_right,R.id.ll_buildnum})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_buildnum:
                startActivityForResult(new Intent(this, BuildNumAct.class),BUILDNUM);
                break;
            case R.id.ll_right:
                if (popwindow==null){
                    popwindow=new MenuPopwindow(this, new String[]{"按年查看", "按月查看"/*,"按日查看"*/}, new MenuPopwindow.MyOnClick() {
                        @Override
                        public void click(String s, int pos) {
                            if (pos==0){
                                DateType=1;
                                tvRight.setText("按年");
                                BottomYearTimeDialog yearTimeDialog = new BottomYearTimeDialog(StatisticsActivity.this, R.style.dialog_style);
                                yearTimeDialog.show();
                                yearTimeDialog.setListener2(new BottomYearTimeDialog.OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClickListener(int year, int i2, int i3, String i4, String i5) {
                                        time=year+"";
                                        if(f1!=null){
                                            f1.setType(year+"",DateType,bid);
                                        }
                                        if(f2!=null){
                                            f2.setType(year+"",DateType,bid);
                                        }
                                    }
                                });
                            }if (pos==1){
                                DateType=2;
                                tvRight.setText("按月");
                                BottomMonthTimeDialog monthTimeDialog = new BottomMonthTimeDialog(StatisticsActivity.this, R.style.dialog_style);
                                monthTimeDialog.show();
                                monthTimeDialog.setListener2(new BottomMonthTimeDialog.OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClickListener(int year, int month, int i3, String i4, String i5) {
                                        time=year+"-"+month;
                                        if(f1!=null){
                                            f1.setType(time+"",DateType,bid);
                                        }
                                        if(f2!=null){
                                            f2.setType(time+"",DateType,bid);
                                        }
                                    }
                                });
                            }else if (pos==2){
//                                type=0;
//                                tvRight.setText("按日");
//                                startActivityForResult(new Intent(StatisticsActivity.this,CalendarActivity.class),REQUEST_CODE);
                            }
                        }
                    });
                }
                popwindow.showPopupWindow(this,llRight);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==BUILDNUM){
                String buildname = data.getStringExtra("buildname");
                bid = data.getIntExtra("buildNum", 1);
                if(f1!=null){
                    f1.setType(time,DateType,bid);
                }
                if(f2!=null){
                    f2.setType(time,DateType,bid);
                }

            }
        }
    }
}
