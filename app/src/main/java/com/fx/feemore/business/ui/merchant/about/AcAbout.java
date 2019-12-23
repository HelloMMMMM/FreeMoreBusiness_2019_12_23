package com.fx.feemore.business.ui.merchant.about;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcAboutBinding;
import com.fx.feemore.business.ui.merchant.about.vm.VMAbout;
import com.hengxian.baselib.base.BaseBindActivity;
import com.tencent.bugly.beta.Beta;

/**
 * function:关于免好多
 * author: frj
 * modify date: 2019/2/27
 */
public class AcAbout extends BaseBindActivity<AcAboutBinding, VMAbout>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_about;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("关于免好多");

        binding.tvUpdateTips.setOnClickListener(v -> //手动检查更新
                Beta.checkUpgrade());
    }
}
