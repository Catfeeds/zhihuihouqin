package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.adapter.SearchHistoryAdapter;
import com.moe.wl.ui.main.adapter.MySpinnerAdapter;
import com.moe.wl.ui.main.bean.BookSearchDataBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.BookSearchModel;
import com.moe.wl.ui.main.modelimpl.BookSearchModelImpl;
import com.moe.wl.ui.main.presenter.BookSearchDataPresenter;
import com.moe.wl.ui.main.view.BookSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：图书搜索页面
 * 作者：Shixhe On 2017/10/24 0024
 */

public class BookNewSearchActivity extends BaseActivity<BookSearchModel, BookSearchView, BookSearchDataPresenter> implements BookSearchView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.clear_history)
    TextView clearHistory;

    private SearchHistoryAdapter adapter;
    private MySpinnerAdapter spinnerAdapter;
    private List<BookSearchDataBean.HistorylistEntity> data;
    private List<BookSearchDataBean.TypelistEntity> typeData;

    private String type;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_book_search_new);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("搜索");
        data = new ArrayList<>();
        typeData = new ArrayList<>();
        getPresenter().getSearchData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                type = typeData.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                etSearch.setText(data.get(position).getKey());
            }
        });
    }

    @Override
    public void getSearchDataSucc(BookSearchDataBean bean) {
        if (bean.getHistorylist() != null && bean.getHistorylist().size() > 0) {
            clearHistory.setVisibility(View.VISIBLE);
            data.clear();
            data.addAll(bean.getHistorylist());
            adapter = new SearchHistoryAdapter(this, data);
            listView.setAdapter(adapter);
        }

        if (bean.getTypelist() != null && bean.getTypelist().size() > 0) {
            //定义适配器
            type = bean.getTypelist().get(0).getId();
            typeData.clear();
            typeData.addAll(bean.getTypelist());
            spinnerAdapter = new MySpinnerAdapter(this, typeData);
            spinner.setAdapter(spinnerAdapter);
        }
    }

    @Override
    public void clearHistorySucc(CollectBean bean) {
        ToastUtil.showToast(this, "清空搜索历史成功！");
        data.clear();
        adapter.notifyDataSetChanged();
        clearHistory.setVisibility(View.GONE);
    }

    @OnClick({R.id.search, R.id.clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_history:

                break;

            case R.id.search:
                if (etSearch.getText().toString().trim().length() == 0) {
                    ToastUtil.showToast(this, "请输入搜索内容");
                    return;
                }
                if ("".equals(type) || type == null) {
                    ToastUtil.showToast(this, "请选择搜索类别");
                    return;
                }
                Intent intent = new Intent(this, BookSearchResultActivity.class);
                intent.putExtra("keyword", etSearch.getText().toString().trim());
                intent.putExtra("type", type);
                startActivity(intent);
                break;
        }
    }

    @Override
    public BookSearchModel createModel() {
        return new BookSearchModelImpl();
    }

    @Override
    public BookSearchDataPresenter createPresenter() {
        return new BookSearchDataPresenter();
    }
}
