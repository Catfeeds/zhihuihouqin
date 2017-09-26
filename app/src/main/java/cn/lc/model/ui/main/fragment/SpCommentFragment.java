package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.adapter.OfficeSpCommentAdapter;
import cn.lc.model.ui.main.bean.SpAllCommentBean;
import cn.lc.model.ui.main.model.SpAllCommentModel;
import cn.lc.model.ui.main.modelimpl.SpAllCommentModelImpl;
import cn.lc.model.ui.main.presenter.SpAllCommentPresenter;
import cn.lc.model.ui.main.view.SpAllCommentView;

/**
 * Created by 我的电脑 on 2017/9/19 0019.
 */

public class SpCommentFragment extends BaseFragment<SpAllCommentModel, SpAllCommentView, SpAllCommentPresenter> implements
        SpAllCommentView {

    @BindView(R.id.sp_comment_content)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private String type;
    private String id;
    private int limit = 10;
    private int page = 1;
    private boolean isRefresh;
    private OfficeSpCommentAdapter commentAdapter;
    List<SpAllCommentBean.PageBean.ListBean> mList=new ArrayList<>();

    @Override
    public SpAllCommentPresenter createPresenter() {
        return new SpAllCommentPresenter();
    }

    @Override
    public SpAllCommentModel createModel() {
        return new SpAllCommentModelImpl();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_all_comment);
    }

    @Override
    public void initView(View v) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            id = bundle.getString("id");

        }
        initRecycler();
        getPresenter().getAllComment(id, type, page + "", limit + "");
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentAdapter = new OfficeSpCommentAdapter(getActivity());
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                isRefresh = true;
                page=1;
                getPresenter().getAllComment(id,type,page+"",limit+"");
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                isRefresh=false;
                page++;
                getPresenter().getAllComment(id,type,page+"",limit+"");
                recyclerView.loadMoreComplete();
            }
        });
    }

    public static SpCommentFragment getInstance(String type, String id) {
        SpCommentFragment fragment = new SpCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getcommentSucc(SpAllCommentBean bean) {
        if(bean!=null){
            List<SpAllCommentBean.PageBean.ListBean> list = bean.getPage().getList();
            if(isRefresh==true){
                mList.clear();
            }
            mList.addAll(list);
            commentAdapter.setDate(mList);
        }
    }

}
