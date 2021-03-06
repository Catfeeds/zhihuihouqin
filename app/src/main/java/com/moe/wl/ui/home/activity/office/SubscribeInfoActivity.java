package com.moe.wl.ui.home.activity.office;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.Imglibrary.ImageSelector;
import com.moe.wl.ui.home.adapter.office.AffirmEquipmentAdapter;
import com.moe.wl.ui.home.bean.office.AppointmentDateBean;
import com.moe.wl.ui.home.bean.office.EquipmentListBean;
import com.moe.wl.ui.home.bean.office.SubscribeInfoResponse;
import com.moe.wl.ui.home.bean.office.TypeListBean;
import com.moe.wl.ui.home.model.office.SubscribeInfoModel;
import com.moe.wl.ui.home.modelimpl.office.SubscribeInfoModelImpl;
import com.moe.wl.ui.home.presenter.office.SubscribeInfoPresenter;
import com.moe.wl.ui.home.view.office.ConferenceTypePop;
import com.moe.wl.ui.home.view.office.SubscribeInfoView;
import com.moe.wl.ui.main.adapter.ActivityPostMulitPicAdapter;
import com.moe.wl.ui.main.adapter.GridViewImageAdapter;
import com.moe.wl.ui.mywidget.StringListDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import mvp.cn.util.ToastUtil;

/**
 * 填写预订信息
 */
