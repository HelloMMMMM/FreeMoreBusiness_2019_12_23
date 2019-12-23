package com.fx.feemore.business.ui.interest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fx.feemore.business.R;
import com.hengxian.baselib.glide.GlideApp;

/**
 * function:发布权益包添加的图片列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class AdPhoto extends BaseMultiItemQuickAdapter<AdPhoto.Enity, BaseViewHolder>
{
    //项宽度
    private int itemWidth;

    public AdPhoto(int itemWidth)
    {
        super(null);
        addItemType(Enity.TYPE_ADD, R.layout.ad_publish_photo_add);
        addItemType(Enity.TYPE_NORMAL, R.layout.ad_publish_photo_item);
        this.itemWidth = itemWidth;
    }

    @Override
    protected void convert(BaseViewHolder helper, Enity item)
    {
        FrameLayout content = helper.getView(R.id.content);
        setViewSize(itemWidth, content);
        if (Enity.TYPE_ADD == item.getItemType())
        {

        } else if (Enity.TYPE_NORMAL == item.getItemType())
        {
            ImageView img = helper.getView(R.id.img);
            GlideApp.with(img).load(item.filePath).into(img);
        }
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

    public static class Enity implements MultiItemEntity
    {
        /**
         * 添加类型
         */
        public static final int TYPE_ADD = 1;
        /**
         * 展示类型
         */
        public static final int TYPE_NORMAL = 2;

        //类型
        private int type;
        //图片地址
        private String filePath;

        public Enity(int type)
        {
            this.type = type;
        }

        public Enity(int type, String filePath)
        {
            this.type = type;
            this.filePath = filePath;
        }

        @Override
        public int getItemType()
        {
            return type;
        }
    }
}
