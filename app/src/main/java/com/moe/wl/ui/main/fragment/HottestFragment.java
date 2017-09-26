package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moe.wl.ui.main.adapter.BookRvAdapter;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.LibraryHomeBean;
import com.moe.wl.ui.main.model.LibraryHomeModel;
import com.moe.wl.ui.main.modelimpl.LibraryHomeModelImpl;
import com.moe.wl.ui.main.view.LibraryHomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.presenter.LibraryHomePresenter;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class HottestFragment extends BaseFragment<LibraryHomeModel,LibraryHomeView,LibraryHomePresenter> implements  LibraryHomeView {
    @BindView(R.id.rv_book)
    RecyclerView rvBook;
    Unbinder unbinder;
    private BookRvAdapter bookRvAdapter;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_latest);
    }

    @Override
    public void initView(View v) {
        getPresenter().getData(2);
        rvBook.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookRvAdapter = new BookRvAdapter(getActivity());
        rvBook.setAdapter(bookRvAdapter);
    }
    public static HottestFragment getInstance(boolean again){
        HottestFragment hottestFragment = new HottestFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("again",again);
        hottestFragment.setArguments(bundle);
        return hottestFragment;
    }
    @Override
    public void getLibraryHomeSucc(LibraryHomeBean homeBean) {
        if(homeBean!=null){
            List<BooklistBean> booklist = homeBean.getBooklist();
            boolean again=false;
            Bundle arguments = getArguments();
            if(arguments!=null){
                 again = arguments.getBoolean("again");
            }
            bookRvAdapter.setData(booklist,again);
        }
    }

    @Override
    public LibraryHomePresenter createPresenter() {
        return new LibraryHomePresenter();
    }


    @Override
    public LibraryHomeModel createModel() {
        return new LibraryHomeModelImpl();
    }
}
