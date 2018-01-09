package com.moe.wl.ui.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.bean.HomePageBean;
import com.moe.wl.ui.main.model.HomePageModel;
import com.moe.wl.ui.main.modelimpl.HomePageModelImpl;
import com.moe.wl.ui.main.presenter.HomePagePresenter;
import com.moe.wl.ui.main.view.HomePageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * 用电排行
 */
public class ElectroRankingFrgment extends BaseFragment<HomePageModel, HomePageView, HomePagePresenter> implements HomePageView {

    @BindView(R.id.mChart)
    ColumnChartView mChart;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_avg)
    TextView tvAvg;

    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private ColumnChartData data;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_ranking);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);

        generateStackedData();

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

    /**
     * 设置排行类型
     */
    public void setType(int type) {
        if (type == 0) {
            tvType.setText("楼总量排行");
            generateStackedData();
        }else if (type == 1) {
            tvType.setText("楼总单位面积排行");
            generateStackedData();
        }else if (type == 2) {
            tvType.setText("部门总量排行");
            generateStackedData();
        }else if (type == 3) {
            tvType.setText("部门单位人员排行");
            generateStackedData();
        }
    }

    private void generateStackedData() {
        int numSubcolumns = 4;
        int numColumns = 8;
        List<String> buildList= Arrays.asList("北楼","业务楼","居民楼","大食堂","南楼"
                ,"信访楼","礼堂","西楼");
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        List<AxisValue> axisXValues = new ArrayList<AxisValue>();
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 20f + 5, ChartUtils.rankColor()));
                //设置X轴的柱子所对应的属性名称
                axisXValues.add(new AxisValue(i).setLabel(buildList.get(i)));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }

        data = new ColumnChartData(columns);

        // Set stacked flag.
        data.setStacked(true);

        if (hasAxes) {
            //Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            //设置X轴显示在底部，并且显示每个属性的Lable，字体颜色为黑色，X轴的名字为“学历”，每个柱子的Lable斜着显示，距离X轴的距离为8
            data.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(getActivity().getResources().getColor(R.color.tv_black)).setName("").setHasTiltedLabels(true).setMaxLabelChars(8));
            //data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        mChart.setColumnChartData(data);
    }

}
