package com.fx.feemore.business.ui.finance;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcFinanceBinding;
import com.fx.feemore.business.ui.finance.vm.VMFinance;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.hengxian.baselib.base.BaseBindActivity;

public class AcFinance extends BaseBindActivity<AcFinanceBinding, VMFinance> {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_finance;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        initData();
    }

    private void initData() {
        setToolbarTitle(R.string.mine_amount_detail);

        binding.tvIncomeOrder.setOnClickListener(v -> jump(AcIncomeRecord.class));
        binding.tvIncomeSale.setOnClickListener(v -> jump(AcIncomeRecord.class));
        binding.btnWithdraw.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_MASTER)) {
                AlertDialogUtil.createPermissionTips(this).show();
                return;
            }
            jump(AcWithdraw.class);
        });
        binding.tvMyBankCard.setOnClickListener(v -> jump(AcMyBank.class));
        binding.tvWithdrawRecord.setOnClickListener(v -> jump(AcWithdrawRecord.class));
        binding.tvRevenueRecord.setOnClickListener(v -> jump(AcIncomeRecord.class));
    }
}
