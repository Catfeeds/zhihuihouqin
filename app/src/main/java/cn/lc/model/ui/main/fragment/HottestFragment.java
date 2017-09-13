package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.adapter.BookRvAdapter;
import cn.lc.model.ui.main.bean.BooklistBean;
import cn.lc.model.ui.main.bean.LibraryHomeBean;
import cn.lc.model.ui.main.model.LibraryHomeModel;
import cn.lc.model.ui.main.modelimpl.LibraryHomeModelImpl;
import cn.lc.model.ui.main.presenter.LibraryHomePresenter;
import cn.lc.model.ui.main.view.LibraryHomeView;

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
    @Override
    public void getLibraryHomeSucc(LibraryHomeBean homeBean) {
        if(homeBean!=null){
            List<BooklistBean> booklist = homeBean.getBooklist();
            bookRvAdapter.setData(booklist);
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
