package com.fx.feemore.business.ui.merchant;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcAllianceQrCodeBinding;
import com.fx.feemore.business.ui.merchant.vm.VMAllianceQRCode;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:联盟二维码界面;参数：KEY_STR：图片路径；KEY:标题
 * author: frj
 * modify date: 2019/1/2
 */
public class AcAllianceQRCode extends BaseBindActivity<AcAllianceQrCodeBinding, VMAllianceQRCode>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_alliance_qr_code;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("二维码名片");
        Toolbar toolbar = getToolbar();
        if (toolbar != null)
        {
            toolbar.setBackgroundColor(Color.parseColor("#00000000"));
            toolbar.setNavigationIcon(null);
        }
        VerificationUtil.setViewValue(binding.tvAllianceTitle, getIntent().getStringExtra(KEY));
        GlideLoad.load(binding.imgCode, getIntent().getStringExtra(KEY_STR));
        binding.vBg.setOnClickListener(v -> finish());
    }
}
