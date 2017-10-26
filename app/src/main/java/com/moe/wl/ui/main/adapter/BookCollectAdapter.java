package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.bean.BookCollect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class BookCollectAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<BookCollect.ListBean> data=new ArrayList<>();
    private MyCallBack callBack;

    public BookCollectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data != null) {
            viewHolder.setData(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }else{
            return 0;
        }
    }

    public void setData(List<BookCollect.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_book_pic)
        ImageView ivBookPic;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_book_name)
        TextView tvBookName;
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
        private BookCollect.ListBean data;
        private BookCollect.ListBean bookListvBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.cb(bookListvBean, bookListvBean.getId()+"");
                    }
                }
            });
        }

        public void setData(BookCollect.ListBean booklistBean) {
            this.bookListvBean = booklistBean;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, booklistBean.getImg(), ivBookPic, R.mipmap.ic_default_square);
            tvBookName.setText(booklistBean.getTitle());
            ratingBar.setRating(((float) booklistBean.getScore()));
            OtherUtils.ratingBarColor(ratingBar, mContext);
            tvStarNum.setText(booklistBean.getScore() + "分");
            tvAuthor.setText("作者:" + booklistBean.getAuthor());
            tvChubanshe.setText(booklistBean.getPublisher());
            tvBookDes.setText(booklistBean.getShortbrief());
            if (booklistBean.getBollowstatus() == 1) {
                tvState.setText("在架上");
                tvState.setTextColor(mContext.getResources().getColor(R.color.blue));
            } else {
                tvState.setText("已借出");
                tvState.setTextColor(mContext.getResources().getColor(R.color.tv_red));
            }

        }
    }
    public interface MyCallBack {
        void cb(BookCollect.ListBean bookListvBean, String BookID);
    }

    public void setMyCallBack(MyCallBack callBack) {
        this.callBack = callBack;
    }
}
