package com.fx.feemore.business.util;

import android.widget.TextView;

/**
 * function:
 * author: frj
 * modify date: 2019/1/8
 */
public class TextViewUtil
{

    /**
     * 获取TextView的值
     *
     * @param tv TextView项
     * @return TextView的文本值
     */
    public static String getText(TextView tv)
    {
        if (tv != null)
        {
            return tv.getText().toString().trim();
        }
        return "";
    }
}
