package com.moe.wl.ui.main.activity.property_maintenance;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.mywidget.StringListDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.adapter.WeixiuAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.WuyeHomeModel;
import com.moe.wl.ui.main.modelimpl.WuyeHomeModelImpl;
import com.moe.wl.ui.main.presenter.WuyeHomePresenter;
import com.moe.wl.ui.main.view.WuyeHomeView;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import mvp.cn.util.DateUtil;

public class PropertyAintenanceActivity extends BaseActivity<WuyeHomeModel, WuyeHomeView, WuyeHomePresenter> implements WuyeHomeView {

    private static final int ORDERTIMEREQUEST = 10;
    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.et_service_address)
    EditText etServiceAddress;
    @BindView(R.id.nsgv_service_type)
    NoSlidingGridView nsgvServiceType;
    @BindView(R.id.et_baoxiu_detail)
    EditText etBaoxiuDetail;
    @BindView(R.id.gv_addPhoto)
    NoSlidingGridView gvAddPhoto;
    @BindView(R.id.tv_now_posted)
    TextView tvNowPosted;
    @BindView(R.id.rl_time)
    LinearLayout rlTime;
    @BindView(R.id.tv_yuyue_time)
    TextView tvYuyueTime;
    private List<String> lists = Arrays.asList(
            "马桶疏通", "水电维修", "房屋维修",
            "开锁/换锁", "线路维修", "其他");
    private String mobile;
    private WeixiuAdapter weixiuAdapter;
    private int selectPosition;
    private String realName;
    private String address;
//    private ProertyAddPicAdapter picAdapter;
    private GridViewImageAdapter imageAdapter;
    private List<String> pics;
    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_complain.jpg";
    String imageUri;
    private ArrayList<String> paths;
    private StringListDialog dialog;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_property_aintenance);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        paths=new ArrayList<>();
        mobile = SharedPrefHelper.getInstance().getMobile();
        realName = SharedPrefHelper.getInstance().getRealName();
        tvName.setText(realName);
        etPhoneNum.setText(mobile);
        etPhoneNum.setSelection(mobile.length());
        initTitle();
        initGrid();
        pics = new ArrayList<>();
        pics.add("addPhoto");
        initAddPhotoGrid();
    }

    private void initAddPhotoGrid() {
        imageAdapter = new GridViewImageAdapter(this, pics, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                //打开
                dialog = new StringListDialog(PropertyAintenanceActivity.this, R.style.dialog_style);
                List<String> itemList = new ArrayList<>();
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
        });
        gvAddPhoto.setAdapter(imageAdapter);
    }

    private void initGrid() {
        weixiuAdapter = new WeixiuAdapter();
        nsgvServiceType.setAdapter(weixiuAdapter);
        weixiuAdapter.setDatas(lists);
        selectPosition = weixiuAdapter.getSelectPosition();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("物业维修");
    }

    @OnClick({R.id.rl_time, R.id.tv_now_posted})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_time:
                Intent intent = new Intent(PropertyAintenanceActivity.this, OrderTimeActivity.class);
                startActivityForResult(intent, ORDERTIMEREQUEST);
                break;
            case R.id.tv_now_posted:
                doPost();
                break;
        }
    }

    private void doPost() {
        String invitetime = tvYuyueTime.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            showToast("请输入电话号码");
            return;
        }
        String regExp = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile);
        if(!m.find()){
            showToast("您输入的手机号码有误！");
            return;
        }

        if (TextUtils.isEmpty(etServiceAddress.getText().toString().trim())) {
            showToast("地址不能为空");
            return;
        } else {
            address = etServiceAddress.getText().toString().trim();
        }
        String mendcontent = etBaoxiuDetail.getText().toString().trim();
        pics.remove(pics.size()-1);
        // 发布传入图片文件
         getPresenter().getData(selectPosition,realName,mobile,invitetime,address,mendcontent,pics);
    }

    @Override
    public void getWuyeHomeResult(ActivityPostBean wuyeHomeBean) {
        if (wuyeHomeBean.getErrCode() == 0) {
            Intent intent=new Intent(this,ProPerrtyPostSuccAct.class);
            startActivity(intent);
        }else{
            showToast("发布失败了");
        }
    }
    /**
     * 相机拍照
     */
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
    public void doSomething(){
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
    public void doFailSomething(){
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }
    /**
     * 相册选取
     */
    private void doPickPhotoFromGallery() {
        PermissionGen.with(this)
                .addRequestCode(200)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
    }
    @PermissionSuccess(requestCode = 200)
    public void getPhotoSucc(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }
    @PermissionFail(requestCode = 200)
    public void getPhotoFialed(){
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            LogUtils.d("requestCode : " + requestCode + "resultCode : " + resultCode);
        } else {
            switch (requestCode) {
                case ORDERTIMEREQUEST:
                    if (data != null) {
                        String time = data.getStringExtra("time");
                        tvYuyueTime.setText(time);
                    }
                    break;
                case TAKE_PHOTO_CAMERA:// 相机
                    if (null != imageUri) {
                        pics.add(0, imageUri);
                        imageAdapter.notifyDataSetChanged();
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
                        pics.add(0, path);
                        imageAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
    @Override
    public WuyeHomePresenter createPresenter() {
        return new WuyeHomePresenter();
    }

    @Override
    public WuyeHomeModel createModel() {
        return new WuyeHomeModelImpl();
    }

}
