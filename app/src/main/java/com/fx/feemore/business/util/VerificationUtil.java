package com.fx.feemore.business.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hengxian.common.ToastUtil;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：非空验证
 */
public class VerificationUtil
{

    private static final String DEFAULT_TIPS = "暂无";

    /**
     * @param views
     * @return true表示验证通过。
     * @author：Frj 功能:值的非空验证 使用方法：
     */
    public static boolean requiredFieldValidator(View[] views, String[] tips)
    {
        boolean flag = false;
        EditText et = null;
        TextView tv = null;
        for (int i = 0; i < views.length; i++)
        {
            if (views[i] instanceof EditText)
            {
                et = (EditText) views[i];
                flag = validator(et, tips[i]);
            } else if (views[i] instanceof TextView)
            {
                tv = (TextView) views[i];
                flag = validator(tv, tips[i]);
            }
            if (flag == false)
            {
                i = views.length;
            } else
            {
                continue;
            }
        }
        return flag;
    }

    /**
     * @param strs 字符串数组
     * @param tips 提示字符串数组
     * @return true表示验证通过。
     * @author：Frj 功能:值的非空验证 使用方法：
     */
    public static boolean requiredFieldValidator(String[] strs, String[] tips)
    {
        boolean flag = false;
        // Strs为数组
        for (int i = 0; i < strs.length; i++)
        {
            flag = validator(strs[i], tips[i]);
            if (flag == false)
            {
                // 退出循环
                // 直接I的值 跳出循环
                i = strs.length;
            } else
            {
                // 如果不是则继续循环使用continue
                continue;
            }
        }
        return flag;
    }

    /**
     * @param strs 字符串
     * @return false 表示字符串集合中存在空字符串
     * @author：frj 功能:判断字符串集合是否存在空字符串，如果存在，那么返回false，如果不存在返回true 使用方法：
     */
    public static boolean requiredFieldValidator(String[] strs)
    {
        boolean flag = false;
        for (int i = 0; i < strs.length; i++)
        {
            flag = validator(strs[i]);
            if (flag == false)
            {
                // 退出循环
                i = strs.length;
            } else
            {
                continue;
            }
        }
        return flag;

    }

    /**
     * @param tv
     * @param tips
     * @author：Frj 功能:验证TextView的值是否为空 使用方法：
     */
    public static boolean validator(TextView tv, String tips)
    {
        String res = tv.getText().toString().trim();
        return validator(res, tips);
    }

    /**
     * @param ed
     * @param tips
     * @author：Frj 功能:验证EditText控件值是否为空 使用方法：
     */
    public static boolean validator(EditText ed, String tips)
    {
        String res = ed.getText().toString().trim();
        return validator(res, tips);
    }

    /**
     * @param res
     * @param tips
     * @return
     * @author：Frj 功能:字符串验证非空，并提示 使用方法：
     */
    public static boolean validator(String res, String tips)
    {
        // 首先判断res是否是个对象 然后判断它是否为空
        if (res != null && !res.equals(""))
        {
            return true;
        } else
        {
            ToastUtil.show(tips);
            return false;
        }
    }

