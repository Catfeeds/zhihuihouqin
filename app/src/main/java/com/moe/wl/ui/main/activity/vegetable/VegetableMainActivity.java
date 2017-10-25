package com.moe.wl.ui.main.activity.vegetable;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.Arith;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.VegetableAdapter;
import com.moe.wl.ui.main.bean.CanOrderedBean;
import com.moe.wl.ui.main.bean.VegetableBean;
import com.moe.wl.ui.main.model.VegetableMainModel;
import com.moe.wl.ui.main.modelimpl.VegetableMainModelImpl;
import com.moe.wl.ui.main.presenter.VegetableMainPresenter;
import com.moe.wl.ui.main.view.VegetableMainView;
import com.moe.wl.ui.mywidget.TsAlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

import static com.moe.wl.R.id.num;

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
//    private float priceNum = 0;// 总价格

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
        getPresenter().canOrdered();//判断是否可以预约
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
                double priceNumber = 0;
                int vegetableNumber = 0;
                for (int i = 0; i < data.size(); i++) {
                    priceNumber = Arith.add(priceNumber, data.get(i).getNumber() * data.get(i).getPrice());
                    vegetableNumber += data.get(i).getNumber();
                }
                vegetableNum.setText("共" + vegetableNumber + "份");
                LogUtils.d("总价钱 ： " + priceNumber);
                price.setText("¥" + priceNumber);
//                priceNum = priceNumber;
                number = vegetableNumber;
                submit.setText("去结算(" + vegetableNumber + ")");
            }
        });

        adapter.setOnMinusClickListener(new VegetableAdapter.OnMinusClickListener() {
            @Override
            public void onMinusClick(int position, int num) {
                double priceNumber = 0;
                int vegetableNumber = 0;
                data.get(position).setNumber(num);
                for (int i = 0; i < data.size(); i++) {
                    priceNumber = Arith.add(priceNumber, data.get(i).getNumber() * data.get(i).getPrice());
                    vegetableNumber += data.get(i).getNumber();
                }
                vegetableNum.setText("共" + vegetableNumber + "份");
                price.setText("¥" + priceNumber);
//                priceNum = priceNumber;
                number = vegetableNumber;
                if (number == 0) {
                    submit.setText("去结算");
                } else {
                    submit.setText("去结算(" + vegetableNumber + ")");
                }
            }
        });
//        getPresenter().getVegetableData(page, "");
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
            number = 0;
            vegetableNum.setText("共0份");
            price.setText("¥0");
            submit.setText("去结算");
            data.clear();
        } else {
            recycleView.loadMoreComplete();
        }
        page = bean.getPage().getCurrPage();
        data.addAll(bean.getPage().getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void canOrderedResult(CanOrderedBean bean) {
        LogUtils.i(bean.getErrCode() + "===================");
        if (bean != null) {
            int status = bean.getStatus();
            String rule = bean.getRule();
            if (status == 1) {//可以预定
                getPresenter().getVegetableData(page, "");
            } else if (status == 0) {//不可以预定
                TsAlertDialog dialog = new TsAlertDialog(this).builder();
                dialog.setTitle("提示")
                        .setMsg(rule)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
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
