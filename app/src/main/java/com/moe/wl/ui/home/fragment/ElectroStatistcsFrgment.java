package com.moe.wl.ui.home.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.home.bean.EnergyCostQueryBean;
import com.moe.wl.ui.home.model.saving.ElectroModel;
import com.moe.wl.ui.home.modelimpl.saving.ElectroModelImpl;
import com.moe.wl.ui.home.presenter.saving.ElectroPresenter;
import com.moe.wl.ui.home.view.saving.ElectroView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
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
public class ElectroStatistcsFrgment extends BaseFragment<ElectroModel, ElectroView, ElectroPresenter> implements ElectroView, View.OnClickListener {

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
    @BindView(R.id.tv_address)
    TextView address;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_electro)
    TextView tvElectro;
    @BindView(R.id.tv_water)
    TextView tvWater;
//    @BindView(R.id.ll_buildnum)
//    LinearLayout llBuildNum;

    private int type;   //0柱形图  1饼状图
    private ColumnChartView columnChartView;
    private int dateType = 1;
    private int buildId=1;//默认楼id
    private String selectTime;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_statistcs);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);

        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        selectTime=curYear+"";
        columnChartView = new ColumnChartView(getActivity());
        columnChartView.setZoomType(ZoomType.HORIZONTAL);
        columnChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        rlColumnChart.addView(columnChartView);

        PieChartView pieChartView = new PieChartView(getActivity());
        pieChartView.setPieChartData(generatePieChartData());
        pieChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        rlPreChart.addView(pieChartView);
        btType.setOnClickListener(this);

        getPresenter().getDetail(curYear + "", dateType, 1, buildId);
        tvTitle.setText(curYear+"年" + "耗能统计（时间单位：" + "年" + ")");
    }


    @Override
    public ElectroModel createModel() {
        return new ElectroModelImpl();
    }

    @Override
    public ElectroPresenter createPresenter() {
        return new ElectroPresenter();
    }

    public void setType(String time, int unit, int bid) {
        this.dateType = unit;
        if(!TextUtils.isEmpty(time)){
            this.selectTime=time;
        }
        if(bid!=0){
            this.buildId=bid;
        }
        if (unit == 1) {
            tvTitle.setText(selectTime + "耗能统计（时间单位：" + "年" + ")");
        } else if (unit == 2) {
            tvTitle.setText(selectTime + "耗能统计（时间单位：" + "月" + ")");
        }
        getPresenter().getDetail(selectTime, unit, 1, buildId);
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

    private void generateColumnChartData(EnergyCostQueryBean bean) {
        List<EnergyCostQueryBean.EnergyListBean> energyList = bean.getEnergyList();
        int numSubcolumns = 1;
        int numColumns = 12;//列数
        if (dateType == 1) {
            numColumns = 12;
        } else if (dateType == 2) {
            numColumns = 24;
        }
        // Column can have many subcolumns, here by default I use 1 subcolumn in each of 8 columns.
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        ArrayList<AxisValue> axisValuesY = new ArrayList<AxisValue>();//定义Y轴刻度值的数据集合
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                if(energyList==null){
                    values.add(new SubcolumnValue(0, getResources().getColor(R.color.columnChart)));
                }
                if (energyList.size() > i) {
                    EnergyCostQueryBean.EnergyListBean energyListBean = energyList.get(i);
                    double energyCost = energyListBean.getEnergyCost();
                    values.add(new SubcolumnValue(((float) energyCost), getResources().getColor(R.color.columnChart)));
                }
            }

            columns.add(new Column(values));
        }
        //获取数据源中最大值
        double maxValue=energyList.get(0).getEnergyCost();
        for (int i = 0; i < energyList.size(); i++) {
            if(maxValue<energyList.get(i).getEnergyCost()){
                maxValue=energyList.get(i).getEnergyCost();
            }
        }
        //根据最大值添加到刻度集合当中
        for (int i = 0; i < maxValue+5; i++) {
            axisValuesY.add(new AxisValue(i).setValue(i));
        }
        ColumnChartData data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis());
        data.setAxisYLeft(new Axis(axisValuesY).setHasLines(true));
        columnChartView.setColumnChartData(data);
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
    public void getInfo(EnergyCostQueryBean bean) {
        if (bean != null) {
            generateColumnChartData(bean);
            EnergyCostQueryBean.EnergyTotalBean energyTotal = bean.getEnergyTotal();
            address.setText(energyTotal.getBuildName());
            tvDate.setText(energyTotal.getCreatTime());
            tvElectro.setText(energyTotal.getEnergyCost()+"");
            //tvWater.setText(water);
        }
    }
}
