package com.fx.feemore.business.ui.merchant.set;

import android.content.Intent;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.ui.character.AcCharacterSelected;
import com.fx.feemore.business.ui.merchant.about.AcAbout;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.JPushUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.hengxian.baselib.base.BaseActivity;

/**
 * function:商家设置
 * author: frj
 * modify date: 2019/1/22
 */
public class AcMerchantSet extends BaseActivity
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_merchant_set;
    }

    @Override
    protected void init()
    {
        setToolbarTitle("设置");
        findViewById(R.id.tv_security).setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(this).show();
                return;
            }
            jump(AcSecurity.class);
        });

        findViewById(R.id.tv_about).setOnClickListener(v -> jump(AcAbout.class));

        findViewById(R.id.btn_logout).setOnClickListener(v ->
                AlertDialogUtil.create(this, "您确定要退出登录吗？", (dialog, which) -> logout(), null).show());
    }

    /**
     * 退出登录
     */
    private void logout()
    {
        AppContext.getInstanceBase().setStoryUserBean(null);
        JPushUtil.jpushLogout();
        Intent intent = new Intent(this, AcCharacterSelected.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
