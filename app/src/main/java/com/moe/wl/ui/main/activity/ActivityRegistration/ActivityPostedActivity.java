package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.SystemClock;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jhworks.library.ImageSelector;
import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.ActivityPostMulitPicAdapter;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.mywidget.BottomTimeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

/**
 * 发布活动
 */
public class ActivityPostedActivity extends Base2Activity implements View.OnClickListener {
    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
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
    NoSlidingGridView gvDetailPic;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.activity_posted)
    LinearLayout activityPosted;

    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_complain.jpg";
    private BottomSheetDialog dialog;
    private Bitmap photo;
    private String picPath;
    private boolean isOne;
    private ActivityPostMulitPicAdapter picAdapter;
    private String imageUri;
    private ArrayList<String> paths;
    private ArrayList<String> pics;
    private int REQUEST_IMAGE_ONE = 1;
    private int REQUEST_IMAGE_MANY = 2;
    private GridViewImageAdapter imageAdapter;
    private ArrayList<String> mpic;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_posted);
        ButterKnife.bind(this);
    }


    @Override
    protected void initView() {
        //单张图片的集合
        paths = new ArrayList<>();
        //多张图片的集合
        pics = new ArrayList<>();
        pics.add("addPhoto");
        initTitle();
        initGrlid();
    }

    private void initGrlid() {
        //picAdapter = new ActivityPostMulitPicAdapter(this);
        //GridViewImageAdapter gridViewImageAdapter = new GridViewImageAdapter();
//        gvDetailPic.setAdapter(picAdapter);
//        picAdapter.setData(pics);

        imageAdapter = new GridViewImageAdapter(this, pics, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                ImageSelector selector = ImageSelector.create();
                // selector.single();  // single mode
                selector.multi();  // multi mode, default mode;
                selector.origin(mpic) // original select data set, used width #.multi()
                        .showCamera(true)   // show camera or not. true by default
                        .count(6)   // max select image size, 9 by default. used width #.multi()
                        .spanCount(3)  // image span count ，default is 3.
                        .start(ActivityPostedActivity.this, REQUEST_IMAGE_MANY);

            }
        });
        gvDetailPic.setAdapter(imageAdapter);

       /* picAdapter.setListener(new ActivityPostMulitPicAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick(int position) {
                if (position == pics.size() - 1) {
                    *//*isOne = false;
                    showButtomDialog();*//*
                    ImageSelector selector = ImageSelector.create();
                    // selector.single();  // single mode
                    selector.multi();  // multi mode, default mode;
                    selector.origin(pics) // original select data set, used width #.multi()
                            .showCamera(true)   // show camera or not. true by default
                            .count(6)   // max select image size, 9 by default. used width #.multi()
                            .spanCount(3)  // image span count ，default is 3.
                            .start(ActivityPostedActivity.this, REQUEST_IMAGE_MANY);
                }
            }
        });*/
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

                ImageSelector selector = ImageSelector.create();
                selector.single();  // single mode
                selector.multi();  // multi mode, default mode;
                selector.origin(paths) // original select data set, used width #.multi()
                        .showCamera(true)   // show camera or not. true by default
                        .count(1)   // max select image size, 9 by default. used width #.multi()
                        .spanCount(3)  // image span count ，default is 3.
                        .start(ActivityPostedActivity.this, REQUEST_IMAGE_ONE);

                /*isOne = true;
                showButtomDialog();*/
                break;
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

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(content) || TextUtils.isEmpty(address) ||
                        TextUtils.isEmpty(zhubanfang) || TextUtils.isEmpty(time) || TextUtils.isEmpty(theme) ||
                        TextUtils.isEmpty(limit) || TextUtils.isEmpty(name) || TextUtils.isEmpty(minzu)) {
                    showToast("请将信息填写完整");
                    return;
                }
                if (!VerifyCheck.isMobilePhoneVerify(phone)) {
                    showToast("您输入的手机号码有误请重新输入");
                    return;
                }
                if (DateUtil.compareDate(time, DateUtil.getTimeyyyyMMddHHmm()) == 2) {
                    showToast("活动时间不能早于当前时间");
                    return;
                }
                if (TextUtils.isEmpty(paths.get(0))) {
                    showToast("请选择活动的封面图");
                    return;
                }
                if (pics.size() <= 1) {
                    showToast("请选择活动详情图片");
                    return;
                }
                //checkIsNull(phone, content, address, zhubanfang, time, theme, limit, name, minzu);
                postActivity(phone, content, address, zhubanfang, time, theme, limit, paths, pics, name, minzu);
                break;
        }
    }

   /* private void showButtomDialog() {
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

    *//**
     * 相机拍照
     *//*
    private void doTakePhoto() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        imageUri = imageLocation + DateUtil.yyyyMMdd_HHmmss.format(new Date()) + imageName;
        File file = new File(imageUri);
        File file1 = new File(imageLocation);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LogUtils.d("imageUri:" + imageUri);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(imageUri));
        //为拍摄的图片指定一个存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, TAKE_PHOTO_CAMERA);
        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    */

    /**
     * 相册选取
     *//*
    private void doPickPhotoFromGallery() {
        PermissionGen.with(this)
                .addRequestCode(200)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
    }

    @PermissionSuccess(requestCode = 200)
    public void getPhotoSucc() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }

    @PermissionFail(requestCode = 200)
    public void getPhotoFialed() {
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_ONE) {
                paths = data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT);
                // data  ..
                GlideLoading.getInstance().loadImgUrlNyImgLoader(this, paths.get(0), addPhoto);
            } else if (requestCode == REQUEST_IMAGE_MANY) {
                mpic = data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT);
                pics.clear();
                pics.add("addPhoto");
                pics.addAll(0, mpic);
                for (int i = 0; i < pics.size(); i++) {
                    LogUtils.i("mSelectPath==" + pics.size() + "=======" + pics.get(i));
                }

                imageAdapter.notifyDataSetChanged();
                //picAdapter.setData(pics);
            }
        }
/*
        if (resultCode != RESULT_OK) {
            LogUtils.d("requestCode : " + requestCode + "resultCode : " + resultCode);
        } else {
            switch (requestCode) {

                case TAKE_PHOTO_CAMERA:// 相机
                    if (null != imageUri) {
                        if (isOne) {
                            paths.clear();
                            paths.add(imageUri);
                            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, imageUri, addPhoto);
                        } else {
                            pics.add(0, imageUri);
                            picAdapter.setData(pics);
                        }
                    }
                    break;
                case TAKE_PHOTO_ALBUM:// 相册
                    if (data != null) {
                        String[] proj = {MediaStore.Images.Media.DATA};
                        //好像是android多媒体数据库的封装接口，具体的看Android文档
                        Cursor cursor = managedQuery(data.getData(), proj, null, null, null);
                        //按我个人理解 这个是获得用户选择的图片的索引值
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        //将光标移至开头 ，这个很重要，不小心很容易引起越界
                        cursor.moveToFirst();
                        //最后根据索引值获取图片路径
                        String path = cursor.getString(column_index);
                        LogUtils.d("返回的图片地址：" + path);
                        if (isOne) {
                            paths.clear();
                            paths.add(path);
                            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, path, addPhoto);
                        } else {
                            pics.add(0, path);
                            picAdapter.setData(pics);
                        }
                    }
                    break;
            }
        }*/
    }

    //时间选择器
    private void showPop() {
        BottomTimeDialog dialog = new BottomTimeDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int i1, int i2, int i3, String i4, String i5) {
                String time = i1 + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5;
                tvTime.setText(time);

            }
        });
    }

    private void postActivity(String aContactMobile, String aContent, String aPlace
            , String aSponsor, String aTime, String aTitle, String aTotal, List<String> photos, List<String> imgs, String aRealname,
                              String aNaion) {
        imgs.remove(imgs.get(imgs.size()-1));//将最后一个图片从集合中清除
        for (String str : imgs) {
            LogUtils.i("多张图片："+str);
        }
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

  /*  @Override
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
    }*/

}
