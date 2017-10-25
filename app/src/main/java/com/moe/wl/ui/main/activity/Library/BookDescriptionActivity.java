package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.BookDetailBean;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.BookDetailModel;
import com.moe.wl.ui.main.modelimpl.BookDetailModelImpl;
import com.moe.wl.ui.main.presenter.BookDetailPresenter;
import com.moe.wl.ui.main.view.BookDetailView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.tencent.qq.QQ;
import lc.cn.thirdplatform.sharesdk.onekeyshare.OnekeyShare;

/**
 * 图书馆-书籍简介
 */
public class BookDescriptionActivity extends BaseActivity<BookDetailModel, BookDetailView, BookDetailPresenter> implements BookDetailView, View.OnClickListener {


    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.iv_book_pic)
    ImageView ivBookPic;
    @BindView(R.id.tv_book_name)
    TextView tvBookName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_chubanshe)
    TextView tvChubanshe;
    @BindView(R.id.tv_authors_introduce)
    TextView tvAuthorsIntroduce;
    @BindView(R.id.tv_authors_introduce_content)
    TextView tvAuthorsIntroduceContent;
    @BindView(R.id.tv_book_introduce)
    TextView tvBookIntroduce;
    @BindView(R.id.tv_book_introduce_content)
    TextView tvBookIntroduceContent;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.tv_now_borrowing)
    TextView tvNowBorrowing;
    @BindView(R.id.activity_book_description)
    RelativeLayout activityBookDescription;

    private BooklistBean bean;
    private boolean again;
    private static final int TYPE = 4;

    @Override
    public BookDetailPresenter createPresenter() {
        return new BookDetailPresenter();
    }

    @Override
    public BookDetailModel createModel() {
        return new BookDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_book_description);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        bean = (BooklistBean) intent.getSerializableExtra("bean");
        again = intent.getBooleanExtra("again", false);

        if (bean!=null && !TextUtils.isEmpty(bean.getId())){
            getPresenter().getDetail(bean.getId());
        }else{
            finish();
        }

        initTitle();
        initAllViw();
    }

    private void initAllViw() {
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getImg(), ivBookPic, R.mipmap.ic_default_book);

        tvBookName.setText(bean.getTitle());
        ratingBar.setRating(((float) bean.getScore()));
        OtherUtils.ratingBarColor(ratingBar,this);
        tvStarNum.setText(bean.getScore() + "分");
        tvAuthor.setText(bean.getAuthor());
        tvChubanshe.setText(bean.getPublisher());

        if (bean.getBollowstatus() == 1) {
            tvState.setText("在架上");
            tvState.setTextColor(Color.parseColor("#36CCAE"));
        } else {
            tvState.setText("已借出");
            tvState.setTextColor(Color.parseColor("#F95759"));
        }
    }

    @Override
    public void collectSucc(CollectBean collectBean) {
        if (collectBean.getStatus() == 0) {
            showToast("取消收藏");
            ivCollect.setImageResource(R.drawable.collect);
        } else {
            showToast("收藏成功");
            ivCollect.setImageResource(R.drawable.collected);
        }
    }

    @Override
    public void getDetail(BookDetailBean bean) {
        tvAuthorsIntroduceContent.setText(bean.getAuthorbrief());
        tvBookIntroduceContent.setText(bean.getContent());
        int favorstatus = bean.getFavorstatus();
        if(favorstatus==1){//收藏
            ivCollect.setImageResource(R.drawable.collected);
        }else if(favorstatus==0){//没有收藏
            ivCollect.setImageResource(R.drawable.collect);
        }
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("书籍简介");
    }

    @OnClick({R.id.ll_share, R.id.ll_collect, R.id.tv_now_borrowing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_share:
                // sharePopWindow();
                //showShare("测试", "智慧后勤", "http://www.baidu.com", "http://casemeet.oss-cn-beijing.aliyuncs.com/2017080214260236353118.png");
                break;
            case R.id.ll_collect:
                getPresenter().getData(TYPE, bean.getId());
                break;
            case R.id.tv_now_borrowing://立即借阅
                if (bean.getBollowstatus() == 1) {
                    if (again){
                        //再次借阅的的直接进去确认订单页面
                        Intent intent = new Intent(this, BookConfirmOrderActivity.class);
                        intent.putExtra("bean", (Serializable) bean);
                        LogUtils.d("---------------again-------------------"+again);
                        intent.putExtra("again", again);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(this, BorrowOrderActivity.class);
                        intent.putExtra("bean", (Serializable) bean);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    showToast("此书已经被借阅,请选择其它书籍");
                }
                break;
        }
    }

    private void showShare(String titles, String content, String currenturl,
                           String imgurl) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(titles);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(currenturl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(imgurl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(currenturl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(titles);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        oks.setPlatform(QQ.NAME);
// 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wf:
//                showShare("测试", "智慧后勤", "http://www.baidu.com", "http://casemeet.oss-cn-beijing.aliyuncs.com/2017080214260236353118.png");
                break;
            case R.id.iv_wx:
                break;
            case R.id.iv_qq:
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
