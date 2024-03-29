package com.fx.feemore.business.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * function:金额输入过滤器
 * author: frj
 * modify date: 2018/7/5
 */
public class CashierInputFilter implements InputFilter
{

    //输入的最大金额
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    //小数点后的位数
    private static final int POINTER_LENGTH = 2;

    private static final String POINTER = ".";

    private static final String ZERO = "0";

    //小数点后的位数
    private int decimal_num = POINTER_LENGTH;

    private Pattern mPattern;

    CashierInputFilter()
    {
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }

    CashierInputFilter(int decimal_num)
    {
        this();
        this.decimal_num = decimal_num;
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入de内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
    {
        String sourceText = source.toString();
        String destText = dest.toString();
        //验证删除等按键
        if (TextUtils.isEmpty(sourceText))
        {
            return "";
        }
        Matcher matcher = mPattern.matcher(source);
        //已经输入小数点的情况下，只能输入数字
        if (destText.contains(POINTER))
        {
            if (!matcher.matches())
            {
                return "";
            } else
            {
                if (POINTER.equals(source))
                {  //只能输入一个小数点
                    return "";
                }
            }
            //验证小数点精度，保证小数点后只能输入两位
            int index = destText.indexOf(POINTER);
            int length = destText.length() - index;
            //如果长度大于2，并且新插入字符在小数点之后
            if (length > decimal_num && index < dstart)
            {
                //超出2位返回null
                return "";
            }
        } else
        {
            if (destText.length() == 1)
            {
                if (destText.charAt(0) == '0')
                {
                    if (!sourceText.contains("."))
                    {
                        return "." + sourceText;
                    }
                }
            }
            //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点
            if (!matcher.matches())
            {
                return "";
            } else
            {
                if ((POINTER.equals(source)) && TextUtils.isEmpty(destText))
                {
                    return "";
                }
            }
        }
        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + sourceText);
        if (sumText > MAX_VALUE)
        {
            return dest.subSequence(dstart, dend);
        }
        return dest.subSequence(dstart, dend) + sourceText;
    }

    /**
     * 添加输入过滤器
     *
     * @param ed
     */
    public static void addFilter(EditText ed)
    {
        CashierInputFilter inputFilter = new CashierInputFilter();
        InputFilter inputFilters[] = ed.getFilters();
        InputFilter afterFilters[] = null;
        if (inputFilters != null)
        {
            afterFilters = new InputFilter[inputFilters.length + 1];
            for (int i = 0; i < inputFilters.length; i++)
            {
                afterFilters[i] = inputFilters[i];
            }
            afterFilters[inputFilters.length] = inputFilter;
        } else
        {
            afterFilters = new InputFilter[1];
            afterFilters[0] = inputFilter;
        }
        ed.setFilters(afterFilters);
    }
}
