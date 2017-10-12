package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ScreenUtils;

public class BottomRechargeDialog extends Dialog implements View.OnClickListener {

    ImageView ivClose;
    ImageView ivWen;
    TextView tvRecharrgeAmount;
    TextView tvOrderInfo;
    TextView tvPayWay;
    LinearLayout llPayWay;
    TextView tvNowPay;
    private Context ct;

    public BottomRechargeDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(ct, R.layout.recharge_dialog, null);
        tvRecharrgeAmount= (TextView) view.findViewById(R.id.tv_recharrge_amount);
        tvOrderInfo= (TextView) view.findViewById(R.id.tv_order_info);
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
                break;
            case R.id.tv_now_pay:
                break;
        }
    }


    public interface OnConfirmClickListener {
        void onConfirmClickListener(int i1, int i2, int i3, int i4, int i5);
    }
}
