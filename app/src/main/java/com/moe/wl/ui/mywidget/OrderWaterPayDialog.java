package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.wheelView.OnWheelScrollListener;
import com.moe.wl.ui.main.wheelView.WheelView;
import com.moe.wl.ui.main.wheelView.adapter.NumericWheelAdapter;

import java.util.Calendar;

import mvp.cn.util.ScreenUtils;

public class OrderWaterPayDialog extends Dialog implements View.OnClickListener {
    private Context ct;

    public OrderWaterPayDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(ct, R.layout.order_water_pay_dialog, null);
        TextView personal = (TextView) view.findViewById(R.id.tv_personal_pay);
        TextView publicPay = (TextView) view.findViewById(R.id.tv_public_pay);
        personal.setOnClickListener(this);
        publicPay.setOnClickListener(this);

        int width = (int) (ScreenUtils.getScreenWidth(ct)*0.8);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, -2);
        setContentView(view,params);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.CENTER;
        getWindow().setAttributes(p);
    }
    private OnConfirmClickListener listener;

    public void setListener(OnConfirmClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.tv_personal_pay:
               if(listener!=null){
                   listener.onConfirmClickListener(true);
               }
               dismiss();
            break;
            case R.id.tv_public_pay:
                if(listener!=null){
                    listener.onConfirmClickListener(false);
                }
                dismiss();
            break;
        }
    }

    public interface OnConfirmClickListener{
        void onConfirmClickListener(boolean isPersonal);
    }
}
