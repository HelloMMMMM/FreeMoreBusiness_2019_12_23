package com.hengxian.common;

/**
 * Created by wangfangqi on 2017/10/26.
 */

public class ClickUtil {

    private static long lastClickTime;
    private static int num;

    /**
     * 限制一秒内连续点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 800) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    /**
     * 限制一秒内连续点击
     */
    public static boolean isFastDoubleClick(int limitTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= limitTime) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    /**
     * 检测第八次点击
     */
    public static boolean eightClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;

        if (lastClickTime != 0 && timeD > 500) {
            num = 0;
        }
        ++num;

        if (num != 8) {
            lastClickTime = time;
            return false;
        }
        return true;
    }
}
