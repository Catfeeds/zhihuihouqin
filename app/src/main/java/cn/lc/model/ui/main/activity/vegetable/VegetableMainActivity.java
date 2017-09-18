package cn.lc.model.ui.main.activity.vegetable;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.VegetableAdapter;
import cn.lc.model.ui.main.bean.VegetableBean;
import cn.lc.model.ui.main.model.VegetableMainModel;
import cn.lc.model.ui.main.presenter.VegetableMainPresenter;
import cn.lc.model.ui.main.view.VegetableMainView;

import static cn.lc.model.R.id.submit;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */

public class VegetableMainActivity extends BaseActivity<VegetableMainModel, VegetableMainView, VegetableMainPresenter> implements VegetableMainView {


    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.price)
    TextView price;

    private int number = 0; // 总份数
    private int priceNum = 0;// 总价格

    private VegetableAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_vegetable_main);
    }

    @Override
    public void initView() {
        titleBar.setTitle("订餐");
        titleBar.setBack(true);

    }

    @OnClick({submit, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case submit:
                submitOrder();
                break;
            case R.id.search:
                break;

        }
    }

    // 提交订单操作
    private void submitOrder() {


    }

    @Override
    public void getVegetableDataSucc(VegetableBean bean) {

    }

    @Override
    public VegetableMainModel createModel() {
        return null;
    }

    @Override
    public VegetableMainPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
