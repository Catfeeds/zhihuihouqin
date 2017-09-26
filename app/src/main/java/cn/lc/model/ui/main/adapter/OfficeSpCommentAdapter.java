package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.bean.SpAllCommentBean;
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
        RatingBar userRatingBar;
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
             userRatingBar.setRating((float) commentlistBean.getScore());
             tvTime.setText(commentlistBean.getCreatetime());
             tvEvaluate.setText(commentlistBean.getContent());
         }
     }
}
