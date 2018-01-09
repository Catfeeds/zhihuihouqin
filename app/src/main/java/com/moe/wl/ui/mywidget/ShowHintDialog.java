package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;

import mvp.cn.util.ScreenUtils;

/**
 * 类描述：
 * 作者：Shixhe On 2017/11/8 0008
 */

public class ShowHintDialog extends Dialog {

    private Context context;
    private int serviceType;
    private String content;
    private TextView tvContent;
    private CheckBox isShowNext;
    private TextView iKnow;
    private ScrollViewBottom scroll;
    private RelativeLayout bg;
    private Display display;
    private LinearLayout ll;

    public ShowHintDialog(Context context) {
        super(context, R.style.dialog_style);
        this.context = context;
    }

    public ShowHintDialog(Context context, String content, int serviceType) {
        this(context);
        this.serviceType = serviceType;
        this.content = content;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.pop_show_hint, null);
        tvContent = (TextView) view.findViewById(R.id.content);
        isShowNext = (CheckBox) view.findViewById(R.id.next_show);
        iKnow = (TextView) view.findViewById(R.id.i_know);
        scroll = (ScrollViewBottom) view.findViewById(R.id.scroll);
        bg = (RelativeLayout) view.findViewById(R.id.bg);
//        ll = (HeightLinearLayout) view.findViewById(R.id.ll);
        if (content == null) {
            tvContent.setText("空");
        } else {
            tvContent.setText(Html.fromHtml(content));
        }

        scroll.setONScrollBottomListener(new ScrollViewBottom.OnScrollBottomListener() {
            @Override
            public void onScrollBottom(boolean isBottom) {
                if (isCanScroll){
                    if (isBottom) {
                        setCanClick();
                    } else {
                        setCancelClick();
                    }
                }
            }
        });
        int width = ScreenUtils.getScreenWidth(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, -2);
        view.setLayoutParams(params);
        setContentView(view, params);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.CENTER;
        getWindow().setAttributes(p);
        getWindow().setWindowAnimations(R.style.AnimationDialog); // 调整dialog背景大小
    }

    private void setCanClick() {
        iKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefHelper.getInstance().setServiceHint(serviceType, isShowNext.isChecked());
                dismiss();
            }
        });
        iKnow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_blue_line));
        iKnow.setTextColor(context.getResources().getColor(R.color.bt));
    }

    private void setCancelClick() {
        iKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("不可点击的点击事件");
            }
        });
        iKnow.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_gray_line));
        iKnow.setTextColor(context.getResources().getColor(R.color.gray));
    }

    @Override
    public void show() {
        super.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (iKnowState != null) {
                    iKnowState.onSetting(tvContent);
                }
            }
        }, 0);
    }

    Handler handler = new Handler();

    private OnSetIKnowState iKnowState;

    public interface OnSetIKnowState {
        void onSetting(TextView content);
    }

    public void setOnSetIKnowState(OnSetIKnowState iKnowState) {
        this.iKnowState = iKnowState;
    }

    private boolean isCanScroll;
    // 设置下方按钮是否可以点击
    public void setButtonStateNo(boolean isBottom) {
        if (isBottom) {
            setCanClick();
            isCanScroll = false;
        } else {
            setCancelClick();
            isCanScroll = true;
        }
    }

}
