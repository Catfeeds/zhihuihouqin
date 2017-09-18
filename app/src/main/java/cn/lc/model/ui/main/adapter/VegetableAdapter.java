package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.VegetableBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */
public class VegetableAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<VegetableBean.PageEntity.ListEntity> data;

    public VegetableAdapter(Context context, List<VegetableBean.PageEntity.ListEntity> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vegetable, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder h = (ViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.minus)
        ImageView minus;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.add)
        ImageView add;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
