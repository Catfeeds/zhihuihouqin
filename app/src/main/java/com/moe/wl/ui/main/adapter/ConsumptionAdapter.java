package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/10/9 0009.
 */

public class ConsumptionAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list = Arrays.asList("消耗统计","消耗排名","消耗对比");

    public ConsumptionAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumption_item,
                    parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        String s = list.get(position);
        viewHolder.tvXiaohao.setText(s);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.civ_logo)
        CircleImageView civLogo;
        @BindView(R.id.tv_xiaohao)
        TextView tvXiaohao;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
