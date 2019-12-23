package com.hengxian.common;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Toast 工具类
 *
 * @author wfq
 * @date 2018/6/8
 */
public class ToastUtil {

    private static Toast mToast;

    /**
     * 初始化Toast， 建议在Application中初始化
     *
     * @param context 可传任意上下文，内部已做 getApplicationContext() 转换
     */
    public static void init(Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
    }

    /**
     * 显示文本资源
     *
     * @param resId StringRes
     */
    public static void showRes(@StringRes int resId) {
        mToast.setText(resId);
        mToast.show();
    }

    /**
     * 显示文本
     *
     * @param value String
     */
    public static void show(String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        mToast.setText(value);
        mToast.show();
    }

    /**
     * 整形值重载
     *
     * @param value int类型
     */
    public static void show(int value) {
        show(String.valueOf(value));
    }

    /**
     * 单精度浮点类型重载
     *
     * @param value float
     */
    public static void show(float value) {
        show(String.valueOf(value));
    }

    /**
     * 双精度浮点类型重载
     *
     * @param value double
     */
    public static void show(double value) {
        show(String.valueOf(value));
    }

    /**
     * bool型重载
     *
     * @param value Boolean
     */
    public static void show(boolean value) {
        show(String.valueOf(value));
    }
}
