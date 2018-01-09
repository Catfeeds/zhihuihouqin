package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.BuildNumAdapter;
import com.moe.wl.ui.main.bean.BuildNumList;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;

public class BuildNumAct extends Base2Activity {

    @BindView(R.id.build_num_list)
    RecyclerView buildNumList;
    @BindView(R.id.title)
    TitleBar title;
    private BuildNumAdapter adapter;
    private List<BuildNumList.DataBean> data;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_build_num);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        initRecycler();
        getDataList();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("楼号");
    }

    private void initRecycler() {
        buildNumList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BuildNumAdapter(this);
        buildNumList.setAdapter(adapter);
        adapter.setListener(new BuildNumAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(int position,String buildName,int id) {
                Intent intent = new Intent();
                intent.putExtra("buildname",buildName);
                intent.putExtra("buildNum",id);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void getDataList() {
        Observable observable = RetrofitUtils.getInstance().getBuildNum();
        showProgressDialog();
        observable.subscribe(new Subscriber<BuildNumList>() {

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("getDataList=="+e.getMessage());
            }

            @Override
            public void onNext(BuildNumList bean) {
                LogUtils.i("BuildNumList===="+bean.getErrCode());
                if(bean.getErrCode()==0){
                    data = bean.getData();
                    Collections.reverse(data);
                    adapter.setData(data);
                } else if (bean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    showToast(bean.getMsg());
                }

            }
        });
    }
}
