package com.fx.feemore.business.ui.finance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.AcWithdrawBinding;
import com.fx.feemore.business.ui.finance.vm.VMWithdraw;
import com.fx.feemore.business.util.BankCardNoUtil;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.CashierInputFilter;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.baselib.utils.Arithmetic;
import com.hengxian.common.ToastUtil;

/**
 * function:提现界面
 * author: frj
 * modify date: 2018/12/31
 */
public class AcWithdraw extends BaseBindActivity<AcWithdrawBinding, VMWithdraw>
{
    /**
     * 获取银行卡信息失败时，选择银行卡按钮提示语
     */
    private static final String RETRY_TIPS = "获取失败，点击重试";
    /**
     * 获取银行卡信息时，选择银行卡按钮提示语
     */
    private static final String GETING_TIPS = "获取中...";
    /**
     * 获取银行卡信息时，如果银行卡信息为空，那么显示
     */
    private static final String ADD_CARD_TIPS = "添加银行卡";

    private ProgressDialog dialog;

    //提现比值,STE兑人民币
    private double ratio;
    //可提现数量
    private double canWithdrawNum;
    //手续费比例
    private double feesRatio;
    //最少手续费
    private double minFees;

    //手续费提示
    private String feeTips;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_withdraw;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.withdraw_title);

        initLiveData();
        initListener();

        binding.tvBalance.setText(String.format(getString(R.string.withdraw_banlance_text), AppContext.getInstanceBase().getStoryUserBean().getBALANCE()));

        binding.tvTargetCard.setText(GETING_TIPS);
        viewModel.getBankCards();
        viewModel.getWithdrawIntro();
    }

    private void initLiveData()
    {
        viewModel.resBankHttpFail.observe(this, httpFailBean -> {
            binding.tvTargetCard.setText(RETRY_TIPS);
        });
        viewModel.resBankCards.observe(this, list -> {
            if (!VerificationUtil.noEmpty(list))
            {
                binding.tvTargetCard.setText(ADD_CARD_TIPS);
            } else
            {
                binding.tvTargetCard.setText("请选择");
            }
        });

        viewModel.resHttpFail.observe(this, httpFailBean -> {
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
            ToastUtil.show("提现成功");
            BroadCastUtil.sendRefreshStoreInfo();
            binding.edMoney.setText("");
            jump(AcWithdrawRecord.class);
        });

        viewModel.resWithdrawIntro.observe(this, withdrawIntroBean -> {
            if (withdrawIntroBean != null)
            {
                binding.tvWithdrawDes.setVisibility(View.VISIBLE);
                binding.tvWithdrawDes.setText(withdrawIntroBean.getExplaination());
                binding.tvBalance.setText(String.format(getString(R.string.withdraw_banlance_text), Arithmetic.doubleToStr(withdrawIntroBean.getAmount(), 2)));

                feeTips = withdrawIntroBean.getExplaination();
                ratio = withdrawIntroBean.getRatio();
                canWithdrawNum = withdrawIntroBean.getAmount();
                feesRatio = withdrawIntroBean.getFeesratio();
                minFees = withdrawIntroBean.getMinfees();
            }
        });
    }

    private void initListener()
    {
        binding.tvTargetCard.setOnClickListener(v -> {
            String text = TextViewUtil.getText(binding.tvTargetCard);
            if (RETRY_TIPS.equals(text))
            {
                viewModel.getBankCards();
            } else if (GETING_TIPS.equals(text))
            {

            } else if (ADD_CARD_TIPS.equals(text))
            {
                jump(AcAddCard.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
            } else
            {
                DialogSelectCard dialog = DialogSelectCard.newInstance(viewModel.resBankCards.getValue());
                dialog.setOnItemSelectListener((pos, bean) -> {
                    if (bean != null)
                    {
                        binding.tvTargetCard.setText(String.format(getString(R.string.withdraw_detail_card_tips), VerificationUtil.verifyDefault(bean.getBankname()), BankCardNoUtil.getLastFourNo(bean.getBankno())));
                        binding.tvTargetCard.setTag(bean.getBankno());
                    } else
                    {
                        binding.tvTargetCard.setText("请选择");
                        binding.tvTargetCard.setTag("");
                    }
                });
                dialog.show(getSupportFragmentManager(), "DialogSelectCard");
            }
        });

        binding.btnWithdraw.setOnClickListener(v -> withdraw());
        binding.tvWithdrawAll.setOnClickListener(v -> {
            double ste = withdrawAll();
            setWithdrawFeesTips(ratio, ste, Arithmetic.mul(canWithdrawNum, ste), minFees, feesRatio);
            binding.edMoney.setText(Arithmetic.doubleToStr(canWithdrawNum));
        });

        binding.edMoney.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                try
                {
                    if (!TextUtils.isEmpty(s))
                    {
                        double ste = Double.parseDouble(s.toString());
                        if (Double.compare(ste, canWithdrawNum) > 0)
                        {
                            s.clear();
                            s.append(Arithmetic.doubleToStr(canWithdrawNum));
                            return;
                        }
                        if (Double.compare(ste, minFees) <= 0)
                        {
//                            s.clear();
                            ToastUtil.show("手续费最低：￥" + minFees + " , 请大于最低手续费值");
                            VerificationUtil.setViewValue(binding.tvWithdrawDes, feeTips);
                            return;
                        }
                        double fee = calFees(ste);
                        setWithdrawFeesTips(ratio, ste - Math.max(fee, minFees), calFees(ste), minFees, feesRatio);
                    } else
                    {
                        if (!TextUtils.isEmpty(feeTips))
                        {
                            VerificationUtil.setViewValue(binding.tvWithdrawDes, feeTips);
                        }
                    }
                } catch (NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
        });

        CashierInputFilter.addFilter(binding.edMoney);
    }

    /**
     * 提现
     */
    private void withdraw()
    {
        if (VerificationUtil.validator(binding.edMoney, "输入您要提现的金额"))
        {
            String tag = (String) binding.tvTargetCard.getTag();
            if (TextUtils.isEmpty(tag))
            {
                ToastUtil.show("请选择到账银行卡");
                return;
            }
            try
            {
                double money = Double.parseDouble(TextViewUtil.getText(binding.edMoney));
                if (Double.compare(money, minFees) <= 0)
                {
                    ToastUtil.show("提现金额要大于最低手续费");
                    return;
                }
                if (dialog == null)
                {
                    dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
                }
                if (!dialog.isShowing())
                {
                    dialog.show();
                }
                viewModel.withdraw(tag, TextViewUtil.getText(binding.edMoney),AppContext.getInstanceBase().getStoryUserBean().getACCOUNT());
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * 计算手续费
     *
     * @param ste 提现数量
     * @return
     */
    private double calFees(double ste)
    {
        return Arithmetic.mul(ste, feesRatio / 100);
    }

    /**
     * 全部提现
     *
     * @return
     */
    private double withdrawAll()
    {
        double all = Arithmetic.div(canWithdrawNum, 1 + feesRatio / 100, 2);
        return Math.abs(all);
    }

    /**
     * 设置提现手续费提示
     *
     * @param ratio     STE兑人民币比值
     * @param fees      手续费
     * @param minFees   最少手续费
     * @param feesRatio 手续费比值
     */
    private void setWithdrawFeesTips(double ratio, double money, double fees, double minFees, double feesRatio)
    {
//        Arithmetic.doubleToStr(ratio),
        String tips = String.format(getString(R.string.withdraw_fees_tips), Arithmetic.doubleToStr(money), Arithmetic.doubleToStr(Math.max(fees, minFees)), Arithmetic.doubleToStr(feesRatio));
        VerificationUtil.setViewValue(binding.tvWithdrawDes, tips);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (RequestAndResultCode.REQUEST_NEED_REFRESH_CODE == requestCode && RESULT_OK == resultCode)
        {
            binding.tvTargetCard.setText(GETING_TIPS);
            viewModel.getBankCards();
        }
    }
}
