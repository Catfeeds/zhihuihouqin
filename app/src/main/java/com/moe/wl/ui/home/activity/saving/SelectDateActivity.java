package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.saving.SelectDateAdapter;
import com.moe.wl.ui.home.bean.saving.DateBean;
import com.moe.wl.ui.home.model.saving.ComparisonModel;
import com.moe.wl.ui.home.modelimpl.saving.ComparisonModelImpl;
import com.moe.wl.ui.home.presenter.saving.ComparisonPresenter;
import com.moe.wl.ui.home.view.saving.ComparisonView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择年月
 */
public class SelectDateActivity extends BaseActivity<ComparisonModel, ComparisonView, ComparisonPresenter> implements View.OnClickListener {


    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.gv_content)
    GridView gv_content;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String myDate;
    private int type;  //0年  1月
    private List<DateBean> list;
    private SelectDateAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_select_date);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        type=getIntent().getIntExtra("type",0);
        list=new ArrayList<>();
        if (type==1){
            list.add(new DateBean("一月"));
            list.add(new DateBean("二月"));
            list.add(new DateBean("三月"));
            list.add(new DateBean("四月"));
            list.add(new DateBean("五月"));
            list.add(new DateBean("六月"));
            list.add(new DateBean("七月"));
            list.add(new DateBean("八月"));
            list.add(new DateBean("九月"));
            list.add(new DateBean("十月"));
            list.add(new DateBean("十一月"));
            list.add(new DateBean("十二月"));
        }else{
            Calendar a=Calendar.getInstance();
            int year=a.get(Calendar.YEAR);
            for (int i = 0; i < 20 ; i++) {
                list.add(new DateBean(year+i+""));
            }
        }
        adapter=new SelectDateAdapter(this);
        adapter.setItemList(list);
        gv_content.setAdapter(adapter);


        gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).isCheck()){
                    list.get(position).setCheck(false);
                }else{
                    for (int i = 0; i < list.size() ; i++) {
                        if (position==i){
                            list.get(i).setCheck(true);
                        }else{
                            list.get(i).setCheck(false);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                myDate=list.get(position).getDate();
                tvTitle.setText(myDate);
            }
        });

        myDate=list.get(0).getDate();
        tvTitle.setText(myDate);

    }

    @Override
    public ComparisonPresenter createPresenter() {
        return new ComparisonPresenter();
    }

    @Override
    public ComparisonModel createModel() {
        return new ComparisonModelImpl();
    }


    @OnClick({R.id.ll_back, R.id.ll_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                Intent intent=new Intent();
                intent.putExtra("date",myDate);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

}
