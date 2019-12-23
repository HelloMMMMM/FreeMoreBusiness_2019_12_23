package com.fx.feemore.business.ui.merchant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcInitateAllianceBinding;
import com.fx.feemore.business.ui.merchant.vm.VMInitateAlliance;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.baselib.glide.GlideApp;
import com.hengxian.common.ToastUtil;
import com.shell.union.selectimage.ConstantValue;
import com.shell.union.selectimage.MultiImageSelectorActivity;

import java.util.ArrayList;

/**
 * function:发起联盟
 * author: frj
 * modify date: 2019/1/2
 */
public class AcInitateAlliance extends BaseBindActivity<AcInitateAllianceBinding, VMInitateAlliance>
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_initate_alliance;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.initate_alliance_title);

        initLiveData();

        binding.llCode.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ConstantValue.EXTRA_TITLE_COLOR, Color.WHITE);
            bundle.putInt(ConstantValue.EXTRA_SELECT_MODE, ConstantValue.MODE_SINGLE);
            jump(MultiImageSelectorActivity.class, bundle, ConstantValue.REQUEST_CODE);
        });
        binding.btnConfirm.setOnClickListener(v -> publish());
    }

    private void initLiveData()
    {
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
            ToastUtil.show("发布成功");
            setResult(RESULT_OK);
            finish();
        });
    }

    /**
     * 发布
     */
    private void publish()
    {
        if (VerificationUtil.validator(binding.edTitle, "请输入联盟主题"))
        {
            String filePath = (String) binding.imgCode.getTag();
            if (TextUtils.isEmpty(filePath))
            {
                ToastUtil.show("请选择联盟二维码图片");
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
            viewModel.publish(TextViewUtil.getText(binding.edTitle), filePath);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (ConstantValue.REQUEST_CODE == requestCode && RESULT_OK == resultCode)
        {
            if (data == null)
            {
                return;
            }
            ArrayList<String> list = data.getStringArrayListExtra(ConstantValue.EXTRA_RESULT);
            if (VerificationUtil.noEmpty(list))
            {
                GlideApp.with(this).load(list.get(0)).into(binding.imgCode);
                binding.imgCode.setTag(list.get(0));
            }
        }
    }
}
