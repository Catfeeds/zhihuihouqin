package com.moe.wl.ui.home.activity.office;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.home.adapter.office.AffirmEquipmentAdapter;
import com.moe.wl.ui.home.model.office.SubscribeInfoModel;
import com.moe.wl.ui.home.modelimpl.office.SubscribeInfoModelImpl;
import com.moe.wl.ui.home.presenter.office.SubscribeInfoPresenter;
import com.moe.wl.ui.home.view.office.SubscribeInfoView;
import com.moe.wl.ui.main.adapter.ActivityPostMulitPicAdapter;
import com.moe.wl.ui.mywidget.BottomTimeDialog;
import com.moe.wl.ui.mywidget.StringListDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import mvp.cn.util.DateUtil;

/**
 * 填写预订信息
 */
public class SubscribeInfoActivity extends BaseActivity<SubscribeInfoModel, SubscribeInfoView, SubscribeInfoPresenter> implements View.OnClickListener, SubscribeInfoView {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.lv_equipment)
    NoSlidingGridView lvEquipment;
    @BindView(R.id.sp_type)
    Spinner spType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_leader)
    EditText etLeader;
    @BindView(R.id.tv_enclosure)
    TextView tvEnclosure;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.gv_pic)
    GridView gvPic;
    @BindView(R.id.tv_finish)
    TextView tvFinish;

    private StringListDialog dialog;

    private AffirmEquipmentAdapter adapter;
    private List<String> mList;

    private List<String> spinnerList;
    private ArrayAdapter spinnerAdapter;

    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    private String imageUri;
    private boolean isOne;
    private ArrayList<String> paths;
    private ArrayList<String> pics;

    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_complain.jpg";

    private ActivityPostMulitPicAdapter picAdapter;

    private BottomTimeDialog startDialog;
    private BottomTimeDialog endDialog;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mList = new ArrayList<>();

        adapter = new AffirmEquipmentAdapter(this);
        adapter.setItemList(mList);
        lvEquipment.setAdapter(adapter);

        for (int i = 0; i < 6; i++) {
            mList.add("投影仪" + i);
        }
        adapter.notifyDataSetChanged();

        spinnerList = new ArrayList<String>();
        spinnerList.add("北京");
        spinnerList.add("上海");
        spinnerList.add("广州");
        spinnerList.add("深圳");

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(spinnerAdapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //单张图片的集合
        paths = new ArrayList<>();
        //多张图片的集合
        pics = new ArrayList<>();

        initGrlid();
    }

    private void initGrlid() {
        picAdapter = new ActivityPostMulitPicAdapter(this);
        gvPic.setAdapter(picAdapter);
        picAdapter.setData(pics);
        picAdapter.setListener(new ActivityPostMulitPicAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick(int position) {
                if (position == pics.size() - 1) {
                    isOne = false;
                    showButtomDialog();
                }
            }
        });
    }

    @Override
    public SubscribeInfoPresenter createPresenter() {
        return new SubscribeInfoPresenter();
    }

    @Override
    public SubscribeInfoModel createModel() {
        return new SubscribeInfoModelImpl();
    }

    @OnClick({R.id.ll_back, R.id.rl_time, R.id.ll_type, R.id.tv_enclosure, R.id.tv_finish})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_time:  //预约时间
                startActivity(new Intent(this,SubscribeTimeActivity.class));
                break;
            case R.id.ll_type:  //会议类型

                break;
            case R.id.tv_enclosure:  //附件上传
                showButtomDialog();
                break;
            case R.id.tv_finish:
                getPresenter().subscribeInfo();
                break;
        }
    }

    @Override
    public void submit() {
        startActivity(new Intent(this, AffirmOrderActivity.class));
        finish();
    }

    private void showButtomDialog() {
        if (dialog == null) {
            dialog = new StringListDialog(this, R.style.dialog_style);
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
        }
        dialog.show();
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
    public void getPhotoSucc() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }

    @PermissionFail(requestCode = 200)
    public void getPhotoFialed() {
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            LogUtils.d("requestCode : " + requestCode + "resultCode : " + resultCode);
        } else {
            switch (requestCode) {
                case TAKE_PHOTO_CAMERA:// 相机
                    if (null != imageUri) {
                        if (isOne) {
                            paths.clear();
                            paths.add(imageUri);
                         /*   GlideLoading.getInstance().loadImgUrlNyImgLoader(this,imageUri,addPhoto);*/
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
                          /*  GlideLoading.getInstance().loadImgUrlNyImgLoader(this,path,addPhoto);*/
                        } else {
                            pics.add(0, path);
                            picAdapter.setData(pics);
                        }
                    }
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
