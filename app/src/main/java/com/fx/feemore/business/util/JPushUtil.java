package com.fx.feemore.business.util;

import com.fx.feemore.business.app.AppContext;

import cn.jpush.android.api.JPushInterface;

/**
 * function:极光推送工具类
 * author: frj
 * modify date: 2019/3/8
 */
public class JPushUtil
{

    /**
     * 清除所有通知
     */
    public static void cleanNotifies()
    {
        //清除所有本地通知
        JPushInterface.clearLocalNotifications(AppContext.getInstanceBase());
        //清除所有通知
        JPushInterface.clearAllNotifications(AppContext.getInstanceBase());
        //清除Tags
        JPushInterface.cleanTags(AppContext.getInstanceBase(), 1);
    }

    /**
     * 极光推送注销
     */
    public static void jpushLogout()
    {
        JPushInterface.setAlias(AppContext.getInstanceBase(), 1, "'");
        cleanNotifies();
    }

    /**
     * 极光设置别名
     *
     * @param id 别名
     */
    public static void jpushSetAlias(String id)
    {
        JPushInterface.setAlias(AppContext.getInstanceBase(), 1, id);
    }
}
