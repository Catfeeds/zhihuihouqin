package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
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
import cn.lc.model.ui.main.activity.HealthConsultDetailActivity;
import cn.lc.model.ui.main.bean.HealthServerceHomeBean;

/**
 * Created by 我的电脑 on 2017/8/15 0015.
 */
public class HealthServiceRvAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<HealthServerceHomeBean.InfolistBean> data=new ArrayList<>();

    public HealthServiceRvAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<HealthServerceHomeBean.InfolistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_service_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(data!=null){
            viewHolder.setData(data.get(position));

        }
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_health_service_item)
        ImageView ivHealthServiceItem;
        @BindView(R.id.tv_health_service_title)
        TextView tvHealthServiceTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_bumen)
        TextView tvBumen;
        private HealthServerceHomeBean.InfolistBean infolistBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,HealthConsultDetailActivity.class);
                    intent.putExtra("infolistBean",infolistBean);
                    context.startActivity(intent);
                }
            });
        }


        public void setData(HealthServerceHomeBean.InfolistBean infolistBean) {
            this.infolistBean=infolistBean;
            /*GlideLoading.getInstance().loadImgUrlNyImgLoader(
                    context,infolistBean.getPhoto,ivHealthServiceItem
            );*/
            tvHealthServiceTitle.setText(infolistBean.getTitle());
            tvTime.setText(infolistBean.getCreatetime());
            tvBumen.setText(infolistBean.getSource());
        }
    }
}
