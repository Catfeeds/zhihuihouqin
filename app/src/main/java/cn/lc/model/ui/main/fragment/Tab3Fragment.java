package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.model.Tab3Model;
import cn.lc.model.ui.main.modelimpl.Tab3ModelImpl;
import cn.lc.model.ui.main.presenter.Tab3Presenter;
import cn.lc.model.ui.main.view.Tab3View;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab3Fragment extends BaseFragment<Tab3Model, Tab3View, Tab3Presenter> implements Tab3View {


    @BindView(R.id.message_title)
    TitleBar titleBar;
    @BindView(R.id.tv_system_msg)
    TextView tvSystemMsg;
    @BindView(R.id.tv_comment_msg)
    TextView tvCommentMsg;
    @BindView(R.id.tv_pay_notify)
    TextView tvPayNotify;
    @BindView(R.id.tv_audit_status)
    TextView tvAuditStatus;
    @BindView(R.id.tv_activity_notify)
    TextView tvActivityNotify;
    @BindView(R.id.tv_order_notify)
    TextView tvOrderNotify;
    @BindView(R.id.tv_Complaints)
    TextView tvComplaints;
    @BindView(R.id.rv_msg)
    NoSlideRecyclerView rvMsg;
    Unbinder unbinder;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab3);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        initTitle();

    }

    private void initTitle() {
        titleBar.setTitle("消息");
        titleBar.setTitleRight("已读");
    }

    @Override
    public Tab3Model createModel() {
        return new Tab3ModelImpl();
    }

    @Override
    public Tab3Presenter createPresenter() {
        return new Tab3Presenter();
    }

}
