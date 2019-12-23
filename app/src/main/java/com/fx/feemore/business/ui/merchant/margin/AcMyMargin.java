package com.fx.feemore.business.ui.merchant.margin;

import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.databinding.AcMyMarginBinding;
import com.fx.feemore.business.ui.apply.AcPayDeposit;
import com.fx.feemore.business.ui.merchant.margin.vm.VMMyMargin;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.baselib.utils.Arithmetic;

/**
 * function:我的保证金页面
 * author: frj
 * modify date: 2019/1/22
 */
public class AcMyMargin extends BaseBindActivity<AcMyMarginBinding, VMMyMargin>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_my_margin;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("保证金");
        bindValue();

        binding.vMargin.setOnClickListener(this::gotoPayDeposit);
        binding.vMarketing.setOnClickListener(this::gotoPayDeposit);
    }

    /**
     * 去缴纳押金
     *
     * @param view
     */
    private void gotoPayDeposit(View view)
    {
        if (AppContext.getInstanceBase().getStoryUserBean().getMARGIN() > 0 && AppContext.getInstanceBase().getStoryUserBean().getFREEZE() > 0)
        {
            AlertDialogUtil.create(this, "您已缴纳押金和营销费", null, null).show();
        } else
        {
            jump(AcPayDeposit.class);
        }
    }

    private void bindValue()
    {
        StoryUserBean bean = AppContext.getInstanceBase().getStoryUserBean();
        if (bean != null)
        {
            binding.tvMarginAreaValue.setText(Arithmetic.doubleToStr(bean.getFREEZE(), 2));
            binding.tvMarketingAreaValue.setText(Arithmetic.doubleToStr(bean.getMARGIN(), 2));
            binding.tvMarginValue.setText(Arithmetic.doubleToStr(Arithmetic.add(bean.getFREEZE(), bean.getMARGIN()), 2));

        }
    }
}
