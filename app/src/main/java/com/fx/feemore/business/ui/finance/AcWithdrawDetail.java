package com.fx.feemore.business.ui.finance;

import android.graphics.Color;
import android.text.TextUtils;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.WithdrawRecordBean;
import com.fx.feemore.business.databinding.AcWithdrawDetailBinding;
import com.fx.feemore.business.ui.finance.vm.VMWithdrawDetail;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:提现详情;参数：KEY_STR:提现记录id
 * author: frj
 * modify date: 2018/12/31
 */
public class AcWithdrawDetail extends BaseBindActivity<AcWithdrawDetailBinding, VMWithdrawDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_withdraw_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.withdraw_title);
        initLiveData();
        viewModel.getData(getIntent().getStringExtra(KEY_STR));

        binding.btnFinish.setOnClickListener(v -> finish());
    }


    private void initLiveData()
    {
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resRecord.observe(this, this::bindValue);
    }

    /**
     * 绑定数据值
     *
     * @param
     */
    private void bindValue(WithdrawRecordBean withdrawRecordBean)
    {
        if (withdrawRecordBean != null)
        {
            binding.setBean(withdrawRecordBean);
            binding.tvWithdrawCard.setText(String.format(getString(R.string.withdraw_detail_card_tips), VerificationUtil.verifyDefault(withdrawRecordBean.getBankname()), lastFourStr(withdrawRecordBean.getBankno())));
            if (withdrawRecordBean.getStatus() == 1)
            {   //待审核
                binding.vInitate.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vHandle.setBackgroundResource(R.drawable.withdraw_detail_point_mark_unsel);
                binding.vSuccess.setBackgroundResource(R.drawable.withdraw_detail_point_mark_unsel);
                binding.vInitateHandleLine.setBackgroundColor(Color.parseColor("#B6B5B5"));
            } else if (withdrawRecordBean.getStatus() == 2)
            {   //汇款中
                binding.vInitate.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vHandle.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vSuccess.setBackgroundResource(R.drawable.withdraw_detail_point_mark_unsel);
                binding.tvTime.setText(String.format(getString(R.string.withdraw_detail_money_transfer_time), withdrawRecordBean.getRemittancetime()));
                binding.vInitateHandleLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else if (withdrawRecordBean.getStatus() == 3)
            {   //提现成功
                binding.vInitate.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vHandle.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vSuccess.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.tvTime.setText(String.format(getString(R.string.withdraw_detail_money_transfer_time), withdrawRecordBean.getRemittancetime()));
                binding.vInitateHandleLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.vHandleSuccessLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else if (withdrawRecordBean.getStatus() == 4)
            {
                binding.vInitate.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vHandle.setBackgroundResource(R.drawable.withdraw_detail_point_mark);
                binding.vSuccess.setBackgroundResource(R.drawable.withdraw_detail_point_mark_unsel);
                binding.tvBankHandleTips.setText("被驳回");
                binding.tvTime.setText(String.format(getString(R.string.withdraw_detail_refused_tips), withdrawRecordBean.getDescription()));
                binding.vInitateHandleLine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    /**
     * 返回最后四个字符串
     *
     * @param bankNo 银行卡号
     * @return
     */
    private String lastFourStr(String bankNo)
    {
        if (!TextUtils.isEmpty(bankNo) && bankNo.length() >= 4)
        {
            return bankNo.substring(bankNo.length() - 4);
        }
        return bankNo;
    }
}
