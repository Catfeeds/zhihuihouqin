package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.wheelView.OnWheelScrollListener;
import com.moe.wl.ui.main.wheelView.WheelView;
import com.moe.wl.ui.main.wheelView.adapter.NumericWheelAdapter;

import java.util.Calendar;

import mvp.cn.util.ScreenUtils;

public class BottomPayWayDialog extends Dialog implements View.OnClickListener {


    private Context ct;

    public BottomPayWayDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }


    private void initView() {
        View view = View.inflate(ct, R.layout.bottom_pay_dialog, null);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvPublic = (TextView) view.findViewById(R.id.tv_public);
        TextView tvPrivate = (TextView) view.findViewById(R.id.tv_private);

        tvCancel.setOnClickListener(this);
        tvPublic.setOnClickListener(this);
        tvPrivate.setOnClickListener(this);

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


    private ClickListener listener2;

    public void setListener2(ClickListener listener2) {
        this.listener2 = listener2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_public:
                if(listener2!=null){
                    listener2.onClickListener(false);
                }
                dismiss();
                break;
            case R.id.tv_private :
                if(listener2!=null){
                    listener2.onClickListener(true);
                }
                dismiss();
                break;
        }
    }

    public interface ClickListener {
        void onClickListener(boolean isPersonal);
    }
}
