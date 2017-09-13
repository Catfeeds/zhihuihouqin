package cn.lc.model.ui.main.activity.complain;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.ComplainReplyAdapter;
import cn.lc.model.ui.main.bean.ComplainReplyBean;
import cn.lc.model.ui.main.model.ComplainReplyModel;
import cn.lc.model.ui.main.modelimpl.ComplainReplyModelImpl;
import cn.lc.model.ui.main.presenter.ComplainReplyPresenter;
import cn.lc.model.ui.main.view.ComplainReplyView;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：投诉反馈列表页
 * 作者：Shixhe On 2017/9/9 0009
 */
public class ComplainReplyActivity extends BaseActivity<ComplainReplyModel, ComplainReplyView, ComplainReplyPresenter> implements ComplainReplyView {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.list_view)
    ListView listView;

    private int id;
    private ComplainReplyAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain_reply);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setTitle("反馈回复");
        titleBar.setBack(true);
        id = getIntent().getIntExtra("ID", 0);
        getPresenter().getComplainReply(id);
    }

    @OnClick({R.id.send})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.send) {
            if (etContent.getText().toString().trim().length() > 0)
                getPresenter().feedbackMessage(id, etContent.getText().toString().trim());
            else
                ToastUtil.showToast(this, "请输入反馈内容！");
        }
    }

    @Override
    public void getComplainReply(ComplainReplyBean bean) {
        if (null == adapter) {
            if (bean.getPage() == null || bean.getPage().getList() == null)
                return;
            adapter = new ComplainReplyAdapter(this, bean.getPage().getList());
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void feedbackMessage() {
        getPresenter().getComplainReply(id);
    }

    @Override
    public ComplainReplyModel createModel() {
        return new ComplainReplyModelImpl();
    }

    @Override
    public ComplainReplyPresenter createPresenter() {
        return new ComplainReplyPresenter();
    }

}
