package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.activity.OfficeSupplies.OfficeCategoryActivity;
import cn.lc.model.ui.main.bean.OfficeIndexBean;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class OfficeIndexAdapter extends BaseAdapter {
    private Context mContext;
    private List<OfficeIndexBean.CategoryListBean> data = new ArrayList<>();

    public OfficeIndexAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public OfficeIndexBean.CategoryListBean getItem(int position) {
        return data != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_item, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(data!=null&&data.size()>0){
            List<String> cNames=new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                String cname = data.get(i).getCname();
                cNames.add(cname);
            }
            viewHolder.setData(data.get(position),position,cNames);
        }
        return convertView;
    }

    public void setData(List<OfficeIndexBean.CategoryListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder {
        @BindView(R.id.iv_iv_grid_item)
        ImageView ivIvGridItem;
        @BindView(R.id.tv_grid_item)
        TextView tvGridItem;
        @BindView(R.id.ll_grid_container)
        LinearLayout llGridContainer;
         private int mPosition;

         ViewHolder(View view) {
            ButterKnife.bind(this, view);

            ivIvGridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,OfficeCategoryActivity.class);
                    intent.putExtra("position",mPosition);
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    intent.putExtra("json",json);
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(OfficeIndexBean.CategoryListBean categoryListBean, int position, List<String> cNames) {
            this.mPosition=position;
            if(categoryListBean!=null){
                GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                        categoryListBean.getImg(),ivIvGridItem);
                tvGridItem.setText(categoryListBean.getCname());

            }
        }
    }
}
