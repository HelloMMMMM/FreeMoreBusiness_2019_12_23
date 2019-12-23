package com.fx.feemore.business.ui.interest;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcInterestDetailBinding;
import com.fx.feemore.business.ui.interest.vm.VMInterestDetail;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:权益详情
 * author: frj
 * modify date: 2018/12/30
 */
public class AcInterestDetail extends BaseBindActivity<AcInterestDetailBinding, VMInterestDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_interest_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.interest_detail_title);
    }
}
