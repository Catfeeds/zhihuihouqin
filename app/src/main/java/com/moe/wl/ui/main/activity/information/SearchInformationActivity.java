package com.moe.wl.ui.main.activity.information;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.HomeNsrlv1Adapter;
import com.moe.wl.ui.main.bean.InformationBean;
import com.moe.wl.ui.main.bean.ListEntity;
import com.moe.wl.ui.main.model.InformationModel;
import com.moe.wl.ui.main.modelimpl.InformationModelImpl;
import com.moe.wl.ui.main.presenter.InformationPresenter;
import com.moe.wl.ui.main.view.InformationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/13 0013
 */

public class SearchInformationActivity extends BaseActivity<InformationModel, InformationView, InformationPresenter> implements InformationView {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycleView)
    XRecyclerView recycleView;

    private HomeNsrlv1Adapter adapter;
    private List<ListEntity> data;

    private int page = 1;
    private String content;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_search_information);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        adapter = new HomeNsrlv1Adapter(this, data);
        etSearch.setOnKeyListener(onKeyListener);
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

    @OnClick({R.id.clear_edit, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.clear_edit:
                etSearch.setText("");
                break;
        }
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
        getPresenter().getInformation(0, 0, content, page);
    }

    @Override
    public void getInformationSucc(InformationBean bean) {
        if (bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
        if (page == 1) {
            data.clear();
        }
        if (page >= bean.getPage().getTotalPage()) {
            recycleView.setLoadingMoreEnabled(false);
        } else {
            recycleView.setLoadingMoreEnabled(true);
        }
        data.addAll(bean.getPage().getList());
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
