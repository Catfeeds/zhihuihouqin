package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */
public class GridViewImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> paths;
    private OnAddPhotoClickListener listener;

    public GridViewImageAdapter(Context context, ArrayList<String> paths, OnAddPhotoClickListener listener) {
        this.context = context;
        this.paths = paths;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public Object getItem(int position) {
        return paths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_view_image, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (paths.get(position).equals("addPhoto")) {
            Glide.with(context).load(R.drawable.add_dotted_line).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick();
                }
            });
        } else if (paths.get(position).equals("addComment")) {
            Glide.with(context).load(R.drawable.add_dotted_line).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick();
                }
            });
        } else {
            Glide.with(context).load( paths.get(position)).into(holder.image);
           // GlideLoading.getInstance().loadImgUrlNyImgLoader(context, paths.get(position), holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtil.showToast(context, "点击图片了！");
                }
            });
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnAddPhotoClickListener {
        void onClick();
    }
}
