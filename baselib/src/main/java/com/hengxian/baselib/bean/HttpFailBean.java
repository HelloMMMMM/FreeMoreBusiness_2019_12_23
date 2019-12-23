package com.hengxian.baselib.bean;

import android.support.annotation.NonNull;

/**
 * 网络请求失败消息体
 *
 * @author wfq
 * @date 2018/6/7
 */
public class HttpFailBean
{

    private int code;
    private String text;

    public HttpFailBean(int code, String text)
    {
        this.code = code;
        this.text = text;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    @NonNull
    public String getText()
    {
        return text == null ? "" : text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
