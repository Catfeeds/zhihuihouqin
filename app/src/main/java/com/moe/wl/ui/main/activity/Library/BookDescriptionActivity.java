package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.BookDetailBean;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.modelimpl.BookDetailModelImpl;
import com.moe.wl.ui.main.view.BookDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.model.BookDetailModel;
import com.moe.wl.ui.main.presenter.BookDetailPresenter;

import cn.sharesdk.tencent.qq.QQ;
import lc.cn.thirdplatform.sharesdk.onekeyshare.OnekeyShare;

public class BookDescriptionActivity extends BaseActivity<BookDetailModel, BookDetailView, BookDetailPresenter> implements BookDetailView, View.OnClickListener {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.wb_book_detail)
    WebView wbBookDetail;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_now_borrowing)
    TextView tvNowBorrowing;
    @BindView(R.id.activity_book_description)
    RelativeLayout activityBookDescription;

    private BooklistBean bookListvBean;
    private int bookId;
    private String title;
    private boolean again;
    private int bollowstatus;

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
        bookListvBean = (BooklistBean) intent.getSerializableExtra("bookListvBean");
        bookId = bookListvBean.getId();
        LogUtils.i("BookDescriptionActivity 的bookId为:" + bookId);
        title = bookListvBean.getTitle();
        String url = bookListvBean.getUrl();
        bollowstatus = bookListvBean.getBollowstatus();
        again = intent.getBooleanExtra("again", false);
        //加载静态界面
        wbBookDetail.loadUrl(url);

        initTitle();
        initAllViw();
    }

    private void initAllViw() {
     /*   GlideLoading.getInstance().loadImgUrlNyImgLoader(this,bookListvBean.getImg(),ivBookPic);
        tvBookName.setText(bookListvBean.getTitle());
        ratingBar.setRating(bookListvBean.getScore());
        tvStarNum.setText(bookListvBean.getScore()+"分");
        tvAuthor.setText(bookListvBean.getAuthor());
        tvChubanshe.setText(bookListvBean.getPublisher());
        if(bookListvBean.getBollowstatus()==1){
            tvState.setText("在架上");
            tvState.setTextColor(Color.parseColor("#36CCAE"));
        }else{
            tvState.setText("已借出");
            tvState.setTextColor(Color.parseColor("#F95759"));
        }*/
    }

    @Override
    public void getBookDetailSucc(BookDetailBean detailBean) {
        if (detailBean != null) {
            if (detailBean.getFavorstatus() == 1) {
                showToast("收藏");
            } else if (detailBean.getFavorstatus() == 0) {
                showToast("取消收藏");
            } else {
                showToast("其他");
            }
        }
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("书籍简介");
    }

    @OnClick({R.id.tv_share, R.id.tv_collect, R.id.tv_now_borrowing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                // sharePopWindow();
                //showShare("测试", "智慧后勤", "http://www.baidu.com", "http://casemeet.oss-cn-beijing.aliyuncs.com/2017080214260236353118.png");
                break;
            case R.id.tv_collect:
                getPresenter().getData(bookId);
                break;
            case R.id.tv_now_borrowing://立即借阅
                if (bollowstatus == 1) {

                        Intent intent = new Intent(this, BorrowOrderActivity.class);
                        intent.putExtra("bookId", bookId + "");
                        intent.putExtra("bookName", title);
                        startActivity(intent);
                        finish();
                } else {
                    showToast("此书已经被借阅,请选择其它书籍");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(2000);
                            finish();
                        }
                    }).start();
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
