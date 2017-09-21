package cn.lc.model.ui.main.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.adapter.HealthServiceRvAdapter;
import cn.lc.model.ui.main.bean.InfolistBean;
import cn.lc.model.ui.main.bean.MoreListBean;
import cn.lc.model.ui.main.model.MoreListModel;
import cn.lc.model.ui.main.modelimpl.MoreListModelImpl;
import cn.lc.model.ui.main.presenter.MoreListPresenter;
import cn.lc.model.ui.main.view.MoreListView;
import mvp.cn.util.ToastUtil;

/**
 * 健康咨询搜索页面
 */
public class HealthSearchActivity extends BaseActivity<MoreListModel, MoreListView, MoreListPresenter> implements MoreListView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.rv_search_result)
    XRecyclerView rvSearchResult;
    @BindView(R.id.activity_health_search)
    LinearLayout activityHealthSearch;

    private HealthServiceRvAdapter adapter;
    private List<InfolistBean> data;

    private int page = 1;
    private String content;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_health_search);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        adapter = new HealthServiceRvAdapter(this, data);
        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        rvSearchResult.setAdapter(adapter);
        etSearch.setOnKeyListener(onKeyListener);
        rvSearchResult.setLoadingListener(new XRecyclerView.LoadingListener() {
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

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                page = 1;
                content = etSearch.getText().toString().trim();
                LogUtils.d("搜索键！！！");
                getData();
                return true;
            }
            return false;
        }
    };

    private void getData() {
        if (content.length() == 0) {
            ToastUtil.showToast(this, "搜索内容不能为空！");
            return;
        }
        getPresenter().getData(page, 20, content);
    }

    @Override
    public void getMoreList(MoreListBean moreListBean) {
        if (page == 1) {
            data.clear();
            rvSearchResult.refreshComplete();
        } else {
            rvSearchResult.loadMoreComplete();
        }
        data.addAll(moreListBean.getInfolist());
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_back, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_close:
                etSearch.setText("");
                break;
        }
    }

    @Override
    public MoreListModel createModel() {
        return new MoreListModelImpl();
    }

    @Override
    public MoreListPresenter createPresenter() {
        return new MoreListPresenter();
    }
}
