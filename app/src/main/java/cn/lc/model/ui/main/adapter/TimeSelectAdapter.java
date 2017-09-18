package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.JieYueTimeBean;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class TimeSelectAdapter extends BaseAdapter {
    private Context mContext;
    private List<JieYueTimeBean.TimelistBean> mList;

    public TimeSelectAdapter(Context context, List<JieYueTimeBean.TimelistBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public JieYueTimeBean.TimelistBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_select_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mList != null && mList.size() > 0) {
            viewHolder.setData(mList.get(position), position);
        }
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(JieYueTimeBean.TimelistBean timelistBean, int position) {
            if (timelistBean != null) {
                time.setText(timelistBean.getTimeperiod());

            }
        }
    }
}
