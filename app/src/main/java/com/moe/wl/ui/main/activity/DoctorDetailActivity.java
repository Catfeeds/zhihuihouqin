package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.ui.main.view.DoctorDetailView;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.adapter.DoctorDetailGrideAdapter;
import com.moe.wl.ui.main.adapter.DoctorDetailrvAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.CommentlistBean;
import com.moe.wl.ui.main.bean.DoctorDetailBean;
import com.moe.wl.ui.main.bean.DoctorListBean;
import com.moe.wl.ui.main.bean.UserCommentBean;
import com.moe.wl.ui.main.model.DoctorDetailModel;
import com.moe.wl.ui.main.modelimpl.DoctorDetailModelImpl;
import com.moe.wl.ui.main.presenter.DoctorDetailPresenter;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

public class DoctorDetailActivity extends BaseActivity<DoctorDetailModel, DoctorDetailView, DoctorDetailPresenter> implements DoctorDetailView {

    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_doctor_position)
    TextView tvDoctorPosition;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.tv_seeing)
    TextView tvSeeing;//问诊量
    @BindView(R.id.tv_doctor_specialty)
    TextView tvDoctorSpecialty;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.iv_doc_photo)
    ImageView ivDocPhoto;
    @BindView(R.id.iv_doc_detail_back)
    ImageView back;
    @BindView(R.id.nsgv_doc_detail)
    NoSlidingGridView nsgvDocDetail;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_content_recomment)
    TextView tvContentRecomment;
    @BindView(R.id.nsrlv_doc_tetail)
    NoSlideRecyclerView recyclerView;
    @BindView(R.id.iv_doc_detial_collect)
    ImageView ivDocDetialCollect;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.tv_check_all)
    TextView tvCheckAll;
    private DoctorListBean.DoctorlistBean doctorDetailBean;
    private DoctorDetailGrideAdapter grideAdapter;
    private DoctorDetailrvAdapter detailrvAdapter;
    private List<CommentlistBean> data;


    @Override
    public DoctorDetailPresenter createPresenter() {
        return new DoctorDetailPresenter();
    }

    @Override
    public DoctorDetailModel createModel() {
        return new DoctorDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        doctorDetailBean = (DoctorListBean.DoctorlistBean) getIntent().getSerializableExtra("listBean");
        setDoctorDetail(doctorDetailBean);
        getPresenter().getData(doctorDetailBean.getDoctorid());
        // TODO: 2017/9/6 0006 获取评论列表有问题
        //getPresenter().getUserComment(doctorDetailBean.getDoctorid(),1,10);
        switchButtonClick();
        switchButton.setChecked(true);
        //ratingBar.setRating(3f);
        initGride();
        initRecycler();

    }

    @Override
    public void getDoctorDetailSucc(DoctorDetailBean listBean) {
        if (listBean != null) {
            tvContentRecomment.setText(listBean.getBrief());
            Log.e("totalcommentcount",listBean.getTotalcommentcount()+"====");
            tvCheckAll.setText("查看全部（"+listBean.getTotalcommentcount()+"）");
        }

    }

    @Override
    public void getUserCommentListSucc(UserCommentBean userCommentBean) {
        if(userCommentBean!=null){
            data.addAll(userCommentBean.getCommentlist());
            detailrvAdapter.notifyDataSetChanged();
//            detailrvAdapter.setData(userCommentBean.getCommentlist());
        }else{
            Log.e("userCommentBean","为空了");
        }
    }

    @Override
    public void getCollectResult(CollectBean collectBean) {
        if(collectBean!=null){
            // TODO: 2017/9/6 0006  根据返回装填修改图标
            int status = collectBean.getStatus();
            if(status==1){//收藏
                //ivDocDetialCollect.setImageResource();
            }else{
               // ivDocDetialCollect.setImageResource();

            }
        }
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailrvAdapter = new DoctorDetailrvAdapter(this, data);
        recyclerView.setAdapter(detailrvAdapter);
    }

    private void initGride() {
        grideAdapter = new DoctorDetailGrideAdapter(this);
        nsgvDocDetail.setAdapter(grideAdapter);
    }

    private void switchButtonClick() {
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
            }
        });
    }


    @OnClick({R.id.iv_doc_detail_back, R.id.iv_doc_detial_collect, R.id.tv_now_order, R.id.tv_check_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_doc_detail_back:
                finish();
                break;
            case R.id.iv_doc_detial_collect:
//                getPresenter().
                break;
            case R.id.tv_check_all:
                Intent intent1 = new Intent(this, MoreUSerCommentActivity.class);
                intent1.putExtra("id",doctorDetailBean.getDoctorid());
                startActivity(intent1);
                break;
            case R.id.tv_now_order:
                Intent intent = new Intent(this, PayFiveJiaoActivity.class);
                intent.putExtra("from", Constants.MEDICAL);
                startActivity(intent);
                break;
        }
    }


    public void setDoctorDetail(DoctorListBean.DoctorlistBean doctorDetail) {
        if (doctorDetail != null) {
            tvDoctorName.setText(doctorDetail.getRealname());
            tvDoctorPosition.setText(doctorDetail.getPositionname());
            // TODO: 2017/9/5 0005 没有星星的字段
            //ratingBar.setRating(listBean.get);
            tvSeeing.setText(doctorDetail.getConsultcount() + "");
            tvHospital.setText(doctorDetail.getHospitalname());
            tvDoctorSpecialty.setText(doctorDetail.getSkilledinfo());
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, doctorDetail.getPhoto(), ivDocPhoto);
        }
    }
}
