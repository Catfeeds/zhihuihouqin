package com.moe.wl.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberChatAdapter;
import com.moe.wl.ui.main.adapter.DocConsultAdapter;
import com.moe.wl.ui.main.bean.DexpertnoticeBean;
import com.moe.wl.ui.main.bean.ExpertnoticelistBean;
import com.moe.wl.ui.main.model.GetDocConsultListModel;
import com.moe.wl.ui.main.modelimpl.GetDocConsultModelImpl;
import com.moe.wl.ui.main.presenter.GetDocConsultListPresenter;
import com.moe.wl.ui.main.view.GetDocConsultLisstView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 我的电脑 on 2017/10/20 0020.
 */

public class DoctorConsultActivity extends BaseActivity<GetDocConsultListModel,GetDocConsultLisstView,GetDocConsultListPresenter> implements GetDocConsultLisstView {
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rv_chat)
    RecyclerView rvChat;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.btn_send)
    Button btnSend;
    private int id;
    private DocConsultAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_consult);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("doctorid", 1);
        initTitle();
        getPresenter().getConsultList(id+"");//获得咨询列表
        rvChat.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new BarberChatAdapter();
        adapter = new DocConsultAdapter(this);
        rvChat.setAdapter(adapter);
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==0){
                    btnSend.setEnabled(false);
                }else{
                    btnSend.setEnabled(true);
                }
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("在先咨询");
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        //获取消息内容
        String content = etMessage.getText().toString().trim();
        //调用presenter方法 发送消息
        presenter.sendMess(id,content);
        //清空edittext
        etMessage.setText("");
        //收起软键盘
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(rvChat.getWindowToken(),0);
    }

    @Override
    public void getListResult(ExpertnoticelistBean bean) {
        if(bean!=null){
            List<ExpertnoticelistBean.NoticelistBean> noticelist = bean.getNoticelist();
            adapter.setData(noticelist);
            if(noticelist.size()>1){
                rvChat.smoothScrollToPosition(noticelist.size()-1);
            }
        }
    }

    @Override
    public void sendResult(DexpertnoticeBean bean) {
        if(bean!=null){
            getPresenter().getConsultList(id+"");
        }
    }

    @Override
    public GetDocConsultListModel createModel() {
        return new GetDocConsultModelImpl();
    }

    @Override
    public GetDocConsultListPresenter createPresenter() {
        return new GetDocConsultListPresenter();
    }
}
