package com.moe.wl.ui.main.activity.complain;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.main.adapter.LabellingAdapter;
import com.moe.wl.ui.main.bean.LabellingBean;
import com.suke.widget.SwitchButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.SubmitComplainModel;
import com.moe.wl.ui.main.modelimpl.SubmitComplainModelImpl;
import com.moe.wl.ui.main.presenter.SubmitComplainPresenter;
import com.moe.wl.ui.main.view.SubmitComplainView;
import com.moe.wl.ui.mywidget.AddPhotoPop;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;

import static com.moe.wl.R.id.is_anonymity;

/**
 * 类描述：意见反馈主页
 * 作者：Shixhe On 2017/9/6 0006
 */

public class SubmitComplainActivity extends BaseActivity<SubmitComplainModel, SubmitComplainView, SubmitComplainPresenter> implements SubmitComplainView {

    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    private static final int CROP_PHOTO = 10003;

    @BindView(R.id.top_bar)
    TitleBar titleBar;
    @BindView(R.id.et_content)
    EditText content;
    @BindView(R.id.grid_view)
    NoSlidingGridView gridView;
    @BindView(R.id.complain_history)
    TextView comPlainHistory;
    @BindView(R.id.suggest)
    EditText suggest;
    @BindView(is_anonymity)
    SwitchButton anonymity;
    @BindView(R.id.labelling)
    NoSlidingGridView labelling_gridView;

    private LabellingAdapter adapter;

    private LabellingBean labellingBean;
    private int labellingID = 0;

    private ArrayList<String> paths;
    private GridViewImageAdapter imageAdapter;
    private AddPhotoPop pop;


    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_complain.jpg";
    String imageUri;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("意见投诉");
        paths = new ArrayList<>();
        paths.add("addPhoto");
        imageAdapter = new GridViewImageAdapter(this, paths, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                pop = new AddPhotoPop(SubmitComplainActivity.this, click);
                pop.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
            }
        });
        gridView.setAdapter(imageAdapter);
        getPresenter().getLabelling();
        labelling_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < labellingBean.getTagList().size(); i++) {
                    labellingBean.getTagList().get(i).setSelect(false);
                }
                labellingBean.getTagList().get(position).setSelect(true);
                labellingID = labellingBean.getTagList().get(position).getId();
                adapter.notifyDataSetChanged();
            }
        });

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.camera:
                    takePhotoCamera();
                    break;

                case R.id.album:
                    takePhotoAlbum();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            LogUtils.d("requestCode : " + requestCode + "resultCode : " + resultCode);
        } else {
            switch (requestCode) {
                case TAKE_PHOTO_CAMERA:// 相机
//                    cropImageUri(takePhoto);
                    if (null != imageUri) {
                        paths.add(0, imageUri);
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
                        paths.add(0, path);
                        imageAdapter.notifyDataSetChanged();
//                        cropImageUri(data.getData());
                    }
                    break;

                case CROP_PHOTO:// 裁剪
                    if (data == null) {
                        return;
                    }
                    break;
            }
        }
    }

    @Override
    public void submitComplainSucc(CollectBean bean) {
        startActivity(new Intent(this, SubmitComplainSuccessActivity.class));
        finish();
    }

    @OnClick({R.id.complain_history, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complain_history:
                startActivity(new Intent(SubmitComplainActivity.this, ComplainHistoryActivity.class));
                break;

            case R.id.submit:
                submitComplain();
                break;

        }
    }

    /**
     * 相机拍照
     */
    private void takePhotoCamera() {
        pop.dismiss();
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
    }

    /**
     * 相册选取
     */
    private void takePhotoAlbum() {
        pop.dismiss();
//        imageUri = Uri.parse(imageLocation + DateUtil.yyyyMMdd_HHmmss.format(new Date()) + imageName);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }

    // 裁图
    private void cropImageUri(Uri uri) {
        imageUri = imageLocation + DateUtil.yyyyMMdd_HHmmss.format(new Date()) + imageName;
        LogUtils.d("uri :" + uri.toString() + "  imageUri:" + imageUri);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", "outputX");
        intent.putExtra("outputY", "outputX");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, CROP_PHOTO);
    }

    private void submitComplain() {

        if (content.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(this, "请输入投诉内容");
            return;
        }

        if (suggest.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(this, "请输入您的建议");
            return;
        }

        if (labellingID == 0) {
            ToastUtil.showToast(this, "请选择标签");
            return;
        }
//        int isAnonymity = anonymity.isChecked() ? 1 : 0;
        for (int i = 0; i < paths.size(); i++) {
            LogUtils.d("图片路径是：" + paths.get(i));
        }
        getPresenter().submitComplainSucc(labellingID, content.getText().toString(),
                suggest.getText().toString(), anonymity.isChecked() ? 1 : 0, paths);

    }

    @Override
    public void getLabelling(LabellingBean bean) {
        labellingBean = bean;
        if (adapter == null) {
            adapter = new LabellingAdapter(this, bean);
            labelling_gridView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public SubmitComplainModel createModel() {
        return new SubmitComplainModelImpl();
    }

    @Override
    public SubmitComplainPresenter createPresenter() {
        return new SubmitComplainPresenter();
    }

}
