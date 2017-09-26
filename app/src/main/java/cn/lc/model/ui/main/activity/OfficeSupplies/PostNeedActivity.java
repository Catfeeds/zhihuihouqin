package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Library.JieYueSuccActivity;
import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.model.PostNeedModel;
import cn.lc.model.ui.main.modelimpl.PostNeedModelImpl;
import cn.lc.model.ui.main.presenter.PostNeedPresenter;
import cn.lc.model.ui.main.view.PostNeedView;

public class PostNeedActivity extends BaseActivity<PostNeedModel, PostNeedView, PostNeedPresenter> implements PostNeedView {


    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_sp_name)
    TextView tvSpName;
    @BindView(R.id.et_sp_name)
    EditText etSpName;
    @BindView(R.id.tv_sp_count)
    TextView tvSpCount;
    @BindView(R.id.et_sp_count)
    EditText etSpCount;
    @BindView(R.id.tv_need_remark)
    TextView tvNeedRemark;
    @BindView(R.id.et_write)
    EditText etWrite;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.activity_post_need)
    LinearLayout activityPostNeed;
    private String realName;
    private String phoneNumber;
    private int  from;
    private int status;

    @Override
    public PostNeedPresenter createPresenter() {
        return new PostNeedPresenter();
    }

    @Override
    public PostNeedModel createModel() {
        return new PostNeedModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_post_need);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initData();
    }

    private void initData() {
        realName = SharedPrefHelper.getInstance().getRealName();
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        tvName.setText("姓名: " + realName);
        etPhone.setText(phoneNumber);
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("其他需求");
        title.setTitleRight("历史需求");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostNeedActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        String phone = etPhone.getText().toString().trim();
        String remark = etWrite.getText().toString().trim();
        String spName = etSpName.getText().toString().trim();
        String spCount = etSpCount.getText().toString().trim();
        getPresenter().post(realName,phone,remark,spName,spCount);
    }

    @Override
    public void postSuccess(ActivityPostBean bean) {
        if (bean != null) {
            Intent intent = new Intent(this, JieYueSuccActivity.class);
            intent.putExtra("from",3);
            startActivity(intent);
        } else {
            showToast(bean.getMsg());
        }
    }
}
