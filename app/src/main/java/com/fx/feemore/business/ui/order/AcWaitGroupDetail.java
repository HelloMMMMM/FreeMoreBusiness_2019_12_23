package com.fx.feemore.business.ui.order;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AcWaitGroupDetailBinding;
import com.fx.feemore.business.ui.order.vm.VMWaitGroupDetail;
import com.fx.feemore.business.util.GlideLoad;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:订单待成团详情
 * author: frj
 * modify date: 2019/1/18
 */
public class AcWaitGroupDetail extends BaseBindActivity<AcWaitGroupDetailBinding, VMWaitGroupDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_wait_group_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("待成团详情");

        bindValue(getIntent().getParcelableExtra(KEY));
    }

    /**
     * 绑定值
     *
     * @param orderBean
     */
    private void bindValue(OrderBean orderBean)
    {
        if (orderBean != null)
        {
            binding.setBean(orderBean);
            GlideLoad.loadAvartar(binding.imgAvartar, orderBean.getMEMBERIMG());
            GlideLoad.load(binding.img, orderBean.getIMG());

            int total = Integer.parseInt(orderBean.getTOTAL());
            int sellNum = Integer.parseInt(orderBean.getSELLNUM());

            binding.tvDiff.setText((total - sellNum) + "人");
        }
    }
}
