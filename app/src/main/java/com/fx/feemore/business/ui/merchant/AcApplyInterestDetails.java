package com.fx.feemore.business.ui.merchant;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcApplyInterestDetailsBinding;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterestDetails;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:权益包申请详情
 * author: frj
 * modify date: 2019/1/2
 */
public class AcApplyInterestDetails extends BaseBindActivity<AcApplyInterestDetailsBinding, VMApplyInterestDetails>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_apply_interest_details;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.apply_interest_detail_title);
    }
}
