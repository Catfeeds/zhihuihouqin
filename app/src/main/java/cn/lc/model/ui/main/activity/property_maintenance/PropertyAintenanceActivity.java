package cn.lc.model.ui.main.activity.property_maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.BarberGridAdapter;

public class PropertyAintenanceActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.et_service_address)
    EditText etServiceAddress;
    @BindView(R.id.nsgv_service_type)
    NoSlidingGridView nsgvServiceType;
    @BindView(R.id.et_baoxiu_detail)
    EditText etBaoxiuDetail;
    @BindView(R.id.iv_up_photo)
    ImageView ivUpPhoto;
    @BindView(R.id.tv_now_posted)
    TextView tvNowPosted;
private List<String> types= Arrays.asList(
        "马桶疏通","水电维修","房屋维修",
        "开锁/换锁","线路维修","其他");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_aintenance);
        ButterKnife.bind(this);
        initTitle();
        initGrid();
    }

    private void initGrid() {
        BarberGridAdapter gridAdapter=new BarberGridAdapter();
        nsgvServiceType.setAdapter(gridAdapter);
        //gridAdapter.setTime(types);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("物业维修");
    }

    @OnClick({R.id.iv_select_time, R.id.tv_now_posted})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_select_time:
                Intent intent=new Intent(PropertyAintenanceActivity.this,OrderTimeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_now_posted:
                break;
        }
    }

}
