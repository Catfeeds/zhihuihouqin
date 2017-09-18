package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.ui.main.bean.InformationClazzBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/13 0013
 */
public class InformationModuleAdapter extends BaseAdapter {

    private Context context;
    private List<InformationClazzBean.NoticeTypeListEntity> data;
    private int type;

    private OnAddModuleClick addListener;
    private OnDeleteModuleClick deleteListener;

    public InformationModuleAdapter(Context context, List<InformationClazzBean.NoticeTypeListEntity> data, int type) {
        this.context = context;
        this.data = data;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_information_module, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.module.setText(data.get(position).getName());

        if (type == 1) { // 我的
            if (data.get(position).getId() != 10000) {
                holder.img.setVisibility(View.VISIBLE);
            } else {
                holder.img.setVisibility(View.GONE);
            }
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteListener != null) {
                        deleteListener.onClick(data.get(position).getId(), data.get(position).getName());
                    }
                }
            });
        } else { // 全部
            holder.img.setVisibility(View.GONE);
            holder.module.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addListener != null) {
                        addListener.onClick(data.get(position).getId(), data.get(position).getName());
                    }
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.module)
        TextView module;
        @BindView(R.id.img)
        ImageView img;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnAddModuleClickListener(OnAddModuleClick listener) {
        this.addListener = listener;
    }

    public interface OnAddModuleClick {
        void onClick(int id, String content);
    }

    public void setOnDeleteModuleClickListener(OnDeleteModuleClick listener) {
        this.deleteListener = listener;
    }

    public interface OnDeleteModuleClick {
        void onClick(int id, String content);
    }
}
