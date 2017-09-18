package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.adapter.BookRvAdapter;
import cn.lc.model.ui.main.bean.SearchBookListBean;
import cn.lc.model.ui.main.model.BookUpAwayModel;
import cn.lc.model.ui.main.modelimpl.BookUpAwayModelImpl;
import cn.lc.model.ui.main.presenter.BookUpAwayPresenter;
import cn.lc.model.ui.main.view.BookUpAwayView;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class HotBookOrderFragment extends BaseFragment<BookUpAwayModel,BookUpAwayView,BookUpAwayPresenter> implements BookUpAwayView {

    @BindView(R.id.rv_book_list)
    RecyclerView rvBookList;
    Unbinder unbinder;
    private BookRvAdapter bookRvAdapter;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.book_list_fragment);
    }
    public static HotBookOrderFragment getInstant(String s,String s1,String s2){
        HotBookOrderFragment f = new HotBookOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("order",s);
        bundle.putString("typeid",s1);
        bundle.putString("keyword",s2);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public void initView(View v) {
        Bundle bundle = getArguments();
        if(bundle!=null){
            String order = bundle.getString("order");
            String typeid = bundle.getString("typeid");
            String keyword = bundle.getString("keyword");
            getPresenter().getData(order,typeid,keyword);
        }
        rvBookList.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookRvAdapter = new BookRvAdapter(getActivity());
        rvBookList.setAdapter(bookRvAdapter);
    }

    @Override
    public void getBookListSucc(SearchBookListBean booklistBean) {
        if(booklistBean!=null){
            bookRvAdapter.setData(booklistBean.getBooklist(),false);
        }
    }

    @Override
    public BookUpAwayModel createModel() {
        return new BookUpAwayModelImpl();
    }

    @Override
    public BookUpAwayPresenter createPresenter() {
        return new BookUpAwayPresenter();
    }
}
