package com.moe.wl.ui.main.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.SearchAdapter;
import com.moe.wl.ui.main.bean.HomeSearchBean;
import com.moe.wl.ui.main.fragment.BookPutAwayFragment;
import com.moe.wl.ui.main.model.MainSearchModel;
import com.moe.wl.ui.main.modelimpl.MainSearchModelImpl;
import com.moe.wl.ui.main.presenter.MainSearchPresenter;
import com.moe.wl.ui.main.view.MainSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSearchAct extends BaseActivity<MainSearchModel, MainSearchView, MainSearchPresenter> implements MainSearchView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_all_cancle)
    ImageView ivAllCancle;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    @BindView(R.id.activity_main_search)
    LinearLayout activityMainSearch;
    private SearchAdapter searchAdapter;

    @Override
    public MainSearchPresenter createPresenter() {
        return new MainSearchPresenter();
    }

    @Override
    public MainSearchModel createModel() {
        return new MainSearchModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main_search);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this);
        rvSearch.setAdapter(searchAdapter);
        etSearch.setCursorVisible(false);
        etSearch.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    etSearch.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    ivAllCancle.setVisibility(View.VISIBLE);
                } else {
                    ivAllCancle.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setKeyListen();
    }
    //对软键盘回车键进行监听
    private void setKeyListen() {

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //*隐藏软键盘*//
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = etSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(search)) {
                        LogUtils.d("搜索键！！！");
                        getPresenter().homeSearch(search);
                    }
                }

                return true;
            }
        });
    }
    @Override
    public void homeSearch(HomeSearchBean bean) {
        if(bean!=null){
            List<HomeSearchBean.ServiceListBean> serviceList = bean.getServiceList();
            for (int i = 0; i < serviceList.size(); i++) {
                LogUtils.i("获取到的服务id==="+serviceList.get(i).getId());
            }
            searchAdapter.setData(serviceList);
        }

    }

    @OnClick({R.id.iv_back,R.id.iv_all_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_all_cancle:
                etSearch.setText("");
                break;
        }
    }
}
