package com.moe.wl.ui.main.activity.DryCleaners;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.DryCleanersLvAdapter;
import com.moe.wl.ui.main.bean.ClothBean;
import com.moe.wl.ui.main.bean.JieYueBean;
import com.moe.wl.ui.main.model.DryCleanReserveInfoModel;
import com.moe.wl.ui.main.modelimpl.DryCleanReserveInfoModelImpl;
import com.moe.wl.ui.main.presenter.DryCleanReserveInfoPresenter;
import com.moe.wl.ui.main.view.DryCleanReserveInfoView;
import com.moe.wl.ui.mywidget.BottomTimeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;

public class DryCleanReserveInfoActivity extends BaseActivity<DryCleanReserveInfoModel,
        DryCleanReserveInfoView, DryCleanReserveInfoPresenter> implements DryCleanReserveInfoView {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.et_phone_num)
    EditText etPhoneNum;
    @BindView(R.id.iv_send_time)
    ImageView ivSendTime;
    @BindView(R.id.rl_set_time)
    LinearLayout rlTime;
    @BindView(R.id.rv_dry_clean)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_time)
    TextView tvTime;

    /* @BindView(R.id.tv_more)
     TextView tvMore;*/
    private int page = 1;
    private String limit = "40";
    private DryCleanersLvAdapter lvAdapter;
    private List<ClothBean.PageEntity.ListEntity> listAll = new ArrayList<>();
    private int sum;
    private BottomTimeDialog dialog;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_dry_clean_reserve_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initListView();
        String realName = SharedPrefHelper.getInstance().getRealName();
        String nickname = SharedPrefHelper.getInstance().getNickname();
        if (TextUtils.isEmpty(realName)) {//真实姓名没有显示昵称
            tvUserName.setText(nickname);
        } else {
            tvUserName.setText(realName);
        }

        etPhoneNum.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        getData(1 + "", limit);
    }

    private void getListSucc(List<ClothBean.PageEntity.ListEntity> list) {
        if (list != null) {
            if (page == 1) {
                listAll.clear();
                sum = 0;
                tvSum.setText("共" + sum + "件");
            }
            listAll.addAll(list);
            lvAdapter.setData(listAll);
            lvAdapter.setAddListener(new DryCleanersLvAdapter.OnAddClickListener() {
                @Override
                public void addClick(int count, int position) {
                    listAll.get(position).setCount(count);
                    sum = 0;
                    for (int i = 0; i < listAll.size(); i++) {
                        sum += listAll.get(i).getCount();
                    }
                    tvSum.setText("共" + sum + "件");
                }
            });
            lvAdapter.setMinusListener(new DryCleanersLvAdapter.OnMinusClickListener() {
                @Override
                public void minusClick(int count, int position) {
                    listAll.get(position).setCount(count);
                    sum = 0;
                    for (int i = 0; i < listAll.size(); i++) {
                        sum += listAll.get(i).getCount();
                    }
                    tvSum.setText("共" + sum + "件");
                }
            });
            lvAdapter.setEtListener(new DryCleanersLvAdapter.EtListener() {
                @Override
                public void etlisten(int position, String content) {
                    listAll.get(position).setRemark(content);
                }
            });
        }
    }
    public  boolean isVisBottom(RecyclerView recyclerView){
     /*   LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if(visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recyclerView.SCROLL_STATE_IDLE){
            return true;
        }else {
            return false;
        }*/

        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
    private void initListView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lvAdapter = new DryCleanersLvAdapter(this);
        recyclerView.setAdapter(lvAdapter);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData(page + "", limit + "");
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                if(!isVisBottom(recyclerView))
                getData(page + "", limit + "");
                recyclerView.loadMoreComplete();
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("洗衣店");
    }

    @OnClick({R.id.tv_submit, /*R.id.tv_more,*/ R.id.rl_set_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                String mobile = etPhoneNum.getText().toString().trim();
                String time = tvTime.getText().toString().trim();
                if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
                    showToast("请输入正确的手机号码");
                    return;
                }
               /* if (TextUtils.isEmpty(time)) {
                    showToast("请选择时间");
                    return;
                }*/
                Gson gson = new Gson();
                List<ClothBean.PageEntity.ListEntity> mList = new ArrayList<>();
                for (int i = 0; i < listAll.size(); i++) {
                    if (listAll.get(i).getCount() != 0) {
                        LogUtils.i("ListEntity==="+listAll.get(i));
                        mList.add(listAll.get(i));
                    }
                }
                String json = gson.toJson(mList);
                if (sum <= 0) {
                    showToast("您没有下单，请下单后在提交");
                    return;
                }
                Intent intent = new Intent(this, ConfirmDryCleanOrderActivity.class);
                intent.putExtra("mobile", mobile);
                intent.putExtra("time", time);
                intent.putExtra("json", json);
                startActivity(intent);
                break;
           /* case R.id.tv_more:
                page++;
                getData(page + "", limit, true);
                break;*/
            case R.id.rl_set_time:
                dialog = new BottomTimeDialog(this, R.style.dialog_style);
                dialog.show();
                dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClickListener(int i1, int i2, int i3, String i4, String i5) {
                        switch (DateUtil.compareDate(i1 + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5, DateUtil.getTimeyyyyMMddHHmm())) {
                            case 1:
                                tvTime.setText(i1 + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5);
                                break;
                            case 2:
                                ToastUtil.showToast(DryCleanReserveInfoActivity.this, "预计送达时间已过！");
                                break;
                            default:
                                tvTime.setText(i1 + "-" + i2 + "-" + i3 + " " + i4 + ":" + i5);
                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void OrderDryCleaner(ClothBean cleanBean) {
        if (cleanBean.getErrCode() == 0) {
            List<ClothBean.PageEntity.ListEntity> list = cleanBean.getPage().getList();
            getListSucc(list);
        } else {
            showToast(cleanBean.getMsg());
        }
    }

    @Override
    public void commitSucc(JieYueBean commitBean) {

    }

    @Override
    public DryCleanReserveInfoModel createModel() {
        return new DryCleanReserveInfoModelImpl();
    }

    @Override
    public DryCleanReserveInfoPresenter createPresenter() {
        return new DryCleanReserveInfoPresenter();
    }

    public void getData(String page, String limit) {
        getPresenter().getData(page, limit);
    }
}
