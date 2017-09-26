package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberProductAdapter;
import com.moe.wl.ui.main.adapter.DoctorDetailrvAdapter;
import com.moe.wl.ui.main.bean.BarberDetailBean;
import com.moe.wl.ui.main.bean.BarberListBean;
import com.moe.wl.ui.main.bean.CommentlistBean;
import com.moe.wl.ui.main.model.BarberDetailModel;
import com.moe.wl.ui.main.modelimpl.BarberDetailModelImpl;
import com.moe.wl.ui.main.presenter.BarberDetailPresenter;
import com.moe.wl.ui.main.view.BarberDetailView;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import mvp.cn.util.CallPhoneUtils;


public class BarberDetailActivity extends BaseActivity<BarberDetailModel, BarberDetailView, BarberDetailPresenter> implements BarberDetailView {

    private static final int CALL_PHONE_REQUEST_CODE = 10;
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
    TextView zixun;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.activity_barber_detail)
    LinearLayout activityBarberDetail;
    private String address;
    private BarberListBean.BarberlistBean barberlistBean;
    private DoctorDetailrvAdapter rvAdapter;
    private List<CommentlistBean> data;
    private String shopName;
    private BarberProductAdapter barberProductAdapter;
    private BarberDetailBean detailBean;

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
        barberlistBean = (BarberListBean.BarberlistBean) extras.getSerializable("barberlistBean");
        System.out.println("理发师详情" + barberlistBean);
        if (barberlistBean != null) {
            getPresenter().getData(barberlistBean.getId());

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
            barberProductAdapter.setData(detailBean.getWorklist());
            data.addAll(detailBean.getCommentlist());
            rvAdapter.notifyDataSetChanged();
//            rvAdapter.setData(detailBean.getCommentlist());
        }
        if (barberlistBean != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, barberlistBean.getPhoto(), civBarberHeaderPhoto);
            tvBarberName.setText(barberlistBean.getName());
            tvChenghao.setText("号称:" + barberlistBean.getPositionName());
            tvBarberShop.setText(shopName);
            tvBarberAddress.setText("地址:" + address);
            tvBarberPhone.setText("电话:" + barberlistBean.getMobile());


        }
    }

    private void initRecycler() {
        data = new ArrayList<>();
        nsrvUserComment.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new DoctorDetailrvAdapter(this, data);
        nsrvUserComment.setAdapter(rvAdapter);
    }

    private void initGrid() {
        barberProductAdapter = new BarberProductAdapter(this);
        nsgvZuopin.setAdapter(barberProductAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发型师详情");
    }

    @OnClick({R.id.tv_barber_jieshao_content, R.id.iv_call_barber_phone, R.id.tv_more_zuopin, R.id.tv_more_comment, R.id.iv_collect, R.id.zixun, R.id.tv_now_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_barber_jieshao_content:
                Intent intent2 = new Intent(this, BarberJianjieActivity.class);
                String content = tvBarberJieshaoContent.getText().toString().trim();
                intent2.putExtra("content", content);
                startActivity(intent2);
                break;
            case R.id.iv_call_barber_phone:
                CallPhoneUtils.call(ivCallBarberPhone, barberlistBean.getMobile(), this);
                break;
            case R.id.tv_more_zuopin:
                Intent intent = new Intent(this, BarberMoreProductActivity.class);
                //intent.putExtra("detailBean", detailBean);
                if(detailBean!=null){
                    int id = detailBean.getWorklist().get(1).getId();
                    intent.putExtra("id",id);
                }
                startActivity(intent);
                break;
            case R.id.tv_more_comment:
                Intent intent1 = new Intent(this, MoreUSerCommentActivity.class);
                // TODO: 2017/8/22 0022 需要传递bean
//                intent1.putExtra()
                startActivity(intent1);
                break;
            case R.id.iv_collect:
                break;
            case R.id.zixun:
                break;
            case R.id.tv_now_order:
                break;
        }
    }
}

