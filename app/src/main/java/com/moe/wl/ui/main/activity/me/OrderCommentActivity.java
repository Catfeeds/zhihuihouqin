package com.moe.wl.ui.main.activity.me;

import android.support.v7.app.AppCompatActivity;

/**
 * 类描述：订单评论页面 （报修、订水、医疗、专家、干洗、）
 * 作者：Shixhe On 2017/10/9 0009
 */

public class OrderCommentActivity extends AppCompatActivity {

   /* private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    private static final int CROP_PHOTO = 10003;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.appraise)
    TextView appraise;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.grid_view)
    NoSlidingGridView gridView;
    @BindView(R.id.radio_button)
    CheckBox radioButton;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.main)
    RelativeLayout main;

    private GridViewImageAdapter adapter;
    private List<String> paths;

    private AddPhotoPop pop;

    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_orderComment.jpg";
    String imageUri;

    private CustomerDialog progressDialog;
    private int orderID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        titleBar.setBack(true);
        titleBar.setTitle("评论");
        orderID = getIntent().getIntExtra("OrderID", 0);
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        paths = new ArrayList<>();
        paths.add("addPhoto");
        adapter = new GridViewImageAdapter(this, paths, new GridViewImageAdapter.OnAddPhotoClickListener() {
            @Override
            public void onClick() {
                pop = new AddPhotoPop(OrderCommentActivity.this, click);
                pop.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                switch ((int) rating) {
                    case 0:
                        appraise.setText("糟糕");
                        break;
                    case 1:
                        appraise.setText("很不满意");
                        break;
                    case 2:
                        appraise.setText("不满意");
                        break;
                    case 3:
                        appraise.setText("一般");
                        break;
                    case 4:
                        appraise.setText("满意");
                        break;
                    case 5:
                        appraise.setText("很满意");
                        break;
                }
            }
        });

        gridView.setAdapter(adapter);
    }

    @OnClick({R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                Observable observable = RetrofitUtils.getInstance().commentOrder(orderID, etContent.getText().toString(),
                        radioButton.isChecked() ? 1 : 0, paths);
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
                            Intent intent = new Intent(OrderCommentActivity.this, OrderCommentSuccActivity.class);
                            startActivity(intent);
                            finish();
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
            switch (requestCode) {
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

    *//**
     * 相机拍照
     *//*
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

    *//**
     * 相册选取
     *//*
    private void takePhotoAlbum() {
        pop.dismiss();
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
        startActivityForResult(intent, TAKE_PHOTO_ALBUM);
    }*/

}
