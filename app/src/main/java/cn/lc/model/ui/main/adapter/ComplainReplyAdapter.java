package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.ui.main.bean.ComplainReplyBean;

/**
 * 类描述：投诉反馈Adapter
 * 作者：Shixhe On 2017/9/9 0009
 */
public class ComplainReplyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ComplainReplyBean.PageEntity.ListEntity> data;
    private int width, height;

    public ComplainReplyAdapter(Context context, List<ComplainReplyBean.PageEntity.ListEntity> data) {
        this.context = context;
        this.data = data;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        LogUtils.d("width:" + width + "  height:" + height);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complain_reply_left, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        ViewHolder holder = (ViewHolder) holder1;
        if (data.get(position).getUtype() == 1) { // 用户
            holder.right.setVisibility(View.GONE);
            holder.left.setVisibility(View.VISIBLE);
            holder.contentLeft.setText(data.get(position).getContent());
            GlideLoading.getInstance().loadImgUrlHeader(context, data.get(position).getPhoto(), holder.headerLeft);
            if (data.get(position).getImgs() != null && data.get(position).getImgs().size() > 0) {
                switch (data.size()) {
                    case 1:
                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams((width - 200) / 4, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewLeft.setLayoutParams(params1);
                        holder.gridViewLeft.setNumColumns(1);
                        break;
                    case 2:
                        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(((width - 200) / 4) * 2, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewLeft.setLayoutParams(params2);
                        holder.gridViewLeft.setNumColumns(2);
                        break;
                    case 3:
                        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(((width - 200) / 4) * 3, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewLeft.setLayoutParams(params3);
                        holder.gridViewLeft.setNumColumns(3);
                        break;
                    default:
                        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(((width - 200) / 4) * 4, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewLeft.setLayoutParams(params4);
                        holder.gridViewLeft.setNumColumns(4);
                        break;
                }
                holder.gridViewLeft.setVisibility(View.VISIBLE);
                holder.adapter = new ComplainImageAdapter(context, data.get(position).getImgs());
                holder.gridViewLeft.setAdapter(holder.adapter);
            } else {
                holder.gridViewLeft.setVisibility(View.GONE);
            }
        } else {// 客服
            holder.right.setVisibility(View.VISIBLE);
            holder.left.setVisibility(View.GONE);
            holder.contentRight.setText(data.get(position).getContent());
            GlideLoading.getInstance().loadImgUrlHeader(context, data.get(position).getPhoto(), holder.headerRight);
            if (data.get(position).getImgs() != null && data.get(position).getImgs().size() > 0) {
                switch (data.size()) {
                    case 1:
                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams((width - 200) / 4, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewRight.setLayoutParams(params1);
                        holder.gridViewRight.setNumColumns(1);
                        break;
                    case 2:
                        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(((width - 200) / 4) * 2, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewRight.setLayoutParams(params2);
                        holder.gridViewRight.setNumColumns(2);
                        break;
                    case 3:
                        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(((width - 200) / 4) * 3, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewRight.setLayoutParams(params3);
                        holder.gridViewRight.setNumColumns(3);
                        break;
                    default:
                        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(((width - 200) / 4) * 4, LinearLayout.LayoutParams.WRAP_CONTENT);
                        holder.gridViewRight.setLayoutParams(params4);
                        holder.gridViewRight.setNumColumns(4);
                        break;
                }
                holder.gridViewRight.setVisibility(View.VISIBLE);
                holder.adapter = new ComplainImageAdapter(context, data.get(position).getImgs());
                holder.gridViewRight.setAdapter(holder.adapter);
            } else {
                holder.gridViewRight.setVisibility(View.GONE);
            }
        }

        if (data.get(position).getCreatetime() == null) {
            holder.time.setVisibility(View.GONE);
        } else {
            holder.time.setVisibility(View.VISIBLE);
            holder.time.setText(data.get(position).getCreatetime());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.left)
        LinearLayout left;
        @BindView(R.id.right)
        LinearLayout right;
        ComplainImageAdapter adapter;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
