package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/10/9 0009.
 */

public class MeetingRoomAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public MeetingRoomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.meeting_room_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHOlder = (ViewHolder) holder;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_room_pic)
        ImageView ivRoomPic;
        @BindView(R.id.tv_rongna)
        TextView tvRongna;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_reserve_num)
        TextView tvReserveNum;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_reserve_state)
        TextView tvReserveState;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
