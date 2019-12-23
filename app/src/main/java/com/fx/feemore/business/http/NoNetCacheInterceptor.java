package com.fx.feemore.business.http;

import com.fx.feemore.business.app.AppContext;
import com.hengxian.common.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 无网缓存拦截器
 */
public class NoNetCacheInterceptor implements Interceptor
{

    @Override
    public Response intercept(Chain chain) throws IOException
    {

        Request request = chain.request();
        boolean connected = NetUtil.isNetConnected(AppContext.getInstanceBase());
        //如果没有网络，则启用 FORCE_CACHE
        if (!connected)
        {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

            Response response = chain.proceed(request);
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=3600")
                    .removeHeader("Pragma")
                    .build();
        }
        //有网络的时候，这个拦截器不做处理，直接返回
        return chain.proceed(request);
    }
}
