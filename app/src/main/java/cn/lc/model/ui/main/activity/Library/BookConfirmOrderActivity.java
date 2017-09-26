package cn.lc.model.ui.main.activity.Library;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.bean.JieYueBean;
import rx.Observable;
import rx.Subscriber;

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
    private String time1;
    private String[] bookNames;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_book_confirm_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        Intent intent = getIntent();
        String bookId = intent.getStringExtra("bookId");
        LogUtils.i("bookId:为:"+bookId);
        String bookName = intent.getStringExtra("bookName");
        String time = intent.getStringExtra("time");
        if(time!=null){
            SharedPrefHelper.getInstance().saveTime(time);
        }
        realName = SharedPrefHelper.getInstance().getRealName();
        mobile = SharedPrefHelper.getInstance().getMobile();
        tvName.setText(realName);
        tvPhoneNum.setText(mobile);
        time1 = SharedPrefHelper.getInstance().getTime();
        tvTakeBookTime.setText(time1);
        String bookName2 = SharedPrefHelper.getInstance().getBookName();
        if(bookName2!=null){
            if(bookName2.equals("")){
                SharedPrefHelper.getInstance().saveBookName(bookName);
            }else{
                SharedPrefHelper.getInstance().saveBookName(bookName2+","+bookName);
            }
        }
        String bookId2 = SharedPrefHelper.getInstance().getBookId();
        if(bookId2!=null){
            if(bookId2.equals("")){
                SharedPrefHelper.getInstance().saveBookId(bookId);
            }else{
                SharedPrefHelper.getInstance().saveBookId(bookId2+","+bookId);
            }
        }

        String bookName3 = SharedPrefHelper.getInstance().getBookName();
        bookNames = bookName3.split(",");
        for (int i = 0; i < bookNames.length; i++) {
            View view = View.inflate(this, R.layout.book_name_item, null);
            TextView name = (TextView) view.findViewById(R.id.book_name);
            name.setText(bookNames[i]);
            llBookContainer.addView(view);
        }
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确认订单");
    }

    @OnClick({R.id.tv_again, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_again:

                if(bookNames.length<3){
                    Intent intent = new Intent(this, LibraryActivity.class);
                    intent.putExtra("again",true);
                    startActivity(intent);
                }else {
                    showToast("单次最多可以借三本书");
                }
                break;
            case R.id.tv_confirm:
                getData();
                break;
        }
    }

    public void getData() {
        String bookId = SharedPrefHelper.getInstance().getBookId();
        String time=SharedPrefHelper.getInstance().getTime();
        Observable observable = RetrofitUtils.getInstance().jieYueBook(time, realName, mobile, bookId);
        showProgressDialog();
        observable.subscribe(new Subscriber<JieYueBean>() {

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("借阅出现问题",e.getMessage());
            }

            @Override
            public void onNext(JieYueBean jieYueBean) {
                SharedPrefHelper.getInstance().saveBookName(null);
                SharedPrefHelper.getInstance().saveBookId(null);
                SharedPrefHelper.getInstance().saveTime(null);
                if(jieYueBean.getErrCode()==0){
                    Intent intent = new Intent(BookConfirmOrderActivity.this, JieYueSuccActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    LogUtils.i("问题:"+jieYueBean.getMsg());
                }
            }
        });
    }
}
