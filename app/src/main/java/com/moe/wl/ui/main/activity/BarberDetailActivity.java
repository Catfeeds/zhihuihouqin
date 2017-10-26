package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberProductAdapter;
import com.moe.wl.ui.main.adapter.DoctorDetailrvAdapter;
import com.moe.wl.ui.main.bean.BarberDetailBean;
import com.moe.wl.ui.main.bean.BarberlistBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.CommentlistBean;
import com.moe.wl.ui.main.model.BarberDetailModel;
import com.moe.wl.ui.main.modelimpl.BarberDetailModelImpl;
import com.moe.wl.ui.main.presenter.BarberDetailPresenter;
import com.moe.wl.ui.main.view.BarberDetailView;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.CallPhoneUtils;


public class BarberDetailActivity extends BaseActivity<BarberDetailModel, BarberDetailView, BarberDetailPresenter> implements BarberDetailView {

    private static final int CALL_PHONE_REQUEST_CODE = 10;
    private static final int Type=7;
    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.civ_barber_header_photo)
    CircleImageView civBarberHeaderPhoto;
    @BindView(R.id.tv_barber_name)
    TextView tvBarberName;
    @BindView(R.id.tv_chenghao)
    TextView tvChenghao;
    @BindView(R.id.tv_barber_shop)
    TextView tvBarberShop;
    @BindView(R.id.tv_barber_address)
    TextView tvBarberAddress;
    @BindView(R.id.tv_barber_phone)
    TextView tvBarberPhone;
    @BindView(R.id.iv_call_barber_phone)
    ImageView ivCallBarberPhone;
    @BindView(R.id.tv_barber_jieshao)
    TextView tvBarberJieshao;
    @BindView(R.id.tv_barber_jieshao_content)
    TextView tvBarberJieshaoContent;
    @BindView(R.id.tv_zuopin_num)
    TextView tvZuopinNum;
    @BindView(R.id.tv_more_zuopin)
    TextView tvMoreZuopin;
    @BindView(R.id.nsgv_zuopin)
    NoSlidingGridView nsgvZuopin;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.tv_more_comment)
    TextView tvMoreComment;
    @BindView(R.id.nsrv_user_comment)
    NoSlideRecyclerView nsrvUserComment;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.zixun)
    LinearLayout zixun;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.activity_barber_detail)
    LinearLayout activityBarberDetail;
    @BindView(R.id.ll_call_phone)
    LinearLayout llCallPhone;
    private String address;
    private BarberlistBean barberlistBean;
    private DoctorDetailrvAdapter rvAdapter;
    private List<CommentlistBean> data;
    private String shopName;
    private BarberProductAdapter barberProductAdapter;
    private BarberDetailBean detailBean;
    private int id;

    @Override
    public BarberDetailPresenter createPresenter() {
        return new BarberDetailPresenter();
    }

    @Override
    public BarberDetailModel createModel() {
        return new BarberDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_barber_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        address = extras.getString("address");
        shopName = extras.getString("shopName");
        barberlistBean = (BarberlistBean) extras.getSerializable("barberlistBean");
        id = barberlistBean.getId();
        System.out.println("理发师详情" + barberlistBean);
        if (barberlistBean != null) {
            getPresenter().getData(barberlistBean.getId());//获得理发师详情
        }

        initTitle();
        initGrid();
        initRecycler();
    }

    @Override
    public void getBarberDetailSucc(BarberDetailBean detailBean) {
        this.detailBean = detailBean;
        System.out.println("理发师详情" + barberlistBean);
        if (detailBean != null) {
            tvBarberJieshaoContent.setText(detailBean.getBrief());
            tvZuopinNum.setText("作品(" + detailBean.getWorktotalcount() + ")");
            /*tvZuopinNum.setText("作品(" + detailBean.getWorklist().size()+ ")");*/
            tvCommentNum.setText("用户评价("+detailBean.getCommentlist().size()+")");
            barberProductAdapter.setData(detailBean.getWorklist());//设置作品列表
            LogUtils.i("worklist===" + detailBean.getWorklist().size());
            data.addAll(detailBean.getCommentlist());
            rvAdapter.notifyDataSetChanged();
            rvAdapter.setData(detailBean.getCommentlist());
            //初始化收藏状太
            if(detailBean.getFavorstatus()==0){
                ivCollect.setImageResource(R.drawable.collect);
            }else if(detailBean.getFavorstatus()==1){
                ivCollect.setImageResource(R.drawable.collected);
            }
        }
        if (barberlistBean != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, barberlistBean.getPhoto(), civBarberHeaderPhoto);
            tvBarberName.setText(barberlistBean.getName());
            tvChenghao.setText("号称: " + barberlistBean.getPositionName());
            tvBarberShop.setText(shopName);
            tvBarberAddress.setText("地址: " + address);
            tvBarberPhone.setText("电话: " + barberlistBean.getMobile());
        }
    }

    @Override
    public void collectSucc(CollectBean listBean) {
        LogUtils.i("CollectBean=="+listBean.getStatus());
        if(listBean.getStatus()==0){//
            ivCollect.setImageResource(R.drawable.collect);
            showToast("取消收藏");
        }else{
            ivCollect.setImageResource(R.drawable.collected);
            showToast("收藏成功");
        }
    }

    private void initRecycler() {
        data = new ArrayList<>();
        nsrvUserComment.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new DoctorDetailrvAdapter(this, data);
        nsrvUserComment.setAdapter(rvAdapter);
    }

    //详情作品列表
    private void initGrid() {
        barberProductAdapter = new BarberProductAdapter(this);
        nsgvZuopin.setAdapter(barberProductAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发型师详情");
    }

    @OnClick({R.id.tv_barber_jieshao_content, R.id.ll_call_phone, R.id.tv_more_zuopin, R.id.tv_more_comment, R.id.iv_collect, R.id.zixun, R.id.tv_now_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_barber_jieshao_content://介绍
                Intent intent2 = new Intent(this, BarberJianjieActivity.class);
                String content = tvBarberJieshaoContent.getText().toString().trim();
                intent2.putExtra("content", content);
                startActivity(intent2);
                break;
            case R.id.iv_call_barber_phone:
                CallPhoneUtils.callPhone(barberlistBean.getMobile(), this);
                break;
            case R.id.tv_more_zuopin:
                Intent intent = new Intent(this, BarberMoreProductActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.tv_more_comment:
                Intent intent1 = new Intent(this, MoreUSerCommentActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
                break;
            case R.id.iv_collect://收藏
                getPresenter().collect(Type,id);
                break;
            case R.id.zixun://咨询
                Intent intent3 = new Intent(this, ConsultActivity.class);
                intent3.putExtra("barberid", id);
                startActivity(intent3);
                break;
            case R.id.tv_now_order:
                Intent intent4=new Intent(this,ReservaBarberActivity.class);
                intent4.putExtra("barberlistBean",barberlistBean);
                intent4.putExtra("address",address);
                startActivity(intent4);
                break;
        }
    }
}

