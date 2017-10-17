package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.saving.ComparisonModel;
import com.moe.wl.ui.home.modelimpl.saving.ComparisonModelImpl;
import com.moe.wl.ui.home.presenter.saving.ComparisonPresenter;
import com.moe.wl.ui.home.view.saving.ComparisonView;
import com.moe.wl.ui.home.view.saving.MenuPopwindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
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

    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = false;


    private LineChartData data;

    private List<String> spinner1List;
    private ArrayAdapter spinner1Adapter;
    private List<String> spinner2List;
    private ArrayAdapter spinner2Adapter;
    private MenuPopwindow popwindow;
    private int REQUEST_CODE=1000;

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

        // Generate some random values.
        generateValues();

        generateData();
        lineChart.setViewportCalculationEnabled(false);

        addLineToData();

        columnChartc.setColumnChartData(generateColumnChartData());
        columnChartc.setZoomType(ZoomType.HORIZONTAL);

        columnChartc.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

        spinner1List = new ArrayList<String>();
        spinner1List.add("北京");
        spinner1List.add("上海");
        spinner1List.add("广州");
        spinner1List.add("深圳");

        spinner2List = new ArrayList<String>();
        spinner2List.add("北京");
        spinner2List.add("上海");
        spinner2List.add("广州");
        spinner2List.add("深圳");

        spinner1Adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1List);
        spinner1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinner1Adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2Adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2List);
        spinner2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(spinner2Adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }

    private void addLineToData() {
        if (data.getLines().size() >= maxNumberOfLines) {
            Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ++numberOfLines;
        }

        generateData();
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
                values.add(new SubcolumnValue((float) Math.random() * 50f + 5, ChartUtils.pickColor()));
            }

            columns.add(new Column(values));
        }

        ColumnChartData data = new ColumnChartData(columns);

        data.setAxisXBottom(new Axis().setName("时间"));
        data.setAxisYLeft(new Axis().setName("wh/㎡").setHasLines(true));
        return data;

    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("时间");
                axisY.setName("wh/㎡");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        lineChart.setLineChartData(data);

    }


    @OnClick({R.id.ll_back,R.id.ll_right,R.id.bt_end})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                if (popwindow==null){
                    popwindow=new MenuPopwindow(this, new String[]{"按年查看", "按月查看","按日查看"}, new MenuPopwindow.MyOnClick() {
                        @Override
                        public void click(String s, int pos) {
                            tvRight.setText(s);
                            if (pos==2){
                                startActivityForResult(new Intent(ComparisonActivity.this,CalendarActivity.class),REQUEST_CODE);
                            }
                        }
                    });
                }
                popwindow.showPopupWindow(this,llRight);
                break;
            case R.id.bt_end:

                break;
        }
    }


    @Override
    public void setData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE && data!=null){
           String date=data.getStringExtra("date");
            showToast(date);
        }
    }
}
