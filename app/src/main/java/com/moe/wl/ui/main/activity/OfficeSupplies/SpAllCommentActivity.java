package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.SpCommentTopAdapter;
import com.moe.wl.ui.main.bean.SpAllCommentCountBean;
import com.moe.wl.ui.main.fragment.SpCommentFragment;
import com.moe.wl.ui.main.model.SpCommentCountModel;
import com.moe.wl.ui.main.modelimpl.SpCommentCountModelImpl;
import com.moe.wl.ui.main.presenter.SpCommentCountPresenter;
import com.moe.wl.ui.main.view.SpCommentCountView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;

public class SpAllCommentActivity extends BaseActivity<SpCommentCountModel, SpCommentCountView, SpCommentCountPresenter> implements SpCommentCountView {


    @BindView(R.id.all_sp_comment_title)
    TitleBar allSpCommentTitle;
    @BindView(R.id.tab)
    RecyclerView tab;
    @BindView(R.id.fragment_container)
    FrameLayout container;
    @BindView(R.id.activity_sp_all_comment)
    LinearLayout activitySpAllComment;

    private List<String> num = new ArrayList<>();
    private List<String> names;
    private List<Fragment> fragments;
    private SpCommentTopAdapter topAdapter;
    private int id;

    @Override
    public SpCommentCountPresenter createPresenter() {
        return new SpCommentCountPresenter();
    }

    @Override
    public SpCommentCountModel createModel() {
        return new SpCommentCountModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_sp_all_comment);
        ButterKnife.bind(this);
    }

    public void initView() {
        names = Arrays.asList("全部评价", "好评", "中评", "差评");
        id = getIntent().getIntExtra("id", -1);
        initTitle();
        initTopView();
        fragments = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if(i==0){
                fragments.add(SpCommentFragment.getInstance(null,id+""));

            }else{
                fragments.add(SpCommentFragment.getInstance(i+"",id+""));

            }
        }
        getPresenter().getData(id+"");
        if(fragments!=null){
            switchFragment(fragments.get(0));
        }
    }


    private void initTopView() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        tab.setLayoutManager(manager);
        topAdapter = new SpCommentTopAdapter(this);
        tab.setAdapter(topAdapter);
    }

    private void initTitle() {
        allSpCommentTitle.setBack(true);
        allSpCommentTitle.setTitle("全部评论");
    }

    @Override
    public void getCommentSucc(SpAllCommentCountBean bean) {
        LogUtils.i("bean========="+bean);
        if (bean != null) {
            int total = bean.getTotal();
            num.add(total + "");
            num.add(bean.getGoodNum()+"");
            num.add(bean.getMiddleNum()+"");
            num.add(bean.getBadNum()+"");
            topAdapter.setData(names, num);
        }
        topAdapter.setListener(new SpCommentTopAdapter.OnClickListener() {
            @Override
            public void onItemClickListener(int index) {
                //根据索引切换fragmeng
                for (int i = 0; i < fragments.size(); i++) {
                    if (i == index)
                        switchFragment(fragments.get(i));
                }
            }
        });

    }

    private void switchFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
