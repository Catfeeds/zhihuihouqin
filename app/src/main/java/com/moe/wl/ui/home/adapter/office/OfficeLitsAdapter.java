package com.moe.wl.ui.home.adapter.office;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.home.activity.office.OfficeDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 办公室列表
 */
public class OfficeLitsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> data = new ArrayList<>();

    public OfficeLitsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_office_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OfficeDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_saturation;
        public TextView tv_time;
        public TextView tv_number;
        public TextView tv_location;
        public TextView tv_subscribe;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_icon = (ImageView) rootView.findViewById(R.id.iv_icon);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_saturation = (TextView) rootView.findViewById(R.id.tv_saturation);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_number = (TextView) rootView.findViewById(R.id.tv_number);
            this.tv_location = (TextView) rootView.findViewById(R.id.tv_location);
            this.tv_subscribe = (TextView) rootView.findViewById(R.id.tv_subscribe);
        }

    }
}
