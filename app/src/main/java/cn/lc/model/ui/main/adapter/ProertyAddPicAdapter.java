package cn.lc.model.ui.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.utils.LogUtils;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class ProertyAddPicAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> pics = new ArrayList<>();

    public ProertyAddPicAdapter(Context context) {
        this.mContext = context;
    }
    public void setData(List<String> list){
        this.pics=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (pics != null && pics.size() > 0) {
            return pics.size();
        }
        return 0;
    }

    @Override
    public String getItem(int position) {
        if (pics != null && pics.size() > 0) {
            return pics.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            LayoutInflater from = LayoutInflater.from(mContext);
            convertView = from.inflate(R.layout.proerty_add_pic_item, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(pics!=null&&pics.size()>0){
            viewHolder.setData(pics.get(position),position);
        }
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.iv_up_photo)
        ImageView ivUpPhoto;
         private String path;
         private int mPosition;

         ViewHolder(View view) {
            ButterKnife.bind(this, view);
             if("addPhoto".equals(path)){
                 ivUpPhoto.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if(listener!=null){
                             listener.addPhotoClick();
                         }
                     }
                 });
             }

        }
         public void setData(String s, int position) {
             this.path=s;
             this.mPosition=position;
            if(!TextUtils.isEmpty(s)){
                GlideLoading.getInstance().loadImgUrlHeader(mContext,s,ivUpPhoto);
            }else{
                LogUtils.i("图片的路径为空");
            }
         }
     }
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener{
        void addPhotoClick();
    }
}
