package com.moe.wl.ui.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.bean.ShopCarInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ScreenUtils;

public class ShopCarDialog extends Dialog {


    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_color_category)
    TextView tvColorCategory;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.minus)
    TextView minus;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_add)
    TextView tvAdd;
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
    private Context ct;
    private int count;
    private List<ShopCarInfoBean.SkuListBean> itemList;
    private int cid;
    private int position;


    public ShopCarDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    private void initView() {
        View contentView = View.inflate(ct, R.layout.dialog_shop_car, null);
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

    }
    public void setData(final List<ShopCarInfoBean.SkuListBean> itemList) {
        this.itemList = itemList;
        for (int i = 0; i < itemList.size(); i++) {
            final TextView textView =  new TextView(ct);
            textView.setText(itemList.get(i).getSkuname());
            textView.setTextColor(Color.parseColor("#333333"));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setBackgroundColor(Color.parseColor("#F6F5F5"));
            textView.setPadding(DensityUtil.dip2px(ct, 8), DensityUtil.dip2px(ct, 8), DensityUtil.dip2px(ct, 8), DensityUtil.dip2px(ct, 8));
            flowLayout.addView(textView);
            flowLayout.getChildAt(0).performClick();
        }

        for (int j = 0; j < flowLayout.getChildCount(); j++) {
            final int index=j;
            flowLayout.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = ((TextView) flowLayout.getChildAt(index)).getText().toString();
                    ShopCarInfoBean.SkuListBean skuListBean = itemList.get(index);
                    int id = skuListBean.getId();//库存id
                    cid = id;
                    GlideLoading.getInstance().loadImgUrlNyImgLoader(ct,
                            skuListBean.getMainimg(),ivPic);
                    price.setText("￥"+skuListBean.getPrice());
                    tvKucun.setText("库存"+skuListBean.getStore()+"件");
                   int in=index;
                    position=in;
                    for (int i = 0; i < flowLayout.getChildCount(); i++) {
                        if(i==in){
                            ((TextView) flowLayout.getChildAt(i)).setBackgroundColor(Color.parseColor("#FF5111"));
                            ((TextView) flowLayout.getChildAt(i)).setTextColor(Color.parseColor("#ffffff"));
                        }else{
                            ((TextView) flowLayout.getChildAt(i)).setBackgroundColor(Color.parseColor("#F6F5F5"));
                            ((TextView) flowLayout.getChildAt(i)).setTextColor(Color.parseColor("#333333"));
                        }
                    }
                }
            });
        }
        flowLayout.getChildAt(0).performClick();

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
             if(listener!=null){
                 listener.onItemClickListener(count,cid,position);
             }
                dismiss();
                break;
        }
    }

    private void doAdd() {
         count++;
        tvSum.setText(count+"");
    }

    private void doMinus() {
        if(count<=0){
            count=0;
        }else{
            count--;
        }
        tvSum.setText(count+"");
    }

    public interface OnItemClickListener {
        void onItemClickListener(int count, int id, int position);
    }

    private boolean isPopShowing = false;

}
