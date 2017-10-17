package com.moe.wl.ui.home.view.saving;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.moe.wl.R;
import com.moe.wl.ui.home.adapter.saving.MenuAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者 Wang
 * 日期 2017/6/22.
 * 描述
 */
public class MenuPopwindow extends PopupWindow {

    private View conentView;
    private ListView lv_content;

    private MyOnClick click;
    private Activity context;
    private String[] strings;


    public MenuPopwindow(Activity context,String[] strings, MyOnClick click) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.menu_popup_window, null);
        this.click=click;
        this.context=context;
        this.strings=strings;
        init();
        setting();
    }

    public void init(){
        lv_content = (ListView) conentView.findViewById(R.id.lv_content);
        MenuAdapter adapter=new MenuAdapter(context);
        final List<String> list=new ArrayList<String>();
        for(int i=0;i<strings.length;i++){
            list.add(strings[i]);
        }
        adapter.setItemList(list);
        lv_content.setAdapter(adapter);
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                if (click!=null){
                    click.click(list.get(position));
                }
            }
        });
    }

    public void setting(){
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w/3);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
    }


    /**
     * 显示popupWindow
     */
    public void showPopupWindow(Context context,View parent) {
        if (!this.isShowing()) {
        // 以下拉方式显示popupwindow
            WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            int xpos=manager.getDefaultDisplay().getWidth()-getWidth();
            this.showAsDropDown(parent, xpos,0);
        } else {
            this.dismiss();
        }
    }

    public interface MyOnClick{
        void click(String s);
    }

}