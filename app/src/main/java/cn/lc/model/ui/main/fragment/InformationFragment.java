package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.adapter.HomeNsrlv1Adapter;
import cn.lc.model.ui.main.bean.InformationBean;
import cn.lc.model.ui.main.model.InformationModel;
import cn.lc.model.ui.main.modelimpl.InformationModelImpl;
import cn.lc.model.ui.main.presenter.InformationPresenter;
import cn.lc.model.ui.main.view.InformationView;

/**
 * 类描述：今日菜谱Fragment（早餐 午餐 晚餐）
 * 作者：Shixhe On 2017/9/5 0005
 */

public class InformationFragment extends BaseFragment<InformationModel, InformationView, InformationPresenter> implements InformationView {

    @BindView(R.id.recycleView)
    XRecyclerView recycleView;

    private List<InformationBean.PageEntity.ListEntity> data;
    private HomeNsrlv1Adapter adapter;

    private int page = 1;

    private boolean isRecommend = false;
    private int typeID = 0;
    private String keyword;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_information);
    }

    @Override
    public void initView(View v) {
        typeID = getArguments().getInt("typeID", 0);
        keyword = getArguments().getString("keyWord", "");
        isRecommend = getArguments().getBoolean("isRecommend", false);

        data = new ArrayList<>();
        adapter = new HomeNsrlv1Adapter(getActivity(), data);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(adapter);
        getData();
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();
            }
        });
    }

    private void getData() {
        if (isRecommend) {
            getPresenter().getInformation(0, 1, keyword, page);
        } else {
            getPresenter().getInformation(typeID, 0, keyword, page);
        }
    }

    @Override
    public void getInformationSucc(InformationBean bean) {
        LogUtils.d("1");
        if (bean == null || bean.getPage() == null || bean.getPage().getList() == null)
            return;
        LogUtils.d("2");
        if (bean.getPage().getTotalPage() <= page) {
            LogUtils.d("3");
            recycleView.setLoadingMoreEnabled(false);
        } else {
            LogUtils.d("4");
            recycleView.setLoadingMoreEnabled(true);
        }
        if (page == 1) {
            LogUtils.d("5");
            data.clear();
            recycleView.refreshComplete();
        } else {
            LogUtils.d("6");
            recycleView.loadMoreComplete();
        }
        data.addAll(bean.getPage().getList());
        LogUtils.d("7");
        adapter.notifyDataSetChanged();
    }

    @Override
    public InformationModel createModel() {
        return new InformationModelImpl();
    }

    @Override
    public InformationPresenter createPresenter() {
        return new InformationPresenter();
    }
}
