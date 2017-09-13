package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.GetTimeUtil;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.wheelView.OnWheelChangedListener;
import cn.lc.model.ui.main.wheelView.WheelView;
import cn.lc.model.ui.main.wheelView.adapter.ArrayWheelAdapter;
import cn.lc.model.ui.main.wheelView.adapter.NumericWheelAdapter;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

public class ActivityPostedActivity extends Base2Activity implements View.OnClickListener {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_posted_name)
    EditText etPostedName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_minzu)
    EditText etMinzu;
    @BindView(R.id.et_activity_time)
    ImageView etActivityTime;
    @BindView(R.id.et_activity_address)
    EditText etActivityAddress;
    @BindView(R.id.et_person_limit)
    EditText etPersonLimit;
    @BindView(R.id.et_activity_content)
    EditText etActivityContent;
    @BindView(R.id.add_photo)
    ImageView addPhoto;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_posted)
    LinearLayout activityPosted;
    @BindView(R.id.iv_posted_des)
    ImageView ivPostedDes;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    private BottomSheetDialog dialog;
    private TextView tvConfirm;
    private TextView tvCancle;
    private WheelView dateWeek;
    private WheelView after;
    private WheelView h;
    private WheelView ss;
    private View popupWindowView;
    private TextView tv_qx;
    private TextView tv_cc;
    private PopupWindow popupWindow;
    private WheelView wl_ymd;
    private WheelView wl_week;
    private WheelView wl_sx;
    private WheelView wl_hour;
    private WheelView wl_min;
    //时间年月第一个为空  设置判断
    private int valuenew;
    private int valueold;
    private int valuenew1;
    private int valueold1;
    private int valuenew2;
    private int valueold2;
    private int minll;
    private int minll1;
    private int minll2;
    private int hours;
    private int hours1;
    private int hours2;
    private String mdData[] = new String[720];
    //判断星期使用的数据
    private String ymdAdata[] = new String[720];
    String[] week_str =
            {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    String[] sx_str =
            {"下午", "上午"};
    String[] xiaoshi_start =
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23"};

    String[] fenzhong_start =
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                    "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
                    "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54",
                    "55", "56", "57", "58", "59"};
    private int taday;
    private String value;
    private String value1;
    private String value_h;
    private String value_m;
    private String value_w;
    private String lastweek;
    private String ss_star1;
    private String s_star;
    private String now_taday;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_posted);
        ButterKnife.bind(this);
        initData();
    }

        //时间
        private void initData() {

            long currentTime = System.currentTimeMillis();
            long day = 24 * 60 * 60 * 1000;
            long lastYear = currentTime - day * 360;
            for (int i = 0; i < 720; i++) {
                long time = lastYear + day * i;
                mdData[i] = GetTimeUtil.getYMDTimeR(time);
            }
            //判断星期
            for (int j = 0; j < 720; j++) {
                long time = lastYear + day * j;
                ymdAdata[j] = GetTimeUtil.getYMDTime(time);
            }
        }


    @Override
    protected void initView() {
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("活动发布");
    }

    @OnClick({R.id.iv_posted_des, R.id.rl_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_posted_des:
                Intent intent = new Intent(this, PostedNotesActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_time:
                //底部弹出框
                showPop();
               //showBottomDialog();
               // SetText(tv_start);
                break;
            case R.id.tv_submit://发布
                // postActivity();
                break;
        }
    }
    //时间选择器
    private void showPop() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupWindowView = inflater.inflate(R.layout.pop_item, null);

        tv_qx = (TextView) popupWindowView.findViewById(R.id.tv_qx);
        tv_cc = (TextView) popupWindowView.findViewById(R.id.tv_cc);

        popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //从底部显示
        popupWindow.showAtLocation(activityPosted, Gravity.BOTTOM, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        popupWindow.setOutsideTouchable(true);
        initWheelView(popupWindowView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                makeWindowLight();
            }
        });
        popupWindow.showAtLocation(activityPosted, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        //取消按钮
        tv_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.dismiss();
            }
        });

    }
    //截取时间
    private String Time_phase(String s) {
        //08月21日周几00:19
//        821nul:
        if (s.substring(0, 1).equals("0") && !s.substring(5, 6).equals("0")) {//首位为0，小时第一位不为零
            //881815null
            ss_star1 = s.substring(1, 2) + s.substring(3, 5) + s.substring(8, 10) + s.substring(11, 13);
        } else if (s.substring(0, 1).equals("0") && s.substring(5, 6).equals("0")) {//首位为0，小时第一位为零
            ss_star1 = s.substring(1, 2) + s.substring(3, 5) + s.substring(9, 10) + s.substring(11, 13);

        } else if (!s.substring(0, 1).equals("0") && !s.substring(5, 6).equals("0")) {//首位不为零，小时第一位不为零
            ss_star1 = s.substring(0, 2) + s.substring(3, 5) + s.substring(8, 10) + s.substring(11, 13);
        } else if (!s.substring(0, 1).equals("0") && s.substring(5, 6).equals("0")) {//首位不为零，小时第一位为零
            ss_star1 = s.substring(0, 2) + s.substring(3, 5) + s.substring(9, 10) + s.substring(11, 13);
        }
        return ss_star1;
    }
    public void SetText(final TextView tv_set) {


        tv_cc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //判断时间
//                if (valueold == 0 && valueold1 == 0 && valueold2 == 0) {
                if (valueold == 0) {
                    value_h = xiaoshi_start[hours];
                    value_m = fenzhong_start[minll];
                    s_star = Time_phase(mdData[taday] + value_w + value_h + ":" + value_m);

                    Log.e("s_star===", s_star + "");

                    Log.e("展示的什么", mdData[taday]);
                    Log.e("s_star_0的时候截取之后的格式", s_star + "");

                } else {
                    s_star = Time_phase(value + value_w + value_h + ":" + value_m);
                    Log.e("s_star截取之后的格式", s_star + "");
                }


                //当前时间
                now_taday = Time_phase(mdData[taday] + value_w + xiaoshi_start[hours] + ":" + fenzhong_start[minll]);
                Log.e("now_taday===", now_taday + "");

                int star_s = Integer.parseInt(s_star);
                int now_day = Integer.parseInt(now_taday);

                Log.e("str_s  now_day ====", star_s + "  " + now_day);
                //820113：
                if (Integer.parseInt(s_star) < Integer.parseInt(now_taday)) {
                    ToastUtil.showToast(ActivityPostedActivity.this, "活动开始时间不能在当前时间之前");
                } else if (valueold == 0) {
                    tv_set.setText(mdData[taday] + value_w + value_h + ":" + value_m);
                } else {
                    tv_set.setText(value + value_w + value_h + ":" + value_m);
                }
                popupWindow.dismiss();

            }
        });
    }
    /**
     * 让屏幕变亮
     */
    private void makeWindowLight() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
    }

    private void initWheelView(View view) {
        Calendar c = Calendar.getInstance();
        //年份
        int curYear = c.get(Calendar.YEAR);
        //月份
        int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
        //日期
        int curDate = c.get(Calendar.DATE);

        //年月日
        wl_ymd = (WheelView) view.findViewById(R.id.wl_ymd);
        wl_week = (WheelView) view.findViewById(R.id.wl_week);
        wl_sx = (WheelView) view.findViewById(R.id.wl_sx);
        wl_hour = (WheelView) view.findViewById(R.id.wl_hour);
        wl_min = (WheelView) view.findViewById(R.id.wl_min);
        wl_ymd.setVisibleItems(2);
        wl_week.setVisibleItems(2);
        wl_sx.setVisibleItems(2);
        wl_hour.setVisibleItems(2);
        wl_min.setVisibleItems(2);
        //年
        ArrayWheelAdapter<String> weekAdapter = new ArrayWheelAdapter<>(ActivityPostedActivity.this, mdData);
        List<String> ymdList = Arrays.asList(mdData);


        //年
        wl_ymd.setViewAdapter(weekAdapter);
        weekAdapter.setTextSize(18);
        wl_ymd.setCyclic(true);
        //当前时间在集合中的位置
        taday = ymdList.indexOf(GetTimeUtil.getYMDTimeR(System.currentTimeMillis()));
        wl_ymd.setCurrentItem(ymdList.indexOf(GetTimeUtil.getYMDTimeR(System.currentTimeMillis())));
        Log.e("taday是什么", taday + "");
        //改变监听
        wl_ymd.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {

                valueold = oldValue;
                valuenew = newValue;
                Log.e("newValue的位置--------", newValue + "");
                Log.e("oldValue的位置--------", oldValue + "");
                //不带有年的数据
                value = mdData[newValue];
                //判断星期  原本数据
                value1 = ymdAdata[newValue];
                int year = Integer.parseInt(value1.substring(0, value1.indexOf("年")));
                int month = Integer.parseInt(value1.substring(value1.indexOf("年") + 1, value1.lastIndexOf("月")));
                int day = Integer.parseInt(value1.substring(value1.lastIndexOf("月") + 1, value1.length()));
                //设置周与月日联动
                changeWheelWeek(year, month, day);
            }
        });

        //周
        ArrayWheelAdapter<String> weekAdapter2 = new ArrayWheelAdapter<String>(this, week_str);
        wl_week.setViewAdapter(weekAdapter2);
        weekAdapter2.setTextSize(18);
        wl_week.setEnabled(false);
        wl_week.setCyclic(true);
        wl_week.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                value_w = week_str[newValue];
            }
        });
        //带有年份
        changeWheelWeek(curYear, curMonth, curDate);

        // 上下午
        ArrayWheelAdapter<String> weekAdapter3 = new ArrayWheelAdapter<String>(this, sx_str);
        wl_sx.setViewAdapter(weekAdapter3);
        weekAdapter3.setTextSize(18);
        wl_sx.setEnabled(false);
        wl_sx.setCyclic(false);
        //小时
        NumericWheelAdapter numericAdapter1 = new NumericWheelAdapter(this, 0, 23);
        //此处可以出掉
        numericAdapter1.setLabel("");
        numericAdapter1.setTextSize(18);
        wl_hour.setViewAdapter(numericAdapter1);
        wl_hour.setCyclic(true);//可循环
        wl_hour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//                value_h = xiaoshi_start[newValue];
