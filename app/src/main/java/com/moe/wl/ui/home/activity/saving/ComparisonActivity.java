package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.saving.EnergyCostCompare;
import com.moe.wl.ui.home.model.saving.ComparisonModel;
import com.moe.wl.ui.home.modelimpl.saving.ComparisonModelImpl;
import com.moe.wl.ui.home.presenter.saving.ComparisonPresenter;
import com.moe.wl.ui.home.view.saving.ComparisonView;
import com.moe.wl.ui.home.view.saving.EnergyTypePop;
import com.moe.wl.ui.home.view.saving.MenuPopwindow;
import com.moe.wl.ui.main.bean.BuildNumList;
import com.moe.wl.ui.mywidget.BottomMonthTimeDialog;
import com.moe.wl.ui.mywidget.BottomYearTimeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 消耗对比
 */
public class ComparisonActivity extends BaseActivity<ComparisonModel, ComparisonView, ComparisonPresenter> implements ComparisonView, View.OnClickListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.lineChart)
    LineChartView lineChart;
    @BindView(R.id.columnChartc)
    ColumnChartView columnChartc;
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.bt_end)
    Button btEnd;
    @BindView(R.id.tv_energy_type)
    TextView tvEnergyType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.tv_build_one)
    TextView tvBuildOne;
    @BindView(R.id.tv_build_two)
    TextView tvBuildTwo;
    @BindView(R.id.ll_build)
    LinearLayout llBuild;

    private List<String> spinner1List;
    private ArrayAdapter spinner1Adapter;
    private List<String> spinner2List;
    private ArrayAdapter spinner2Adapter;
    private MenuPopwindow popwindow;
    private int REQUEST_CODE = 1000;
    private List<Integer> buildIdList;
    private int leftId;
    private int rightId;
    private int energyType = 1;//能源类型
    private String time;
    private int DateType = 1;
    private EnergyTypePop energypopwindow;
    private int[] buildArr;
    private String firstBuild;
    private String twoBuild;


    @Override
    public ComparisonPresenter createPresenter() {
        return new ComparisonPresenter();
    }

    @Override
    public ComparisonModel createModel() {
        return new ComparisonModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_comparison);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        tvRight.setText("按年");
        //获取楼号
        getPresenter().getBuildNum();
        //楼号数组
        buildArr = new int[2];
        buildIdList = new ArrayList<>();
        spinner1List = new ArrayList<>();
        spinner2List = new ArrayList<>();
        spinner1Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner1List);
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinner1Adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leftId = buildIdList.get(position);
                firstBuild = spinner1List.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner2List);
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinner2Adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rightId = buildIdList.get(position);
                twoBuild = spinner2List.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //处理数据的对比
    private void generateData(EnergyCostCompare bean) {
        List<AxisValue> mAxisXValues = new ArrayList<>();
        List<EnergyCostCompare.PageBean.ListBean> list;
        List<EnergyCostCompare.PageBean> page = bean.getPage();
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < page.size(); i++) {
            EnergyCostCompare.PageBean pageBean = page.get(i);
            List<PointValue> mPointValues = new ArrayList<>();
            if (pageBean != null) {
                list = pageBean.getList();
                mAxisXValues.clear();
                if(list.size()==0||list==null){
                    lineChart.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                }else{
                    lineChart.setVisibility(View.VISIBLE);
                    tvNoData.setVisibility(View.GONE);
                }
                for (int j = 0; j < list.size(); j++) {
                    EnergyCostCompare.PageBean.ListBean listBean = list.get(j);
                    double energyCost = listBean.getEnergyCost();
                    mPointValues.add(new PointValue(j, ((float) energyCost)));
                    mAxisXValues.add(new AxisValue(j).setLabel(j+""));
                }
            }
            Line line = new Line(mPointValues).setColor(ChartUtils.COLORS[i]);  //折线的颜色（橙色）
            line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
            line.setCubic(false);//曲线是否平滑，即是曲线还是折线
            line.setFilled(false);//是否填充曲线的面积
            line.setHasLabels(true);//曲线的数据坐标是否加上备注
            line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
            line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
            line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
            lines.add(line);

        }
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        axisX.setName("时间");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("Wh/m2");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
    }
    @OnClick({R.id.ll_back, R.id.ll_type, R.id.ll_right, R.id.bt_end})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_type:
                if (energypopwindow == null) {
                    energypopwindow = new EnergyTypePop(this, new String[]{"电耗", "水耗"}, new MenuPopwindow.MyOnClick() {
                        @Override
                        public void click(String s, int pos) {
                            if (pos == 0) {
                                tvEnergyType.setText("电耗");
                                energyType = 1;
                            }
                            if (pos == 1) {
                                tvEnergyType.setText("水耗");
                                energyType = 2;
                            }
                        }
                    });
                }
                energypopwindow.showPopupWindow(this, llType);
                break;
            case R.id.ll_right:
                if (popwindow == null) {
                    popwindow = new MenuPopwindow(this, new String[]{"按年查看", "按月查看",/* "按日查看"*/}, new MenuPopwindow.MyOnClick() {
                        @Override
                        public void click(String s, int pos) {
                            if (pos == 0) {
                                DateType = 1;
                                tvRight.setText("按年");
                                BottomYearTimeDialog yearTimeDialog = new BottomYearTimeDialog(ComparisonActivity.this, R.style.dialog_style);
                                yearTimeDialog.show();
                                yearTimeDialog.setListener2(new BottomYearTimeDialog.OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClickListener(int year, int i2, int i3, String i4, String i5) {
                                        time = year + "";
                                    }
                                });
                            }
                            if (pos == 1) {
                                DateType = 2;
                                tvRight.setText("按月");
                                BottomMonthTimeDialog monthTimeDialog = new BottomMonthTimeDialog(ComparisonActivity.this, R.style.dialog_style);
                                monthTimeDialog.show();
                                monthTimeDialog.setListener2(new BottomMonthTimeDialog.OnConfirmClickListener() {
                                    @Override
                                    public void onConfirmClickListener(int year, int month, int i3, String i4, String i5) {
                                        time = year + "-" + month;
                                    }
                                });
                            } else if (pos == 2) {
//                                tvRight.setText("按日");
//                                startActivityForResult(new Intent(ComparisonActivity.this, CalendarActivity.class), REQUEST_CODE);
                            }
                        }
                    });
                }
                popwindow.showPopupWindow(this, llRight);
                break;
            case R.id.bt_end:
                //  根据选择查询数据
                if(TextUtils.isEmpty(time)){
                    showToast("请选择对比的日期");
                    return;
                }
                if (leftId == 0 || rightId == 0) {
                    showToast("请选择对比的楼号");
                    return;
                }
                tvBuildOne.setText(firstBuild);
                tvBuildTwo.setText(twoBuild);
                llBuild.setVisibility(View.VISIBLE);
                buildArr[0] = leftId;
                buildArr[1] = rightId;
                //请求对比数据
                getPresenter().getInfo(DateType, energyType, buildArr, time);
                break;
        }
    }


    @Override
    public void setData() {
    }

    @Override
    public void getBuildNum(BuildNumList bean) {
        List<BuildNumList.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            BuildNumList.DataBean dataBean = data.get(i);
            String buildName = dataBean.getBuildName();
            int id = dataBean.getId();
            buildIdList.add(id);
            spinner1List.add(buildName);
            spinner2List.add(buildName);

        }
        spinner1Adapter.notifyDataSetChanged();
        spinner2Adapter.notifyDataSetChanged();
    }

    @Override
    public void getInfo(EnergyCostCompare bean) {
        generateData(bean);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            String date = data.getStringExtra("date");
            showToast(date);
        }
    }
}
