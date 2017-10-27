package com.moe.wl.ui.main.activity.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.PayUpMessageBean;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moe.wl.R.id.pay_type;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/25 0025
 */
public class PayUpMessageDetailActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.pay_way)
    TextView payWay;
    @BindView(pay_type)
    TextView payType;
    @BindView(R.id.pay_to)
    TextView payTo;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.order_number)
    TextView orderNumber;

    private PayUpMessageBean.PageEntity.ListEntity data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_up_message_detail);
        ButterKnife.bind(this);

        data = (PayUpMessageBean.PageEntity.ListEntity) getIntent().getSerializableExtra("Data");
        titleBar.setTitle("缴费详情");
        titleBar.setBack(true);
        if (data == null) {
            return;
        }
        price.setText(data.getPaymoney() + "");
        time.setText(data.getCreatetime());

        StringBuffer title = new StringBuffer();
        switch (data.getOrdertype()) {
            case 1: // 网络报修
                title.append("网络报修");
                break;

            case 2: // 健康档案
                title.append("健康档案");
                break;

            case 3: // 图书借阅
                title.append("图书借阅");
                break;

            case 4: // 餐费充值
                title.append("餐费充值");
                break;

            case 5: // 团队活动
                title.append("团队活动");
                break;

            case 6: // 美容美发
                title.append("美容美发");
                break;

            case 7: // 干洗店
                title.append("洗衣店");
                break;

            case 8: // 办公用品
                title.append("办公用品");
                break;

            case 9: // 订餐
                title.append("订餐");
                break;

            case 10: // 营养套餐
                title.append("营养套餐");
                break;

            case 11: // 信息公告
                title.append("信息公告");
                break;

            case 12: // 意见反馈
                title.append("意见反馈");
                break;
        }

        payTo.setText(title.toString());

        String way = "";
        switch (data.getPaytype()) {
            case 1: // 支付宝
                way = "支付宝";
                break;
            case 2: // 微信
                way = "微信";
                break;

            case 3: // 个人余额对私
                way = "个人余额对私";
                break;

            case 4: // 集体余额对公
                way = "集体余额对公";
                break;

            case 5: // 代金券对私
                way = "代金券对私";
                break;

            case 6: // 代金券对公
                way = "代金券对公";
                break;
        }
        payWay.setText(way);
        payType.setText(data.getPayremark());
        orderNumber.setText(data.getOrdercode());
        int status = data.getStatus();
        if (status == 0) {
            result.setText("交易" + "付款中");
        } else if (status == 1) {
            result.setText("交易" + "成功");
        } else if (status == 2) {
            result.setText("交易" + "失败");
        }
    }
}