//                Log.e("滑动到", value_h + "小时");
                //改变时获得时间 传入方法 设置改变上下午

                if (oldValue == 0) {
                    value_h = xiaoshi_start[hours];
                    Log.e("value_m-----", value_m + "");
                    Log.e("minll-----", minll + "");
                } else {
                    value_h = xiaoshi_start[newValue];
                    Log.e("value_m2", value_m + "");
                }

                change_Sx(value_h);

                //点击之前就要获得s_star数据
            }


        });


        //分钟
        NumericWheelAdapter numericAdapter2 = new NumericWheelAdapter(this, 0, 59);
        numericAdapter2.setLabel("");
        numericAdapter2.setTextSize(18);
        wl_min.setViewAdapter(numericAdapter2);
        wl_min.setCyclic(true);
        wl_min.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                //未选择状态保存   （保存当前时间）
                if (oldValue == 0) {
                    value_m = fenzhong_start[minll];
                    Log.e("value_m-----", value_m + "");
                    Log.e("minll-----", minll + "");
                } else {
                    value_m = fenzhong_start[newValue];
                    Log.e("value_m2", value_m + "");
                }
                Log.e("分钟的oldValue和newValue的位置", oldValue + "" + newValue + "");
                //截取时间字符串
            }
        });

        //设置小时格式
        String currenthh = new SimpleDateFormat("HH").format(c.getTime());
        List<String> asList = Arrays.asList(xiaoshi_start);
        int hour_index = asList.indexOf(currenthh);
        hours = hour_index;
        wl_hour.setCurrentItem(hour_index);

        //设置分钟格式
        String currentmm = new SimpleDateFormat("mm").format(c.getTime());
        List<String> asList2 = Arrays.asList(fenzhong_start);
        int min_index = asList2.indexOf(currentmm);
        minll = min_index;
        Log.e("min_index是==", min_index + "");
        wl_min.setCurrentItem(min_index);
        //获取当前时间分钟的位置

    }

    private void change_Sx(String value_h) {
            int time = Integer.parseInt(value_h);
            Log.e("time=", time + "");
            if (time < 12) {
                wl_sx.setCurrentItem(1);
            } else {
                wl_sx.setCurrentItem(0);
            }
    }

    private void changeWheelWeek(int year, int month, int day) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);

            int i = calendar.get(Calendar.DAY_OF_WEEK);
            wl_week.setCurrentItem(i - 1);
            lastweek = week_str[i - 1];

    }

   /* private void showBottomDialog() {
        dialog = new BottomSheetDialog(this);
        View view = View.inflate(this, R.layout.post_time, null);
        tvCancle = (TextView) view.findViewById(R.id.tv_cancle);
        tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);

        dateWeek = (WheelView) view.findViewById(R.id.date_week);
        after = (WheelView) view.findViewById(R.id.after);
        h = (WheelView) view.findViewById(R.id.h);
        ss = (WheelView) view.findViewById(R.id.ss);

        dateWeek.setVisibleItems(7);
        after.setVisibleItems(7);
        h.setVisibleItems(7);
        ss.setVisibleItems(7);

        tvCancle.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();
    }*/

    private void postActivity(String aContactMobile, String aContent, String aPlace
            , String aSponsor, String aTime, String aTitle, String aTotal, File photos, File imgs, String aRealname,
                              String aNaion) {
        Observable observable = RetrofitUtils.getInstance().postActivity(aContactMobile, aContent, aPlace, aSponsor, aTime, aTitle,
                aTotal, photos, imgs, aRealname, aNaion);
        observable.subscribe(new Subscriber<ActivityPostBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ActivityPostBean o) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                dialog.dismiss();
                break;
            case R.id.tv_confirm:
                dialog.dismiss();
                break;
        }
    }
}
