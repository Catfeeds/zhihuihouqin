package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import cn.lc.model.ui.main.bean.HistoryPostBean;
import cn.lc.model.ui.main.model.PostNeedModel;
import cn.lc.model.ui.main.modelimpl.PostNeedModelImpl;
import cn.lc.model.ui.main.presenter.PostNeedPresenter;
import cn.lc.model.ui.main.view.PostNeedView;

public class CheckHistoryPostItemAct extends BaseActivity<PostNeedModel, PostNeedView, PostNeedPresenter> implements PostNeedView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
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
    private int status;
    private boolean isEdit=false;
    private String mobile;
    private String productname;
    private int productcount;
    private String createtime;
    private String remark;

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
        setContentView(R.layout.activity_check_history_post_item);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        status = intent.getIntExtra("status", -1);
        HistoryPostBean.PageBean.ListBean listBean = (HistoryPostBean.PageBean.ListBean) intent.getSerializableExtra("ListBean");
        status=listBean.getStatus();
        mobile = listBean.getMobile();
        productname = listBean.getProductname();
        productcount = listBean.getProductcount();
        createtime = listBean.getCreatetime();
        remark = listBean.getRemark();
        initTitle();
        initData();
    }

    private void initData() {
        realName = SharedPrefHelper.getInstance().getRealName();
        tvName.setText("姓名: " + realName);
        etPhone.setText(mobile);
        etSpName.setText(productname);
        etSpCount.setText(productcount+"个");
        etWrite.setText(remark);
        if(isEdit==false){
            etPhone.setFocusable(false);
            etSpCount.setFocusable(false);
            etSpName.setFocusable(false);
            etWrite.setFocusable(false);
            etPhone.setFocusableInTouchMode(false);
            etSpCount.setFocusableInTouchMode(false);
            etSpName.setFocusableInTouchMode(false);
            etWrite.setFocusableInTouchMode(false);
            tvCommit.setEnabled(false);
        }else{
            etPhone.setFocusable(true);
            etSpCount.setFocusable(true);
            etSpName.setFocusable(true);
            etWrite.setFocusable(true);
            etPhone.setFocusableInTouchMode(true);
            etSpCount.setFocusableInTouchMode(true);
            etSpName.setFocusableInTouchMode(true);
            etWrite.setFocusableInTouchMode(true);
            etPhone.requestFocus();
            etSpCount.requestFocus();
            etSpName.requestFocus();
            etWrite.requestFocus();
            tvCommit.setEnabled(true);
        }
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("其他需求");
        if(status==2||status==3){
            tvEdit.setEnabled(false);
            tvEdit.setTextColor(Color.GRAY);
            if(status==2){
                tvCommit.setText("审核中");
                tvCommit.setBackgroundColor(Color.parseColor("#333333"));//审核中
            }else{
                tvCommit.setText("审核成功");
                tvCommit.setBackgroundColor(Color.parseColor("#00CC33"));//审核成功
            }

        }else if(status==1||status==4){
            if(status==1){
                tvCommit.setBackgroundColor(Color.parseColor("#03A7DA"));//未审核
            }else{
                tvCommit.setBackgroundColor(Color.parseColor("#FF2F2F"));//审核失败
            }
            tvCommit.setText("重新审核");
            tvEdit.setEnabled(true);
            tvEdit.setTextColor(Color.parseColor("#333333"));
        }
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEdit==false){
                    tvEdit.setText("编辑");
                    tvEdit.setTextColor(Color.parseColor("#333333"));
                }else{
                    tvEdit.setTextColor(Color.GRAY);
                }
                isEdit=!isEdit;
            }
        });

    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        String phone = etPhone.getText().toString().trim();
        String remark = etWrite.getText().toString().trim();
        String spName = etSpName.getText().toString().trim();
        String spCount = etSpCount.getText().toString().trim();
        getPresenter().post(realName, phone, remark, spName, spCount);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
