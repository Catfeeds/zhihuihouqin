package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.utils.ServiceIntentUtils;
import com.moe.wl.ui.main.bean.HomeSearchBean;
import com.moe.wl.ui.main.bean.ServiceDataBean;
import com.moe.wl.ui.main.bean.ServiceIcon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 我的电脑 on 2017/12/12 0012.
 */

public class SearchAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HomeSearchBean.ServiceListBean> data = new ArrayList<>();

    public SearchAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(data.size()>0&&data!=null){
            HomeSearchBean.ServiceListBean serviceListBean = data.get(position);
            if(serviceListBean!=null){
                int id = serviceListBean.getId();//服务id
                String name = serviceListBean.getName();//服务名称
                ((ViewHolder) holder).setData(position,id,name);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (data.size() > 0 && data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(List<HomeSearchBean.ServiceListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_serviece)
        ImageView ivServiece;
        @BindView(R.id.tv_name)
        TextView tvName;
         private ServiceDataBean serviceIcon;

         ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
             view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (ServiceIntentUtils.goService(serviceIcon.getId()) == null) {
                         return;
                     }
                     UIManager.turnToAct(mContext,ServiceIntentUtils.goService(serviceIcon.getId()));
                 }
             });
        }

        public void setData(int position, int id, String name) {
            serviceIcon = ServiceIcon.getInstance().getServiceIcon(id);
            if(serviceIcon !=null){
                int sourceID = serviceIcon.getSourceID();//图片
                String serviceName = serviceIcon.getServiceName();
               ivServiece.setImageResource(sourceID);
                tvName.setText(serviceName);

            }
        }
    }
}
