package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ExpertnoticelistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/20 0020.
 */

public class DocConsultAdapter extends RecyclerView.Adapter {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    private Context mContext;
    private List<ExpertnoticelistBean.NoticelistBean> data = new ArrayList<>();

    public DocConsultAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        LogUtils.d("viewType : " + viewType);
        if (viewType == 1) { //商户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item, parent, false);
        } else if (viewType == 2) { //用户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item_send, parent, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data.size() > 0) {
            ExpertnoticelistBean.NoticelistBean noticelistBean = data.get(position);
            String content = noticelistBean.getContent();
            String createtime = noticelistBean.getCreatetime();
            viewHolder.tvMessage.setText(content);
            viewHolder.tvTime.setText(createtime);
            viewHolder.tvTime.getBackground().setAlpha(100);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (data != null && data.size() > 0) {
            ExpertnoticelistBean.NoticelistBean noticelistBean = data.get(position);
            int gettype = noticelistBean.getGettype();
            return gettype;
        } else {
            return super.getItemViewType(position);
        }
    }

    public void setData(List<ExpertnoticelistBean.NoticelistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_message)
        TextView tvMessage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
