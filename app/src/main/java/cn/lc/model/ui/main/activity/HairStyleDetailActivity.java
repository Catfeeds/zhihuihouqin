package cn.lc.model.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class HairStyleDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.more_product_title)
    TitleBar titleBar;
    @BindView(R.id.iv_hair_style)
    ImageView ivHairStyle;
    @BindView(R.id.tv_hair_style_name)
    TextView tvHairStyleName;
    @BindView(R.id.tv_how_much)
    TextView tvHowMuch;
    @BindView(R.id.tv_hair_style_des)
    TextView tvHairStyleDes;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.activity_hair_style_detail)
    RelativeLayout activityHairStyleDetail;
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_style_detail);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("发行详情");
    }

    @OnClick({R.id.tv_share, R.id.tv_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                showBottomDialog();
                break;
            case R.id.tv_collect:
                // TODO: 2017/8/23 0023 还未完成
                showToast("还没写");
                break;
        }
    }

    private void showBottomDialog() {
        dialog = new BottomSheetDialog(this, R.style.bottomSheet_dialog_animation);
       // View view = View.inflate(this, R.layout.bottom_share, null);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_share, null);
        LinearLayout llFrendsCircle= (LinearLayout) view.findViewById(R.id.ll_frends_circle);
        LinearLayout llWeixinFriend= (LinearLayout) view.findViewById(R.id.ll_weixin_frends);
        LinearLayout llQQ= (LinearLayout) view.findViewById(R.id.ll_qq);
        llFrendsCircle.setOnClickListener(this);
        llWeixinFriend.setOnClickListener(this);
        llQQ.setOnClickListener(this);
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_frends_circle:
                dialog.dismiss();
                showToast("分享到朋友圈");
                break;
            case R.id.ll_weixin_frends:
                dialog.dismiss();
                showToast("分享给微信好友");
                break;
            case R.id.ll_qq:
                dialog.dismiss();
                showToast("分享到qq");
                break;
        }
    }

    private void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
