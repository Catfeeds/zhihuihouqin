package cn.lc.model.ui.mywidget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.lc.model.R;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class ImageSelect {
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int READ_REQUEST_CODE = 2;
    private  Context mContext;

    public ImageSelect(Context context){
        this.mContext=context;
    }
    public void showChooseImgDialog(){
        final StringListDialog dialog = new StringListDialog(mContext, R.style.dialog_style);
        List<String> itemList = new ArrayList<String>();
        itemList.add("相机拍摄");
        itemList.add("手机相册");
        itemList.add("取消");
        dialog.setData(itemList);
        dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:// 拍照上传
                        doTakePhoto();
                        dialog.dismiss();
                        break;
                    case 1:// 从gallery选择
                        doPickPhotoFromGallery();
                        dialog.dismiss();
                        break;
                    case 2:// 取消
                        dialog.dismiss();
                        break;
                }
            }
        });
        dialog.show();
    }

    private void doPickPhotoFromGallery() {
        // 打开本地相册
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请开启摄像机权限
            ActivityCompat.requestPermissions(((Activity) mContext), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_REQUEST_CODE);//自定义的code
        } else {
            initPhoto();
        }
    }

    private void doTakePhoto() {

    }

    private void initPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 设定结果返回
        ((Activity) mContext).startActivityForResult(i, 2);
    }
    public void doResult(int requestCode, int resultCode, Intent data, OnGetPhotoListener onGetPhotoListener) {

    }
    /**
     * 得到照片文件的回调
     */
    public interface OnGetPhotoListener {
        public void onGetPhoto(File photoFile);
    }
}
