package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ShopCarInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ScreenUtils;
import mvp.cn.util.ToastUtil;

import static android.R.id.list;

public class NewShopCarDialog extends Dialog {


    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.price)
    TextView tvprice;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    /*@BindView(R.id.tv_color_category)
    TextView tvColorCategory;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.view1)
    View view1;*/
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.minus)
    ImageView minus;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_add)
    ImageView tvAdd;
    @BindView(R.id.rl_count)
    RelativeLayout rlCount;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.rl2)
    LinearLayout rl2;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_select_cat)
    TextView tvSelectCategory;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private Context ct;
    private int count;
    private List<ShopCarInfoBean.SkuListBean> itemList;
    private int cid;
    private int position;
    private int selectPosition;
    private ShopCarInfoBean.SkuListBean skuListBean;
    private List<Integer> categoryList;
    private int cataId;
    //private List<ShopCarInfoBean.AllskuinfolistBean.InfolistBeanX> infolist;


    public NewShopCarDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View contentView = View.inflate(ct, R.layout.dialog_shop_car_new, null);
        ButterKnife.bind(this, contentView);
        int width = ScreenUtils.getScreenWidth(ct);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, -2);
        contentView.setLayoutParams(params);
        setContentView(contentView, params);

        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(p);
        getWindow().setWindowAnimations(R.style.AnimationDialog);
        categoryList=new ArrayList<>();

    }

    // TODO: 2017/11/28 0028 设置购物车信息
    public void setDatas(ShopCarInfoBean shopCarInfoBean) {
        LogUtils.i("=================" + shopCarInfoBean.toString());
        if (shopCarInfoBean != null) {
            //库存列表,用于列表查询
            List<ShopCarInfoBean.SkuListBean> skuList = shopCarInfoBean.getSkuList();

            //所有库存列表
            List<ShopCarInfoBean.AllskuinfolistBean> allskuinfolist = shopCarInfoBean.getAllskuinfolist();
            //初始化第一次进入展示信息
            if (skuList.size() > 0) {
                GlideLoading.getInstance().loadImgUrlNyImgLoader(ct,
                        skuList.get(0).getMainimg(), ivPic);
                tvprice.setText("￥" + skuList.get(0).getPrice());
                tvKucun.setText("库存" + skuList.get(0).getStore() + "件");
                //分类描述初始设置
                List<ShopCarInfoBean.SkuListBean.CatainfolistBean> catainfolist = skuList.get(0).getCatainfolist();
                String str = "";
                for (int i = 0; i < catainfolist.size(); i++) {
                    str += catainfolist.get(i).getContent();
                    tvSelectCategory.setText(str);
                }
            }
            LogUtils.i("------------" + allskuinfolist.size());
            for (int i = 0; i < allskuinfolist.size(); i++) {
                ShopCarInfoBean.AllskuinfolistBean allskuinfolistBean = allskuinfolist.get(i);
                if (allskuinfolistBean != null) {
                    //类别条目
                    View view = LayoutInflater.from(ct).inflate(R.layout.office_category, llContainer, false);
                    int width = ScreenUtils.getScreenWidth(ct);
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width, -2);
                    view.setLayoutParams(param);
                    llContainer.addView(view);
                    TextView tvCategoryName = (TextView) view.findViewById(R.id.tv_color_category);
                    final FlowLayout flowLayout = (FlowLayout) view.findViewById(R.id.flow_layout);
                    //获得类别名称
                    String cataName = allskuinfolistBean.getCataname();
                    //设置类别名称
                    tvCategoryName.setText(cataName);
                    //获得子分类集合
                    final List<ShopCarInfoBean.AllskuinfolistBean.InfolistBean> infolist = allskuinfolistBean.getInfolist();
                    LogUtils.i("子分类集合的数量++" + infolist.size());
                    for (int j = 0; j < infolist.size(); j++) {
                        //获得类别id
                        int cataid = infolist.get(j).getCataid();
                        if(this.cataId==cataid){
                            continue;
                        }else{//类别不同
                            categoryList.add(cataId);
                        }
                        TextView textView = new TextView(ct);
                        LogUtils.i("子分类集合的索引" + i + "描述内容===" + infolist.get(j).getContent());
                        textView.setText(infolist.get(j).getContent());
                        textView.setTextColor(Color.parseColor("#333333"));
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(14);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(DensityUtil.dip2px(ct, 5), DensityUtil.dip2px(ct, 5), DensityUtil.dip2px(ct, 5), DensityUtil.dip2px(ct, 5));
                        textView.setBackgroundResource(R.drawable.shape_gray_circle_light);
                        textView.setPadding(DensityUtil.dip2px(ct, 6), DensityUtil.dip2px(ct, 4), DensityUtil.dip2px(ct, 6), DensityUtil.dip2px(ct, 4));
                        flowLayout.addView(textView, params);

                    }

                    //处理子条目的点击事件
                    for (int j = 0; j < flowLayout.getChildCount(); j++) {
                        final int index = j;
                        this.selectPosition = index;
                        flowLayout.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                count = 0;
                                //String s = ((TextView) flowLayout.getChildAt(index)).getText().toString();
                                /*skuListBean =*/
                                ShopCarInfoBean.AllskuinfolistBean.InfolistBean infolistBean = infolist.get(index);
                                //类别的id
                                int id = infolistBean.getCataid();
                                //描述信息
                                String content = infolistBean.getContent();
                                cid = id;


                                /*GlideLoading.getInstance().loadImgUrlNyImgLoader(ct,
                                        skuListBean.getMainimg(), ivPic);
                                LogUtils.i("skuListBean.getPrice()===" + skuListBean.getPrice());
                                tvprice.setText("￥" + skuListBean.getPrice());
                                tvKucun.setText("库存" + skuListBean.getStore() + "件");*/


                                int in = index;
                                position = in;
                                for (int i = 0; i < flowLayout.getChildCount(); i++) {
                                    if (i == in) {
                                        ((TextView) flowLayout.getChildAt(i)).setBackgroundResource(R.drawable.shape_blue_circle_confirm);
                                        ((TextView) flowLayout.getChildAt(i)).setTextColor(Color.parseColor("#ffffff"));
                                    } else {
                                        ((TextView) flowLayout.getChildAt(i)).setBackgroundResource(R.drawable.shape_gray_circle_light);
                                        ((TextView) flowLayout.getChildAt(i)).setTextColor(Color.parseColor("#333333"));
                                    }
                                }
                            }
                        });

                    }
                }
            }
        } else {
            LogUtils.i("shopCarInfoBean==null");
        }

    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @OnClick({R.id.iv_close, R.id.minus, R.id.tv_add, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.minus:
                doMinus();
                break;
            case R.id.tv_add:
                doAdd();
                break;
            case R.id.tv_confirm:
                if (listener != null) {
                    listener.onItemClickListener(count, cid, position);
                }
                break;
        }
    }

    private void doAdd() {
        if (skuListBean == null) {
            ToastUtil.showToast(ct, "请选择颜色分类");
            return;
        }
        int store = skuListBean.getStore();
        if (store > count)//判断库存数量
            count++;
        tvSum.setText(count + "");
    }

    private void doMinus() {
        if (count <= 0) {
            count = 0;
        } else {
            count--;
        }
        tvSum.setText(count + "");
    }

    public interface OnItemClickListener {
        void onItemClickListener(int count, int id, int position);
    }

    private boolean isPopShowing = false;

}
