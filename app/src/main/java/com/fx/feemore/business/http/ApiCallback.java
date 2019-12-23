package com.fx.feemore.business.http;


import com.fx.feemore.business.BuildConfig;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.http.ApiException;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 自定义网络回调，只暴露 onNext()、onFail() 方法
 *
 * @author wfq
 * @date 2018/6/7
 */
public abstract class ApiCallback<T> implements Observer<T>
{

    /**
     * 网络超时提示语
     */
    static final String TIMEOUT_TIPS = "o(╯□╰)o您的网络状况不佳，请稍后再试...";

    /**
     * 数据异常提示语
     */
    static final String DATA_ERROR_TIPS = "(⊙o⊙)…数据出现异常啦...";

    /**
     * 默认提示语
     */
    static final String DEFAULT_MSG = "网络请求失败";

    @Override
    public void onSubscribe(Disposable d)
    {

    }

    @Override
    public void onError(Throwable e)
    {
        if (BuildConfig.DEBUG)
        {
            e.printStackTrace();
        }
        ApiException apiException;
        if (e instanceof ApiException)
        {
            apiException = (ApiException) e;
        } else
        {
            String msg = checkFail(e);
            apiException = new ApiException(e);
            apiException.setDescription(msg);
        }
        HttpFailBean failBean = new HttpFailBean(apiException.getCode(), apiException.getDescription());
        preHandleFail(failBean);
        onFail(failBean);
    }

    @Override
    public void onComplete()
    {

    }

    /**
     * 检查异常原因
     *
     * @param t
     * @return
     */
    private String checkFail(Throwable t)
    {
        if (t instanceof SocketTimeoutException)
        {
            return TIMEOUT_TIPS;
        } else if (t instanceof IllegalStateException || t instanceof JsonSyntaxException || t instanceof MalformedJsonException)
        {
            return DATA_ERROR_TIPS;
        } else if (t instanceof HttpException)
        {
            HttpException httpException = (HttpException) t;
            //表示网络禁用或缓存不可用时
            if (httpException.code() == 504)
            {
                return DEFAULT_MSG;
            } else
            {
                return DATA_ERROR_TIPS;
            }
        } else
        {
            return DEFAULT_MSG;
        }
    }

    /**
     * 失败回调
     *
     * @param httpFail
     */
    public abstract void onFail(HttpFailBean httpFail);

    /**
     * 统一预处理错误
     *
     * @param httpFail
     */
    private void preHandleFail(HttpFailBean httpFail)
    {
    }
}
