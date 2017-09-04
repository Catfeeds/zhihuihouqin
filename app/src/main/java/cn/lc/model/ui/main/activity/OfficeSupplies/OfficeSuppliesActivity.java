package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.HomeAdapter;
import cn.lc.model.ui.main.adapter.OfficeSpAdapter;

public class OfficeSuppliesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.office_supplies_title)
    TitleBar titleBar;
    @BindView(R.id.tv_need)
    TextView tvNeed;
    @BindView(R.id.iv_office_big_pic)
    ImageView ivOfficeBigPic;
    @BindView(R.id.nsgv_office)
    NoSlidingGridView nsgvOffice;
    @BindView(R.id.nsgv_sp)
    NoSlidingGridView nsgvSp;

    private String[] des = {"文件储存", "电脑耗材", "纸制品", "财务用品",
            "生活用品", "办公用品", "桌面用品", "书写工具"};
    // TODO: 2017/8/29 0029 图片需要更换
    private int[] photos = {R.drawable.health_service,
            R.drawable.property_maintenance,
            R.drawable.library,
            R.drawable.enrollment,
            R.drawable.reserva_haircut,
            R.drawable.dry_cleaners,
            R.drawable.office_supplies,
            R.drawable.more};
    private HomeAdapter homeAdapter;
    private OfficeSpAdapter officeSpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_supplies);
        ButterKnife.bind(this);
        initTitle();
        initnsgvOffice();
        initNsgvSp();
    }

    private void initNsgvSp() {
        officeSpAdapter = new OfficeSpAdapter(this);
        nsgvSp.setAdapter(officeSpAdapter);
    }

    private void initnsgvOffice() {
        homeAdapter = new HomeAdapter(this, des, photos);
        nsgvOffice.setAdapter(homeAdapter);
        nsgvOffice.setOnItemClickListener(this);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("办公用品");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,OfficeCategoryActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
