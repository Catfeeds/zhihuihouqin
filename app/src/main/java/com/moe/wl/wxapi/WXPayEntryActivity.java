package com.moe.wl.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.moe.wl.framework.base.MessageEvent;
import com.moe.wl.framework.utils.LogUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import lc.cn.thirdplatform.R;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, "wx723f038c9add02a3");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d("error= " + resp.errCode);
        if (resp.errCode == 0) {
            //微信支付，不需要依赖于服务器端的通知，直接提示即可
            Toast.makeText(this, "支付成功!", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new MessageEvent(MessageEvent.WECHAT_PAY));
        } else if (resp.errCode == -1) {
            Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        } else if (resp.errCode == -2) {
            Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}