package com.moe.wl.ui.main.activity.Library;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.SearchCategoryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.adapter.BookRvAdapter;
import com.moe.wl.ui.main.adapter.FmPagerAdapter;
import com.moe.wl.ui.main.bean.SearchBookListBean;
import com.moe.wl.ui.main.bean.SearchCategoryBean;
import com.moe.wl.ui.main.fragment.BookPutAwayFragment;
import rx.Observable;
import rx.Subscriber;

public class BookSearchActivity extends Base2Activity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.gv_category)
    GridView gvCategory;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_book)
    ViewPager vpBook;
    @BindView(R.id.ll_search_list)
    LinearLayout llSearchList;
    @BindView(R.id.activity_book_search)
    LinearLayout activityBookSearch;
    private SearchCategoryAdapter categoryAdapter;
    private BookRvAdapter bookRvAdapter;
    private List<Fragment> fragments;
    private FmPagerAdapter fmPagerAdapter;
    private List<String> tabs = Arrays.asList("上架时间", "热书排名", "好评排名");

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_book_search);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initGride();
        fragments = new ArrayList<>();
        getSearchCategory();
        fmPagerAdapter = new FmPagerAdapter(getSupportFragmentManager());
        vpBook.setAdapter(fmPagerAdapter);
        tab.setupWithViewPager(vpBook);
        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEND);
        setKeyListen();
    }

    @OnClick({R.id.iv_back, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_close:
                etSearch.setText("");
                break;
        }
    }


    //对软键盘回车键进行监听
    private void setKeyListen() {

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String search = etSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(search)) {
                        for (int i = 0; i < 3; i++) {
                            fragments.add(BookPutAwayFragment.getInstant(search,-1,i));
                        }
                        fmPagerAdapter.setFragments(fragments, tabs);
                    }
                }
                return true;
            }
        });
    }

    private void initGride() {
        categoryAdapter = new SearchCategoryAdapter(this);
        gvCategory.setAdapter(categoryAdapter);
    }

    //获取列别列表
    public void getSearchCategory() {
        Observable observable = RetrofitUtils.getInstance().getSearchCategory();
        showProgressDialog();
        observable.subscribe(new Subscriber<SearchCategoryBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("查询类别:", e.getMessage());
                dismissProgressDialog();
            }

            @Override
            public void onNext(SearchCategoryBean categoryBean) {
                if (categoryBean.getErrCode() == 0) {
                    getCategorySucc(categoryBean);
                } else {
                    showToast(categoryBean.getMsg());
                }
            }
        });
    }

    private void getCategorySucc(SearchCategoryBean categoryBean) {
        if (categoryBean != null) {
            final List<SearchCategoryBean.TypelistBean> typelist = categoryBean.getTypelist();
            categoryAdapter.setDatas(typelist);
            gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int typeId = typelist.get(position).getId();
                    llType.setVisibility(View.GONE);
                    llSearchList.setVisibility(View.VISIBLE);
                    for (int i = 0; i < 3; i++) {
                        fragments.add(BookPutAwayFragment.getInstant("",typeId,i));
                    }
                    fmPagerAdapter.setFragments(fragments, tabs);
                }
            });
        }
    }

 /*   private void getSearchFragmentList(Observable observable, final int i) {
        showProgressDialog();
        observable.subscribe(new Subscriber<SearchBookListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();

            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("获取fragment列表", e.getMessage());
            }

            @Override
            public void onNext(SearchBookListBean bookListBean) {
                Log.e("SearchBookListBean", "获取到了fragmeng" + i);
                if (bookListBean.getErrCode() == 0) {
                    if (i == 0) {
                        fragments.add(BookPutAwayFragment.getInstant(bookListBean));
                    } else if (i == 1) {
                        fragments.add(BookPutAwayFragment.getInstant(bookListBean));
                    } else if (i == 2) {
                        fragments.add(BookPutAwayFragment.getInstant(bookListBean));
                    }
                    llType.setVisibility(View.GONE);
                    llSearchList.setVisibility(View.VISIBLE);
                } else {
                    llType.setVisibility(View.VISIBLE);
                    llSearchList.setVisibility(View.GONE);
                }

            }
        });
    }*/


    private void getBookList(String s, String s1, String s2) {
        Observable observable = RetrofitUtils.getInstance().getSearchBookList(s, s1, s2);
        showProgressDialog();
        observable.subscribe(new Subscriber<SearchBookListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("获取查询书列表", e.getMessage());
                dismissProgressDialog();
            }

            @Override
            public void onNext(SearchBookListBean bookListBean) {
                if (bookListBean.getErrCode() == 0) {
                    if (bookListBean.getBooklist().size() > 0) {
                       llSearchList.setVisibility(View.VISIBLE);
                        gvCategory.setVisibility(View.GONE);

                    } else {
                        tab.setVisibility(View.GONE);
                        vpBook.setVisibility(View.GONE);
                        gvCategory.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
