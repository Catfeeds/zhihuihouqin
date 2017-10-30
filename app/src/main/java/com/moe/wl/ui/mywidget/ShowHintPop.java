package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/1 0001
 */

public class ShowHintPop extends PopupWindow {

    private Context context;
    private View view;

    private TextView content;
    private CheckBox isShowNext;
    private TextView iKnow;

    public ShowHintPop(final Context context, final String cont, final int serviceType) {

        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.pop_show_hint, null);
        content = (TextView) view.findViewById(R.id.content);
        isShowNext = (CheckBox) view.findViewById(R.id.next_show);
        iKnow = (TextView) view.findViewById(R.id.i_know);

        if (cont == null) {
            content.setText("空");
        } else {
            content.setText(cont);
        }

        iKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefHelper.getInstance().setServiceHint(serviceType, isShowNext.isChecked());
                dismiss();
            }
        });

        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.popwin_anim_style);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        /*view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.ll_top).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });*/
    }
}
