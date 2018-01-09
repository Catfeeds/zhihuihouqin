package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ConsultBarberBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/9/27 0027.
 */

public class BarberChatAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ConsultBarberBean.NoticelistBean> data = new ArrayList<>();
    private String photo;
    private final String userPhoto;

    public BarberChatAdapter(Context context, String photo) {
        this.mContext = context;
        this.photo = photo;
        userPhoto = SharedPrefHelper.getInstance().getUserPhoto();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == 2) {//商户
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_item_send, parent, false);
        } else if (viewType == 1) {//用户
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
            int type = getItemViewType(position);
            if (type == 1) {//用户
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, photo, viewHolder.header);
            } else if (type == 2) {//商户
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, userPhoto, viewHolder.header);
            }
        } else {
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
        if (data != null && data.size() > 0) {
            ConsultBarberBean.NoticelistBean noticelistBean = data.get(position);
            int gettype = noticelistBean.getGettype();
            return gettype;
        } else {
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
        private final CircleImageView header;
        // private ImageView iv_state;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            header = (CircleImageView) itemView.findViewById(R.id.iv_header);
        }
    }
}
