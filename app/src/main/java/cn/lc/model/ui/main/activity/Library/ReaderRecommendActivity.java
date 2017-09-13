package cn.lc.model.ui.main.activity.Library;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.SubmitSuccessActivity;
import cn.lc.model.ui.main.bean.RecommandBookBean;
import cn.lc.model.ui.main.model.RecommandBookModel;
import cn.lc.model.ui.main.modelimpl.RecommandVBookModelImpl;
import cn.lc.model.ui.main.presenter.RecommandBookPresenter;
import cn.lc.model.ui.main.view.RecommandBookView;

public class ReaderRecommendActivity extends BaseActivity<RecommandBookModel, RecommandBookView, RecommandBookPresenter> implements RecommandBookView {

    private static final boolean BOOKSUBMIT = false;
    @BindView(R.id.reader_commend_title)
    TitleBar titleBar;
    @BindView(R.id.view_down)
    View viewDown;
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
            showToast("请讲信息填写完整");
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
