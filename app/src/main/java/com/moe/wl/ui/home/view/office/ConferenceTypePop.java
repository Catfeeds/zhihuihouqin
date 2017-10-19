package com.moe.wl.ui.home.view.office;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.MyBaseAdapter;
import com.moe.wl.ui.home.bean.office.TypeListBean;

import java.util.List;

import mvp.cn.util.DensityUtil;


/**
 * 作者 Wang
 * 日期 2017/6/22.
 * 描述
 */
public class ConferenceTypePop extends PopupWindow {

    private View conentView;
    private ListView lv_content;

    private MyOnClick click;
    private Activity context;


    public ConferenceTypePop(Activity context, MyOnClick click) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.menu_conference_type, null);
        this.click = click;
        this.context = context;
        init();
        setting();
    }

    public void init() {
        lv_content = (ListView) conentView.findViewById(R.id.lv_content);
    }

    public void setting() {
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w- DensityUtil.dip2px(context,120));
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
    public void showPopupWindow(View parent,int height) {

        if (!this.isShowing()) {
            showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    public interface MyOnClick {
        void click(TypeListBean bean);
    }

    public void setData(final List<TypeListBean> list) {
        if (list!=null && list.size()>0){
            final MenuAdapter adapter=new MenuAdapter(context);
            adapter.setItemList(list);
            lv_content.setAdapter(adapter);
            lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheck(false);
                    }
                    list.get(position).setCheck(true);
                    adapter.notifyDataSetChanged();
                    if (click!=null){
                        click.click(list.get(position));
                    }
                    dismiss();
                }
            });
        }

    }

    public class MenuAdapter extends MyBaseAdapter<TypeListBean> {

        public MenuAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            final TypeListBean bean = getItem(position);

            holder.tv_spinner_text.setText(bean.getTypename());
            if (bean.isCheck()) {
                view.setBackgroundColor(getContext().getResources().getColor(
                        R.color.font_blue));
            } else {
                view.setBackgroundColor(getContext().getResources().getColor(
                        R.color.font_gray));
            }
            return view;
        }

        public class ViewHolder {
            public View rootView;
            public TextView tv_spinner_text;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.tv_spinner_text = (TextView) rootView.findViewById(R.id.tv_spinner_text);
            }

        }
    }

}