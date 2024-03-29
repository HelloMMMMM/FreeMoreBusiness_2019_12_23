package com.fx.feemore.business.receive;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.ui.notify.AcNotifyManager;
import com.fx.feemore.business.util.BroadCastUtil;
import com.google.gson.Gson;
import com.hengxian.common.Log;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * function:极光推送广播接收者
 * author: frj
 * modify date:
 * modify date:
 */

public class JPushReceive extends BroadcastReceiver {
    private static final String TAG = "JPushReceive";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            handlerMsg(extras);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            receivingNotification(context, bundle);
            handlerMsg(extras);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            if (isApplicationRunning(context)) {
                openNotification(context, bundle);
            } else {
                String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                Log.e("receive", extras);

                //跳转至主界面，再在主界面做判断
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName(context.getPackageName(), context.getPackageName() + ".ui.character.AcCharacterSelected");
                i.setComponent(cn);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    /**
     * 接收到推送通知
     *
     * @param context
     * @param bundle
     */
    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d(TAG, "extras : " + extras);
    }

    /**
     * 点击消息通知
     *
     * @param context 上下文
     * @param bundle
     */
    private void openNotification(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.i(TAG, "openNotification,extra:" + extras);
        if (!TextUtils.isEmpty(extras)) {
            Intent intent;
            intent = new Intent(context, AcNotifyManager.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.i(TAG, "jump to detail");
        } else {
            Log.i(TAG, "advisoryBean is   null");
        }
    }

    /**
     * 处理消息
     *
     * @param extras 附加消息内容
     */
    private void handlerMsg(String extras) {
        Gson gson = new Gson();
        MsgExtraData data = gson.fromJson(extras, MsgExtraData.class);
        BroadCastUtil.sendRefreshStoreInfo();
        if (data != null) {
            if (MsgExtraData.TYPE_ORDER.equals(data.getType())) {
                AppContext.getInstanceBase().putValueToShare(MsgExtraData.KEY_ORDER, true);
            } else {
                AppContext.getInstanceBase().putValueToShare(MsgExtraData.KEY_OTHER, true);
            }
        }
    }


    /**
     * 判断应用是否在运行
     *
     * @param context
     * @return
     */
    private boolean isApplicationRunning(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(
                    context.getPackageName())
                    && info.baseActivity.getPackageName().equals(
                    context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 附加消息
     */
    public static class MsgExtraData {
        /**
         * 订单消息
         */
        public static final String TYPE_ORDER = "1";
        /**
         * 其他消息
         */
        public static final String TYPE_OTHER = "2";

        public static final String KEY_ORDER = "notify_type_order_key";
        public static final String KEY_OTHER = "notify_type_other_key";

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
