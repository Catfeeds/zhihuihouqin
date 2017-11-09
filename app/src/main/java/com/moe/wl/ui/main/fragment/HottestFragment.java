package com.moe.wl.ui.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.activity.Library.BookDescriptionActivity;
import com.moe.wl.ui.main.activity.Library.LibraryActivity;
import com.moe.wl.ui.main.adapter.BookRvAdapter;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.LibraryHomeBean;
import com.moe.wl.ui.main.model.LibraryHomeModel;
import com.moe.wl.ui.main.modelimpl.LibraryHomeModelImpl;
import com.moe.wl.ui.main.presenter.LibraryHomePresenter;
import com.moe.wl.ui.main.view.LibraryHomeView;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class HottestFragment extends BaseFragment<LibraryHomeModel, LibraryHomeView, LibraryHomePresenter> implements LibraryHomeView {
    @BindView(R.id.rv_book)
    RecyclerView rvBook;
    Unbinder unbinder;
    private BookRvAdapter bookRvAdapter;
    private LibraryActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (LibraryActivity) activity;
    }

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
        bookRvAdapter.setMyCallBack(new BookRvAdapter.MyCallBack() {
            @Override
            public void cb(BooklistBean bookListvBean, String BookID, boolean again) {
                Intent intent = new Intent(getActivity(), BookDescriptionActivity.class);
                intent.putExtra("content", bookListvBean.getBrief());
                intent.putExtra("bean", bookListvBean);
                intent.putExtra("again", again);
                if (activity.bookList != null && activity.bookList.size() > 0) {
                    for (int i = 0; i < activity.bookList.size(); i++) {
                        if (bookListvBean.getId().equals(activity.bookList.get(i).getId())) {
                            showToast("已经添加了此书");
                            return;
                        }
                    }
                    getActivity().startActivity(intent);
                    getActivity().finish();
                } else {
                    getActivity().startActivity(intent);
                }
            }
        });
    }

    public static HottestFragment getInstance(boolean again) {
        HottestFragment hottestFragment = new HottestFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("again", again);
        hottestFragment.setArguments(bundle);
        return hottestFragment;
    }

    @Override
    public void getLibraryHomeSucc(LibraryHomeBean homeBean) {
        if (homeBean != null) {
            List<BooklistBean> booklist = homeBean.getBooklist();
            boolean again = false;
            Bundle arguments = getArguments();
            if (arguments != null) {
                again = arguments.getBoolean("again");
            }
            bookRvAdapter.setData(booklist, again);
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
