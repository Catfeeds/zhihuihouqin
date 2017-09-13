package cn.lc.model.ui.main.activity.complain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.GridViewImageAdapter;
import cn.lc.model.ui.main.adapter.LabellingAdapter;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.LabellingBean;
import cn.lc.model.ui.main.model.SubmitComplainModel;
import cn.lc.model.ui.main.modelimpl.SubmitComplainModelImpl;
import cn.lc.model.ui.main.presenter.SubmitComplainPresenter;
import cn.lc.model.ui.main.view.SubmitComplainView;
import cn.lc.model.ui.mywidget.AddPhotoPop;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;

import static cn.lc.model.R.id.is_anonymity;

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


    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/wisdowLogistics/";
    private String imageName = "complainImage.jpg";
    Uri imageUri = Uri.parse(imageLocation + DateUtil.getCurrentDateTimeyyyyMMddHHmmss() + imageName);//The Uri to store the big bitmap
    Uri takePhoto;

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
                    cropImageUri(takePhoto);
                    break;

                case TAKE_PHOTO_ALBUM:// 相册
                    if (data != null) {
                        cropImageUri(data.getData());
                    }
                    break;

                case CROP_PHOTO:// 裁剪
                    LogUtils.d("image地址：" + imageUri.toString());
                    if (imageUri != null) {
                        paths.add(0, imageUri.toString());
                        imageAdapter.notifyDataSetChanged();
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

    /**
     * 相机拍照
     */
    private void takePhotoCamera() {
        pop.dismiss();
        File file = new File(imageLocation);
        if (!file.exists()) {
            file.mkdir();
        }
        takePhoto = Uri.parse(imageLocation + DateUtil.getCurrentDateTimeyyyyMMddHHmmss() + imageName);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra(MediaStore.EXTRA_OUTPUT, takePhoto);
        startActivityForResult(intent, TAKE_PHOTO_CAMERA);
    }

    /**
     * 相册选取
     */
    private void takePhotoAlbum() {
        pop.dismiss();

        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }

    private void cropImageUri(Uri uri) {
        LogUtils.d("uri :" + uri.toString() + "  imageUri:" + imageUri.toString());
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

}
