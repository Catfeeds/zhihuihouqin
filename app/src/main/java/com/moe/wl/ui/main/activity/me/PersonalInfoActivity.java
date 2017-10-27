package com.moe.wl.ui.main.activity.me;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moe.wl.R;
import com.moe.wl.framework.application.SoftApplication;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.activity.PositionActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.ChangeUserInfo;
import com.moe.wl.ui.main.bean.UpLoadHeaderBean;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.main.model.UserInfoModel;
import com.moe.wl.ui.main.modelimpl.UserInfoModelImpl;
import com.moe.wl.ui.main.presenter.UserInfoPresenter;
import com.moe.wl.ui.main.view.UserInfoView;
import com.moe.wl.ui.mywidget.StringListDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import mvp.cn.util.ToastUtil;

public class PersonalInfoActivity extends BaseActivity<UserInfoModel, UserInfoView, UserInfoPresenter> implements UserInfoView {


    private static final int SEXREQUESTCODE = 3;
    private static final int POSITIONREQUESTCODE = 4;
    @BindView(R.id.personal_info_titlebar)
    TitleBar titleBar;
    CircleImageView iv_header;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_nicheng)
    EditText tvNicheng;
    @BindView(R.id.ll_nicheng)
    LinearLayout llNicheng;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.et_build_num)
    EditText etBuildNum;
    @BindView(R.id.ll_buildnum)
    LinearLayout llBuildnum;
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
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.rl_position)
    RelativeLayout rlPosition;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.activity_personal_info)
    LinearLayout activityPersonalInfo;


    private boolean isEdit = false;
    private int positionId;
    private int sexid;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;
    private final String IMG_TEMP_FILE = SoftApplication.getInstance().getFilesDir().getAbsolutePath() + "/ImageHead" + ".png";
    private File mFile;
    private String url;
    private String nickname;
    private String phone;
    private String tel;
    private String buildNum;
    private String roomNum;
    private String sex;
    private UserInfoBean.UserinfoEntity userinfo;

    @Override
    public UserInfoPresenter createPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    public UserInfoModel createModel() {
        return new UserInfoModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        iv_header = (CircleImageView) findViewById(R.id.iv_header);
        titleBar.setBack(true);
        //此file用来保存剪裁后的头像图片
        mFile = new File(IMG_TEMP_FILE);
        titleBar.setTitle("个人信息");
        titleBar.setTitleRight("编辑");
        titleBar.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdit) {//可编辑
                    isEdit = !isEdit;
                    tvNicheng.setFocusableInTouchMode(true);
                    tvNicheng.setFocusable(true);
                    tvNicheng.requestFocus();
                    tvPhone.setFocusableInTouchMode(true);
                    tvPhone.setFocusable(true);
                    tvPhone.requestFocus();
                    etOfficePhone.setFocusableInTouchMode(true);
                    etOfficePhone.setFocusable(true);
                    etOfficePhone.requestFocus();
                    etBuildNum.setFocusableInTouchMode(true);
                    etBuildNum.setFocusable(true);
                    etBuildNum.requestFocus();
                    etRoomNum.setFocusableInTouchMode(true);
                    etRoomNum.setFocusable(true);
                    etRoomNum.requestFocus();
                    rlSex.setClickable(true);
                    rlPosition.setClickable(true);
                    rlHeader.setClickable(true);
                    titleBar.setTitleRight("完成");
                } else {//不可编辑
                    tvNicheng.setFocusable(false);
                    tvNicheng.setFocusableInTouchMode(false);

                    tvPhone.setFocusable(false);
                    tvPhone.setFocusableInTouchMode(false);

                    etOfficePhone.setFocusable(false);
                    etOfficePhone.setFocusableInTouchMode(false);

                    etBuildNum.setFocusable(false);
                    etBuildNum.setFocusableInTouchMode(false);

                    etRoomNum.setFocusable(false);
                    etRoomNum.setFocusableInTouchMode(false);
                    rlSex.setClickable(false);
                    rlPosition.setClickable(false);
                    rlHeader.setClickable(false);

                    titleBar.setTitleRight("编辑");
                    //修改用户信息
                    /*用户信息检测*/
                    userInfoCheck();

                }
            }
        });
        //获取用户信息
        getPresenter().getUserInfo();
    }

    private void userInfoCheck() {
        sex = tvSex.getText().toString();
        if ("男".equals(sex)) {
            sexid = 1;
        } else {
            sexid = 0;
        }
        nickname = tvNicheng.getText().toString();
        phone = tvPhone.getText().toString();
        tel = etOfficePhone.getText().toString();
        buildNum = etBuildNum.getText().toString();
        roomNum = etRoomNum.getText().toString();
        if (TextUtils.isEmpty(nickname) || TextUtils.isEmpty(tel) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(buildNum) || TextUtils.isEmpty(roomNum)) {
            showToast("请将个人信息填写完整");
            return;
        }
        isEdit = !isEdit;
        getPresenter().changeUserInfo(sexid, nickname, url, positionId, tel, roomNum, phone, buildNum);
    }

    @OnClick({R.id.rl_header, R.id.rl_sex, R.id.rl_position})
    public void onViewClicked(View view) {
        if (!isEdit) {
            ToastUtil.showToast(this, "点击编辑后才可修改资料");
            return;
        }
        switch (view.getId()) {
            case R.id.rl_header:
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
                break;
            case R.id.rl_sex:
                Intent intent = new Intent(this, SexActivity.class);
                intent.putExtra("sex", tvSex.getText().toString().trim());
                startActivityForResult(intent, SEXREQUESTCODE);
                break;
            case R.id.rl_position:
                Intent intent1 = new Intent(this, PositionActivity.class);
                startActivityForResult(intent1, POSITIONREQUESTCODE);
                break;
        }
    }

    private void doPickPhotoFromGallery() {
        PermissionGen.with(this)
                .addRequestCode(200)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
    }

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
        Intent openCameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        tempUri = Uri.fromFile(new File(Environment
                .getExternalStorageDirectory(), "temp_image.jpg"));
        // 将拍照所得的相片保存到SD卡根目录
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
//        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
    }

    @PermissionSuccess(requestCode = 200)
    public void doSomething2() {
        Intent openAlbumIntent = new Intent(
                Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
//        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
//        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 200)
    public void doFailSomething2() {
//        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    if (data.getData() == null) {
                        LogUtils.d("Data为空！");
                        return;
                    }
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
                case SEXREQUESTCODE:
                    if (data != null) {
                        sex = data.getStringExtra("sex");
                        tvSex.setText(sex);
                    }
                    break;
                case POSITIONREQUESTCODE:
                    if (data != null) {
                        positionId = data.getIntExtra("positionId", 0);
                        String position = data.getStringExtra("position");
                        tvPosition.setText(position);
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            LogUtils.i("mbitmap===" + mBitmap);
            //保存裁剪图片
            saveBitmap(mBitmap);
            iv_header.setImageBitmap(mBitmap);//显示图片
            //上传头像到服务器
            getPresenter().upLoadHeader(mFile);
        } else {
            LogUtils.i("这里为空");
        }
    }

    /**
     * @param bitmap 需要保存的图片
     */
    private void saveBitmap(Bitmap bitmap) {
        FileOutputStream fout = null;
        try {
            if (mFile.exists()) {
                mFile.delete();
            }
            mFile.createNewFile();
            fout = new FileOutputStream(mFile);
            //将图片保存之后的质量
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
            fout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取用户信息，设置信息
    @Override
    public void getUserInfoSucc(UserInfoBean bean) {
        if (bean != null) {
            userinfo = bean.getUserinfo();
            if (userinfo.getSex() == 1) {
                tvSex.setText("男");
            } else {
                tvSex.setText("女");
            }
            url = userinfo.getPhoto();
            Glide.with(this).load(url).into(iv_header);
            tvNicheng.setText(userinfo.getNickname());
            tvId.setText(userinfo.getId() + "");
            etOfficePhone.setText(userinfo.getTel());
            etRoomNum.setText(userinfo.getRoomnum() + "");
            tvPhone.setText(userinfo.getMobile());
            tvPosition.setText(userinfo.getPosition());
            etBuildNum.setText(userinfo.getBuildnum());
        }
    }

    //修改用户信息结果
    @Override
    public void getChangeResult(ActivityPostBean bean) {
        if (bean != null) {
            if (bean.getErrCode() == 0) {
                showToast("修改信息成功");
                String nickName = tvNicheng.getText().toString().trim();
//                sexid, nickname, url, positionId, tel, roomNum, phone, buildNum
                SharedPrefHelper.getInstance().setPhoneNumber(phone);
                SharedPrefHelper.getInstance().setuserPhoto(url);
                SharedPrefHelper.getInstance().setNickname(nickName);
                if (sexid == 1) {
                    SharedPrefHelper.getInstance().setSex("男");
                } else {
                    SharedPrefHelper.getInstance().setSex("女");
                }
                //通知头像和昵称发生变化
                EventBus.getDefault().post(new ChangeUserInfo(url, nickName, tvPosition.getText().toString()));
            } else {
                showToast("修改信息失败");
            }
        }
    }

    //上传头像的结果处理
    @Override
    public void upLoadHeaderResult(UpLoadHeaderBean bean) {
        if (bean.getErrCode() == 0) {
            showToast("上传头像成功");
            url = bean.getUrl();
            //提交头像成功,缓存头像并保存
            File file = mFile.getAbsoluteFile();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            iv_header.setImageBitmap(bitmap);
            //通知头像和昵称发生变化
            EventBus.getDefault().post(new ChangeUserInfo(url, userinfo.getNickname(), userinfo.getPosition()));
        } else {
            showToast("上传头像失败了");
        }
    }
}
