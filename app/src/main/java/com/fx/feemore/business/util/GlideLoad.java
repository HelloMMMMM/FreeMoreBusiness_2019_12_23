package com.fx.feemore.business.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fx.feemore.business.R;
import com.fx.feemore.business.http.ServiceUrlUtil;
import com.hengxian.baselib.base.BaseActivity;
import com.hengxian.baselib.glide.GlideApp;
import com.hengxian.baselib.glide.GlideRequest;
import com.hengxian.baselib.glide.GlideRequests;

/**
 * function:Glide加载工具类
 * author: frj
 * modify date: 2018/9/22
 */
public class GlideLoad {

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     */
    public static void load(ImageView img, String url) {
        load(img, url, false);
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     */
    public static void load(Fragment fragment, ImageView img, String url) {
        load(fragment, img, url, false);
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     */
    public static void load(BaseActivity activitySupport, ImageView img, String url) {
        load(activitySupport, img, url, false);
    }


    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     * @param asBitmap
     */
    public static void load(ImageView img, String url, boolean asBitmap) {
        load(img, url, asBitmap, R.drawable.ic_default_adimage, R.drawable.ic_default_adimage);
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     */
    public static void loadAvartar(ImageView img, String url) {
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }
        GlideApp.with(img).load(loadUrl).placeholder(R.mipmap.default_avatar).error(R.mipmap.default_avatar).circleCrop().into(img);
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     * @param asBitmap
     */
    public static void load(BaseActivity activitySupport, ImageView img, String url, boolean asBitmap) {
        load(activitySupport, img, url, asBitmap, R.drawable.ic_default_adimage, R.drawable.ic_default_adimage);
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     * @param asBitmap
     */
    public static void load(Fragment fragment, ImageView img, String url, boolean asBitmap) {
        load(fragment, img, url, asBitmap, R.drawable.ic_default_adimage, R.drawable.ic_default_adimage);
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     * @param asBitmap
     * @param placeholder
     * @param error
     */
    public static void load(ImageView img, String url, boolean asBitmap, @DrawableRes int placeholder, @DrawableRes int error) {
        GlideRequests requests = GlideApp.with(img);
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl =ServiceUrlUtil.getDefault() + url;
            }
        }
        if (asBitmap) {
            GlideRequest<Bitmap> glideRequest = requests.asBitmap();
            glideRequest.load(loadUrl);
            if (placeholder != 0) {
                glideRequest.placeholder(placeholder);
            }
            if (error != 0) {
                glideRequest.error(placeholder);
            }
            glideRequest.centerCrop().into(img);
        } else {
            GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
            if (placeholder != 0) {
                glideRequest.placeholder(placeholder);
            }
            if (error != 0) {
                glideRequest.error(placeholder);
            }
            glideRequest.centerCrop().into(img);
        }
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     * @param asBitmap
     * @param placeholder
     * @param error
     */
    public static void load(Fragment fragment, ImageView img, String url, boolean asBitmap, @DrawableRes int placeholder, @DrawableRes int error) {

        GlideRequests requests;
        if (fragment != null) {
            requests = GlideApp.with(fragment);
        } else {
            requests = GlideApp.with(img);
        }
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }
        if (asBitmap) {
            GlideRequest<Bitmap> glideRequest = requests.asBitmap();
            glideRequest.load(loadUrl);
            if (placeholder != 0) {
                glideRequest.placeholder(placeholder);
            }
            if (error != 0) {
                glideRequest.error(placeholder);
            }
            glideRequest.centerCrop().into(img);
        } else {
            GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
            if (placeholder != 0) {
                glideRequest.placeholder(placeholder);
            }
            if (error != 0) {
                glideRequest.error(placeholder);
            }
            glideRequest.centerCrop().into(img);
        }
    }

    /**
     * Glide加载图片
     *
     * @param img
     * @param url
     * @param asBitmap
     * @param placeholder
     * @param error
     */
    public static void load(BaseActivity activitySupport, ImageView img, String url, boolean asBitmap, @DrawableRes int placeholder, @DrawableRes int error) {

        GlideRequests requests;
        if (activitySupport != null) {
            requests = GlideApp.with(activitySupport);
        } else {
            requests = GlideApp.with(img);
        }
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }
        if (asBitmap) {
            GlideRequest<Bitmap> glideRequest = requests.asBitmap();
            glideRequest.load(loadUrl);
            if (placeholder != 0) {
                glideRequest.placeholder(placeholder);
            }
            if (error != 0) {
                glideRequest.error(placeholder);
            }
            glideRequest.centerCrop().into(img);
        } else {
            GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
            if (placeholder != 0) {
                glideRequest.placeholder(placeholder);
            }
            if (error != 0) {
                glideRequest.error(placeholder);
            }
            glideRequest.centerCrop().into(img);
        }
    }

    /**
     * Glide加载圆形图片
     *
     * @param img
     * @param url
     * @param placeholder
     * @param error
     */
    public static void loadCicle(ImageView img, String url, @DrawableRes int placeholder, @DrawableRes int error) {
        GlideRequests requests = GlideApp.with(img);
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }

        GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
        if (placeholder != 0) {
            glideRequest.placeholder(placeholder);
        }
        if (error != 0) {
            glideRequest.error(placeholder);
        }
        glideRequest.circleCrop().into(img);
    }

    /**
     * Glide加载圆形图片
     *
     * @param img
     * @param url
     * @param placeholder
     * @param error
     */
    public static void loadCicle(Fragment fragment, ImageView img, String url, @DrawableRes int placeholder, @DrawableRes int error) {
        GlideRequests requests;
        if (fragment != null) {
            requests = GlideApp.with(fragment);
        } else {
            requests = GlideApp.with(img);
        }
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }

        GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
        if (placeholder != 0) {
            glideRequest.placeholder(placeholder);
        }
        if (error != 0) {
            glideRequest.error(placeholder);
        }
        glideRequest.circleCrop().into(img);
    }

