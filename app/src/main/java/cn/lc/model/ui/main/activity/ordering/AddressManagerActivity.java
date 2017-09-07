package cn.lc.model.ui.main.activity.ordering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.AddressAdapter;
import cn.lc.model.ui.main.bean.AddressBean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */
public class AddressManagerActivity extends AppCompatActivity {

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.list_view)
    ListView listView;

    private AddressAdapter adapter;
    private List<AddressBean> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        adapter = new AddressAdapter(this, data);
        listView.setAdapter(adapter);
    }
}
