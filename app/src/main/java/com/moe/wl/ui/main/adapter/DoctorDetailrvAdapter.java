package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.bean.CommentlistBean;
import com.moe.wl.ui.mywidget.StarBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/16 0016.
 */
public class DoctorDetailrvAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CommentlistBean> data;
    private int mType;

    public DoctorDetailrvAdapter(Context context, List<CommentlistBean> data) {
        this.context = context;
        this.data = data;
    }

    public DoctorDetailrvAdapter(Context context, List<CommentlistBean> data, int type) {
        this.context = context;
        this.data = data;
        mType = type;
    }

    public DoctorDetailrvAdapter(Context context) {
        data = new ArrayList<>();
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_detail_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int position) {
        ViewHolder holder = (ViewHolder) holder1;
        CommentlistBean commentlistBean = data.get(position);
        GlideLoading.getInstance().loadImgUrlNyImgLoader(context, commentlistBean.getPhoto(), holder.civUserPhoto/*, R.mipmap.ic_default_square*/);
        holder.tvUserName.setText(commentlistBean.getRealname());
        holder.userRatingBar.setStarMark((float) commentlistBean.getScore());
        holder.userRatingBar.setIntegerMark(false);
        holder.userRatingBar.ismove(false);
        holder.tvTime.setText(commentlistBean.getCreatetime());
        holder.tvEvaluate.setText(commentlistBean.getContent());
    }

    @Override
    public int getItemCount() {
        if (mType == 1)
            return data.size() > 4 ? 4 : data.size();
        return data.size();
    }

    public void setData(List<CommentlistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_user_photo)
        CircleImageView civUserPhoto;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.user_ratingBar)
        StarBar userRatingBar;
        @BindView(R.id.tv_evaluate)
        TextView tvEvaluate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
