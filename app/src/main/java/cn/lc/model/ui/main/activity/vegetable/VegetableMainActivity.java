package cn.lc.model.ui.main.activity.vegetable;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.VegetableAdapter;
import cn.lc.model.ui.main.bean.VegetableBean;
import cn.lc.model.ui.main.model.VegetableMainModel;
import cn.lc.model.ui.main.modelimpl.VegetableMainModelImpl;
import cn.lc.model.ui.main.presenter.VegetableMainPresenter;
import cn.lc.model.ui.main.view.VegetableMainView;
import mvp.cn.util.ToastUtil;

import static cn.lc.model.R.id.num;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */

public class VegetableMainActivity extends BaseActivity<VegetableMainModel, VegetableMainView, VegetableMainPresenter> implements VegetableMainView {

    private static final int REQUEST_SEARCH = 1001;

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    @BindView(num)
    TextView vegetableNum;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.submit)
    Button submit;

    private int number = 0; // 总份数
//    private int priceNum = 0;// 总价格

    private int page = 1;

    private List<VegetableBean.PageEntity.ListEntity> data;

    private VegetableAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_vegetable_main);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setTitle("订餐");
        titleBar.setBack(true);
        data = new ArrayList<>();
        adapter = new VegetableAdapter(this, data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);

        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getVegetableData(page, "");
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getVegetableData(page, "");
            }
        });
        adapter.setOnAddClickListener(new VegetableAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(int position, int num) {
                data.get(position).setNumber(num);
                int priceNumber = 0;
                int vegetableNumber = 0;
                for (int i = 0; i < data.size(); i++) {
                    priceNumber += data.get(i).getNumber() * data.get(i).getPrice();
                    vegetableNumber += data.get(i).getNumber();
                }
                vegetableNum.setText("共" + vegetableNumber + "份");
                price.setText("¥" + priceNumber);
//                priceNum = priceNumber;
                number = vegetableNumber;
                submit.setText("去结算(" + vegetableNumber + ")");
            }
        });

        adapter.setOnMinusClickListener(new VegetableAdapter.OnMinusClickListener() {
            @Override
            public void onMinusClick(int position, int num) {
                int priceNumber = 0;
                int vegetableNumber = 0;
                data.get(position).setNumber(num);
                for (int i = 0; i < data.size(); i++) {
                    priceNumber += data.get(i).getNumber() * data.get(i).getPrice();
                    vegetableNumber += data.get(i).getNumber();
                }
                vegetableNum.setText("共" + vegetableNumber + "份");
                price.setText("¥" + priceNumber);
//                priceNum = priceNumber;
                number = vegetableNumber;
                submit.setText("去结算(" + vegetableNumber + ")");
            }
        });
        getPresenter().getVegetableData(page, "");
    }

    @OnClick({R.id.submit, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                submitOrder();
                break;
            case R.id.search:
                startActivityForResult(new Intent(VegetableMainActivity.this, VegetableSearchActivity.class), REQUEST_SEARCH);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent datas) {
        super.onActivityResult(requestCode, resultCode, datas);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SEARCH) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getId() == datas.getIntExtra("VegetableID", 0)) {
                        LogUtils.d("  ID=" + data.get(i).getId() + "  i=" + i);
                        recycleView.scrollToPosition(i + 1);
                        return;
                    }
                }
            }
        }
    }

    // 提交订单操作
    private void submitOrder() {
        if (number == 0) {
            ToastUtil.showToast(this, "您还没有点餐！");
            return;
        }
        Intent intent = new Intent(this, VegetableOrderMessageActivity.class);
        intent.putExtra("Data", (Serializable) data);
        startActivity(intent);
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

    @Override
    public VegetableMainModel createModel() {
        return new VegetableMainModelImpl();
    }

    @Override
    public VegetableMainPresenter createPresenter() {
        return new VegetableMainPresenter();
    }

}
