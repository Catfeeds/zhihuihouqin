package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.moe.wl.R;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.PreOrderBean;

/**
 * Created by 我的电脑 on 2017/8/31 0031.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<PreOrderBean.ItemlistBeanX> mItemlist;
    private boolean select;

    public ExpandableListAdapter(Context context, List<PreOrderBean.ItemlistBeanX> itemlist) {
        this.mContext = context;
        this.mItemlist = itemlist;
    }
    /**
     * 获取一级标签总数
     */
    @Override
    public int getGroupCount() {
        LogUtils.i("mItemlist的数量："+mItemlist.size());
        return mItemlist.size();
    }
    /**
     * 获取一级标签下二级标签的总数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mItemlist.get(groupPosition).getItemlist().size();
    }

    /**
     * 获取一级标签内容
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mItemlist.get(groupPosition).getName();
    }

    /**
     * 获取一级标签下二级标签的内容
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mItemlist.get(groupPosition).getItemlist().get(childPosition);
    }

    /**
     * 获取一级标签的ID
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取二级标签的ID
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 对一级标签进行设置
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        convertView = (LinearLayout) LinearLayout.inflate(mContext,
                R.layout.item_group_layout, null);
        TextView tvYewuName = (TextView) convertView.findViewById(R.id.tv_yewu_name);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price_fanwei);
        ImageView upOrDown = (ImageView) convertView.findViewById(R.id.iv_upordown1);
        if (isExpanded) {
            upOrDown.setImageResource(R.drawable.group_down);
        } else {
            upOrDown.setImageResource(R.drawable.group_up);
        }
        tvYewuName.setText(mItemlist.get(groupPosition).getName());
        tvPrice.setText("￥" + mItemlist.get(groupPosition).getMinprice() + "-" + mItemlist.get(groupPosition).getMaxprice());
        return convertView;
    }

    /**
     * 对一级标签下的二级标签进行设置
     */
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = (LinearLayout) LinearLayout.inflate(mContext,
                R.layout.item_child_layout, null);
        TextView tvChildName = (TextView) convertView.findViewById(R.id.tv_child_name);
        TextView tvChilePrice = (TextView) convertView.findViewById(R.id.tv_child_price);
        ImageView ivCheck = (ImageView) convertView.findViewById(R.id.iv_check);
        final List<PreOrderBean.ItemlistBeanX.ItemlistBean> childItemlist = mItemlist.get(groupPosition).getItemlist();

        tvChildName.setText(childItemlist.get(childPosition).getName());
        tvChilePrice.setText("￥"+childItemlist.get(childPosition).getPrice());
        boolean select = childItemlist.get(childPosition).isSelect();
        if(select){
            ivCheck.setImageResource(R.drawable.select);
        }else{
            ivCheck.setImageResource(R.drawable.unselect);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean select = childItemlist.get(childPosition).isSelect();
                for (int i = 0; i < childItemlist.size(); i++) {
                    if(i==childPosition){
                        childItemlist.get(childPosition).setSelect(!select);
                    }else{
                        childItemlist.get(i).setSelect(false);
                    }
                    notifyDataSetChanged();
                }
                if(listener!=null){
                    listener.onItemClickListener();
                }
            }
        });
        return convertView;
    }

    /**
     * 当选择子节点的时候，调用该方法
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener();
    }
}