    /**
     * @param res 要验证的字符串
     * @return true表示为非空的字符串，false表示为null或者""
     * @author：frj 功能: 验证字符串是否非空 使用方法：
     */
    public static boolean validator(String res)
    {
        if (res != null && !res.trim().equals(""))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * @param res 要验证的字符串
     * @return true表示为非空的字符串，false表示为null或者""
     * @author：frj 功能: 验证字符串是否非空 使用方法：
     */
    public static boolean validator(TextView res)
    {
        if (res.getText() != null && !res.getText().toString().trim().equals(""))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * @param res 要验证的字符串
     * @return true表示为非空的字符串，false表示为null或者""
     * @author：frj 功能: 验证字符串是否非空 使用方法：
     */
    public static boolean validator(EditText res)
    {
        if (res.getText() != null && !res.getText().toString().trim().equals(""))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * @param textView 要设置值的TextView控件
     * @param value    要为TextView设置的值
     * @author：frj 功能:为TextView设置值，如果value为null，那么设置TextView的值为"" 使用方法：
     */
    public static void setViewValue(TextView textView, String value)
    {
        setViewValue(textView, value, DEFAULT_TIPS);
    }

    /**
     * @param textView     要设置值的TextView控件
     * @param value        要为TextView设置的值
     * @param defaultValue 当要设置的值为空时，设置默认值
     * @author：frj 功能:为TextView设置值，如果value为null，那么设置TextView的值为"" 使用方法：
     */
    public static void setViewValue(TextView textView, String value, String defaultValue)
    {
        if (textView == null)
        {
            return;
        }
        if (TextUtils.isEmpty(value))
        {
            textView.setText(defaultValue);
        } else
        {
            textView.setText(value.trim());
        }
    }

    /**
     * @param editText 要设置值的EditText控件
     * @param value    要为EditText设置的值
     * @author：frj 功能:为EditText设置值，如果value为null，那么设置EditText的值为"" 使用方法：
     */
    public static void setViewValue(EditText editText, String value)
    {
        if (editText == null)
        {
            return;
        }
        if (TextUtils.isEmpty(value))
        {
            editText.setText("");
        } else
        {
            editText.setText(value);
        }
    }

    /**
     * @param editText  要设置值的EditText控件
     * @param value     要为EditText设置的值
     * @param hintValue 默认显示的值
     * @author：frj 功能:为EditText设置值，如果value为null，那么设置EditText的值为"" 使用方法：
     */
    public static void setViewValue(EditText editText, String value, String hintValue)
    {
        if (editText == null)
        {
            return;
        }
        if (TextUtils.isEmpty(value))
        {
            editText.setHint(hintValue);
        } else
        {
            editText.setText(value);
        }
    }

    /**
     * @param btn   要设置值的Button控件
     * @param value 要为Button设置的值
     * @author：frj 功能:为TextView设置值，如果value为null，那么设置TextView的值为"" 使用方法：
     */
    public static void setViewValue(Button btn, String value)
    {
        if (btn == null)
        {
            return;
        }
        if (TextUtils.isEmpty(value))
        {
            btn.setText("");
        } else
        {
            btn.setText(value);
        }
    }

    /**
     * 验证手机号码
     *
     * @param str
     * @return
     */
    public static boolean isCellphone(String str)
    {
        String regex = "1[0-9]{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches())
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 验证固定电话
     *
     * @param str
     * @return
     */
    public static boolean isTelephone(String str)
    {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断邮箱格式是否正确
     *
     * @param strEmail 邮箱字符串
     * @return true表示正确
     */
    public static boolean isEmail(String strEmail)
    {
        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 清除TextView的数据（单指Text数据）
     *
     * @param textViews TextView数组
     */
    public static void clearTextViewData(TextView[] textViews)
    {
        if (textViews == null)
        {
            return;
        }
        for (int i = 0; i < textViews.length; i++)
        {
            textViews[i].setText("");
        }
    }

    /**
     * 校验值是否为空，如果为空返回默认值
     *
     * @param value        需要检验的值
     * @param defaultValue 默认值
     * @return
     */
    public static String verifyDefault(String value, String defaultValue)
    {
        if (validator(value))
        {
            return value;
        } else
        {
            if (validator(defaultValue))
            {
                return defaultValue;
            } else
            {
                return DEFAULT_TIPS;
            }
        }
    }

    /**
     * 校验值是否为空，如果为空返回默认值
     *
     * @param value 需要检验的值
     * @return
     */
    public static String verifyDefault(String value)
    {
        if (validator(value))
        {
            return value;
        } else
        {
            return "";
        }
    }

    /**
     * 判断是否为空对象
     *
     * @param obj
     * @return true表示非空 false表示空
     */
    public static boolean noEmpty(Object obj)
    {
        return obj != null;
    }

    /**
     * 校验集合是否为空
     *
     * @param collection 集合
     * @return true表示非空 false表示空
     */
    public static boolean noEmpty(Collection collection)
    {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 获取集合大小
     *
     * @param collection 集合
     * @return 返回集合大小
     */
    public static int getSize(Collection collection)
    {
        if (noEmpty(collection))
        {
            return collection.size();
        }
        return 0;
    }

    /**
     * 判断字符串中是否包含表情
     *
     * @param source 字符串
     * @return true 表示包含
     */
    public static boolean containsEmoji(String source)
    {
        int len = source.length();
        for (int i = 0; i < len; i++)
        {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint))
            { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint)
    {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

}
