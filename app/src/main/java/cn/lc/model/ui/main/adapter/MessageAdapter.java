package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.bean.MessageBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */
public class MessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MessageBean.TalkListEntity> data;

    public MessageAdapter(Context context, List<MessageBean.TalkListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, final int position) {
        ViewHolder holder = (ViewHolder) holders;
        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getServicePhoto(), holder.header);
        holder.userName.setText(data.get(position).getServiceUsername());
        holder.content.setText(data.get(position).getNewContent());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (data.get(position).getTargetType()){
                    case 1: // 1: 报修聊天

                        break;

                    case 6: // 6：理发师

                        break;

                    case 12: // 12：反馈聊天

                        break;

                    case 14: // 14：专家坐诊

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.header)
        ImageView header;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.item)
        RelativeLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
