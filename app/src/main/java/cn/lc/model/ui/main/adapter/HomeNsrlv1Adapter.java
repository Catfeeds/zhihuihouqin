package cn.lc.model.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeNsrlv1Adapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, parent, false);
        return new ViewHolder(view){
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_firstrv_logo)
        ImageView ivFirstrvLogo;
        @BindView(R.id.tv_firstrv_title)
        TextView tvFirstrvTitle;
        @BindView(R.id.tv_firstrv_time)
        TextView tvFirstrvTime;
        @BindView(R.id.tv_firstrv_des)
        TextView tvFirstrvDes;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
