package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;

public class ExpertsVisitActivity extends AppCompatActivity {

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
    @BindView(R.id.tv_content_recomment)
    TextView tvContentRecomment;
    @BindView(R.id.nsrlv_doc_tetail)
    NoSlideRecyclerView recyclerView;
    @BindView(R.id.iv_doc_detial_consult)
    TextView ivDocDetialConsult;
    @BindView(R.id.tv_now_order)
    TextView tvNowOrder;
    @BindView(R.id.tv_check_all)
    TextView tvCheckAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experts_visit);
        ButterKnife.bind(this);
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DoctorDetailrvAdapter detailrvAdapter = new DoctorDetailrvAdapter(this);
        recyclerView.setAdapter(detailrvAdapter);
    }

    @OnClick({R.id.iv_doc_detail_back, R.id.iv_doc_detial_consult, R.id.tv_now_order,R.id.tv_check_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_doc_detail_back:
                finish();
                break;
            case R.id.iv_doc_detial_consult:
                break;
            case R.id.tv_now_order:
                Intent intent2=new Intent(this,ReserveInfoActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_check_all:
                Intent intent = new Intent(this, MoreUSerCommentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
