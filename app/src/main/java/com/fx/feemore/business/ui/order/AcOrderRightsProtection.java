package com.fx.feemore.business.ui.order;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcOrderRightsProtectionBinding;
import com.fx.feemore.business.ui.order.vm.VMOrderRightsProtection;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:维权订单详情
 * author: frj
 * modify date: 2018/12/30
 */
public class AcOrderRightsProtection extends BaseBindActivity<AcOrderRightsProtectionBinding, VMOrderRightsProtection>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_order_rights_protection;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.order_rights_protection_title);
    }
}
