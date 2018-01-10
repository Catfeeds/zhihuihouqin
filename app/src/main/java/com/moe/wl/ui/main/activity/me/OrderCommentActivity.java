package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.Imglibrary.ImageSelector;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.property_maintenance.PropertyAintenanceActivity;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.mywidget.AddPhotoPop;
import com.moe.wl.ui.mywidget.StarBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：订单评论页面
 * 作者：Shixhe On 2017/10/9 0009
 */

public class OrderCommentActivity extends MyBaseActivity {

    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    private static final int CROP_PHOTO = 10003;
    private static final int REQUEST_IMAGE=1;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rating_bar)
    StarBar ratingBar;
    @BindView(R.id.appraise)
    TextView appraise;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.grid_view)
    NoSlidingGridView gridView;
    @BindView(R.id.radio_button)
    CheckBox radioButton;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.main)
    RelativeLayout main;
    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.rating_one)
    StarBar ratingOne;
    @BindView(R.id.text_two)
    TextView textTwo;
    @BindView(R.id.rating_two)
    StarBar ratingTwo;

    private GridViewImageAdapter adapter;
    private ArrayList<String> paths;

    private AddPhotoPop pop;

    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_orderComment.jpg";
    String imageUri;

    private CustomerDialog progressDialog;
    private int orderID;
    private int from;
    private GridViewImageAdapter imageAdapter;
    private int maxNum=5;
    private ArrayList<String> mSelectPath;
    private ArrayList<String> pics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);
        initData();
    }
    private void initAddPhotoGrid() {
        imageAdapter = new GridViewImageAdapter(this, pics, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                ImageSelector selector = ImageSelector.create();
                // selector.single();  // single mode
                selector.multi();  // multi mode, default mode;
                selector.origin(mSelectPath) // original select data set, used width #.multi()
                        .showCamera(true)   // show camera or not. true by default
                        .count(maxNum)   // max select image size, 9 by default. used width #.multi()
                        .spanCount(3)  // image span count ，default is 3.
                        .start(OrderCommentActivity.this, REQUEST_IMAGE);

            }
        });
        gridView.setAdapter(imageAdapter);
    }
    private void initData() {
        //对星星进行设置
        ratingOne.ismove(true);
        ratingOne.setStarMark(5);
        ratingOne.setIntegerMark(true);
        ratingTwo.ismove(true);
        ratingTwo.setStarMark(5);
        ratingTwo.setIntegerMark(true);
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        titleBar.setBack(true);
        titleBar.setTitle("评论");
        orderID = getIntent().getIntExtra("OrderID", 0);
        from = getIntent().getIntExtra("ServiceType", 0);
        LogUtils.d("orderID : " + orderID + "  from : " + from);
        switch (from) {
            case Constants.PROPERRY:
                name.setText("维修人员");
                textOne.setText("维修速度");
                textTwo.setText("服务评价");
                break;

            case Constants.MEDICAL:
                name.setText("医疗");
                textOne.setText("医术评价");
                textTwo.setText("服务评价");
                break;

            case Constants.BOOK:
                name.setText("图书馆");
                textOne.setText("图书评价");
                textTwo.setText("服务评价");
                break;

            case Constants.HAIRCUTS:
                name.setText("发型师");
                textOne.setText("技术评价");
                textTwo.setText("服务态度");
                break;

            case Constants.CONFERENCE:
                name.setText("会议室");
                textOne.setText("会场质量");
                textTwo.setText("服务评价");
                break;

            case Constants.OFFICESUPPLIES:
                name.setText("超市");
                textOne.setText("产品质量");
                textTwo.setText("服务评价");
                break;

            case Constants.VEGETABLE:
                name.setText("净菜");
                textOne.setText("菜品评价");
                textTwo.setText("服务评价");
                break;

            case Constants.EXPERTS:
                name.setText("专家");
                textOne.setText("医术评价");
                textTwo.setText("服务评价");
                break;

            case Constants.ORDERMEAL:
                name.setText("机关餐厅");
                textOne.setText("菜品评价");
                textTwo.setText("服务评价");
                break;

            case Constants.DRYCLEANER:
                name.setText("洗衣店");
                textOne.setText("干洗质量");
                textTwo.setText("服务评价");
                break;

            case Constants.ORDERWATER:
                name.setText("水站");
                textOne.setText("配送速度");
                textTwo.setText("服务评价");
                break;

            case Constants.VISITORS:
                name.setText("安保处");
                textOne.setText("安保质量");
                textTwo.setText("服务评价");
                break;

        }
        paths = new ArrayList<>();
        paths.add("addComment");

        pics = new ArrayList<>();
        pics.add("addPhoto");
        initAddPhotoGrid();
      /*  adapter = new GridViewImageAdapter(this, paths, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                pop = new AddPhotoPop(OrderCommentActivity.this, click);
                pop.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
            }
        });*/

        //对评分星星监听，评分取整，可以滑动
        ratingBar.setIntegerMark(true);
        ratingBar.setStarMark(5);
        ratingBar.ismove(true);
        ratingBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                switch ((int) mark) {
                    case 0:
                        appraise.setText("0分");
                        break;
                    case 1:
                        appraise.setText("1分");
                        break;
                    case 2:
                        appraise.setText("2分");
                        break;
                    case 3:
                        appraise.setText("3分");
                        break;
                    case 4:
                        appraise.setText("4分");
                        break;
                    case 5:
                        appraise.setText("5分");
                        break;
                }
            }
        });

        //gridView.setAdapter(adapter);
    }

    @OnClick({R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                if (ratingBar.getStarMark() == 0 || ratingOne.getStarMark() == 0 || ratingTwo.getStarMark() == 0) {
                    Toast.makeText(this, "评分不能为0！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (paths.size()<=0){
                    return;
                }
                paths.remove(paths.size() - 1);
                Observable observable = RetrofitUtils.getInstance().commentOrder(orderID, ratingBar.getStarMark(),
                        ratingOne.getStarMark(), ratingTwo.getStarMark(),
                        etContent.getText().toString(), radioButton.isChecked() ? 1 : 0, from, paths);
                progressDialog.show();
                observable.subscribe(new Subscriber<CollectBean>() {
                    @Override
                    public void onCompleted() {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onNext(CollectBean orderBean) {
                        if (orderBean.getErrCode() == 0) {
                            Intent intent = new Intent(OrderCommentActivity.this, OrderCommentSuccessActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtil.showToast(OrderCommentActivity.this, orderBean.getMsg());
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            LogUtils.d("requestCode : " + requestCode + "resultCode : " + resultCode);
        } else {
           /* switch (requestCode) {
                case TAKE_PHOTO_CAMERA:// 相机
                    if (null != imageUri) {
                        paths.add(0, imageUri);
                        adapter.notifyDataSetChanged();
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
                        adapter.notifyDataSetChanged();
                    }
                    break;

                case CROP_PHOTO:// 裁剪
                    if (data == null)
                        return;
                    break;
            }*/
            if (requestCode == REQUEST_IMAGE) {
                if (resultCode == RESULT_OK) {
                    mSelectPath = data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT);
                    // data  ..
                    pics.clear();
                    pics.add("addPhoto");
                    pics.addAll(0,mSelectPath);
                    LogUtils.i("mSelectPath=="+mSelectPath.size()+"======="+mSelectPath.get(0));
                    imageAdapter.notifyDataSetChanged();
                }
            }
        }
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
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }

}
