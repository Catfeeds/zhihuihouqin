package com.moe.wl.ui.main.activity.message;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.SystemMessageAdapter;
import com.moe.wl.ui.main.bean.MessageListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.moe.wl.R;

/**
 * 类描述：系统消息
 * 作者：Shixhe On 2017/9/15 0015
 */
public class SystemMessageActivity extends MessageActivity {

    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    private SystemMessageAdapter adapter;
    private List<MessageListBean.PageEntity.ListEntity> data;
    private int page = 1;
    private int messageType = 1; // 消息类型 请求接口做为参数

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_system_message);
    }

    @Override
    public void initView() {
        titleBar.setTitle("系统消息");
        titleBar.setBack(true);
        data = new ArrayList<>();
        adapter = new SystemMessageAdapter(this, data);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(adapter);
        getPresenter().getMessageList(messageType, page);
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getMessageList(messageType, page);
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getMessageList(messageType, page);
            }
        });

        adapter.setOnItemClickListener(new SystemMessageAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SystemMessageActivity.this, MessageDetailActivity.class);
                intent.putExtra("Data", data.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void getDataSuccess(MessageListBean bean) {
        if (bean.getPage().getList() == null)
            return;

        if (page == 1) {
            recycleView.refreshComplete();
            data.clear();
        } else {
            recycleView.loadMoreComplete();
        }
        data.addAll(bean.getPage().getList());
        adapter.notifyDataSetChanged();
    }
}
