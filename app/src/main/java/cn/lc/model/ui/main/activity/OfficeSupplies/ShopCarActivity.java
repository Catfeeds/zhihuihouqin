package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ShopCarListAdapter;

public class ShopCarActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_store_select)
    ImageView ivStoreSelect;
    @BindView(R.id.store)
    TextView store;
    @BindView(R.id.iv_select_all)
    ImageView ivSelectAll;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.activity_shop_car)
    LinearLayout activityShopCar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.tv_clearing)
    TextView tvClearing;
    private boolean isEdit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        initTitle();
        edit();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShopCarListAdapter listAdapter = new ShopCarListAdapter(this);
        recyclerView.setAdapter(listAdapter);
    }

    private void edit() {
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEdit){
                    isEdit=true;
                    title.setTitleRight("完成");
                    tvSum.setText("删除");
                    ivSelectAll.setVisibility(View.VISIBLE);
                    ivStoreSelect.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("购物车");
        title.setTitleRight("编辑");
    }

    @OnClick({R.id.iv_store_select, R.id.iv_select_all, R.id.tv_sum,R.id.tv_clearing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_store_select:
                break;
            case R.id.iv_select_all:
                break;
            case R.id.tv_sum:
                break;
            case R.id.tv_clearing:
                if(!isEdit){
                    Intent intent = new Intent(this,OfficeSpConfirmOrderAct.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
