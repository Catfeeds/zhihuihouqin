package cn.lc.model.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.activity.message.SystemMessageActivity;
import cn.lc.model.ui.main.model.Tab3Model;
import cn.lc.model.ui.main.modelimpl.Tab3ModelImpl;
import cn.lc.model.ui.main.presenter.Tab3Presenter;
import cn.lc.model.ui.main.view.Tab3View;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab3Fragment extends BaseFragment<Tab3Model, Tab3View, Tab3Presenter> implements Tab3View {


    @BindView(R.id.already_read)
    TextView alreadyRead;
    @BindView(R.id.system_bubble)
    TextView systemBubble;
    @BindView(R.id.msg_system_content)
    TextView msgSystemContent;
    @BindView(R.id.system)
    RelativeLayout system;
    @BindView(R.id.comment_bubble)
    TextView commentBubble;
    @BindView(R.id.msg_comment_content)
    TextView msgCommentContent;
    @BindView(R.id.comment)
    RelativeLayout comment;
    @BindView(R.id.pay_up_bubble)
    TextView payUpBubble;
    @BindView(R.id.msg_pay_up_content)
    TextView msgPayUpContent;
    @BindView(R.id.pay_up)
    RelativeLayout payUp;
    @BindView(R.id.audit_bubble)
    TextView auditBubble;
    @BindView(R.id.msg_audit_content)
    TextView msgAuditContent;
    @BindView(R.id.audit)
    RelativeLayout audit;
    @BindView(R.id.active_bubble)
    TextView activeBubble;
    @BindView(R.id.msg_active_content)
    TextView msgActiveContent;
    @BindView(R.id.active)
    RelativeLayout active;
    @BindView(R.id.order_bubble)
    TextView orderBubble;
    @BindView(R.id.msg_order_content)
    TextView msgOrderContent;
    @BindView(R.id.order)
    RelativeLayout order;
    @BindView(R.id.complain_bubble)
    TextView complainBubble;
    @BindView(R.id.msg_complain_content)
    TextView msgComplainContent;
    @BindView(R.id.complain)
    RelativeLayout complain;
    @BindView(R.id.rv_msg)
    NoSlideRecyclerView rvMsg;

//    Unbinder unbinder;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab3);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        initTitle();
    }

    @OnClick({R.id.already_read, R.id.system, R.id.comment, R.id.pay_up, R.id.audit, R.id.active, R.id.order, R.id.complain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.already_read: //  已读

                break;

            case R.id.system: //  系统消息
                startActivity(new Intent(getActivity(), SystemMessageActivity.class));
                break;

            case R.id.comment: //  评论消息

                break;

            case R.id.pay_up: //  缴费消息

                break;

            case R.id.audit: //  审核状态

                break;

            case R.id.active: //  活动报名

                break;

            case R.id.order: //  订单通知

                break;

            case R.id.complain: //  意见投诉

                break;

        }
    }

    private void initTitle() {
    }

    @Override
    public Tab3Model createModel() {
        return new Tab3ModelImpl();
    }

    @Override
    public Tab3Presenter createPresenter() {
        return new Tab3Presenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
}
