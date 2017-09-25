package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.MessageListBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */
public class SystemMessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MessageListBean.PageEntity.ListEntity> data;
    private OnItemClickListener listener;

    public SystemMessageAdapter(Context context, List<MessageListBean.PageEntity.ListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_system_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        holder.title.setText(data.get(position).getMessagetitle());
        holder.content.setText(data.get(position).getContent());
        holder.time.setText(data.get(position).getCreatetime());
        LogUtils.d("时间：" + data.get(position).getCreatetime());
        holder.llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 跳转详情
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.ll_all)
        LinearLayout llAll;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
