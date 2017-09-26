package cn.lc.model.ui.main.adapter;

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
import cn.lc.model.ui.main.bean.SearchCategoryBean;

/**
 * Created by 我的电脑 on 2017/9/9 0009.
 */

public class SearchCategoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchCategoryBean.TypelistBean> datas = new ArrayList<>();

    public void setDatas(List<SearchCategoryBean.TypelistBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public SearchCategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        if (datas != null) {
            return datas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (datas != null) {
            return datas.get(position);
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
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_category_item, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if(datas!=null&&datas.size()>0){
            viewHolder.setData(datas.get(position),position);
        }
        viewHolder.setData(datas.get(position),position);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.tv_child)
        TextView tvChild;
        @BindView(R.id.view)
        View view;
        private int mPosition;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
           /* view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(datas!=null&&datas.size()>0){
                        Log.e("选中的条目",mPosition+"");
                        SearchCategoryBean.TypelistBean typelistBean = datas.get(mPosition);
                        if(listener!=null){
                            listener.onItemClickListener(typelistBean.getId());
                        }
                    }
                }
            });*/
        }

        public void setData(SearchCategoryBean.TypelistBean s, int position) {
            this.mPosition=position;
            if(s!=null){
                String typename = s.getTypename();
                tvChild.setText(typename);
            }
            if((position+1)%3==0){
                view.setVisibility(View.GONE);
            }
        }
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(int typeId);
    }
}
