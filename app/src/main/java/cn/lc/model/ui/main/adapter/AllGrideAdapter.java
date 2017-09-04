package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/21 0021.
 */
public class AllGrideAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> types=new ArrayList<>();
    private List<Integer> photos=new ArrayList<>();

    public AllGrideAdapter(Context context, List<String> myApps, List<Integer> myAppPhotos) {
        this.mContext = context;
        this.types = myApps;
        this.photos = myAppPhotos;
    }

    @Override
    public int getCount() {
        if (types != null) {
            return types.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (types != null) {
            return types.get(position);
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
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_tab2_gride_item, parent, false);
            viewHolder =new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(types.get(position),photos.get(position));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_app_logo)
        ImageView ivAppLogo;
        @BindView(R.id.iv_add_or_delete)
        ImageView ivAddOrDelete;
        @BindView(R.id.tv_service)
        TextView tvService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(String s, Integer integer) {
            tvService.setText(s);
            ivAppLogo.setImageResource(integer);
        }
    }
}
