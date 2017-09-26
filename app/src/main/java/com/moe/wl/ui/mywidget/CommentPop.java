package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.moe.wl.R;
import mvp.cn.util.CommonUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/1 0001
 */

public class CommentPop extends PopupWindow {

    private Context context;
    private View view;
    private TextView cancel, sure;
    private EditText etContent;
    private OnCommentListener listener;

    public CommentPop(final Context context, OnCommentListener listen) {
        this.context = context;
        this.listener = listen;
        view = LayoutInflater.from(context).inflate(R.layout.pop_comment, null);
        cancel = (TextView) view.findViewById(R.id.cancel);
        sure = (TextView) view.findViewById(R.id.sure);
        etContent = (EditText) view.findViewById(R.id.et_content);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etContent.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "内容不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    listener.onListener(etContent.getText().toString());
                }
            }
        });

        CommonUtil.openSoftKeyboard(context);

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
        view.setOnTouchListener(new View.OnTouchListener() {

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
        });
    }

    public interface OnCommentListener {
        void onListener(String content);
    }

}
