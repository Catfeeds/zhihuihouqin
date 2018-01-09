package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.BuildNumList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/12/7 0007.
 */

public class BuildNumAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<BuildNumList.DataBean> dataList=new ArrayList<>();

    public BuildNumAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.build_num_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (dataList != null && dataList.size() > 0)
            ((ViewHolder) holder).setData(position, dataList.get(position));

    }

    @Override
    public int getItemCount() {
        if(dataList!=null&&dataList.size()>0){
            return dataList.size();
        }else{
            return 0;
        }

    }

    public void setData(List<BuildNumList.DataBean> data) {
        this.dataList = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_build_num_item)
        TextView tvBuildNumItem;
        private int mPosition;
        private String buildName;
        private int id;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickListener(mPosition, buildName, id);
                    }
                }
            });
        }

        public void setData(int position, BuildNumList.DataBean dataBean) {
            this.mPosition = position;
            if (dataBean != null) {
                buildName = dataBean.getBuildName();
                id = dataBean.getId();
                tvBuildNumItem.setText(buildName);
            }

        }
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClickListener(int position, String buildname, int id);
    }
}
