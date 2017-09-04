package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/16 0016.
 */
public class DoctorDetailGrideAdapter extends BaseAdapter {

    private Context context;

    public DoctorDetailGrideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 16;
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
            convertView = View.inflate(parent.getContext(), R.layout.doc_detail_gride_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.setData();
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_order_detail_time)
        TextView tvOrderDetailTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData() {

        }
    }
}
