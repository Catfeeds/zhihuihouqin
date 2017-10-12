package com.moe.wl.ui.main.activity.EnergyAndemissions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.widget.SimpleImageBanner;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.framework.widget.bean.BannerItem;
import com.moe.wl.ui.main.adapter.ConsumptionAdapter;
import com.moe.wl.ui.main.adapter.EnergySavingHomeRvAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.LogUtil;

public class EnergySavingActivity extends AppCompatActivity {

    @BindView(R.id.dry_cleaners_title)
    TitleBar title;
    @BindView(R.id.h_banner_viewPager)
    SimpleImageBanner sib;
    @BindView(R.id.gv_consumption)
    GridView gvConsumption;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.rv_recommended)
    RecyclerView rvRecommended;
    @BindView(R.id.activity_energy_saving)
    LinearLayout activityEnergySaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_saving);
        ButterKnife.bind(this);
        initTitle();
//        initBanner();
        initGrid();
        initRecycler();
    }

    private void initRecycler() {
        rvRecommended.setLayoutManager(new LinearLayoutManager(this));
        EnergySavingHomeRvAdapter rvAdapter=new EnergySavingHomeRvAdapter(this);
        rvRecommended.setAdapter(rvAdapter);
    }

    private void initGrid() {
        ConsumptionAdapter consumptionAdapter=new ConsumptionAdapter(this);
        gvConsumption.setAdapter(consumptionAdapter);
        gvConsumption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("节能减排");
    }

    @OnClick(R.id.ll_more)
    public void onViewClicked() {

    }
    private void initBanner(String[] topPhotos) {
        if (topPhotos.length > 0) {
            ArrayList<BannerItem> list = new ArrayList<>();
            for (int i = 0; i < topPhotos.length; i++) {
                BannerItem item = new BannerItem();
                item.imgUrl = topPhotos[i];
                LogUtil.log(item.imgUrl);
                list.add(item);
            }
            sib
                    .setSource(list)
                    .startScroll();
        } else {
           /* sib
                    .setSource(DataProvider.getList())
                    .startScroll();*/
        }
        sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
//                showToast("position--->" + position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        sib.computeScroll();

    }

    @Override
    public void onPause() {
        super.onPause();
        sib.pauseScroll();

    }
}
