package cn.lc.model.ui.main.activity.complain;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ComplainHistoryAdapter;
import cn.lc.model.ui.main.bean.ComplainHistoryBean;
import cn.lc.model.ui.main.model.ComplainHistoryModel;
import cn.lc.model.ui.main.modelimpl.ComplainHistoryModelImpl;
import cn.lc.model.ui.main.presenter.ComplainHistoryPresenter;
import cn.lc.model.ui.main.view.ComplainHistoryView;

/**
 * 类描述：反馈历史页面
 * 作者：Shixhe On 2017/9/6 0006
 */
public class ComplainHistoryActivity extends BaseActivity<ComplainHistoryModel, ComplainHistoryView, ComplainHistoryPresenter> implements ComplainHistoryView {

    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    private ComplainHistoryAdapter adapter;
    private List<ComplainHistoryBean.PageEntity.ListEntity> data;
    private int page = 1;
    private int pageCount = 10;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain_history);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        titleBar.setBack(true);
        titleBar.setTitle("反馈历史");
        getPresenter().getComplainHistoryData(page, pageCount);

        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getComplainHistoryData(page, pageCount);
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getComplainHistoryData(page, pageCount);
            }
        });
    }

    @Override
    public void getComplainHistorySucc(ComplainHistoryBean bean) {
        if (bean == null || bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
        if (page == 1) {
            data.clear();
            recycleView.refreshComplete();
        } else {
            recycleView.loadMoreComplete();
        }
        data.addAll(bean.getPage().getList());
        if (adapter == null) {
            adapter = new ComplainHistoryAdapter(this, data);
            recycleView.setLayoutManager(new LinearLayoutManager(this));
            recycleView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new ComplainHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ComplainHistoryActivity.this, ComplainHistoryDetailActivity.class);
                intent.putExtra("ID", data.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public ComplainHistoryModel createModel() {
        return new ComplainHistoryModelImpl();
    }

    @Override
    public ComplainHistoryPresenter createPresenter() {
        return new ComplainHistoryPresenter();
    }
}
