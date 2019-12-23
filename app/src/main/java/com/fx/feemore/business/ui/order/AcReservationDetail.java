package com.fx.feemore.business.ui.order;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcReservationDetailBinding;
import com.fx.feemore.business.ui.order.vm.VMReservationDetail;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:预约详情
 * author: frj
 * modify date: 2018/12/27
 */
public class AcReservationDetail extends BaseBindActivity<AcReservationDetailBinding, VMReservationDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_reservation_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.reservation_detail_title);
    }
}
