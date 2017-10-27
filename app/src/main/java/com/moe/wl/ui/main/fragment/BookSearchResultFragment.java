package com.moe.wl.ui.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.Library.BookDescriptionActivity;
import com.moe.wl.ui.main.adapter.BookRvAdapter;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.SearchBookListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class BookSearchResultFragment extends BaseFragment2 {

    @BindView(R.id.rv_book_list)
    RecyclerView rvBookList;
    Unbinder unbinder;
    private BookRvAdapter bookRvAdapter;

    private int order;
    private String keyword;
    private String typeId;

    @Override
    public View setLayout() {
        View view = View.inflate(getActivity(), R.layout.book_list_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static BookSearchResultFragment getInstant(String s, String typeId, int order) {
        BookSearchResultFragment bookPutAwayFragment = new BookSearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", s);
        bundle.putString("typeId", typeId);
        bundle.putInt("order", order);
//        if (order == 1) {
//            bundle.putInt("issearch", 1);
//        } else {
//            bundle.putInt("issearch", 0);
//        }
        bookPutAwayFragment.setArguments(bundle);
        return bookPutAwayFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            LogUtils.d("initView()方法！！");
            keyword = bundle.getString("keyword");
            typeId = bundle.getString("typeId");
            order = bundle.getInt("order");
        }
    }

    @Override
    public void initView() {
        rvBookList.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookRvAdapter = new BookRvAdapter(getActivity());
        rvBookList.setAdapter(bookRvAdapter);
        getData(keyword, typeId, order);

        bookRvAdapter.setMyCallBack(new BookRvAdapter.MyCallBack() {
            @Override
            public void cb(BooklistBean bookListvBean, String BookID, boolean again) {
                Intent intent = new Intent(getActivity(), BookDescriptionActivity.class);
                intent.putExtra("bean", bookListvBean);
                intent.putExtra("again", again);
                getActivity().startActivity(intent);
            }
        });
    }

    private void getData(String keyword, String typeId, int order) {
        Observable observable = RetrofitUtils.getInstance().searchBookResult(typeId, keyword, order, 0);
        showProgressDialog();
        observable.subscribe(new Subscriber<SearchBookListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i(e.getMessage());
            }

            @Override
            public void onNext(SearchBookListBean bookListBean) {
                if (bookListBean.getErrCode() == 0) {
                    List<BooklistBean> booklist = bookListBean.getBooklist();
                    if (booklist != null) {
                        bookRvAdapter.setData(booklist, false);
                    }
                } else {
                    LogUtils.i(bookListBean.getMsg());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
