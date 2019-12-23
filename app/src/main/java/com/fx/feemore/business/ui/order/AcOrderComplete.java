package com.fx.feemore.business.ui.order;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcOrderCompleteBinding;
import com.fx.feemore.business.ui.order.vm.VMOrderComplete;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:已完成订单详情
 * author: frj
 * modify date: 2018/12/30
 */
public class AcOrderComplete extends BaseBindActivity<AcOrderCompleteBinding, VMOrderComplete>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_order_complete;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.order_complete_title);
    }
}
