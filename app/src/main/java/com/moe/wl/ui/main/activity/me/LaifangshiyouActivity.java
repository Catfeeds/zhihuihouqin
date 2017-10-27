package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.widget.EditText;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.SaveAdviceModel;
import com.moe.wl.ui.main.modelimpl.SaveAdviceModelImpl;
import com.moe.wl.ui.main.presenter.SaveAdvicePresenter;
import com.moe.wl.ui.main.view.SaveAdviceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaifangshiyouActivity extends BaseActivity<SaveAdviceModel, SaveAdviceView, SaveAdvicePresenter> implements SaveAdviceView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_shiyou)
    EditText etShiyou;

    private String from;
    private String reason = "";

    @Override
    public SaveAdvicePresenter createPresenter() {
        return new SaveAdvicePresenter();
    }

    @Override
    public SaveAdviceModel createModel() {
        return new SaveAdviceModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_laifangshiyou);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        from = getIntent().getStringExtra("from");
        reason = getIntent().getStringExtra("arriveReason");
        initTitle();
    }

    private void initTitle() {
        if (Constants.YIJIANFANKUI.equals(from)) {
            title.setTitle("意见反馈");
            etShiyou.setHint("请在这里写下您宝贵的意见，让我们不断进步，更好的为您服务！");
            title.setBack(true);
        } else {
            etShiyou.setHint("请输入来访事由");
            etShiyou.setText(reason);
//            etShiyou.setSelection(reason.length());
            title.setTitle("来访事由");
            title.setBack(true);
        }
    }

    @OnClick(R.id.tb_confirm)
    public void onViewClicked() {
        String shiyou = etShiyou.getText().toString().trim();
        Intent intent = new Intent();
        if (Constants.YIJIANFANKUI.equals(from)) {
            intent.putExtra("yijianfankui", shiyou);
            setResult(Constants.SHIYOU, intent);
            getPresenter().saveAdvice(shiyou);//提交意见反馈
        } else {
            intent.putExtra("shiyou", shiyou);
            setResult(Constants.SHIYOU, intent);
        }
        finish();
    }

    @Override
    public void saveAdviceResult(ActivityPostBean bean) {
        if (bean != null) {
            showToast("提交反馈成功");
        }
    }
}
