package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.imageload.GlideLoading;
import cn.lc.model.framework.utils.OtherUtils;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;
import cn.lc.model.ui.main.adapter.ExpertSelectTimeAdapter;
import cn.lc.model.ui.main.bean.CommentlistBean;
import cn.lc.model.ui.main.bean.ExpertDetailBean;
import cn.lc.model.ui.main.model.ExpertDetailModel;
import cn.lc.model.ui.main.modelimpl.ExpertDetailModelImpl;
import cn.lc.model.ui.main.presenter.ExpertDetailPresenter;
import cn.lc.model.ui.main.view.ExpertDetailView;
import cn.lc.model.ui.mywidget.NoScrollLinearLayoutManager;
import mvp.cn.util.ToastUtil;

/**
 * 专家详情页面
 */

public class ExpertsVisitActivity extends BaseActivity<ExpertDetailModel, ExpertDetailView, ExpertDetailPresenter> implements ExpertDetailView {


    @BindView(R.id.iv_doc_detail_consult)
    Button ivDocDetailConsult; // 资讯
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
    @BindView(R.id.grid_view)
    NoSlidingGridView gridView;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;

    private List<CommentlistBean> data;
    private DoctorDetailrvAdapter adapter;
    private ExpertSelectTimeAdapter timeAdapter;

    private int doctorID = 0;

    private ExpertDetailBean.ExpertEntity entity;
    private List<ExpertDetailBean.SchedulesEntity> timeData;

    private int timeID = 0;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_experts_visit);
    }

    @Override
    public void initView() {
        entity = new ExpertDetailBean.ExpertEntity();
        timeData = new ArrayList<>();
        timeAdapter = new ExpertSelectTimeAdapter(this, timeData, new ExpertSelectTimeAdapter.OnClickListener() {
            @Override
            public void onClick(int id) {
                timeID = id;
            }
        });
        gridView.setAdapter(timeAdapter);

        data = new ArrayList<>();
        adapter = new DoctorDetailrvAdapter(this, data, 1);
        recycleView.setLayoutManager(new NoScrollLinearLayoutManager(this));
        recycleView.setAdapter(adapter);
        OtherUtils.ratingBarColor(ratingBar);
        getPresenter().getExpertDetail();
    }

    @OnClick({R.id.iv_doc_detail_back, R.id.iv_doc_detail_consult, R.id.tv_now_order, R.id.tv_check_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_doc_detail_back:
                finish();
                break;
            case R.id.iv_doc_detail_consult: // 资讯
                break;
            case R.id.tv_now_order://
                if (timeID == 0) {
                    ToastUtil.showToast(ExpertsVisitActivity.this, "请选择预约时间！");
                    return;
                }
                Intent intent2 = new Intent(this, ReserveInfoActivity.class);
                intent2.putExtra("TimeID", timeID);
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
        tvDoctorPosition.setText(bean.getExpert().getPositionname());// 职位名
        tvHospital.setText(bean.getExpert().getHospitalName());// 医院名
        tvStarNum.setText(bean.getExpert().getScore() + "");// 分数
        ratingBar.setRating((float) bean.getExpert().getScore());
        skilledInfo.setText("擅长：" + bean.getExpert().getSkilledinfo());// 擅长
        String personNum = bean.getExpert().getRemaincount() == null ? "0" : bean.getExpert().getRemaincount();
        tvOrderPersonNum.setText(personNum + "/" + bean.getExpert().getInvitetotalcount());// 预约人数
        tvWorkTime.setText(bean.getExpert().getWorktime());// 工作时间
        tvContent.setText(bean.getExpert().getBrief());// 简介
        GlideLoading.getInstance().loadImgUrlNyImgLoader(this, bean.getExpert().getPhoto(), ivDocPhoto);
        // 评论
        if (bean.getCommentlist() == null) {
            return;
        }
        data.addAll(bean.getCommentlist());
        adapter.notifyDataSetChanged();
        // 时间
        if (bean.getSchedules() == null) {
            return;
        }
        timeData.addAll(bean.getSchedules());
        timeAdapter.notifyDataSetChanged();
    }

    @Override
    public ExpertDetailPresenter createPresenter() {
        return new ExpertDetailPresenter();
    }

    @Override
    public ExpertDetailModel createModel() {
        return new ExpertDetailModelImpl();
    }

}
