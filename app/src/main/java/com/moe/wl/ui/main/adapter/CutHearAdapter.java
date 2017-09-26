package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

import com.moe.wl.ui.main.bean.ShopBean;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/15 0015.
 */
public class CutHearAdapter extends BaseAdapter {
private List<ShopBean.BarberlistBean> mBarberList=new ArrayList<>();
    private Context context;

    public CutHearAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if(mBarberList!=null){
            return mBarberList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cut_hear_grid_item, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(mBarberList!=null){
            ShopBean.BarberlistBean barberlistBean = mBarberList.get(position);
            viewHolder.setData(barberlistBean);
        }
        return convertView;
    }

    public void setData(List<ShopBean.BarberlistBean> barberlist) {
        this.mBarberList=barberlist;
        notifyDataSetChanged();
    }

     class ViewHolder {
        @BindView(R.id.civ_barber_photo)
        CircleImageView civBarberPhoto;
        @BindView(R.id.tv_barber_name)
        TextView tvBarberName;
        @BindView(R.id.tv_position)
        TextView tvPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(ShopBean.BarberlistBean barberlistBean) {
            if(barberlistBean!=null){
                tvBarberName.setText(barberlistBean.getName());
                tvPosition.setText(barberlistBean.getPositionName());
                GlideLoading.getInstance().loadImgUrlNyImgLoader(context,barberlistBean.getPhoto(),civBarberPhoto);
            }
        }
    }
}
