package com.moe.wl.ui.home.adapter.saving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.CircleImageView;
import com.moe.wl.ui.home.adapter.MyBaseAdapter;

/**
 * 评论列表
 */
public class CommentAdapter extends MyBaseAdapter<String> {

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
    class ViewHolder {
        public View rootView;
        public CircleImageView iv_avatar;
        public TextView tv_user;
        public TextView tv_time;
        public TextView tv_content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_avatar = (CircleImageView) rootView.findViewById(R.id.iv_avatar);
            this.tv_user = (TextView) rootView.findViewById(R.id.tv_user);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        }

    }
}
