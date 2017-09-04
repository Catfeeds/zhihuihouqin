package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.HealthServiceRvAdapter;

public class MoreHealthConsultActivity extends AppCompatActivity {

    @BindView(R.id.rv_more_health_consult)
    RecyclerView recyclerView;
    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.iv_more_health_consult_search)
    ImageView ivMoreHealthConsultSearch;
    private HealthServiceRvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_health_consult);
        ButterKnife.bind(this);
        initTitle();
        initRecycler();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("健康咨询");
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new HealthServiceRvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
    }

    @OnClick(R.id.iv_more_health_consult_search)
    public void onViewClicked() {
        Intent intent = new Intent(this, HealthSearchActivity.class);
    }
}
