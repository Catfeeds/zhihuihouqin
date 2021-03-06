package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;

import org.xml.sax.XMLReader;

/**
 * 类描述：每个子项目的弹出窗
 * 作者：Shixhe On 2017/9/1 0001
 */

public class ShowHintPop extends PopupWindow {

    private Context context;
    private View view;

    private TextView content;
    private CheckBox isShowNext;
    private TextView iKnow;
    private ScrollViewBottom scroll;

    public ShowHintPop(final Context context, final String cont, final int serviceType) {

        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.pop_show_hint, null);
//        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        content = (TextView) view.findViewById(R.id.content);
        isShowNext = (CheckBox) view.findViewById(R.id.next_show);
        iKnow = (TextView) view.findViewById(R.id.i_know);
        scroll = (ScrollViewBottom) view.findViewById(R.id.scroll);

        if (cont == null) {
            content.setText("空");
        } else {
            content.setText(Html.fromHtml(cont, null, new Html.TagHandler() {
                @Override
                public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
//                    LogUtils.d("opening : " + opening + " tag : " + tag + " output : " + output.toString() + " xmlReader : " + xmlReader.toString());
                }
            }));
        }

        iKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefHelper.getInstance().setServiceHint(serviceType, isShowNext.isChecked());
                dismiss();
            }
        });
//        LogUtils.d("是否可依向下滚动？" + scroll.canScrollVertically(-1));
//        if (scroll.canScrollVertically(0)) {
//            iKnow.setVisibility(View.INVISIBLE);
//        } else {
//            iKnow.setVisibility(View.VISIBLE);
//        }

        scroll.setONScrollBottomListener(new ScrollViewBottom.OnScrollBottomListener() {
            @Override
            public void onScrollBottom(boolean isBottom) {
                if (isBottom) {
                    iKnow.setVisibility(View.VISIBLE);
                } else {
                    iKnow.setVisibility(View.INVISIBLE);
                }
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

    @Override
    public int getHeight() {
        LogUtils.d("高度：" + super.getHeight());
        return super.getHeight();
    }

    @Override
    public int getWidth() {
        LogUtils.d("宽度：" + super.getWidth());
        return super.getWidth();
    }
}
