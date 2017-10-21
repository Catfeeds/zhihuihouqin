package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.JieYueBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

/**
 * 确认订单
 */
public class BookConfirmOrderActivity extends Base2Activity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.tv_take_book_time)
    TextView tvTakeBookTime;
    @BindView(R.id.ll_book_container)
    LinearLayout llBookContainer;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.activity_book_confirm_order)
    LinearLayout activityBookConfirmOrder;
    @BindView(R.id.tv_again)
    TextView tvAgain;
    private String realName;
    private String mobile;

    private BooklistBean bean;
    private String time;
    private boolean again;
    private List<BooklistBean> bookList;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_book_confirm_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        BooklistBean bean = (BooklistBean) intent.getSerializableExtra("bean");
        boolean again = intent.getBooleanExtra("again",false);
        if (again){
            if (bookList!=null){
                bookList.add(bean);
            }
        }
    }

    @Override
    protected void initView() {
        initTitle();
        Intent intent = getIntent();
        bean = (BooklistBean) intent.getSerializableExtra("bean");
        time = intent.getStringExtra("time");
        again = intent.getBooleanExtra("again",false);
        bookList=new ArrayList<>();

        realName = SharedPrefHelper.getInstance().getRealName();
        if (TextUtils.isEmpty(realName)){
            realName=SharedPrefHelper.getInstance().getNickname();
        }
        mobile = SharedPrefHelper.getInstance().getMobile();

        tvName.setText(realName);
        tvPhoneNum.setText(mobile);
        tvTakeBookTime.setText(time);

        bookList.add(bean);
        showBook();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确认订单");
    }

    public void showBook(){
        if (bookList!=null && bookList.size()>0){
            for (int i = 0; i <bookList.size() ; i++) {
                View view = View.inflate(this, R.layout.book_name_item, null);
                TextView name = (TextView) view.findViewById(R.id.book_name);
                name.setText(bookList.get(i).getTitle());
                llBookContainer.addView(view);
            }
        }
    }

    @OnClick({R.id.tv_again, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_again:
                if (bookList.size() < 3) {
                    Intent intent = new Intent(this, LibraryActivity.class);
                    intent.putExtra("again", true);
                    intent.putExtra("list",(Serializable) bookList);
                    startActivity(intent);
                } else {
                    showToast("单次最多可以借三本书");
                }
                break;
            case R.id.tv_confirm:
                getData();
                break;
        }
    }

    public void getData() {
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <bookList.size() ; i++) {
            if (sb.length()!=0){
                sb.append(",");
            }
            sb.append(bookList.get(i).getId());
        }
        Observable observable = RetrofitUtils.getInstance().jieYueBook(time, realName, mobile, sb.toString());
        showProgressDialog();
        observable.subscribe(new Subscriber<JieYueBean>() {

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.d("借阅出现问题", e.getMessage());
            }

            @Override
            public void onNext(JieYueBean jieYueBean) {
                SharedPrefHelper.getInstance().saveBookName(null);
                SharedPrefHelper.getInstance().saveBookId(null);
                SharedPrefHelper.getInstance().saveTime(null);
                if (jieYueBean.getErrCode() == 0) {
                    Intent intent = new Intent(BookConfirmOrderActivity.this, JieYueSuccActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    LogUtils.i("问题:" + jieYueBean.getMsg());
                }
            }
        });
    }
}
