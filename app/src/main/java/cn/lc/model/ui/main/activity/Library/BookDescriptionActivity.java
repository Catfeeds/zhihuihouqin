package cn.lc.model.ui.main.activity.Library;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.bean.BookDetailBean;
import cn.lc.model.ui.main.bean.LibraryHomeBean;
import cn.lc.model.ui.main.model.BookDetailModel;
import cn.lc.model.ui.main.modelimpl.BookDetailModelImpl;
import cn.lc.model.ui.main.presenter.BookDetailPresenter;
import cn.lc.model.ui.main.view.BookDetailView;

public class BookDescriptionActivity extends BaseActivity<BookDetailModel,BookDetailView,BookDetailPresenter> implements BookDetailView {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.iv_book_pic)
    ImageView ivBookPic;
    @BindView(R.id.tv_book_name)
    TextView tvBookName;
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
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_now_borrowing)
    TextView tvNowBorrowing;
    @BindView(R.id.activity_book_description)
    RelativeLayout activityBookDescription;
    private LibraryHomeBean.BooklistBean bookListvBean;

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
        bookListvBean = (LibraryHomeBean.BooklistBean) getIntent().getSerializableExtra("bookListvBean");
        int bookId= bookListvBean.getId();
        getPresenter().getData(bookId);
        initTitle();
        initAllViw();
    }

    private void initAllViw() {
       /* GlideLoading.getInstance().loadImgUrlNyImgLoader(this,bookListvBean.getImg(),ivBookPic);
        tvBookName.setText(bookListvBean.getTitle());
        ratingBar.setRating(bookListvBean.getScore());
        tvStarNum.setText(bookListvBean.getScore()+"分");
        tvAuthor.setText(bookListvBean.getAuthor());
        tvChubanshe.setText(bookListvBean.getPublisher());*/
        /*if(bookListvBean.getBollowstatus()==1){
            tvState.setText("在架上");
            tvState.setTextColor(Color.parseColor("#36CCAE"));
        }else{
            tvState.setText("已借出");
            tvState.setTextColor(Color.parseColor("#F95759"));
        }*/
    }

    @Override
    public void getBookDetailSucc(BookDetailBean detailBean) {
    if(detailBean!=null){
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
                break;
            case R.id.tv_collect:
                break;
            case R.id.tv_now_borrowing://立即借阅
                Intent intent=new Intent(this,BorrowOrderActivity.class);
                startActivity(intent);
                break;
        }
    }


}
