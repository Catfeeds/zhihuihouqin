package com.moe.wl.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.ui.main.bean.AddressBean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */
public class AddressAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    //    private AddressBean data;
    private List<AddressBean.AddressListEntity> data;
    private OnEditClickListener listener;
    private OnSelectClickListener listen;
    private Context context;

    public AddressAdapter(Context context, List<AddressBean.AddressListEntity> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
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
            convertView = inflater.inflate(R.layout.item_address, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(data.get(position).getRealname());
        holder.phoneNumber.setText(data.get(position).getMobile());
        holder.address.setText(data.get(position).getAddress());
        holder.setData(position);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, AddAddressActivity.class));
                listener.onClick(position);
            }
        });

        return convertView;
    }

    private int selectPosition = -1;

    public void setSelectPosition(int position) {
        selectPosition = position;
    }

    class ViewHolder {
        ImageView checkBox;
        TextView name, phoneNumber, address;
        ImageView edit;

        private int mPosition;

        public ViewHolder(View view) {
            checkBox = (ImageView) view.findViewById(R.id.radio_button);
            name = (TextView) view.findViewById(R.id.name);
            phoneNumber = (TextView) view.findViewById(R.id.phone_number);
            address = (TextView) view.findViewById(R.id.address);
            edit = (ImageView) view.findViewById(R.id.edit);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectPosition = mPosition;
                    if (listen != null)
                        listen.onClick(data.get(selectPosition).getId(),
                                data.get(selectPosition).getRealname(),
                                data.get(selectPosition).getAddress());
                    notifyDataSetChanged();
                }
            });
        }

        void setData(int position) {
            mPosition = position;
            if (selectPosition == position) {
                checkBox.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.selected));
            } else {
                checkBox.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.unselected));
            }
        }

    }

    public interface OnEditClickListener {
        void onClick(int position);
    }

    public void setOnEditClickListener(OnEditClickListener listener) {
        this.listener = listener;
    }

    public interface OnSelectClickListener {
        void onClick(int id, String name, String address);
    }

    public void setOnSelectClickListener(OnSelectClickListener listener) {
        this.listen = listener;
    }
}
