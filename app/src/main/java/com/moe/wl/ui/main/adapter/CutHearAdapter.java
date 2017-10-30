package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.BarberlistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/15 0015.
 */
public class CutHearAdapter extends BaseAdapter {
    private List<BarberlistBean> mBarberList = new ArrayList<>();
    private Context context;

    public CutHearAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (mBarberList != null) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cut_hear_grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mBarberList != null) {
            BarberlistBean barberlistBean = mBarberList.get(position);
            viewHolder.setData(barberlistBean);
        }
        return convertView;
    }

    public void setData(List<BarberlistBean> barberlist) {
        this.mBarberList = barberlist;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.civ_barber_photo)
        CircleImageView civBarberPhoto;
        @BindView(R.id.tv_barber_name)
        TextView tvBarberName;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        private BarberlistBean barberlistBean;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, BarberDetailActivity.class);
                    // TODO: 2017/9/28 0028 传递参数
*//* Bundle extras = intent.getExtras();
        address = extras.getString("address");
        shopName = extras.getString("shopName");
        barberlistBean = (BarberListsBean.BarberlistBean) extras.getSerializable("barberlistBean");
        *//*
                    Bundle bundle = new Bundle();
                    bundle.putString("address");
                    bundle.putString("shopName");
                    bundle.putSerializable("barberlistBean",barberlistBean);
                    context.startActivity(intent);
                }
            });*/
        }

        public void setData(BarberlistBean barberlistBean) {
            this.barberlistBean = barberlistBean;
            if (barberlistBean != null) {
                tvBarberName.setText(barberlistBean.getName());
                tvPosition.setText(barberlistBean.getPositionName());
                GlideLoading.getInstance().loadImgUrlNyImgLoader(context, barberlistBean.getPhoto(), civBarberPhoto/*, R.mipmap.ic_default_square*/);
            }
        }
    }
}
