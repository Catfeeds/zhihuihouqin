package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.login.bean.CarInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class CarAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CarInfo>  data=new ArrayList<>();
    private List<String> lists = Arrays.asList("京", "津", "冀", "晋", "蒙", "辽",
            "吉", "黑", "沪", "苏", "浙", "皖"
            , "闽", "赣", "鲁", "豫", "鄂", "湘"
            , "粤", "桂", "琼", "川", "贵", "云"
            , "渝", "藏", "陕", "甘", "青", "宁"
            , "新");
    private List<String> listTwo = Arrays.asList("A", "B", "C", "D", "E", "F"
            , "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "X"
            , "Y", "Z");

    public CarAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(data.get(position),position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CarInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shengfen)
        TextView tvShengfen;
        @BindView(R.id.et_chepaihao)
        EditText etChepaihao;
         @BindView(R.id.rl_car_type)
         RelativeLayout rlCarType;
         @BindView(R.id.tv_car_type)
         TextView tvCarType;
         private int mPosition;
         private String str;
         private CarInfo carInfo;
         private int MAX_NUM=5;

         ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

             tvShengfen.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     showShengFenDialog();
                 }
             });
             TextWatcher watcher = new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                 }

                 @Override
                 public void onTextChanged(CharSequence s, int start, int before, int count) {
                 }

                 @Override
                 public void afterTextChanged(Editable s) {
                     //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
                     if (s.length() > MAX_NUM) {
                         s.delete(MAX_NUM, s.length());
                     }
                 }
             };
             etChepaihao.addTextChangedListener(watcher);
        }

        public  void setData(CarInfo carInfo, int position) {
            this.mPosition = position;
            this.carInfo=carInfo;
        }
         private void showShengFenDialog() {
             show(true);
         }

         private void show(final boolean isFirst) {
             final AlertDialog dlg = new AlertDialog.Builder(mContext).create();
             dlg.show();
             Window window = dlg.getWindow();
             // *** 主要就是在这里实现这种效果的.
             // 设置窗口的内容页面,alertdialog.xml文件中定义view内容
             window.setContentView(R.layout.view_car_alertdialog);
             GridView gv = (GridView) window.findViewById(R.id.gv_car_num);
             ArrayAdapter<String> adapter = null;
             if (isFirst) {
                 str = "";
                 tvShengfen.setText(str);
                 adapter = new ArrayAdapter<String>(mContext, R.layout.simple_list_item_1, R.id.tv_item, lists);
             } else {
                 adapter = new ArrayAdapter<String>(mContext, R.layout.simple_list_item_1, R.id.tv_item, listTwo);

             }
             gv.setAdapter(adapter);
             gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     if (isFirst) {
                         str += lists.get(position);
                         show(false);
                     } else {
                         str += listTwo.get(position);
                     }
                     dlg.cancel();
                     tvShengfen.setText(str);
                 }
             });
         }
    }
}
