package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.bean.BookOrderListBean;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class BookOrderAdapter extends RecyclerView.Adapter {
    private Context mContext;
    List<BookOrderListBean.OrderlistBean> mList = new ArrayList<>();
    int mState = 0;

    public BookOrderAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.library_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mList != null && mList.size() > 0) {
            viewHolder.setData(mList.get(position), position, mState);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size();
        }
        return 0;
    }

    public void setData(List<BookOrderListBean.OrderlistBean> data, int state) {
        this.mList = data;
        this.mState = state;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.iv_book)
        ImageView ivBook;
        @BindView(R.id.tv_book_name)
        TextView tvBookName;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_chubanshe)
        TextView tvChubanshe;
        @BindView(R.id.tv_to_comment)
        TextView tvToComment;
        @BindView(R.id.tv_all)
        TextView tvAll;
        private int mPosition;
        private int state;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(BookOrderListBean.OrderlistBean orderlistBean, int position, int state) {
            this.mPosition = position;
            this.state = state;
            if (orderlistBean != null) {
                tvOrderNum.setText("订单号: " + orderlistBean.getOrdercode());
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                        orderlistBean.getImg(), ivBook);
                tvBookName.setText(orderlistBean.getTitle());
                tvAuthor.setText("作者:" + orderlistBean.getAuthor());
                tvChubanshe.setText(orderlistBean.getPublisher());
            }
            switch (state) {
                case 0:
                    tvAll.setText("已预定");
                    tvToComment.setVisibility(View.GONE);
                    tvAll.setOnClickListener(this);
                    break;

                case 1:
                    tvAll.setText("已借阅");
                    tvToComment.setVisibility(View.GONE);
                    tvAll.setOnClickListener(this);
                    break;
                case 2:
                    tvAll.setText("已归还");
                    tvToComment.setVisibility(View.VISIBLE);
                    tvToComment.setOnClickListener(this);
                    tvAll.setOnClickListener(this);
                    break;
                case 3:
                    tvAll.setText("待评价");
                    tvToComment.setVisibility(View.GONE);
                    tvAll.setOnClickListener(this);

                    break;
                case 4:
                    tvAll.setText("已取消");
                    tvToComment.setVisibility(View.VISIBLE);
                    tvAll.setOnClickListener(this);
                    tvToComment.setOnClickListener(this);
                    break;

            }
        }

        @Override
        public void onClick(View v) {
            switch (mState){
                case 0:
                    if(listener!=null){
                        listener.onClickListener(0,mPosition);
                    }
                    break;

                case 1:
                    if(listener!=null){
                        listener.onClickListener(1,mPosition);
                    }
                    break;
                case 2:
                    if(listener!=null){
                        listener.onClickListener(2,mPosition);
                    }
                    break;
                case 3:
                    if(listener!=null){
                        listener.onClickListener(3,mPosition);
                    }
                    break;
                case 4:
                    if(listener!=null){
                        listener.onClickListener(4,mPosition);
                    }
                    break;
            }
        }
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onClickListener(int state, int position);
    }
}
