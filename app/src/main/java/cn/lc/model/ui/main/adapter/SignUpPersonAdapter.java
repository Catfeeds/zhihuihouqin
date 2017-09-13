package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
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
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.ui.main.activity.ActivityRegistration.SignUpPersonActivity;
import cn.lc.model.ui.main.bean.ActivitySignListBean;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 我的电脑 on 2017/8/24 0024.
 */

public class SignUpPersonAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ActivitySignListBean.MemberlistBean> data = new ArrayList<>();

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
        if (data != null) {
            viewHolder.setData(data.get(position), position);

        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public void setData(List<ActivitySignListBean.MemberlistBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_person_header)
        CircleImageView ivPersonHeader;
        @BindView(R.id.person_name)
        TextView personName;
        private ActivitySignListBean.MemberlistBean memberlistBean;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SignUpPersonActivity.class);
                    intent.putExtra("name", memberlistBean.getAsUsername());
                    intent.putExtra("phone", memberlistBean.getAsMebile());
                    // TODO: 2017/9/7 0007 没有民族字段
                    //bundle.putString("minzu",memberlistBean.get());
                    mContext.startActivity(intent);
                }
            });
        }

        public void setData(ActivitySignListBean.MemberlistBean memberlistBean, int position) {
            this.memberlistBean = memberlistBean;
            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                    memberlistBean.getAsAvatar(), ivPersonHeader);
            personName.setText(memberlistBean.getAsUsername());
        }
    }

}
