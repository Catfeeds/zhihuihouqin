package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.ui.mywidget.gridpasswordview.GridPasswordView;

/**
 * Created by 我的电脑 on 2017/10/23 0023.
 */

public class WalletPayDialog {
    private Context context;
    private Dialog dialog;
    private Button btn_neg;
    private Button btn_pos;
    private Display display;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private GridPasswordView passwordView;
    private String pwd;
    private LinearLayout lLayout_bg;

    public WalletPayDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public WalletPayDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_wallet_pay, null);

        // 获取自定义Dialog布局中的控件

        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg1);
        passwordView = (GridPasswordView) view.findViewById(R.id.pswView);
        passwordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if (psw.length() == 6) {
                    pwd = psw;
                }

            }

            @Override
            public void onInputFinish(String psw) {

            }
        });

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.8), LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }


    public WalletPayDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public WalletPayDialog setPositiveButton(String text,
                                             final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public WalletPayDialog setNegativeButton(String text,
                                             final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }


    public void show() {
        //setLayout();
        dialog.show();
    }

    private OnConfirmListener listen;

    public void setListen(OnConfirmListener listen) {
        this.listen = listen;
    }

    public interface OnConfirmListener {
        void onConfirmListener(String pwd);
    }
}
