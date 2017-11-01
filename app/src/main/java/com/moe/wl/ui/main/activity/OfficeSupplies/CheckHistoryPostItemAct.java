package com.moe.wl.ui.main.activity.OfficeSupplies;

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
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Library.JieYueSuccActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.HistoryPostBean;
import com.moe.wl.ui.main.model.PostNeedModel;
import com.moe.wl.ui.main.modelimpl.PostNeedModelImpl;
import com.moe.wl.ui.main.presenter.PostNeedPresenter;
import com.moe.wl.ui.main.view.PostNeedView;

public class CheckHistoryPostItemAct extends BaseActivity<PostNeedModel, PostNeedView, PostNeedPresenter> implements PostNeedView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_name)
    TextView name;
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
    private boolean isEnableEdit;

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
        //审核的状态
        status = intent.getIntExtra("status", 1);
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
        name.setText( realName);
        etPhone.setText(mobile);
        etSpName.setText(productname);
        etSpCount.setText(productcount+"");
        etWrite.setText(remark);
        edit();
    }

    private void edit() {
        if(isEdit){
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
            //tvCommit.setEnabled(true);
        }else{
            etPhone.setFocusable(false);
            etSpCount.setFocusable(false);
            etSpName.setFocusable(false);
            etWrite.setFocusable(false);
            etPhone.setFocusableInTouchMode(false);
            etSpCount.setFocusableInTouchMode(false);
            etSpName.setFocusableInTouchMode(false);
            etWrite.setFocusableInTouchMode(false);
            //tvCommit.setEnabled(false);
        }
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("其他需求");
        if(status==2||status==3){
            isEdit=false;
            isEnableEdit = false;
            tvEdit.setEnabled(false);
            tvEdit.setTextColor(Color.GRAY);
            if(status==2){
                tvCommit.setText("审核中");
                tvCommit.setBackgroundColor(Color.parseColor("#333333"));//审核中
            }else{
                tvCommit.setText("审核成功");
                tvCommit.setBackgroundColor(Color.parseColor("#00CC33"));//审核成功
            }
            tvCommit.setEnabled(false);

        }else if(status==1||status==4){
            if(status==1){
                tvCommit.setText("未审核");
                tvCommit.setBackgroundColor(Color.parseColor("#03A7DA"));//未审核
            }else{
                tvCommit.setText("审核失败");
                tvCommit.setBackgroundColor(Color.parseColor("#FF2F2F"));//审核失败
            }
            isEnableEdit = true;
            //tvCommit.setText("重新审核");
            tvEdit.setEnabled(true);
            tvCommit.setEnabled(true);
            tvEdit.setTextColor(Color.parseColor("#333333"));
        }
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEdit){
                    tvEdit.setText("编辑");
                    tvCommit.setText("审核失败");
                    //tvEdit.setTextColor(Color.parseColor("#333333"));
                }else{
                    tvEdit.setText("完成");
                    tvCommit.setText("重新审核");
                    //tvEdit.setTextColor(Color.GRAY);
                }
                isEdit=!isEdit;
                edit();
                LogUtils.i("isEdit==="+isEdit);
            }
        });

    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        if(isEdit){
            String phone = etPhone.getText().toString().trim();
            String remark = etWrite.getText().toString().trim();
            String spName = etSpName.getText().toString().trim();
            String spCount = etSpCount.getText().toString().trim();
            getPresenter().post(realName, phone, remark, spName, spCount);
        }else{
            Intent intent = new Intent(this, CheckFailActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void postSuccess(ActivityPostBean bean) {
        if (bean != null) {
            Intent intent = new Intent(this, JieYueSuccActivity.class);
            intent.putExtra("from",3);
            startActivity(intent);
            finish();
        } else {
            showToast(bean.getMsg());
        }
    }

}
