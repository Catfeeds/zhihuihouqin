package cn.lc.model.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.adapter.ConfirmDryCleanOrderAdapter;
import cn.lc.model.ui.main.bean.JieYueBean;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;
import rx.Observable;
import rx.Subscriber;

public class ConfirmDryCleanOrderActivity extends Base2Activity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.rl_phone)
    LinearLayout rlPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.lv_order_info)
    ListView lvOrderInfo;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_confirm_dry_clean_order)
    LinearLayout activityConfirmDryCleanOrder;

    private List<OrderDryCleanBean.PageBean.ListBean> list;
    private String time;
    private String mobile;
    private ConfirmDryCleanOrderAdapter orderAdapter;
    private String json;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_confirm_dry_clean_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initListView();
        initTitle();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        time = intent.getStringExtra("time");
        json = intent.getStringExtra("json");
        String realName =  SharedPrefHelper.getInstance().getRealName();
        Gson gson = new Gson();
        list = gson.fromJson(json, new TypeToken<List<OrderDryCleanBean.PageBean.ListBean>>() {
        }.getType());
        int counts = 0;
        for (int i = 0; i < list.size(); i++) {
            counts += list.get(i).getCount();
        }
        if (counts != 0) {
            orderAdapter.setList(list);
        }
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getCount() * list.get(i).getPrice();
        }
        tvUserName.setText(realName);
        tvPhoneNum.setText(mobile);
        tvTime.setText(time);
        tvSum.setText("￥" + sum);
    }

    private void commit() {
        JSONArray json1 = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("clothestypeid", list.get(i).getId());
                object.put("count", list.get(i).getCount());
                object.put("name", list.get(i).getName());
                object.put("price", list.get(i).getPrice());
                json1.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        LogUtils.i(json1.toString());
        Observable observable = RetrofitUtils.getInstance().dryOrderCommit(mobile, time, json1.toString());
        showProgressDialog();
        observable.subscribe(new Subscriber<JieYueBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("下单出现问题");
            }

            @Override
            public void onNext(JieYueBean o) {
                if (o.getErrCode() == 0) {
                    Intent intent = new Intent(ConfirmDryCleanOrderActivity.this, SubmitResultActivity.class);
                    startActivity(intent);
                } else {

                }
            }
        });
    }

    private void initListView() {
        orderAdapter = new ConfirmDryCleanOrderAdapter(this);
        lvOrderInfo.setAdapter(orderAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确定订单");
    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        commit();

    }
}
