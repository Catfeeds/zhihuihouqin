package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/8/17 0017.
 */
public class WaitOrderAdapter extends RecyclerView.Adapter {

    private Context context;

    public WaitOrderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.wait_order_fragment_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_order_photo)
        ImageView ivOrderPhoto;
        @BindView(R.id.tv_order_num)
        TextView tvOrderNum;
        @BindView(R.id.tv_place_order)
        TextView tvPlaceOrder;
        @BindView(R.id.tv_service_type)
        TextView tvServiceType;
        @BindView(R.id.tv_service_address)
        TextView tvServiceAddress;
        @BindView(R.id.tv_visit_time)
        TextView tvVisitTime;
        @BindView(R.id.tv_cancle_order)
        TextView tvCancle;
         @BindView(R.id.tv_chat)
        TextView tvChat;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View view1 = View.inflate(context, R.layout.dialog, null);
                    TextView tvNo= (TextView) view1.findViewById(R.id.tv_no);
                    TextView tvYes= (TextView) view1.findViewById(R.id.tv_yes);
                    // TODO: 2017/8/17 0017 分别对是否做点击事件
                    builder.setView(view1);
                    builder.show();
                }
            });
        }

        public void setData() {

        }
    }
}
