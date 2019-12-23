package com.fx.feemore.business.ui.merchant;


import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcActiveDetailBinding;
import com.fx.feemore.business.ui.merchant.vm.VMActiveDetail;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:活动详情
 * author: frj
 * modify date: 2018/12/31
 */
public class AcActiveDetail extends BaseBindActivity<AcActiveDetailBinding, VMActiveDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_active_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.active_detail_title);
    }
}
