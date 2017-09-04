package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/24 0024.
 */

public class SignUpPersonAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public SignUpPersonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_person_header)
        ImageView ivPersonHeader;
        @BindView(R.id.person_name)
        TextView personName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private OnitemClickListener listener;

    public void setListener(OnitemClickListener listener) {
        this.listener = listener;
    }

    public interface OnitemClickListener{
        void onItemClickListener(int position);
    }
}
