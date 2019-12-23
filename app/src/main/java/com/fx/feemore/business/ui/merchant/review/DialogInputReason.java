package com.fx.feemore.business.ui.merchant.review;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.util.InputDialogUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:审核驳回原因输入
 * author: frj
 * modify date: 2019/5/17
 */
public class DialogInputReason
{

    private Dialog dialog;


    public DialogInputReason(Context context)
    {
        initDialog(context);
    }

    private void initDialog(Context context)
    {
        dialog = new Dialog(context, R.style.Dialog_Normal);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setContentView(createView(context));
    }

    private View createView(Context context)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_reason, null);
        EditText editText = view.findViewById(R.id.ed_reason);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(v -> {
            String inputStr = TextViewUtil.getText(editText);
            if (TextUtils.isEmpty(inputStr))
            {
                ToastUtil.show("请输入驳回原因");
                return;
            }
        });
        return view;
    }

}
