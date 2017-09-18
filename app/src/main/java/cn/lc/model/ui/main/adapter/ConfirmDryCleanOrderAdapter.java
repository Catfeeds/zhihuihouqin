package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;

/**
 * Created by 我的电脑 on 2017/8/25 0025.
 */

public class ConfirmDryCleanOrderAdapter extends BaseAdapter {
    private List<OrderDryCleanBean.PageBean.ListBean> mList = new ArrayList();
    private Context mContent;

    public ConfirmDryCleanOrderAdapter(Context mContent) {
        this.mContent = mContent;
    }

    public void setList(List<OrderDryCleanBean.PageBean.ListBean> list) {
        mList.clear();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getCount()!=0){
                mList.add(list.get(i));
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dry_cleaner_order_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mList!=null&&mList.size()>0){
            viewHolder.setData(mList.get(position),position);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        private int mPosition;
        private OrderDryCleanBean.PageBean.ListBean listBean;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(OrderDryCleanBean.PageBean.ListBean listBean, int position) {
            this.mPosition=position;
            this.listBean=listBean;
            if(listBean!=null){
                if(listBean.getCount()>0){
                    tvCategory.setText(listBean.getName());
                    tvCount.setText("x"+listBean.getCount());
                    tvPrice.setText("￥"+listBean.getPrice());
                }else{
                    rlItem.setVisibility(View.GONE);
                }
            }
        }
    }
}
