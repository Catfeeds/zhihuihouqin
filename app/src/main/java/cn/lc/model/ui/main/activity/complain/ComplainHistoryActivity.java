package cn.lc.model.ui.main.activity.complain;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ComplainHistoryAdapter;
import cn.lc.model.ui.main.bean.ComplainHistoryBean;
import cn.lc.model.ui.main.model.ComplainHistoryModel;
import cn.lc.model.ui.main.modelimpl.ComplainHistoryModelImpl;
import cn.lc.model.ui.main.presenter.ComplainHistoryPresenter;
import cn.lc.model.ui.main.view.ComplainHistoryView;

/**
 * 类描述：反馈历史页面
 * 作者：Shixhe On 2017/9/6 0006
 */
public class ComplainHistoryActivity extends BaseActivity<ComplainHistoryModel, ComplainHistoryView, ComplainHistoryPresenter> implements ComplainHistoryView {

    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    private ComplainHistoryAdapter adapter;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain_history);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("反馈历史");
        getPresenter().getComplainHistoryData(1, 20);
    }

    @Override
    public void getComplainHistorySucc(ComplainHistoryBean bean) {
        recycleView.setAdapter(adapter);
    }

    @Override
    public ComplainHistoryModel createModel() {
        return new ComplainHistoryModelImpl();
    }

    @Override
    public ComplainHistoryPresenter createPresenter() {
        return new ComplainHistoryPresenter();
    }
}
