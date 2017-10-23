package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.ActivityRegistration.SignUpPersonActivity;
import com.moe.wl.ui.main.bean.ActivitySignListBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/24 0024.
 */

public class SignUpPersonAdapter extends MyBaseAdapter<ActivitySignListBean.MemberlistBean> {

    public SignUpPersonAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.person_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ActivitySignListBean.MemberlistBean bean=getItem(position);
        GlideLoading.getInstance().loadImgUrlNyImgLoader(getContext(), bean.getAsAvatar(),viewHolder.iv_person_header,R.mipmap.ic_default_rectangle);
        if (TextUtils.isEmpty(bean.getAsUsername())){
            viewHolder.person_name.setText("暂无名字");
        }else{
            viewHolder.person_name.setText(bean.getAsUsername());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SignUpPersonActivity.class);
                intent.putExtra("id", bean.getAsId());
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }


    public static class ViewHolder {
        public View rootView;
        public CircleImageView iv_person_header;
        public TextView person_name;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_person_header = (CircleImageView) rootView.findViewById(R.id.iv_person_header);
            this.person_name = (TextView) rootView.findViewById(R.id.person_name);
        }

    }
}
