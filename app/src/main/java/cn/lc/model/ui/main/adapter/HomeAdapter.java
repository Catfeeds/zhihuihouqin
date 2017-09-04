package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.fragment.Tab1Fragment;


/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeAdapter extends BaseAdapter {


    private final String[] mDes;
    private final int[] mPhoto;
    private  Context mContext;


    public HomeAdapter(Context context,String[] des,int[] photos) {
        this.mContext=context;
        this.mDes=des;
        this.mPhoto=photos;

    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(mDes, position);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.iv_iv_grid_item)
        ImageView ivIvGridItem;
        @BindView(R.id.tv_grid_item)
        TextView tvGridItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(String[] des, int position) {
            tvGridItem.setText(des[position]);
            ivIvGridItem.setImageResource(mPhoto[position]);
        }
    }
}
