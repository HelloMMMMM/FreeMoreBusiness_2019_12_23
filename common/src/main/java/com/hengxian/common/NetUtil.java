package com.hengxian.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 */
public class NetUtil {

    /**
     * 检测网络是否连接
     *
     * @param context 上下文
     * @return 结果
     */
    @SuppressLint("MissingPermission")
    public static boolean isNetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                return true;
            }
        }

        return false;
    }
}
