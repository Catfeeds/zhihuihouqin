package cn.lc.model.ui.main.activity.message;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import mvp.cn.common.MvpPresenter;
import mvp.cn.rx.MvpModel;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */
public class SystemMessageActivity extends BaseActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_system_message);
    }

    @Override
    public void initView() {

    }

    @Override
    public MvpModel createModel() {
        return null;
    }

    @Override
    public MvpPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
