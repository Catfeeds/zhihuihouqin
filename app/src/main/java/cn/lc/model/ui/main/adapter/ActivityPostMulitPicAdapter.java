package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;

/**
 * Created by 我的电脑 on 2017/9/25 0025.
 */

public class ActivityPostMulitPicAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> data=new ArrayList<>();

    public ActivityPostMulitPicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }
        return 0;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mulitpic_item,
                    parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data != null && data.size() > 0) {
            viewHolder.setData(data.get(position),position);
        }
        return convertView;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.iv_item)
        ImageView ivItem;
        private String data;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(String data, final int position) {
            this.data = data;
            if (data.equals("pic")) {
                Glide.with(mContext).load(R.drawable.add_photo).into(ivItem);
                ivItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(position);
                    }
                });
            } else {
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,data,ivItem);

            }
        }
    }
    private OnAddPhotoClickListener listener;

    public void setListener(OnAddPhotoClickListener listener) {
        this.listener = listener;
    }

    public interface OnAddPhotoClickListener {
        void onClick(int position);
    }
}
