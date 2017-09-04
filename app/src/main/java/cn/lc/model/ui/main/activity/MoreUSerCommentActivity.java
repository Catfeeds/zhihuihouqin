package cn.lc.model.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;

public class MoreUSerCommentActivity extends AppCompatActivity {

    @BindView(R.id.more_user_comment_title)
    TitleBar titleBar;
    @BindView(R.id.rv_more_user_comment)
    RecyclerView recyclerView;
    private DoctorDetailrvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_user_comment);
        ButterKnife.bind(this);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new DoctorDetailrvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("更多评论");
    }
}
