package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.MessageBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */
public class MessageListAdapter extends BaseAdapter {

    private Context context;
    private List<MessageBean.TalkListEntity> data;

    public MessageListAdapter(Context context, List<MessageBean.TalkListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getServicePhoto(), holder.header);
        holder.userName.setText(data.get(position).getServiceUsername());
        holder.content.setText(data.get(position).getNewContent());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (data.get(position).getTargetType()) {
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

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.header)
        ImageView header;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.item)
        RelativeLayout item;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
