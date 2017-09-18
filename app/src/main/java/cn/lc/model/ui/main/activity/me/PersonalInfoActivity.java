package cn.lc.model.ui.main.activity.me;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.activity.PositionActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.LogUtil;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int READ_REQUEST_CODE = 2;
    private static final int SEXREQUESTCODE = 3;
    private static final int POSITIONREQUESTCODE = 4;
    @BindView(R.id.personal_info_titlebar)
    TitleBar titleBar;
    @BindView(R.id.civ_header)
    CircleImageView civHeader;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.et_nicheng)
    EditText etNicheng;
    @BindView(R.id.ll_nicheng)
    LinearLayout llNicheng;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.et_room_num)
    EditText etRoomNum;
    @BindView(R.id.ll_roomnum)
    LinearLayout llRoomnum;
    @BindView(R.id.et_office_phone)
    EditText etOfficePhone;
    @BindView(R.id.ll_office_phone)
    LinearLayout llOfficePhone;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.rl_position)
    RelativeLayout rlPosition;
    @BindView(R.id.activity_personal_info)
    LinearLayout activityPersonalInfo;
    private BottomSheetDialog dialog;
    private Bitmap photo;
    private String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        titleBar.setBack(true);
        titleBar.setTitle("个人信息");
    }

    @OnClick({R.id.rl_header, R.id.rl_sex, R.id.rl_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_header:
                dialog = new BottomSheetDialog(this, R.style.bottomdialog);
                dialog.show();
                View view1 = View.inflate(this, R.layout.header, null);
                LinearLayout llContainer = (LinearLayout) view1.findViewById(R.id.ll_container);
                CardView xiangji = (CardView) view1.findViewById(R.id.cv_xiangji);
                CardView photo = (CardView) view1.findViewById(R.id.cv_photo);
                CardView cancle = (CardView) view1.findViewById(R.id.cv_cancle);
                xiangji.setOnClickListener(this);
                photo.setOnClickListener(this);
                cancle.setOnClickListener(this);
                dialog.setContentView(view1);
                break;
            case R.id.rl_sex:
                Intent intent = new Intent(this, SexActivity.class);
                startActivityForResult(intent,SEXREQUESTCODE);
                break;
            case R.id.rl_position:
                Intent intent1=new Intent(this,PositionActivity.class);
                startActivityForResult(intent1,POSITIONREQUESTCODE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_xiangji:
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
                dialog.dismiss();
                break;
            case R.id.cv_photo:
                // 打开本地相册
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请开启摄像机权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_REQUEST_CODE);//自定义的code
                } else {
                    initPhoto();
                }
                dialog.dismiss();
                break;
            case R.id.cv_cancle:
                dialog.dismiss();
                break;
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
        }else if(requestCode==READ_REQUEST_CODE){
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
                    // 两种方式 获取拍好的图片
                    if (data.getData() != null || data.getExtras() != null) { // 防止没有返回结果
                        Uri uri = data.getData();
                        LogUtils.i("拍照获取uri:"+uri);
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
                                    civHeader.setImageBitmap(photo);
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
                    civHeader.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                }
                case SEXREQUESTCODE:
                    if(resultCode== Constants.SELECTSEX){
                        if(data!=null){
                            String sex = data.getStringExtra("sex");
                            tvSex.setText(sex);
                        }
                    }
                    break;
                case POSITIONREQUESTCODE:
                    if(resultCode==RESULT_OK){
                        if(data!=null){
                            String position = data.getStringExtra("position");

                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
