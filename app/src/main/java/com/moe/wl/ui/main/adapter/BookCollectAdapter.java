package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.BookCollect;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.mywidget.StarBar;

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
    private List<BooklistBean> data = new ArrayList<>();
    private MyCallBack callBack;
    private boolean mIsEdit=false;

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
            viewHolder.setData(data.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(List<BooklistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void setIsEdit(boolean isEdit) {
        this.mIsEdit = isEdit;
        LogUtils.i("adapter里的isEdit==" + isEdit);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_book_pic)
        ImageView ivBookPic;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_book_name)
        TextView tvBookName;
        @BindView(R.id.ratingBar)
        StarBar ratingBar;
        @BindView(R.id.tv_star_num)
        TextView tvStarNum;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_chubanshe)
        TextView tvChubanshe;
        @BindView(R.id.tv_book_des)
        TextView tvBookDes;
        @BindView(R.id.iv_cancel)
        ImageView cancel;
        private BooklistBean data;
        private BooklistBean bookListvBean;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.cb(bookListvBean, bookListvBean.getId() + "");
                    }
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean select = bookListvBean.isSelect();
                    select = !select;
                    bookListvBean.setSelect(select);
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.updataListListener(select, mPosition);
                    }
                }
            });
        }

        public void setData(BooklistBean booklistBean,int position) {
            this.bookListvBean = booklistBean;
            this.mPosition=position;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, booklistBean.getImg(), ivBookPic, R.mipmap.ic_default_square);
            tvBookName.setText(booklistBean.getTitle());
            ratingBar.setIntegerMark(false);
            ratingBar.setStarMark(((float) booklistBean.getScore()));
            ratingBar.ismove(false);
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

            if (mIsEdit) {
                cancel.setVisibility(View.VISIBLE);
            } else {
                cancel.setVisibility(View.GONE);
            }
            boolean select = bookListvBean.isSelect();
            if (select) {
                cancel.setImageResource(R.drawable.selected);
            } else {
                cancel.setImageResource(R.drawable.unselected);
            }

        }
    }

    public interface MyCallBack {
        void cb(BooklistBean bookListvBean, String BookID);
    }

    public void setMyCallBack(MyCallBack callBack) {
        this.callBack = callBack;
    }
    private UpdataListListener listener;

    public void setListener(UpdataListListener listener) {
        this.listener = listener;
    }

    public interface UpdataListListener {
        void updataListListener(boolean isSelect, int position);
    }
}
