package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.moe.wl.R;
import com.moe.wl.ui.main.adapter.TimeSelectAdapter;
import com.moe.wl.ui.main.bean.JieYueTimeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class PopSelectTime extends PopupWindow {
    private Context context;
    private View view;
    private OnSelectClick listene;
    private GridView am, pm;
    private JieYueTimeBean data;
    private List<JieYueTimeBean.AmListEntity> amList;
    private List<JieYueTimeBean.PmListEntity> pmList;

    public PopSelectTime(Context context, JieYueTimeBean bean, OnSelectClick listener) {
        this.context = context;
        this.listene = listener;
        this.data = bean;
        view = LayoutInflater.from(context).inflate(R.layout.pop_select_time, null);
        am = (GridView) view.findViewById(R.id.grid_am);
        pm = (GridView) view.findViewById(R.id.grid_pm);

        amList = new ArrayList();
        amList.addAll(bean.getAmList());
        pmList = new ArrayList();
        pmList.addAll(bean.getPmList());

        TimeSelectAdapter anAdapter = new TimeSelectAdapter(context, bean, 1);
        TimeSelectAdapter pnAdapter = new TimeSelectAdapter(context, bean, 2);

        am.setAdapter(anAdapter);
        pm.setAdapter(pnAdapter);

        am.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listene != null)
                    listene.onClick(amList.get(position).getTypeid(), amList.get(position).getTimeperiod());
                dismiss();
            }
        });

        pm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listene != null)
                    listene.onClick(pmList.get(position).getTypeid(), pmList.get(position).getTimeperiod());
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

    }

    public interface OnSelectClick {
        void onClick(String id, String time);
    }

}
