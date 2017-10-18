package com.moe.wl.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.bean.HomePageBean;
import com.moe.wl.ui.main.model.HomePageModel;
import com.moe.wl.ui.main.modelimpl.HomePageModelImpl;
import com.moe.wl.ui.main.presenter.HomePagePresenter;
import com.moe.wl.ui.main.view.HomePageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 用电统计
 */
public class ElectroStatistcsFrgment extends BaseFragment<HomePageModel, HomePageView, HomePagePresenter> implements HomePageView, View.OnClickListener {

    @BindView(R.id.bt_type)
    Button btType;
    @BindView(R.id.rl_columnChart)
    RelativeLayout rlColumnChart;
    @BindView(R.id.rl_preChart)
    RelativeLayout rlPreChart;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private int type;   //0柱形图  1饼状图

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_statistcs);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);

        ColumnChartView columnChartView = new ColumnChartView(getActivity());
        columnChartView.setColumnChartData(generateColumnChartData());
        columnChartView.setZoomType(ZoomType.HORIZONTAL);

        columnChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        rlColumnChart.addView(columnChartView);


        PieChartView pieChartView = new PieChartView(getActivity());
        pieChartView.setPieChartData(generatePieChartData());

        pieChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        rlPreChart.addView(pieChartView);

        btType.setOnClickListener(this);

    }


    @Override
    public HomePageModel createModel() {
        return new HomePageModelImpl();
    }

    @Override
    public HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }

    @Override
    public void getHomePageSucc(HomePageBean bean) {

    }

    public void setType(String time,String unit) {
        if (type == 0){
            tvTitle.setText(time+"耗能统计（时间单位："+unit+")");
        }else{
            tvTitle.setText(time+"用能比例（时间单位："+unit+")");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_type://二维码扫描
                if (type == 1) {
                    type = 0;
                    btType.setText("用能比例");
                    tvUnit.setVisibility(View.VISIBLE);
                    rlColumnChart.setVisibility(View.VISIBLE);
                    rlPreChart.setVisibility(View.GONE);
                } else {
                    type = 1;
                    tvUnit.setVisibility(View.GONE);
                    btType.setText("耗能统计");
                    rlColumnChart.setVisibility(View.GONE);
                    rlPreChart.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private ColumnChartData generateColumnChartData() {
        int numSubcolumns = 1;
        int numColumns = 12;
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, getResources().getColor(R.color.columnChart)));
            }

            columns.add(new Column(values));
        }

        ColumnChartData data = new ColumnChartData(columns);

        data.setAxisXBottom(new Axis());
        data.setAxisYLeft(new Axis().setHasLines(true));
        return data;

    }

    private PieChartData generatePieChartData() {
        int numValues = 6;

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor()));
        }

        PieChartData data = new PieChartData(values);
        return data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
