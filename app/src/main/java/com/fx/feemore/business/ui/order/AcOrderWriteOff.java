package com.fx.feemore.business.ui.order;

import android.app.ProgressDialog;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.ConsumeRes;
import com.fx.feemore.business.databinding.AcOrderWriteOffBinding;
import com.fx.feemore.business.ui.order.vm.VMOrderWriteOff;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:核销订单详情
 * author: frj
 * modify date: 2019/2/26
 */
public class AcOrderWriteOff extends BaseBindActivity<AcOrderWriteOffBinding, VMOrderWriteOff>
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_order_write_off;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("订单核销");
        initLiveData();

        binding.btnConfirm.setOnClickListener(v -> {
            if (dialog == null)
            {
                dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
            }
            if (viewModel.resConsume.getValue() == null)
            {
                ToastUtil.show("暂未获取到订单信息");
                return;
            }
            dialog.show();
            viewModel.orderWriteOff(viewModel.resConsume.getValue().getURL(), "1");
        });
        if (dialog == null)
        {
            dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
        }
        dialog.show();
        viewModel.writeOffConsume(getIntent().getStringExtra(KEY_STR));
    }

    private void initLiveData()
    {
        viewModel.resConsume.observe(this, consumeBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (consumeBean != null)
            {
                VerificationUtil.setViewValue(binding.tvNickname, consumeBean.getMEMBERNAME());
                GlideLoad.loadAvartar(binding.imgAvartar, consumeBean.getMEMBERIMG());
                bindValue(consumeBean);
            }
        });

        viewModel.resFail.observe(this, httpFailBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resSucc.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            ToastUtil.show("订单消费成功");
        });
    }

    /**
     * 绑定数据信息
     *
     * @param consumeRes
     */
    private void bindValue(ConsumeRes consumeRes)
    {
        if (consumeRes != null)
        {
            VerificationUtil.setViewValue(binding.tvInterestName, consumeRes.getCARDNAME());
            VerificationUtil.setViewValue(binding.tvPrice, String.format(getString(R.string.commodity_using_interest_price), consumeRes.getCARDPRICE()));
            VerificationUtil.setViewValue(binding.tvPayPrice, String.format(getString(R.string.commodity_using_interest_price), consumeRes.getCARDPRICE()));
            GlideLoad.load(binding.img, consumeRes.getCARDIMG());

            if (consumeRes.getBaseCardOrder() != null)
            {
                binding.vOrderSpace.setVisibility(View.VISIBLE);
                binding.tvCreateTime.setVisibility(View.VISIBLE);
                binding.tvOrderNum.setVisibility(View.VISIBLE);

                binding.tvCreateTime.setText(String.format(getString(R.string.order_rights_protection_order_time), consumeRes.getBaseCardOrder().getORDERDATE()));
                binding.tvOrderNum.setText(String.format(getString(R.string.order_rights_protection_order_num), consumeRes.getBaseCardOrder().getBASECARDORDER_ID()));
            } else
            {
                binding.vOrderSpace.setVisibility(View.GONE);
                binding.tvCreateTime.setVisibility(View.GONE);
                binding.tvOrderNum.setVisibility(View.GONE);
            }
        }
    }
}
