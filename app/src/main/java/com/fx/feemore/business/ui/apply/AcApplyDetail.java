package com.fx.feemore.business.ui.apply;

import android.support.v7.widget.GridLayoutManager;
import android.text.Html;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.InterestInfoBean;
import com.fx.feemore.business.databinding.AcApplyDetailBinding;
import com.fx.feemore.business.ui.apply.adapter.AdApplyItem;
import com.fx.feemore.business.ui.apply.adapter.AdImgItem;
import com.fx.feemore.business.ui.apply.vm.VMApplyDetail;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.DisplayUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:申请详情;参数：KEY:权益包id；KEY_STR:权益包名称
 * author: frj
 * modify date: 2019/1/19
 */
public class AcApplyDetail extends BaseBindActivity<AcApplyDetailBinding, VMApplyDetail>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_apply_detail;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("申请详情");
        initLiveData();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 15), true, false, false));
        int itemWidth = (DisplayUtil.getScreenSize(this).widthPixels - DensityUtil.dip2px(this, 15) * 5) / 4;
        AdImgItem adImgItem = new AdImgItem(itemWidth);
        adImgItem.bindToRecyclerView(binding.recyclerView);

        viewModel.getInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), getIntent().getStringExtra(KEY));
    }

    private void initLiveData()
    {
        viewModel.failRes.observe(this, httpFailBean -> {
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.interestInfoRes.observe(this, this::bindValue);
    }

    /**
     * 绑定数据
     *
     * @param bean 权益包详情信息
     */
    private void bindValue(InterestInfoBean bean)
    {
        if (bean != null)
        {
            binding.setBean(bean);

//            if (VMApplyItem.STATUS_PASS == bean.getSTATUS())
//            {
//                binding.tvStatus.setText("审核通过");
//
//            } else if (VMApplyItem.STATUS_APPLICATION == bean.getSTATUS())
//            {
//                binding.tvStatus.setText("审核中");
//                binding.tvRemark.setText("商品正在审核，请耐心等待");
//            } else
//            {
//                binding.tvStatus.setText("审核被驳回");
//            }

            if (AdApplyItem.STATUS_PLATFORM_APPLICATION == bean.getSTATUS())
            {   //申请中
                binding.tvStatus.setText("平台审核中");
            } else if (AdApplyItem.STATUS_PLATFORM_PASS == bean.getSTATUS())
            {   //已通过
                binding.tvStatus.setText("已通过");
            } else if (AdApplyItem.STATUS_PLATFORM_REFUSED == bean.getSTATUS())
            {   //未通过
                binding.tvStatus.setText("平台审核未通过");
            } else if (AdApplyItem.STATUS_MERCHANT_REFUSED == bean.getSTATUS())
            {   //未通过
                binding.tvStatus.setText("店铺审核未通过");
            } else
            {
                binding.tvStatus.setText("店铺审核中");
            }

            binding.tvEquityType.setText(bean.getTYPE() == 1 ? "普通权益包" : "拼团权益包");

            if (bean.getTYPE() == 1)
            {   //普通权益包
                binding.tvPeopleNumTips.setText("使用人数");
                binding.tvPriceTips.setText("价格");
                VerificationUtil.setViewValue(binding.tvPeopleNum, bean.getUSERSNUM());
            } else
            {
                binding.tvPeopleNumTips.setText("成团人数");
                VerificationUtil.setViewValue(binding.tvPeopleNum, bean.getQUANTITY());
                binding.tvPriceTips.setText("团购价格");
            }

            binding.tvWhetherPresell.setText(bean.getISPRESELL() == 1 ? "预售" : "非预售");

            binding.tvAboutRefund.setText(bean.getISREFUND() == 1 ? "72小时无理由退货" : "不支持72小时无理由退货");

            binding.tvConsumeWeekTime.setText(bean.getSTARTCOSTDATE() + "至" + bean.getENDCOSTDATE() + (bean.getISCURRENCY() == 1 ? " 节假日通用" : " 节假日不可使用"));

            binding.tvDes.setText(Html.fromHtml(bean.getDESCRIPTION()));

            AdImgItem adImgItem = (AdImgItem) binding.recyclerView.getAdapter();
            adImgItem.setNewData(bean.getListPicture());
        }
    }
}
