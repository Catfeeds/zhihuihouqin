package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.CircleImageView;
import com.moe.wl.ui.main.bean.InformationDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/18 0018
 */

public class InformationCommentAdapter extends BaseAdapter {

    private Context context;
    private List<InformationDetailBean.CommentListEntity> data;

    public InformationCommentAdapter(Context context, List<InformationDetailBean.CommentListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int positon) {
        return data.get(positon);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getPhoto(), holder.ivAvatar);
        holder.tvUser.setText(data.get(position).getUsername());
        holder.tvTime.setText(data.get(position).getCreatetime());
        holder.tvContent.setText(data.get(position).getContent());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_avatar)
        CircleImageView ivAvatar;
        @BindView(R.id.tv_user)
        TextView tvUser;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
