package com.moe.wl.ui.main.activity.medicalService;

import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.MedicalDetailBean;
import com.moe.wl.ui.main.model.MedicalDetailModel;
import com.moe.wl.ui.main.modelimpl.MedicalDetailModelImpl;
import com.moe.wl.ui.main.presenter.CollectPresenter;
import com.moe.wl.ui.main.view.CollectView;
import com.moe.wl.ui.mywidget.CommentPop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class HealthConsultDetailActivity extends BaseActivity<MedicalDetailModel, CollectView, CollectPresenter> implements CollectView {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_comment)
    TextView etComment;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.main)
    LinearLayout main;

    private int id;
    private CommentPop pop;

    @Override
    public CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public MedicalDetailModel createModel() {
        return new MedicalDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_medical_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        getPresenter().getDetail(id);
    }

    private void setUI(MedicalDetailBean bean) {
        if (bean == null) {
            ToastUtil.showToast(this, "内容为空！");
            return;
        }
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getInfo().getImgs(), image, R.mipmap.ic_default_rectangle);
        title.setText(bean.getInfo().getTitle());
        from.setText("来源：" + bean.getInfo().getSource());
        time.setText(bean.getInfo().getCreatetime());
        content.setText(Html.fromHtml(bean.getInfo().getContent()));
    }


    @OnClick({R.id.back, R.id.collect, R.id.et_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.collect:
                getPresenter().getData(8, id);
                break;

            case R.id.et_comment:
                pop = new CommentPop(HealthConsultDetailActivity.this, new CommentPop.OnCommentListener() {
                    @Override
                    public void onListener(String content) {
                        // 评论
                        getPresenter().submitComment(id, content);
                    }
                });
                pop.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    public void getCollectResult(CollectBean collectBean) {
        if (collectBean.getStatus() == 1) {
            showToast("已收藏");
            collect.setImageResource(R.drawable.collected);
        } else {
            showToast("取消收藏");
            collect.setImageResource(R.drawable.collect);
        }
    }

    @Override
    public void submitCommentSucc(CollectBean bean) {
        ToastUtil.showToast(this, "评论成功！");
    }

    @Override
    public void getDetailSucc(MedicalDetailBean bean) {
        setUI(bean);
    }
}
