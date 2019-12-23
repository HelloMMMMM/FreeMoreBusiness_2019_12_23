package com.fx.feemore.business.ui.apply;

import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.ApplyRecordBean;
import com.fx.feemore.business.databinding.AdApplyRecordBinding;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.VerificationUtil;

/**
 * function:申请记录适配器
 * author: frj
 * modify date: 2018/12/24
 */
public class AdApplyRecord extends BaseDataBindingAdapter<ApplyRecordBean, AdApplyRecordBinding>
{
    public AdApplyRecord()
    {
        super(R.layout.ad_apply_record);
    }

    @Override
    protected void convert(AdApplyRecordBinding binding, ApplyRecordBean item)
    {
        binding.setItem(item);
        binding.tvName.setText(item.getStoryName());
        GlideLoad.load(binding.img, item.getImg());

        if (-1 == item.getStatus())
        {   //表示审核不通过
            binding.vProgressUndoMark.setBackgroundResource(R.mipmap.ic_turn_down_mark);
//            binding.vProgressUndoMark.setBackgroundResource(android.R.color.black);
            binding.vProgressUndo.setSelected(true);
            binding.tvPass.setText("审核被驳回");
            binding.tvRemark.setVisibility(View.VISIBLE);
            binding.tvRemark.setText("驳回原因：" + VerificationUtil.verifyDefault(item.getRemark()));
        } else
        {
            binding.vProgressUndoMark.setBackgroundResource(R.drawable.apply_record_progress_undo_mark);
            binding.vProgressUndo.setSelected(false);
            binding.tvPass.setText("审核通过");
            binding.tvRemark.setVisibility(View.GONE);
        }
    }
}
