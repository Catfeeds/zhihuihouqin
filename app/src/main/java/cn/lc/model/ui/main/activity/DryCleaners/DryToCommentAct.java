package cn.lc.model.ui.main.activity.DryCleaners;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.model.DryToCommentModel;
import cn.lc.model.ui.main.modelimpl.DryToCommentModelImpl;
import cn.lc.model.ui.main.presenter.DryToCommentPresenter;
import cn.lc.model.ui.main.view.DryToCommentView;

public class DryToCommentAct extends BaseActivity<DryToCommentModel,DryToCommentView,DryToCommentPresenter> implements DryToCommentView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_dry)
    TextView tvDry;
    @BindView(R.id.tv_manyidu)
    TextView tvManyidu;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.et_write_comment)
    EditText etWriteComment;
    @BindView(R.id.take)
    ImageView take;
    @BindView(R.id.rb_niming)
    RadioButton rbNiming;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.activity_dry_to_comment)
    RelativeLayout activityDryToComment;

    @Override
    public DryToCommentPresenter createPresenter() {
        return new DryToCommentPresenter();
    }

    @Override
    public DryToCommentModel createModel() {
        return new DryToCommentModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_dry_to_comment);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.take, R.id.rb_niming, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.take:
                break;
            case R.id.rb_niming:
                break;
            case R.id.tv_commit:
                break;
        }
    }

    @Override
    public void commentSucc(CollectBean collectBean) {
        if(collectBean.getErrCode()==0){
            showToast("评论成功");
        }else{
            showToast("评论失败了");
        }
    }
}
