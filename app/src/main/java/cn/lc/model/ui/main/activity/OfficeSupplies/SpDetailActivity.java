package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.SimpleImageBanner;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.framework.widget.bean.BannerItem;
import cn.lc.model.ui.main.activity.ordering.AddressManagerActivity;
import cn.lc.model.ui.main.adapter.OfficeSpDetailrvAdapter;
import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ShopCarInfoBean;
import cn.lc.model.ui.main.bean.SpDetailBean;
import cn.lc.model.ui.main.model.SpDetailModel;
import cn.lc.model.ui.main.modelimpl.SpDetailModelImpl;
import cn.lc.model.ui.main.presenter.SpDetailPresenter;
import cn.lc.model.ui.main.view.SpDetailView;
import cn.lc.model.ui.mywidget.ShopCarDialog;
import mvp.cn.util.LogUtil;
import mvp.cn.util.ToastUtil;


public class SpDetailActivity extends BaseActivity<SpDetailModel, SpDetailView, SpDetailPresenter> implements SpDetailView {

    private static final int ADDRESSREQUEST = 1;
    @BindView(R.id.sp_detail_title)
    TitleBar spDetailTitle;
    @BindView(R.id.sp_detail_rv)
    XRecyclerView spDetailRv;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_add_car)
    TextView tvAddCar;
    @BindView(R.id.tv_now_buy)
    TextView tvNowBuy;
    @BindView(R.id.activity_sp_detail)
    LinearLayout activitySpDetail;
    private SimpleImageBanner sib;
    private TextView tvSpCategory;
    private TextView tvSpPrice;
    private TextView tvAddress;
    private TextView tvComment;
    private TextView tvCommnetRate;
    private ImageView dot;
    public int position;
    private OfficeSpDetailrvAdapter detailrvAdapter;
    private int id;
    private int index;
    private ShopCarDialog shopCarDialog;
    private int mPositon;
    private List<ShopCarInfoBean.SkuListBean> skuList;
    private int mCount;
    private ShopCarInfoBean shopCarInfoBean;
    private boolean mIsAddShopCar;

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
        getPresenter().getShopCarInfo(id + "");
    }

    private void intiRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        spDetailRv.setLayoutManager(manager);
        //设置不容许刷新加载
        spDetailRv.setLoadingMoreEnabled(false);
        spDetailRv.setPullRefreshEnabled(false);
        View header = View.inflate(this, R.layout.sp_detail_header, null);
        sib = (SimpleImageBanner) header.findViewById(R.id.h_banner_viewPager);
        tvSpCategory = (TextView) header.findViewById(R.id.tv_sp_category);
        tvSpPrice = (TextView) header.findViewById(R.id.tv_sp_price);
        tvAddress = (TextView) header.findViewById(R.id.tv_address);
        tvComment = (TextView) header.findViewById(R.id.tv_comment);
        tvCommnetRate = (TextView) header.findViewById(R.id.tv_comment_rate);
        dot = (ImageView) header.findViewById(R.id.iv_three_dot);
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
        dot.setOnClickListener(new View.OnClickListener() {
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
    }

    @OnClick({R.id.tv_share, R.id.tv_collect, R.id.tv_add_car, R.id.tv_now_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                break;
            case R.id.tv_collect:
                getPresenter().getCollectInfo(11, id);
                break;
            case R.id.tv_add_car:
                showPop(true);
                break;
            case R.id.tv_now_buy:
                showPop(false);
                break;
        }
    }

    private void showPop(boolean isAddShopCar) {
        this.mIsAddShopCar = isAddShopCar;
        shopCarDialog = new ShopCarDialog(this, R.style.dialog_style);
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
                        }
                    }
                });
            }
        } else {
            LogUtils.i("bean 为null");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        } else {
            if (requestCode == ADDRESSREQUEST) {
                if (data != null) {
                    String addressName = data.getStringExtra("Name");
                    tvAddress.setText(addressName);
                }
            }
        }
    }

    @Override
    public void getSpDetailSucc(SpDetailBean bean) {
        if (bean != null) {
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
                List<?> imgList = product.getImgList();
                if (imgList.size() > 0) {
                    ArrayList<BannerItem> list = new ArrayList<>();
                    for (int i = 0; i < imgList.size(); i++) {
                        BannerItem item = new BannerItem();
                        item.imgUrl = (String) imgList.get(i);
                        LogUtil.log(item.imgUrl);
                        list.add(item);
                    }
                    sib
                            .setSource(list)
                            .startScroll();
                }
                sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtil.showToast(getActivity(), "position--->" + position);

                    }
                });
            }
        }
    }

    @Override
    public void getCollectResult(CollectBean bean) {
        if (bean != null) {
            if (bean.getStatus() == 1) {
                showToast("收藏");
            } else if (bean.getStatus() == 0) {
                showToast("取消收藏");
            } else {
                LogUtils.i("没有收藏成功");
            }
        }
    }

    @Override
    public void getShopCarInfo(ShopCarInfoBean bean) {
        this.shopCarInfoBean = bean;

    }

    @Override
    public void getShopCar(ActivityPostBean bean) {
        if (bean != null) {
            if (mIsAddShopCar == true) {
                Intent intent = new Intent(SpDetailActivity.this, ShopCarActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, OfficeSpConfirmOrderAct.class);
                intent.putExtra("count", mCount);
                intent.putExtra("from","nowpay");
                List<ShopCarInfoBean.SkuListBean> skuList = shopCarInfoBean.getSkuList();
                ShopCarInfoBean.SkuListBean skuListBean = skuList.get(mPositon);
                int price = skuListBean.getPrice();
                intent.putExtra("price",price);
                intent.putExtra("position", mPositon);
                intent.putExtra("skuListBean",skuListBean);
                startActivity(intent);
            }

        } else {
            LogUtils.i(bean.getMsg());
        }
    }
}
