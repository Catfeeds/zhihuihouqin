package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.ActivityPostMulitPicAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.mywidget.BottomTimeDialog;
import com.moe.wl.ui.mywidget.StringListDialog;
import rx.Observable;
import rx.Subscriber;

public class ActivityPostedActivity extends Base2Activity implements View.OnClickListener {
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int READ_REQUEST_CODE = 2;
    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.iv_posted_des)
    ImageView ivPostedDes;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_posted_name)
    EditText etPostedName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_minzu)
    EditText etMinzu;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_activity_time)
    ImageView etActivityTime;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.et_zhubanfang)
    EditText etZhubanfang;
    @BindView(R.id.et_theme)
    EditText etTheme;
    @BindView(R.id.et_activity_address)
    EditText etActivityAddress;
    @BindView(R.id.et_person_limit)
    EditText etPersonLimit;
    @BindView(R.id.et_activity_content)
    EditText etActivityContent;
    @BindView(R.id.add_photo)
    ImageView addPhoto;
    @BindView(R.id.gv_detail_pic)
    GridView gvDetailPic;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_posted)
    LinearLayout activityPosted;


    private BottomSheetDialog dialog;
    private Bitmap photo;
    private String picPath;
    private boolean isOne;
    private ActivityPostMulitPicAdapter picAdapter;
    private List<String> picPaths;
    private List<String> photo1;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_posted);
        ButterKnife.bind(this);
    }


    @Override
    protected void initView() {
        photo1 = new ArrayList<>();
        picPaths = new ArrayList<>();
        initTitle();
        initGrlid();
        picPaths.add(0, "pic");
        picAdapter.setData(picPaths);
    }

    private void initGrlid() {
        picAdapter = new ActivityPostMulitPicAdapter(this);
        //GridViewImageAdapter gridViewImageAdapter = new GridViewImageAdapter();
        gvDetailPic.setAdapter(picAdapter);
        picAdapter.setListener(new ActivityPostMulitPicAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick(int position) {
                if(position==picPaths.size()-1){
                    isOne = false;
                    showButtomDialog();
                }
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("活动发布");
    }

    @OnClick({R.id.iv_posted_des, R.id.add_photo, R.id.rl_time, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_posted_des:
                Intent intent = new Intent(this, PostedNotesActivity.class);
                startActivity(intent);
                break;
            case R.id.add_photo:
                isOne = true;
                showButtomDialog();
                break;
         /*   case R.id.add_multiphoto:
                isOne = false;
                showButtomDialog();
                break;*/
            case R.id.rl_time:
                //底部弹出框
                showPop();
                break;
            case R.id.tv_submit://发布
                String name = etPostedName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String minzu = etMinzu.getText().toString().trim();
                String time = tvTime.getText().toString().trim();
                String address = etActivityAddress.getText().toString().trim();
                String limit = etPersonLimit.getText().toString().trim();
                String content = etActivityContent.getText().toString().trim();
                String zhubanfang = etZhubanfang.getText().toString().trim();
                String theme = etTheme.getText().toString().trim();
                checkIsNull(phone, content, address, zhubanfang, time, theme, limit, name, minzu);
                postActivity(phone, content, address, zhubanfang, time, theme, limit, photo1, picPaths, name, minzu);
                break;
        }
    }

    private void checkIsNull(String phone, String content, String address,
                             String zhubanfang, String time, String theme, String limit,
                             String name, String minzu) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(content) || TextUtils.isEmpty(address) ||
                TextUtils.isEmpty(zhubanfang) || TextUtils.isEmpty(time) || TextUtils.isEmpty(theme) ||
                TextUtils.isEmpty(limit) || TextUtils.isEmpty(name) || TextUtils.isEmpty(minzu)) {
            showToast("请将信息填写完整");
            return;
        }
    }

    private void showButtomDialog() {
        final StringListDialog dialog = new StringListDialog(this, R.style.dialog_style);
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请开启摄像机权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_REQUEST_CODE);//自定义的code
        } else {
            initPhoto();
        }
    }

    private void doTakePhoto() {
        String state = Environment.getExternalStorageState();// 获取内存卡可用状态
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 内存卡状态可用
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请开启摄像机权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        CAMERA_REQUEST_CODE);//自定义的code
            } else {
                init();
            }

        } else {
            // 不可用
            Toast.makeText(this, "内存不可用", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //开启照相机
                init();
            } else {
                Toast.makeText(this, "您已拒绝了访问的权限", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == READ_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
                initPhoto();
            } else {
                Toast.makeText(this, "您已拒绝了访问的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void init() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 1);
    }

    public void initPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 设定结果返回
        startActivityForResult(i, 2);
    }

    //时间选择器
    private void showPop() {
        BottomTimeDialog dialog = new BottomTimeDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int i1, int i2, int i3, int i4, int i5) {
                tvTime.setText(i1 + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5);
            }
        });

    }

    private void postActivity(String aContactMobile, String aContent, String aPlace
            , String aSponsor, String aTime, String aTitle, String aTotal, List<String> photos, List<String> imgs, String aRealname,
                              String aNaion) {
        Observable observable = RetrofitUtils.getInstance().postActivity(aContactMobile, aContent, aPlace, aSponsor, aTime, aTitle,
                aTotal, photos, imgs, aRealname, aNaion);
        showProgressDialog();
        observable.subscribe(new Subscriber<ActivityPostBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("发布：" + e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean o) {
                if (o.getErrCode() == 0) {
                    showToast("发布成功");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(2);
                            finish();
                        }
                    }).start();
                } else {
                    showToast("发布失败了");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                dialog.dismiss();
                break;
            case R.id.tv_confirm:
                dialog.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    if (resultCode == RESULT_OK)
                        // 两种方式 获取拍好的图片
                        if (data.getData() != null || data.getExtras() != null) { // 防止没有返回结果
                            Uri uri = data.getData();
                            LogUtils.i("拍照获取uri:" + uri);
                            if (uri != null) {
                                this.photo = BitmapFactory.decodeFile(uri.getPath()); // 拿到图片
                            }
                            if (photo == null) {
                                Bundle bundle = data.getExtras();
                                if (bundle != null) {
                                    photo = (Bitmap) bundle.get("data");
                                    FileOutputStream fileOutputStream = null;
                                    try {
                                        // 获取 SD 卡根目录 生成图片并
                                        String saveDir = Environment
                                                .getExternalStorageDirectory()
                                                + "/dhj_Photos";
                                        // 新建目录
                                        File dir = new File(saveDir);
                                        if (!dir.exists())
                                            dir.mkdir();
                                        // 生成文件名
                                        SimpleDateFormat t = new SimpleDateFormat(
                                                "yyyyMMddssSSS");
                                        String filename = "MT" + (t.format(new Date()))
                                                + ".jpg";
                                        // 新建文件
                                        File file = new File(saveDir, filename);
                                        // 打开文件输出流
                                        fileOutputStream = new FileOutputStream(file);
                                        // 生成图片文件
                                        this.photo.compress(Bitmap.CompressFormat.JPEG,
                                                100, fileOutputStream);
                                        // 相片的完整路径
                                        this.picPath = file.getPath();
                                        //ImageView imageView = (ImageView) findViewById(R.id.imageView1);
                                        if (isOne == true) {
                                            addPhoto.setImageBitmap(photo);
                                            photo1.add(picPath);
                                        } else {
                                            if (picPaths.size() < 6) {
                                                picPaths.add(0, picPath);
                                                picAdapter.setData(picPaths);
                                            } else {
                                                showToast("最多可发布6张图片");
                                            }
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    Toast.makeText(getApplicationContext(), "获取到了",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "找不到图片",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    break;
                case 2: {
                    //打开相册并选择照片，这个方式选择单张
                    // 获取返回的数据，这里是android自定义的Uri地址
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // 获取选择照片的数据视图
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    // 从数据视图中获取已选择图片的路径
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    // 将图片显示到界面上
                    //ImageView imageView = (ImageView) findViewById(R.id.imageView1);
                    if (isOne == true) {
                        addPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                        photo1.add(picturePath);
                    } else {
                        if (picPaths.size() < 6) {
                            picPaths.add(picturePath);
                            picAdapter.setData(picPaths);
                        } else {
                            showToast("最多可发布6张图片");
                        }

                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

}
