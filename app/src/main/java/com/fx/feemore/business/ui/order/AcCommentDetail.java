package com.fx.feemore.business.ui.order;

import android.app.ProgressDialog;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.AcCommentDetailBinding;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.fx.feemore.business.ui.order.vm.VMCommentDetail;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:订单评价详情;参数：KEY_STR：评论id
 * author: frj
 * modify date: 2019/1/18
 */
public class AcCommentDetail extends BaseBindActivity<AcCommentDetailBinding, VMCommentDetail> {

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_comment_detail;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle("评价详情");

        initLiveData();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 8), false, false, true, false));

        binding.btnSubmit.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR)) {
                AlertDialogUtil.createPermissionTips(this).show();
                return;
            }
            answer();
        });

        viewModel.getCommentInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), getIntent().getStringExtra(KEY_STR));
    }

    private void initLiveData() {
        viewModel.resCommentBean.observe(this, this::bindData);
        viewModel.resFail.observe(this, httpFailBean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resResponse.observe(this, responseBean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            viewModel.getCommentInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), getIntent().getStringExtra(KEY_STR));
        });
    }

    /**
     * 绑定数据
     *
     * @param bean
     */
    private void bindData(CommentBean bean) {
        if (bean != null) {
            binding.setItem(bean);
            boolean isUnAnswer = TextUtils.isEmpty(bean.getREPLY());
            binding.edAnswer.setEnabled(isUnAnswer);
            binding.btnSubmit.setEnabled(isUnAnswer);
            binding.btnSubmit.setText(isUnAnswer ? "提交" : "已回复");
            GlideLoad.loadAvartar(binding.imgAvartar, bean.getMEMBERIMG());
            GlideLoad.load(binding.img, bean.getORDERIMG());

            binding.starView.setStarRating(bean.getTYPE());
        }
    }

    /**
     * 回复
     */
    private void answer() {
        if (VerificationUtil.validator(binding.edAnswer, "请输入回复内容")) {
            if (dialog == null) {
                dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
            }
            dialog.show();
            viewModel.answerComment(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), getIntent().getStringExtra(KEY_STR), TextViewUtil.getText(binding.edAnswer));
        }
    }
}
