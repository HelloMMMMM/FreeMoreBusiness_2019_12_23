package com.fx.feemore.business.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * function:进度对话框生成器
 * author: frj
 * modify date: 2018/9/22
 */
public class ProgressDialogUtil
{
    /**
     * 获取ProgressDialog对象
     *
     * @param activitySupport
     * @param msg
     * @param isCanCancel
     * @return
     */
    @NonNull
    public static ProgressDialog getProgressDialog(Activity activitySupport, String msg, boolean isCanCancel)
    {
        if (activitySupport == null)
        {
            return null;
        }
        ProgressDialog progressDialog = new ProgressDialog(activitySupport);
        if (!TextUtils.isEmpty(msg))
        {
            progressDialog.setMessage(msg);
        }
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(isCanCancel);
        return progressDialog;
    }
}
