package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.utils.ServiceIntentUtils;
import com.moe.wl.ui.main.bean.ServiceDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.cn.util.ToastUtil;

/**
 * Created by 我的电脑 on 2017/8/21 0021.
 */
public class AllGrideAdapter extends BaseAdapter {
    private Context mContext;
    private int type = 0;
    private List<ServiceDataBean> data;
    private OnAddServiceListener addListener;
    private OnMinusServiceListener minusListener;

    public AllGrideAdapter(Context mContext, List<ServiceDataBean> data, int type) {
        this.mContext = mContext;
        this.data = data;
        this.type = type;
    }

    public AllGrideAdapter(Context mContext, List<ServiceDataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_tab2_gride_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (type != 0) {
            holder.ivAddOrDelete.setVisibility(View.VISIBLE);
        } else {
            holder.ivAddOrDelete.setVisibility(View.GONE);
        }

        if (data.get(position).getIsAdd() == 0) {
            // 显示加号
            holder.ivAddOrDelete.setImageResource(R.drawable.add_app);
        } else if (data.get(position).getIsAdd() == 1) {
            // 显示对号
            holder.ivAddOrDelete.setImageResource(R.drawable.unselected);
        } else {
            // 显示减号
            holder.ivAddOrDelete.setImageResource(R.drawable.delete_app);
        }

        holder.tvService.setText(data.get(position).getServiceName());
//        if (data.get(position).getSourceID() != 0) {
//            GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext, data.get(position).getSourceID(), holder.ivAppLogo);
//        } else {
        GlideLoading.getInstance().loadImgUrlNyImgLoader(mContext,
                ServiceIntentUtils.serviceImageData.get(data.get(position).getId() - 1), holder.ivAppLogo);
//        }

        holder.ivAppLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!OtherUtils.isAuth()){
                    // 没有认证
                    OtherUtils.showAuth(mContext);
                    return;
                }
                if (ServiceIntentUtils.goService(data.get(position).getId()) == null) {
                    ToastUtil.showToast(mContext, "敬请期待！");
                    return;
                }
                mContext.startActivity(new Intent(mContext, ServiceIntentUtils.goService(data.get(position).getId())));
            }
        });
        holder.ivAddOrDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("点击position：" + position);
                if (data.get(position).getIsAdd() == 0) {
                    // 添加服务
                    if (addListener != null) {
                        addListener.onAddClick(position);
                    }
                } else if (data.get(position).getIsAdd() == 1) {
                    // 已添加服务
                    ToastUtil.showToast(mContext, "已添加该服务！");
                } else {
                    // 删除服务
                    if (minusListener != null) {
                        minusListener.onMinusClick(position);
                    }
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_app_logo)
        ImageView ivAppLogo;
        @BindView(R.id.iv_add_or_delete)
        ImageView ivAddOrDelete;

        @BindView(R.id.tv_service)
        TextView tvService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnAddServiceListener {
        void onAddClick(int position);
    }

    public void setOnAddServiceListener(OnAddServiceListener listener) {
        addListener = listener;
    }

    public interface OnMinusServiceListener {
        void onMinusClick(int position);
    }

    public void setOnMinusServiceListener(OnMinusServiceListener listener) {
        minusListener = listener;
    }

}
