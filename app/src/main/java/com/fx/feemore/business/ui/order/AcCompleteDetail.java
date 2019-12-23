package com.fx.feemore.business.ui.order;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AcCompleteDetailBinding;
import com.fx.feemore.business.ui.order.vm.VMCompleteDetail;
import com.fx.feemore.business.util.GlideLoad;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:订单完成详情;参数：KEY:订单实体数据
 * author: frj
 * modify date: 2019/1/18
 */
public class AcCompleteDetail extends BaseBindActivity<AcCompleteDetailBinding, VMCompleteDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_complete_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("订单详情");

        bindValue(getIntent().getParcelableExtra(KEY));
    }

    /**
     * 绑定数据
     */
    private void bindValue(OrderBean orderBean)
    {
        if (orderBean != null)
        {
            binding.setBean(orderBean);
            GlideLoad.load(binding.img, orderBean.getIMG());
            GlideLoad.loadAvartar(binding.imgAvartar, orderBean.getMEMBERIMG());
            if (1 == orderBean.getSTATUS())
            {   //已付款
                binding.tvOrderStatus.setText("已付款");
            } else
            {   //已完成
                binding.tvOrderStatus.setText("已完成");
            }
        }
    }
}
