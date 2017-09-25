package cn.lc.model.ui.main.activity.DryCleaners;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.adapter.DryCleanersLvAdapter;
import cn.lc.model.ui.main.bean.JieYueBean;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;
import cn.lc.model.ui.main.model.DryCleanReserveInfoModel;
import cn.lc.model.ui.main.modelimpl.DryCleanReserveInfoModelImpl;
import cn.lc.model.ui.main.presenter.DryCleanReserveInfoPresenter;
import cn.lc.model.ui.main.view.DryCleanReserveInfoView;
import cn.lc.model.ui.main.wheelView.OnWheelScrollListener;
import cn.lc.model.ui.main.wheelView.WheelView;
import cn.lc.model.ui.main.wheelView.adapter.ArrayWheelAdapter;
import cn.lc.model.ui.main.wheelView.adapter.NumericWheelAdapter;
import cn.lc.model.ui.mywidget.CenterTimeDialog;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

public class DryCleanReserveInfoActivity extends BaseActivity<DryCleanReserveInfoModel,
        DryCleanReserveInfoView,DryCleanReserveInfoPresenter> implements DryCleanReserveInfoView {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_send_time)
    ImageView ivSendTime;
    @BindView(R.id.rl_set_time)
    LinearLayout rlTime;
    @BindView(R.id.rv_dry_clean)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.activity_dry_clean_reserve_info)
    RelativeLayout activityDryCleanReserveInfo;
    @BindView(R.id.tv_more)
    TextView tvMore;
    private int page = 1;
    private String limit = "10";
    private DryCleanersLvAdapter lvAdapter;
    private Observable observable;
    private List<OrderDryCleanBean.PageBean.ListBean> listAll=new ArrayList<>();
    private int sum;
    private CenterTimeDialog dialog;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_dry_clean_reserve_info);
        ButterKnife.bind(this);
    }

    @Override
    public  void initView() {
        initTitle();
        initListView();
        tvUserName.setText(SharedPrefHelper.getInstance().getRealName());
        etPhoneNum.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        getData(1+"",limit,false);
    }
    private void getListSucc(List<OrderDryCleanBean.PageBean.ListBean> list,boolean getMore) {
        if(list!=null){
            if(!getMore){
                listAll.clear();
            }else{
                LogUtils.i("获取更多数据成功");
            }
            listAll.addAll(list);
            lvAdapter.setData(listAll);
            lvAdapter.setAddListener(new DryCleanersLvAdapter.OnAddClickListener() {
                @Override
                public void addClick(int count, int position) {
                    listAll.get(position).setCount(count);
                    sum = 0;
                    for (int i = 0; i < listAll.size(); i++) {
                        sum +=listAll.get(i).getCount();
                    }
                    tvSum.setText("共"+ sum +"件");
                }
            });
            lvAdapter.setMinusListener(new DryCleanersLvAdapter.OnMinusClickListener() {
                @Override
                public void minusClick(int count, int position) {
                    listAll.get(position).setCount(count);
                    int sum=0;
                    for (int i = 0; i < listAll.size(); i++) {
                        sum+=listAll.get(i).getCount();
                    }
                    tvSum.setText("共"+sum+"件");
                }
            });
        }
    }

    private void initListView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lvAdapter = new DryCleanersLvAdapter(this);
        recyclerView.setAdapter(lvAdapter);
        recyclerView.setPullRefreshEnabled(false);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("干洗店");
    }
    @OnClick({ R.id.tv_submit,R.id.tv_more,R.id.rl_set_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                String mobile = etPhoneNum.getText().toString().trim();
                String time = tvTime.getText().toString().trim();
                Gson gson = new Gson();
                List<OrderDryCleanBean.PageBean.ListBean> mList=new ArrayList<>();
                for (int i = 0; i < listAll.size(); i++) {
                    if(listAll.get(i).getCount()!=0){
                        mList.add(listAll.get(i));
                    }
                }
                String json = gson.toJson(mList);
                if(sum<=0){
                    showToast("您没有下单,请下单后在提交");
                    return;
                }
                Intent intent = new Intent(this, ConfirmDryCleanOrderActivity.class);
                intent.putExtra("mobile",mobile);
                intent.putExtra("time",time);
                intent.putExtra("json",json);
                startActivity(intent);
                break;
            case R.id.tv_more:
                page++;
                getData(page+"",limit,true);
                break;
            case R.id.rl_set_time:
                dialog = new CenterTimeDialog(this, R.style.dialog_style);
                dialog.show();
                dialog.setListener2(new CenterTimeDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClickListener(int i1, int i2, int i3, int i4, int i5) {
                        tvTime.setText(i1+"-"+i2+"-"+i3+" "+i4+":"+i5 );
                    }
                });
                break;
        }
    }

    @Override
    public void OrderDryCleaner(OrderDryCleanBean cleanBean,boolean getMore) {
        if(cleanBean.getErrCode()==0){
            List<OrderDryCleanBean.PageBean.ListBean> list = cleanBean.getPage().getList();
            getListSucc(list,getMore);
        }else{
            showToast(cleanBean.getMsg());
        }
    }

    @Override
    public void commitSucc(JieYueBean commitBean) {

    }

    @Override
    public DryCleanReserveInfoModel createModel() {
        return new DryCleanReserveInfoModelImpl();
    }

    @Override
    public DryCleanReserveInfoPresenter createPresenter() {
        return new DryCleanReserveInfoPresenter();
    }

    public void getData(String page,String limit,boolean getMore) {
        getPresenter().getData(page,limit,getMore);
    }
}
