package com.moe.wl.ui.mywidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ScreenUtils;

public class BottomRechargeDialog extends Dialog implements View.OnClickListener {

    ImageView ivClose;
    ImageView ivWen;
    TextView tvRecharrgeAmount;
   /* TextView tvOrderInfo;*/
    TextView tvPayWay;
    LinearLayout llPayWay;
    TextView tvNowPay;
    private Context ct;
    private int paytype;
    private String mAmount;

    public BottomRechargeDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(ct, R.layout.recharge_dialog, null);
        tvRecharrgeAmount= (TextView) view.findViewById(R.id.tv_recharrge_amount);
        /*tvOrderInfo= (TextView) view.findViewById(R.id.tv_order_info);*/
        tvPayWay= (TextView) view.findViewById(R.id.tv_pay_way);
        llPayWay= (LinearLayout) view.findViewById(R.id.ll_pay_way);
        tvNowPay= (TextView) view.findViewById(R.id.tv_now_pay);
        ivClose= (ImageView) view.findViewById(R.id.iv_close);
        ivWen= (ImageView) view.findViewById(R.id.iv_wen);
        llPayWay= (LinearLayout) view.findViewById(R.id.ll_pay_way);
        tvNowPay= (TextView) view.findViewById(R.id.tv_now_pay);
        ivClose.setOnClickListener(this);
        ivWen.setOnClickListener(this);
        llPayWay.setOnClickListener(this);
        tvNowPay.setOnClickListener(this);

        int width = ScreenUtils.getScreenWidth(ct);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, -2);
        view.setLayoutParams(params);
        setContentView(view, params);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(p);
        getWindow().setWindowAnimations(R.style.AnimationDialog);

    }

    public void setAmount(String amount) {
        this.mAmount=amount;
        tvRecharrgeAmount.setText("￥"+amount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_wen:
                break;
            case R.id.ll_pay_way:
                selectPayWay();
                break;
            case R.id.tv_now_pay:
                if(listener!=null){
                    listener.onConfirmClickListener(mAmount,paytype);
                }
                break;
        }
    }

    private void selectPayWay() {
        final String items[] = {"支付宝", "微信"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ct);

        builder.setTitle("请选择支付的方式");
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = items[which];
                        paytype = which+1;
                        LogUtils.i("which====="+which+"======="+paytype);
                        tvPayWay.setText(item);

                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

    private OnConfirmClickListener listener;

    public void setListener(OnConfirmClickListener listener) {
        this.listener = listener;
    }

    public interface OnConfirmClickListener {
        void onConfirmClickListener(String money,int paytype);
    }
}
