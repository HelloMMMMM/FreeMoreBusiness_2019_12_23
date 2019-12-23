package com.fx.feemore.business.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fx.feemore.business.R;

/**
 * function:输入对话框
 * author: frj
 * modify date: 2019/2/27
 */
public class InputDialogUtil
{

    /**
     * 创建输入对话框
     *
     * @param activity              Activity对象
     * @param title                 标题
     * @param onInputFinishListener 输入完成监听
     * @return
     */
    public static Dialog createInputDialog(Activity activity, String title, OnInputFinishListener onInputFinishListener)
    {
        if (activity == null)
        {
            return null;
        }
        Dialog dialog = new Dialog(activity, R.style.Dialog_Bottom);
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_input, null);
        EditText ed_input = view.findViewById(R.id.ed_input);
        Button btn_confirm = view.findViewById(R.id.btn_confirm);
        TextView tv_title = view.findViewById(R.id.tv_title);
        VerificationUtil.setViewValue(tv_title, title);
        btn_confirm.setOnClickListener(v -> {
            if (VerificationUtil.validator(ed_input, "请输入内容"))
            {
                if (onInputFinishListener != null)
                {
                    onInputFinishListener.onInputFinish(TextViewUtil.getText(ed_input));
                }
                hideSoftInput(ed_input);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        return dialog;
    }

    /**
     * 隐藏软件盘
     *
     * @param editText 焦点控件
     */
    private static void hideSoftInput(EditText editText)
    {
        if (editText == null)
        {
            return;
        }
        if (editText.getWindowToken() != null && editText.getContext() != null)
        {
            InputMethodManager im = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (im != null)
            {
                im.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 输入完成监听
     */
    public interface OnInputFinishListener
    {
        void onInputFinish(String result);
    }
}
