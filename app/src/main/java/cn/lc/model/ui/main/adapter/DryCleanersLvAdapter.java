package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/25 0025.
 */

public class DryCleanersLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> lists = Arrays.asList(
            "短薄衣物/配饰", "薄外套/夹克", "大衣/羽绒服"
            , "裤子(薄)", "裤子(厚)");

    public DryCleanersLvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return lists.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dry_cleaners_lv_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(lists.get(position));
        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_yiwu)
        TextView tvYiwu;
        @BindView(R.id.tv_money1)
        TextView tvMoney1;
        @BindView(R.id.iv_add1)
        ImageView ivAdd1;
        @BindView(R.id.tv_count1)
        TextView tvCount1;
        @BindView(R.id.iv_minus1)
        ImageView ivMinus1;
        private String data;
        private  int count=-1;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            count = Integer.parseInt(tvCount1.getText().toString().trim());

            ivAdd1.setOnClickListener(this);
            ivMinus1.setOnClickListener(this);
        }

        public void setData(String data) {
            this.data = data;
            tvYiwu.setText(data);
        }

        @Override
        public void onClick(View v) {
         /*   if(count>0){
                ivMinus1.setEnabled(true);
            }else{
                ivMinus1.setEnabled(false);
            }*/
            switch (v.getId()){
                case R.id.iv_add1:
                    count++;
                    break;
                case R.id.iv_minus1:
                    count--;
                    break;
            }
            tvCount1.setText(count+"");
        }
    }
    public interface OnClickLister{
        void onClickListener(int position);
    }
}
