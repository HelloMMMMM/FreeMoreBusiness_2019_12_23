package com.fx.feemore.business.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.ShareUtils;
import com.hengxian.common.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * function:
 * author: www46
 * modify date: 2018/12/11
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler
{

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, ShareUtils.WECHAT_APPID);
        //等待微信回调
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq)
    {

    }

    @Override
    public void onResp(BaseResp baseResp)
    {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX)
        {
            if (baseResp.errCode == BaseResp.ErrCode.ERR_OK)
            {
                ToastUtil.show("微信支付成功");
                BroadCastUtil.sendRefreshStoreInfo();
            } else if (baseResp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL)
            {
                ToastUtil.show("用户取消支付");
            } else
            {
                ToastUtil.show("微信支付失败");
            }
            finish();
        }
    }
}
