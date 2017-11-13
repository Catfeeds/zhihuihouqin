package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moe.wl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/1 0001
 */

public class OrderPop extends PopupWindow {

    @BindView(R.id.tv_public_maintain)
    TextView tvPublicMaintain;
    @BindView(R.id.tv_office_supplies)
    TextView tvOfficeSupplies;
    @BindView(R.id.tv_clear_vegetables)
    TextView tvClearVegetables;
    @BindView(R.id.tv_order_water)
    TextView tvOrderWater;
    @BindView(R.id.tv_work_meal)
    TextView tvWorkMeal;
    @BindView(R.id.tv_health_service)
    TextView tvHealthService;
    @BindView(R.id.tv_cut_hair)
    TextView tvCutHair;
    @BindView(R.id.tv_dry_cleaner)
    TextView tvDryCleaner;
    @BindView(R.id.tv_experts_visit)
    TextView tvExpertsVisit;
    @BindView(R.id.tv_book_order)
    TextView tvBookOrder;
    @BindView(R.id.tv_office)
    TextView tvOffice;
    @BindView(R.id.tv_visitors)
    TextView tvVisitors;

    private Context context;
    private View view;

    public OrderPop(Context context, View.OnClickListener click) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.layout_popup_window, null);
        ButterKnife.bind(this, view);

        tvPublicMaintain.setOnClickListener(click);
        tvOfficeSupplies.setOnClickListener(click);
        tvClearVegetables.setOnClickListener(click);
        tvOrderWater.setOnClickListener(click);
        tvWorkMeal.setOnClickListener(click);
        tvHealthService.setOnClickListener(click);
        tvCutHair.setOnClickListener(click);
        tvDryCleaner.setOnClickListener(click);
        tvExpertsVisit.setOnClickListener(click);
        tvBookOrder.setOnClickListener(click);
        tvOffice.setOnClickListener(click);
        tvVisitors.setOnClickListener(click);

        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);

        //实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Popupwindow_animation);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.main).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }
}
