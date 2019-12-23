package com.hengxian.baselib.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hengxian.baselib.glide.GlideApp;
import com.hengxian.baselib.glide.GlideRequest;

/**
 * 自定义DataBinding属性
 */
public class ViewBindings
{

    private ViewBindings()
    {
        throw new AssertionError("No instances.");
    }

    /**
     * @param view          ImageView
     * @param url           图片路径
     * @param placeHolder   加载占位图
     * @param error         错误占位图
     * @param thumbnailUrl  缩略图路径
     * @param skipMemory    跳过内存缓存
     * @param skipDisk      跳过磁盘
     * @param skipCache     不做缓存
     * @param loadOnlyCache 仅从缓存加载
     * @param isCircle      是否是圆形图片
     */
    @BindingAdapter(value = {"src", "placeHolder", "error", "thumbnailUrl", "skipMemory", "skipDisk",
            "skipCache", "loadOnlyCache", "isCircle"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable placeHolder, Drawable error,
                                 String thumbnailUrl, boolean skipMemory, boolean skipDisk, boolean skipCache,
                                 boolean loadOnlyCache, boolean isCircle)
    {
        // 跳过缓存
        if (skipCache)
        {
            skipMemory = true;
            skipDisk = true;
        }
        GlideRequest<Drawable> request = GlideApp.with(view)
                .load(url)
                .placeholder(placeHolder)
                .error(error)
                .thumbnail(thumbnailUrl == null ? null : GlideApp.with(view).load(thumbnailUrl))
                .onlyRetrieveFromCache(loadOnlyCache)
                .skipMemoryCache(skipMemory);
        //判断是否要显示成圆形图片
        if (isCircle)
        {
            request.circleCrop();
        }
        if (skipDisk)
        {
            request.diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        request.into(view);
    }

    /**
     * @param view
     * @param isGone
     */
    @BindingAdapter("gone")
    public static void gone(View view, boolean isGone)
    {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }

    /**
     * @param view
     * @param isHide
     */
    @BindingAdapter("hide")
    public static void hide(View view, boolean isHide)
    {
        view.setVisibility(isHide ? View.INVISIBLE : View.VISIBLE);
    }

    /**
     * 自定义定义背景属性：背景色，边框颜色、宽度、虚线间距
     *
     * @param view
     * @param shape       {@linkplain GradientDrawable}
     * @param solidColor  填充色
     * @param strokeWidth 边框宽度
     * @param strokeColor 边框颜色
     * @param dashWidth   虚线宽度
     * @param dashGap     虚线间距
     * @param radius      圆角 设置dp值，内部会转化为px
     * @param radiusRes   圆角 引用资源
     */
    @SuppressWarnings("deprecation")
    @BindingAdapter(value = {"shape", "solidColor", "strokeWidth", "strokeColor", "dashWidth", "dashGap",
            "radius", "radiusRes"}, requireAll = false)
    public static void background(View view, int shape, @ColorInt int solidColor,
                                  int strokeWidth, @ColorInt int strokeColor, float dashWidth, float dashGap,
                                  Integer radius, Float radiusRes)
    {
        Context context = view.getContext();
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(shape);
        drawable.setColor(solidColor);
        drawable.setStroke(dpToPx(context, strokeWidth), strokeColor, dpToPx(context, dashWidth), dpToPx(context, dashGap));
        if (radius != null)
        {
            drawable.setCornerRadius(dpToPx(context, radius));
        }
        if (radiusRes != null)
        {
            drawable.setCornerRadius(radiusRes);
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
        {
            view.setBackground(drawable);
        } else
        {
            view.setBackgroundDrawable(drawable);
        }
    }

    private static int dpToPx(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
