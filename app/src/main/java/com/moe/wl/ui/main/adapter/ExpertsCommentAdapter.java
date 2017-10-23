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
import com.moe.wl.ui.main.activity.MoreUSerCommentActivity;
import com.moe.wl.ui.main.bean.ExpertsCommentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/9/28 0028.
 */

public class ExpertsCommentAdapter extends RecyclerView.Adapter {

    private List<ExpertsCommentBean.CommentlistEntity> list;
    private Context mContext;

    public ExpertsCommentAdapter(MoreUSerCommentActivity context, List<ExpertsCommentBean.CommentlistEntity> data) {
        this.mContext = context;
        this.list = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_detail_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ExpertsCommentBean.CommentlistEntity commentlistBean = list.get(position);
//        GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, commentlistBean.getPhoto(), viewHolder.civUserPhoto);
        GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, commentlistBean.getPhoto(), viewHolder.civUserPhoto, R.mipmap.ic_default_square);
        viewHolder.tvUserName.setText(commentlistBean.getRealname());
        viewHolder.userRatingBar.setRating(commentlistBean.getScore());
        OtherUtils.ratingBarColor(viewHolder.userRatingBar, mContext);
        viewHolder.tvTime.setText(commentlistBean.getCreatetime());
        viewHolder.tvEvaluate.setText(commentlistBean.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_user_photo)
        CircleImageView civUserPhoto;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.user_ratingBar)
        RatingBar userRatingBar;
        @BindView(R.id.tv_evaluate)
        TextView tvEvaluate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
