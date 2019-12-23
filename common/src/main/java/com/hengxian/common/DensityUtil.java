package com.hengxian.common;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * dp、sp、px互相转换
 */
public class DensityUtil {

    /**
     * 默认设计图宽度
     */
    private static float sDesignWidthSize = 375;

    /**
     * 原始屏幕密度
     */
    private static float sNonCompatDensity;

    /**
     * 原始字体缩放因子
     */
    private static float sNonCompatScaledDensity;

    /**
     * 设置设计图尺寸
     * @param width 设计图宽度，单位dp
     */
    public static void InitDesignSize(float width) {
        sDesignWidthSize = width;
    }

    /**
     * 自定义单位密度, 以屏幕宽度为基准
     *
     * @param activity
     * @param application
     */
    public static void setDensityWithWidth(@NonNull Activity activity, @NonNull final Application application) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNonCompatDensity == 0) {
            sNonCompatDensity = appDisplayMetrics.density;
            sNonCompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        float targetDensity = appDisplayMetrics.widthPixels / sDesignWidthSize;
        float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    /**
     * @param context
     * @param dpValue dip值
     * @return px值
     * @author：Frj 功能:dip转px 使用方法：
     */
    public static int dip2px(Context context, float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);

    }

    /**
     * @param context
     * @param pxValue px值
     * @return dip值
     * @author：Frj 功能:px转dip 使用方法：
     */
    public static int px2dip(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);

    }

    /**
     * @param context
     * @param pxValue px值
     * @return sp值
     * @author：Frj 功能:px转sp 使用方法：
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * @param context
     * @param spValue sp值
     * @return px值
     * @author：Frj 功能:sp转px 使用方法：
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
