package com.fx.feemore.business.ui.merchant.set;

import android.content.Intent;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseActivity;

/**
 * function:账户与安全
 * author: frj
 * modify date: 2019/1/22
 */
public class AcSecurity extends BaseActivity
{
    private TextView textView;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_security;
    }

    @Override
    protected void init()
    {
        setToolbarTitle("账户与安全");

        textView = findViewById(R.id.tv_account);
        VerificationUtil.setViewValue(textView, AppContext.getInstanceBase().getStoryUserBean().getACCOUNT());
        textView.setOnClickListener(v -> jump(AcChangePhone.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (RequestAndResultCode.REQUEST_NEED_REFRESH_CODE == requestCode && RESULT_OK == resultCode)
        {
            if (data != null)
            {
                String phone = data.getStringExtra(KEY);
                VerificationUtil.setViewValue(textView, phone);
            }
        }
    }
}
