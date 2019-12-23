package com.fx.feemore.business.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * function:确认对话框工具类
 * author: frj
 * modify date: 2019/2/14
 */
public class AlertDialogUtil
{

    /**
     * 创建确认对话框
     *
     * @param context           上下文对象
     * @param message           确认提示内容
     * @param onConfirmListener 确认按钮点击事件
     * @param onCancelListener  取消按钮点击事件
     * @return
     */
    public static AlertDialog create(@NonNull Context context, @NonNull String message, DialogInterface.OnClickListener onConfirmListener, DialogInterface.OnClickListener onCancelListener)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(false).setPositiveButton("确定", onConfirmListener).setNegativeButton("取消", onCancelListener);
        return builder.create();
    }

    /**
     * 创建操作权限提示对话框
     *
     * @param context 上下文对象
     * @return
     */
    public static AlertDialog createPermissionTips(@NonNull Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("您没有该功能的操作权限~").setCancelable(false).setPositiveButton("确定", null);
        return builder.create();
    }
}
