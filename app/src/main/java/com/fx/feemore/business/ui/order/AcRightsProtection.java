package com.fx.feemore.business.ui.order;

import android.app.ProgressDialog;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.databinding.AcRightsProtectionBinding;
import com.fx.feemore.business.ui.order.vm.VMRightsProtectionDetail;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.InputDialogUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:订单维权详情;参数：KEY_STR:订单编号
 * author: frj
 * modify date: 2019/1/18
 */
public class AcRightsProtection extends BaseBindActivity<AcRightsProtectionBinding, VMRightsProtectionDetail>
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_rights_protection;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("维权详情");

        initLiveData();
        initListener();

        viewModel.getOrderDetail(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), getIntent().getStringExtra(KEY_STR));
    }

    private void initLiveData()
    {
        viewModel.resBean.observe(this, this::bindValue);
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
        viewModel.resResponseBean.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            viewModel.getOrderDetail(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), getIntent().getStringExtra(KEY_STR));
        });
    }

    private void initListener()
    {
        binding.btnAgree.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(this).show();
                return;
            }
            AlertDialogUtil.create(this, "您确定要同意退款吗", (dialog1, which) -> {
                agreeRefund(getIntent().getStringExtra(KEY_STR));
            }, null);
        });
        binding.btnRefuse.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(this).show();
                return;
            }
            AlertDialogUtil.create(this, "您确定要驳回退款吗", (dialog1, which) ->
                    refusedRefundInputReason(getIntent().getStringExtra(KEY_STR)), null);
        });
    }

    /**
     * 绑定数据
     *
     * @param bean
     */
    private void bindValue(OrderRefundBean bean)
    {
        if (bean != null)
        {
            binding.setItem(bean);
            GlideLoad.load(binding.img, bean.getIMG());
            GlideLoad.loadAvartar(binding.imgAvartar, bean.getMEMBERIMG());
            if (5 == bean.getSTATUS())
            {
                binding.tvStatus.setText("待退款");
                binding.tvStatusDes.setText("等待商家退款");
            } else if (6 == bean.getSTATUS())
            {
                binding.tvStatus.setText("已退款");
                binding.tvStatusDes.setText("退款成功");
            } else if (7 == bean.getSTATUS())
            {
                binding.tvStatus.setText("已驳回");
                binding.tvStatusDes.setText(bean.getMESSAGE());
            }
        }
    }

    /**
     * 同意维权订单退款
     *
     * @param orderId 订单id
     */
    private void agreeRefund(String orderId)
    {
        if (dialog == null)
        {
            dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
        }
        dialog.show();
        viewModel.agreeRefund(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), orderId);
    }

    /**
     * 驳回维权订单退款输入原因
     *
     * @param orderId 订单id
     */
    private void refusedRefundInputReason(String orderId)
    {
        InputDialogUtil.createInputDialog(this, "驳回原因", result ->
                refusedRefund(orderId, result)).show();
    }


    /**
     * 驳回维权订单退款
     *
     * @param orderId 订单id
     */
    private void refusedRefund(String orderId, String reason)
    {
        if (dialog == null)
        {
            dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
        }
        dialog.show();
        viewModel.refusedRefund(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), orderId, reason);
    }

}
