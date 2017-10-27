package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Library.JieYueSuccActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.PostNeedModel;
import com.moe.wl.ui.main.modelimpl.PostNeedModelImpl;
import com.moe.wl.ui.main.presenter.PostNeedPresenter;
import com.moe.wl.ui.main.view.PostNeedView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.VerifyCheck;

/**
 * 发布需求
 */
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
        if (TextUtils.isEmpty(realName)){
            realName= SharedPrefHelper.getInstance().getNickname();
        }
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

        if(!VerifyCheck.isMobilePhoneVerify(phone)){
            showToast("请输入正确的手机号码");
            return ;
        }
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(remark)||TextUtils.isEmpty(spName)||
                TextUtils.isEmpty(spCount)){
            showToast("请将信息填写完整");
        }else{
            getPresenter().post(realName,phone,remark,spName,spCount);
        }
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
