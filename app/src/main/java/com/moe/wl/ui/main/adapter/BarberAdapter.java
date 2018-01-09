package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.BarberDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.ReservaBarberActivity;
import com.moe.wl.ui.main.bean.BarberListsBean;
import com.moe.wl.ui.main.bean.BarberlistBean;
import com.moe.wl.ui.mywidget.StarBar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/18 0018.
 */
public class BarberAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BarberlistBean> data = new ArrayList<>();
    private String address;
    private String shopName;

    public BarberAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.barber_item, null);
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
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<BarberlistBean> data, String address, String shopName) {
        this.data = data;
        this.address = address;
        this.shopName = shopName;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.barber_header)
        CircleImageView barberHeader;
        @BindView(R.id.tv_barber_name)
        TextView tvBarberName;
        @BindView(R.id.tv_now_reserva)
        TextView tvNowReserva;
        @BindView(R.id.starBar)
        StarBar ratingBar;
        @BindView(R.id.tv_star_num)
        TextView tvStarNum;
        @BindView(R.id.tv_barber_address)
        TextView tvBarberAddress;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        private BarberlistBean barberlistBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvNowReserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReservaBarberActivity.class);
                    intent.putExtra("barberlistBean",barberlistBean);
                    intent.putExtra("addresss",address);
                    context.startActivity(intent);
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BarberDetailActivity.class);
                    /*intent.putExtra("shopName",shopName);
                    intent.putExtra("address",address);
                    intent.putExtra("barberlistBean",barberlistBean);*/
                    Bundle bundle = new Bundle();
                    if (shopName != null) {
                        bundle.putString("shopName", shopName);
                    }
                    if(address!=null){
                        bundle.putString("address",address);
                    }
                    bundle.putSerializable("barberlistBean", barberlistBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

        public void setData(BarberlistBean barberlistBean) {
            this.barberlistBean = barberlistBean;
            if (barberlistBean != null) {
                GlideLoading.getInstance().loadImgUrlNyImgLoader(context, barberlistBean.getPhoto(), barberHeader);
                tvBarberName.setText(barberlistBean.getName());
                ratingBar.setStarMark(barberlistBean.getScore());
                ratingBar.setIntegerMark(false);
                tvStarNum.setText(barberlistBean.getScore() + "");
                int tatalcount = barberlistBean.getTatalcount();
                int remaincount = barberlistBean.getRemaincount();
                tvPercent.setText(remaincount + "/" + tatalcount);
            }
            if (address != null) {
                tvBarberAddress.setText("地址:"+address);
            }
        }
    }
}