    /**
     * Glide加载圆角图片
     *
     * @param img
     * @param url
     * @param placeholder
     * @param error
     * @param radius      半径
     */
    public static void loadRound(ImageView img, String url, @DrawableRes int placeholder, @DrawableRes int error, int radius) {
        GlideRequests requests = GlideApp.with(img);
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }

        GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
        if (placeholder != 0) {
            glideRequest.placeholder(placeholder);
        }
        if (error != 0) {
            glideRequest.error(placeholder);
        }
        glideRequest.transform(new RoundedCorners(radius)).into(img);
    }


    /**
     * Glide加载圆角图片
     *
     * @param img
     * @param url
     * @param placeholder
     * @param error
     * @param radius      半径
     */
    public static void loadRound(Fragment fragment, ImageView img, String url, @DrawableRes int placeholder, @DrawableRes int error, int radius) {
        GlideRequests requests;
        if (fragment != null) {
            requests = GlideApp.with(fragment);
        } else {
            requests = GlideApp.with(img);
        }
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }

        GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
        if (placeholder != 0) {
            glideRequest.placeholder(placeholder);
        }
        if (error != 0) {
            glideRequest.error(placeholder);
        }
        glideRequest.transform(new RoundedCorners(radius)).into(img);
    }


    /**
     * 加载图片 Fitcenter模式
     *
     * @param img
     * @param url
     * @param placeholder
     * @param error
     */
    public static void loadFitCenter(ImageView img, String url, @DrawableRes int placeholder, @DrawableRes int error) {
        GlideRequests requests = GlideApp.with(img);
        String loadUrl = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                loadUrl = url;
            } else if (url.startsWith("//")) {
                loadUrl = "http:" + url;
            } else {
                loadUrl = ServiceUrlUtil.getDefault() + url;
            }
        }

        GlideRequest<Drawable> glideRequest = requests.load(loadUrl);
        if (placeholder != 0) {
            glideRequest.placeholder(placeholder);
        }
        if (error != 0) {
            glideRequest.error(placeholder);
        }
        glideRequest.fitCenter().into(img);
    }

    public static void loadCircle(Context context, String url, ImageView into) {
        Glide.with(context).load(ServiceUrlUtil.getDefault() + url).apply(new RequestOptions().circleCrop()).into(into);
    }
}


