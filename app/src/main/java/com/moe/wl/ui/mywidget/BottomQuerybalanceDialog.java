package com.moe.wl.ui.mywidget;

import android.app.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ScreenUtils;

public class BottomQuerybalanceDialog extends Dialog {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_butie_amount)
    TextView tvButieAmount;
    @BindView(R.id.tv_cash_withdrawal)
    TextView tvCashWithdrawal;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    /*ImageView ivClose;
        ImageView ivWen;
        TextView tvRecharrgeAmount;
       *//* TextView tvOrderInfo;*//*
    TextView tvPayWay;
    LinearLayout llPayWay;
    TextView tvNowPay;*/
    private Context ct;
    private int paytype;

    public BottomQuerybalanceDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(ct, R.layout.query_balance_dialog, null);
        ButterKnife.bind(this, view);

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

    @OnClick({R.id.tv_cash_withdrawal, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cash_withdrawal:
                if(listener!=null){
                    listener.onWithdrawalClickListener();
                }
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public void setData(double remainSum, double subsidySum) {
        tvMoney.setText(remainSum+"");
        tvButieAmount.setText(subsidySum+"元");
    }
private OnWithdrawalClickListener listener;

    public void setListener(OnWithdrawalClickListener listener) {
        this.listener = listener;
    }

    public interface OnWithdrawalClickListener {
        void onWithdrawalClickListener();
    }
}
