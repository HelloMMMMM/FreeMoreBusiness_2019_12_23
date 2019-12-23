package com.fx.feemore.business.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * function:银行卡号工具类
 * author: frj
 * modify date: 2019/3/6
 */
public class BankCardNoUtil
{
    /**
     * 获取银行卡最后四位
     *
     * @param bankCardNo 银行卡号
     * @return
     */
    @NonNull
    public static String getLastFourNo(String bankCardNo)
    {
        if (TextUtils.isEmpty(bankCardNo))
        {
            return "";
        }
        if (bankCardNo.length() > 4)
        {
            return bankCardNo.substring(bankCardNo.length() - 4);
        }
        return bankCardNo;
    }

    /**
     * 银行卡号匿名化
     *
     * @param bankCardNo 银行卡号
     * @return 4位数前的字都显示*号的字符串
     */
    public static String cardAnonymization(String bankCardNo)
    {
        return "**** **** **** " + getLastFourNo(bankCardNo);
    }
}
