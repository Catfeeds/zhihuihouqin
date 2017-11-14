package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.home.adapter.office.AffirmDateAdapter;
import com.moe.wl.ui.home.bean.office.AppointmentDateBean;
import com.moe.wl.ui.home.bean.office.TypeListBean;
import com.moe.wl.ui.home.model.office.AffirmOrderModel;
import com.moe.wl.ui.home.modelimpl.office.AffirmOrderModelImpl;
import com.moe.wl.ui.home.presenter.office.AffirmOrderPresenter;
import com.moe.wl.ui.home.view.office.AffirmOrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.moe.wl.R.id.ll_back;
import static com.moe.wl.R.id.tv_leader;
import static com.moe.wl.R.id.tv_name;
import static com.moe.wl.R.id.tv_people;
import static com.moe.wl.R.id.tv_submit;
import static com.moe.wl.R.id.tv_type;

/**
 * 确认订单信息
 */
public class AffirmOrderActivity extends BaseActivity<AffirmOrderModel, AffirmOrderView, AffirmOrderPresenter> implements View.OnClickListener, AffirmOrderView {

    @BindView(ll_back)
    LinearLayout llBack;
    @BindView(tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(tv_name)
    TextView tvName;
    @BindView(tv_type)
    TextView tvType;
    @BindView(R.id.lv_time)
    NoSlidingListView lv_time;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(tv_leader)
    TextView tvLeader;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(tv_submit)
    TextView tvSubmit;

    private String id;
    private String equipmentids;
    private TypeListBean conferencetype;
    private String conferencename;
    private String attendnum;
    private String attentdleader;
    private String remark;
    private List<AppointmentDateBean> dates;  //预约的时间
    private List<String> path;
    private String username;
    private String mobile;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_affirm_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        equipmentids = intent.getStringExtra("equipmentids");
        conferencetype = (TypeListBean) intent.getSerializableExtra("conferencetype");
        conferencename = intent.getStringExtra("conferencename");
        attendnum = intent.getStringExtra("attendnum");
        attentdleader = intent.getStringExtra("attentdleader");
        remark = intent.getStringExtra("remark");
        dates = (List<AppointmentDateBean>) intent.getSerializableExtra("list");

        username = SharedPrefHelper.getInstance().getRealName();
        mobile = SharedPrefHelper.getInstance().getMobile();

        tvPeople.setText(username);
        tvPhone.setText(mobile);
        tvName.setText(conferencename);
        tvType.setText(conferencetype.getTypename());
        tvNumber.setText(attendnum);
        tvLeader.setText(attentdleader);
        tvRemark.setText(remark);

        AffirmDateAdapter adapter = new AffirmDateAdapter(this);
        adapter.setItemList(dates);
        lv_time.setAdapter(adapter);

    }

    @Override
    public AffirmOrderPresenter createPresenter() {
        return new AffirmOrderPresenter();
    }

    @Override
    public AffirmOrderModel createModel() {
        return new AffirmOrderModelImpl();
    }

    @OnClick({R.id.ll_back, R.id.tv_submit})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case tv_submit:
                JsonArray ja = null;
                if (dates != null && dates.size() != 0) {
                    ja = new JsonArray();
                    for (int i = 0; i < dates.size(); i++) {
                        AppointmentDateBean bean = dates.get(i);
                        JsonObject obj = new JsonObject();
                        StringBuffer buffer = new StringBuffer();
                        for (int j = 0; j < bean.getTimes().size(); j++) {
                            if (buffer.length() != 0) {
                                buffer.append(",");
                            }
                            buffer.append(bean.getTimes().get(j).getIntervalid());
                        }
                        obj.addProperty("date", bean.getDate());
                        obj.addProperty("intervalids", buffer.toString());
                        ja.add(obj);
                    }
                }
                LogUtils.d("------------ja---------------" + ja);
                getPresenter().findAvailableEquipment(id, username, mobile, equipmentids, conferencetype.getId(), conferencename, attendnum, attentdleader, remark, ja, path);
                break;
        }
    }

    @Override
    public void setData() {
        startActivity(new Intent(this, SubmitSuccActivity.class));
        finish();
    }

}
