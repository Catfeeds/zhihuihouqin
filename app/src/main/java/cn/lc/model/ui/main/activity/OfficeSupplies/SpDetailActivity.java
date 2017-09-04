package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.SimpleImageBanner;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.DoctorDetailrvAdapter;


public class SpDetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_detail);
        ButterKnife.bind(this);
        intiTitle();
        intiRecycler();
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
        TextView tvSpCategory= (TextView) header.findViewById(R.id.tv_sp_category);
        TextView tvSpPrice= (TextView) header.findViewById(R.id.tv_sp_price);
        TextView tvAddress= (TextView) header.findViewById(R.id.tv_address);
        TextView tvComment= (TextView) header.findViewById(R.id.tv_comment);
        TextView tvCommnetRate= (TextView) header.findViewById(R.id.tv_comment_rate);
        ImageView dot= (ImageView) header.findViewById(R.id.iv_three_dot);
        LinearLayout more= (LinearLayout) header.findViewById(R.id.ll_more_comment);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpDetailActivity.this, SpAllCommentActivity.class);
                startActivity(intent);
            }
        });
        spDetailRv.addHeaderView(header);
        DoctorDetailrvAdapter detailrvAdapter = new DoctorDetailrvAdapter(this);
        spDetailRv.setAdapter(detailrvAdapter);
    }
   /* public void succ(Response mResponse){
        if (mResponse.getBanner().size() > 0) {
            ArrayList<BannerItem> list = new ArrayList<>();
            for (int i = 0; i < mResponse.getBanner().size(); i++) {
                BannerItem item = new BannerItem();
                item.imgUrl = mResponse.getBanner().get(i).getImg();
                LogUtil.log(item.imgUrl);
                list.add(item);
            }
            sib
                    .setSource(list)
                    .startScroll();
        } else {
            sib
                    .setSource(DataProvider.getList())
                    .startScroll();
        }
        sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.showToast(getActivity(), "position--->" + position);

            }
        });
    }*/

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
                break;
            case R.id.tv_add_car:
                break;
            case R.id.tv_now_buy:
                break;
        }
    }
}
