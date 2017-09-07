package cn.lc.model.ui.mywidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import cn.lc.model.R;
import cn.lc.model.ui.main.adapter.SelectTimeAdapter;
import cn.lc.model.ui.main.bean.SelectTimeBean;
import mvp.cn.util.CommonUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/1 0001
 */

public class SelectTimePop extends PopupWindow {

    private Context context;
    private View view;
    private OnSelectClick listener;
    private GridView am, pm;
    private SelectTimeAdapter adapterAm, adapterPm;

    public SelectTimePop(final Context context, final SelectTimeBean bean, final OnSelectClick listener) {
        this.context = context;
        this.listener = listener;
        view = LayoutInflater.from(context).inflate(R.layout.pop_select_time, null);
        am = (GridView) view.findViewById(R.id.grid_am);
        pm = (GridView) view.findViewById(R.id.grid_pm);

        adapterAm = new SelectTimeAdapter(context, bean, 0);
        adapterPm = new SelectTimeAdapter(context, bean, 1);

        am.setAdapter(adapterAm);
        pm.setAdapter(adapterPm);

        am.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onClick(bean.getAmList().get(position).getId());
                dismiss();
            }
        });

        pm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onClick(bean.getPmList().get(position).getId());
                dismiss();
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

    public interface OnSelectClick {
        void onClick(int id);
    }

}
