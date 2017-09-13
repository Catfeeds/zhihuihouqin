package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.ui.main.adapter.BookRvAdapter;
import cn.lc.model.ui.main.bean.BooklistBean;
import cn.lc.model.ui.main.bean.SearchBookListBean;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class BookPutAwayFragment extends Fragment {

    @BindView(R.id.rv_book_list)
    RecyclerView rvBookList;
    Unbinder unbinder;
    private BookRvAdapter bookRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater from = LayoutInflater.from(container.getContext());
        View view = from.inflate(R.layout.book_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    /* @Override
     public void setContentLayout(Bundle savedInstanceState) {
         setContentView(R.layout.book_list_fragment);
     }*/
    public static BookPutAwayFragment getInstant(SearchBookListBean bookList) {
        BookPutAwayFragment bookPutAwayFragment = new BookPutAwayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BooklistBean", bookList);
        bookPutAwayFragment.setArguments(bundle);
        return bookPutAwayFragment;
    }

    public void initView() {
        rvBookList.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookRvAdapter = new BookRvAdapter(getActivity());
        rvBookList.setAdapter(bookRvAdapter);
        Bundle bundle = getArguments();
        Log.e("bundle", "唯恐吗");
        if (bundle != null) {
            SearchBookListBean booklistBean = (SearchBookListBean) bundle.getSerializable("BooklistBean");
            List<BooklistBean> booklist = booklistBean.getBooklist();
            if (booklist != null && booklist.size() > 0) {
                bookRvAdapter.setData(booklist);
                Log.e("boolist的数量:",booklist.size()+"");
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

  /*  @Override
    public void getBookListSucc(SearchBookListBean booklistBean) {
        if(booklistBean!=null){
            bookRvAdapter.setData(booklistBean.getBooklist());
            ((BookSearchActivity) getActivity()).tab.setVisibility(View.VISIBLE);
            ((BookSearchActivity) getActivity()).vpBook.setVisibility(View.VISIBLE);
            ((BookSearchActivity) getActivity()).tvCategory.setVisibility(View.GONE);
            ((BookSearchActivity) getActivity()).gvCategory.setVisibility(View.GONE);
        }else{
            ((BookSearchActivity) getActivity()).tab.setVisibility(View.GONE);
            ((BookSearchActivity) getActivity()).vpBook.setVisibility(View.GONE);
            ((BookSearchActivity) getActivity()).tvCategory.setVisibility(View.VISIBLE);
            ((BookSearchActivity) getActivity()).gvCategory.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public BookUpAwayModel createModel() {
        return new BookUpAwayModelImpl();
    }

    @Override
    public BookUpAwayPresenter createPresenter() {
        return new BookUpAwayPresenter();
    }*/
}
