package com.moe.wl.ui.main.activity.vegetable;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.Arith;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.VegetableAdapter;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.CanOrderedBean;
import com.moe.wl.ui.main.bean.VegetableBean;
import com.moe.wl.ui.main.model.VegetableMainModel;
import com.moe.wl.ui.main.modelimpl.VegetableMainModelImpl;
import com.moe.wl.ui.main.presenter.VegetableMainPresenter;
import com.moe.wl.ui.main.view.VegetableMainView;
import com.moe.wl.ui.mywidget.ShowBigPhotoPop;
import com.moe.wl.ui.mywidget.ShowHintDialog;
import com.moe.wl.ui.mywidget.TsAlertDialog;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

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
    @BindView(R.id.num)
    TextView vegetableNum;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.submit)
    Button submit;

    private int number = 0; // 总份数
    //    private float priceNum = 0;// 总价格
    private boolean move;
    private LinearLayoutManager mLinearLayoutManager;
    private int mPosition;

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
        titleBar.setTitle("净菜预订");
        titleBar.setBack(true);
        data = new ArrayList<>();
        adapter = new VegetableAdapter(this, data);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(mLinearLayoutManager);
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
        adapter.setOnImageClickListener(new VegetableAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(String imageUrl) {
                ShowBigPhotoPop pop = new ShowBigPhotoPop(VegetableMainActivity.this, imageUrl);
                pop.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
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
                DecimalFormat df = new DecimalFormat("#0.00");
                price.setText("¥" + df.format(priceNumber));
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
                DecimalFormat df = new DecimalFormat("#0.00");
                price.setText("¥" + df.format(priceNumber));
                number = vegetableNumber;
                if (number == 0) {
                    submit.setText("去结算");
                } else {
                    submit.setText("去结算(" + vegetableNumber + ")");
                }
            }
        });
        recycleView.setOnScrollListener(new RecyclerViewListener());
//        getPresenter().getVegetableData(page, "");
    }

    @OnClick({R.id.submit, R.id.search, R.id.iv_hint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                submitOrder();
                break;
            case R.id.iv_hint:
                SharedPrefHelper.getInstance().setServiceHint(Constants.VEGETABLE, false);
                getHint();
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
                        mPosition = i + 1;
                        moveToPosition(i + 1);
                        return;
                    }
                }
            }
        }
    }

    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            recycleView.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = recycleView.getChildAt(n - firstItem).getTop();
            recycleView.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recycleView.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            move = true;
        }

    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //在这里进行第二次滚动（最后的100米！）
            if (move) {
                move = false;
                //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                int n = mPosition - mLinearLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < recycleView.getChildCount()) {
                    //获取要置顶的项顶部离RecyclerView顶部的距离
                    int top = recycleView.getChildAt(n).getTop();
                    //最后的移动
                    recycleView.scrollBy(0, top);
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
        Intent intent = new Intent(this, ConfirmVegetableOrderActivity.class);
        intent.putExtra("Data", (Serializable) data);
        startActivity(intent);
    }

    @Override
    public void getVegetableDataSucc(VegetableBean bean) {
        if (bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
        if (page == 1) {
            number = 0;
            vegetableNum.setText("共0份");
            price.setText("¥0");
            submit.setText("去结算");
            data.clear();
        }
        page = bean.getPage().getCurrPage();
        data.addAll(bean.getPage().getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void canOrderedResult(CanOrderedBean bean) {
        int status = bean.getStatus();
        String rule = bean.getRule();
        if (status == 1) {//可以预定
            if (!SharedPrefHelper.getInstance().getServiceHint(Constants.VEGETABLE)) {
                getHint();
            }
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

    @Override
    public void onError() {
        if (page == 1) {
            recycleView.refreshComplete();
        } else {
            recycleView.loadMoreComplete();
        }
    }

    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.VEGETABLE);
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.errCode == 0) {

                    // TODO 弹出温馨提示窗
                    final ShowHintDialog pop = new ShowHintDialog(VegetableMainActivity.this, mResponse.getServiceInfo().getRemind(), Constants.VEGETABLE);
//                        pop.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
                    pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                        @Override
                        public void onSetting(TextView content) {
                            pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(VegetableMainActivity.this, 280));
                        }
                    });
                    pop.show();
                } else {
                    ToastUtil.showToast(VegetableMainActivity.this, mResponse.msg);
                }
            }
        });
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
