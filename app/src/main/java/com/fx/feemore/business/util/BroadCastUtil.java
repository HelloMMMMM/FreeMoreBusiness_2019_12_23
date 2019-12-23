package com.fx.feemore.business.util;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.fx.feemore.business.app.AppContext;

/**
 * function:广播工具类
 * author: frj
 * modify date: 2019/2/17
 */
public class BroadCastUtil {
    /**
     * 刷新店铺信息Action
     */
    public static final String ACTION_REFRESH_STOREINFO = "action_refresh_storeinfo";
    /**
     * 微信支付成功Action
     */
    public static final String ACTION_WCHAT_PAY_SUCC = "action_wchat_pay_succ";

    /**
     * 刷新店铺信息显示的Action
     */
    public static final String ACTION_REFRESH_SHOW_STOREINFO = "action_refresh_show_storeinfo";

    /**
     * 刷新店铺信息显示失败的Action
     */
    public static final String ACTION_REFRESH_SHOW_STOREINFO_FAIL = "action_refresh_show_storeinfo_fail";

    /**
     * 发送刷新店铺信息广播
     */
    public static void sendRefreshStoreInfo() {
        sendBroadCast(ACTION_REFRESH_STOREINFO);
    }

    /**
     * 发送刷新店铺信息显示广播
     */
    public static void sendRefreshShowStoreInfo() {
        sendBroadCast(ACTION_REFRESH_SHOW_STOREINFO);
    }

    /**
     * 刷新店铺信息失败
     */
    public static void sendRefreshStoreInfoFail() {
        sendBroadCast(ACTION_REFRESH_SHOW_STOREINFO_FAIL);
    }

    /**
     * 发送微信支付成功广播
     */
    public static void sendWchatPaySucc() {
        sendBroadCast(ACTION_WCHAT_PAY_SUCC);
    }


    /**
     * 发送指定广播
     *
     * @param action 广播Action
     */
    public static void sendBroadCast(String action) {
        LocalBroadcastManager.getInstance(AppContext.getInstanceBase()).sendBroadcast(new Intent(action));
    }
}
