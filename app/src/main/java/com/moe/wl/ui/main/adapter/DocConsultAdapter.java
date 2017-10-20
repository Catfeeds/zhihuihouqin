package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.ConsultBarberBean;
import com.moe.wl.ui.main.bean.ExpertnoticelistBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/20 0020.
 */

public class DocConsultAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ExpertnoticelistBean.NoticelistBean> data=new ArrayList<>();

    public DocConsultAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 1) {//用户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item_send, parent, false);
        } else if (viewType == 2) {//商户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item, parent, false);

        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if (data != null && data.size() > 0) {
            ExpertnoticelistBean.NoticelistBean noticelistBean = data.get(position);
            String content = noticelistBean.getContent();
            String createtime = noticelistBean.getCreatetime();
            viewHolder.tv_message.setText(content);
            viewHolder.tv_time.setText(createtime);
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    public void setData(List<ExpertnoticelistBean.NoticelistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time;
        private TextView tv_message;
        // private ImageView iv_state;

        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
    /* tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);*/
}
