package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.CategoryAdapter;
import cn.lc.model.ui.main.adapter.IndecatorAdapter;
import cn.lc.model.ui.main.adapter.OfficeSpAdapter;

public class OfficeCategoryActivity extends AppCompatActivity {

    @BindView(R.id.sp_category_title)
    TitleBar titleBar;
    @BindView(R.id.indector)
    RecyclerView indector;
    @BindView(R.id.category_list)
    RecyclerView categoryList;
    @BindView(R.id.sp_grid)
    GridView spGrid;
    @BindView(R.id.activity_office_category)
    LinearLayout activityOfficeCategory;
    private List<String> category= Arrays.asList(
            "文件储存","电脑耗材","纸制品","财务用品",
            "生活用品","办公用品","桌面用品","书写工具");
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_category);
        ButterKnife.bind(this);
        position = getIntent().getIntExtra("position", 0);
        initTitle();
        initIndecator();
        initCategory();
        intiSpList();
    }

    private void intiSpList() {
        OfficeSpAdapter officeSpAdapter = new OfficeSpAdapter(this);
        spGrid.setAdapter(officeSpAdapter);
    }

    private void initCategory() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        categoryList.setLayoutManager(manager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this,category);
        categoryList.setAdapter(categoryAdapter);
        categoryAdapter.setSelected(position);
        categoryAdapter.notifyDataSetChanged();
    }

    private void initIndecator() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        indector.setLayoutManager(manager);
        IndecatorAdapter indecatorAdapter = new IndecatorAdapter(this);
        indector.setAdapter(indecatorAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("商品分类");
    }
}
