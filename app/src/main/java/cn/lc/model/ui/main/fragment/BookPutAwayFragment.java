package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.adapter.BookRvAdapter;
import cn.lc.model.ui.main.bean.BooklistBean;
import cn.lc.model.ui.main.bean.SearchBookListBean;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class BookPutAwayFragment extends BaseFragment2 {

    @BindView(R.id.rv_book_list)
    RecyclerView rvBookList;
    Unbinder unbinder;
    private BookRvAdapter bookRvAdapter;

    @Override
    public View setLayout() {
        View view = View.inflate(getActivity(), R.layout.book_list_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    public static BookPutAwayFragment getInstant(String s, int typeId, int i) {
        BookPutAwayFragment bookPutAwayFragment = new BookPutAwayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", s);
        bundle.putInt("typeId", typeId);
        bundle.putInt("order", i);
        bookPutAwayFragment.setArguments(bundle);
        return bookPutAwayFragment;
    }
    @Override
    public void initView() {
        rvBookList.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookRvAdapter = new BookRvAdapter(getActivity());
        rvBookList.setAdapter(bookRvAdapter);
        Bundle bundle = getArguments();
        Log.e("bundle", "唯恐吗");

        if (bundle != null) {
            String keyword = bundle.getString("keyword");
            int typeId = bundle.getInt("typeId");
            int order = bundle.getInt("order");
            getData(keyword, typeId, order);

        }
    }
    private void getData(String keyword, int typeId, int order) {
        Observable observable = RetrofitUtils.getInstance().getSearchBookList(keyword, typeId + "", order + "");
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
                if(bookListBean.getErrCode()==0){
                    List<BooklistBean> booklist = bookListBean.getBooklist();
                    if(booklist!=null){
                        bookRvAdapter.setData(booklist,false);
                    }
                }else{
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
