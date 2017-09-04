package cn.lc.model.ui.main.activity.Library;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.BookPagerAdapter;
import cn.lc.model.ui.main.fragment.HottestFragment;
import cn.lc.model.ui.main.fragment.LatestFragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class LibraryActivity extends AppCompatActivity {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.iv_more_health_consult_search)
    ImageView ivMoreHealthConsultSearch;
    @BindView(R.id.view_down)
    View viewDown;
    @BindView(R.id.iv_big_pic)
    ImageView ivBigPic;
    @BindView(R.id.tab_book)
    TabLayout tabBook;
    @BindView(R.id.vp_book)
    ViewPager vpBook;
    @BindView(R.id.civ_recommend)
    CircleImageView civRecommend;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new LatestFragment());
        fragments.add(new HottestFragment());
        initTitle();
        initPager();
    }

    private void initPager() {
        BookPagerAdapter pagerAdapter = new BookPagerAdapter(getSupportFragmentManager());
        vpBook.setAdapter(pagerAdapter);
        tabBook.setupWithViewPager(vpBook);
        pagerAdapter.setFragments(fragments);
    }

    private void initTitle() {
        titleBar.setTitle("图书借阅");
        titleBar.setBack(true);
    }

    @OnClick(R.id.civ_recommend)
    public void onViewClicked() {
        Intent intent = new Intent(this, ReaderRecommendActivity.class);
        startActivity(intent);
    }
}
