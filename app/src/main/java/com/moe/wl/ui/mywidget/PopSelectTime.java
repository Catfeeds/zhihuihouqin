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
import com.moe.wl.ui.main.adapter.TimeAdapter;
import com.moe.wl.ui.main.adapter.TimeSelectAdapter;
import com.moe.wl.ui.main.bean.JieYueTimeBean;

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
  /*  private final List<JieYueTimeBean.TimelistBean> list1;
    private final List<JieYueTimeBean.TimelistBean> list2;*/

    public PopSelectTime(Context context, JieYueTimeBean bean, OnSelectClick listener) {
        this.context = context;
        this.listene = listener;
        this.data = bean;
        view = LayoutInflater.from(context).inflate(R.layout.pop_select_time, null);
        am = (GridView) view.findViewById(R.id.grid_am);
        pm = (GridView) view.findViewById(R.id.grid_pm);
        final List<JieYueTimeBean.AmListBean> amList = bean.getAmList();
        final List<JieYueTimeBean.PmListBean> pmList = bean.getPmList();
      /*  List<JieYueTimeBean.TimelistBean> timelist = bean.getTimelist();
        list1 = new ArrayList();
        list2 = new ArrayList();
        if(timelist!=null)
        for (int i = 0; i < timelist.size(); i++) {
            if (timelist.get(i).getTypeid().equals("1")) {
                list1.add(timelist.get(i));
            } else if (timelist.get(i).getTypeid().equals("2")) {
                list2.add(timelist.get(i));
            }
        }*/
        TimeSelectAdapter anAdapter = new TimeSelectAdapter(context, amList);
        TimeAdapter pnAdapter = new TimeAdapter(context, pmList);

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
