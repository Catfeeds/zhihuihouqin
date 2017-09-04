package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/15 0015.
 */
public class HealthServiceGridAdapter extends BaseAdapter {

    private Context context;
    private String[] des={"预约挂号","健康档案","专家坐诊"};

    public HealthServiceGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.health_service_grid_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(des[position]);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.civ_heath_service)
        CircleImageView civHeathService;
        @BindView(R.id.tv_health_service)
        TextView tvHealthService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(String s) {
            tvHealthService.setText(s);
        }
    }
}
