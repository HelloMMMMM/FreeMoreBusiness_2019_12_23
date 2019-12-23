package com.hengxian.baselib.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * function:获取设备信息
 * author: frj
 * modify date: 2018/7/20
 */
public class DeviceInfoUtil {

    /**
     * 获取手机IMEI值，如果IMEI值为空，那么获取AndroidId
     *
     * @return
     */
    public static String getIMEI(Context context) {
        if (context == null) {
            return "";
        }
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getDeviceId();
        if (TextUtils.isEmpty(imei)) {
            imei = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return imei;
    }
}
