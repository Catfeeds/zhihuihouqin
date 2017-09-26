package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.activity.DoctorDetailActivity;
import com.moe.wl.ui.main.bean.DoctorListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/15 0015.
 */
public class RegistrationAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<DoctorListBean.DoctorlistBean> data = new ArrayList<>();

    public RegistrationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registration_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (data != null) {
            viewHolder.setData(data.get(position));

        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<DoctorListBean.DoctorlistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_photo)
        CircleImageView civPhoto;
        @BindView(R.id.tv_doc_name)
        TextView tvDocName;
        @BindView(R.id.tv_zhicheng)
        TextView tvZhicheng;
        @BindView(R.id.tv_order_percent)
        TextView tvOrderPercent;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.tv_grade)
        TextView tvGrade;
        @BindView(R.id.tv_seeing)
        TextView tvSeeing;
        @BindView(R.id.tv_hos)
        TextView tvHos;
        @BindView(R.id.tv_hospital)
        TextView tvHospital;
        @BindView(R.id.tv_able_order)
        TextView tvAbleOrder;
        @BindView(R.id.tv_shan_chang)
        TextView tvShanChang;

        private DoctorListBean.DoctorlistBean listBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvAbleOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击预约医师，进入医生详情界面
                    Log.e("dianji","进入到医生详情界面");
                    Intent intent = new Intent(context, DoctorDetailActivity.class);
                    intent.putExtra("listBean", listBean);
                    context.startActivity(intent);
                }
            });
        }

        public void setData(DoctorListBean.DoctorlistBean listBean) {
            this.listBean = listBean;
            tvDocName.setText(listBean.getRealname());
            tvZhicheng.setText(listBean.getPositionname());
            //ratingBar.setRating(listBean.get);
            tvSeeing.setText(listBean.getConsultcount()+"");
            tvHospital.setText(listBean.getHospitalname());
            tvOrderPercent.setText(listBean.getRemaincount() / listBean.getTotalcount()+"");
            tvShanChang.setText(listBean.getSkilledinfo());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(context, listBean.getPhoto(), civPhoto);

        }
    }
}
