package com.moe.wl.ui.main.activity.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.main.adapter.InformationCommentAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.InformationDetailBean;
import com.moe.wl.ui.mywidget.CommentPop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/18 0018
 */
public class InformationDetailActivity extends AppCompatActivity {

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
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_comment)
    TextView etComment;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.scroll)
    ScrollView scroll;

    private int informationID;
    private CustomerDialog progressDialog;
    private CommentPop pop;
    private InformationCommentAdapter adapter;

    private int serviceType = 1;

    private boolean isCollect = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        informationID = getIntent().getIntExtra("ID", 0);
        if (informationID == 0) {
            ToastUtil.showToast(this, "数据错误！");
            finish();
        }
        getData();
    }

    @OnClick({R.id.back, R.id.collect, R.id.et_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.collect: // 收藏
                if (isCollect) {
                    ToastUtil.showToast(InformationDetailActivity.this, "已收藏该公告！");
                    return;
                }
                collect();
                break;

            case R.id.et_comment:
                pop = new CommentPop(InformationDetailActivity.this, new CommentPop.OnCommentListener() {
                    @Override
                    public void onListener(String content) {
                        submitComment(content);
                    }
                });
                pop.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
                break;
        }
    }


    // 设置页面数据
    private void setUI(InformationDetailBean bean) {
        if (bean.getNoticeInfo() != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getNoticeInfo().getImg(), image);
            title.setText(bean.getNoticeInfo().getTitle());
            time.setText(bean.getNoticeInfo().getCreatetime());
            from.setText("来源：" + bean.getNoticeInfo().getSource());
            content.setText(Html.fromHtml(bean.getNoticeInfo().getContent()));
        }

        if (bean.getFavorNum() != 0) {
            isCollect = true;
        }

        if (bean.getCommentList() != null) {
            adapter = new InformationCommentAdapter(this, bean.getCommentList());
            listView.setAdapter(adapter);
        }
        scroll.smoothScrollTo(0, 20);
    }

    // 获取详情
    private void getData() {
        Observable observable = RetrofitUtils.getInstance().getInformationDetail(informationID);
        showProgressDialog();
        observable.subscribe(new Subscriber<InformationDetailBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(InformationDetailBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    setUI(orderBean);
                    int favorNum = orderBean.getFavorNum();
                    if(favorNum==0){
                        collect.setImageResource(R.drawable.collect);
                    }else if(favorNum==1){
                        collect.setImageResource(R.drawable.collected);
                    }
                }
            }
        });
    }

    // 评论
    private void submitComment(String content) {
        Observable observable = RetrofitUtils.getInstance().informationDetailComment(informationID, content);
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    ToastUtil.showToast(InformationDetailActivity.this, "评论成功！");
                }
            }
        });
    }

    // 添加收藏
    private void collect() {
        Observable observable = RetrofitUtils.getInstance().addCollect(serviceType, informationID+"");
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    int status = orderBean.getStatus();
                    if(status==0){
                        collect.setImageResource(R.drawable.collect);
                    }else if(status==1){
                        collect.setImageResource(R.drawable.collected);
                    }
                    ToastUtil.showToast(InformationDetailActivity.this, "收藏成功！");
                } else {
                    ToastUtil.showToast(InformationDetailActivity.this, orderBean.getMsg());
                }
            }
        });
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog() {
        progressDialog.show();
    }
}
