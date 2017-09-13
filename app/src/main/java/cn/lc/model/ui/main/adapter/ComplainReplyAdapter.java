package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.ui.main.bean.ComplainReplyBean;

/**
 * 类描述：投诉反馈Adapter
 * 作者：Shixhe On 2017/9/9 0009
 */
public class ComplainReplyAdapter extends BaseAdapter {

    private Context context;
    private List<ComplainReplyBean.PageEntity.ListEntity> data;

    public ComplainReplyAdapter(Context context, List<ComplainReplyBean.PageEntity.ListEntity> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_complain_reply_left, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (data.get(position).getUtype() == 1) { // 用户
            holder.headerRight.setVisibility(View.GONE);
            holder.contentRight.setVisibility(View.GONE);
            holder.headerLeft.setVisibility(View.VISIBLE);
            holder.contentLeft.setVisibility(View.VISIBLE);
            holder.contentLeft.setText(data.get(position).getContent());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getPhoto(), holder.headerLeft);
        } else {// 客服
            holder.headerLeft.setVisibility(View.GONE);
            holder.contentLeft.setVisibility(View.GONE);
            holder.headerRight.setVisibility(View.VISIBLE);
            holder.contentRight.setVisibility(View.VISIBLE);
            holder.contentRight.setText(data.get(position).getContent());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, data.get(position).getPhoto(), holder.headerRight);
        }

        if (data.get(position).getImgs().size() > 0) {
            holder.adapter = new ComplainImageAdapter(context, data.get(position).getImgs());
            if (data.get(position).getUtype() == 1) {
                holder.gridViewLeft.setAdapter(holder.adapter);
            } else {
                holder.gridViewRight.setAdapter(holder.adapter);
            }
        }

        holder.time.setText(data.get(position).getCreatetime());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.header_left)
        ImageView headerLeft;
        @BindView(R.id.content_left)
        TextView contentLeft;
        @BindView(R.id.grid_view_left)
        NoSlidingGridView gridViewLeft;
        @BindView(R.id.content_right)
        TextView contentRight;
        @BindView(R.id.grid_view_right)
        NoSlidingGridView gridViewRight;
        @BindView(R.id.header_right)
        ImageView headerRight;
        ComplainImageAdapter adapter;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
