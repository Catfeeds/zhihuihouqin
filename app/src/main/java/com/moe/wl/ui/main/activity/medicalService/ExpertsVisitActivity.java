package com.moe.wl.ui.main.activity.medicalService;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.ActivityRegistration.ActivityRegistrationActivity;
import com.moe.wl.ui.main.activity.MoreUSerCommentActivity;
import com.moe.wl.ui.main.activity.ReserveInfoActivity;
import com.moe.wl.ui.main.adapter.DoctorDetailrvAdapter;
import com.moe.wl.ui.main.adapter.ExpertSelectTimeDayAdapter;
import com.moe.wl.ui.main.adapter.ExpertTimeAdapter;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.CommentlistBean;
import com.moe.wl.ui.main.bean.ExpertDetailBean;
import com.moe.wl.ui.main.model.ExpertDetailModel;
import com.moe.wl.ui.main.modelimpl.ExpertDetailModelImpl;
import com.moe.wl.ui.main.presenter.ExpertDetailPresenter;
import com.moe.wl.ui.main.view.ExpertDetailView;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.ShowHintDialog;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 专家详情页面
 */

public class ExpertsVisitActivity extends BaseActivity<ExpertDetailModel, ExpertDetailView, ExpertDetailPresenter> implements ExpertDetailView {

