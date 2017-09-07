package cn.lc.model.ui.main.activity.complain;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.SubmitSuccessActivity;
import cn.lc.model.ui.main.adapter.LabellingAdapter;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.LabellingBean;
import cn.lc.model.ui.main.model.SubmitComplainModel;
import cn.lc.model.ui.main.modelimpl.SubmitComplainModelImpl;
import cn.lc.model.ui.main.presenter.SubmitComplainPresenter;
import cn.lc.model.ui.main.view.SubmitComplainView;

/**
 * 类描述：意见反馈主页
 * 作者：Shixhe On 2017/9/6 0006
 */

public class SubmitComplainActivity extends BaseActivity<SubmitComplainModel, SubmitComplainView, SubmitComplainPresenter> implements SubmitComplainView {

    @BindView(R.id.top_bar)
    TitleBar titleBar;
    @BindView(R.id.et_content)
    EditText content;
    @BindView(R.id.grid_view)
    NoSlidingGridView gridView;
    @BindView(R.id.complain_history)
    TextView comPlainHistory;
    @BindView(R.id.suggest)
    EditText suggest;
    @BindView(R.id.is_anonymity)
    SwitchButton anonymity;
    @BindView(R.id.labelling)
    NoSlidingGridView labelling_gridView;

    private LabellingAdapter adapter;

    private LabellingBean labellingBean;
    private int labellingID = 0;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("意见投诉");
        getPresenter().getLabelling();

        anonymity.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

            }
        });

        labelling_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < labellingBean.getTagList().size(); i++) {
                    labellingBean.getTagList().get(i).setSelect(false);
                }
                labellingBean.getTagList().get(position).setSelect(true);
                labellingID = labellingBean.getTagList().get(position).getId();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void submitComplainSucc(CollectBean bean) {
        startActivity(new Intent(this, SubmitSuccessActivity.class));
        finish();
    }

    @OnClick({R.id.complain_history, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complain_history:
                startActivity(new Intent(SubmitComplainActivity.this, ComplainHistoryActivity.class));
                break;

            case R.id.submit:
                break;

        }
    }

    @Override
    public void getLabelling(LabellingBean bean) {
        labellingBean = bean;
        if (adapter == null) {
            adapter = new LabellingAdapter(this, bean);
            labelling_gridView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public SubmitComplainModel createModel() {
        return new SubmitComplainModelImpl();
    }

    @Override
    public SubmitComplainPresenter createPresenter() {
        return new SubmitComplainPresenter();
    }

}
