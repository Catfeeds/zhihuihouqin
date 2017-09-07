package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import cn.lc.model.ui.main.bean.ComplainHistoryBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */
public class ComplainHistoryAdapter extends RecyclerView.Adapter {

    private Context context;
    private ComplainHistoryBean bean;

    public ComplainHistoryAdapter(Context context, ComplainHistoryBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
