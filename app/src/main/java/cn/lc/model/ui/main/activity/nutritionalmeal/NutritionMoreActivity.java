package cn.lc.model.ui.main.activity.nutritionalmeal;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.NutritionAdapter;
import cn.lc.model.ui.main.bean.NutritionBean;
import cn.lc.model.ui.main.model.NutritionModel;
import cn.lc.model.ui.main.modelimpl.NutritionModelImpl;
import cn.lc.model.ui.main.presenter.NutritionPresenter;
import cn.lc.model.ui.main.view.NutritionView;

/**
 * 类描述：营养套餐更多页面
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionMoreActivity extends BaseActivity<NutritionModel, NutritionView, NutritionPresenter> implements NutritionView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.list_view)
    ListView listView;

    // 营养套餐Adapter
    private NutritionAdapter nutritionAdapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_nutrition_more);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("营养套餐");
        // 获取营养套餐
        getPresenter().getNutritionData(2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO 跳转详情页
            }
        });

    }

    @Override
    public void getNutritionList(NutritionBean bean) {
        if (nutritionAdapter == null) {
            nutritionAdapter = new NutritionAdapter(this, bean, 1);
            listView.setAdapter(nutritionAdapter);
        } else {
            nutritionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getNutritionBanner() {
    }

    @Override
    public NutritionModel createModel() {
        return new NutritionModelImpl();
    }

    @Override
    public NutritionPresenter createPresenter() {
        return new NutritionPresenter();
    }
}
