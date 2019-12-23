package com.fx.feemore.business.ui.merchant;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcApplyActiveDetailBinding;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActiveDetail;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:活动权益包申请进度详情
 * author: frj
 * modify date: 2019/1/2
 */
public class AcApplyActiveDetail extends BaseBindActivity<AcApplyActiveDetailBinding, VMApplyActiveDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_apply_active_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.apply_active_detail_title);
    }
}
