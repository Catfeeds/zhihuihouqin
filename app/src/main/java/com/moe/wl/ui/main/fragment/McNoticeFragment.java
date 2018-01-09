package com.moe.wl.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.activity.Library.BookDescriptionActivity;
import com.moe.wl.ui.main.adapter.BarberCollectAdapter;
import com.moe.wl.ui.main.adapter.BarberProAdapter;
import com.moe.wl.ui.main.adapter.BookCollectAdapter;
import com.moe.wl.ui.main.adapter.BookRvAdapter;
import com.moe.wl.ui.main.adapter.HealthServiceRvAdapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv3Adapter;
import com.moe.wl.ui.main.adapter.InfomationCollectAdapter;
import com.moe.wl.ui.main.adapter.OfficeCollectAdapter;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.ActivitylistBean;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<ActivitylistBean> actlist;
    private BookCollectAdapter bookAdapter;
    private BarberProAdapter bpAdapter;
    private BarberCollectAdapter barberAdapter;
    private List<BarberProductCollect.ListBean> barberlist;
    private boolean edit;

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
            if (!"5".equals(type) || !"7".equals(type))
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
            rvCollect.refreshComplete();
        }
        infolist.addAll(list);
        infocollectAdpater.setData(infolist);
        infocollectAdpater.setListener(new InfomationCollectAdapter.UpdataListListener() {
            @Override
            public void updataListListener(boolean isSelect, int position) {
                infolist.get(position).setSelect(isSelect);
            }
        });
    }

    @Override//办公用品
    public void getCollect2(List<OfficeCollect.ListBean> list) {
        if (page == 1) {
            officelist.clear();
            rvCollect.refreshComplete();
        }
        officelist.addAll(list);
        officeadapter.setData(officelist);
        officeadapter.setListener(new OfficeCollectAdapter.UpdataListListener() {
            @Override
            public void updataListListener(boolean isSelect, int position) {
                officelist.get(position).setSelect(isSelect);
            }
        });

    }

    @Override//理发师作品
    public void getCollect3(List<BarberProductCollect.ListBean> worklist) {
        if (worklist != null) {
            if (page == 1) {
                productlist.clear();
                rvCollect.refreshComplete();
            }
            productlist.addAll(worklist);
            bpAdapter.setData(productlist);
        } else {
            LogUtils.i("工作列表为空");
        }
        bpAdapter.setListener(new BarberProAdapter.UpdataListListener() {
            @Override
            public void updataListListener(boolean isSelect, int position) {
                //点击设置条目是否呗选中
                productlist.get(position).setSelect(isSelect);
            }
        });
    }

    @Override//图书
    public void getCollect4(List<BooklistBean> booklists) {
        if (page == 1) {
            booklist.clear();
            rvCollect.refreshComplete();
        }
        booklist.addAll(booklists);
        bookAdapter.setData(booklist);
        bookAdapter.setMyCallBack(new BookCollectAdapter.MyCallBack() {
            @Override
            public void cb(BooklistBean bookListvBean, String BookID) {
                Intent intent = new Intent(getActivity(), BookDescriptionActivity.class);
                intent.putExtra("content", bookListvBean.getBrief());
                intent.putExtra("bean", bookListvBean);
                intent.putExtra("again", true);
                startActivity(intent);
            }
        });
        bookAdapter.setListener(new BookCollectAdapter.UpdataListListener() {
            @Override
            public void updataListListener(boolean isSelect, int position) {
                booklist.get(position).setSelect(isSelect);
            }
        });
    }

    @Override//专家
    public void getCollect5(List<InfolistBean> infolist) {
        if (page == 1) {
            doclist.clear();
            rvCollect.refreshComplete();
        }
        doclist.addAll(infolist);
        docAdapter.setData(doclist);
    }

    @Override//活动
    public void getCollect6(List<ActivitylistBean> activitylist) {
        if (activitylist != null) {
            if (page == 1) {
                actlist.clear();
                rvCollect.refreshComplete();
            }
            actlist.addAll(activitylist);
            homeNsrlv3Adapter.setData(actlist);
        } else {
            LogUtils.i("活动集合为空了");
        }
    }

    @Override//理发师
    public void getCollect7(List<BarberProductCollect.ListBean> barberList) {
        if (page == 1) {
            barberlist.clear();
            rvCollect.refreshComplete();
        }
        barberlist.addAll(barberList);
        barberAdapter.setData(barberlist);
    }

    //删除成功的回调
    @Override
    public void getDetleteResult(ActivityPostBean bean) {
        if (bean != null) {
            showToast("删除成功");
            rvCollect.refresh();
        }
    }

    public void setIsEdit(boolean isEdit) {
        this.edit = isEdit;
        LogUtils.i("f-s里的isEdit==" + isEdit);
        if (isEdit) {//正在编辑状态，显示可删除按钮
            if ("1".equals(type)) {//infocollectAdpater
                infocollectAdpater.setIsEdit(isEdit);
            } else if ("2".equals(type)) {
                officeadapter.setIsEdit(isEdit);
            } else if ("3".equals(type)) {
                bpAdapter.setIsEdit(isEdit);
            } else if ("4".equals(type)) {
                bookAdapter.setIsEdit(isEdit);
            }

        } else {//点击后删除出条目，变为为编辑状态
            //  请求删除
            if ("1".equals(type)) {
                infocollectAdpater.setIsEdit(isEdit);

                List<Map<String, Integer>> noticeList = new ArrayList<>();
                for (int i = 0; i < infolist.size(); i++) {
                    InforMationCollect.ListBean listBean = infolist.get(i);
                    if (listBean.isSelect()) {
                        Map<String, Integer> map = new HashMap<>();
                        int id = listBean.getId();
                        map.put("type", 1);
                        map.put("entityid", id);
                        noticeList.add(map);
                    }
                }
                // 发起删除请求
                if(noticeList.size()>0)
                getPresenter().deleteFavorList(noticeList);

            } else if ("2".equals(type)) {
                officeadapter.setIsEdit(isEdit);

                List<Map<String, Integer>> officeLists = new ArrayList<>();
                for (int i = 0; i < officelist.size(); i++) {
                    OfficeCollect.ListBean listBean = officelist.get(i);
                    if (listBean.isSelect()) {
                        Map<String, Integer> map = new HashMap<>();
                        int id = listBean.getId();
                        map.put("type", 2);
                        map.put("entityid", id);
                        officeLists.add(map);
                    }
                }
                // 发起删除请求
                if(officeLists.size()>0)
                getPresenter().deleteFavorList(officeLists);

            } else if ("3".equals(type)) {
                //设置编辑状态
                bpAdapter.setIsEdit(isEdit);

                List<Map<String, Integer>> proList = new ArrayList<>();
                for (int i = 0; i < productlist.size(); i++) {
                    BarberProductCollect.ListBean listBean = productlist.get(i);
                    if (listBean.isSelect()) {
                        Map<String, Integer> map = new HashMap<>();
                        int id = listBean.getId();
                        map.put("type", 3);
                        map.put("entityid", id);
                        proList.add(map);
                    }
                }
                // 发起删除请求
                if (proList.size() > 0)
                    getPresenter().deleteFavorList(proList);
            }else if("4".equals(type)){//图书
                bookAdapter.setIsEdit(isEdit);

                List<Map<String, Object>> booksList = new ArrayList<>();
                for (int i = 0; i < booklist.size(); i++) {
                    BooklistBean listBean = booklist.get(i);
                    if (listBean.isSelect()) {
                        Map<String, Object> map = new HashMap<>();
                        String id = listBean.getId();
                        map.put("type", 4);
                        map.put("entityid", id);
                        booksList.add(map);
                    }
                }
                //  发起删除请求
                if(booksList.size()>0)
                getPresenter().deleteFavorList(booksList);
            }

        }
    }
}
