package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.DryCleaners.DryToCommentAct;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.activity.me.OrderDryDetailActivity;
import com.moe.wl.ui.main.bean.CheckDryOrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 干洗订单列表adapter
 * Created by 我的电脑 on 2017/9/14 0014.
 */

public class DryCleanAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CheckDryOrderBean.ListEntity> mList = new ArrayList<>();
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
            CheckDryOrderBean.ListEntity listBean = mList.get(position);
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

    public void setData(List<CheckDryOrderBean.ListEntity> list, int state) {
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
        @BindView(R.id.item)
        LinearLayout item;

        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(final CheckDryOrderBean.ListEntity listBean, final int position, int state) {
            llDryType.removeAllViews();//每次刷新时都要移除条目,重新赋值
            if (listBean != null) {
                this.mPosition = position;
                tvOrderNum.setText("订单号：" + listBean.getOrdercode());
                tvPaystate.setText("支付状态：" + (listBean.getPayStatus() == 0 ? "未支付" : "已支付"));
                tvTime.setText("下单时间：" + listBean.getCreatetime());
                List<CheckDryOrderBean.ListEntity.ClothesListEntity> detailList = listBean.getClothesList();
                if (detailList != null && detailList.size() > 0) {
                    for (int i = 0; i < detailList.size(); i++) {
                        View view = View.inflate(mContext, R.layout.dry_order_item, null);
                        TextView name = (TextView) view.findViewById(R.id.tv_yiwu_name1);
                        TextView count = (TextView) view.findViewById(R.id.tv_count);
                        int count1 = detailList.get(i).getCount();
                        String clothName = detailList.get(i).getClothesName() + "";
                        name.setText(clothName);
                        count.setText("x" + count1);
                        llDryType.addView(view);
                    }
                } else {
                    LogUtils.d("detailList为null");
                }
                switch (state) {
                    case 1:
                        if (mList.get(position).getPayStatus() == 0) {
                            tvToComment.setVisibility(View.VISIBLE);
                            if (mList.get(position).getCheckstatus() == 0) {
                                tvToComment.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_order_gray_button));
//                                tvToComment.setClickable(false);
                            } else {
                                tvToComment.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_order_button));
//                                tvToComment.setClickable(true);
                            }
                        } else {
                            tvToComment.setVisibility(View.GONE);
                        }
                        tvAll.setText("取消预订");
                        tvAll.setOnClickListener(this);
                        break;
                    case 2:
                        tvAll.setText("拨打电话");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                    case 3:
                        tvAll.setText("再次预订");
                        tvToComment.setVisibility(View.GONE);
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
                        tvAll.setText("立即评价");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                    case 5:
                        tvAll.setText("删除订单");
                        tvToComment.setVisibility(View.GONE);
                        tvAll.setOnClickListener(this);
                        break;
                }
            }

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, OrderDryDetailActivity.class);
                    intent.putExtra("OrderID", listBean.getId());
                    mContext.startActivity(intent);
                }
            });

            tvToComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mList.get(position).getCheckstatus() == 1) {
                        Intent intent = new Intent(mContext, PayFiveJiaoActivity.class);
                        intent.putExtra("from", Constants.DRYCLEANER);
                        intent.putExtra("pay", listBean.getTotalprice());
                        intent.putExtra("orderid", listBean.getId() + "");
                        intent.putExtra("ordercode", listBean.getOrdercode());
                        intent.putExtra("ordertype", Constants.DRYCLEANER + "");
                        intent.putExtra("time", listBean.getCreatetime());
                        mContext.startActivity(intent);
                    }
                }
            });

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
