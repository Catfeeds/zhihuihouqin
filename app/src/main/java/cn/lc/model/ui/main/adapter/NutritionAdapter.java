package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.NutritionBean;

/**
 * 类描述：营养套餐Adapter
 * 作者：Shixhe On 2017/9/5 0005
 */
public class NutritionAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private List<NutritionBean.PageEntity.ListEntity> data;
    private int type;
    private OnItemClickListener listener;

    public NutritionAdapter(Context context, List<NutritionBean.PageEntity.ListEntity> data, int type) {
        this.data = data;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_nutrition, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        ViewHolder holder = (ViewHolder) holder1;
        holder.title.setText(data.get(position).getMTitle());
        holder.time.setText(data.get(position).getMCreateTime());
        holder.from.setText(data.get(position).getMSource());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onItemClick(data.get(position));
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (type == 0)
            return data.size() > 4 ? 4 : data.size();
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.from)
        TextView from;
        @BindView(R.id.rl_item)
        RelativeLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void onItemClick(NutritionBean.PageEntity.ListEntity entity);
    }
}