public class SubscribeInfoActivity extends BaseActivity<SubscribeInfoModel, SubscribeInfoView, SubscribeInfoPresenter> implements View.OnClickListener, SubscribeInfoView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.lv_equipment)
    NoSlidingGridView lvEquipment;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_leader)
    EditText etLeader;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R.id.rv_equipment)
    NoSlidingListView rvEquipment;
    @BindView(R.id.rv_service_needs)
    NoSlidingListView rvServiceNeeds;
    @BindView(R.id.tv_enclosure)
    TextView tvEnclosure;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.gv_pic)
    GridView gvPic;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.grid_view)
    NoSlidingGridView gridView;

    private StringListDialog dialog;

    private AffirmEquipmentAdapter adapterOne, adapterTwo;
    private List<EquipmentListBean> listOne;
    private List<EquipmentListBean> listTwo;

    private static final int TAKE_PHOTO_CAMERA = 10001;
    private static final int TAKE_PHOTO_ALBUM = 10002;
    private static final int TAKE_TIME = 10003;
    private String imageUri;
    private boolean isOne;
    private ArrayList<String> paths;
    private ArrayList<String> pics;

    private String imageLocation = Environment.getExternalStorageDirectory().getPath() + "/file/";
    private String imageName = "_complain.jpg";

    private ActivityPostMulitPicAdapter picAdapter;

    private String id;
    private String equipmentids;  //设备ids，（逗号分隔）

    private List<TypeListBean> typeList;
    private ConferenceTypePop pw;
    private TypeListBean conferencetype;  //会议类型1文艺类型

    private List<AppointmentDateBean> dates;  //预约的时间

    private GridViewImageAdapter imageAdapter;
    private int personNum;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("预订信息");
        id = getIntent().getStringExtra("id");
        personNum = getIntent().getIntExtra("personNum",0);

        //单张图片的集合
        paths = new ArrayList<>();
        //多张图片的集合
        pics = new ArrayList<>();
        imageAdapter = new GridViewImageAdapter(SubscribeInfoActivity.this, pics, null);
        gridView.setAdapter(imageAdapter);

        initGrlid();
        initEquipment();
        typeList = new ArrayList<>();
        if (!TextUtils.isEmpty(id)) {
            getPresenter().subscribeInfo(id);
        }

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()) && Integer.parseInt(s.toString())>personNum){
                    ToastUtil.showToast(SubscribeInfoActivity.this,"参会人数不能超过会议室容纳人数");
                    etNumber.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 初始化会场设备
     */
    public void initEquipment() {
        listOne = new ArrayList<>();
        adapterOne = new AffirmEquipmentAdapter(this);
        adapterOne.setItemList(listOne);
        rvEquipment.setAdapter(adapterOne);
        adapterOne.setOnMinusClickListener(new AffirmEquipmentAdapter.OnMinusClickListener() {
            @Override
            public void onMinusClick(int pos, int count) {
                listOne.get(pos).setCheck(count > 0);
                listOne.get(pos).setCount(count);
                adapterOne.notifyDataSetChanged();
            }
        });
        adapterOne.setOnAddClickListener(new AffirmEquipmentAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(int pos, int count) {
                listOne.get(pos).setCheck(count > 0);
                listOne.get(pos).setCount(count);
                adapterOne.notifyDataSetChanged();
            }
        });

        listTwo = new ArrayList<>();
        adapterTwo = new AffirmEquipmentAdapter(this);
        adapterTwo.setItemList(listTwo);
        rvServiceNeeds.setAdapter(adapterTwo);
        adapterTwo.setOnMinusClickListener(new AffirmEquipmentAdapter.OnMinusClickListener() {
            @Override
            public void onMinusClick(int pos, int count) {
                listTwo.get(pos).setCheck(count > 0);
                listTwo.get(pos).setCount(count);
                adapterTwo.notifyDataSetChanged();
            }
        });
        adapterTwo.setOnAddClickListener(new AffirmEquipmentAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(int pos, int count) {
                listTwo.get(pos).setCheck(count > 0);
                listTwo.get(pos).setCount(count);
                adapterTwo.notifyDataSetChanged();
            }
        });
        /*adapterOne.setMyCallBack(new AffirmEquipmentAdapter.MyCallBack() {
            @Override
            public void cb(int pos) {
                if (listOne.get(pos).isCheck()) {
                    listOne.get(pos).setCheck(false);
                } else {
                    listOne.get(pos).setCheck(true);
                }
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < listOne.size(); i++) {
                    if (listOne.get(pos).isCheck()) {
                        if (buffer.length() > 0) {
                            buffer.append(",");
                        }
                        buffer.append(listOne.get(pos).getId());
                    }
                }
                equipmentids = buffer.toString();
                adapterOne.notifyDataSetChanged();
            }
        });*/
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

    @OnClick({R.id.rl_time, R.id.ll_type, R.id.tv_enclosure, R.id.tv_finish})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_time:  //预约时间
                Intent intent1 = new Intent(this, SubscribeTimeActivity.class);
                intent1.putExtra("id",id);
                startActivityForResult(intent1, TAKE_TIME);
                break;
            case R.id.ll_type:  //会议类型
                int[] location = new int[2];
                llType.getLocationOnScreen(location);
                pw.showPopupWindow(llType, location[1]);
                break;
            case R.id.tv_enclosure:  //附件上传
                showButtomDialog();
                break;
            case R.id.tv_finish:
                if (conferencetype == null) {
                    showToast("请选择会议类型");
                    return;
                }
                String conferencename = etName.getText().toString();
                if (TextUtils.isEmpty(conferencename)) {
                    showToast("请输入会议名称");
                    return;
                }
                String attendnum = etNumber.getText().toString();
                if (TextUtils.isEmpty(attendnum)) {
                    showToast("请输入参会人数");
                    return;
                }
                String attentdleader = etLeader.getText().toString();
                if (TextUtils.isEmpty(attentdleader)) {
                    showToast("请输入参会领导");
                    return;
                }
                String remark = etRemark.getText().toString();
                if (dates == null || dates.size() == 0) {
                    showToast("请选择预约时间");
                    return;
                }
                Intent intent = new Intent(this, AffirmOrderActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("equipmentids", equipmentids);
                intent.putExtra("conferencetype", conferencetype);
                intent.putExtra("conferencename", conferencename);
                intent.putExtra("attendnum", attendnum);
                intent.putExtra("attentdleader", attentdleader);
                if (!TextUtils.isEmpty(remark)) {
                    intent.putExtra("remark", remark);
                }
                intent.putExtra("list", (Serializable) dates);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void setData(SubscribeInfoResponse mResponse) {
        if (mResponse.getEquipmentList() != null) {
            listOne.addAll(mResponse.getEquipmentList());
            adapterOne.notifyDataSetChanged();
        }
        if (mResponse.getServiceList() != null) {
            listTwo.addAll(mResponse.getServiceList());
            adapterTwo.notifyDataSetChanged();
        }
        if (mResponse.getTypeList() != null) {
            typeList.addAll(mResponse.getTypeList());
            if (typeList != null && typeList.size() > 0) {
                if (pw == null) {
                    pw = new ConferenceTypePop(SubscribeInfoActivity.this, new ConferenceTypePop.MyOnClick() {
                        @Override
                        public void click(TypeListBean bean) {
                            conferencetype = bean;
                            tvType.setText(conferencetype.getTypename());
                        }
                    });
                    pw.setData(typeList);
                }
            }
            if (typeList.size() > 0) {
                conferencetype = typeList.get(0);
                typeList.get(0).setCheck(true);
                tvType.setText(typeList.get(0).getTypename());
            }
        }
    }

    private void showButtomDialog() {
//        if (dialog == null) {
//            dialog = new StringListDialog(this, R.style.dialog_style);
//            List<String> itemList = new ArrayList<String>();
//            itemList.add("相机拍摄");
//            itemList.add("手机相册");
//            itemList.add("取消");
//            dialog.setData(itemList);
//            dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    switch (position) {
//                        case 0:// 拍照上传
                            doTakePhoto();
//                            dialog.dismiss();
//                            break;
//                        case 1:// 从gallery选择
//                            doPickPhotoFromGallery();
//                            dialog.dismiss();
//                            break;
//                        case 2:// 取消
//                            dialog.dismiss();
//                            break;
//                    }
//                }
//            });
//        }
//        dialog.show();
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
        ImageSelector selector = ImageSelector.create();
        selector.single();  // single mode
        selector.multi();  // multi mode, default mode;
        selector.origin(pics) // original select data set, used width #.multi()
                .showCamera(true)   // show camera or not. true by default
                .count(6)   // max select image size, 9 by default. used width #.multi()
                .spanCount(3)  // image span count ，default is 3.
                .start(this, TAKE_PHOTO_CAMERA);
        /*imageUri = imageLocation + DateUtil.yyyyMMdd_HHmmss.format(new Date()) + imageName;
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
        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();*/
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        Toast.makeText(this, "未获得权限许可", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_TIME) {
            if (data != null) {
                if (dates == null) {
                    dates = new ArrayList<>();
                }
                dates = (List<AppointmentDateBean>) data.getSerializableExtra("list");
                String times = "";
                for (int i = 0; i < dates.size(); i++) {
                    times += dates.get(i).getDate()+" ";
                    for (int j = 0; j < dates.get(i).getTimes().size(); j++) {
                        times += dates.get(i).getTimes().get(j).getDurationstr()+" ";
                    }
                    if (i != dates.size()-1){
                        times += "\n";
                    }
                }
                tvTime.setText(times);
            }
        } else {
            if (resultCode != RESULT_OK) {
                LogUtils.d("requestCode : " + requestCode + "resultCode : " + resultCode);
            } else {
                switch (requestCode) {
                    case TAKE_PHOTO_CAMERA:// 相机
                        pics.clear();
                        pics.addAll(data.getStringArrayListExtra(ImageSelector.EXTRA_RESULT));
                        imageAdapter.notifyDataSetChanged();
                        /*if (null != imageUri) {
                            if (isOne) {
                                paths.clear();
                                paths.add(imageUri);
                         *//*   GlideLoading.getInstance().loadImgUrlNyImgLoader(this,imageUri,addPhoto);*//*
                            } else {
                                pics.add(0, imageUri);
                                picAdapter.setData(pics);
                            }
                        }*/
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
    }
}
