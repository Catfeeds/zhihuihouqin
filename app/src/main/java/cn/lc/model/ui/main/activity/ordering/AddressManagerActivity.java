package cn.lc.model.ui.main.activity.ordering;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.ui.main.adapter.AddressAdapter;
import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.modelimpl.AddressModelImpl;
import cn.lc.model.ui.main.presenter.AddressPresenter;
import cn.lc.model.ui.main.view.AddressView;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：送货地址管理类
 * 作者：Shixhe On 2017/9/4 0004
 */
public class AddressManagerActivity extends BaseActivity<AddressModel, AddressView, AddressPresenter> implements AddressView {

    private static final int REQUEST_EDIT_ADDRESS = 101;
    private static final int REQUEST_ADD_ADDRESS = 102;

    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.list_view)
    ListView listView;

    private AddressAdapter adapter;
    //    private AddressBean data;
    private List<AddressBean.AddressListEntity> data;

    private int addressId = 0;
    private String userName;

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    public AddressModel createModel() {
        return new AddressModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_address_manager);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        addressId = getIntent().getIntExtra("ID", 0);
        userName = getIntent().getStringExtra("addressName");
        data = new ArrayList<>();
        adapter = new AddressAdapter(this, data);
        listView.setAdapter(adapter);
        adapter.setOnEditClickListener(new AddressAdapter.OnEditClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                intent.putExtra("Type", 0);
                intent.putExtra("Id", data.get(position).getId());
                intent.putExtra("Name", data.get(position).getRealname());
                intent.putExtra("Mobile", data.get(position).getMobile());
                intent.putExtra("Address", data.get(position).getAddress());
                startActivityForResult(intent, REQUEST_EDIT_ADDRESS);
            }
        });

        adapter.setOnSelectClickListener(new AddressAdapter.OnSelectClickListener() {
            @Override
            public void onClick(int id, String name) {
                addressId = id;
                userName = name;
            }
        });
        getPresenter().getAddress();
    }

    @OnClick({R.id.btn_add, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                intent.putExtra("Type", 1);
                startActivityForResult(intent, REQUEST_ADD_ADDRESS);
                break;

            case R.id.back:
                Intent intent1 = new Intent();
                intent1.putExtra("ID", addressId);
                intent1.putExtra("Name", userName);
                setResult(RESULT_OK, intent1);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent datas) {
        super.onActivityResult(requestCode, resultCode, datas);
        if (resultCode != RESULT_OK) {

        } else {
            switch (requestCode) {
                case REQUEST_EDIT_ADDRESS:
                    // 编辑地址
                    int id = datas.getIntExtra("ID", 0);
                    if (datas.getIntExtra("Type", -1) == 0) {
                        // 删除地址
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getId() == id) {
                                data.remove(i);
                                break;
                            }
                        }
                    } else {
                        //  编辑地址
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getId() == id) {
                                data.get(i).setRealname(datas.getStringExtra("Name"));
                                data.get(i).setAddress(datas.getStringExtra("Address"));
                                data.get(i).setMobile(datas.getStringExtra("Mobile"));
                                break;
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();

                    break;

                case REQUEST_ADD_ADDRESS:
                    // 添加地址
//                    AddressBean.AddressListEntity address = new AddressBean.AddressListEntity();
//                    address.setRealname(datas.getStringExtra("Name"));
//                    address.setMobile(datas.getStringExtra("Mobile"));
//                    address.setAddress(datas.getStringExtra("Address"));
//                    data.getAddressList().add(address);
//                    adapter.notifyDataSetChanged();
                    getPresenter().getAddress();
                    break;
            }
        }
    }

    @Override
    public void addressData(AddressBean bean) {
        if (bean == null || bean.getAddressList() == null) {
            ToastUtil.showToast(this, "暂无数据！");
            return;
        }
        data.clear();
        data.addAll(bean.getAddressList());
        adapter.notifyDataSetChanged();
//        }
        if (addressId != 0) {
            for (int i = 0; i < bean.getAddressList().size(); i++) {
                if (bean.getAddressList().get(i).getId() == addressId) {
                    adapter.setSelectPosition(i);
                    adapter.notifyDataSetChanged();
                    return;
                }
            }
        }

    }
}
