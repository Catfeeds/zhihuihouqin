package com.moe.wl.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.main.activity.message.ActiveMessageActivity;
import com.moe.wl.ui.main.activity.message.CommentMessageActivity;
import com.moe.wl.ui.main.activity.message.PayUpMessageActivity;
import com.moe.wl.ui.main.activity.message.SystemMessageActivity;
import com.moe.wl.ui.main.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.activity.message.AuditMessageActivity;
import com.moe.wl.ui.main.activity.message.ComplainMessageActivity;
import com.moe.wl.ui.main.activity.message.OrderMessageActivity;
import com.moe.wl.ui.main.bean.MessageBean;
import com.moe.wl.ui.main.model.Tab3Model;
import com.moe.wl.ui.main.modelimpl.Tab3ModelImpl;
import com.moe.wl.ui.main.presenter.Tab3Presenter;
import com.moe.wl.ui.main.view.Tab3View;

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
    @BindView(R.id.comment_bubble)
    TextView commentBubble;
    @BindView(R.id.msg_comment_content)
    TextView msgCommentContent;
    @BindView(R.id.pay_up_bubble)
    TextView payUpBubble;
    @BindView(R.id.msg_pay_up_content)
    TextView msgPayUpContent;
    @BindView(R.id.audit_bubble)
    TextView auditBubble;
    @BindView(R.id.msg_audit_content)
    TextView msgAuditContent;
    @BindView(R.id.active_bubble)
    TextView activeBubble;
    @BindView(R.id.msg_active_content)
    TextView msgActiveContent;
    @BindView(R.id.order_bubble)
    TextView orderBubble;
    @BindView(R.id.msg_order_content)
    TextView msgOrderContent;
    @BindView(R.id.complain_bubble)
    TextView complainBubble;
    @BindView(R.id.msg_complain_content)
    TextView msgComplainContent;
    @BindView(R.id.recycleView)
    NoSlidingListView recycleView;

    private List<MessageBean.TalkListEntity> data;
    private MessageListAdapter adapter;

//    Unbinder unbinder;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab3);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        data = new ArrayList<>();
        getPresenter().getData();
        adapter = new MessageListAdapter(getActivity(), data);
        recycleView.setAdapter(adapter);
    }

    @Override
    public void getDataSucc(MessageBean bean) {
        if (bean.getMessageList() != null) {
            for (int i = 0; i < bean.getMessageList().size(); i++) {
                MessageBean.MessageListEntity entity = bean.getMessageList().get(i);
                switch (entity.getMessagetype()) {
                    case 1: // 1 系统消息
                        msgSystemContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), systemBubble);
                        break;

                    case 2: // 2 评论消息
                        msgCommentContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), commentBubble);
                        break;

                    case 3: // 3 缴费通知
                        msgPayUpContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), payUpBubble);
                        break;

                    case 4: // 4 审核状态
                        msgAuditContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), auditBubble);
                        break;

                    case 5: // 5 活动通知
                        msgActiveContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), activeBubble);
                        break;

                    case 6: // 6 订单通知
                        msgOrderContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), orderBubble);
                        break;

                    case 7: // 7 意见投诉
                        msgComplainContent.setText(entity.getMessagetitle());
                        bubbleNumber(entity.getNewCount(), complainBubble);
                        break;
                }
            }
        }
        if (bean.getTalkList() != null) {
            data.clear();
            data.addAll(bean.getTalkList());
            adapter.notifyDataSetChanged();
        }
    }

    // 每个条目新消息数量设置
    private void bubbleNumber(int messageBubble, TextView tvBubble) {
        tvBubble.setText(messageBubble);
        tvBubble.setVisibility(/*messageBubble != null &&*/ messageBubble > 0 ? View.VISIBLE : View.VISIBLE);
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
                startActivity(new Intent(getActivity(), CommentMessageActivity.class));
                break;

            case R.id.pay_up: //  缴费消息
                startActivity(new Intent(getActivity(), PayUpMessageActivity.class));
                break;

            case R.id.audit: //  审核状态
                startActivity(new Intent(getActivity(), AuditMessageActivity.class));
                break;

            case R.id.active: //  活动报名
                startActivity(new Intent(getActivity(), ActiveMessageActivity.class));
                break;

            case R.id.order: //  订单通知
                startActivity(new Intent(getActivity(), OrderMessageActivity.class));
                break;

            case R.id.complain: //  意见投诉
                startActivity(new Intent(getActivity(), ComplainMessageActivity.class));
                break;

        }
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
