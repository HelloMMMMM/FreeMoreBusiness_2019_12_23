package com.fx.feemore.business.util;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * function:兼容7.0的popupwindow
 * author: frj
 * modify date: 2017/5/15
 */

public class CompatiblePopupwindow extends PopupWindow
{

    public CompatiblePopupwindow(View contentView)
    {
        super(contentView);
    }

    public CompatiblePopupwindow(View contentView, int width, int height)
    {
        super(contentView, width, height);
    }

    public CompatiblePopupwindow(View contentView, int width, int height, boolean focusable)
    {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchor)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);

        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }
}
