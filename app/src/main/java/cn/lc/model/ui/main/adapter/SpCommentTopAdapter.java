package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.utils.LogUtils;

/**
 * Created by 我的电脑 on 2017/9/19 0019.
 */

public class SpCommentTopAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> name=new ArrayList<>();
    private List<String> nums=new ArrayList<>();


    public SpCommentTopAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.spcomment_top_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if(nums!=null&&nums.size()>0){
            viewHolder.setData(name.get(position),nums.get(position),position);
        }
    }

    @Override
    public int getItemCount() {
        if(nums!=null&nums.size()>0){
            return nums.size();
        }
        return 0;
    }

    public void setData(List<String> names, List<String> num) {
        LogUtils.i("num==="+num);
        this.name=names;
        this.nums=num;
        notifyDataSetChanged();
    }
    private int selectPosition=0;
     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_comment_type)
        TextView tvCommentType;
        @BindView(R.id.tv_comment_count)
        TextView tvCommentCount;
        private int mPosition;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClickListener(mPosition);
                        selectPosition=mPosition;
                        notifyDataSetChanged();
                    }
                }
            });
        }

        public void setData(String s, String s1, int position) {
            this.mPosition=position;
            if(selectPosition==position){
                tvCommentCount.setTextColor(Color.parseColor("#E67771"));
                tvCommentType.setTextColor(Color.parseColor("#E67771"));
            }else{
                tvCommentCount.setTextColor(Color.parseColor("#333333"));
                tvCommentType.setTextColor(Color.parseColor("#333333"));
            }
            tvCommentType.setText(s);
            tvCommentCount.setText(s1);
        }

     }
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void onItemClickListener(int index);
    }
}
