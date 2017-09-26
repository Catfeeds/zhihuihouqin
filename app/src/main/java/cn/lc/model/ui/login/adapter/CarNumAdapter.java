package cn.lc.model.ui.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/9/4 0004.
 */

public class CarNumAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> data = new ArrayList<>();

    public CarNumAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
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
        ViewHolder viewHolder=null;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView= inflater.inflate(R.layout.car_num_sheng, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(data!=null&&data.size()>0){
            viewHolder.setData(data.get(position),position);

        }
        return convertView;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
         private int mPosition;

         ViewHolder(View view) {
            ButterKnife.bind(this, view);
             view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                 }
             });
        }

         public void setData(String data, int position) {
             this.mPosition=position;
             tv.setText(data);
         }
     }

}
