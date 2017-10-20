package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.RecommandBookBean;
import com.moe.wl.ui.main.model.RecommandBookModel;
import com.moe.wl.ui.main.modelimpl.RecommandVBookModelImpl;
import com.moe.wl.ui.main.presenter.RecommandBookPresenter;
import com.moe.wl.ui.main.view.RecommandBookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 讀者推薦
 */
public class ReaderRecommendActivity extends BaseActivity<RecommandBookModel, RecommandBookView, RecommandBookPresenter> implements RecommandBookView {

    private static final boolean BOOKSUBMIT = false;
    @BindView(R.id.reader_commend_title)
    TitleBar titleBar;
    @BindView(R.id.et_book_name)
    EditText etBookName;
    @BindView(R.id.author_name)
    EditText authorName;
    @BindView(R.id.chubanshe_name)
    EditText chubansheName;
    @BindView(R.id.et_write_your_book_name)
    EditText etWriteYourBookName;
    @BindView(R.id.submit)
    TextView submit;
    private String bookName;
    private String author;
    private String pressName;
    private String book;
    private String writeBookDetail;

    @Override
    public RecommandBookPresenter createPresenter() {
        return new RecommandBookPresenter();
    }

    @Override
    public RecommandBookModel createModel() {
        return new RecommandVBookModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_reader_recommend);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();

    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("读者推荐");
    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
        getReaderCommend();
        if (TextUtils.isEmpty(bookName)||TextUtils.isEmpty(author)||
                TextUtils.isEmpty(pressName)) {
            showToast("请信息填写完整");
        } else {
            getPresenter().getData(book, author, pressName, writeBookDetail);
        }
    }

    public void getReaderCommend() {
        bookName = etBookName.getText().toString().trim();
        author = authorName.getText().toString().trim();
        pressName = chubansheName.getText().toString().trim();
        writeBookDetail = etWriteYourBookName.getText().toString().trim();
    }
    @Override
    public void getRecommandResult(RecommandBookBean recommandBookBean) {
        if(recommandBookBean.getErrCode()==0){
            Intent intent =new Intent(this, JieYueSuccActivity.class);
            intent.putExtra("from",1);
            intent.putExtra("bookSubmit",BOOKSUBMIT);
            startActivity(intent);
            finish();
        }else{
            showToast(recommandBookBean.getMsg());
        }
    }
}
