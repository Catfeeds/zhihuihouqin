package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.activity.OfficeSupplies.SpDetailActivity;
import cn.lc.model.ui.main.bean.OfficeIndexBean;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class OfficeSpAdapter extends BaseAdapter {
    private Context mContext;
    private List<OfficeIndexBean.NewProductListBean> data=new ArrayList<>();

    public OfficeSpAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if(data!=null&&data.size()>0){
            return data.size();
        }
        return 0;
    }

    @Override
    public OfficeIndexBean.NewProductListBean getItem(int position) {
        if(data!=null&&data.size()>0){
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.office_sp_item, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(data!=null&&data.size()>0){
            viewHolder.setData(data.get(position),position);
        }
        return convertView;
    }

    public void setData(List<OfficeIndexBean.NewProductListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.iv_sp_pic)
        ImageView ivSpPic;
        @BindView(R.id.tv_sp_des)
        TextView tvSpDes;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        private int id;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SpDetailActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("position",mPosition);
                    mContext.startActivity(intent);
                }
            });
        }
        public void setData(OfficeIndexBean.NewProductListBean newProductListBean, int position) {
            this.mPosition=position;
            if(newProductListBean!=null){
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,newProductListBean.getProductImg(),
                        ivSpPic);
                tvSpDes.setText(newProductListBean.getProductname());
                tvPrice.setText("￥"+newProductListBean.getPrice());
                id = newProductListBean.getId();
            }
        }
    }
}
