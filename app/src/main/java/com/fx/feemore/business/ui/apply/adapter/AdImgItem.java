package com.fx.feemore.business.ui.apply.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.PictureBean;
import com.fx.feemore.business.databinding.AdImgItemBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:图片列表适配器
 * author: frj
 * modify date: 2019/2/17
 */
public class AdImgItem extends BaseDataBindingAdapter<PictureBean, AdImgItemBinding>
{
    //项宽度
    private int itemWidth;

    public AdImgItem(int itemWidth)
    {
        super(R.layout.ad_img_item);
        this.itemWidth = itemWidth;
    }

    @Override
    protected void convert(AdImgItemBinding binding, PictureBean item)
    {
        binding.setItem(item);
        setViewSize(itemWidth, binding.content);

        GlideLoad.load(binding.img, item.getPATH());
    }

    /**
     * 设置控件大小
     *
     * @param width 控件尺寸，宽高相等
     * @param view  控件
     */
    private void setViewSize(int width, View view)
    {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params.width != width || params.height != width)
        {
            params.width = width;
            params.height = width;
            view.setLayoutParams(params);
        }
    }
}