    @BindView(R.id.tv_now_order)
    Button tvNowOrder; // 立即预约
    @BindView(R.id.iv_doc_detail_back)
    ImageView ivDocDetailBack;
    @BindView(R.id.iv_doc_photo)
    ImageView ivDocPhoto;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_doctor_position)
    TextView tvDoctorPosition;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.tv_seeing)
    TextView tvSeeing;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_order_person_num)
    TextView tvOrderPersonNum;
    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.skilledinfo)
    TextView skilledInfo;
    /*@BindView(R.id.grid_view)
    NoSlidingGridView gridView;*/
    @BindView(R.id.recycleView1)
    RecyclerView recycleView1;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.rv_time)
    RecyclerView rvTime;
    @BindView(R.id.hint)
    ImageView hint;

    private List<CommentlistBean> data;
    private DoctorDetailrvAdapter adapter;
    private ExpertSelectTimeDayAdapter dayAdapter;

    private int doctorID;
    private String doctorName;
    private String doctorImage;

    private ExpertDetailBean.ExpertEntity entity;
    private List<ExpertDetailBean.SchedulesEntity> timeDayData;
    private List<ExpertDetailBean.SchedulesEntity.SchedulelistEntity> timeData;

    private int timeID = 0;
    private String time;
    private ExpertTimeAdapter timeAdapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_experts_visit);
    }

    @Override
    public void initView() {
        entity = new ExpertDetailBean.ExpertEntity();
        timeDayData = new ArrayList<>();
        timeData = new ArrayList<>();

        dayAdapter = new ExpertSelectTimeDayAdapter(this, timeDayData, new ExpertSelectTimeDayAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                timeData.clear();
                timeData.addAll(timeDayData.get(position).getSchedulelist());
                timeAdapter.notifyDataSetChanged();
            }
        });

        recycleView1.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));
        recycleView1.setAdapter(dayAdapter);

        rvTime.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        timeAdapter = new ExpertTimeAdapter(this, timeData, new ExpertTimeAdapter.OnClickListener() {
            @Override
            public void onClick(int id, String timeString) {
                timeID = id;
                time = timeString;
            }
        });
       /* this.timeAdapter = new ExpertSelectTimeAdapter(this, timeData, new ExpertSelectTimeAdapter.OnClickListener() {
            @Override
            public void onClick(int id, String timeString) {
                timeID = id;
                time = timeString;
            }
        });*/
        rvTime.setAdapter(timeAdapter);
        //gridView.setAdapter(timeAdapter);
        data = new ArrayList<>();
        adapter = new DoctorDetailrvAdapter(this, data, 1);
        recycleView.setLayoutManager(new NoScrollLinearLayoutManager(this));
        recycleView.setAdapter(adapter);
        OtherUtils.ratingBarColor(ratingBar, this);
        getPresenter().getExpertDetail();
        if (!SharedPrefHelper.getInstance().getServiceHint(Constants.EXPERTS)) {
            getHint();
        }
    }

    @OnClick({R.id.iv_doc_detail_back, R.id.iv_doc_detail_consult, R.id.tv_now_order, R.id.tv_check_all, R.id.hint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_doc_detail_back:
                finish();
                break;
            case R.id.iv_doc_detail_consult: // 咨询
                Intent intent1 = new Intent(this, DoctorConsultActivity.class);
                intent1.putExtra("doctorid", doctorID);
                intent1.putExtra("Name", doctorName);
                intent1.putExtra("Image", doctorImage);
                startActivity(intent1);
                break;
            case R.id.tv_now_order://
                if (timeID == 0) {
                    ToastUtil.showToast(ExpertsVisitActivity.this, "请选择预约时间！");
                    return;
                }
                Intent intent2 = new Intent(this, ReserveInfoActivity.class);
                intent2.putExtra("TimeID", timeID);
                intent2.putExtra("Time", time);
                if (entity.getId() != 0) {
                    intent2.putExtra("Data", entity);
                } else {
                    ToastUtil.showToast(ExpertsVisitActivity.this, "数据为空！");
                    return;
                }
                startActivity(intent2);
                break;
            case R.id.tv_check_all:// 更多评论
                Intent intent = new Intent(this, MoreUSerCommentActivity.class);
                intent.putExtra("id", doctorID);
                startActivity(intent);
                break;
            case R.id.hint:
                getHint();
                break;
        }
    }

    @Override
    public void getExpertDetailSucc(ExpertDetailBean bean) {
        if (bean.getExpert() == null) {
            return;
        }
        entity = bean.getExpert();
        doctorID = bean.getExpert().getId();
        tvSeeing.setText(bean.getExpert().getConsultcount() + "");// 问诊量
        tvDoctorName.setText(bean.getExpert().getRealname());// 医生名
        doctorName = bean.getExpert().getRealname();
        tvDoctorPosition.setText(bean.getExpert().getPositionname());// 职位名
        tvHospital.setText(bean.getExpert().getHospitalName());// 医院名
        tvStarNum.setText(bean.getExpert().getScore() + "");// 分数
        ratingBar.setRating((float) bean.getExpert().getScore());
        skilledInfo.setText(/*"擅长：" + */bean.getExpert().getSkilledinfo());// 擅长
        tvOrderPersonNum.setText(bean.getExpert().getRemaincount() + "/" + bean.getExpert().getInvitetotalcount());// 预约人数
        tvWorkTime.setText(bean.getExpert().getWorktime());// 工作时间
        tvContent.setText(bean.getExpert().getBrief());// 简介
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getExpert().getPhoto(), ivDocPhoto, R.mipmap.ic_default_square);
        doctorImage = bean.getExpert().getPhoto();
        // 评论
        if (bean.getCommentlist() != null) {
            data.addAll(bean.getCommentlist());
            adapter.notifyDataSetChanged();
        }
        // 时间(天)
        if (bean.getSchedules() != null) {
            timeDayData.addAll(bean.getSchedules());
            dayAdapter.notifyDataSetChanged();
            if (bean.getSchedules().size() > 0 && bean.getSchedules().get(0).getSchedulelist() != null) {
                timeData.addAll(bean.getSchedules().get(0).getSchedulelist());
                timeAdapter.notifyDataSetChanged();
            }
        }
    }

    // 获取服务信息  用于弹出窗
    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.EXPERTS);
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.errCode == 0) {
                    // TODO 弹温馨出提示窗
                    final ShowHintDialog pop = new ShowHintDialog(ExpertsVisitActivity.this, mResponse.getServiceInfo().getRemind(), Constants.EXPERTS);
                    pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                        @Override
                        public void onSetting(TextView content) {
                            pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(ExpertsVisitActivity.this, 280));
                        }
                    });
                    pop.show();
                } else {
                    ToastUtil.showToast(ExpertsVisitActivity.this, mResponse.msg);
                }
            }
        });
    }

    @Override
    public ExpertDetailPresenter createPresenter() {
        return new ExpertDetailPresenter();
    }

    @Override
    public ExpertDetailModel createModel() {
        return new ExpertDetailModelImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.blue), true);
    }
}
