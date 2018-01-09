package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.SpAllCommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.ui.mywidget.StarBar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/16 0016.
 */
public class OfficeSpCommentAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<SpAllCommentBean.PageBean.ListBean> data=new ArrayList<>();

    public OfficeSpCommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_detail_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(data!=null){
            SpAllCommentBean.PageBean.ListBean listBean = data.get(position);
            viewHolder.setData(listBean);
        }

    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    public void setDate(List<SpAllCommentBean.PageBean.ListBean> date) {
        this.data = date;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
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

         public void setData(SpAllCommentBean.PageBean.ListBean commentlistBean) {
             GlideLoading.getInstance().loadImgUrlNyImgLoader(context,
                     commentlistBean.getPhoto(),civUserPhoto);
             tvUserName.setText(commentlistBean.getRealname());
             userRatingBar.setStarMark((float) commentlistBean.getScore());
             userRatingBar.setIntegerMark(false);
             userRatingBar.ismove(false);
             tvTime.setText(commentlistBean.getCreatetime());
             tvEvaluate.setText(commentlistBean.getContent());
         }
     }
}
