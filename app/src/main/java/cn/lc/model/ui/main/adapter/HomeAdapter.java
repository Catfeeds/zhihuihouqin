package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.utils.ServiceIntentUtils;
import cn.lc.model.ui.main.bean.HomePageBean;


/**
 * Created by 我的电脑 on 2017/8/14 0014.
 */

public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private List<HomePageBean.ServiceListEntity> data;

    public HomeAdapter(Context context, List<HomePageBean.ServiceListEntity> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (data.get(position).getId() == 10001) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, R.drawable.more, viewHolder.ivIvGridItem);
            viewHolder.tvGridItem.setText("更多");
        } else {
            viewHolder.tvGridItem.setText(data.get(position).getName());
//            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, data.get(position).getSmallimg(), viewHolder.ivIvGridItem);
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                    ServiceIntentUtils.serviceImageData.get(data.get(position).getId() - 1), viewHolder.ivIvGridItem);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_iv_grid_item)
        ImageView ivIvGridItem;
        @BindView(R.id.tv_grid_item)
        TextView tvGridItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
