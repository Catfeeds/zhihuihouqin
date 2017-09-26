package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.DryCleaners.DryToCommentAct;
import com.moe.wl.ui.main.bean.CheckDryOrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * Created by 我的电脑 on 2017/9/14 0014.
 */

public class DryCleanAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CheckDryOrderBean.PageBean.ListBean> mList = new ArrayList<>();
    private int mState = 1;


    public DryCleanAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.dry_xiadan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mList != null && mList.size() > 0) {
            CheckDryOrderBean.PageBean.ListBean listBean = mList.get(position);
            viewHolder.setData(listBean, position, mState);
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size();
        }
        return 0;
    }

    public void setData(List<CheckDryOrderBean.PageBean.ListBean> list, int state) {
        this.mList = list;
        this.mState = state;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.ll_Dry_type)
        LinearLayout llDryType;
        @BindView(R.id.tv_paystate)
        TextView tvPaystate;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_to_comment)
        TextView tvToComment;
        @BindView(R.id.tv_all)
        TextView tvAll;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(CheckDryOrderBean.PageBean.ListBean listBean, int position, int state) {
            llDryType.removeAllViews();//每次刷新时都要移除条目,重新赋值
            if (listBean != null) {
                this.mPosition = position;
                tvOrderNum.setText("订单号: " + listBean.getCode());
                tvPaystate.setText("支付状态: " + listBean.getPayStatus());
                tvTime.setText("下单时间: " + listBean.getExpectarrivaltime());
                List<CheckDryOrderBean.PageBean.ListBean.DetailListBean> detailList = listBean.getDetailList();
                if (detailList != null && detailList.size() > 0) {
                    for (int i = 0; i < detailList.size(); i++) {
                        View view = View.inflate(mContext, R.layout.dry_order_item, null);
                        TextView name = (TextView) view.findViewById(R.id.tv_yiwu_name1);
                        TextView count = (TextView) view.findViewById(R.id.tv_count);
                        int count1 = detailList.get(i).getCount();
                        String clothName = detailList.get(i).getClothName() + "";
                        name.setText(clothName);
                        count.setText("x" + count1);
                        llDryType.addView(view);
                    }
                } else {
                    LogUtils.d("detailList为null");
                }
                switch (state) {
                    case 1:
                        tvAll.setText("取消预约");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                    case 2:
                        tvAll.setText("拨打电话");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                    case 3:
                        tvAll.setText("再次预定");
                        tvToComment.setVisibility(View.VISIBLE);
                        tvAll.setOnClickListener(this);
                        tvToComment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, DryToCommentAct.class);
                                mContext.startActivity(intent);
                            }
                        });
                        break;
                    case 4:
                        tvAll.setText("评价");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                    case 5:
                        tvAll.setText("删除");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                }
            }
        }

        @Override
        public void onClick(View v) {
            switch (mState) {
                case 1:
                    if (listener != null) {
                        listener.onClickListener(1, mPosition);
                    }
                    break;
                case 2:
                    if (listener != null) {
                        listener.onClickListener(2, mPosition);
                    }
                    break;
                case 3:
                    if (listener != null) {
                        listener.onClickListener(3, mPosition);
                    }
                    break;
                case 4:
                    if (listener != null) {
                        listener.onClickListener(4, mPosition);
                    }
                    break;
                case 5:
                    if (listener != null) {
                        listener.onClickListener(5, mPosition);
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
