package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.AddressManagerActivity;
import com.moe.wl.ui.main.adapter.OfficeSpDetailrvAdapter;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ShopCarInfoBean;
import com.moe.wl.ui.main.bean.SpDetailBean;
import com.moe.wl.ui.main.model.SpDetailModel;
import com.moe.wl.ui.main.modelimpl.SpDetailModelImpl;
import com.moe.wl.ui.main.presenter.SpDetailPresenter;
import com.moe.wl.ui.main.view.SpDetailView;
import com.moe.wl.ui.mywidget.NewShopCarDialog;
import com.moe.wl.ui.mywidget.ShopCarDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 商品详情页
 */
public class SpDetailActivity extends BaseActivity<SpDetailModel, SpDetailView, SpDetailPresenter> implements SpDetailView {

    private static final int ADDRESSREQUEST = 1;
    @BindView(R.id.sp_detail_title)
    TitleBar spDetailTitle;
    @BindView(R.id.sp_detail_rv)
    XRecyclerView spDetailRv;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.tv_add_car)
    TextView tvAddCar;
    @BindView(R.id.tv_now_buy)
    TextView tvNowBuy;
    @BindView(R.id.activity_sp_detail)
    LinearLayout activitySpDetail;

    private SliderLayout sib;
    //private SimpleImageBanner sib;

    private TextView tvSpCategory;
    private TextView tvSpPrice;
    private TextView tvAddress;
    private TextView tvComment;
    private TextView tvCommnetRate;
    private ImageView dot;
    public int position;
    private OfficeSpDetailrvAdapter detailrvAdapter;
    private int index;
    private ShopCarDialog shopCarDialog;
    private int mPositon;
    private List<ShopCarInfoBean.SkuListBean> skuList;
    private int mCount;
    private ShopCarInfoBean shopCarInfoBean;
    private boolean mIsAddShopCar;
    private LinearLayout address;
    private int id;
    private String mobile;
    private String name;
    private String addressName;
    private int id1;
    private boolean enableShow = false;

    @Override
    public SpDetailPresenter createPresenter() {
        return new SpDetailPresenter();
    }

    @Override
    public SpDetailModel createModel() {
        return new SpDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_sp_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        position = intent.getIntExtra("position", -1);
        intiTitle();
        intiRecycler();
        getPresenter().getSpDetail(id + "");
//        getPresenter().getShopCarInfo(id + "");
    }

    private void intiRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        spDetailRv.setLayoutManager(manager);
        //设置不容许刷新加载
        spDetailRv.setLoadingMoreEnabled(false);
        spDetailRv.setPullRefreshEnabled(false);
        View header = View.inflate(this, R.layout.sp_detail_header, null);
        sib = (SliderLayout) header.findViewById(R.id.slider_layout);
        //sib = (SimpleImageBanner) header.findViewById(R.id.slider_layout);
        tvSpCategory = (TextView) header.findViewById(R.id.tv_sp_category);
        tvSpPrice = (TextView) header.findViewById(R.id.tv_sp_price);
        address = (LinearLayout) header.findViewById(R.id.ll_address);
        tvAddress = (TextView) header.findViewById(R.id.tv_address);
        tvComment = (TextView) header.findViewById(R.id.tv_comment);
        tvCommnetRate = (TextView) header.findViewById(R.id.tv_comment_rate);
        LinearLayout more = (LinearLayout) header.findViewById(R.id.ll_more_comment);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpDetailActivity.this, SpAllCommentActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        //选择地址
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpDetailActivity.this, AddressManagerActivity.class);
                startActivityForResult(intent, ADDRESSREQUEST);
            }
        });
        spDetailRv.addHeaderView(header);
        detailrvAdapter = new OfficeSpDetailrvAdapter(this);
        spDetailRv.setAdapter(detailrvAdapter);
    }

    private void intiTitle() {
        spDetailTitle.setBack(true);
        spDetailTitle.setTitle("商品详情");
        spDetailTitle.setTitleRight("购物车");
        spDetailTitle.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpDetailActivity.this, ShopCarActivity.class);//进入购物车
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.ll_share, R.id.ll_collect, R.id.tv_add_car, R.id.tv_now_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_share:
                break;
            case R.id.ll_collect:
                getPresenter().getCollectInfo(2, id);
                break;
            case R.id.tv_add_car:
                if (enableShow)//获取购物车详情成功后才可以弹出购物车
                    showPop(true);
                break;
            case R.id.tv_now_buy:
                if (enableShow)
                    showPop(false);
                break;
        }
    }

    private void showPop(boolean isAddShopCar) {
        this.mIsAddShopCar = isAddShopCar;
        NewShopCarDialog newShopCarDialog = new NewShopCarDialog(this, R.style.dialog_style);
        newShopCarDialog.show();

        String testJson = "{\n" +
                "  \"msg\": \"success\",\n" +
                "  \"skuList\": [\n" +
                "    {\n" +
                "      \"cataName\": null,\n" +
                "      \"catainfolist\": [\n" +
                "        {\n" +
                "          \"cataid\": 2,\n" +
                "          \"content\": \"8G\",\n" +
                "          \"id\": 4,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": 76\n" +
                "        },\n" +
                "        {\n" +
                "          \"cataid\": 1,\n" +
                "          \"content\": \"red\",\n" +
                "          \"id\": 1,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": 76\n" +
                "        }\n" +
                "      ],\n" +
                "      \"createtime\": \"2017-11-21 19:03:49\",\n" +
                "      \"failedreason\": null,\n" +
                "      \"failedreasonids\": null,\n" +
                "      \"firstcataid\": 2,\n" +
                "      \"gg\": null,\n" +
                "      \"id\": 76,\n" +
                "      \"isMain\": 0,\n" +
                "      \"mainimg\": \"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201711211511262217898.jpg\",\n" +
                "      \"price\": 1.5,\n" +
                "      \"productid\": 52,\n" +
                "      \"secondcataid\": null,\n" +
                "      \"skuname\": \"得力胶棒（21G）/支\",\n" +
                "      \"status\": 1,\n" +
                "      \"store\": 199\n" +
                "    },\n" +
                "    {\n" +
                "      \"cataName\": null,\n" +
                "      \"catainfolist\": [\n" +
                "        {\n" +
                "          \"cataid\": 1,\n" +
                "          \"content\": \"yellow\",\n" +
                "          \"id\": 8,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": 77\n" +
                "        },\n" +
                "        {\n" +
                "          \"cataid\": 2,\n" +
                "          \"content\": \"8G\",\n" +
                "          \"id\": 7,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": 77\n" +
                "        }\n" +
                "      ],\n" +
                "      \"createtime\": \"2017-11-21 19:04:23\",\n" +
                "      \"failedreason\": null,\n" +
                "      \"failedreasonids\": null,\n" +
                "      \"firstcataid\": 2,\n" +
                "      \"gg\": null,\n" +
                "      \"id\": 77,\n" +
                "      \"isMain\": 0,\n" +
                "      \"mainimg\": \"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201711211511262217898.jpg\",\n" +
                "      \"price\": 3,\n" +
                "      \"productid\": 52,\n" +
                "      \"secondcataid\": null,\n" +
                "      \"skuname\": \"得力胶棒（36G）/支\",\n" +
                "      \"status\": 1,\n" +
                "      \"store\": 189\n" +
                "    }\n" +
                "  ],\n" +
                "  \"allskuinfolist\": [\n" +
                "    {\n" +
                "      \"cataid\": 1,\n" +
                "      \"cataname\": \"颜色分类\",\n" +
                "      \"infolist\": [\n" +
                "        {\n" +
                "          \"cataid\": 1,\n" +
                "          \"content\": \"red\",\n" +
                "          \"id\": null,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": null\n" +
                "        },\n" +
                "        {\n" +
                "          \"cataid\": 1,\n" +
                "          \"content\": \"yellow\",\n" +
                "          \"id\": null,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": null\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"cataid\": 2,\n" +
                "      \"cataname\": \"大小分类\",\n" +
                "      \"infolist\": [\n" +
                "        {\n" +
                "          \"cataid\": 2,\n" +
                "          \"content\": \"8G\",\n" +
                "          \"id\": null,\n" +
                "          \"productid\": 52,\n" +
                "          \"skuid\": null\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errCode\": 0\n" +
                "}";
        Gson gson = new Gson();
        ShopCarInfoBean shopCarInfo = gson.fromJson(testJson, ShopCarInfoBean.class);

        newShopCarDialog.setDatas(shopCarInfo);
        newShopCarDialog.setListener(new NewShopCarDialog.OnItemClickListener() {
            @Override
            public void onItemClickListener(int count, int id, int position) {
                mPositon = position;
                mCount = count;
                if (count > 0) {
                    LogUtils.i("id===" + id + "count==" + count);

                    if (mIsAddShopCar == true) {//是购物车
                        getPresenter().shopCar(id + "", count + "");
                    }
                    else {
                        Intent intent = new Intent(SpDetailActivity.this, OfficeSpConfirmOrderAct.class);
                        intent.putExtra("count", mCount);
                        intent.putExtra("from", "nowpay");
                        List<ShopCarInfoBean.SkuListBean> skuList = shopCarInfoBean.getSkuList();
                        ShopCarInfoBean.SkuListBean skuListBean = skuList.get(mPositon);
                        double price = skuListBean.getPrice();
                        intent.putExtra("price", price);
                        intent.putExtra("position", mPositon);
                        intent.putExtra("skuListBean", skuListBean);
                        //传递个人信息
                        intent.putExtra("Mobile", mobile);
                        intent.putExtra("ID", id1);
                        intent.putExtra("Name", name);
                        intent.putExtra("Address", addressName);
                        startActivity(intent);
                    }
                    shopCarDialog.dismiss();
                } else {
                    showToast("您还没有选择购买的商品");
                }
            }
        });
       /* shopCarDialog = new ShopCarDialog(this, R.style.dialog_style);
        shopCarDialog.show();
        if (shopCarInfoBean != null) {
            skuList = shopCarInfoBean.getSkuList();
            if (skuList != null && skuList.size() > 0) {
                shopCarDialog.setData(skuList);
                //点击加入到购物车
                shopCarDialog.setListener(new ShopCarDialog.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(int count, int id, int position) {
                        mPositon = position;
                        mCount = count;
                        if (count > 0) {
                            getPresenter().shopCar(id + "", count + "");
                            shopCarDialog.dismiss();
                        }else{
                            showToast("您还没有选择购买的商品");
                        }
                    }
                });
            }
        } else {
            LogUtils.i("bean 为null");
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        } else {
            if (requestCode == ADDRESSREQUEST) {
                if (data != null) {
                    /*  intent1.putExtra("ID", addressId);
                intent1.putExtra("Name", name);
                intent1.putExtra("Address", mAddress);
                intent1.putExtra("Mobile", phone);*/
                    id1 = data.getIntExtra("ID", 1);
                    mobile = data.getStringExtra("Mobile");
                    name = data.getStringExtra("Name");//姓名
                    addressName = data.getStringExtra("Address");
                    tvAddress.setText(addressName);
                }
            }
        }
    }

    @Override
    public void getSpDetailSucc(SpDetailBean bean) {
        getPresenter().getShopCarInfo(id + "");//获取购物车信息
        if (bean != null) {
            int favorNum = bean.getFavorNum();
            if (favorNum == 0) {
                ivCollect.setImageResource(R.drawable.collect);
            } else {
                ivCollect.setImageResource(R.drawable.collected);
            }
            SpDetailBean.ProductBean product = bean.getProduct();
            id = product.getId();
            tvComment.setText("评论(" + bean.getCommentTotal() + ")");
            tvCommnetRate.setText(bean.getRateGood() + "");
            //设置评论列表
            detailrvAdapter.setData(bean.getCommentList());
            if (product != null) {
                tvSpCategory.setText(product.getProductname());
                tvSpPrice.setText("￥" + product.getPrice());
                //tvAddress.setText("送至:"+product.get);
                //获取轮播图
                List<SpDetailBean.ProductBean.ImgEntityListBean> imgEntityList = product.getImgEntityList();
                //List<String> imgList = product.getImgList();
                if (imgEntityList.size() > 0) {
                    // TODO 轮播图数据
                    sib.removeAllSliders();
                    for (int i = 0; i < imgEntityList.size(); i++) {
                        TextSliderView textSliderView = new TextSliderView(getActivity());
                        textSliderView.description("").image(imgEntityList.get(i).getImg());
                        sib.addSlider(textSliderView);
                    }

                   /* ArrayList<BannerItem> list = new ArrayList<>();
                    for (int i = 0; i < imgEntityList.size(); i++) {
                        BannerItem item = new BannerItem();
                        item.imgUrl = imgEntityList.get(i).getImg();
                        LogUtil.log(item.imgUrl);
                        list.add(item);
                    }
                    sib
                            .setSource(list)
                            .startScroll();*/

                }
            }
        }
    }

    @Override
    public void getCollectResult(CollectBean bean) {
        if (bean.getStatus() == 0) {
            ivCollect.setImageResource(R.drawable.collect);
            showToast("取消收藏");
        } else {
            ivCollect.setImageResource(R.drawable.collected);
            showToast("收藏成功");
        }

    }


    @Override
    public void getShopCarInfo(ShopCarInfoBean bean) {
        this.enableShow = true;
        this.shopCarInfoBean = bean;

    }

    @Override
    public void getShopCar(ActivityPostBean bean) {
        if (bean != null) {
            //if (mIsAddShopCar == true) {//是购物车
                Intent intent = new Intent(SpDetailActivity.this, ShopCarActivity.class);
                startActivity(intent);
           // } else {
              /*  Intent intent = new Intent(this, OfficeSpConfirmOrderAct.class);
                intent.putExtra("count", mCount);
                intent.putExtra("from", "nowpay");
                List<ShopCarInfoBean.SkuListBean> skuList = shopCarInfoBean.getSkuList();
                ShopCarInfoBean.SkuListBean skuListBean = skuList.get(mPositon);
                double price = skuListBean.getPrice();
                intent.putExtra("price", price);
                intent.putExtra("position", mPositon);
                intent.putExtra("skuListBean", skuListBean);
                //传递个人信息
                intent.putExtra("Mobile", mobile);
                intent.putExtra("ID", id1);
                intent.putExtra("Name", name);
                intent.putExtra("Address", addressName);
                startActivity(intent);
            }*/

        } else {
            LogUtils.i(bean.getMsg());
        }
    }
}
