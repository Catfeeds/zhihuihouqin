package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.BarberCollectAdapter;
import com.moe.wl.ui.main.adapter.BarberProAdapter;
import com.moe.wl.ui.main.adapter.BookCollectAdapter;
import com.moe.wl.ui.main.adapter.BookRvAdapter;
import com.moe.wl.ui.main.adapter.HealthServiceRvAdapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv3Adapter;
import com.moe.wl.ui.main.adapter.InfomationCollectAdapter;
import com.moe.wl.ui.main.adapter.OfficeCollectAdapter;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.BarberProductCollect;
import com.moe.wl.ui.main.bean.BookCollect;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.bean.InfolistBean;
import com.moe.wl.ui.main.bean.InforMationCollect;
import com.moe.wl.ui.main.bean.OfficeCollect;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.modelimpl.McNoticeModelImpl;
import com.moe.wl.ui.main.presenter.McNoticePresenter;
import com.moe.wl.ui.main.view.McNoticeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的收藏列表
 */

public class McNoticeFragment extends BaseFragment<McNocticeModel, McNoticeView, McNoticePresenter> implements McNoticeView {

    @BindView(R.id.rv_collect)
    XRecyclerView rvCollect;
    Unbinder unbinder;

    private String type;  //1: 公告，2：办公，3：理发作品，4：图书，5：医生，6：活动，7：发型师 //8:健康资讯 9专家 10营养套餐
    private int page;
    private InfomationCollectAdapter infocollectAdpater;
    private List<InforMationCollect.ListBean> infolist;
    private OfficeCollectAdapter officeadapter;
    private List<OfficeCollect.ListBean> officelist;
    private BarberCollectAdapter productadapter;
    private List<BarberProductCollect.ListBean> productlist;
    private BookRvAdapter bookRvAdapter;
    private List<BooklistBean> booklist;
    private HealthServiceRvAdapter docAdapter;
    private List<InfolistBean> doclist;
    private HomeNsrlv3Adapter homeNsrlv3Adapter;
    private List<ActivityHomeBean.ActivitylistBean> actlist;
    private BookCollectAdapter bookAdapter;
    private BarberProAdapter bpAdapter;
    private BarberCollectAdapter barberAdapter;
    private List<BarberProductCollect.ListBean> barberlist;

    public static McNoticeFragment getInstance(String type) {
        McNoticeFragment fragment = new McNoticeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public McNocticeModel createModel() {
        return new McNoticeModelImpl();
    }

    @Override
    public McNoticePresenter createPresenter() {
        return new McNoticePresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.rv_fragment);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        infolist = new ArrayList<>();//公告集合
        officelist = new ArrayList<>(); //办公用品
        //理发作品
        productlist = new ArrayList<>();
        //图书
        booklist = new ArrayList<>();
        //医生
        doclist = new ArrayList<>();
        //活动
        actlist = new ArrayList<>();
        //发型师
        barberlist = new ArrayList();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
        if (!TextUtils.isEmpty(type)) {
            getPresenter().findUserFavorList(type);//请求收藏
        }


        rvCollect.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCollect.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvCollect.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        if ("1".equals(type)) {
            infocollectAdpater = new InfomationCollectAdapter(getActivity());
            rvCollect.setAdapter(infocollectAdpater);
        } else if ("2".equals(type)) {
            officeadapter = new OfficeCollectAdapter(getActivity());
            rvCollect.setAdapter(officeadapter);
        } else if ("3".equals(type)) {
            //productadapter = new BarberCollectAdapter(getActivity());
            bpAdapter = new BarberProAdapter(getActivity());
            rvCollect.setAdapter(bpAdapter);
        } else if ("4".equals(type)) {//图书
            //bookRvAdapter = new BookRvAdapter(getActivity());
            bookAdapter = new BookCollectAdapter(getActivity());
            rvCollect.setAdapter(bookAdapter);
        } else if ("5".equals(type)) {//专家
            docAdapter = new HealthServiceRvAdapter(getActivity());
            rvCollect.setAdapter(docAdapter);
        } else if ("6".equals(type)) {
            homeNsrlv3Adapter = new HomeNsrlv3Adapter(getActivity());
            rvCollect.setAdapter(homeNsrlv3Adapter);
        } else if ("7".equals(type)) {
            barberAdapter = new BarberCollectAdapter(getActivity());
            rvCollect.setAdapter(barberAdapter);
        }
        rvCollect.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().findUserFavorList(type);
                rvCollect.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().findUserFavorList(type);
                rvCollect.refreshComplete();
            }
        });
    }

    @Override//信息公告
    public void getCollect1(List<InforMationCollect.ListBean> list) {
        if (page == 1) {
            infolist.clear();
        }
        infolist.addAll(list);
        infocollectAdpater.setData(infolist);
    }

    @Override//办公用品
    public void getCollect2(List<OfficeCollect.ListBean> list) {
        if (page == 1) {
            officelist.clear();
        }
        officelist.addAll(list);
        officeadapter.setData(officelist);

    }

    @Override//理发师作品
    public void getCollect3(List<BarberProductCollect.ListBean> worklist) {
        if (worklist != null) {
            if (page == 1) {
                productlist.clear();
            }
            productlist.addAll(worklist);
            bpAdapter.setData(productlist);
        }else{
            LogUtils.i("工作列表为空");
        }
    }

    @Override//图书
    public void getCollect4(List<BookCollect.ListBean> booklist) {
        if (page == 1) {
            booklist.clear();
        }
        booklist.addAll(booklist);
        bookAdapter.setData(booklist);
    }

    @Override//专家
    public void getCollect5(List<InfolistBean> infolist) {
        if (page == 1) {
            doclist.clear();
        }
        doclist.addAll(infolist);
        docAdapter.setData(doclist);
    }

    @Override//活动
    public void getCollect6(List<ActivityHomeBean.ActivitylistBean> activitylist) {
        if (activitylist != null) {
            if (page == 1) {
                actlist.clear();
            }
            actlist.addAll(activitylist);
            homeNsrlv3Adapter.setData(actlist);
        } else {
            LogUtils.i("活动集合为空了");
        }
    }

    @Override//理发师
    public void getCollect7(List<BarberProductCollect.ListBean> barberList) {
        if(page==1){
            barberlist.clear();
        }
        barberlist.addAll(barberList);
        barberAdapter.setData(barberlist);
    }
}
