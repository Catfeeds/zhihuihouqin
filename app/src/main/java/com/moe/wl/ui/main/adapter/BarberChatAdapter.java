package com.moe.wl.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ConsultBarberBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/27 0027.
 */

public class BarberChatAdapter extends RecyclerView.Adapter {
    private List<ConsultBarberBean.NoticelistBean> data = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 2) {//商户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item_send, parent, false);
        } else if (viewType ==1 ) {//用户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item, parent, false);

        }
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if (data != null && data.size() > 0) {
            ConsultBarberBean.NoticelistBean noticelistBean = data.get(position);
            String content = noticelistBean.getContent();
            String createtime = noticelistBean.getCreatetime();
//            int time = Integer.parseInt(createtime);
            viewHolder.tv_message.setText(content);
            viewHolder.tv_time.setText(createtime);

           /* //如果是第1条消息 与当前的系统时间比较 如果消息发送/收到的时间和当前时间足够近就不需要展示
            //显示消息时间的textView
            if (position == 0) {
                if (System.currentTimeMillis() - time > 60000) {
                    viewHolder.tv_time.setVisibility(View.GONE);
                } else {
                    viewHolder.tv_time.setVisibility(View.VISIBLE);
                }
            } else {//如果不是第一条 就需要跟当前消息的上一条消息比较
                ConsultBarberBean.NoticelistBean noticelistBean1 = data.get(position - 1);
                String createtime1 = noticelistBean1.getCreatetime();
                int lastTime = Integer.parseInt(createtime1);
                if (time - lastTime > 60000) {
                    viewHolder.tv_time.setVisibility(View.GONE);
                } else {
                    viewHolder.tv_time.setVisibility(View.VISIBLE);
                }
            }*/
        }else{
            LogUtils.i("data为空");
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
        if(data!=null&&data.size()>0){
            ConsultBarberBean.NoticelistBean noticelistBean = data.get(position);
            int gettype = noticelistBean.getGettype();
            return gettype;
        }else{
            return super.getItemViewType(position);
        }
    }

    public void setData(List<ConsultBarberBean.NoticelistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time;
        private TextView tv_message;
       // private ImageView iv_state;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
