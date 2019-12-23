package com.fx.feemore.business.ui.commodity;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.ConsumeRecordBean;
import com.fx.feemore.business.databinding.AcConsumeDetailBinding;
import com.fx.feemore.business.ui.commodity.vm.VMConsumeDetail;
import com.fx.feemore.business.util.GlideLoad;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:消费详情
 * author: frj
 * modify date: 2019/3/2
 */
public class AcConsumeDetail extends BaseBindActivity<AcConsumeDetailBinding, VMConsumeDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_consume_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("消费详情");
        bindValue(getIntent().getParcelableExtra(KEY));
    }

    /**
     * 绑定数据
     *
     * @param bean
     */
    private void bindValue(ConsumeRecordBean bean)
    {
        if (bean != null)
        {
            binding.setBean(bean);
            GlideLoad.load(binding.img, bean.getCARDIMG());
            GlideLoad.loadAvartar(binding.imgAvartar, bean.getMEMBERIMG());
        }
    }
}
