package com.fx.feemore.business.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.hengxian.baselib.utils.DeviceInfoUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * function:请求拦截器，拼接了各种Url参数（Android平台、版本和IMEI等值）；如果已登录，在请求头里添加了Session和Secret信息
 * author: frj
 * modify date: 2016/11/18
 */

public class CurrencyInterceptor implements Interceptor
{

    private static final String TAG = "CurrencyInterceptor";

    /**
     * 平台序号参数
     */
    public static final String OSTYPE_PARAM = "ostype";

    /**
     * 版本号参数
     */
    public static final String VERSION_PARAM = "version";


    /**
     * Token
     */
    public static final String TOKEN = "tokens";

    /**
     * IEMI参数
     */
    public static final String IMEI_PARAM = "imei";

    /**
     * Android平台的序号
     */
    public static final String ANDROID_OSTYPE = "1";

    /**
     * App版本号
     */
    public static String VERSION_CODE = "";

    /**
     * 手机IMEI值
     */
    public static String imei = "";

    private Context context;

    public CurrencyInterceptor(Context context)
    {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        HttpUrl httpUrl = addParameter(request.url().newBuilder())
                .build();

        Request.Builder builder = request.newBuilder();
        builder.url(httpUrl);
        request = builder.build();

        return chain.proceed(request);
    }

    /**
     * 添加通用参数
     *
     * @param builder
     * @return
     */
    protected HttpUrl.Builder addParameter(HttpUrl.Builder builder)
    {
        builder.addQueryParameter(OSTYPE_PARAM, ANDROID_OSTYPE);
        if (TextUtils.isEmpty(VERSION_CODE))
        {
            VERSION_CODE = String.valueOf(getVersion(context));
        }
        builder.addQueryParameter(VERSION_PARAM, VERSION_CODE);
        if (TextUtils.isEmpty(imei))
        {
            imei = DeviceInfoUtil.getIMEI(context);
        }

        builder.addQueryParameter(IMEI_PARAM, imei);
        return builder;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     * @throws PackageManager.NameNotFoundException
     */
    static int getVersion(Context context)
    {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try
        {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     */
    static Boolean isNetworkReachable(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null)
        {
            return false;
        }
        return (current.isAvailable());
    }

}
