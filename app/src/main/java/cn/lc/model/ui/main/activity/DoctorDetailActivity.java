package cn.lc.model.ui.main.activity;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.ui.main.adapter.DoctorDetailGrideAdapter;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;
import cn.lc.model.ui.main.bean.DoctorDetailBean;
import cn.lc.model.ui.main.model.DoctorDetailModel;
import cn.lc.model.ui.main.presenter.DoctorDetailPresenter;
import cn.lc.model.ui.main.view.DoctorDetailView;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;

public class DoctorDetailActivity extends BaseActivity<DoctorDetailModel,DoctorDetailView,DoctorDetailPresenter> implements DoctorDetailView {

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
    TextView tvSeeing;//问诊量
    @BindView(R.id.tv_doctor_specialty)
    TextView tvDoctorSpecialty;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
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
    private DoctorDetailBean doctorDetailBean;

    /* @BindView(R.id.ll_title)
     LinearLayout llTitle;
     @BindView(R.id.sv)
     ScrollView sv;*/
    /*@BindView(R.id.activity_doctor_detail)
    LinearLayout activityDoctorDetail;*/
   /* int sumY; //在y轴方向上滚动的距离
    float distance = 150.0f; //最大的滚动距离
    int start = 0xFF3190E8;
    int end = 0x553190E8;
    int bgColor;  //动态计算的背景值
    ArgbEvaluator mEvaluator = new ArgbEvaluator();*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int dy=oldScrollY-scrollY;
                    sumY += dy;
                    Log.e("scrollY",scrollY+"");
                    Log.e("oldScrollY",oldScrollY+"");
                    Log.e("dy====",dy+"");
//                sumY / distance;
                    if(sumY <= 0){
                        bgColor = start; //未开始滚动
                    }else if(sumY >= distance){
                        bgColor = end; //已经达到或者超出最大值
                    }else{
                        bgColor = (int) mEvaluator.evaluate(sumY/distance, start, end);
                    }
                    llTitle.setBackgroundColor(bgColor);
                }
            });
        }*/
    }

    @Override
    public DoctorDetailPresenter createPresenter() {
        return null;
    }

    @Override
    public DoctorDetailModel createModel() {
        return null;
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        doctorDetailBean = (DoctorDetailBean) getIntent().getSerializableExtra("listBean");
        switchButtonClick();
        switchButton.setChecked(true);
        //ratingBar.setRating(3f);
        initGride();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DoctorDetailrvAdapter detailrvAdapter = new DoctorDetailrvAdapter(this);
        recyclerView.setAdapter(detailrvAdapter);
    }

    private void initGride() {
        DoctorDetailGrideAdapter grideAdapter = new DoctorDetailGrideAdapter(this);
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


    @OnClick({R.id.iv_doc_detail_back, R.id.iv_doc_detial_collect, R.id.tv_now_order,R.id.tv_check_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_doc_detail_back:
                finish();
                break;
            case R.id.iv_doc_detial_collect:
                break;
            case R.id.tv_check_all:
                Intent intent1 = new Intent(this, MoreUSerCommentActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_now_order:
                Intent intent = new Intent(this,PayFiveJiaoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getDoctorDetailSucc(DoctorDetailBean listBean) {

    }
}
