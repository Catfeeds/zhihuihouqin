package com.moe.wl.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.ByuserlistEntity;
import com.moe.wl.ui.main.bean.LaifangPersonInfo;
import com.moe.wl.ui.main.bean.OrderVisitorsListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/12/5 0005.
 */

public class LfPersonAdapter extends RecyclerView.Adapter {
    private  List<LaifangPersonInfo> lfPersonInfoList;
    private List<ByuserlistEntity> data;
    private int from=0;

    public LfPersonAdapter(List<LaifangPersonInfo> lfPersonInfoList) {
        this.lfPersonInfoList = lfPersonInfoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lfperson_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(from==1){
            ((ViewHolder) holder).setData1(position,data.get(position));
        }else{
            ((ViewHolder) holder).setData(position,lfPersonInfoList.get(position));
        }


    }
    public List<LaifangPersonInfo> getPersonInfo(){

        return lfPersonInfoList;
    }

    @Override
    public int getItemCount() {
        return lfPersonInfoList.size();
    }

    public void setData(List<ByuserlistEntity> data, int i) {
        this.data = data;
        this.from=i;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.et_lname)
        EditText etLname;
        @BindView(R.id.et_identity_num)
        EditText etIdentityNum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(int position, final LaifangPersonInfo laifangPersonInfo) {
            etLname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    laifangPersonInfo.setName(s.toString());

                }
            });
            etIdentityNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    laifangPersonInfo.setIdcard(s.toString());
                }
            });
        }

        public void setData1(int position, ByuserlistEntity byuserlistEntity) {
            etLname.setText(byuserlistEntity.getName());
            etIdentityNum.setText(byuserlistEntity.getIdcard());
        }
    }
}
