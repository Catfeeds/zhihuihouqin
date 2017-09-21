package cn.lc.model.ui.main.activity.vegetable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.adapter.VegetableSearchAdapter;
import cn.lc.model.ui.main.bean.VegetableBean;
import cn.lc.model.ui.main.model.VegetableMainModel;
import cn.lc.model.ui.main.modelimpl.VegetableMainModelImpl;
import cn.lc.model.ui.main.presenter.VegetableMainPresenter;
import cn.lc.model.ui.main.view.VegetableMainView;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/18 0018
 */
public class VegetableSearchActivity extends BaseActivity<VegetableMainModel, VegetableMainView, VegetableMainPresenter> implements VegetableMainView {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycleView)
    XRecyclerView recycleView;

    private VegetableSearchAdapter adapter;
    private List<VegetableBean.PageEntity.ListEntity> data;

    private int searchId = 0;
    private int page = 1;
    private String content;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_search_vegetable);
    }

    @Override
    public void initView() {
        etSearch.setOnKeyListener(onKeyListener);
        data = new ArrayList<>();
        adapter = new VegetableSearchAdapter(this, data, new VegetableSearchAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                searchId = data.get(position).getId();
                Intent intent = new Intent();
                intent.putExtra("VegetableID", searchId);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(adapter);

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

    @Override
    public void getVegetableDataSucc(VegetableBean bean) {
        if (bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
        if (page == 1) {
            recycleView.refreshComplete();
            data.clear();
        } else {
            recycleView.loadMoreComplete();
        }
        page = bean.getPage().getCurrPage();
        data.addAll(bean.getPage().getList());
        adapter.notifyDataSetChanged();
    }

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /* 隐藏软键盘 */
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
        getPresenter().getVegetableData(page, content);
    }

    @OnClick({R.id.clear_edit, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_edit:
                etSearch.setText("");
                break;

            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public VegetableMainModel createModel() {
        return new VegetableMainModelImpl();
    }

    @Override
    public VegetableMainPresenter createPresenter() {
        return new VegetableMainPresenter();
    }

}
