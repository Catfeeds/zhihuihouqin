package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.activity.OfficeSupplies.CheckHistoryPostItemAct;
import cn.lc.model.ui.main.activity.OfficeSupplies.PostNeedActivity;
import cn.lc.model.ui.main.bean.HistoryPostBean;

/**
 * Created by 我的电脑 on 2017/9/22 0022.
 */

public class HistoryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HistoryPostBean.PageBean.ListBean> data=new ArrayList<>();

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(mContext);
        View view = from.inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(data!=null&&data.size()>0){
            viewHolder.setData(data.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }
        return 0;
    }

    public void setData(List<HistoryPostBean.PageBean.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_sp_name)
        TextView tvSpName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_sp_count)
        TextView tvSpCount;
        @BindView(R.id.tv_commit_time)
        TextView tvCommitTime;
        @BindView(R.id.tv_state)
        TextView tvState;
        private HistoryPostBean.PageBean.ListBean listBean;
         private int status;

         ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CheckHistoryPostItemAct.class);
                    intent.putExtra("ListBean",listBean);
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(HistoryPostBean.PageBean.ListBean listBean, int position) {
            this.listBean =listBean;
            if(listBean!=null){
                tvSpName.setText(listBean.getProductname());
                tvSpCount.setText(listBean.getProductcount()+"个");
                tvCommitTime.setText(listBean.getCreatetime());
                status = listBean.getStatus();
                switch (status){
                    case 1:
                        tvState.setText("未审核");
                        tvState.setTextColor(Color.parseColor("#03A7DA"));
                        break;
                    case 2:
                        tvState.setText("审核中");
                        tvState.setTextColor(Color.parseColor("#333333"));
                        break;
                    case 3:
                        tvState.setText("审核成功");
                        tvState.setTextColor(Color.parseColor("#00CC33"));
                        break;
                    case 4:
                        tvState.setText("审核失败");
                        tvState.setTextColor(Color.parseColor("#FF2F2F"));
                        break;
                    default:
                        break;
                }
            }else{
                LogUtils.i("listBean 为null");
            }
        }
    }
}
