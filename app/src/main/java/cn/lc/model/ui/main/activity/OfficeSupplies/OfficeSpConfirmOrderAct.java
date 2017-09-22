package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.ordering.AddressManagerActivity;
import cn.lc.model.ui.main.bean.SpOrderBean;
import cn.lc.model.ui.main.model.SpOrderModel;
import cn.lc.model.ui.main.modelimpl.SpOrderModelImpl;
import cn.lc.model.ui.main.presenter.SpOrderPresenter;
import cn.lc.model.ui.main.view.SpOrderView;
import cn.lc.model.ui.main.wheelView.OnWheelScrollListener;
import cn.lc.model.ui.main.wheelView.WheelView;
import cn.lc.model.ui.main.wheelView.adapter.NumericWheelAdapter;
import cn.lc.model.ui.mywidget.BottomTimeDialog;
import mvp.cn.util.ScreenUtils;

public class OfficeSpConfirmOrderAct extends BaseActivity<SpOrderModel,SpOrderView,SpOrderPresenter> implements SpOrderView {

    private static final int ADDRESSREQUESTCODE = 10;
    private static final int REMARKREUQESTCODE = 20;
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.iv_address_logo)
    ImageView ivAddressLogo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.iv_clock)
    ImageView ivClock;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_expect_time)
    RelativeLayout rlExpectTime;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.ll_write_other)
    LinearLayout llWriteOther;
    @BindView(R.id.tv_shop_amout)
    TextView tvShopAmout;
    @BindView(R.id.tv_how_much)
    TextView tvHowMuch;
    @BindView(R.id.tv_now_pay)
    TextView tvNowPay;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.activity_office_sp_confirm_order)
    LinearLayout activityOfficeSpConfirmOrder;
    private String remark;
    private String address;


    @Override
    public SpOrderPresenter createPresenter() {
        return new SpOrderPresenter();
    }

    @Override
    public SpOrderModel createModel() {
        return new SpOrderModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_office_sp_confirm_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initData();

    }

    private void initData() {
        tvUsername.setText(SharedPrefHelper.getInstance().getRealName());
        tvPhone.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        String sex = SharedPrefHelper.getInstance().getSex();
        if("男".equals(sex)){
            tvSex.setText("先生");
        }else if("女".equals(sex)){
            tvSex.setText("女士");
        }
        //        getPresenter().getOrder();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("确认订单");
    }

    @OnClick({R.id.ll_address, R.id.rl_expect_time, R.id.ll_write_other, R.id.tv_how_much, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                startActivityForResult(intent1,ADDRESSREQUESTCODE);
                break;
            case R.id.rl_expect_time:
                showPop();
                break;
            case R.id.ll_write_other:
                Intent intent2 = new Intent(this,RemarkActivity.class );
                startActivityForResult(intent2,REMARKREUQESTCODE);
                break;
            case R.id.tv_how_much:
                break;
            case R.id.tv_now_pay:
                Intent intent = new Intent(this,OfficeSpPayAct.class);
                startActivity(intent);
                break;
        }
    }

    private void showPop() {
        BottomTimeDialog dialog = new BottomTimeDialog(this,R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int n_year, int n_month, int n_day, int n_min, int n_sec) {
                LogUtils.i(n_year+" "+n_month+" "+n_day+" "+n_min+" "+n_sec);
                tvTime.setText(n_year+"-"+n_month+"-"+n_day+" "+n_min+":"+n_sec);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==ADDRESSREQUESTCODE){
                if(data!=null){
                    address = data.getStringExtra("Address");
                    tvAddress.setText("送至："+ address);
                }
            }
        }else if( resultCode== Constants.REMARK){
            if(requestCode==REMARKREUQESTCODE){
                if(data!=null){
                    remark = data.getStringExtra("remark");
                }
            }
        }
    }

    @Override
    public void getOrderInfoSucc(SpOrderBean bean) {

    }
}
