package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.activity.Library.JieYueSuccActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.wheelView.WheelView;
import rx.Observable;
import rx.Subscriber;

import static com.moe.wl.R.id.laifang_title;

public class LaiFangActivity extends Base2Activity {

    private static final int LAIFANGSHIYOUREQUEST = 10;
    @BindView(laifang_title)
    TitleBar laifangTitle;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_room_num)
    EditText etRoomNum;
    @BindView(R.id.rl_laifangshiyou)
    RelativeLayout rlLaifangshiyou;
    @BindView(R.id.rl_revice_time)
    RelativeLayout rlReviceTime;
    @BindView(R.id.rl_likai_time)
    RelativeLayout rlLikaiTime;
    @BindView(R.id.rb_once)
    RadioButton rbOnce;
    @BindView(R.id.rb_a_week)
    RadioButton rbAWeek;
    @BindView(R.id.rb_bangeyue)
    RadioButton rbBangeyue;
    @BindView(R.id.rb_long)
    RadioButton rbLong;
    @BindView(R.id.tv_car_type)
    TextView tvCarType;
    @BindView(R.id.rl_car_type)
    RelativeLayout rlCarType;
    @BindView(R.id.tv_shengfen)
    TextView tvShengfen;
    @BindView(R.id.et_chepaihao)
    EditText etChepaihao;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.activity_lai_fang)
    RelativeLayout activityLaiFang;
    private String str;
    private List<String> lists = Arrays.asList("京", "津", "冀", "晋", "蒙", "辽",
            "吉", "黑", "沪", "苏", "浙", "皖"
            , "闽", "赣", "鲁", "豫", "鄂", "湘"
            , "粤", "桂", "琼", "川", "贵", "云"
            , "渝", "藏", "陕", "甘", "青", "宁"
            , "新");
    private List<String> listTwo = Arrays.asList("A", "B", "C", "D", "E", "F"
            , "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "X"
            , "Y", "Z");
    private String shiyou;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_lai_fang);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        laifangTitle.setBack(true);
        laifangTitle.setTitle("来访人员");
    }

    @OnClick({R.id.tv_commit, R.id.tv_shengfen, R.id.rl_laifangshiyou, R.id.rl_revice_time, R.id.rl_likai_time, R.id.rb_once, R.id.rb_a_week, R.id.rb_bangeyue, R.id.rb_long, R.id.rl_car_type, R.id.activity_lai_fang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                postInfo();
                break;
            case R.id.tv_shengfen:
                showShengFenDialog();
                break;
            case R.id.rl_laifangshiyou:
                Intent intent = new Intent(this, LaifangshiyouActivity.class);
                startActivityForResult(intent, LAIFANGSHIYOUREQUEST);
                break;
            case R.id.rl_revice_time:
                showTime();
                break;
            case R.id.rl_likai_time:
                showTime();
                break;
            case R.id.rb_once:
                break;
            case R.id.rb_a_week:
                break;
            case R.id.rb_bangeyue:
                break;
            case R.id.rb_long:
                break;
            case R.id.rl_car_type:
                break;
            case R.id.activity_lai_fang:
                break;
        }
    }

    private void postInfo() {
        String username = etName.getText().toString().trim();
        String mobile = etPhone.getText().toString().trim();
        String roomunm = etRoomNum.getText().toString().trim();
        String shengfen = tvShengfen.getText().toString().trim();
        String chepaihao = etChepaihao.getText().toString().trim();
        String str = shengfen + chepaihao;
//        if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(mobile)&&!TextUtils.isEmpty(roomunm)&&
//                !TextUtils.isEmpty(shengfen)&&!TextUtils.isEmpty(shiyou)&&!TextUtils.isEmpty())
        Observable observable = RetrofitUtils.getInstance().postBaifagnInfo(username, mobile, roomunm, shiyou, "", "", "", "", str);
        showProgressDialog();
        observable.subscribe(new Subscriber<ActivityPostBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("提交拜访信息失败",e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean postBean) {
                if(postBean.getErrCode()==0){
                    Intent intent =new Intent(LaiFangActivity.this, JieYueSuccActivity.class);
                    intent.putExtra("from",2);
                    startActivity(intent);
                    finish();
                }else{
                    showToast(postBean.getMsg());
                }
            }
        });
    }

    // TODO: 2017/9/11 0011 没有完成
    private void showTime() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.time_wheel);
        WheelView wv1 = (WheelView) window.findViewById(R.id.wv1);
        //NumericWheelAdapter numericWheelAdapter1=new NumericWheelAdapter(this,1950, norYear);
        WheelView wv2 = (WheelView) window.findViewById(R.id.wv2);
        WheelView wv3 = (WheelView) window.findViewById(R.id.wv3);
    }

    private void showShengFenDialog() {
        show(true);
    }

    private void show(final boolean isFirst) {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,alertdialog.xml文件中定义view内容
        window.setContentView(R.layout.view_car_alertdialog);
        GridView gv = (GridView) window.findViewById(R.id.gv_car_num);
        ArrayAdapter<String> adapter = null;
        if (isFirst) {
            str = "";
            tvShengfen.setText(str);
            adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, R.id.tv_item, lists);
        } else {
            adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, R.id.tv_item, listTwo);

        }
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isFirst) {
                    str += lists.get(position);
                    show(false);
                } else {
                    str += listTwo.get(position);
                }
                dlg.cancel();
                tvShengfen.setText(str);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == LAIFANGSHIYOUREQUEST && resultCode == Constants.SHIYOU) {
                shiyou = data.getStringExtra("shiyou");
            }
        }
    }
}
