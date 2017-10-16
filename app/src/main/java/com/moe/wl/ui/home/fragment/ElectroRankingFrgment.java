package com.moe.wl.ui.home.fragment;

import android.os.Bundle;
import android.view.View;

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
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * 用电排行
 */
public class ElectroRankingFrgment extends BaseFragment<HomePageModel, HomePageView, HomePagePresenter> implements HomePageView{

    @BindView(R.id.mChart)
    ColumnChartView mChart;

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

    private void generateStackedData() {
        int numSubcolumns = 4;
        int numColumns = 8;
        // Column can have many stacked subcolumns, here I use 4 stacke subcolumn in each of 4 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                values.add(new SubcolumnValue((float) Math.random() * 20f + 5, ChartUtils.pickColor()));
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
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        mChart.setColumnChartData(data);
    }

}
