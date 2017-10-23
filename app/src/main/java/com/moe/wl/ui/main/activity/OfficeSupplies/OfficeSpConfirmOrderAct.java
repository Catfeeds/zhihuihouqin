package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.NumberUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.AddressManagerActivity;
import com.moe.wl.ui.main.adapter.SpOrderAdapter;
import com.moe.wl.ui.main.bean.ShopCarInfoBean;
import com.moe.wl.ui.main.bean.SpCheckShopCarBean;
import com.moe.wl.ui.main.bean.SpOrderBean;
import com.moe.wl.ui.main.model.SpOrderModel;
import com.moe.wl.ui.main.modelimpl.SpOrderModelImpl;
import com.moe.wl.ui.main.presenter.SpOrderPresenter;
import com.moe.wl.ui.main.view.SpOrderView;
import com.moe.wl.ui.mywidget.BottomTimeDialog;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 办公用品确认订单
 */
public class OfficeSpConfirmOrderAct extends BaseActivity<SpOrderModel, SpOrderView, SpOrderPresenter> implements SpOrderView {

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
    @BindView(R.id.rv_order_item)
    NoSlideRecyclerView rvOrderItem;
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
    @BindView(R.id.tv_other_need)
     TextView tvOtherNeed;
    @BindView(R.id.activity_office_sp_confirm_order)
    LinearLayout activityOfficeSpConfirmOrder;
    private String remark;
    private String address;
    private List<SpCheckShopCarBean.CartItemsBean> cartItemLists = new ArrayList<>();

    private int count;
    private int price;
    private String from;
    private SpOrderAdapter spOrderAdapter;



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
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        if (from.equals("nowpay")) {
            price = intent.getIntExtra("price", -1);
            count = intent.getIntExtra("count", -1);
            ShopCarInfoBean.SkuListBean skuListBean = (ShopCarInfoBean.SkuListBean) intent.getSerializableExtra("skuListBean");
            String mainimg = skuListBean.getMainimg();
            String cataName = skuListBean.getSkuname();
            SpCheckShopCarBean.CartItemsBean bean = new SpCheckShopCarBean.CartItemsBean();
            SpCheckShopCarBean.CartItemsBean.SkuBean skuBean = new SpCheckShopCarBean.CartItemsBean.SkuBean();
            bean.setSku(skuBean);
            skuBean.setMainimg(mainimg);
            skuBean.setCataName(cataName);
            bean.setCount(count);
            bean.getSku().setPrice(price);
            cartItemLists.add(bean);
        } else {
            String json = intent.getStringExtra("json");
            Gson gson = new Gson();
            List<SpCheckShopCarBean.CartItemsBean> list = gson.fromJson(json, new TypeToken<List<SpCheckShopCarBean.CartItemsBean>>() {
            }.getType());
            cartItemLists.addAll(list);
        }
        initTitle();
        initData();
        initRecycler();
    }

    private void initRecycler() {
        rvOrderItem.setLayoutManager(new NoScrollLinearLayoutManager(this));
        spOrderAdapter = new SpOrderAdapter(this, cartItemLists);
        rvOrderItem.setAdapter(spOrderAdapter);
    }

    private void initData() {
        tvUsername.setText(SharedPrefHelper.getInstance().getRealName());
        tvPhone.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        String sex = SharedPrefHelper.getInstance().getSex();
        if ("男".equals(sex)) {
            tvSex.setText("先生");
        } else if ("女".equals(sex)) {
            tvSex.setText("女士");
        }
        int sum = 0;
        for (int i = 0; i < cartItemLists.size(); i++) {
            int count = cartItemLists.get(i).getCount();
            int price = cartItemLists.get(i).getSku().getPrice();
            sum += count * price;
        }
        tvShopAmout.setText("￥" + NumberUtils.keepPrecision(sum+"",2));
        tvHowMuch.setText("实际付款￥" + NumberUtils.keepPrecision(sum+"",2));

    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("确认订单");
    }

    @OnClick({R.id.ll_address, R.id.rl_expect_time, R.id.ll_write_other, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                startActivityForResult(intent1, ADDRESSREQUESTCODE);
                break;
            case R.id.rl_expect_time:
                showPop();
                break;
            case R.id.ll_write_other:
                Intent intent2 = new Intent(this, RemarkActivity.class);
                startActivityForResult(intent2, REMARKREUQESTCODE);
                break;
            case R.id.tv_now_pay:
                if(TextUtils.isEmpty(address)){
                    showToast("请选择送货地址");
                    return;
                }
                String time = tvTime.getText().toString().trim();
                if(TextUtils.isEmpty(time)){
                    showToast("请选择期望送货时间");
                    return;
                }
                Intent intent = new Intent(this, SpPayActivity.class);
                String money = tvHowMuch.getText().toString().trim();
                String[] s=money.split("￥");
                intent.putExtra("money", s[1]);
                startActivity(intent);
                break;
        }
    }

    private void showPop() {
        BottomTimeDialog dialog = new BottomTimeDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int n_year, int n_month, int n_day, String n_min, String n_sec) {
                LogUtils.i(n_year + " " + n_month + " " + n_day + " " + n_min + " " + n_sec);
                tvTime.setText(n_year + "-" + n_month + "-" + n_day + " " + n_min + ":" + n_sec);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADDRESSREQUESTCODE) {
                if (data != null) {
                    address = data.getStringExtra("Address");
                    tvAddress.setText("送至：" + address);
                }
            }
        } else if (resultCode == Constants.REMARK) {
            if (requestCode == REMARKREUQESTCODE) {
                if (data != null) {
                    remark = data.getStringExtra("remark");
                    tvOtherNeed.setText(remark);
                }
            }
        }
    }

    @Override
    public void getOrderInfoSucc(SpOrderBean bean) {

    }

}
