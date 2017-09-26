package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.ui.main.bean.ComplainHistoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * 类描述：反馈历史页面
 * 作者：Shixhe On 2017/9/6 0006
 */
public class ComplainHistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ComplainHistoryBean.PageEntity.ListEntity> info;
    private OnItemClickListener listener;

    public ComplainHistoryAdapter(Context context, List<ComplainHistoryBean.PageEntity.ListEntity> info) {
        this.context = context;
        this.info = info;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complain_history, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = ((ViewHolder) holders);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        holder.setData(info.get(position));
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.rl_item)
        RelativeLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void setData(ComplainHistoryBean.PageEntity.ListEntity data) {
            title.setText(data.getComplaintContent());
            content.setText(data.getSuggestContent());
            time.setText(data.getCreatetime());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
