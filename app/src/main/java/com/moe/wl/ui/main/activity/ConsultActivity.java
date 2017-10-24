package com.moe.wl.ui.main.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberChatAdapter;
import com.moe.wl.ui.main.bean.ConsultBarberBean;
import com.moe.wl.ui.main.bean.SendMessageBean;
import com.moe.wl.ui.main.model.ConsultModel;
import com.moe.wl.ui.main.modelimpl.ConsultModelImpl;
import com.moe.wl.ui.main.presenter.ConsultPresenter;
import com.moe.wl.ui.main.view.ConsultView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultActivity extends BaseActivity<ConsultModel,ConsultView,ConsultPresenter> implements ConsultView {
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rv_chat)
    RecyclerView rvChat;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.btn_send)
    TextView btnSend;
    @BindView(R.id.activity_consult)
    LinearLayout activityConsult;
    private BarberChatAdapter adapter;
    private int id;

    @Override
    public ConsultPresenter createPresenter() {
        return new ConsultPresenter();
    }

    @Override
    public ConsultModel createModel() {
        return new ConsultModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_consult);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("barberid", 1);
        initTitle();
        getPresenter().getConsultBarberInfo(id);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BarberChatAdapter();
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
        title.setTitle("在线咨询");
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        //获取消息内容
        String content = etMessage.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            return;
        }
        //调用presenter方法 发送消息
        presenter.sendMessage(id,content);
        //清空edittext
        etMessage.setText("");
        //收起软键盘
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(rvChat.getWindowToken(),0);
    }

    @Override
    public void getConsultInfo(ConsultBarberBean bean) {
        if(bean!=null){
            List<ConsultBarberBean.NoticelistBean> noticelist = bean.getNoticelist();
            adapter.setData(noticelist);
            if(noticelist.size()>1){
                rvChat.smoothScrollToPosition(noticelist.size()-1);
            }
        }
    }

    @Override
    public void sendMessageSucc(SendMessageBean bean) {
        //adapter.notifyDataSetChanged();
        getPresenter().getConsultBarberInfo(id);
    }
}
