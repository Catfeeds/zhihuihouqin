package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.bean.InformationBean;

/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeNsrlv1Adapter extends RecyclerView.Adapter {

    private Context context;
    private List<InformationBean.PageEntity.ListEntity> data;
    private int i = 0;

    public HomeNsrlv1Adapter() {
        data = new ArrayList<>();
        i = 1;
    }

    public HomeNsrlv1Adapter(Context context, List<InformationBean.PageEntity.ListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_nsrlv1_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        if (i == 1)
            return;
        ViewHolder holder = (ViewHolder) holder1;
        holder.tvFirstrvTitle.setText(data.get(position).getTitle());
        holder.tvFirstrvTime.setText(data.get(position).getCreatetime());
        holder.tvFirstrvDes.setText(data.get(position).getSource());
        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getImg(), holder.ivFirstrvLogo);
    }

    @Override
    public int getItemCount() {
        if (i == 1)
            return 3;
        return data.size();
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
