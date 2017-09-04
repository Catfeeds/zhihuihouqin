package cn.lc.model.ui.main.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/22 0022.
 */
public class BarberRvAdapter extends RecyclerView.Adapter {
    private List<String> workDays= Arrays.asList(
            "星期日","星期一","星期二","星期三","星期四","星期五","星期六");

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.barber_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(workDays.get(position));

    }

    @Override
    public int getItemCount() {
        return 7;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_workday)
        TextView tvWorkday;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.view_to_red)
        View viewToRed;
        @BindView(R.id.ll_item_container)
        LinearLayout llItemContainer;

         ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);

           /* llItemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     recoverNomal();
                     tvWorkday.setTextColor(Color.parseColor("#e75c5c"));
                     tvDate.setTextColor(Color.parseColor("#e75c5c"));
                     viewToRed.setBackgroundColor(Color.parseColor("#e75c5c"));
                     BarberRvAdapter.this.notifyDataSetChanged();
                 }
             });

        }
         private void recoverNomal(){
             for (int i = 0; i < getItemCount(); i++) {
                 tvWorkday.setTextColor(Color.parseColor("#9a0000"));
                 tvDate.setTextColor(Color.parseColor("#9a0000"));
                 viewToRed.setBackgroundColor(Color.TRANSPARENT);
             }

         }

         public void setData(String workDay) {
            tvWorkday.setText(workDay);
         }
     }
}
