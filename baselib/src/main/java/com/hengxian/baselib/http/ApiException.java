package com.hengxian.baselib.http;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * 自定义网络异常
 *
 * @author wfq
 * @date 2018/6/7
 */
public class ApiException extends RuntimeException
{

    /**
     * 网络连接超时提示，外界可根据多语言提示修改
     */
    public static String EXCEPTION_NETWORK_TIMEOUT = "o(╯□╰)o您的网络状况不佳，请稍后再试...";

    /**
     * Json解析失败提示，外界可根据多语言提示修改
     */
    public static String EXCEPTION_DATA_ERROR = "(⊙o⊙)…数据出现异常啦...";

    private int code;
    private String description;

    public ApiException()
    {
    }

    public ApiException(String message)
    {
        super(message);
    }

    public ApiException(String message, Throwable cause)
    {
        super(message, cause);
        handleException(cause);
    }

    public ApiException(Throwable cause)
    {
        super(cause);
        handleException(cause);
    }

    public int getCode()
    {
        return code;
    }

    public ApiException setCode(int code)
    {
        this.code = code;
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public ApiException setDescription(String description)
    {
        this.description = description;
        return this;
    }

    /**
     * 处理常见的客户端网络连接、数据解析异常
     *
     * @param cause 异常原因
     */
    private void handleException(Throwable cause)
    {
        if (cause != null)
        {
            if (cause instanceof HttpException)
            {
                HttpException httpException = (HttpException) cause;
                code = httpException.code();
                description = httpException.message();
            } else if (cause instanceof ConnectException || cause instanceof SocketTimeoutException)
            {
                description = EXCEPTION_NETWORK_TIMEOUT;
            } else if (cause instanceof IllegalStateException || cause instanceof JsonSyntaxException || cause instanceof MalformedJsonException)
            {
                description = EXCEPTION_DATA_ERROR;
            } else
            {
                description = cause.getMessage();
            }
        }
    }
}
