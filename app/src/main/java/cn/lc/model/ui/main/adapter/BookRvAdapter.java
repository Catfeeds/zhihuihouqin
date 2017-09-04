package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.activity.ActivityRegistration.PostedNotesActivity;
import cn.lc.model.ui.main.activity.Library.BookDescriptionActivity;
import cn.lc.model.ui.main.bean.LibraryHomeBean;

/**
 * Created by 我的电脑 on 2017/8/23 0023.
 */

public class BookRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<LibraryHomeBean.BooklistBean> data=new ArrayList<>();

    public BookRvAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(data!=null){
            viewHolder.setData(data.get(position));

        }
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    public void setData(List<LibraryHomeBean.BooklistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_book_name)
        TextView tvBookName;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.iv_book_pic)
        ImageView ivBookPic;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.tv_star_num)
        TextView tvStarNum;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_chubanshe)
        TextView tvChubanshe;
        @BindView(R.id.tv_book_des)
        TextView tvBookDes;
        private LibraryHomeBean.BooklistBean bookListvBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext,BookDescriptionActivity.class);
                    intent.putExtra("bookListvBean",bookListvBean);
                    mContext.startActivity(intent);
                }
            });
        }


        public void setData(LibraryHomeBean.BooklistBean booklistBean) {
            this.bookListvBean=booklistBean;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                    booklistBean.getImg(),ivBookPic);
            tvBookName.setText(booklistBean.getTitle());
            ratingBar.setRating(booklistBean.getScore());
            tvStarNum.setText(booklistBean.getScore()+"分");
            tvAuthor.setText("作者:"+booklistBean.getAuthor());
            tvChubanshe.setText(booklistBean.getPublisher());
            tvBookDes.setText(booklistBean.getShortbrief());
            if(booklistBean.getBollowstatus()==1){
                tvState.setText("在架上");
                tvState.setTextColor(Color.parseColor("#36CCAE"));
            }else{
                tvState.setText("已借出");
                tvState.setTextColor(Color.parseColor("#F95759"));
            }

        }
    }
}