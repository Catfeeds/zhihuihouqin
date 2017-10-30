package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    private List<CarInfo> data = new ArrayList<>();
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
    private String typeName;
    private int index;

    public CarAdapter(Context mContext, List<CarInfo> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.rlCarType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carListener != null) {
                    carListener.onChooseCarClick(position);
                }
            }
        });

        viewHolder.tvShengfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberListener != null) {
                    numberListener.onChooseNumberClick(position);
                }
            }
        });

        viewHolder.etChepaihao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (textListener != null){
                    textListener.onTextChange(editable.toString(), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private int selectPosition = -1;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shengfen)
        TextView tvShengfen;
        @BindView(R.id.et_chepaihao)
        EditText etChepaihao;
        @BindView(R.id.rl_car_type)
        RelativeLayout rlCarType;
        @BindView(R.id.tv_car_type)
        TextView tvCarType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    private OnChooseCarListener carListener;
    private OnChooseNumberListener numberListener;
    private OnTextListener textListener;

    // 选择车辆条目监听
    public void setChooseCarListener(OnChooseCarListener listener) {
        this.carListener = listener;
    }

    public interface OnChooseCarListener {
        void onChooseCarClick(int position);
    }

    // 车牌号前缀监听
    public void setOnChooseNumberListener(OnChooseNumberListener listener) {
        this.numberListener = listener;
    }

    public interface OnChooseNumberListener {
        void onChooseNumberClick(int position);
    }

    // 车牌号变化监听
    public void setTextListener(OnTextListener textListener) {
        this.textListener = textListener;
    }

    public interface OnTextListener {
        void onTextChange(String content, int position);
    }

}
