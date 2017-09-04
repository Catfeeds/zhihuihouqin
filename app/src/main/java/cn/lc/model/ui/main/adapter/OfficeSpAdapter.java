package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.activity.OfficeSupplies.SpDetailActivity;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class OfficeSpAdapter extends BaseAdapter {
    private Context mContext;

    public OfficeSpAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.office_sp_item, parent, false);
                    viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.setData();
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.iv_sp_pic)
        ImageView ivSpPic;
        @BindView(R.id.tv_sp_des)
        TextView tvSpDes;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,SpDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData() {

        }
    }
}
